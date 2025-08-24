package com.brayan.miapp.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "programas")
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    
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

    public Programa(double id, String nombre, Double duracion, Date registro, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

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
