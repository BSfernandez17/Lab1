package com.brayan.miapp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "programas")
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Mejor usar Long en vez de double
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "duracion_semestres")
    private Double duracion;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    private Date registro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultad_id", nullable = false)
    private Facultad facultad;

    // Constructor por defecto requerido por JPA
    public Programa() {}

    public Programa(Long id, String nombre, Double duracion, Date registro, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    // -------- Getters y Setters --------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    // -------- Métodos --------
    @Override
    public String toString() {
        return "Programa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", registro=" + registro +
                ", facultad=" + facultad +
                '}';
    }
}
