package com.brayan.miapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    public Curso curso;
    
    @Column(nullable = false)
    public int año;
    
    @Column(nullable = false)
    public int semestre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    public Estudiante estudiante;

    // Constructor por defecto requerido por JPA
    public Inscripcion() {}
    
    // Constructor
    public Inscripcion(Curso curso, int año, int semestre, Estudiante estudiante) {
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    // -------- Métodos --------
    @Override
    public String toString() {
        return "Inscripcion{" +
                "curso=" + (curso != null ? curso : "N/A") +
                ", año=" + año +
                ", semestre=" + semestre +
                ", estudiante=" + (estudiante != null ? estudiante : "N/A") +
                '}';
    }

    // Método de validación
    public boolean esValida() {
        return curso != null && estudiante != null && año > 0 && (semestre == 1 || semestre == 2);
    }
}
