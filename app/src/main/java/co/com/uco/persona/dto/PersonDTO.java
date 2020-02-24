package co.com.uco.persona.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonDTO implements Serializable {

    private String name;
    private String lastName;
    private Date bornDate;

    public PersonDTO(String name, String lastName, Date bornDate) {
        this.name = name;
        this.lastName = lastName;
        this.bornDate = bornDate;
    }

    public Date getFechaNacimiento() {
        return bornDate;
    }

    public String getNombre() {
        return name;
    }

    public String getApellido() {
        return lastName;
    }
}
