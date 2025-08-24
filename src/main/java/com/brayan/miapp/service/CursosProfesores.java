package com.brayan.miapp.service;
import java.util.ArrayList;
import java.util.List;
import com.brayan.miapp.model.CursoProfesor;
import com.brayan.miapp.model.Profesor;
import com.brayan.miapp.model.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CursosProfesores implements Servicios {
    private List<CursoProfesor> listado = new ArrayList<>();
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public CursosProfesores() {
        // Inicializar JPA
        emf = Persistence.createEntityManagerFactory("universidad-pu");
        em = emf.createEntityManager();
        cargarDatos(); // Cargar datos al inicializar
    }

    public void inscribir(CursoProfesor cursoProfesor) {
        try {
            em.getTransaction().begin();
            em.persist(cursoProfesor);
            em.getTransaction().commit();
            listado.add(cursoProfesor);
            System.out.println("Curso-Profesor inscrito exitosamente: " + cursoProfesor.toString());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al inscribir curso-profesor: " + e.getMessage());
        }
    }

    public void eliminar(CursoProfesor cursoProfesor) {
        try {
            em.getTransaction().begin();
            CursoProfesor managedCursoProfesor = em.find(CursoProfesor.class, cursoProfesor.getId());
            if (managedCursoProfesor != null) {
                em.remove(managedCursoProfesor);
                em.getTransaction().commit();
                listado.remove(cursoProfesor);
                System.out.println("Curso-Profesor eliminado exitosamente");
            } else {
                System.out.println("Curso-Profesor no encontrado para eliminar");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar curso-profesor: " + e.getMessage());
        }
    }

    public void actualizar(CursoProfesor cursoProfesor) {
        try {
            em.getTransaction().begin();
            CursoProfesor managedCursoProfesor = em.find(CursoProfesor.class, cursoProfesor.getId());
            if (managedCursoProfesor != null) {
                // Actualizar campos
                managedCursoProfesor.setProfesor(cursoProfesor.getProfesor());
                managedCursoProfesor.setCurso(cursoProfesor.getCurso());
                managedCursoProfesor.setAño(cursoProfesor.getAño());
                managedCursoProfesor.setSemestre(cursoProfesor.getSemestre());
                em.merge(managedCursoProfesor);
                em.getTransaction().commit();
                
                // Actualizar en la lista local
                int index = listado.indexOf(cursoProfesor);
                if (index != -1) {
                    listado.set(index, managedCursoProfesor);
                }
                System.out.println("Curso-Profesor actualizado exitosamente");
            } else {
                System.out.println("Curso-Profesor no encontrado para actualizar");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar curso-profesor: " + e.getMessage());
        }
    }

    public void guardarInformacion(CursoProfesor cursoProfesor) {
        try {
            em.getTransaction().begin();
            
            // Verificar si la relación curso-profesor ya existe
            CursoProfesor existingCursoProfesor = em.find(CursoProfesor.class, cursoProfesor.getId());
            if (existingCursoProfesor != null) {
                // Actualizar relación existente
                em.merge(cursoProfesor);
                System.out.println("Información actualizada en BD para: " + cursoProfesor.toString());
            } else {
                // Guardar nueva relación
                em.persist(cursoProfesor);
                System.out.println("Nueva información guardada en BD para: " + cursoProfesor.toString());
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
            TypedQuery<CursoProfesor> query = em.createQuery("SELECT cp FROM CursoProfesor cp", CursoProfesor.class);
            listado = query.getResultList();
            System.out.println("Datos cargados desde BD. Total curso-profesores: " + listado.size());
        } catch (Exception e) {
            System.err.println("Error al cargar datos desde BD: " + e.getMessage());
            listado = new ArrayList<>(); // Inicializar lista vacía en caso de error
        }
    }
    
    // Métodos de búsqueda específicos
    public CursoProfesor buscarPorId(Long id) {
        try {
            return em.find(CursoProfesor.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar curso-profesor por ID: " + e.getMessage());
            return null;
        }
    }
    
    public List<CursoProfesor> buscarPorProfesor(Profesor profesor) {
        try {
            TypedQuery<CursoProfesor> query = em.createQuery(
                "SELECT cp FROM CursoProfesor cp WHERE cp.profesor = :profesor", CursoProfesor.class);
            query.setParameter("profesor", profesor);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar por profesor: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public List<CursoProfesor> buscarPorCurso(Curso curso) {
        try {
            TypedQuery<CursoProfesor> query = em.createQuery(
                "SELECT cp FROM CursoProfesor cp WHERE cp.curso = :curso", CursoProfesor.class);
            query.setParameter("curso", curso);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar por curso: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public List<CursoProfesor> buscarPorPeriodo(int año, int semestre) {
        try {
            TypedQuery<CursoProfesor> query = em.createQuery(
                "SELECT cp FROM CursoProfesor cp WHERE cp.año = :año AND cp.semestre = :semestre", 
                CursoProfesor.class);
            query.setParameter("año", año);
            query.setParameter("semestre", semestre);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar por período: " + e.getMessage());
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
        List<String> resultado = new ArrayList<>();
        for (CursoProfesor cp : listado) {
            resultado.add(cp.toString());
        }
        return resultado;
    }
}