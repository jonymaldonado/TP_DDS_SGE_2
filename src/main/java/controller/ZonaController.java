
package controller;

import java.io.Serializable;

import ar.com.sge.geografia.Zona;
import controllers.exceptions.NonexistentEntityException;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

public class ZonaController implements Serializable {

    public ZonaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Zona zona) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(zona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void eliminar(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zona zona;
            try {
                zona = em.getReference(Zona.class, id);
            }  catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actuadoresComandos wit no longer exists.", enfe);
            }
            em.remove(zona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Zona findZonas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zona.class, id);
        } finally {
            em.close();
        }
    }
    
}
