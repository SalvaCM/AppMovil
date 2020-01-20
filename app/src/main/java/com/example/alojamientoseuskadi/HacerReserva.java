package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HacerReserva extends AppCompatActivity {
    // Declaración de elementos de la actividad.xml
    EditText txtPruebas;
    EditText etFechaEntrada;
    EditText etFechaSalida;
    Calendar calendarioEntrada = Calendar.getInstance();
    Calendar calendarioSalida = Calendar.getInstance();

    TextView nombreAloj;
    TextView descripAloj;
    TextView localidadAloj;
    TextView telefonoAloj;
    TextView emailAloj;
    TextView webAloj;
    TextView usuarioLog;

    private String nombAlojSeleccionado;

    //Declaración de variables
    String formatoDeFecha = "dd/MM/yyyy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
    Toast toastFecha;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat fechaActual = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat horaActual = new SimpleDateFormat("HH:mm:ss ");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_reserva);

        //para recuperar los datos que nos hemos pasado de la actividad anterior (VerTareas)
        String idAlojSeleccionado = getIntent().getStringExtra("idAlojSeleccionado");
        String nombreAlojamiento = getIntent().getStringExtra("nombreAlojSeleccionado");
        String descripcionAlojSeleccionado = getIntent().getStringExtra("descripcionAlojSeleccionado");
        String localidadAlojSeleccionado = getIntent().getStringExtra("localidadAlojSeleccionado");
        String telefonoAlojSeleccionado = getIntent().getStringExtra("telefonoAlojSeleccionado");
        String webAlojSeleccionado = getIntent().getStringExtra("webAlojSeleccionado");
        String emailAlojSeleccionado = getIntent().getStringExtra("emailAlojSeleccionado");

        Toast toast1 = Toast.makeText(getApplicationContext(), "nombreAlojSeleccionado" + nombreAlojamiento, Toast.LENGTH_SHORT);
        toast1.show();

        //Se saca el usuario que se ha logueado que está guardado en la clase SharedPreferences
        SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
        String nombre = settings.getString("usuarioLogeado", "");
        usuarioLog = findViewById(R.id.tvUsuarioLog);
        usuarioLog.setText(nombre);

        //Se rellenan los datos del activity con los datos que hemos pasado:
        nombreAloj = findViewById(R.id.tvNombre);
        nombreAloj.setText(nombreAlojamiento);
        descripAloj = findViewById(R.id.tvDescripcion);
        descripAloj.setText(descripcionAlojSeleccionado);
        localidadAloj =findViewById(R.id.tvlocalidad);
        localidadAloj.setText(localidadAlojSeleccionado);
        telefonoAloj= findViewById(R.id.tvTelefono);
        telefonoAloj.setText(telefonoAlojSeleccionado);
        webAloj= findViewById(R.id.tvWeb);
        webAloj.setText(webAlojSeleccionado);
        emailAloj= findViewById(R.id.tvEmail);
        emailAloj.setText(emailAlojSeleccionado);

        //Fecha de entrada:
        etFechaEntrada = findViewById(R.id.fechaEntrada);
        etFechaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(HacerReserva.this, date, calendarioEntrada
                        .get(Calendar.YEAR), calendarioEntrada.get(Calendar.MONTH),
                        calendarioEntrada.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Fecha Salida:
        etFechaSalida = findViewById(R.id.fechaSalida);
        etFechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(HacerReserva.this, date2, calendarioSalida
                        .get(Calendar.YEAR), calendarioSalida.get(Calendar.MONTH),
                        calendarioSalida.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    //fechaEntrada
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendarioEntrada.set(Calendar.YEAR, year);
            calendarioEntrada.set(Calendar.MONTH, monthOfYear);
            calendarioEntrada.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };

    //fechaSalida
    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendarioSalida.set(Calendar.YEAR, year);
            calendarioSalida.set(Calendar.MONTH, monthOfYear);
            calendarioSalida.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput2();
        }

    };

    //Fecha entrada
    private void actualizarInput() {
        etFechaEntrada.setText(sdf.format(calendarioEntrada.getTime()));

    }

    //Fecha Salida
    private void actualizarInput2() {
        etFechaSalida.setText(sdf.format(calendarioSalida.getTime()));
    }

    //Métodos de los botones
    public void atras(View view){
        Intent i = new Intent(this, VerAlojamientos.class);
        startActivity(i);
    }

    public void hacerReserva(View view)throws SQLException {
        ResultSet rs;
        Statement st = null;
        String sURL="";
        Toast.makeText(HacerReserva.this,"Conexión Establecida", Toast.LENGTH_LONG).show();

        String strFechaActual = fechaActual.format(calendar.getTime());
        try {    boolean estadoConexion = false;

            Connection conexionMySQL = null;
            String driver = "com.mysql.jdbc.Driver";

           Class.forName(driver).newInstance();



            conexionMySQL = DriverManager.getConnection("jdbc:mysql://188.213.5.150:3306/alojamientos", "accesoadatos", "123456");

            Toast toast1 =Toast.makeText(getApplicationContext(),"Toast por defecto", Toast.LENGTH_SHORT);

            toast1.show();
            if(!conexionMySQL.isClosed())
            {
                estadoConexion = true;
                Toast.makeText(HacerReserva.this,"Conexión Establecida", Toast.LENGTH_LONG).show();
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }}