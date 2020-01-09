package com.example.alojamientoseuskadi;

public class Receta {
    private String nombre;
    private String pueblo;
    private String ingredientes;

    public Receta(String nombre, String pueblo, String ingredientes) {
        super();
        this.nombre = nombre;
        this.pueblo = pueblo;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPueblo() {
        return pueblo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

}
