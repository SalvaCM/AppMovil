package com.example.alojamientoseuskadi;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseJson {

    //LEER RECETA
    //readJsonStream: recibe un inputstream con el contenido de nuestro fichero entremeses.json y nos devolverá nuestra lista formada.
    public List<Receta> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readRecetasArray(reader);
        } finally {
            reader.close();
        }
    }

    //readRecetaArray: este método creará nuestra Lista y será el encargado de añadir cada objeto Receta desde que comienza el array (.beginArray(), que correspondería con el 1º [ de nuestro entremeses.json) hasta el final del array (.endArray(), que correspondería con el último ]).
    public List<Receta> readRecetasArray(JsonReader reader) throws IOException {
        List<Receta> recetas = new ArrayList<Receta>();

        reader.beginArray();
        while (reader.hasNext()) {
            recetas.add(readReceta(reader));
        }
        reader.endArray();
        return recetas;
    }

    //readReceta:  éste método irá recorriendo los atributos entre {}, en nuestro caso: nombre, pueblo e ingredientes e irá creando un objeto de tipo Receta (tipo Alojamiento, Usuario, Reserva) por cada par de llaves {} encontradas y será devuelto al método anteriormente descrito para que sea añadido a la lista.
    public Receta readReceta(JsonReader reader) throws IOException {
        String nombre = null;
        String pueblo = null;
        String ingredientes = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("nombre")) {
                nombre = reader.nextString();
            } else if (name.equals("pueblo")) {
                pueblo = reader.nextString();
            } else if (name.equals("ingredientes") && reader.peek() != JsonToken.NULL) {
                ingredientes = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Receta(nombre, pueblo, ingredientes);
    }

    //LEER USUARIO
    //readJsonStream: recibe un inputstream con el contenido de nuestro fichero entremeses.json y nos devolverá nuestra lista formada.
    public List<Usuario> readJsonStreamUSUARIO(InputStream in) throws IOException {
        JsonReader reader1 = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readUsuarioArray(reader1);
        } finally {
            reader1.close();
        }
    }

    //readRecetaArray: este método creará nuestra Lista y será el encargado de añadir cada objeto Receta desde que comienza el array (.beginArray(), que correspondería con el 1º [ de nuestro entremeses.json) hasta el final del array (.endArray(), que correspondería con el último ]).
    public List<Usuario> readUsuarioArray(JsonReader reader1) throws IOException {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        reader1.beginArray();
        while (reader1.hasNext()) {
            listaUsuarios.add(readUsuario(reader1));
        }
        reader1.endArray();
        return listaUsuarios;
    }

    //readReceta:  éste método irá recorriendo los atributos entre {}, en nuestro caso: nombre, pueblo e ingredientes e irá creando un objeto de tipo Receta (tipo Alojamiento, Usuario, Reserva) por cada par de llaves {} encontradas y será devuelto al método anteriormente descrito para que sea añadido a la lista.
    public Usuario readUsuario(JsonReader reader) throws IOException {
        String dni= null;
        String nombre= null;
        String apellidos= null;
        String contrasena= null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("dni")) {
                dni = reader.nextString();
            } else if (name.equals("nombre")) {
                nombre = reader.nextString();
            } else if (name.equals("apellidos") && reader.peek() != JsonToken.NULL) {
                apellidos = reader.nextString();
            } else if (name.equals("contrasena")) {
                contrasena = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Usuario(dni,nombre, apellidos, contrasena);
    }
}
