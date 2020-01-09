package com.example.alojamientoseuskadi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Pruebas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);
        loadJSONFromAsset("app/src/assets/raw/animales.json");
    }

    public String loadJSONFromAsset(String flName) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(flName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.v("MainActivity", "Load json ok");
        } catch (IOException ex) {
            Log.v("MainActivity", "Error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
