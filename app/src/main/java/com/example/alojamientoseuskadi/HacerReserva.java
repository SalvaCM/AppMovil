package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HacerReserva extends AppCompatActivity {
    // Declaración de elementos de la actividad.xml
    EditText etFechaEntrada;
    EditText etFechaSalida;
    Calendar calendarioEntrada = Calendar.getInstance();
    Calendar calendarioSalida = Calendar.getInstance();

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
        Toast toast1 = Toast.makeText(getApplicationContext(), "botón atras pulsado", Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void hacerReserva(View view){
        String strFechaActual =  fechaActual.format(calendar.getTime());

        if(etFechaEntrada.getText().length()==0){
            toastFecha = Toast.makeText(getApplicationContext(), "@String/toastErrorFecha1" + etFechaEntrada.getText(), Toast.LENGTH_SHORT);
        }
        if(etFechaSalida.getText().length()==0){
            toastFecha = Toast.makeText(getApplicationContext(), "@String/toastErrorFecha2" + etFechaEntrada.getText(), Toast.LENGTH_SHORT);
        }

        else{
            toastFecha = Toast.makeText(getApplicationContext(), "botón HacerReserva pulsado." , Toast.LENGTH_SHORT);
        }



        toastFecha.show();
    }
}
