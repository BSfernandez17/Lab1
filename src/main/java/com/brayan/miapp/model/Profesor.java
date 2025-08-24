package com.brayan.miapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profesores")
public class Profesor extends Persona {
    @Column(name = "tipo_contrato", nullable = false, length = 50)
    private String tipoContrato;

    // Constructor por defecto requerido por JPA
    public Profesor() {}

    public Profesor(Double id, String nombres, String apellidos, String email, String tipoContrato) {
        super(id, nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
    }

    // -------- Getters y Setters --------
    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return super.toString() + " Profesor{tipoContrato='" + tipoContrato + "'}";
    }
}