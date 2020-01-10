package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VerAlojamientos extends AppCompatActivity {
    private List<Alojamiento> listaAlojamientos;
    private ParseJson parse2;
    private ListView lv1;
    public String tareaSelecc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alojamientos);

        //*** LEER JSON: Obtener el fichero json desde la carpeta raw
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

        TextView et = (TextView) findViewById(R.id.tv_test_json3);

        StringBuilder alojamient = new StringBuilder();
        // Recorrer objeto List<Usuario> y concatenarlo en una variable para
        // mostrarlo.



        //***FIN LEER JSON: Obtener el fichero json desde la carpeta raw

        //ListView
        //listaAlojamientos.clear();
        mostrarTodosLosAlojamientos();
    }

    public void mostrarTodosLosAlojamientos(){
        lv1 = (ListView)findViewById(R.id.lv1);

        registerForContextMenu(lv1);//Se debe “registrar” el ContextMenu , por lo que se añadirá la siguiente línea al método onCreate.

        //MOSTRAR EL CONTENIDO DE SQLite de la tabla tareas3


       ArrayAdapter<Alojamiento> adapter = new ArrayAdapter<Alojamiento>(this, android.R.layout.simple_list_item_1, listaAlojamientos);
        lv1.setAdapter(adapter);
        lv1.setLongClickable(true);
        lv1.setClickable(true);

        //Cuando se hace click en una tarea:
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {

                //cambiar por codAdlojamiento??
                tareaSelecc = listaAlojamientos.get(posicion).getNombre();

                irADetallesHacerReserva(posicion);

            }
        });


    }
    public void irADetallesHacerReserva(int pos){
        Intent i = new Intent(this, HacerReserva.class);
         //  i.putExtra("nombreAlojamiento", listaAlojamientos.get(pos));//para mandar un dato a otra actividad
        startActivity(i);
    }

    public void irAHacerReserva(View view){
        Toast toast1 = Toast.makeText(getApplicationContext(), "botón Reservar pulsado", Toast.LENGTH_SHORT);
        toast1.show();
        Intent i = new Intent(this, HacerReserva.class);
        startActivity(i);
    }

}
