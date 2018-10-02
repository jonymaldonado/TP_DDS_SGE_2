/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Controllers.exceptions.NonexistentEntityException;
import entity.Actuadores;
import entity.Reglas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ReglasJpaController implements Serializable {

    public ReglasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Reglas reglas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reglas);
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
            Reglas reglas;
            try {
                reglas = em.getReference(Reglas.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El id no existe", enfe);
            }
            em.remove(reglas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Reglas findReglas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reglas.class, id);
        } finally {
            em.close();
        }
    }

    
}
