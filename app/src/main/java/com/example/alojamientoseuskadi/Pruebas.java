package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Pruebas extends AppCompatActivity {
    private List<Reserva> listaReservas;
    //pruebas

    private List<Usuario> listaUsuarios;
    private ParseJson parse1;

    private final boolean CANCELAR_SI_MAS_DE_100_IMAGENES = false;//se encargará de decir si cancelamos o no la descarga al pasa de 100 imágenes.
    private final String TAG_LOG = "test";//El tag del log para que veamos por consola lo que va haciendo nuestra aplicación.
    private TextView TV_mensaje; //Y el TextView que será el que muestre el mensaje que cambia de color.
    //pruebas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);

        TV_mensaje = (TextView) findViewById(R.id.TextView_mensajesAlUsuario);

        new ConnectMySql(CANCELAR_SI_MAS_DE_100_IMAGENES).execute(" Estos Strings van a variableNoUsada que no usaremos en este ejemplo y podiamos haber declarado como Void "," si lo necesitaramos podemos cambiar el String por otro tipo de datos "," podemos añadir más de 4 datos que los de este ejemplo, todos los que necesitemos "," y recuerda que se usan como un array, para acceder en concreto a este usaríamos variableNoUsada[3] "); //Arrancamos el AsyncTask. el método "execute" envía datos directamente a doInBackground()

    }

    private class ConnectMySql extends AsyncTask<String, Void, String> {
        private boolean cancelarSiHayMas100Archivos;
        private ProgressBar miBarraDeProgreso;
        String res = "";

        /**
         * Contructor de ejemplo que podemos crear en el AsyncTask
         *
         * en este ejemplo le pasamos un booleano que indica si hay más de 100 archivos o no. Si le pasas true se cancela por la mitad del progreso, si le pasas false seguirá hasta el final sin cancelar la descarga simulada
         */
        public ConnectMySql(boolean cancelarSiHayMas100Archivos) {
            this.cancelarSiHayMas100Archivos = cancelarSiHayMas100Archivos;
        }

        /**
         * Se ejecuta antes de empezar el hilo en segundo plano. Después de este se ejecuta el método "doInBackground" en Segundo Plano
         *
         * Se ejecuta en el hilo: PRINCIPAL
         */
        @Override
        protected void onPreExecute() {
            TV_mensaje.setText("ANTES de EMPEZAR la descarga. Hilo PRINCIPAL");
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
        protected String doInBackground(String... variableNoUsada) {

            int cantidadImagenesDescargadas = 0;
            float progreso = 0.0f;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    //String url = "jdbc:mysql://188.213.5.150:3306?";
                    //url += "user=accesoadatos&password=123456";

                    String url =  "jdbc:mysql://188.213.5.150:3306/alojamientos?serverTimezone=UTC";
                    //url = "Server=188.213.5.150;Port=3306;Database=alojamientos;Uid=accesoadatos;Pwd=123456;";
                    java.sql.Connection con = DriverManager.getConnection(url,"accesoadatos","123456");


                    // our SQL SELECT query.
                    // if you only need a few columns, specify them by name instead of using "*"
                    String query = "SELECT * FROM tReservas";

                    // create the java statement
                    Statement st = con.createStatement();

                    // execute the query, and get a java resultset
                    ResultSet rs = st.executeQuery(query);

                    // iterate through the java resultset
                    while (rs.next())
                    {
                        int codReserva = rs.getInt("cReserva");
                        int codAlojamiento = rs.getInt("cCodAlojamiento");
                        String codUsuario = rs.getString("cCodUsuario");
                        String fechaRealizada = rs.getString("cFechaRealizada");
                        String fechaEntrada = rs.getString("cFechaEntrada");
                        String fechaSalida = rs.getString("cFechaSalida");

                       Reserva reserva = new Reserva(codReserva, codAlojamiento, codUsuario, fechaRealizada, fechaEntrada, fechaSalida);

                       // listaReservas.add(reserva);
                        // print the results
                        System.out.format("%s \n", codReserva, codUsuario);
                       //TV_mensaje.setText("Progreso descarga: "+codUsuario);
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

            return res;
        }

        /**
         * Se ejecuta después de que en "doInBackground" ejecute el método "publishProgress".
         *
         * Se ejecuta en el hilo: PRINCIPAL
         *
         * param array con los valores pasados en "publishProgress"
         */
        //@Override
        protected void onProgressUpdate(Float... porcentajeProgreso) {
            TV_mensaje.setText("Progreso descarga: "+porcentajeProgreso[0]+"%. Hilo PRINCIPAL");
            Log.v(TAG_LOG, "Progreso descarga: "+porcentajeProgreso[0]+"%. Hilo PRINCIPAL");

            miBarraDeProgreso.setProgress( Math.round(porcentajeProgreso[0]) );
        }

        /**
         * Se ejecuta después de terminar "doInBackground".
         *
         * Se ejecuta en el hilo: PRINCIPAL
         *
         * param array con los valores pasados por el return de "doInBackground".
         */
       // @Override
        protected void onPostExecute(Integer cantidadProcesados) {
            TV_mensaje.setText("DESPUÉS de TERMINAR la descarga. Se han descarcado "+cantidadProcesados+" imágenes. Hilo PRINCIPAL");
            Log.v(TAG_LOG, "DESPUÉS de TERMINAR la descarga. Se han descarcado "+cantidadProcesados+" imágenes. Hilo PRINCIPAL");

            TV_mensaje.setTextColor(Color.GREEN);
        }

        /**
         * Se ejecuta si se ha llamado al método "cancel" y después de terminar "doInBackground". Por lo que se ejecuta en vez de "onPostExecute"
         * Nota: Este onCancelled solo funciona a partir de Android 3.0 (Api Level 11 en adelante). En versiones anteriores onCancelled no funciona
         *
         * Se ejecuta en el hilo: PRINCIPAL
         *
         * param array con los valores pasados por el return de "doInBackground".
         */
        //@Override
        protected void onCancelled (Integer cantidadProcesados) {
            TV_mensaje.setText("DESPUÉS de CANCELAR la descarga. Se han descarcado "+cantidadProcesados+" imágenes. Hilo PRINCIPAL");
            Log.v(TAG_LOG, "DESPUÉS de CANCELAR la descarga. Se han descarcado "+cantidadProcesados+" imágenes. Hilo PRINCIPAL");

            TV_mensaje.setTextColor(Color.RED);
        }

    }
}
