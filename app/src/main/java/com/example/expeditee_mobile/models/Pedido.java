package com.example.expeditee_mobile.models;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable {

    private int id;
    private String estado;
    private Date fecha;
    private ListaFavoritos lista;
    private double montoTotal;

    public Pedido() {
    }

    public Pedido(int id, String estado, Date fecha, ListaFavoritos lista, double montoTotal) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
        this.lista = lista;
        this.montoTotal = montoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ListaFavoritos getLista() {
        return lista;
    }

    public void setLista(ListaFavoritos lista) {
        this.lista = lista;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
