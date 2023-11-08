import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controladores.ControladorCitaMedica;
import Controladores.ControladorHorario;
import Controladores.ControladorPaciente;
import Models.Paciente;

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
        String identificacionApoderado = "";
        String tipoDocumentoApoderado = "";
        String fechaNacimientoApoderado = "";
        int ultimoElemento = 0;

        List<String> registrosAntesDeNuevaCita = new ArrayList<>();

        ControladorHorario horario = new ControladorHorario();
        ControladorPaciente pacienteControlador = new ControladorPaciente();
        ControladorCitaMedica citaControlador = new ControladorCitaMedica();

        try {
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.contains("NUEVA CITA")) {
                    encontradaNuevaCita = true;
                    continue;
                }

                registrosAntesDeNuevaCita.add(linea);
                ultimoElemento = registrosAntesDeNuevaCita.size() - 1;
                // System.out.println(linea);

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
                    fechaNacimiento = valores[9].trim();
                    if (valores.length > 10) {
                        apoderado = valores[10].trim();
                        nombreApoderado = valores[11].trim();
                        tipoDocumentoApoderado = valores[12].trim();
                        identificacionApoderado = valores[13].trim();
                        fechaNacimientoApoderado = valores[14].trim();
                    }
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

        registrosAntesDeNuevaCita.remove(ultimoElemento);

        if (!horario.horarioFuncionamiento(fechaCita)) {
            System.out.println("No se pudo agregar: **Esa fecha no hay atención solo horario laboral**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        if (!horario.validarHorario(fechaCita, horaCita)) {
            System.out.println("No se pudo agregar: **ese horario esta fuera del horario de atención**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        if (horario.validaFeriado(fechaCita)) {
            System.out.println("No se pudo agregar: **esa fecha es feriado**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        try {
            Integer respuesta = horario.validaFechaFuturoEspecialista(fechaCita, tipoCita);

            if (respuesta == 1) {
                System.out.println(
                        "No se pudo agregar: **La cita para especialista debe ser con al menos 24h de anticipación**");
                mostrarListadoOriginal(registrosAntesDeNuevaCita);
                return;
            } else if (respuesta == 2) {
                System.out.println(
                        "No se pudo agregar: **La cita para medicina general debe ser una fecha actual o superior**");
                mostrarListadoOriginal(registrosAntesDeNuevaCita);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!horario.horarioConsecutivo(horaCita)) {
            System.out.println("No se pudo agregar: **hora debe ser en intervalos de 20min.**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        // String tipoIdentificacion, String identificacion, String nombre,
        // String fechaNacimiento, String telefono

        Paciente paciente = new Paciente();
        paciente.setTipoIdentificaion(tipoDocumento);
        paciente.setIdentificacion(identificacion);
        paciente.setNombre(nombrePaciente);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setTelefono(telefono);

        String mensajeError = pacienteControlador.validaDatosPaciente(paciente);
        if (!mensajeError.isEmpty()) {
            System.out.println("No se pudo agregar: **" + mensajeError + "**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        if (pacienteControlador.requiereApoderado(tipoPaciente, apoderado)) {
            System.out.println("No se pudo agregar: **Se requiere un apoderado**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        if (!pacienteControlador.isMayorEdadApoderado(fechaNacimientoApoderado)) {
            System.out.println("No se pudo agregar: **El apoderado debe ser mayor de edad**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        if (pacienteControlador.existeCitaSimultanea(fechaCita, horaCita, identificacion)) {
            System.out.println("No se pudo agregar: **No se puede tener citas simultaneas**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        if (citaControlador.disponibleEspecialista(fechaCita, especialidad)) {
            System.out.println("No se pudo agregar: **El especialista no esta disponible**");
            mostrarListadoOriginal(registrosAntesDeNuevaCita);
            return;
        }

        citaControlador.disponibleGeneral(fechaCita);

        // cita fue exitosa

    }

    public static void mostrarListadoOriginal(List<String> registrosAntesDeNuevaCita) {
        for (String item : registrosAntesDeNuevaCita) {
            System.out.println(item);
        }
    }
}