package com.example.exameniipamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FichaPersonalActivity extends AppCompatActivity {

    TextView tvFichaPersonal;
    String nombre, cedula, telefono,genero,nacimiento, fechanacimiento;
    Button btnIngresarBDD;
    int edad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_personal);

        tvFichaPersonal = findViewById(R.id.tvFichaPersonal);
        btnIngresarBDD = findViewById(R.id.btnIngresarBDD);

        Bundle datos = getIntent().getExtras();
        nombre = datos.getString("nombre");
        cedula = datos.getString("cedula");
        nacimiento = datos.getString("nacimiento");
        telefono = datos.getString("telefono");
        genero = datos.getString("genero");

        fechanacimiento = formatFecha(nacimiento);
        edad = calcularEdad(nacimiento);

        tvFichaPersonal.setText("Hola, mi nombre es " + nombre + " con cedula " + cedula + " mi genero " + genero +" naci el "
                + fechanacimiento + " por lo cual tengo " + edad + " años de edad, te puedes contactar conmigo con el telefono "
                +  telefono);

        btnIngresarBDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearTablaExamen();
            }
        });
    }
    public void CrearTablaExamen() {
        BDHelper admin = new BDHelper(this, "ExamenIIPAMovil.db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        if (!cedula.isEmpty() && !nombre.isEmpty() && !nacimiento.isEmpty() && !telefono.isEmpty() && !genero.isEmpty()) {
            ContentValues datosRegistrar = new ContentValues();
            datosRegistrar.put("per_cedula", cedula);
            datosRegistrar.put("per_nombre", nombre);
            datosRegistrar.put("per_telefono", telefono);
            datosRegistrar.put("per_edad", edad);
            datosRegistrar.put("per_genero", genero);

            bd.insert("ExamenIIPAMovil", null, datosRegistrar);

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
        }
    }


    public String formatFecha(String fecha){
        String[] partes = fecha.split("/");
        String fechaformateada = "";

        if (partes[1].equals(1)) {
            partes[1] = "Enero";
        } else if (partes[1].equals(2)) {
            partes[1] = "Febrero";
        } else if (partes[1].equals(3)) {
            partes[1] = "Marzo";
        } else if (partes[1].equals(4)) {
            partes[1] = "Abril";
        } else if (partes[1].equals(5)) {
            partes[1] = "Mayo";
        } else if (partes[1].equals(6)) {
            partes[1] = "Junio";
        } else if (partes[1].equals(7)) {
            partes[1] = "Julio";
        } else if (partes[1].equals(8)) {
            partes[1] = "Agosto";
        } else if (partes[1].equals(9)) {
            partes[1] = "Septiembre";
        } else if (partes[1].equals(10)) {
            partes[1] = "Octubre";
        } else if (partes[1].equals(11)) {
            partes[1] = "Noviembre";
        } else if (partes[1].equals(12)) {
            partes[1] = "Diciembre";
        }
        fechaformateada = partes[0]+ " de " + partes[1] + " del " + partes[2];
        return fechaformateada;
    }

    public static int calcularEdad(String fechaNacimiento) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNac = null;
        try {
            fechaNac = sdf.parse(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar fechaNacimientoCal = Calendar.getInstance();
        fechaNacimientoCal.setTime(fechaNac);

        Calendar fechaActualCal = Calendar.getInstance();

        if (fechaNacimientoCal.after(fechaActualCal)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar en el futuro.");
        }

        int edad = fechaActualCal.get(Calendar.YEAR) - fechaNacimientoCal.get(Calendar.YEAR);

        if (fechaNacimientoCal.get(Calendar.DAY_OF_YEAR) > fechaActualCal.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        return edad;
    }
}