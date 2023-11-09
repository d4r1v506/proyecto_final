package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controladores.ControladorHorario;



public class HorarioFuncionamientoTest {
      @Test
    public void testHorarioFuncionamientoDiasLaborales_EsValido() {
        ControladorHorario horario = new ControladorHorario();
        assertTrue(horario.horarioFuncionamiento("2023-11-10"));// El establecimiento opera de lunes a viernes
    }

    @Test
    public void testHorarioFuncionamientoDiasLaborales_NoEsvalido() {
        ControladorHorario horario = new ControladorHorario();
         assertFalse(horario.horarioFuncionamiento("2023-11-11"));
    }
}
