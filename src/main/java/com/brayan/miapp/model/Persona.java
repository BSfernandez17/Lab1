package com.brayan.miapp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;
    
    @Column(nullable = false, length = 50)
    private String nombres;
    
    @Column(nullable = false, length = 50)
    private String apellidos;
    
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    // Constructor por defecto requerido por JPA
    public Persona() {}
    
    public Persona(double id, String nombres, String apellidos, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }

    // -------- Getters y Setters --------
    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{id=" + id + ", nombres='" + nombres + "', apellidos='" + apellidos + "', email='" + email + "'}";
    }
}
