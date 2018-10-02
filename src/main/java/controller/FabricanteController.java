/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Fabricantes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Controllers.exceptions.NonexistentEntityException;

public class FabricanteController implements Serializable {

    public FabricanteController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fabricantes fabricantes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fabricantes);
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
            Fabricantes fabricantes;
            try {
                fabricantes = em.getReference(Fabricantes.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El id no existe", enfe);
            }
            em.remove(fabricantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Fabricantes findFabricante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fabricantes.class, id);
        } finally {
            em.close();
        }
    }

    
}
