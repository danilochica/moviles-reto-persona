package co.com.uco.persona.dto;

import java.util.Date;

public class Persona {

    private String nombre;
    private String apellido;
    private Date fechaNacimiento;

    public Persona(String nombre, String apellido, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
