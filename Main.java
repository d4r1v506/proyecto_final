import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import inputs.Controladores.ControladorHorario;

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
        String fechaIngresada = "2024-11-03";
        String horaIngresada = "17:00";

        boolean existe = horario.horarioFuncionamiento(fechaIngresada);
        // Las consultas médicas tienen los siguientes horarios: Lunes a jueves 8:00 a
        // 19:00 Viernes 8:00 a 13:00
        if (existe) {
            boolean valida = horario.validarHorario(fechaIngresada, horaIngresada);
            System.out.println("valida " + valida);
        } else {
            System.out.println("dia fuera de funcionamiento");
        }
        // -------------------------------------
        boolean validaFeriado = horario.validaFeriado(fechaIngresada);
        if (validaFeriado) {
            System.out.println("fecha no disponible es feriado");
        }
        // -------------------------------------
        try {
            boolean validaCitaFuturo = horario.fechaFuturo(fechaIngresada);
            if (validaCitaFuturo) {
                System.out.println("la Cita es correcta");
            } else {
                System.out.println("La cita debe ser antes de 24 horas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // -------------------------------------
        boolean validaHoraConsecutiva = horario.horarioConsecutivo(horaIngresada);
        System.out.println("valida " + validaHoraConsecutiva);
    }
}