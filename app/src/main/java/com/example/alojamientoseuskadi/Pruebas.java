package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Pruebas extends AppCompatActivity {
    //pruebas
    private List<Usuario> listaUsuarios;
    private ParseJson parse1;
    //pruebas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);



    }
}
