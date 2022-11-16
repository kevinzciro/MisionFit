package com.ciclo4.misionfit.models;

public class Medition {

    private int id;
    private String date;
    private Double medition;

    public Medition(String name, Double medition) {
        this.date = name;
        this.medition = medition;
    }

    public Medition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMedition() {
        return medition;
    }

    public void setMedition(Double medition) {
        this.medition = medition;
    }
}
