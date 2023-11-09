package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controladores.ControladorHorario;

public class HorarioValidarHorarioTest {
      @Test
    public void testvalidarHorarioEsValido() {
        ControladorHorario horario = new ControladorHorario();
        assertTrue(horario.validarHorario("2023-11-10","08:20"));
        // Las consultas médicas tienen los siguientes horarios: Lunes a jueves 8:00 a
    // 19:00 Viernes 8:00 a 13:00
    }

    @Test
    public void testvalidarHorarioNoEsValido() {
        ControladorHorario horario = new ControladorHorario();
         assertFalse(horario.validarHorario("2023-11-10","19:00"));
    }
}
