package com.example.alojamientoseuskadi;

public class Reserva {
    private int codReserva;
    private int codAlojamiento;
    private String codUsuario;
    private String fechaRealizada;
    private String fechaEntrada;
    private String fechaSalida;

    public Reserva() {
    }

    public Reserva(int codReserva, int codAlojamiento, String codUsuario, String fechaRealizada, String fechaEntrada, String fechaSalida) {
        this.codReserva = codReserva;
        this.codAlojamiento = codAlojamiento;
        this.codUsuario = codUsuario;
        this.fechaRealizada = fechaRealizada;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public int getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(int codReserva) {
        this.codReserva = codReserva;
    }

    public int getCodAlojamiento() {
        return codAlojamiento;
    }

    public void setCodAlojamiento(int codAlojamiento) {
        this.codAlojamiento = codAlojamiento;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(String fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        return
                "codReserva:" + codReserva  +"\n" +
                "codAlojamiento:" + codAlojamiento  +"\n" +
                "codUsuario:" + codUsuario +"\n" +
                "Realizada el: " + fechaRealizada +"\n" +
                "Fecha reserva:" + fechaEntrada + " - " + fechaSalida +
                '}';
    }
}
