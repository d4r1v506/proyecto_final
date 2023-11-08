package util;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class UtilDate {

    public static String obtenerDiaSemana(String fechaIngresada){
        LocalDate fecha = LocalDate.parse(fechaIngresada, DateTimeFormatter.ISO_LOCAL_DATE);        
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        String nombreDiaSemana = diaSemana.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));        
        String diaSemanSinTitlde = Normalizer.normalize(nombreDiaSemana, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return diaSemanSinTitlde;
    }

    public static LocalTime convierteHoraStringtoLocalTime(String hora){        
        LocalTime horaLocalTime = LocalTime.parse(hora);
        return horaLocalTime;
    }
    public static LocalDate convierteFechaStringtoLocalDate(String fecha){        
        LocalDate fechaLocalDate = LocalDate.parse(fecha);
        return fechaLocalDate;
    }

    public static Date obtieneFechaStringtoDate(String fecha) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = formato.parse(fecha);
        return fechaDate;
    }

    public static Date obtieneFechaActualDate() throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSistema = formato.format(new Date());        
        return formato.parse(fechaSistema);
    }

    public static LocalDate obtieneFechaActualLocalDate(){
        return LocalDate.now();
    }

    public static String restarMinutosHoraCita(String hora, Integer minutosRestar){
        LocalTime horaLocalTime = convierteHoraStringtoLocalTime(hora);
        LocalTime horaRestada = horaLocalTime.minusMinutes(minutosRestar);
        return horaRestada.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String sumarMinutosHoraCita(String hora, Integer minutosSumar){
        LocalTime horaLocalTime = convierteHoraStringtoLocalTime(hora);
        LocalTime horaSumanda = horaLocalTime.plusMinutes(minutosSumar);
        return horaSumanda.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
}
