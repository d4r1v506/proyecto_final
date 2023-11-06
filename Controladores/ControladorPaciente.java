package Controladores;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean validaDatosPaciente(String tipoIdentificacion, String identificacion, String nombre,
            String fechaNacmiento, String telefono) {
        if (tipoIdentificacion.length() != 1) {
            throw new IllegalArgumentException("El tipo de identificacion solo es un caracter C o P");
        }
        if (!(tipoIdentificacion.equals("C") || tipoIdentificacion.equals("P"))) {
            throw new IllegalArgumentException("El tipo identificacion solo puede ser C o P");
        }

        switch (tipoIdentificacion) {
            case "C":
                if (identificacion.length() != 10) {
                    throw new IllegalArgumentException("La cédula debe tener 10 dígitos");
                }
                if (!identificacion.matches("\\d{10}")) {
                    throw new IllegalArgumentException("La cédula debe contener solo números");
                }
                break;
            case "P":
                if (identificacion.length() != 12) {
                    throw new IllegalArgumentException("El pasaporte debe tener 12 dígitos");
                }
                if (!identificacion.matches("\\d{12}")) {
                    throw new IllegalArgumentException("El pasaporte debe contener solo números");
                }
                break;
        }

        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente son campos obligatorios");
        }

        String formatoFecha = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(formatoFecha);
        Matcher matcher = pattern.matcher(fechaNacmiento);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("el formato de fecha de nacimiento no es valido (yyyy-MM-dd)");
        }

        if (telefono.length() != 10) {
            throw new IllegalArgumentException("el telefono debe tener 10 dígitos");
        }
        if (!telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El telefono debe contener solo números");
        }

        return true;
    }

    public boolean mayorEdad() {
        LocalDate fNacimiento = LocalDate.of(2015, Month.SEPTEMBER, 18);
        System.out.println("Tu edad es de " + ChronoUnit.YEARS.between(fNacimiento, LocalDate.now()) + " años.");
        return ChronoUnit.YEARS.between(fNacimiento, LocalDate.now()) >= 18;

    }
}