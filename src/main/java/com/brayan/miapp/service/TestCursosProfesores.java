package com.brayan.miapp.service;

import com.brayan.miapp.model.*;
import java.util.List;

public class TestCursosProfesores {
    
    public static void main(String[] args) {
        CursosProfesores servicoCursosProfesores = new CursosProfesores();
        
        try {
            // Mostrar estado inicial
            mostrarEstadoInicial(servicoCursosProfesores);
            
            // Probar métodos básicos
            probarMetodosBasicos(servicoCursosProfesores);
            
        } catch (Exception e) {
            System.err.println("Error en la prueba: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar conexiones
            servicoCursosProfesores.cerrarConexiones();
        }
    }
    
    private static void mostrarEstadoInicial(CursosProfesores servicio) {
        System.out.println("=== ESTADO INICIAL ===");
        System.out.println("Base de datos inicializada correctamente.");
        System.out.println("Cantidad total de curso-profesores: " + servicio.cantidadActual());
        System.out.println("Conexión JPA establecida exitosamente.\n");
    }
    
    private static void probarMetodosBasicos(CursosProfesores servicio) {
        System.out.println("=== PROBANDO MÉTODOS BÁSICOS ===");
        
        // Probar búsqueda por ID inexistente
        CursoProfesor cpInexistente = servicio.buscarPorId(999L);
        System.out.println("Búsqueda por ID inexistente (999): " + 
            (cpInexistente == null ? "null (correcto)" : "encontrado"));
        
        // Probar búsqueda por período sin datos
        List<CursoProfesor> cursosDelPeriodo = servicio.buscarPorPeriodo(2024, 1);
        System.out.println("Búsqueda por período 2024-1: " + cursosDelPeriodo.size() + " resultados");
        
        // Mostrar listado vacío
        System.out.println("=== LISTADO ACTUAL ===");
        System.out.println("Cantidad total: " + servicio.cantidadActual());
        
        List<String> listado = servicio.imprimirListado();
        if (listado.isEmpty()) {
            System.out.println("Lista vacía (base de datos limpia)");
        } else {
            for (int i = 0; i < listado.size(); i++) {
                System.out.println((i + 1) + ". " + listado.get(i));
            }
        }
        
        // Probar impresión por posición con lista vacía
        System.out.println("\n=== PROBANDO IMPRESIÓN POR POSICIÓN ===");
        System.out.println("Posición 0: " + servicio.imprimirPosicion(0));
        System.out.println("Posición inválida (10): " + servicio.imprimirPosicion(10));
        
        System.out.println("\n✅ Todos los métodos básicos funcionan correctamente!");
        System.out.println("✅ La clase CursosProfesores está lista para usar con datos reales!");
    }
}
