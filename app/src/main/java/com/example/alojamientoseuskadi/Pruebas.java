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


        //*** LEER JSON: Obtener el fichero json desde la carpeta raw
        InputStream usu = getResources().openRawResource(R.raw.usuarios);
        try {
            // re = readJsonStream(is);
            parse1 = new ParseJson();
            listaUsuarios = parse1.readJsonStreamUSUARIO(usu); //recibe un inputstream con el contenido de nuestro fichero .json y nos devolverá nuestra lista formada.
            System.out.println("Lectura Json terminada");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView et = (TextView) findViewById(R.id.tv_test_json);

        StringBuilder receta = new StringBuilder();
        // Recorrer objeto List<Receta> y concatenarlo en una variable para
        // mostrarlo.
        for (Usuario u : listaUsuarios) {
            receta.append("\nDni: " + u.getDni());
            receta.append("\nNombre: " + u.getNombre());

        }

        et.setText(receta);
        //***FIN LEER JSON: Obtener el fichero json desde la carpeta raw

    }
}
