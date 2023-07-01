package com.example.expeditee_mobile.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Pedido implements Serializable {

    private String estado;
    private String fecha;
    private int id;
    private int idEmpleado;
    private int idCliente;
    private double montoTotal;

    public Pedido() {
    }


    public Pedido(String estado, String fecha, int id, int idEmpleado, int idCliente, double montoTotal) {
        this.estado = estado;
        this.fecha = fecha;
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }



}