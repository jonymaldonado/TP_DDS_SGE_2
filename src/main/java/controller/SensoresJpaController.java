/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Sensores;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Controllers.exceptions.NonexistentEntityException;
import entity.UsuarioDispositivos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SensoresJpaController implements Serializable {

    public SensoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Sensores sensores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sensores);
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
            Sensores sensores;
            try {
                sensores = em.getReference(Sensores.class, id);
                sensores.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El id no existe", enfe);
            }
            em.remove(sensores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Sensores findSensores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sensores.class, id);
        } finally {
            em.close();
        }
    }

    
}
