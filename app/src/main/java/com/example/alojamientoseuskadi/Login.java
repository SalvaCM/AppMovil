package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Login extends AppCompatActivity {
    private List<Usuario> listaUsuarios;
    private ParseJson parse1;
    private EditText usuario;
    private EditText contrasena;
    private boolean usuarioCorrecto = false;
    private boolean contrasenaCorrecta = false;
    private String usuarioIntroducido;
    private String contrasenaIntroducida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usuario=(EditText)findViewById(R.id.usuario);
        contrasena=(EditText)findViewById(R.id.contraseña);

        SharedPreferences prefs = getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario.setText(prefs.getString("usuario", ""));
        contrasena.setText(prefs.getString("contrasena", ""));


        //*** LEER JSON: Obtener el fichero json desde la carpeta raw
        InputStream usu = getResources().openRawResource(R.raw.usuarios);
        try {
            // re = readJsonStream(is);
            parse1 = new ParseJson();
            listaUsuarios = parse1.readJsonStreamUSUARIO(usu); //recibe un inputstream con el contenido de nuestro fichero .json y nos devolverá nuestra lista formada.
            System.out.println("Lectura Json terminada");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView et = (TextView) findViewById(R.id.tv_test_json);

        StringBuilder receta = new StringBuilder();
        // Recorrer objeto List<Usuario> y concatenarlo en una variable para
        // mostrarlo.
        receta.append("\nPRUEBAS:: " );
        for (Usuario u : listaUsuarios) {
            receta.append("\nDni: " + u.getDni());
            receta.append("\nNombre: " + u.getNombre());

        }

        et.setText(receta);
        //***FIN LEER JSON: Obtener el fichero json desde la carpeta raw

    }

    public void iniciar(View view){
        Toast toast1 = Toast.makeText(getApplicationContext(), "botón iniciar pulsado", Toast.LENGTH_SHORT);
        toast1.show();

        String usu=usuario.getText().toString();
        String contr=contrasena.getText().toString();
        Toast notificacion;
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);

        if (usuario.length()<1){
            notificacion = Toast.makeText(this, R.string.invalid_username, Toast.LENGTH_LONG);
            notificacion.show();
        }

        if (contrasena.length()<1){
            notificacion = Toast.makeText(this, R.string.invalid_password, Toast.LENGTH_LONG);
            notificacion.show();
        }

        //Se comprueba que el usuario y la contraseña introducidos sean correctos
        //Usuario
        for (Usuario u : listaUsuarios) {

            if (u.getDni().equals(usuario.getText().toString())) {
                usuarioCorrecto = true;
                usuarioIntroducido = u.getDni().toString();
                //contraseña
                if (u.getContrasena().equals(contrasena.getText().toString()) & contrasena.length() >= 1) {
                    contrasenaCorrecta = true;

                    //Se resetean los datos:
                    usuario.setText("");
                    contrasena.setText("");

                    //Si el usuario y la contraseña son correctos se entra en la app
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    //Se guarda el usuario y la contraseña y entra en la app
                    SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("usuarioLogeado", usuarioIntroducido);
                    editor.commit();
                    //SharedPreferences.Editor editor=preferencias.edit();
                    //editor.putString("usuarioLogeado", usuarioIntroducido);
                    //editor.putString("contrasena", contr);
                    //editor.commit();

                    setContentView(R.layout.activity_main);
                } else {
                    notificacion = Toast.makeText(this, R.string.invalid_password2, Toast.LENGTH_LONG);
                    notificacion.show();
                    //Se resetean los datos:
                    contrasena.setText("");
                }
     /*   else{
            //Se resetean los datos:
            usuario.setText("");
            contrasena.setText("");
            notificacion = Toast.makeText(this, R.string.login_failed, Toast.LENGTH_LONG);
            notificacion.show();
        }
*/
            }
        }}}




