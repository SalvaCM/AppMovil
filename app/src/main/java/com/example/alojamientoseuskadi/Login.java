package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void iniciar(){
        Toast toast1 = Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void registrarse(){
        Toast toast2=Toast.makeText(getApplicationContext(), "Toast por defecto2", Toast.LENGTH_SHORT);
        toast2.show();
    }

}
