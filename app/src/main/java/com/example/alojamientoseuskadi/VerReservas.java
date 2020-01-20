package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VerReservas extends AppCompatActivity {
    private List<Reserva> listaReservass;
    private ParseJson parse2;
    private ListView lvVerReservas;
    public int reservaSelecc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reservas);

        //Se cargan los datos de las reservas:
        cargarDatosReservas();

        cargarDatosReservasBBDD();

        //Se muestran los datos de los alojamientos:
        //listaAlojamientos.clear();
        mostrarTodasLasReservas();
    }

    public void cargarDatosReservasBBDD(){

    }

    public void cargarDatosReservas(){
        InputStream reserv = getResources().openRawResource(R.raw.alojamientos);
        try {
            // re = readJsonStream(is);
            parse2 = new ParseJson();
            listaReservass = parse2.readJsonStreamReserva(reserv); //recibe un inputstream con el contenido de nuestro fichero .json y nos devolverá nuestra lista formada.
            System.out.println("Lectura Json terminada");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarTodasLasReservas(){
        lvVerReservas = (ListView)findViewById(R.id.lvVerReservas);
        registerForContextMenu(lvVerReservas);//Se debe “registrar” el ContextMenu , por lo que se añadirá la siguiente línea al método onCreate.

        ArrayAdapter<Reserva> adapter = new ArrayAdapter<Reserva>(this, android.R.layout.simple_list_item_1, listaReservass);
        lvVerReservas.setAdapter(adapter);
        lvVerReservas.setLongClickable(true);
        lvVerReservas.setClickable(true);

        //Cuando se hace click en una tarea:
        lvVerReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {

                //cambiar por codAdlojamiento??
                reservaSelecc = listaReservass.get(posicion).getCodReserva();

                irADetallesVerReserva(posicion);

            }
        });


    }
    public void irADetallesVerReserva(int pos){
        Intent i = new Intent(this, VerReserva.class);
        // i.putExtra("nombreTarea", listaTareas.get(pos));//para mandar un dato a otra actividad
        //  i.putExtra("nombreAlojamiento", listaAlojamientos.get(pos));//para mandar un dato a otra actividad
        startActivity(i);
    }
}
