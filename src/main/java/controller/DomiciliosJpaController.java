/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Domicilios;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Controllers.exceptions.NonexistentEntityException;
import entity.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DomiciliosJpaController implements Serializable {

    public DomiciliosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Domicilios domicilios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(domicilios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public void eliminar(Integer id) throws NonexistentEntityException{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domicilios domicilios;
            try {
                domicilios = em.getReference(Domicilios.class, id);
                domicilios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El id no existe", enfe);
            }
            em.remove(domicilios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Domicilios findDomicilios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Domicilios.class, id);
        } finally {
            em.close();
        }
    }

    
}
