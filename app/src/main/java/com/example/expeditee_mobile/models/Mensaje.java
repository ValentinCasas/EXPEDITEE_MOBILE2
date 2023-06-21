package com.example.expeditee_mobile.models;

import java.io.Serializable;
import java.util.Date;

public class Mensaje implements Serializable {

    private int id;
    private String descripcion;
    private Usuario emisor;
    private Usuario receptor;
    private Date fecha;

    public Mensaje() {
    }

    public Mensaje(int id, String descripcion, Usuario emisor, Usuario receptor, Date fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.emisor = emisor;
        this.receptor = receptor;
        this.fecha = fecha;
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

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
