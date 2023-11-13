package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LecturaArchivo {

    public static Map<String, String> cargarDatosNuevaCita() {
        BufferedReader br = null;

        Map<String, String> map = new HashMap<>();
        boolean encontradaNuevaCita = false;

        try {
            br = new BufferedReader(new FileReader(Constantes.NOMBRE_ARCHIVO_INPUT));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.contains("NUEVA CITA")) {
                    encontradaNuevaCita = true;
                    continue;
                }
                map.put("citaExitosa", linea);

                if (encontradaNuevaCita) {
                    String[] valores = linea.split("\\|");
                    map.put("fechaCita", valores[0].trim());
                    map.put("horaCita", valores[1].trim());
                    map.put("tipoCita", valores[2].trim());
                    map.put("especialidad", valores[3].trim());
                    map.put("nombrePaciente", valores[4].trim());
                    map.put("tipoPaciente", valores[5].trim());
                    map.put("tipoDocumento", valores[6].trim());
                    map.put("identificacion", valores[7].trim());
                    map.put("telefono", valores[8].trim());
                    map.put("fechaNacimiento", valores[9].trim());

                    if (valores.length > 10) {
                        map.put("apoderado", valores[10].trim());
                        map.put("nombreApoderado", valores[11].trim());
                        map.put("tipoDocumentoApoderado", valores[12].trim());
                        map.put("identificacionApoderado", valores[13].trim());
                        map.put("fechaNacimientoApoderado", valores[14].trim());
                    }
                    break; // Detiene el bucle una vez que se encuentra el nuevo registro
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
        return map;
    }

    public static List<String> retornaListaOriginal() {
        BufferedReader br = null;
        int ultimoElemento = 0;
        List<String> registrosAntesDeNuevaCita = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(Constantes.NOMBRE_ARCHIVO_INPUT));
            String linea;

            while ((linea = br.readLine()) != null) {

                registrosAntesDeNuevaCita.add(linea);
                ultimoElemento = registrosAntesDeNuevaCita.size() - 1;

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
        registrosAntesDeNuevaCita.remove(ultimoElemento);
        return registrosAntesDeNuevaCita;
    }

}
