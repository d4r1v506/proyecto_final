/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultamédica;

/**
 *
 * @author Admin
 */
public class Contacto {
     private String numeroTelefono;
    private String email;

    public Contacto(String numeroTelefono, String email) {
        this.numeroTelefono = numeroTelefono;
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
