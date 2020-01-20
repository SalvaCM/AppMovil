package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    Toast notificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }

    public void btnRegistrarse(View view){
        Intent i = new Intent(this,inicio.class);
        startActivity(i);

        //Mostrar mensaje:
        //notificacion = Toast.makeText(this, R.string.msjUsuarioRegis, Toast.LENGTH_LONG);
        //notificacion.show();
    }
    public void btnCancelar(View view){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
}
