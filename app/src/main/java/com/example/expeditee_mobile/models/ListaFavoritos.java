package com.example.expeditee_mobile.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaFavoritos implements Serializable {

    private int id;
    private String nombre;
    private Usuario usuario;
    private ArrayList<Producto> productos;

    public ListaFavoritos() {
    }

    public ListaFavoritos(int id, String nombre, Usuario usuario, ArrayList<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
}
