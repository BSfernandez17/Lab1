package com.brayan.miapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante extends Persona {
    @Column(unique = true, nullable = false)
    private Double codigo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_id")
    private Programa programa;
    
    @Column(nullable = false)
    private Boolean activo;
    
    @Column(name = "promedio_academico")
    private Double promedio;

    // Constructor por defecto requerido por JPA
    public Estudiante() {}

    public Estudiante(Double id, String nombres, String apellidos, String email,
        Double codigo, Programa programa, Boolean activo, Double promedio) {
        super(id, nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    // -------- Getters y Setters --------
    public Double getCodigo() {
        return codigo;
    }

    public void setCodigo(Double codigo) {
        this.codigo = codigo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return super.toString() + " Estudiante{codigo=" + codigo + ", programa=" + programa+", activo=" + activo + ", promedio=" + promedio + "}";
    }
}
