package com.example.alojamientoseuskadi;

import java.util.List;

public class Usuario {
    private String dni;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private List<Usuarios> listaUsuarios;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> friends) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String pass) {
        this.contrasena = pass;
    }
}