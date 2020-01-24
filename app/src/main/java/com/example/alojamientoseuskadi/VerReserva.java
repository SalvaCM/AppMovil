package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class VerReserva extends AppCompatActivity {
    //variables:
    //Datos usuario:
    String nombreUsuario;
    String apellidosUsuario;
    String telfUsuario;

    //Datos alojamiento:
    String nombreAloj;
    String direccionAloj;
    String localizacionAloj;
    String emailAloj;
    String webAloj;
    String telfAloj;
    String tipoAloj;
    int capacidadAloj;
    String descripcionAloj;
    String latitudAloj;
    String longitudAloj;
    String localidadAloj;


    //Datos reserva:
    int codReserva;
    int codAlojamiento ;
    String codUsuario ;
    String fechaRealizada ;
    String fechaEntrada ;
    String fechaSalida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reserva);

        //para recuperar los datos que nos hemos pasado de la actividad anterior (VerTareas)
       // String codReservaSelec = getIntent().getStringExtra("codReservaSelec");

        //Se saca el usuario que se ha logueado que está guardado en la clase SharedPreferences
        SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
        String codReservaSelec = settings.getString("codReservaSelec", "");
        //codReserva = findViewById(R.id.tvUsuarioLog);
       // codReserva.setText(nombre);

        Toast toast1 = Toast.makeText(getApplicationContext(), "codReservaSelec" + codReservaSelec.toString(), Toast.LENGTH_SHORT);
        toast1.show();
    }
}
