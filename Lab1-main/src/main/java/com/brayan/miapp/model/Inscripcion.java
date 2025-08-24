package com.brayan.miapp.model;

public class Inscripcion {
    public Curso curso;
    public int año;
    public int semestre;
    public Estudiante estudiante;

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
