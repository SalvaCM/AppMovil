package com.example.alojamientoseuskadi;

public class Alojamiento {
    private int codAlojamiento;
    private String nombre;
    private String telefono;
    private String tipo;
    private String web;
    private int capacidad;
    private String descripcion;
    private String email;
    private String latitud;
    private String longitud;
    private String localidad;
    private String localizacion;

    @Override
    public String toString() {
        return
                "Nombre:"+ nombre  +"\n" +
                "Dirección:"+localizacion + " "+localidad  +"\n" +
                "Latitud:"+latitud + "- longitud "+longitud  +"\n" +
                "Teléfono :" + telefono  +"\n" +
                "Email :" + email  +"\n" +
                "Web :" + web;
    }

    public Alojamiento() {
    }

    public Alojamiento(int codAlojamiento, String nombre, String telefono, String tipo, String web, int capacidad, String descripcion, String email, String latitud, String longitud, String localidad, String localizacion) {
        this.codAlojamiento = codAlojamiento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
        this.web = web;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.email = email;
        this.latitud = latitud;
        this.longitud = longitud;
        this.localidad = localidad;
        this.localizacion = localizacion;
    }

    public int getCodAlojamiento() {
        return codAlojamiento;
    }

    public void setCodAlojamiento(int codAlojamiento) {
        this.codAlojamiento = codAlojamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
