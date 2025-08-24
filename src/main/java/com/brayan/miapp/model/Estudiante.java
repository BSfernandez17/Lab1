package com.brayan.miapp.model;

import com.brayan.miapp.model.Programa;

public class Estudiante extends Persona {
    private Double codigo;
    private Programa programa;
    private Boolean activo;
    private Double promedio;

    public Estudiante(Double id, String nombres, String apellidos, String email,
        Double codigo, Programa programa, Boolean activo, Double promedio) {
        super(id, nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return super.toString() + " Estudiante{codigo=" + codigo + ", programa=" programa+", activo=" + activo + ", promedio=" + promedio + "}";
    }
}
