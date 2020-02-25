package co.com.uco.persona.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.com.uco.persona.R;
import co.com.uco.persona.dto.PersonDTO;
import co.com.uco.persona.transversal.Constants;


public class MainActivity extends AppCompatActivity{

    EditText txtName;
    EditText txtLastName;
    EditText txtBornDate;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);
        txtBornDate = findViewById(R.id.txtBornDate);

        txtBornDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBornDate.setText("");
                createCalendar();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "-"  +  month  + "-" + year;
                txtBornDate.setText(date);
            }
        };
    }

    public void goToPersonActivity(View view) {

        String name = txtName.getText().toString();
        String lastName = txtLastName.getText().toString();
        Date bornDate =  convertStringtoDate(txtBornDate.getText().toString()) ;

        if (isNotEmpty(name, lastName, bornDate)  && isAdult(bornDate)) {

            PersonDTO person = PersonDTO.builder()
                    .name(name)
                    .lastName(lastName)
                    .bornDate(bornDate)
                    .build();

            Intent intent = new Intent(MainActivity.this, AdultPerson.class);
            intent.putExtra("PersonDTO",person);
            startActivity(intent);
        }

        if(!isAdult(bornDate) && !Constants.EMPTY_STRING.equals(bornDate)){
            alertInfoAdult();
        }
    }

    private void alertInfoAdult() {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(R.string.informacion);
        alertDialog.setMessage("La persona es menor de edad");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private boolean isAdult(Date bornDate) {
        return calculateYearsOld(bornDate) >= Constants.AGE_TO_BE_ADULT;
    }

    private int calculateYearsOld(Date bornDate){
        Calendar born = Calendar.getInstance();
        born.setTime(bornDate);
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        return today.get(Calendar.YEAR) - born.get(Calendar.YEAR);
    }

    private boolean isNotEmpty(String name, String lastName, Date bornDate) {
        unsetErrorLabelEditText();
        if (!name.isEmpty() && !lastName.isEmpty() && !Constants.EMPTY_STRING.equals(bornDate)) {
            return true;

        } else {
            setErrorLabelInEditText(name, lastName, bornDate);
            return false;
        }
    }

    private void setErrorLabelInEditText(String name, String lastName, Date bornDate) {

        if (Constants.EMPTY_STRING.equals(name)) {
            txtName.setError(getString(R.string.campoRequerido));
        }

        if (Constants.EMPTY_STRING.equals(lastName)) {
            txtLastName.setError(getString(R.string.campoRequerido));
        }

        if (Constants.EMPTY_STRING.equals(bornDate)) {
            txtBornDate.setError(getString(R.string.campoRequerido));
        }
    }

    private void unsetErrorLabelEditText(){
        txtName.setError(null);
        txtLastName.setError(null);
        txtBornDate.setError(null);
    }

    private Date convertStringtoDate (String bornString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateConverted = new Date();

        try {
            dateConverted =  sdf.parse(bornString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateConverted;

    }

    private void createCalendar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                onDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}s
