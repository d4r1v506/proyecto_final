package Controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControladorCitaMedica {

    /**
     * Los espacios para exámenes duran 20 minutos, por lo que la última cita se
     * puede hacer 20 minutos antes de la hora de cierre.
     * Hay 5 profesionales disponibles. Dos generales y 3 especialistas.
     * Las citas para especialistas deben agendarse con al menos 24h de
     * anticipación.
     * Las citas de medicina general pueden agendarse el mismo día o ser asignadas
     * al instante, sujeto a disponibilidad.
     * La atención de los generales está disponible la jornada completa.
     * Ningún profesional puede tener citas simultáneas
     */

    // 2 generales
    // 3 especialistas
    public boolean disponibleEspecialista(String fechaCita, String especialidad) {
        String nombreArchivo = "inputs/med_input.txt";
        boolean noDisponible = false;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            boolean enRango = false;

            while ((linea = br.readLine()) != null) {
                if (linea.contains(fechaCita)) {
                    enRango = true;
                } else if (enRango && linea.matches("\\d{2}:\\d{2}\\|.*")) {
                    String[] valores = linea.split("\\|");

                    if (especialidad.equals(valores[2])) {
                        noDisponible = true;
                        // System.out.println("El especialista no esta disponible");
                    }
                } else if (enRango && linea.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    enRango = false;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return noDisponible;
    }

    public boolean disponibleGeneral(String fechaCita) {
        String nombreArchivo = "inputs/med_input.txt";
        boolean noDisponible = false;
        BufferedReader br = null;
        Integer contador = 0;
        try {
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            boolean enRango = false;

            while ((linea = br.readLine()) != null) {
                if (linea.contains(fechaCita)) {
                    enRango = true;
                } else if (enRango && linea.matches("\\d{2}:\\d{2}\\|.*")) {
                    String[] valores = linea.split("\\|");

                    if ("GENERAL".equals(valores[1].trim())) {
                        System.out.println("ingreso");
                        contador++;
                        // System.out.println("El especialista no esta disponible");
                    }
                } else if (enRango && linea.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    enRango = false;
                    break;
                }
            }
            System.out.println("contador: " + contador);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return noDisponible;
    }
}