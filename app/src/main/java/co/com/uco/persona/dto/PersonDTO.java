package co.com.uco.persona.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PersonDTO implements Serializable {

    private String name;
    private String lastName;
    private Date bornDate;


}
