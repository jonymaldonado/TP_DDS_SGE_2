/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Actuadores;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Controllers.exceptions.NonexistentEntityException;
import entity.Reglas;
import java.util.ArrayList;
import java.util.List;
import entity.ActuadoresComandos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ActuadoresJpaController implements Serializable {

    public ActuadoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Actuadores actuadores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(actuadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actuadores actuadores;
            try {
                actuadores = em.getReference(Actuadores.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El id no existe", enfe);
            }
            em.remove(actuadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Actuadores findActuadores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actuadores.class, id);
        } finally {
            em.close();
        }
    }
    
}
