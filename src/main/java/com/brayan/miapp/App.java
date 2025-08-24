package com.brayan.miapp;

import com.brayan.miapp.model.*;
import com.brayan.miapp.service.InscripcionesPersonas;
import com.brayan.miapp.service.CursosProfesores;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * Aplicación principal del sistema universitario
 * Demuestra el uso de JPA con H2 Database
 */
public class App {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public static void main(String[] args) {
        System.out.println("🚀 === SISTEMA UNIVERSITARIO ===");
        System.out.println("Inicializando aplicación...\n");
        
        try {
            // Inicializar JPA
            inicializarJPA();
            
            // Crear datos de prueba
            crearDatosDePrueba();
            
            // Probar servicios
            probarServicios();
            
            // Mostrar reportes
            mostrarReportes();
            
        } catch (Exception e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar conexiones
            cerrarJPA();
            System.out.println("\n=== APLICACIÓN TERMINADA ===");
        }
    }
    
    private static void inicializarJPA() {
        System.out.println("📊 Inicializando base de datos...");
        emf = Persistence.createEntityManagerFactory("universidad-pu");
        em = emf.createEntityManager();
        System.out.println("✅ Base de datos inicializada correctamente\n");
    }
    
    private static void crearDatosDePrueba() {
        System.out.println("🏗️  CREANDO DATOS DE PRUEBA");
        System.out.println("================================");
        
        try {
            em.getTransaction().begin();
            
            // 1. Crear Personas (Decanos y base para profesores/estudiantes)
            System.out.println("👥 Creando personas...");
            Persona decanoIngenieria = new Persona(0, "Ana María", "García López", "ana.garcia@universidad.edu");
            Persona decanoMedicina = new Persona(0, "Carlos", "Rodríguez Pérez", "carlos.rodriguez@universidad.edu");
            Persona profesor1Base = new Persona(0, "María Elena", "Martínez Silva", "maria.martinez@universidad.edu");
            Persona profesor2Base = new Persona(0, "José Luis", "Hernández Cruz", "jose.hernandez@universidad.edu");
            Persona estudiante1Base = new Persona(0, "Laura", "González Morales", "laura.gonzalez@estudiante.edu");
            Persona estudiante2Base = new Persona(0, "Diego", "Vargas Ruiz", "diego.vargas@estudiante.edu");
            
            em.persist(decanoIngenieria);
            em.persist(decanoMedicina);
            em.persist(profesor1Base);
            em.persist(profesor2Base);
            em.persist(estudiante1Base);
            em.persist(estudiante2Base);
            
            // 2. Crear Facultades
            System.out.println("🏛️  Creando facultades...");
            Facultad facultadIngenieria = new Facultad(0.0, "Facultad de Ingeniería", decanoIngenieria);
            Facultad facultadMedicina = new Facultad(0.0, "Facultad de Medicina", decanoMedicina);
            
            em.persist(facultadIngenieria);
            em.persist(facultadMedicina);
            
            // 3. Crear Programas
            System.out.println("📚 Creando programas académicos...");
            Programa ingenieriaISistemas = new Programa(0, "Ingeniería de Sistemas", 10.0, new Date(), facultadIngenieria);
            Programa ingenieriaICivil = new Programa(0, "Ingeniería Civil", 10.0, new Date(), facultadIngenieria);
            Programa medicina = new Programa(0, "Medicina", 12.0, new Date(), facultadMedicina);
            
            em.persist(ingenieriaISistemas);
            em.persist(ingenieriaICivil);
            em.persist(medicina);
            
            // 4. Crear Cursos
            System.out.println("📖 Creando cursos...");
            Curso programacionI = new Curso(0, "Programación I", ingenieriaISistemas, true);
            Curso basesDatos = new Curso(0, "Bases de Datos", ingenieriaISistemas, true);
            Curso estructuras = new Curso(0, "Estructuras", ingenieriaICivil, true);
            Curso anatomia = new Curso(0, "Anatomía Humana", medicina, true);
            
            em.persist(programacionI);
            em.persist(basesDatos);
            em.persist(estructuras);
            em.persist(anatomia);
            
            // 5. Crear Profesores
            System.out.println("👨‍🏫 Creando profesores...");
            Profesor profesor1 = new Profesor(0.0, "María Elena", "Martínez Silva", "maria.profe@universidad.edu", "Tiempo Completo");
            Profesor profesor2 = new Profesor(0.0, "José Luis", "Hernández Cruz", "jose.profe@universidad.edu", "Cátedra");
            
            em.persist(profesor1);
            em.persist(profesor2);
            
            // 6. Crear Estudiantes
            System.out.println("👨‍🎓 Creando estudiantes...");
            Estudiante estudiante1 = new Estudiante(0.0, "Laura", "González Morales", "laura.est@estudiante.edu",
                    20241001.0, ingenieriaISistemas, true, 4.2);
            Estudiante estudiante2 = new Estudiante(0.0, "Diego", "Vargas Ruiz", "diego.est@estudiante.edu",
                    20241002.0, ingenieriaICivil, true, 3.8);
            
            em.persist(estudiante1);
            em.persist(estudiante2);
            
            // 7. Crear relaciones Curso-Profesor
            System.out.println("🔗 Creando asignaciones curso-profesor...");
            CursoProfesor cp1 = new CursoProfesor(profesor1, 2024, 1, programacionI);
            CursoProfesor cp2 = new CursoProfesor(profesor1, 2024, 1, basesDatos);
            CursoProfesor cp3 = new CursoProfesor(profesor2, 2024, 2, estructuras);
            
            em.persist(cp1);
            em.persist(cp2);
            em.persist(cp3);
            
            // 8. Crear Inscripciones de estudiantes
            System.out.println("📝 Creando inscripciones de estudiantes...");
            Inscripcion inscripcion1 = new Inscripcion(programacionI, 2024, 1, estudiante1);
            Inscripcion inscripcion2 = new Inscripcion(basesDatos, 2024, 1, estudiante1);
            Inscripcion inscripcion3 = new Inscripcion(estructuras, 2024, 2, estudiante2);
            
            em.persist(inscripcion1);
            em.persist(inscripcion2);
            em.persist(inscripcion3);
            
            em.getTransaction().commit();
            System.out.println("✅ Datos de prueba creados exitosamente\n");
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error creando datos de prueba: " + e.getMessage(), e);
        }
    }
    
