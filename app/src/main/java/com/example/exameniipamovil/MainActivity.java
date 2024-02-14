package com.example.exameniipamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String fecha;
    EditText txtNombre, txtCedula, txtTelefono;
    Button btnProcesar, btnCalendario;
    Spinner sprGenero;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCedula = findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnProcesar = findViewById(R.id.btnProcesar);
        btnCalendario = findViewById(R.id.btnCalendar);
        sprGenero = findViewById(R.id.sprGenero);

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendario();
            }
        });

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Procesar();
            }
        });
    }
    public void Calendario() {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                fecha = date;
            }
        }, anio, mes, dia);
        dpd.show();
    }

    public void Procesar() {
        Bundle datos = new Bundle();

        datos.putString("nombre", txtNombre.getText().toString());
        datos.putString("cedula", txtCedula.getText().toString());
        datos.putString("telefono", txtTelefono.getText().toString());
        datos.putString("nacimiento", fecha);
        datos.putString("genero", sprGenero.getSelectedItem().toString());


        Intent i = new Intent(this, FichaPersonalActivity.class);
        i.putExtras(datos);
        startActivity(i);
    }
}