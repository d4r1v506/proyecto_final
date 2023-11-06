import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Controladores.ControladorHorario;
import Controladores.ControladorPaciente;

public class Main {
    public static void main(String[] args) {
        /*
         * String nombreArchivo = "inputs/lab_input.txt";
         * BufferedReader br = null;
         * 
         * try {
         * br = new BufferedReader(new FileReader(nombreArchivo));
         * String linea;
         * 
         * for (int i = 0; i < 11; i++) {
         * linea = br.readLine();
         * if (linea == null) {
         * // Si la línea 2 no existe, salir del bucle
         * break;
         * }
         * if (i == 10) {// leer linea
         * String[] valores = linea.split("\\|");
         * 
         * System.out.println("Palabra: " + valores[4].trim());
         * }
         * }
         * } catch (IOException e) {
         * e.printStackTrace();
         * } finally {
         * try {
         * if (br != null) {
         * br.close();
         * }
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         * }
         */

        ControladorHorario horario = new ControladorHorario();
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

    }
}