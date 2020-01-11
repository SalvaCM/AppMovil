package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VerAlojamientos extends AppCompatActivity {
    private List<Alojamiento> listaAlojamientos;
    private ParseJson parse2;

    public String tareaSelecc;

    //lista (ListView) personalizado:
    private ListView lvItems;
    private Adaptador adaptador;
    private ArrayList<Entidad> arrayEntidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alojamientos);

        //Se cargan los datos de los alojamientos:
        cargarDatosAlojamientos();

        //lista (ListView) personalizado:
        lvItems = (ListView) findViewById(R.id.lvItems);
        arrayEntidad = GetArrayItems();
        adaptador = new Adaptador(arrayEntidad, this);
        lvItems.setAdapter(adaptador);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                Intent i = new Intent(VerAlojamientos.this, HacerReserva.class);
                getIntent().putExtra("nombreAlojSeleccionado",listaAlojamientos.get(position).getNombre());
                startActivity(i);
            }
        });
    }

    //lista (ListView) personalizado:
    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listItems = new ArrayList<>();
        for (Alojamiento a : listaAlojamientos) {
            listItems.add(new Entidad(R.drawable.imgcasa, a.getNombre(), a.getTelefono()));

        }
        return listItems;
    }

    public void cargarDatosAlojamientos(){
        InputStream aloj = getResources().openRawResource(R.raw.alojamientos);
        try {
            // re = readJsonStream(is);
            parse2 = new ParseJson();
            listaAlojamientos = parse2.readJsonStreamAlojamiento(aloj); //recibe un inputstream con el contenido de nuestro fichero .json y nos devolverá nuestra lista formada.
            System.out.println("Lectura Json terminada");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void irAHacerReserva(View view){
        Toast toast1 = Toast.makeText(getApplicationContext(), "botón Reservar pulsado", Toast.LENGTH_SHORT);
        toast1.show();
        // i.putExtra("nombreTarea", listaTareas.get(pos));//para mandar un dato a otra actividad
        //  i.putExtra("nombreAlojamiento", listaAlojamientos.get(pos));//para mandar un dato a otra actividad

        Intent i = new Intent(this, HacerReserva.class);
        startActivity(i);
    }

}
