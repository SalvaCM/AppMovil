package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VerAlojamientos extends AppCompatActivity {
    private List<Alojamiento> listaAlojamientos;
    private ParseJson parse2;

    public String nombreAlojSelecc;

    public String tipoAlojSelecc;
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

        //Spiner para tipo de alojamiento:
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTipoAloj);
        String[] letra = {"Todos","Albergue","Rural","Camping"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));


        //Cuando se selecciona un tipo de alojamiento:
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                tipoAlojSelecc = (String) adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(adapterView.getContext(),
                        (String) "Seleccionado: " + tipoAlojSelecc, Toast.LENGTH_SHORT).show();

                //Se cargan los datos de los alojamientos Filtrados por tipo:
                GetArrayItemsFiltrado(tipoAlojSelecc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });



        //Cuando selecciona algún alojamiento:
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                Intent i = new Intent(VerAlojamientos.this, HacerReserva.class);
               //Se le pasa a la Actividad: Hacer reserva los datos del hotel seleccionado:
                i.putExtra("idAlojSeleccionado",GetArrayItemsFiltrado(tipoAlojSelecc).get(position).getcodAlojamiento());
                i.putExtra("nombreAlojSeleccionado", GetArrayItemsFiltrado(tipoAlojSelecc).get(position).getTitulo());
                i.putExtra("descripcionAlojSeleccionado",GetArrayItemsFiltrado(tipoAlojSelecc).get(position).getDesc());
                i.putExtra("localidadAlojSeleccionado",GetArrayItemsFiltrado(tipoAlojSelecc).get(position).getLocalidad());
                i.putExtra("telefonoAlojSeleccionado",GetArrayItemsFiltrado(tipoAlojSelecc).get(position).getTelefono());
                i.putExtra("emailAlojSeleccionado",GetArrayItemsFiltrado(tipoAlojSelecc).get(position).getEmail());
                i.putExtra("webAlojSeleccionado",GetArrayItemsFiltrado(tipoAlojSelecc).get(position).getWeb());
                startActivity(i);
            }
        });
    }

    

    private ArrayList<Entidad> GetArrayItemsFiltrado(String opcion){
        ArrayList<Entidad> listItems = new ArrayList<>();
        for (Alojamiento a : listaAlojamientos) {
            if(a.getTipo().equals(opcion)){
                listItems.add(new Entidad(a.getCodAlojamiento(), R.drawable.imgcasa, a.getNombre(), a.getLocalizacion(),a.getTelefono(), a.getWeb(), a.getDescripcion(), a.getLocalidad(), a.getEmail()));
            }else if(opcion.equals("Todos")){
                listItems.add(new Entidad(a.getCodAlojamiento(), R.drawable.imgcasa, a.getNombre(), a.getLocalizacion(),a.getTelefono(), a.getWeb(), a.getDescripcion(), a.getLocalidad(), a.getEmail()));
            }
        }
        adaptador = new Adaptador(listItems,this);
        lvItems.setAdapter(adaptador);
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
            System.out.println("Lectura Json no se pudo realizar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Métodos de los botones
    public void atras(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
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
