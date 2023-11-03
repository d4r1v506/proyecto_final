package inputs.util;

import java.util.Arrays;
import java.util.List;

public class Constantes {
    public static final String PACIENTE_ADULTO = "ADULTO";
    public static final String PACIENTE_MENOR = "MENOR";

    public static final String TIPO_CEDULA = "C";
    public static final String TIPO_PASAPORTE = "P";

    public static final String[] DIAS_LABORABLES = { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" };
    public static final List<String> LISTA_DIAS_LABORABLES = Arrays.asList(DIAS_LABORABLES);

    public static final String[] DIAS_FERIADO = { "2023-08-10", "2023-11-03" };
    public static final List<String> LISTA_DIAS_FERIADO = Arrays.asList(DIAS_FERIADO);

}