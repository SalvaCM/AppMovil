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
    //FIN LEER USUARIO

    //LEER ALOJAMIENTO
    //readJsonStream: recibe un inputstream con el contenido de nuestro fichero entremeses.json y nos devolverá nuestra lista formada.
    public List<Alojamiento> readJsonStreamAlojamiento(InputStream in) throws IOException {
        JsonReader reader3 = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readAlojamientoArray(reader3);
        } finally {
            reader3.close();
        }
    }

    //este método creará nuestra Lista y será el encargado de añadir cada objeto Receta desde que comienza el array (.beginArray(), que correspondería con el 1º [ de nuestro entremeses.json) hasta el final del array (.endArray(), que correspondería con el último ]).
    public List<Alojamiento> readAlojamientoArray(JsonReader reader3) throws IOException {
        List<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();

        reader3.beginArray();
        while (reader3.hasNext()) {
            listaAlojamientos.add(readAlojamiento(reader3));
        }
        reader3.endArray();
        return listaAlojamientos;
    }

    //éste método irá recorriendo los atributos entre {}, en nuestro caso: nombre, pueblo e ingredientes e irá creando un objeto de tipo Receta (tipo Alojamiento, Usuario, Reserva) por cada par de llaves {} encontradas y será devuelto al método anteriormente descrito para que sea añadido a la lista.
    public Alojamiento readAlojamiento(JsonReader reader) throws IOException {
        int codAlojamiento = 0;
        String nombre= null;
        String telefono= null;
        String tipo = null;
        String web = null;
        int capacidad = 0;
        String descripcion = null;
        String email = null;
        String latitud = null;
        String longitud = null;
        String localidad = null;
        String localizacion = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("codAlojamiento")) {
                codAlojamiento = reader.nextInt();
            } else if (name.equals("nombre")) {
                nombre = reader.nextString();
            } else if (name.equals("telefono")) {
                telefono = reader.nextString();
            } else if (name.equals("tipo")) {
                tipo = reader.nextString();
            }else if (name.equals("web")) {
                web = reader.nextString();
            }else if (name.equals("capacidad")) {
                capacidad = reader.nextInt();
            }else if (name.equals("descripcion")) {
                descripcion = reader.nextString();
            }else if (name.equals("email")) {
                email = reader.nextString();
            }else if (name.equals("latitud")) {
                latitud = reader.nextString();
            }else if (name.equals("longitud")) {
                longitud = reader.nextString();
            }else if (name.equals("localidad")) {
                localidad = reader.nextString();
            }else if (name.equals("localizacion")) {
                localizacion = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Alojamiento(codAlojamiento, nombre, telefono, tipo, web, capacidad, descripcion, email, latitud, longitud, localidad, localizacion) ;
    }
    //FIN LEER ALOJAMIENTO

    //LEER RESERVA
    //readJsonStream: recibe un inputstream con el contenido de nuestro fichero entremeses.json y nos devolverá nuestra lista formada.
    public List<Reserva> readJsonStreamReserva(InputStream in) throws IOException {
        JsonReader reader3 = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readReservaArray(reader3);
        } finally {
            reader3.close();
        }
    }

    //este método creará nuestra Lista y será el encargado de añadir cada objeto Receta desde que comienza el array (.beginArray(), que correspondería con el 1º [ de nuestro entremeses.json) hasta el final del array (.endArray(), que correspondería con el último ]).
    public List<Reserva> readReservaArray(JsonReader reader3) throws IOException {
        List<Reserva> listaReservas = new ArrayList<Reserva>();

        reader3.beginArray();
        while (reader3.hasNext()) {
            listaReservas.add(readReserva(reader3));
        }
        reader3.endArray();
        return listaReservas;
    }

    //éste método irá recorriendo los atributos entre {}, en nuestro caso: nombre, pueblo e ingredientes e irá creando un objeto de tipo Receta (tipo Alojamiento, Usuario, Reserva) por cada par de llaves {} encontradas y será devuelto al método anteriormente descrito para que sea añadido a la lista.
    public Reserva readReserva(JsonReader reader) throws IOException {
        int codReserva = 0;
        int codAlojamiento = 0;
        String codUsuario= null;
        String fechaRealizada= null;
        String fechaEntrada = null;
        String fechaSalida = null;


        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("codReserva")) {
                codReserva = reader.nextInt();
            } else if (name.equals("codAlojamiento")) {
                codAlojamiento = reader.nextInt();
            } else if (name.equals("codUsuario")) {
                codUsuario = reader.nextString();
            } else if (name.equals("fechaRealizada")) {
                fechaRealizada = reader.nextString();
            }else if (name.equals("fechaEntrada")) {
                fechaEntrada = reader.nextString();
            }else if (name.equals("fechaSalida")) {
                fechaSalida = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Reserva(codReserva,codAlojamiento,codUsuario,fechaRealizada,fechaEntrada,fechaSalida ) ;
    }
    //FIN LEER RESERVA
}
