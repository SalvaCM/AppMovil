package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HacerReserva extends AppCompatActivity {
    private final boolean CANCELAR_SI_MAS_DE_1000_IMAGENES = true;//se encargará de decir si cancelamos o no la descarga al pasa de 100 imágenes.
    private final String TAG_LOG = "test";//El tag del log para que veamos por consola lo que va haciendo nuestra aplicación.
    private TextView TV_mensaje; //Y el TextView que será el que muestre el mensaje que cambia de color.
    //pruebas
    // Declaración de elementos de la actividad.xml
    EditText etFechaEntrada;
    EditText etFechaSalida;
    Calendar calendarioEntrada = Calendar.getInstance();
    Calendar calendarioSalida = Calendar.getInstance();

    TextView telefonoAloj;
    TextView nombreAloj;
    TextView descripAloj;
    TextView localidadAloj;
    TextView emailAloj;
    TextView webAloj;
    Integer idAlojSeleccionado;


    //Declaración de variables
    String Entrada = "dd/MM/yyyy";
    String Salida = "dd/MM/yyyy"; //In which you need put here
    String formatoDeFecha = "yyyy-MM-dd"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat fechaActual = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_reserva);

        //para recuperar los datos que nos hemos pasado de la actividad anterior (VerTareas)
        //String idAlojSeleccionado =getIntent().getStringExtra("idAlojSeleccionado");
        String nombreAlojamiento = getIntent().getStringExtra("nombreAlojSeleccionado");
        String descripcionAlojSeleccionado = getIntent().getStringExtra("descripcionAlojSeleccionado");
        String localidadAlojSeleccionado = getIntent().getStringExtra("localidadAlojSeleccionado");
        String telefonoAlojSeleccionado = getIntent().getStringExtra("telefonoAlojSeleccionado");
        String webAlojSeleccionado = getIntent().getStringExtra("webAlojSeleccionado");
        String emailAlojSeleccionado = getIntent().getStringExtra("emailAlojSeleccionado");

        Toast toast1 = Toast.makeText(getApplicationContext(), "nombreAlojSeleccionado" + nombreAlojamiento, Toast.LENGTH_SHORT);
        toast1.show();

        //Se saca el usuario que se ha logueado que está guardado en la clase SharedPreferences



        //Se rellenan los datos del activity con los datos que hemos pasado:
        nombreAloj = findViewById(R.id.tvNombre);
        nombreAloj.setText(nombreAlojamiento);

        descripAloj = findViewById(R.id.tvDescripcion);
        descripAloj.setText(descripcionAlojSeleccionado);
        localidadAloj =findViewById(R.id.tvlocalidad);
        localidadAloj.setText(localidadAlojSeleccionado);
        telefonoAloj= findViewById(R.id.tvTelefono);
        telefonoAloj.setText(telefonoAlojSeleccionado);
        webAloj= findViewById(R.id.tvWeb);
        webAloj.setText(webAlojSeleccionado);
        emailAloj= findViewById(R.id.tvEmail);
        emailAloj.setText(emailAlojSeleccionado);

        //Fecha de entrada:
        etFechaEntrada = findViewById(R.id.fechaEntrada);
        etFechaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(HacerReserva.this, date, calendarioEntrada
                        .get(Calendar.YEAR), calendarioEntrada.get(Calendar.MONTH),
                        calendarioEntrada.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Fecha Salida:
        etFechaSalida = findViewById(R.id.fechaSalida);
        etFechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(HacerReserva.this, date2, calendarioSalida
                        .get(Calendar.YEAR), calendarioSalida.get(Calendar.MONTH),
                        calendarioSalida.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    //fechaEntrada
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendarioEntrada.set(Calendar.YEAR, year);
            calendarioEntrada.set(Calendar.MONTH, monthOfYear);
            calendarioEntrada.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };

    //fechaSalida
    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendarioSalida.set(Calendar.YEAR, year);
            calendarioSalida.set(Calendar.MONTH, monthOfYear);
            calendarioSalida.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput2();
        }

    };

    //Fecha entrada
    private void actualizarInput() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        String strFechaActual = fechaActual.format(calendar.getTime());


         Date hola1 =calendarioEntrada.getTime();
         Date hola =calendar.getTime();
        int dias=(int) ((hola1.getTime()-hola.getTime())/86400000);

        if(dias < 0){

                Toast toast1 = Toast.makeText(getApplicationContext(), "la fecha de Entrada debe ser mayor a la  a de hoy", Toast.LENGTH_SHORT);
                toast1.show();

                Entrada = null;

        } else {
            etFechaSalida.setEnabled(true);
            etFechaSalida.setClickable(true);
            etFechaSalida.setText(null);

            etFechaEntrada.setText(sdf.format(calendarioEntrada.getTime()));
            Entrada = fechaActual.format(calendarioEntrada.getTime());

        }
    }

    //Fecha Salida
    private void actualizarInput2() {



        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        etFechaSalida = findViewById(R.id.fechaSalida);



        String strFechaActual = fechaActual.format(calendar.getTime());


        Date hola1 =calendarioSalida.getTime();
        Date hola =calendarioEntrada.getTime();
        int dias=(int) ((hola1.getTime()-hola.getTime())/86400000);

        if(dias < 0){

            Toast toast1 = Toast.makeText(getApplicationContext(), "la fecha de salida debe ser igual o mayor a la de entrada", Toast.LENGTH_SHORT);
            toast1.show();

            Salida = null;

        } else {

            etFechaSalida.setText(sdf.format(calendarioSalida.getTime()));
            Salida = fechaActual.format(calendarioSalida.getTime());

        }

    }

    //Métodos de los botones
    public void atras(View view){
        Intent i = new Intent(this, VerAlojamientos.class);
        startActivity(i);
    }

    private class ConnectMySql extends AsyncTask<String, Void, String> {
        private boolean cancelarSiHayMas100Archivos;
        private ProgressBar miBarraDeProgreso;
        String res = "";

        /**
         * Contructor de ejemplo que podemos crear en el AsyncTask
         * <p>
         * en este ejemplo le pasamos un booleano que indica si hay más de 100 archivos o no. Si le pasas true se cancela por la mitad del progreso, si le pasas false seguirá hasta el final sin cancelar la descarga simulada
         */

        /**
         * Se ejecuta antes de empezar el hilo en segundo plano. Después de este se ejecuta el método "doInBackground" en Segundo Plano
         * <p>
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
        protected String doInBackground(String... variableNoUsada) {
            SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
            String nombre = settings.getString("usuarioLogeado", "");

            ResultSet rs;
            Statement st = null;
            ResultSet rs1;
            Statement st1 = null;
            String sURL = "";
            Integer reservas = 0;
            String strFechaActual = fechaActual.format(calendar.getTime());


            int cantidadImagenesDescargadas = 0;
            float progreso = 0.0f;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                //String url = "jdbc:mysql://188.213.5.150:3306?";
                //url += "user=accesoadatos&password=123456";

                String url =  "jdbc:mysql://188.213.5.150:3306/alojamientos?serverTimezone=UTC";
                //url = "Server=188.213.5.150;Port=3306;Database=alojamientos;Uid=accesoadatos;Pwd=123456;";
                java.sql.Connection con = DriverManager.getConnection(url,"accesoadatos","123456");

                String query = "SELECT Max(cReserva) FROM tReservas";
                // create the java statement
                st = con.createStatement();

                // execute the query, and get a java resultset
                rs = st.executeQuery(query);

                // iterate through the java resultset
                while (rs.next()) {

                    try {

                        reservas = rs.getInt("Max(cReserva)");
                        reservas = reservas + 1;


                    } catch (SQLException e) {
                        reservas = 1;
                    }
                }
                st.close();
                rs.close();

                String queryCodAloja = "SELECT cCodAlojamiento FROM tAlojamientos WHERE cNombre = '" + nombreAloj.getText() +"'";
                // create the java statement
                st = con.createStatement();

                // execute the query, and get a java resultset
                rs = st.executeQuery(queryCodAloja);

                while (rs.next()) {

                    try {

                        idAlojSeleccionado = rs.getInt("cCodAlojamiento");
                        //idAlojSeleccionado = reservas + 1;


                    } catch (SQLException e) {
                        reservas = 1;
                    }
                }

                st.close();
                rs.close();

                String query1 = "INSERT INTO `tReservas`(`cReserva`,`cCodAlojamiento`, `cCodUsuario`, `cFechaEntrada`, `cFechaRealizada`, `cFechaSalida`) VALUES (" + reservas + "," + idAlojSeleccionado + ",'"  +nombre +"','" +Entrada  + "','" +strFechaActual  + "','" + Salida + "')";
                // create the java statement
                st1 = con.createStatement();

                // execute the query, and get a java resultset
                boolean algo =  st1.execute(query1);

                // iterate through the java resultset
                // while (rs.next()) {

                //   String firstName = rs.getString("cDni");


                // print the results
                // System.out.format("%s \n", firstName);
                //}
                st1.close();



            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
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
        protected void onPostExecute(List<Reserva> cantidadProcesados) {

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


    public void hacerReserva(View view)throws SQLException {

        new ConnectMySql().execute();
        Toast toast1 = Toast.makeText(getApplicationContext(), "reserva realizada", Toast.LENGTH_SHORT);
        toast1.show();
        Intent i = new Intent(this, VerAlojamientos.class);
        startActivity(i);
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
    //FIN Menu ActionBar
}