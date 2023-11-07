package Models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Paciente {
    private String identificacion;
    private String tipoIdentificaion;
    private String nombre;
    private String fechaNacimiento;
    private String telefono;

    public Paciente(String identificacion, String tipoIdentificaion, String nombre, String apellido,
            String fechaNacimiento) {
        this.identificacion = identificacion;
        this.tipoIdentificaion = tipoIdentificaion;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Paciente() {

    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipoIdentificaion() {
        return tipoIdentificaion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTipoIdentificaion(String tipoIdentificaion) {
        this.tipoIdentificaion = tipoIdentificaion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
