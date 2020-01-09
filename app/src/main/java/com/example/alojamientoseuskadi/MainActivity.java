package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //pruebas
    private List<Receta> re;
    private ParseJson parse;
    StringBuilder receta;
    //pruebas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //*** LEER JSON: Obtener el fichero json desde la carpeta raw
        InputStream is = getResources().openRawResource(R.raw.entrantes);
        try {
            // re = readJsonStream(is);
            parse = new ParseJson();
            re = parse.readJsonStream(is); //recibe un inputstream con el contenido de nuestro fichero .json y nos devolverá nuestra lista formada.
            System.out.println("Lectura Json terminada");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView et = (TextView) findViewById(R.id.tv_test_json);

        receta = new StringBuilder();
        // Recorrer objeto List<Receta> y concatenarlo en una variable para
        // mostrarlo.
        for (Receta r : re) {
            receta.append("Nombre: " + r.getNombre());
            receta.append("\nPueblo: " + r.getPueblo());
            if (r.getIngredientes() != null) {
                receta.append("\nIngredientes:\n " + r.getIngredientes()
                        + "\n\n");
            }
        }

        et.setText(receta);
        //***FIN LEER JSON: Obtener el fichero json desde la carpeta raw


    }

    //Menu ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.opcion1) {
            Intent i = new Intent(this,Pruebas.class);
            startActivity(i);
        }
        if (id==R.id.opcion2) {
            Intent i = new Intent(this,VerReservas.class);
            startActivity(i);
        }
        if (id==R.id.opcion3) {
            Intent i = new Intent(this, Mapa.class);
            startActivity(i);
        }
        if (id==R.id.opcion4) {
            //redirect user to app Settings
            Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + getPackageName()));
            startActivity(i);
        }
        if (id==R.id.opcion5) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
