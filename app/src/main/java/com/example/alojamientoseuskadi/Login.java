package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {

    public static String usuarioLogeado;
    private EditText usuario;
    private EditText contrasena;
    private String logeado = "";
    private TextView errorUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usuario=(EditText)findViewById(R.id.usuario);
        contrasena=(EditText)findViewById(R.id.contraseña);

        SharedPreferences prefs = getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario.setText(prefs.getString("usuario", ""));
        contrasena.setText(prefs.getString("contrasena", ""));


        errorUsu = (TextView) findViewById(R.id.txtErrorUsu);
        errorUsu.setText("");


    }

    // ************************* INICIO CLICK INICIAR SESION ************************************

    public void iniciar(View view) {
       new ConnectMySql().execute();
       while(logeado.equals("")){
        //este while es solamente para esperar la respuesta del proceso del background

       }
       //si se logea correctamente
       if(logeado.equals("si")){

           SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
           SharedPreferences.Editor editor = settings.edit();
           editor.putString("usuarioLogeado", usuario.getText().toString());
           editor.commit();

           Intent i = new Intent(this, MainActivity.class);
           startActivity(i);
    //si el proceso devuelve un "no"
       }else{
           errorUsu.setText("Usuario o contraseña mal introducido");
       }

    }
    // **************************** FIN CLICK INICIAR SESION *************************************

    // **************************** INICIO ENCRIPTADO DE CONTRASEÑA ******************************

    public String encriptar(String pass){
        String value = pass;
        String sha1 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e){
            e.printStackTrace();
        }

        return sha1;
    }
    // ******************************* FIN ENCRIPTADO DE CONTRASEÑA ******************************

    // ******************************* BOTON IR A REGISTRARSE ************************************
    public void registrarse(View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
    // ******************************** FIN BOTON REGISTRARSE *************************************

    // ***************************** INICIO PROCESO BASE DE DATOS *********************************
    private class ConnectMySql extends AsyncTask<String, Void, String>  {

        String res = "";

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        //proceso para conectar a la base de datos y ver si ese usuario y pass estan bien
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... variableNoUsada) {

            String usu = usuario.getText().toString();
            String contr = contrasena.getText().toString();

            try {
                Class.forName("com.mysql.jdbc.Driver");


                String url =  "jdbc:mysql://188.213.5.150:3306/alojamientos?serverTimezone=UTC";
                //url = "Server=188.213.5.150;Port=3306;Database=alojamientos;Uid=accesoadatos;Pwd=123456;";
                java.sql.Connection con = DriverManager.getConnection(url,"accesoadatos","123456");

                String query = "SELECT cDni, cContrasena FROM tUsuarios";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()){
                    String var1 = rs.getString("cDni");
                    String var2 = rs.getString("cContrasena");

                    if(var1.equals(usu) && var2.equals(encriptar(contr))){
                        logeado = "si";
                    }
                }
                //si la variable logeado sigue vacia, quiere decir que no ha coincidido los datos introducidos
                //con lo que hay en la base de datos
                if(logeado.equals("")){
                    logeado = "no";
                }

                rs.close();
                st.close();
                con.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return res;
        }
    }
    //****************************** FIN PROCESO BASE DE DATOS ***********************************
}


