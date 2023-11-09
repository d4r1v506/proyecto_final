package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import Controladores.ControladorHorario;

public class HorarioConsecutivoTest {
    @Test
    public void testHorarioConsecutivo_horaValida() {
        ControladorHorario horario = new ControladorHorario();
        assertTrue(horario.horarioConsecutivo("08:00")); // Debería ser true
    }

    @Test
    public void testHorarioConsecutivo_HoraInvalida() {
        ControladorHorario horario = new ControladorHorario();
        assertFalse(horario.horarioConsecutivo("19:00")); // Debería ser false
    }

    @Test
    public void testHorarioConsecutivo_HoraLimiteInferior() {
        ControladorHorario horario = new ControladorHorario();
        assertFalse(horario.horarioConsecutivo("07:59")); // Debería ser false
    }

    @Test
    public void testHorarioConsecutivo_HoraLimiteSuperior() {
        ControladorHorario horario = new ControladorHorario();
        assertFalse(horario.horarioConsecutivo("19:00")); // Debería ser false
    }

    @Test
    public void testHorarioConsecutivo_HoraNoMultiploDe20() {
        ControladorHorario horario = new ControladorHorario();
        assertFalse(horario.horarioConsecutivo("08:05")); // Debería ser false
    }
}
