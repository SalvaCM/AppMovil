package com.example.alojamientoseuskadi;

import java.io.Serializable;

public class EntidadReservas implements Serializable {
    private int imgFoto;
    private int codAlojamiento;
    private String titulo;
    private String contenido;
    private String telefono;
    private String web;
    private String desc;
    private String localidad;
    private String email;

    public EntidadReservas(int codAlojamiento, int imgFoto, String titulo, String contenido, String telefono, String web, String desc, String localidad, String email) {
        this.codAlojamiento = codAlojamiento;
        this.imgFoto = imgFoto;
        this.titulo = titulo;
        this.contenido = contenido;
        this.telefono = telefono;
        this.web = web;
        this.desc = desc;
        this.localidad = localidad;
        this.email = email;
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

    public int getcodAlojamiento() {return codAlojamiento;}

    public void setcodAlojamiento(int codAlojamiento) {this.codAlojamiento = codAlojamiento;}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
