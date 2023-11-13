package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controladores.ControladorHorario;

public class HorarioFeriadoTest {
    @Test
    public void testHorarioFeriadoEsValido() {
        ControladorHorario horario = new ControladorHorario();
        assertTrue(horario.esDiaFeriado("2023-08-10"));
    }

    @Test
    public void testHorarioFeriadoNoEsValido() {
        ControladorHorario horario = new ControladorHorario();
        assertFalse(horario.esDiaFeriado("2023-08-11"));
    }
}
