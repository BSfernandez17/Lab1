package com.brayan.miapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
    
    @Column(nullable = false)
    private int año;
    
    @Column(nullable = false)
    private int semestre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    // Constructor por defecto requerido por JPA
    public Inscripcion() {}
    
    // Constructor
    public Inscripcion(Curso curso, int año, int semestre, Estudiante estudiante) {
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    // -------- Getters y Setters --------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
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
