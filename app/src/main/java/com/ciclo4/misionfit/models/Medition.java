package com.ciclo4.misionfit.models;

public class Medition {

    private String id;
    private String fecha_medicion;
    private Double medicion;
    private String correo_usuario;

    public Medition(String id, String fecha_medicion, Double medicion, String correo_usuario) {
        this.id = id;
        this.fecha_medicion = fecha_medicion;
        this.medicion = medicion;
        this.correo_usuario = correo_usuario;
    }

    public Medition() {
    }

    public Medition(String fecha_medicion, Double medicion, String correo_usuario) {
        this.fecha_medicion = fecha_medicion;
        this.medicion = medicion;
        this.correo_usuario = correo_usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha_medicion() {
        return fecha_medicion;
    }

    public void setFecha_medicion(String fecha_medicion) {
        this.fecha_medicion = fecha_medicion;
    }

    public Double getMedicion() {
        return medicion;
    }

    public void setMedicion(Double medicion) {
        this.medicion = medicion;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }
}
