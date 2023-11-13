package Controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.Constantes;
import util.UtilDate;
import Models.Paciente;

public class ControladorPaciente {

    /*
     * Se requiere tener identificación (cédula o pasaporte), nombres, apellidos,
     * fecha de nacimiento
     * Se debe registrar datos de contacto: número téléfono de contacto, correo
     * electrónico
     * Para el caso de menores de edad, se debe registrar un adulto apoderado con
     * los mismos datos que un paciente y los datos de contacto serán los del
     * apoderado en lugar de los datos del paciente
     * -Los apoderados deben ser mayores de edad
     * Un paciente no puede tener citas simultáneas ni en el mismo servicio ni en
     * distintos servicios
     */

    public String validaDatosPaciente(Paciente paciente) {
        String mensajeError = "";
        if (!(paciente.getTipoIdentificacion().equals(Constantes.TIPO_CEDULA)
                || paciente.getTipoIdentificacion().equals(Constantes.TIPO_PASAPORTE))) {
            mensajeError = "El tipo identificacion solo puede ser C o P";
        }

        switch (paciente.getTipoIdentificacion()) {
            case Constantes.TIPO_CEDULA:
                if (paciente.getIdentificacion().length() != 10) {
                    mensajeError = "La cédula debe tener 10 dígitos";
                } else {
                    if (!paciente.getIdentificacion().matches("\\d{10}")) {
                        mensajeError = "La cédula debe contener solo números";
                    }
                }
                break;
            case Constantes.TIPO_PASAPORTE:
                if (paciente.getIdentificacion().length() != 12) {
                    mensajeError = "El pasaporte debe tener 12 dígitos";
                } else {
                    if (!paciente.getIdentificacion().matches("\\d{12}")) {
                        mensajeError = "El pasaporte debe contener solo números";
                    }
                }
                break;
        }

        if (paciente.getNombre().isEmpty()) {
            mensajeError = "El nombre del paciente es campo obligatorio";
        }

        Pattern pattern = Pattern.compile(Constantes.FORMATO_FECHA);
        Matcher igualFormato = pattern.matcher(paciente.getFechaNacimiento());
        if (!igualFormato.matches()) {
            mensajeError = "el formato de fecha de nacimiento no es valido (yyyy-MM-dd)";
        }

        if (paciente.getTelefono().length() != 10) {
            mensajeError = "el telefono debe tener 10 dígitos";
        } else {
            if (!paciente.getTelefono().matches("\\d{10}")) {
                mensajeError = "El telefono debe contener solo números";
            }
        }

        return mensajeError;
    }

    public boolean requiereApoderado(String tipoPaciente, String apoderado) {
        return tipoPaciente.equals(Constantes.PACIENTE_MENOR) && apoderado.isEmpty();
    }

    public boolean esMayorEdadApoderado(String fechaNacimiento) {
        LocalDate fechaLocalDate = UtilDate.convierteFechaStringtoLocalDate(fechaNacimiento);
        return ChronoUnit.YEARS.between(fechaLocalDate,
                UtilDate.obtieneFechaActualLocalDate()) >= Constantes.EDAD_ADULTO;
    }

    public boolean existeCitaSimultanea(String fechaCita, String horaCita, String identificacion) {
        boolean existeCita = false;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Constantes.NOMBRE_ARCHIVO_INPUT));
            String linea;
            boolean enRango = false;

            while ((linea = br.readLine()) != null) {
                if (linea.contains(fechaCita)) {
                    enRango = true;
                } else if (enRango && linea.matches("\\d{2}:\\d{2}\\|.*")) {
                    String[] valores = linea.split("\\|");

                    if (identificacion.equals(valores[6])
                            && (UtilDate.restarMinutosHoraCita(horaCita, 20).equals(valores[0])
                                    || UtilDate.sumarMinutosHoraCita(horaCita, 20).equals(valores[0]))) {
                        existeCita = true;
                    }
                } else if (enRango && linea.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    enRango = false;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return existeCita;
    }
}