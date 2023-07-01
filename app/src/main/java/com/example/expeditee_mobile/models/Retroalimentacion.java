package com.example.expeditee_mobile.models;

import java.io.Serializable;

public class Retroalimentacion implements Serializable {

    private int id;
    private String descripcion;
    private String fechaEnvio;
    private Usuario usuario;

    public Retroalimentacion() {
    }

    public Retroalimentacion(int id, String descripcion, String fechaEnvio, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaEnvio = fechaEnvio;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
