package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
    Toast notificacion;
    private TextView usuario, contrasena1, contrasena2, nombres, apellidos, telefono, email;
    String registrado = "";
    String errorTexto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario=(EditText)findViewById(R.id.dni);
        contrasena1=(EditText)findViewById(R.id.usuario);
        contrasena2=(EditText)findViewById(R.id.contrasena2);
        nombres=(EditText)findViewById(R.id.nombre);
        apellidos=(EditText)findViewById(R.id.apellidos);
        telefono=(EditText)findViewById(R.id.telefono);
        email=(EditText)findViewById(R.id.email);
    }
    // ************************************* INICIO BOTON REGISTRARSE **********************************
    public void btnRegistrarse(View view){
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.toString());
        //se comprueba que se haya introducido datos en todos los campos
        if (usuario.length()<1 || contrasena1.length()<1 || contrasena2.length()<1 || nombres.length()<1|| apellidos.length()<1 || telefono.length()<1 || email.length()<1  ) {
            notificacion = Toast.makeText(this, R.string.msjError, Toast.LENGTH_LONG);
            notificacion.show();
        }
        //se comprueba que las dos contraseñas introducidas sean iguales
        else if (!contrasena1.getText().toString().equals(contrasena2.getText().toString())) {
            notificacion = Toast.makeText(this, R.string.msjErrorContr, Toast.LENGTH_LONG);
            notificacion.show();

        }
        //validador del email
        else if(matcher.matches() == false){
            notificacion = Toast.makeText(this, "El formato del correo es incorrecto", Toast.LENGTH_LONG);
            notificacion.show();
        }
        //numero 9 digitos
        else if(telefono.length()< 9 ){
            notificacion = Toast.makeText(this, "El numero tiene que tener 9 digitos", Toast.LENGTH_LONG);
            notificacion.show();
        }

        //Si las validaciones se cumplen, se inserta en la BBDD
        else{
            notificacion = Toast.makeText(this, "Pruebas: "+ usuario.getText().toString() + contrasena1.getText().toString() +contrasena2.getText().toString() +nombres.getText().toString() +apellidos.getText().toString() +telefono.getText().toString() +email.getText().toString() , Toast.LENGTH_LONG);
            notificacion.show();
            //insertar en BBDD
            new ConnectMySql().execute();
            while (registrado.equals("")){
                //para que espere la app hasta que el hilo de su respuesta
            }
            if(registrado.equals("si")){
                //si ha salido bien, se abrira sesion y se ira a la ventana de inicio

                SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("usuarioLogeado", usuario.getText().toString());
                editor.commit();
                Intent i = new Intent(this,inicio.class);
                startActivity(i);

            }else if(registrado.equals("no")){
                // si ha dado fallo, se mostrara por este mensaje
                notificacion = Toast.makeText(this, errorTexto, Toast.LENGTH_LONG);
                notificacion.show();
            }
            //Intent i = new Intent(this,inicio.class);
            //startActivity(i);
        }
    }
    // ********************************** FIN BOTON REGISTRARSE ************************************
    private class ConnectMySql extends AsyncTask<String, Void, String> {

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
            String contra = encriptar(contrasena1.getText().toString());
            String nombre = nombres.getText().toString();
            String apellido = apellidos.getText().toString();
            String movil = telefono.getText().toString();
            String correo = email.getText().toString();

            try {
                //conexion y insercion en la BBDD
                Class.forName("com.mysql.jdbc.Driver");
                String url =  "jdbc:mysql://188.213.5.150:3306/alojamientos?serverTimezone=UTC";
                java.sql.Connection con = DriverManager.getConnection(url,"accesoadatos","123456");
                String query = "INSERT INTO tUsuarios VALUES ('" + usu + "','" + apellido + "', '" + contra + "','" + nombre + "', '" + movil + "','','" + correo + "' );";

                PreparedStatement prest = con.prepareStatement(query);
                prest.execute();
                registrado = "si";
                con.close();
                //erorres que pueden dar
            } catch (ClassNotFoundException e) {

                registrado = "no";
                System.out.println("Error 1:" + e);
                errorTexto = "Error: Clase no encontrada";
            } catch (SQLException e) {

                registrado = "no";
                System.out.println("Error 2:" + e);
                errorTexto = "Error: Ese usuario ya existe";
            }
            catch (Exception e) {
                registrado = "no";
                System.out.println("Error 3:" + e);
                errorTexto = e.toString();
            }

            return res;
        }
    }


    // *************************** INICIO BOTON CANCELAR *******************************************
    public void btnCancelar(View view){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
    // **************************** FIN BOTON CANCELAR *********************************************
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

    // ********************************** INICIO FORMATO DNI *************************************
    public boolean validarDNI(String dni){

        boolean correcto=false;
        Pattern pattern=Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher=pattern.matcher(dni);

        if(matcher.matches()){
            String letra=matcher.group(2);
            String letras="TRWAGMYFPDXBNJZSQVHLCKE";
            int index=Integer.parseInt(matcher.group(1));
            index=index%23;
            String reference=letras.substring(index,index+1);
            if(reference.equalsIgnoreCase(letra)){
                correcto=true;
            }else{
                correcto=false;
            }
        }else{
            correcto=false;
        }
        return correcto;

    }

    // ************************************ FIN FORMATO DNI *************************************


}