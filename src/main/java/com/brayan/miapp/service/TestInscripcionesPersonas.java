package com.brayan.miapp.service;

import java.util.List;
import com.brayan.miapp.model.Persona;
import com.brayan.miapp.model.Estudiante;
import com.brayan.miapp.model.Profesor;

public class TestInscripcionesPersonas {
    public static void main(String[] args) {
        InscripcionesPersonas servicio = new InscripcionesPersonas();
        
        try {
            // Crear algunas personas de prueba
            Persona persona1 = new Persona(1, "Juan", "Pérez", "juan.perez@email.com");
            Persona persona2 = new Persona(2, "María", "García", "maria.garcia@email.com");
            Estudiante estudiante1 = new Estudiante(3.0, "Carlos", "López", "carlos.lopez@email.com",
                    20231001.0, null, true, 4.2);
            Profesor profesor1 = new Profesor(4.0, "Ana", "Martínez", "ana.martinez@email.com", "Tiempo Completo");
            
            System.out.println("=== PRUEBA DE INSCRIPCIONES ===");
            
            // Inscribir personas
            System.out.println("\n1. Inscribiendo personas...");
            servicio.inscribir(persona1);
            servicio.inscribir(persona2);
            servicio.inscribir(estudiante1);
            servicio.inscribir(profesor1);
            
            // Mostrar cantidad actual
            System.out.println("\n2. Cantidad actual de personas: " + servicio.cantidadActual());
            
            // Mostrar listado completo
            System.out.println("\n3. Listado completo:");
            for (String persona : servicio.imprimirListado()) {
                System.out.println("   " + persona);
            }
            
            // Mostrar persona en posición específica
            System.out.println("\n4. Persona en posición 1:");
            System.out.println("   " + servicio.imprimirPosicion(1));
            
            // Buscar por ID
            System.out.println("\n5. Buscar persona por ID (2):");
            Persona encontrada = servicio.buscarPorId(2);
            if (encontrada != null) {
                System.out.println("   Encontrada: " + encontrada.toString());
            }
            
            // Buscar por email
            System.out.println("\n6. Buscar persona por email (juan.perez@email.com):");
            List<Persona> personasPorEmail = servicio.buscarPorEmail("juan.perez@email.com");
            for (Persona p : personasPorEmail) {
                System.out.println("   " + p.toString());
            }
            
            // Buscar por nombre
            System.out.println("\n7. Buscar personas por nombre (María):");
            List<Persona> personasPorNombre = servicio.buscarPorNombre("María");
            for (Persona p : personasPorNombre) {
                System.out.println("   " + p.toString());
            }
            
            // Actualizar una persona
            System.out.println("\n8. Actualizando email de persona1...");
            persona1.setEmail("juan.perez.nuevo@email.com");
            servicio.actualizar(persona1);
            
            // Eliminar una persona
            System.out.println("\n9. Eliminando persona2...");
            servicio.eliminar(persona2);
            
            // Mostrar listado final
            System.out.println("\n10. Listado final:");
            System.out.println("    Cantidad: " + servicio.cantidadActual());
            for (String persona : servicio.imprimirListado()) {
                System.out.println("    " + persona);
            }
            
        } catch (Exception e) {
            System.err.println("Error durante las pruebas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar conexiones
            servicio.cerrarConexiones();
            System.out.println("\n=== PRUEBAS COMPLETADAS ===");
        }
    }
}
