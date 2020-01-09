package com.example.alojamientoseuskadi;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseJson {
    public List<Receta> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readRecetasArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<Receta> readRecetasArray(JsonReader reader) throws IOException {
        List<Receta> recetas = new ArrayList<Receta>();

        reader.beginArray();
        while (reader.hasNext()) {
            recetas.add(readReceta(reader));
        }
        reader.endArray();
        return recetas;
    }

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
}
