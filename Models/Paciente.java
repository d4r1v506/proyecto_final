package Models;

/**
 *
 * @author Admin
 */
public class Paciente {
    private String identificacion;
    private String tipoIdentificacion;
    private String nombre;
    private String fechaNacimiento;
    private String telefono;

    // public Paciente(String identificacion, String tipoIdentificaion, String
    // nombre, String apellido,
    // String fechaNacimiento) {
    // this.identificacion = identificacion;
    // this.tipoIdentificaion = tipoIdentificaion;
    // this.nombre = nombre;
    // this.fechaNacimiento = fechaNacimiento;
    // }

    public Paciente() {

    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public static class PacienteBuilder {
        private Paciente paciente = new Paciente();

        public PacienteBuilder identificacion(String identificacion) {
            paciente.identificacion = identificacion;
            return this;
        }

        public PacienteBuilder tipoIdentificacion(String tipoIdentificacion) {
            paciente.tipoIdentificacion = tipoIdentificacion;
            return this;
        }

        public PacienteBuilder nombre(String nombre) {
            paciente.nombre = nombre;
            return this;
        }

        public PacienteBuilder fechaNacimiento(String fechaNacimiento) {
            paciente.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public PacienteBuilder telefono(String telefono) {
            paciente.telefono = telefono;
            return this;
        }

        public Paciente build() {
            return paciente;
        }

    }

}
