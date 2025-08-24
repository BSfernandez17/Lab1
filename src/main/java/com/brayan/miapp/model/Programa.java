package com.brayan.miapp.model;

import java.util.Date;

public class Programa {
    private double id;
    private String nombre;
    private Double duracion;
    private Date registro;
    private Facultad facultad;

    public Programa(double id, String nombre, Double duracion, Date registro, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }
    
}
