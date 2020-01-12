package com.example.alojamientoseuskadi;

import java.io.Serializable;

public class Entidad implements Serializable {
    private int imgFoto;
    private String titulo;
    private String contenido;
    private String telefono;
    private String web;

    public Entidad(int imgFoto, String titulo, String contenido, String telefono, String web) {
        this.imgFoto = imgFoto;
        this.titulo = titulo;
        this.contenido = contenido;
        this.telefono = telefono;
        this.web = web;
    }

    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
