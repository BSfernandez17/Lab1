package com.brayan.miapp.service;

import java.util.ArrayList;
import java.util.List;
import com.brayan.miapp.model.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class InscripcionesPersonas implements Servicios {
    private List<Persona> listado = new ArrayList<>();
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public InscripcionesPersonas() {
        // Inicializar JPA
        emf = Persistence.createEntityManagerFactory("universidad-pu");
        em = emf.createEntityManager();
        cargarDatos(); // Cargar datos al inicializar
    }

    public void inscribir(Persona persona) {
        try {
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
            listado.add(persona);
            System.out.println("Persona inscrita exitosamente: " + persona.toString());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al inscribir persona: " + e.getMessage());
        }
    }

    public void eliminar(Persona persona) {
        try {
            em.getTransaction().begin();
            Persona managedPersona = em.find(Persona.class, persona.getId());
            if (managedPersona != null) {
                em.remove(managedPersona);
                em.getTransaction().commit();
                listado.remove(persona);
                System.out.println("Persona eliminada exitosamente");
            } else {
                System.out.println("Persona no encontrada para eliminar");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar persona: " + e.getMessage());
        }
    }

    public void actualizar(Persona persona) {
        try {
            em.getTransaction().begin();
            Persona managedPersona = em.find(Persona.class, persona.getId());
            if (managedPersona != null) {
                // Actualizar campos
                managedPersona.setNombres(persona.getNombres());
                managedPersona.setApellidos(persona.getApellidos());
                managedPersona.setEmail(persona.getEmail());
                em.merge(managedPersona);
                em.getTransaction().commit();
                
                // Actualizar en la lista local
                int index = listado.indexOf(persona);
                if (index != -1) {
                    listado.set(index, managedPersona);
                }
                System.out.println("Persona actualizada exitosamente");
            } else {
                System.out.println("Persona no encontrada para actualizar");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar persona: " + e.getMessage());
        }
    }

    public void guardarInformacion(Persona persona) {
        try {
            em.getTransaction().begin();
            
            // Verificar si la persona ya existe
            Persona existingPersona = em.find(Persona.class, persona.getId());
            if (existingPersona != null) {
                // Actualizar persona existente
                em.merge(persona);
                System.out.println("Información actualizada en BD para: " + persona.toString());
            } else {
                // Guardar nueva persona
                em.persist(persona);
                System.out.println("Nueva información guardada en BD para: " + persona.toString());
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar información: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        try {
            TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
            listado = query.getResultList();
            System.out.println("Datos cargados desde BD. Total personas: " + listado.size());
        } catch (Exception e) {
            System.err.println("Error al cargar datos desde BD: " + e.getMessage());
            listado = new ArrayList<>(); // Inicializar lista vacía en caso de error
        }
    }
    
    public Persona buscarPorId(double id) {
        try {
            return em.find(Persona.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar persona por ID: " + e.getMessage());
            return null;
        }
    }
    
    public List<Persona> buscarPorEmail(String email) {
        try {
            TypedQuery<Persona> query = em.createQuery(
                "SELECT p FROM Persona p WHERE p.email = :email", Persona.class);
            query.setParameter("email", email);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar persona por email: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public List<Persona> buscarPorNombre(String nombre) {
        try {
            TypedQuery<Persona> query = em.createQuery(
                "SELECT p FROM Persona p WHERE p.nombres LIKE :nombre OR p.apellidos LIKE :nombre", 
                Persona.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar persona por nombre: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public void cerrarConexiones() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Override
    public String imprimirPosicion(int posicion) {
        if (posicion >= 0 && posicion < listado.size()) {
            return listado.get(posicion).toString();
        } else {
            return "Posición inválida. Total de elementos: " + listado.size();
        }
    }

    @Override
    public Integer cantidadActual() {
        return listado.size();
    }

    @Override
    public List<String> imprimirListado() {
        return listado.stream().map(Object::toString).toList();
    }
}