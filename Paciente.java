/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultamédica;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Paciente {
    private String identificacion;
    private String tipoIdentificaion;
    private String nombre;
    private String apellido;
    private Date FechaNacimiento;

    public Paciente(String identificacion, String tipoIdentificaion, String nombre, String apellido, Date FechaNacimiento) {
        this.identificacion = identificacion;
        this.tipoIdentificaion = tipoIdentificaion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.FechaNacimiento = FechaNacimiento;
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

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
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

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    
    
}
