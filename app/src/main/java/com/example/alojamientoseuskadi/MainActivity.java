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
            Intent i = new Intent(this,VerAlojamientos.class);
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
