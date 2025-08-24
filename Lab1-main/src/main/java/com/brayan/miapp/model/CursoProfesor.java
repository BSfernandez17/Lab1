package com.brayan.miapp.model;

public class CursoProfesor {
    private Profesor profesor;
    private int año;
    private int semestre;
    private Curso curso;

    // Constructor
    public CursoProfesor(Profesor profesor, int año, int semestre, Curso curso) {
        this.profesor = profesor;
        this.año = año;
        this.semestre = semestre;
        this.curso = curso;
    }

    // ---------- Métodos ----------
    @Override
    public String toString() {
        return "CursoProfesor{" +
                "profesor=" + (profesor != null ? profesor.toString() : "N/A") +
                ", año=" + año +
                ", semestre=" + semestre +
                ", curso=" + (curso != null ? curso.toString() : "N/A") +
                '}';
    }
}
