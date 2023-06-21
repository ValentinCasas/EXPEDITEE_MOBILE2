package com.example.expeditee_mobile.models;

import java.io.Serializable;

public class ComprobanteEfectivo implements Serializable {

    private int id;
    private String imagen;
    private Pedido pedido;

    public ComprobanteEfectivo() {
    }

    public ComprobanteEfectivo(int id, String imagen, Pedido pedido) {
        this.id = id;
        this.imagen = imagen;
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
