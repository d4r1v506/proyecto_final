package util;

import java.util.Arrays;
import java.util.List;

public class Constantes {
    public static final String PACIENTE_ADULTO = "ADULTO";
    public static final String PACIENTE_MENOR = "PMENOR";

    public static final String TIPO_CEDULA = "C";
    public static final String TIPO_PASAPORTE = "P";

    public static final String[] DIAS_LABORABLES = { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" };
    public static final List<String> LISTA_DIAS_LABORABLES = Arrays.asList(DIAS_LABORABLES);

    public static final String[] DIAS_FERIADO = { "2023-08-10", "2023-11-03" };
    public static final List<String> LISTA_DIAS_FERIADO = Arrays.asList(DIAS_FERIADO);

    public static final String FORMATO_FECHA = "\\d{4}-\\d{2}-\\d{2}";

    public static final Integer EDAD_ADULTO = 18;

    public static final String NOMBRE_ARCHIVO_INPUT = "inputs/med_input.txt";

}