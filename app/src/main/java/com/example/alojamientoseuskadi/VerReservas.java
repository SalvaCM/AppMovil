package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerReservas extends AppCompatActivity {
    public List<Reserva> listaReservas = new ArrayList<Reserva>();
    public List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    public List<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();
    private final boolean CANCELAR_SI_MAS_DE_100_IMAGENES = false;//se encargará de decir si cancelamos o no la descarga al pasa de 100 imágenes.
    private final String TAG_LOG = "test";//El tag del log para que veamos por consola lo que va haciendo nuestra aplicación.
    private TextView TV_mensaje; //Y el TextView que será el que muestre el mensaje que cambia de color.

    private ParseJson parse2;
    private ListView lvVerReservas;
    public int reservaSelecc;
    ArrayAdapter<Reserva> adapter;

    private int posicionReservaSelecc;

    //variables:
    //Datos usuario:
    String nombreUsuario;
    String apellidosUsuario;
    String telfUsuario;

    //Datos alojamiento:
    String nombreAloj;
    String direccionAloj;
    String localizacionAloj;
    String emailAloj;
    String webAloj;
    String telfAloj;
    String tipoAloj;
    int capacidadAloj;
    String descripcionAloj;
    String latitudAloj;
    String longitudAloj;
    String localidadAloj;


    //Datos reserva:
    int codReserva;
    int codAlojamiento ;
    String codUsuario ;
    String fechaRealizada ;
    String fechaEntrada ;
    String fechaSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reservas);
        new ConnectMySqlBBDD().execute(" Estos Strings van a variableNoUsada que no usaremos en este ejemplo y podiamos haber declarado como Void "," si lo necesitaramos podemos cambiar el String por otro tipo de datos "," podemos añadir más de 4 datos que los de este ejemplo, todos los que necesitemos "," y recuerda que se usan como un array, para acceder en concreto a este usaríamos variableNoUsada[3] "); //Arrancamos el AsyncTask. el método "execute" envía datos directamente a doInBackground()


        mostrarTodasLasReservas();
    }

    private class ConnectMySqlBBDD extends AsyncTask<String, Void, List<Reserva> > {
        private boolean cancelarSiHayMas100Archivos;
        private ProgressBar miBarraDeProgreso;
        String res = "";

        /**
         * Contructor de ejemplo que podemos crear en el AsyncTask
         *
         * en este ejemplo le pasamos un booleano que indica si hay más de 100 archivos o no. Si le pasas true se cancela por la mitad del progreso, si le pasas false seguirá hasta el final sin cancelar la descarga simulada
         */
        public ConnectMySqlBBDD() {

        }
        /**
         * Se ejecuta antes de empezar el hilo en segundo plano. Después de este se ejecuta el método "doInBackground" en Segundo Plano
         *
         * Se ejecuta en el hilo: PRINCIPAL
         */
        @Override
        protected void onPreExecute() {

            Log.v(TAG_LOG, "ANTES de EMPEZAR la descarga. Hilo PRINCIPAL");

            super.onPreExecute();

        }

        /**
         * Se ejecuta después de "onPreExecute". Se puede llamar al hilo Principal con el método "publishProgress" que ejecuta el método "onProgressUpdate" en hilo Principal
         *
         * Se ejecuta en el hilo: EN SEGUNDO PLANO
         *
         * param array con los valores pasados en "execute"
         * @return devuelve un valor al terminar de ejecutar este segundo plano. Se lo envía y ejecuta "onPostExecute" si ha termiado, o a "onCancelled" si se ha cancelado con "cancel"
         */
        @SuppressLint("WrongThread")
        @Override
        protected List<Reserva> doInBackground(String... variableNoUsada) {

            try {
                Class.forName("com.mysql.jdbc.Driver");

                String url =  "jdbc:mysql://188.213.5.150:3306/alojamientos?serverTimezone=UTC";
                //url = "Server=188.213.5.150;Port=3306;Database=alojamientos;Uid=accesoadatos;Pwd=123456;";
                java.sql.Connection con = DriverManager.getConnection(url,"accesoadatos","123456");

                //String query = "SELECT * FROM tReservas";

                //FALTA: and R.cCodUsuario='" & usuarioLogeado.ToString & "'"
                String query = "select R.cReserva, R.cFechaEntrada, R.cFechaSalida, R.cFechaRealizada, R.cCodUsuario,U.cNombre 'NombreUsuario' ,U.cApellidos, U.cTelefono 'telfUsuario', R.cCodAlojamiento,A.cNombre 'NombreAlojamiento', A.cDireccion, A.cLocalizacion, A.cEmail, A.cTelefono 'telfAloj', A.cWeb, A.cTipo, A.cCapacidad, A.cDescripcion, A.cLatitud, A.cLongitud, A.cLocalidad    from tReservas R, tAlojamientos A, tUsuarios U where R.cCodAlojamiento=A.cCodAlojamiento and R.cCodUsuario=U.cDni";
                // create the java statement
                Statement st = con.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                // iterate through the java resultset
                while (rs.next())
                {
                    //Datos usuario:
                    nombreUsuario = rs.getString("NombreUsuario");
                    apellidosUsuario = rs.getString("cApellidos");
                    telfUsuario = rs.getString("telfUsuario");

                    //Datos alojamiento:
                    nombreAloj = rs.getString("NombreAlojamiento");
                    direccionAloj = rs.getString("cDireccion");
                    localizacionAloj = rs.getString("cLocalizacion");
                    emailAloj = rs.getString("cEmail");
                    webAloj = rs.getString("cWeb");
                    telfAloj = rs.getString("telfAloj");
                    //A.cTipo, A.cCapacidad, A.cDescripcion, A.cLatitud, A.cLongitud, A.cLocalidad
                    tipoAloj = rs.getString("cTipo");
                    capacidadAloj = rs.getInt("cCapacidad");
                    descripcionAloj = rs.getString("cDescripcion");
                    latitudAloj = rs.getString("cLatitud");
                    longitudAloj = rs.getString("cLongitud");
                    localidadAloj = rs.getString("cLocalidad");


                    //Datos reserva:
                    codReserva = rs.getInt("cReserva");
                    codAlojamiento = rs.getInt("cCodAlojamiento");
                    codUsuario = rs.getString("cCodUsuario");
                    fechaRealizada = rs.getString("cFechaRealizada");
                    fechaEntrada = rs.getString("cFechaEntrada");
                    fechaSalida = rs.getString("cFechaSalida");

                    Usuario usuarioLogeado = new Usuario(codUsuario, nombreUsuario, apellidosUsuario, telfUsuario);
                    listaUsuarios.add(usuarioLogeado);
                    Alojamiento alojamiento = new Alojamiento(codAlojamiento, nombreAloj, direccionAloj, telfAloj, tipoAloj, webAloj, capacidadAloj, descripcionAloj, emailAloj, latitudAloj, longitudAloj, localidadAloj, localizacionAloj);
                    listaAlojamientos.add(alojamiento);

                    Reserva reserva = new Reserva(codReserva, codAlojamiento, codUsuario, fechaRealizada, fechaEntrada, fechaSalida);
                    listaReservas.add(reserva);
                    // print the results
                    System.out.format("%s \n", codReserva, codAlojamiento,  codUsuario);

                }
                st.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                String asd = "";
            }

            return listaReservas;
        }

        /**
         * Se ejecuta después de terminar "doInBackground".
         *
         * Se ejecuta en el hilo: PRINCIPAL
         *
         * param array con los valores pasados por el return de "doInBackground".
         */
        // @Override
        protected void onPostExecute(List<Reserva> cantidadProcesados) {
            listaReservas = cantidadProcesados;
            adapter.notifyDataSetChanged();

        }
    }


    public void mostrarTodasLasReservas(){
        lvVerReservas = (ListView)findViewById(R.id.lvVerReservas);
        registerForContextMenu(lvVerReservas);//Se debe “registrar” el ContextMenu , por lo que se añadirá la siguiente línea al método onCreate.

        adapter = new ArrayAdapter<Reserva>(this, android.R.layout.simple_list_item_1, listaReservas);
        lvVerReservas.setAdapter(adapter);
        lvVerReservas.setLongClickable(true);
        lvVerReservas.setClickable(true);

        //Cuando se hace click en una tarea:
        lvVerReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {

                //cambiar por codAdlojamiento??
                posicionReservaSelecc = posicion;
                reservaSelecc = listaReservas.get(posicion).getCodReserva();

                irADetallesVerReserva(posicion);
            }
        });



    }
    public void irADetallesVerReserva(int pos){

        SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("codReservaSelec", String.valueOf(listaReservas.get(posicionReservaSelecc).getCodReserva()));
        editor.putString("fechaRealizadaReservaSelec", String.valueOf(listaReservas.get(posicionReservaSelecc).getFechaRealizada()));
        editor.putString("fechaEntradaReservaSelec", String.valueOf(listaReservas.get(posicionReservaSelecc).getFechaEntrada()));
        editor.putString("fechaSalidaReservaSelec", String.valueOf(listaReservas.get(posicionReservaSelecc).getFechaSalida()));

        editor.putString("dniUsuarioLog", String.valueOf(listaUsuarios.get(posicionReservaSelecc).getDni()));
        editor.putString("nombreUsuario", String.valueOf(listaUsuarios.get(posicionReservaSelecc).getNombre()));
        editor.putString("apellidosUsuario", String.valueOf(listaUsuarios.get(posicionReservaSelecc).getApellidos()));
        editor.putString("telfUsuario", String.valueOf(listaUsuarios.get(posicionReservaSelecc).getTelefono()));

        editor.putString("nombreAlojReservaSelec", String.valueOf(listaAlojamientos.get(posicionReservaSelecc).getNombre()));
        editor.putString("descripAlojReservaSelec", String.valueOf(listaAlojamientos.get(posicionReservaSelecc).getDescripcion()));
        editor.putString("localidadAlojReservaSelec", String.valueOf(listaAlojamientos.get(posicionReservaSelecc).getLocalidad()));
        editor.putString("telfAlojReservaSelec", String.valueOf(listaAlojamientos.get(posicionReservaSelecc).getTelefono()));
        editor.putString("emailAlojReservaSelec", String.valueOf(listaAlojamientos.get(posicionReservaSelecc).getEmail()));
        editor.putString("webAlojReservaSelec", String.valueOf(listaAlojamientos.get(posicionReservaSelecc).getWeb()));
        editor.commit();

        Intent i = new Intent(this, VerReserva.class);

        //Se le pasa a la Actividad: Ver reserva los datos de la reserva seleccionada:
        i.putExtra("codReservaSelec",listaReservas.get(posicionReservaSelecc).getCodReserva());

        startActivity(i);
    }
}
