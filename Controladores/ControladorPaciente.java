package Controladores;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (paciente.getTipoIdentificaion().length() != 1) {
            mensajeError = "El tipo de identificacion solo es un caracter C o P";
        }
        if (!(paciente.getTipoIdentificaion().equals("C") || paciente.getTipoIdentificaion().equals("P"))) {
            mensajeError = "El tipo identificacion solo puede ser C o P";
        }

        switch (paciente.getTipoIdentificaion()) {
            case "C":
                if (paciente.getIdentificacion().length() != 10) {
                    mensajeError = "La cédula debe tener 10 dígitos";
                }
                if (!paciente.getIdentificacion().matches("\\d{10}")) {
                    mensajeError = "La cédula debe contener solo números";
                }
                break;
            case "P":
                if (paciente.getIdentificacion().length() != 12) {
                    mensajeError = "El pasaporte debe tener 12 dígitos";
                }
                if (!paciente.getIdentificacion().matches("\\d{12}")) {
                    mensajeError = "El pasaporte debe contener solo números";
                }
                break;
        }

        if (paciente.getNombre().isEmpty()) {
            mensajeError = "El nombre del paciente son campos obligatorios";
        }

        String formatoFecha = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(formatoFecha);
        Matcher matcher = pattern.matcher(paciente.getFechaNacimiento());
        if (!matcher.matches()) {
            mensajeError = "el formato de fecha de nacimiento no es valido (yyyy-MM-dd)";
        }

        if (paciente.getTelefono().length() != 10) {
            mensajeError = "el telefono debe tener 10 dígitos";
        }
        if (!paciente.getTelefono().matches("\\d{10}")) {
            mensajeError = "El telefono debe contener solo números";
        }

        return mensajeError;
    }

    public boolean mayorEdad() {
        LocalDate fNacimiento = LocalDate.of(2015, Month.SEPTEMBER, 18);
        System.out.println("Tu edad es de " + ChronoUnit.YEARS.between(fNacimiento, LocalDate.now()) + " años.");
        return ChronoUnit.YEARS.between(fNacimiento, LocalDate.now()) >= 18;

    }
}