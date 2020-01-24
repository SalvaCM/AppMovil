package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class VerReserva extends AppCompatActivity {
    // Declaración de elementos de la activity_ver_reserva.xml
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
    TextView tvCodReserva;
    TextView tvFechaEntrada;
    TextView tvFechaSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reserva);

        //para recuperar los datos que nos hemos pasado de la actividad anterior (VerTareas)
       // String codReservaSelec = getIntent().getStringExtra("codReservaSelec");

        //Se saca el usuario que se ha logueado que está guardado en la clase SharedPreferences
        SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
        String codReservaSelec = settings.getString("codReservaSelec", "");
        String fechaRealizadaReservaSelec = settings.getString("fechaRealizadaReservaSelec", "");
        String fechaEntradaReservaSelec = settings.getString("fechaEntradaReservaSelec", "");
        String fechaSalidaReservaSelec = settings.getString("fechaSalidaReservaSelec", "");

        String nombreAlojReservaSelec = settings.getString("nombreAlojReservaSelec", "");
        String descripAlojReservaSelec = settings.getString("descripAlojReservaSelec", "");
        String localidadAlojReservaSelec = settings.getString("localidadAlojReservaSelec", "");
        String telefonoAlojReservaSelec = settings.getString("telfAlojReservaSelec", "");
        String emailAlojReservaSelec = settings.getString("emailAlojReservaSelec", "");
        String webAlojReservaSelec = settings.getString("webAlojReservaSelec", "");

        //se recorta la hora de la date:
        fechaRealizadaReservaSelec = fechaRealizadaReservaSelec.substring(0,10);
        fechaEntradaReservaSelec = fechaEntradaReservaSelec.substring(0,10);
        fechaSalidaReservaSelec = fechaSalidaReservaSelec.substring(0,10);

        //Se rellena el textView con los datos recuperados de la clase SharedPreferences
        tvCodReserva = findViewById(R.id.tvCodReserva);
        tvCodReserva.setText(codReservaSelec);
        tvFechaEntrada = findViewById(R.id.tvFechaEntrada);
        tvFechaEntrada.setText(fechaEntradaReservaSelec);
        tvFechaSalida = findViewById(R.id.tvFechaSalida);
        tvFechaSalida.setText(fechaSalidaReservaSelec);

        nombreAloj = findViewById(R.id.tvNombre);
        nombreAloj.setText(nombreAlojReservaSelec);
        descripAloj = findViewById(R.id.tvDescripcion);
        descripAloj.setText(descripAlojReservaSelec);
        localidadAloj =findViewById(R.id.tvlocalidad);
        localidadAloj.setText(localidadAlojReservaSelec);
        telefonoAloj= findViewById(R.id.tvTelefono);
        telefonoAloj.setText(telefonoAlojReservaSelec);
        webAloj= findViewById(R.id.tvWeb);
        webAloj.setText(emailAlojReservaSelec);
        emailAloj= findViewById(R.id.tvEmail);
        emailAloj.setText(webAlojReservaSelec);


        Toast toast1 = Toast.makeText(getApplicationContext(), "codReservaSelec" + codReservaSelec.toString(), Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void borrarReserva(View view){

    }
}
