package Controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
        if (!(paciente.getTipoIdentificaion().equals("C") || paciente.getTipoIdentificaion().equals("P"))) {
            mensajeError = "El tipo identificacion solo puede ser C o P";
        }

        switch (paciente.getTipoIdentificaion()) {
            case "C":                              
                if (paciente.getIdentificacion().length() != 10) {                
                    mensajeError = "La cédula debe tener 10 dígitos";                  
                }else{
                    if (!paciente.getIdentificacion().matches("\\d{10}")) {
                        mensajeError = "La cédula debe contener solo números";
                    }
                }   
                break;
            case "P":
                if (paciente.getIdentificacion().length() != 12) {
                    mensajeError = "El pasaporte debe tener 12 dígitos";
                }else{
                    if (!paciente.getIdentificacion().matches("\\d{12}")) {
                        mensajeError = "El pasaporte debe contener solo números";
                    }
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
        }else{
            if (!paciente.getTelefono().matches("\\d{10}")) {
                mensajeError = "El telefono debe contener solo números";
            }
        }
        
        return mensajeError;
    }

    public boolean requiereApoderado(String tipoPaciente, String apoderado){
        return tipoPaciente.equals("PMENOR") && apoderado.isEmpty();
    }

    public boolean isMayorEdadApoderado(String fechaNacimiento) {
        //LocalDate fNacimiento = LocalDate.of(2015, Month.SEPTEMBER, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaLocalDate = LocalDate.parse(fechaNacimiento, formatter);         
        return ChronoUnit.YEARS.between(fechaLocalDate, LocalDate.now()) >= 18;
    }

    public boolean existeCitaSimultanea(String fechaCita, String horaCita, String identificacion){
        String nombreArchivo = "inputs/med_input.txt";
        BufferedReader br = null;
         try {
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;            
            boolean enRango = false;

            while ((linea = br.readLine()) != null) {
                if (linea.contains(fechaCita)) {
                    enRango = true;
                } else if (enRango && linea.matches("\\d{2}:\\d{2}\\|.*")) {
                    //String hora = linea.split("\\|")[0];
                    String[] valores = linea.split("\\|");
                    //String horaAntes = valores[0];
                    if(identificacion.equals(valores[6])){
                        System.out.println("existeCedula");                        
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
        return true;
    }

    public String horaAntes(String horaCita){
        LocalTime hora = LocalTime.parse(horaCita, DateTimeFormatter.ofPattern("HH:mm"));
                        // Restar 20 minutos
                        LocalTime horaRestada = hora.minusMinutes(20);
                        // Formatear la hora restada como una cadena en el formato HH:mm
                        String horaRestadaStr = horaRestada.format(DateTimeFormatter.ofPattern("HH:mm"));

                        System.out.println("Hora original: " + horaCita);
                        System.out.println("Hora restada: " + horaRestadaStr);
        return horaRestadaStr;
    }
    public String horaDespues(String horaCita){
        LocalTime hora = LocalTime.parse(horaCita, DateTimeFormatter.ofPattern("HH:mm"));
                        // Restar 20 minutos
                        LocalTime horaSumada = hora.plusMinutes(20);
                        // Formatear la hora restada como una cadena en el formato HH:mm
                        String horaSumadaStr = horaSumada.format(DateTimeFormatter.ofPattern("HH:mm"));

                        System.out.println("Hora original: " + horaCita);
                        System.out.println("Hora restada: " + horaSumadaStr);
        return horaSumadaStr;
    }
}