    private static void probarServicios() {
        System.out.println("🔬 PROBANDO SERVICIOS");
        System.out.println("====================");
        
        // Probar servicio de Personas
        System.out.println("👥 Probando InscripcionesPersonas...");
        InscripcionesPersonas servicioPersonas = new InscripcionesPersonas();
        System.out.println("   Personas cargadas: " + servicioPersonas.cantidadActual());
        
        if (servicioPersonas.cantidadActual() > 0) {
            System.out.println("   Primera persona: " + servicioPersonas.imprimirPosicion(0));
        }
        
        // Buscar por email
        List<Persona> personasPorEmail = servicioPersonas.buscarPorEmail("ana.garcia@universidad.edu");
        System.out.println("   Búsqueda por email (ana.garcia@universidad.edu): " + personasPorEmail.size() + " resultados");
        
        servicioPersonas.cerrarConexiones();
        
        // Probar servicio de Cursos-Profesores
        System.out.println("\n👨‍🏫 Probando CursosProfesores...");
        CursosProfesores servicioCursosProfesores = new CursosProfesores();
        System.out.println("   Curso-Profesores cargados: " + servicioCursosProfesores.cantidadActual());
        
        if (servicioCursosProfesores.cantidadActual() > 0) {
            System.out.println("   Primera asignación: " + servicioCursosProfesores.imprimirPosicion(0));
        }
        
        // Buscar por período
        List<CursoProfesor> cursosPeriodo = servicioCursosProfesores.buscarPorPeriodo(2024, 1);
        System.out.println("   Cursos período 2024-1: " + cursosPeriodo.size() + " resultados");
        
        servicioCursosProfesores.cerrarConexiones();
        
        System.out.println("✅ Servicios probados exitosamente\n");
    }
    
    private static void mostrarReportes() {
        System.out.println("📊 REPORTES DEL SISTEMA");
        System.out.println("=======================");
        
        try {
            // Reporte de Personas
            System.out.println("👥 REPORTE DE PERSONAS:");
            List<Persona> todasPersonas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
            System.out.println("   Total de personas: " + todasPersonas.size());
            for (Persona p : todasPersonas) {
                System.out.println("   - " + p.toString());
            }
            
            // Reporte de Estudiantes
            System.out.println("\n👨‍🎓 REPORTE DE ESTUDIANTES:");
            List<Estudiante> todosEstudiantes = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
            System.out.println("   Total de estudiantes: " + todosEstudiantes.size());
            for (Estudiante e : todosEstudiantes) {
                System.out.println("   - Código: " + e.getCodigo() + ", Promedio: " + e.getPromedio());
            }
            
            // Reporte de Profesores
            System.out.println("\n👨‍🏫 REPORTE DE PROFESORES:");
            List<Profesor> todosProfesores = em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
            System.out.println("   Total de profesores: " + todosProfesores.size());
            for (Profesor p : todosProfesores) {
                System.out.println("   - " + p.getNombres() + " " + p.getApellidos() + " (" + p.getTipoContrato() + ")");
            }
            
            // Reporte de Facultades y Programas
            System.out.println("\n🏛️  REPORTE DE FACULTADES:");
            List<Facultad> todasFacultades = em.createQuery("SELECT f FROM Facultad f", Facultad.class).getResultList();
            System.out.println("   Total de facultades: " + todasFacultades.size());
            for (Facultad f : todasFacultades) {
                System.out.println("   - " + f.getNombre() + " (Decano: " + f.getDecano().getNombres() + ")");
            }
            
            // Reporte de Cursos
            System.out.println("\n📖 REPORTE DE CURSOS:");
            List<Curso> todosCursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
            System.out.println("   Total de cursos: " + todosCursos.size());
            for (Curso c : todosCursos) {
                System.out.println("   - " + c.getNombre() + " (Activo: " + c.isActivo() + ")");
            }
            
            // Reporte de Inscripciones
            System.out.println("\n📝 REPORTE DE INSCRIPCIONES:");
            List<Inscripcion> todasInscripciones = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class).getResultList();
            System.out.println("   Total de inscripciones: " + todasInscripciones.size());
            for (Inscripcion i : todasInscripciones) {
                System.out.println("   - " + i.toString());
            }
            
            // Reporte de Asignaciones Curso-Profesor
            System.out.println("\n🔗 REPORTE DE ASIGNACIONES CURSO-PROFESOR:");
            List<CursoProfesor> todasAsignaciones = em.createQuery("SELECT cp FROM CursoProfesor cp", CursoProfesor.class).getResultList();
            System.out.println("   Total de asignaciones: " + todasAsignaciones.size());
            for (CursoProfesor cp : todasAsignaciones) {
                System.out.println("   - " + cp.toString());
            }
            
        } catch (Exception e) {
            System.err.println("Error generando reportes: " + e.getMessage());
        }
        
        System.out.println("\n✅ Reportes generados exitosamente");
    }
    
    private static void cerrarJPA() {
        System.out.println("🔒 Cerrando conexiones...");
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        System.out.println("✅ Conexiones cerradas correctamente");
    }
}
