package com.example.alojamientoseuskadi;

import java.io.Serializable;

public class Entidad implements Serializable {
    private int imgFoto;
    private String titulo;
    private String contenido;
    private String contenido2;

    public Entidad(int imgFoto, String titulo, String contenido){
        this.imgFoto=imgFoto;
        this.titulo=titulo;
        this.contenido=contenido;
    }

    public int getImgFoto(){
        return imgFoto;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getContenido(){
        return contenido;
    }
}
