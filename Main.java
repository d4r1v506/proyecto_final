import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controladores.ControladorCitaMedica;
import Controladores.ControladorHorario;
import Controladores.ControladorPaciente;
import Models.Paciente;
import util.LecturaArchivo;

public class Main implements LecturaArchivo {
    public static void main(String[] args) {
        ControladorHorario horario = new ControladorHorario();
        ControladorPaciente pacienteControlador = new ControladorPaciente();
        ControladorCitaMedica citaControlador = new ControladorCitaMedica();

        Map<String, String> map = new HashMap<>();
        map = LecturaArchivo.cargarDatosNuevaCita();

        if (!horario.horarioFuncionamiento(map.get("fechaCita"))) {
            System.out.println("No se pudo agregar: **Esa fecha no hay atención solo horario laboral**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        if (!horario.validarHorario(map.get("fechaCita"), map.get("horaCita"))) {
            System.out.println("No se pudo agregar: **ese horario esta fuera del horario de atención**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        if (horario.esDiaFeriado(map.get("fechaCita"))) {
            System.out.println("No se pudo agregar: **esa fecha es feriado**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        try {
            Integer respuesta = horario.validaFechaFuturoEspecialista(map.get("fechaCita"), map.get("tipoCita"));

            if (respuesta == 1) {
                System.out.println(
                        "No se pudo agregar: **La cita para especialista debe ser con al menos 24h de anticipación**");
                mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
                return;
            } else if (respuesta == 2) {
                System.out.println(
                        "No se pudo agregar: **La cita para medicina general debe ser una fecha actual o superior**");
                mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!horario.horarioConsecutivo(map.get("horaCita"))) {
            System.out.println("No se pudo agregar: **hora debe ser en intervalos de 20min.**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        Paciente paciente = new Paciente.PacienteBuilder()
                .tipoIdentificacion(map.get("tipoDocumento"))
                .identificacion(map.get("identificacion"))
                .nombre(map.get("nombrePaciente"))
                .fechaNacimiento(map.get("fechaNacimiento"))
                .telefono(map.get("telefono"))
                .build();

        String mensajeError = pacienteControlador.validaDatosPaciente(paciente);
        if (!mensajeError.isEmpty()) {
            System.out.println("No se pudo agregar: **" + mensajeError + "**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        if (pacienteControlador.requiereApoderado(map.get("tipoPaciente"), map.get("apoderado"))) {
            System.out.println("No se pudo agregar: **Se requiere un apoderado**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        if (!pacienteControlador.esMayorEdadApoderado(map.get("fechaNacimientoApoderado"))) {
            System.out.println("No se pudo agregar: **El apoderado debe ser mayor de edad**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        if (pacienteControlador.existeCitaSimultanea(map.get("fechaCita"), map.get("horaCita"),
                map.get("identificacion"))) {
            System.out.println("No se pudo agregar: **No se puede tener citas simultaneas**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        if (citaControlador.disponibleEspecialista(map.get("fechaCita"), map.get("especialidad"),
                map.get("horaCita"))) {
            System.out.println("No se pudo agregar: **El especialista no esta disponible**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        if (citaControlador.disponibleGeneral(map.get("fechaCita"), map.get("horaCita"))) {
            System.out.println("No se pudo agregar: **los 2 prfesionales en medicina general esta ocupados**");
            mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
            return;
        }

        // cita fue exitosa
        System.out.println("Cita creada Existosamente!!: \n" + map.get("citaExitosa"));
        mostrarListadoOriginal(LecturaArchivo.retornaListaOriginal());
    }

    public static void mostrarListadoOriginal(List<String> registrosAntesDeNuevaCita) {
        System.out.println("-----Lista Original------");
        for (String item : registrosAntesDeNuevaCita) {
            System.out.println(item);
        }
    }

}