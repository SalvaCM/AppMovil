package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

public class VerReserva extends AppCompatActivity {
    // Declaración de elementos de la activity_ver_reserva.xml
    EditText txtPruebas;
    EditText etFechaEntrada;
    EditText etFechaSalida;
    Calendar calendarioEntrada = Calendar.getInstance();
    Calendar calendarioSalida = Calendar.getInstance();

    String codReservaSelec;

    TextView nombreUsu;
    TextView apellidoUsu;
    TextView telfUsu;
    TextView emailUsu;

    TextView nombreAloj;
    TextView descripAloj;
    TextView localidadAloj;
    TextView telefonoAloj;
    TextView emailAloj;
    TextView webAloj;
    TextView usuarioLog;
    TextView tvCodReserva;
    TextView tvFechaEntrada;
    TextView tvFechaSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reserva);

        //para recuperar los datos que nos hemos pasado de la actividad anterior (VerTareas)
       // String codReservaSelec = getIntent().getStringExtra("codReservaSelec");

        //Se saca el usuario que se ha logueado que está guardado en la clase SharedPreferences
        SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
        codReservaSelec = settings.getString("codReservaSelec", "");
        String fechaRealizadaReservaSelec = settings.getString("fechaRealizadaReservaSelec", "");
        String fechaEntradaReservaSelec = settings.getString("fechaEntradaReservaSelec", "");
        String fechaSalidaReservaSelec = settings.getString("fechaSalidaReservaSelec", "");

        String dniUsuarioLog = settings.getString("dniUsuarioLog", "");
        String nombreUsuario = settings.getString("nombreUsuario", "");
        String apellidosUsuario = settings.getString("apellidosUsuario", "");
        String telfUsuario = settings.getString("telfUsuario", "");

        String nombreAlojReservaSelec = settings.getString("nombreAlojReservaSelec", "");
        String descripAlojReservaSelec = settings.getString("descripAlojReservaSelec", "");
        String localidadAlojReservaSelec = settings.getString("localidadAlojReservaSelec", "");
        String telefonoAlojReservaSelec = settings.getString("telfAlojReservaSelec", "");
        String emailAlojReservaSelec = settings.getString("emailAlojReservaSelec", "");
        String webAlojReservaSelec = settings.getString("webAlojReservaSelec", "");

        //se recorta la hora de la date:
        fechaRealizadaReservaSelec = fechaRealizadaReservaSelec.substring(0,10);
        fechaEntradaReservaSelec = fechaEntradaReservaSelec.substring(0,10);
        fechaSalidaReservaSelec = fechaSalidaReservaSelec.substring(0,10);

        //Se rellena el textView con los datos recuperados de la clase SharedPreferences
        tvCodReserva = findViewById(R.id.tvCodReserva);
        tvCodReserva.setText(codReservaSelec);
        tvFechaEntrada = findViewById(R.id.tvFechaEntrada);
        tvFechaEntrada.setText(fechaEntradaReservaSelec);
        tvFechaSalida = findViewById(R.id.tvFechaSalida);
        tvFechaSalida.setText(fechaSalidaReservaSelec);

        nombreUsu = findViewById(R.id.tvDniUsu);
        nombreUsu.setText(dniUsuarioLog);
        apellidoUsu= findViewById(R.id.tvNombreUsu);
        apellidoUsu.setText(nombreUsuario);
        telfUsu= findViewById(R.id.tvApellidosUsu);
        telfUsu.setText(apellidosUsuario);
        emailUsu= findViewById(R.id.tvTelfUsu);
        emailUsu.setText(telfUsuario);

        nombreAloj = findViewById(R.id.tvNombre);
        nombreAloj.setText(nombreAlojReservaSelec);
        descripAloj = findViewById(R.id.tvDescripcion);
        descripAloj.setText(descripAlojReservaSelec);
        localidadAloj =findViewById(R.id.tvlocalidad);
        localidadAloj.setText(localidadAlojReservaSelec);
        telefonoAloj= findViewById(R.id.tvTelefono);
        telefonoAloj.setText(telefonoAlojReservaSelec);
        webAloj= findViewById(R.id.tvWeb);
        webAloj.setText(emailAlojReservaSelec);
        emailAloj= findViewById(R.id.tvEmail);
        emailAloj.setText(webAlojReservaSelec);


        Toast toast1 = Toast.makeText(getApplicationContext(), "codReservaSelec" + codReservaSelec.toString(), Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void atras(View view){
        Intent i = new Intent(this, VerReservas.class);
        startActivity(i);
    }
    public void borrarReserva(View view){
        new ConnectMySqlBBDDDelete().execute(" Estos Strings van a variableNoUsada que no usaremos en este ejemplo y podiamos haber declarado como Void "," si lo necesitaramos podemos cambiar el String por otro tipo de datos "," podemos añadir más de 4 datos que los de este ejemplo, todos los que necesitemos "," y recuerda que se usan como un array, para acceder en concreto a este usaríamos variableNoUsada[3] "); //Arrancamos el AsyncTask. el método "execute" envía datos directamente a doInBackground()

        Intent i = new Intent(this, VerReservas.class);
        startActivity(i);
    }

    private class ConnectMySqlBBDDDelete extends AsyncTask<String, Void, List<Reserva>> {
        private boolean cancelarSiHayMas100Archivos;
        private ProgressBar miBarraDeProgreso;
        String res = "";

        /**
         * Contructor de ejemplo que podemos crear en el AsyncTask
         *
         * en este ejemplo le pasamos un booleano que indica si hay más de 100 archivos o no. Si le pasas true se cancela por la mitad del progreso, si le pasas false seguirá hasta el final sin cancelar la descarga simulada
         */
        public ConnectMySqlBBDDDelete() {

        }
        /**
         * Se ejecuta antes de empezar el hilo en segundo plano. Después de este se ejecuta el método "doInBackground" en Segundo Plano
         *
         * Se ejecuta en el hilo: PRINCIPAL
         */
        @Override
        protected void onPreExecute() {
           // Log.v(TAG_LOG, "ANTES de EMPEZAR la descarga. Hilo PRINCIPAL");
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

            boolean borrado = false;
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String url =  "jdbc:mysql://188.213.5.150:3306/alojamientos?serverTimezone=UTC";
                //url = "Server=188.213.5.150;Port=3306;Database=alojamientos;Uid=accesoadatos;Pwd=123456;";
                java.sql.Connection con = DriverManager.getConnection(url,"accesoadatos","123456");

                //String query = "SELECT * FROM tReservas";

                //FALTA: and R.cCodUsuario='" & usuarioLogeado.ToString & "'"
                String query = "DELETE FROM tReservas where cReserva=" + codReservaSelec ;
                // create the java statement
                Statement st = con.createStatement();

                // execute the query, and get a java resultset
                st.execute(query);

                // iterate through the java resultset

                    System.out.format("La reserva se eliminó correctamente.");
                borrado = true;

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

            return null;
        }


    }
}
