package com.example.alquilercoches.model;

public class User {

    //Atributos
    private String nombre;
    private String apellidos;
    private String email;
    private String clave;
    private String dni;
    private String rol;

    //Constructor
    public User(String nombre, String apellidos, String email, String clave,  String dni, String rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.clave = clave;
        this.dni = dni;
        this.rol = rol;
    }

    //Constructor vacío
    public User() {
    }

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    //toString
    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", clave='" + clave + '\'' +
                ", dni='" + dni + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}

