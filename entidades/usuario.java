package com.gosama.entidades;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
*

@author robertosh21
*/
public class Usuario {
@id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String nombre;
private String apellidos;
private String telefono;
private String correo;
private String password;

//opcionales
private String direccion;
private String codigoPostal;

// Getters y Setters
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

public String getApellidos() {
    return apellidos;
}

public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
}

public String getTelefono() {
    return telefono;
}

public void setTelefono(String numeroTelefono) {
    this.telefono = numeroTelefono;
}

public String getCorreo() {
    return correo;
}

public void setCorreo(String correo) {
    this.correo = correo;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getDireccion() {
    return direccion;
}

public void setDireccion(String direccion) {
    this.direccion = direccion;
}

public String getCodigoPostal() {
    return codigoPostal;
}

public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
}
}
