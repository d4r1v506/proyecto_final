import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main{
    public static void main(String[] args){
        String nombreArchivo = "inputs/lab_input.txt";
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
          
            for (int i = 0; i < 11; i++) {
                linea = br.readLine();
                if (linea == null) {
                    // Si la línea 2 no existe, salir del bucle
                    break;
                }
                if(i==10){//leer linea
                    String[] valores = linea.split("\\|");                    

                    System.out.println("Palabra: " + valores[2].trim());
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
    }
}