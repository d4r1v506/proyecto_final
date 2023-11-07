package Controladores;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import util.Constantes;

public class ControladorHorario {

    // El establecimiento opera de lunes a viernes
    public boolean horarioFuncionamiento(String fecha) {

        LocalDate fechaIngresada = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
        // Obtener el día de la semana correspondiente a la fecha ingresada
        DayOfWeek diaSemana = fechaIngresada.getDayOfWeek();
        // Devolver el nombre del día de la semana en español
        String nombreDiaSemana = diaSemana.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        // System.out.println("dia que se ingreso es: " + nombreDiaSemana);

        String diaSemanSinTitlde = Normalizer.normalize(nombreDiaSemana, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return Constantes.LISTA_DIAS_LABORABLES.contains(diaSemanSinTitlde.toUpperCase());
    }

    // Las consultas médicas tienen los siguientes horarios: Lunes a jueves 8:00 a
    // 19:00 Viernes 8:00 a 13:00
    public boolean validarHorario(String fecha, String hora) {

        LocalDate fechaIngresada = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
        // Obtener el día de la semana correspondiente a la fecha ingresada
        DayOfWeek diaSemana = fechaIngresada.getDayOfWeek();
        // Devolver el nombre del día de la semana en español
        String nombreDiaSemana = diaSemana.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String diaSemanSinTitlde = Normalizer.normalize(nombreDiaSemana, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        boolean validaDiaViernes = false;
        boolean validaDiaLunesAJueves = false;
        boolean validaHora = false;

        if (diaSemanSinTitlde.toUpperCase().equals("VIERNES")) {
            validaDiaViernes = true;
            // Convertir la hora a un objeto de tipo LocalTime
            LocalTime horaIngresada = LocalTime.parse(hora);

            // Definir el rango de horas permitido
            LocalTime horaInicio = LocalTime.parse("07:59");
            LocalTime horaFin = LocalTime.parse("13:01");

            // Verificar si la hora ingresada está dentro del rango
            if (horaIngresada.isAfter(horaInicio) && horaIngresada.isBefore(horaFin)) {
                validaHora = true;
            }

        } else {
            validaDiaLunesAJueves = true;
            // Convertir la hora a un objeto de tipo LocalTime
            LocalTime horaIngresada = LocalTime.parse(hora);

            // Definir el rango de horas permitido
            LocalTime horaInicio = LocalTime.parse("07:59");
            LocalTime horaFin = LocalTime.parse("19:01");

            // Verificar si la hora ingresada está dentro del rango
            if (horaIngresada.isAfter(horaInicio) && horaIngresada.isBefore(horaFin)) {
                validaHora = true;
            }
        }

        return validaHora;
    }

    // Los días feriados no hay atención de ningún servicio.
    public boolean validaFeriado(String fecha) {
        return Constantes.LISTA_DIAS_FERIADO.contains(fecha);
    }

    // Solo se pueden registrar citas en el futuro. solo para especialistas
    public Integer validaFechaFuturoEspecialista(String fecha, String tipoCita) throws Exception {
        Integer respuesta = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = formato.parse(fecha);
        Date fechaActual = new Date();
        if (tipoCita.equals("ESPECIALISTA")) {
            if (fechaDate.compareTo(fechaActual) <= 0) {
                respuesta = 1;
            }
        } else {
            if (fechaDate.compareTo(fechaActual) < 0) {
                respuesta = 2;
            }
        }
        return respuesta;
    }

    // Los espacios son consecutivos, es decir 08:00, 08:20, etc
    public boolean horarioConsecutivo(String horaIngresada) {
        // String horaIngresada = "8:20"; // Aquí debes asignar la hora ingresada por el
        // usuario
        boolean valida = false;

        LocalTime hora = LocalTime.parse(horaIngresada);
        LocalTime limiteInferior = LocalTime.parse("08:00");
        LocalTime limiteSuperior = LocalTime.parse("18:40");

        if (hora.isAfter(limiteInferior) && hora.isBefore(limiteSuperior) && hora.getMinute() % 20 == 0) {
            // System.out.println("La hora ingresada está dentro del rango de 20 minutos
            // entre las 8:00 y las 19:00.");
            valida = true;
        }
        return valida;
    }
}
