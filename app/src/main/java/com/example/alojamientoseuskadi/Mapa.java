package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    TextView tvPruebas;
    ListView lvVerPosiciones;
    private List<Alojamiento> listaAlojamientos;
    private ParseJson parse2;
    private GoogleMap mapa;
    public String tipoAlojSelecc;

    private final LatLng UPV = new LatLng(43.2814236, -2.9675669);
    //private final LatLng UPV2 = new LatLng(39.481106, -0.340987);

    private ListView lvItems;
    private Adaptador adaptador;
    private ArrayList<Entidad> arrayEntidad;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        //Se cargan los datos de los alojamientos:
        cargarDatosAlojamientos();

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
                //GetArrayItemsFiltrado(tipoAlojSelecc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

        //*************************MAPAS**********************************************
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }
    //*************************DATOS ALOJAMIENTOS**********************************************
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
    //FIN*************************DATOS ALOJAMIENTOS**********************************************

    //*************************MAPAS**********************************************
    @Override public void onMapReady(GoogleMap googleMap) {

        LatLng UPV2 = new LatLng(39.481106, -0.340987);

        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //mapa.getUiSettings().setZoomControlsEnabled(false);

        //Botón 5km
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 5));//desplaza el área de visualización a una determinada posición (UPV), a la vez que define el nivel de zum (15)

        //Para añadir  marcador posición actual:

        mapa.addMarker(new MarkerOptions() //permite añadir  marcadores
                .position(UPV)
                .title("Mi posición")
                .snippet("CIFP Elorrieta - Erreka Mari LHII")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_myplaces))
                .anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);

        //Añadir  marcadores de los alojamientos:
        for (Alojamiento a :listaAlojamientos) {
            if (!a.getLatitud().toString().equals("Latitud no disponible")){
                final double latitud = Double.parseDouble(a.getLatitud().toString());
                final double longitud = Double.parseDouble(a.getLongitud().toString());

                LatLng posicion = new LatLng(latitud, longitud);

                mapa.addMarker(new MarkerOptions() //permite añadir  marcadores
                        .position(posicion)
                        .title(a.getNombre().toString())
                        .snippet("Contacto: " + a.getTelefono().toString())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.aloj))
                      // .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass))
                        .anchor(0.5f, 0.5f));


                //Botón 10km
                mapa.animateCamera(CameraUpdateFactory.newLatLng(posicion));
                mapa.setOnMapClickListener(this);
            }

        }
        //FIN Añadir  marcadores de los alojamientos:

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mapa.setMyLocationEnabled(true); //activa la visualización de la posición del dispositivo por  medio del típico círculo
            // El método getUiSettings() permiteconfigurar las acciones de la interfaz de usuario.
            mapa.getUiSettings().setCompassEnabled(true);
        }
    }
    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(UPV));
    }

    public void animateCamera(View view) {
        //mapa.animateCamera(CameraUpdateFactory.newLatLng(UPV));
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 10));//desplaza el área de visualización a una determinada posición (posEnKM), a la vez que define el nivel de zum (15)

    }
    public void cincokm(View view) {
        //Botón 5km
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 15));

    }
    public void quincekm(View view) {
        //Botón 5km
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 12));
    }
    //Marcar posición:
    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(
                mapa.getCameraPosition().target));
    }
    @Override public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }
    //FIN Marcar posición:

    //FIN*************************MAPAS**********************************************

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

}