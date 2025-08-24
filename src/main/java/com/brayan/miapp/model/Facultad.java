package com.brayan.miapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "facultades")
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decano_id", nullable = false)
    private Persona decano;

    // Constructor por defecto requerido por JPA
    public Facultad() {}

    public Facultad(Double id, String nombre, Persona decano) {
        this.id = id;
        this.nombre = nombre;
        this.decano = decano;
    }

    // -------- Getters y Setters --------
    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona getDecano() {
        return decano;
    }

    public void setDecano(Persona decano) {
        this.decano = decano;
    }

    @Override
    public String toString() {
        return "Facultad{id=" + id + ", nombre='" + nombre + "', decano=" + decano + "}";
    }
}
