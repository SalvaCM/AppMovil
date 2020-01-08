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

    EditText etFechaEntrada;
    EditText etFechaSalida;
    Calendar calendarioEntrada = Calendar.getInstance();
    Calendar calendarioSalida = Calendar.getInstance();

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
                new DatePickerDialog(HacerReserva.this, date, calendarioSalida
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
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        etFechaEntrada.setText(sdf.format(calendarioEntrada.getTime()));

    }

    //Fecha Salida
    private void actualizarInput2() {
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);


        etFechaSalida.setText(sdf.format(calendarioSalida.getTime()));
    }

    //Métodos de los botones
    public void atras(View view){
        Toast toast1 = Toast.makeText(getApplicationContext(), "botón atras pulsado", Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void hacerReserva(View view){
        Toast toast1 = Toast.makeText(getApplicationContext(), "botón HacerReserva pulsado", Toast.LENGTH_SHORT);
        toast1.show();
    }
}
