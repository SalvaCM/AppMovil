package com.example.alojamientoseuskadi;

import java.util.List;

public class Usuario {
    private String dni;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private String telefono;
    private String email;

    public Usuario(String dni, String nombre, String apellidos, String contrasena, String telefono, String email) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(String dni, String nombre, String apellidos,String telefono) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String pass) {
        this.contrasena = pass;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}