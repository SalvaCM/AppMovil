package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    Toast notificacion;
    private TextView usuario, contrasena1, contrasena2, nombre, apellidos, telefono, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario=(EditText)findViewById(R.id.dni);
        contrasena1=(EditText)findViewById(R.id.contrasena1);
        contrasena2=(EditText)findViewById(R.id.contrasena2);
        nombre=(EditText)findViewById(R.id.nombre);
        apellidos=(EditText)findViewById(R.id.apellidos);
        telefono=(EditText)findViewById(R.id.telefono);
        email=(EditText)findViewById(R.id.email);
    }

    public void btnRegistrarse(View view){

        //se comprueba que se haya introducido datos en todos los campos
        if (usuario.length()<1 || contrasena1.length()<1 || contrasena2.length()<1 || nombre.length()<1|| apellidos.length()<1 || telefono.length()<1 || email.length()<1  ){
            notificacion = Toast.makeText(this, R.string.msjError, Toast.LENGTH_LONG);
            notificacion.show();
        }
        //se comprueba que el usuario no esté ya registrado:
        /*
        else if (contrasena1.getText().toString() != contrasena2.getText().toString()){

            notificacion = Toast.makeText(this, R.string.msjErrorUsu, Toast.LENGTH_LONG);
            notificacion.show();
        }
        */
        //se comprueba que las dos contraseñas introducidas sean iguales
        else if (!contrasena1.getText().toString().equals(contrasena2.getText().toString())){
            notificacion = Toast.makeText(this, R.string.msjErrorContr, Toast.LENGTH_LONG);
            notificacion.show();
        }
        //Si las validaciones se cumplen, se inserta en la BBDD
        else{
            notificacion = Toast.makeText(this, "Pruebas: "+ usuario.getText().toString() + contrasena1.getText().toString() +contrasena2.getText().toString() +nombre.getText().toString() +apellidos.getText().toString() +telefono.getText().toString() +email.getText().toString() , Toast.LENGTH_LONG);
            notificacion.show();
            //insertar en BBDD

            //Mostrar mensaje:
            //notificacion = Toast.makeText(this, R.string.msjUsuarioRegis, Toast.LENGTH_LONG);
            //notificacion.show();
            Intent i = new Intent(this,inicio.class);
            startActivity(i);
        }




    }

    public void btnCancelar(View view){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
}
