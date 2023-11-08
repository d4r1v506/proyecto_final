package Controladores;

import java.time.LocalTime;
import java.util.Date;
import util.Constantes;
import util.UtilDate;

public class ControladorHorario {

    // El establecimiento opera de lunes a viernes
    public boolean horarioFuncionamiento(String fecha) {
        String  diaSemana = UtilDate.obtenerDiaSemana(fecha);
        return Constantes.LISTA_DIAS_LABORABLES.contains(diaSemana.toUpperCase());
    }

    // Las consultas médicas tienen los siguientes horarios: Lunes a jueves 8:00 a
    // 19:00 Viernes 8:00 a 13:00
    public boolean validarHorario(String fecha, String hora) {
        String diaSemana = UtilDate.obtenerDiaSemana(fecha);
        boolean validaHora = false;
        if (diaSemana.toUpperCase().equals("VIERNES")) {
           LocalTime horaIngresada = UtilDate.convierteHoraStringtoLocalTime(hora);
            // Definir el rango de horas permitido
            LocalTime horaInicio = LocalTime.parse("07:59");
            LocalTime horaFin = LocalTime.parse("13:01");
            // Verificar si la hora ingresada está dentro del rango
            if (horaIngresada.isAfter(horaInicio) && horaIngresada.isBefore(horaFin)) {
                validaHora = true;
            }
        } else {
            // Convertir la hora a un objeto de tipo LocalTime
            LocalTime horaIngresada = UtilDate.convierteHoraStringtoLocalTime(hora);
            // Definir el rango de horas permitido
            LocalTime horaInicio = LocalTime.parse("07:59");
            LocalTime horaFin = LocalTime.parse("18:41");
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
        Date fechaCita = UtilDate.obtieneFechaStringtoDate(fecha);        
        Date fechaActual = UtilDate.obtieneFechaActualDate();

        if (tipoCita.equals("ESPECIALISTA")) {
            //fechaCita es anterior o igual a fechaActual
            if (fechaCita.compareTo(fechaActual) <= 0) {
                respuesta = 1;
            }
        } else {
            //fechaCita es posterior a fechaActual
            if (fechaCita.compareTo(fechaActual) < 0) {
                respuesta = 2;
            }
        }
        return respuesta;
    }

    // Los espacios son consecutivos, es decir 08:00, 08:20, etc
    public boolean horarioConsecutivo(String horaIngresada) {
        boolean horaConsecutiva = false;
        LocalTime horaCita = UtilDate.convierteHoraStringtoLocalTime(horaIngresada);
        LocalTime limiteInferior = LocalTime.parse("07:59");
        LocalTime limiteSuperior = LocalTime.parse("18:59");
        if (horaCita.isAfter(limiteInferior) && horaCita.isBefore(limiteSuperior) && horaCita.getMinute() % 20 == 0) {
            horaConsecutiva = true;
        }
        return horaConsecutiva;
    }
}
