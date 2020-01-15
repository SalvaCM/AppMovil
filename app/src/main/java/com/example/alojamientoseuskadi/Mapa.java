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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    TextView tvPruebas;
    ListView lvVerPosiciones;
    private List<Alojamiento> listaAlojamientos;
    private ParseJson parse2;
    private GoogleMap mapa;
    //private final LatLng UPV = new LatLng(39.481106, -0.340987);
    private final LatLng UPV = new LatLng(43.2814236, -2.9675669);
    private final LatLng UPV2 = new LatLng(39.481106, -0.340987);
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        //Se cargan los datos de los alojamientos:
        cargarDatosAlojamientos();

        //Se muestran los datos de los alojamientos:
        mostrarTodasLasReservas();
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
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //mapa.getUiSettings().setZoomControlsEnabled(false);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 15));//desplaza el área de visualización a una determinada posición (UPV), a la vez que define el nivel de zum (15)

        //Para añadir  marcadores
        mapa.addMarker(new MarkerOptions() //permite añadir  marcadores
                .position(UPV)
                .title("Mi posición")
                .snippet("CIFP Elorrieta - Erreka Mari LHII")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_mylocation))
                .anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);

        //PRUEBAS Para añadir  marcadores
/*
for (Alojamiento a : listaAlojamientos) {
            double latitud = parseInt(a.getLatitud().toString());
            double longitud = parseInt(a.getLongitud().toString());

            LatLng posicion = new LatLng(latitud, longitud);

            mapa.addMarker(new MarkerOptions() //permite añadir  marcadores

                    .position(posicion)
                    //.title(a.getNombre().toString())
                   // .snippet("222222")
                    .icon(BitmapDescriptorFactory
                            .fromResource(android.R.drawable.ic_menu_mylocation))
                    .anchor(0.5f, 0.5f));
            mapa.setOnMapClickListener(this);

        }
 */




        //PRUEBAS Para añadir  marcadores

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
        mapa.animateCamera(CameraUpdateFactory.newLatLng(UPV));
    }
    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(
                mapa.getCameraPosition().target));
    }
    @Override public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }

    //FIN*************************MAPAS**********************************************
    public void mostrarTodasLasReservas(){
        lvVerPosiciones = (ListView)findViewById(R.id.lvVerPosiciones);
        registerForContextMenu(lvVerPosiciones);//Se debe “registrar” el ContextMenu , por lo que se añadirá la siguiente línea al método onCreate.

        ArrayAdapter<Alojamiento> adapter = new ArrayAdapter<Alojamiento>(this, android.R.layout.simple_list_item_1, listaAlojamientos);
        lvVerPosiciones.setAdapter(adapter);
        lvVerPosiciones.setLongClickable(true);
        lvVerPosiciones.setClickable(true);




    }

}