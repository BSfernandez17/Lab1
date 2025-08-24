package com.brayan.miapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "curso_profesor")
public class CursoProfesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;
    
    @Column(nullable = false)
    private int año;
    
    @Column(nullable = false)
    private int semestre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    // Constructor por defecto requerido por JPA
    public CursoProfesor() {}
    
    // Constructor
    public CursoProfesor(Profesor profesor, int año, int semestre, Curso curso) {
        this.profesor = profesor;
        this.año = año;
        this.semestre = semestre;
        this.curso = curso;
    }

    // ---------- Getters y Setters ----------
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // ---------- Métodos ----------

    @Override
    public String toString() {
        return "CursoProfesor{profesor=" + profesor + ", año=" + año + ", semestre=" + semestre + ", curso=" + curso + "}";
    }
}
