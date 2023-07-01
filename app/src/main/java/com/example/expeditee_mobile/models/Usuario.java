package com.example.expeditee_mobile.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String apellido;
    private String ciudad;
    private String clave;
    private String mail;
    private String direccion;
    private Long dni;
    private String imagen;
    private String latitud;
    private String longitud;
    private String nombre;
    private String pais;
    private int rol;
    private Long telefono;
    private int idPedido;
    private Bitmap imagenFile;


    public Usuario() {
    }

    public Usuario(String mail, String clave) {
        this.mail = mail;
        this.clave = clave;
    }

    public Usuario(int id, String apellido, String ciudad, String clave, String mail, String direccion, Long dni, String imagen, String latitud, String longitud, String nombre, String pais, int rol, Long telefono, int idPedido, Bitmap imagenFile) {
        this.id = id;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.clave = clave;
        this.mail = mail;
        this.direccion = direccion;
        this.dni = dni;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.pais = pais;
        this.rol = rol;
        this.telefono = telefono;
        this.idPedido = idPedido;
        this.imagenFile = imagenFile;
    }

    public Usuario(int id, String apellido, String ciudad, String clave, String mail, String direccion, Long dni, String imagen, String latitud, String longitud, String nombre, String pais, int rol, Long telefono, int idPedido) {
        this.id = id;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.clave = clave;
        this.mail = mail;
        this.direccion = direccion;
        this.dni = dni;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.pais = pais;
        this.rol = rol;
        this.telefono = telefono;
        this.idPedido = idPedido;
    }

    public Usuario(int id, String apellido, String ciudad, String clave, String mail, String direccion, Long dni, String imagen, String latitud, String longitud, String nombre, String pais, int rol, Long telefono) {
        this.id = id;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.clave = clave;
        this.mail = mail;
        this.direccion = direccion;
        this.dni = dni;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.pais = pais;
        this.rol = rol;
        this.telefono = telefono;
    }

    public Bitmap getImagenFile() {
        return imagenFile;
    }

    public void setImagenFile(Bitmap imagenFile) {
        this.imagenFile = imagenFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
