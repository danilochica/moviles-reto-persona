package co.com.uco.persona.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

import co.com.uco.persona.R;
import co.com.uco.persona.dto.PersonDTO;

public class AdultPerson extends AppCompatActivity implements Serializable {

    TextView txtName;
    TextView txtLastName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_person);

        PersonDTO person = (PersonDTO) getIntent().getSerializableExtra("PersonDTO");

        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);

        txtName.setText(person.getNombre());
        txtLastName.setText(person.getApellido());


    }
}
