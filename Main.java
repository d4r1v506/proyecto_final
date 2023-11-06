import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controladores.ControladorHorario;
import Controladores.ControladorPaciente;

public class Main {
    public static void main(String[] args) {
        
        String nombreArchivo = "inputs/med_input.txt";
        BufferedReader br = null;
        boolean encontradaNuevaCita = false;
        String fechaCita = "";
        String horaCita = "";
        String tipoCita = "";
        String especialidad = "";
        String nombrePaciente = "";
        String tipoPaciente = "";
        String tipoDocumento = "";
        String identificacion = "";        
        String telefono = "";
        String fechaNacimiento = "";
        String apoderado = "";
        String nombreApoderado = "";

        // List<String> registrosAntesDeNuevaCita = new ArrayList<>();
          
          try {
          br = new BufferedReader(new FileReader(nombreArchivo));
          String linea;
          
          while ((linea = br.readLine()) != null) {
            if (linea.contains("NUEVA CITA")) {
                encontradaNuevaCita = true;
                continue;
            }

            System.out.println(linea);
    
            if (encontradaNuevaCita) {
                String[] valores = linea.split("\\|");
                fechaCita = valores[0].trim();
                horaCita = valores[1].trim();
                tipoCita = valores[2].trim();
                especialidad = valores[3].trim();
                nombrePaciente = valores[4].trim();
                tipoPaciente = valores[5].trim();
                tipoDocumento = valores[6].trim();
                identificacion = valores[7].trim();
                telefono = valores[8].trim();
                fechaNacimiento =  valores[9].trim();
                if(valores.length>10){
                    apoderado = valores[10].trim();
                    nombreApoderado = valores[11].trim();
                }

                System.out.println("fecha: "+fechaCita);
                System.out.println("hora: "+horaCita);
                System.out.println("tipoCIta: "+tipoCita);
                System.out.println("espcialidad: "+especialidad);
                System.out.println("nombrePaciente: "+nombrePaciente);
                System.out.println("tipoPaciente: "+tipoPaciente);
                System.out.println("tipoDocumento: "+tipoDocumento);
                System.out.println("identificacion: "+identificacion);
                System.out.println("telefono: "+telefono);
                System.out.println("fechaNacimiento: "+fechaNacimiento);
                System.out.println("apoderado: "+apoderado);
                System.out.println("nombreApoderado: "+nombreApoderado);

                break; // Detiene el bucle una vez que se encuentra el nuevo registro
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
         

       /* ControladorHorario horario = new ControladorHorario();
        String fechaCita = "2024-11-03";
        String horaIngresada = "17:00";
        String tipoCitaIngresada = "General";
        String tipoIndentificacion = "P";
        String identificacionIngresada = "111";
        String nombreIngresado = "Darius";
        String fechaNacimiento = "2023-11-04";
        String telefono = "0986287516";

        boolean existe = horario.horarioFuncionamiento(fechaCita);
        // Las consultas médicas tienen los siguientes horarios: Lunes a jueves 8:00 a
        // 19:00 Viernes 8:00 a 13:00
        if (existe) {
            boolean valida = horario.validarHorario(fechaCita, horaIngresada);
            System.out.println("valida " + valida);
        } else {
            System.out.println("dia fuera de funcionamiento");
        }
        // -------------------------------------
        boolean validaFeriado = horario.validaFeriado(fechaCita);
        if (validaFeriado) {
            System.out.println("fecha no disponible es feriado");
        }
        // -------------------------------------

        try {
            horario.validaFechaFuturoEspecialista(fechaCita, tipoCitaIngresada);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // -------------------------------------
        boolean validaHoraConsecutiva = horario.horarioConsecutivo(horaIngresada);
        System.out.println("valida " + validaHoraConsecutiva);

        ControladorPaciente paciente = new ControladorPaciente();
        boolean isMayor = paciente.mayorEdad();
        if (isMayor) {
            System.out.println("Es mayor de edad");
        } else {
            System.out.println("Es menor de edad");
        }

        paciente.validaDatosPaciente(tipoIndentificacion, identificacionIngresada, nombreIngresado, fechaNacimiento,
                telefono);
        */
    }
}