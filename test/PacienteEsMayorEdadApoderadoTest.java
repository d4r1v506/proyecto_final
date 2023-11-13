package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controladores.ControladorPaciente;

public class PacienteEsMayorEdadApoderadoTest {

    @Test
    public void testPacienteEsMayorEdadApoderado() {
        ControladorPaciente paciente = new ControladorPaciente();
        assertTrue(paciente.esMayorEdadApoderado("1986-01-01"));
    }

    @Test
    public void testPacienteNoEsMayorEdadApoderado() {
        ControladorPaciente paciente = new ControladorPaciente();
        assertFalse(paciente.esMayorEdadApoderado("2015-01-01"));
    }
}
