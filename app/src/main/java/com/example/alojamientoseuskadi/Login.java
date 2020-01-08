package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText usuario;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usuario=(EditText)findViewById(R.id.usuario);
        contrasena=(EditText)findViewById(R.id.contraseña);

        SharedPreferences prefs = getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario.setText(prefs.getString("usuario", ""));
        contrasena.setText(prefs.getString("contrasena", ""));
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
        if(preferencias.getString("usuario", "").equals(usuario.getText().toString())){
            //contraseña
            if(preferencias.getString("contrasena", "").equals(contrasena.getText().toString())& contrasena.length()>=1){

                //Se resetean los datos:
                usuario.setText("");
                contrasena.setText("");

                //Si el usuario y la contraseña son correctos se entra en la app
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                setContentView(R.layout.activity_main);


            }
            else{
                //Se resetean los datos:
                contrasena.setText("");
            }
        }
        else{
            //Se resetean los datos:
            usuario.setText("");
            contrasena.setText("");

            notificacion = Toast.makeText(this, R.string.login_failed, Toast.LENGTH_LONG);
            notificacion.show();
        }

    }

    public void registrarse(View view){
        Toast toast2=Toast.makeText(getApplicationContext(), "Botón registrarse pulsado", Toast.LENGTH_SHORT);
        toast2.show();


    }


}
