/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Dispositivos;
import entity.Sensores;
import entity.Usuarios;
import entity.EstadosDispositivos;
import entity.UsuarioDispositivos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jean Pierre
 */
public class UsuarioDispositivosJpaController implements Serializable {

    public UsuarioDispositivosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioDispositivos usuarioDispositivos) {
        if (usuarioDispositivos.getEstadosDispositivosList() == null) {
            usuarioDispositivos.setEstadosDispositivosList(new ArrayList<EstadosDispositivos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dispositivos dispositivo = usuarioDispositivos.getDispositivo();
            if (dispositivo != null) {
                dispositivo = em.getReference(dispositivo.getClass(), dispositivo.getId());
                usuarioDispositivos.setDispositivo(dispositivo);
            }
            Sensores sensor = usuarioDispositivos.getSensor();
            if (sensor != null) {
                sensor = em.getReference(sensor.getClass(), sensor.getId());
                usuarioDispositivos.setSensor(sensor);
            }
            Usuarios usuario = usuarioDispositivos.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                usuarioDispositivos.setUsuario(usuario);
            }
            List<EstadosDispositivos> attachedEstadosDispositivosList = new ArrayList<EstadosDispositivos>();
            for (EstadosDispositivos estadosDispositivosListEstadosDispositivosToAttach : usuarioDispositivos.getEstadosDispositivosList()) {
                estadosDispositivosListEstadosDispositivosToAttach = em.getReference(estadosDispositivosListEstadosDispositivosToAttach.getClass(), estadosDispositivosListEstadosDispositivosToAttach.getId());
                attachedEstadosDispositivosList.add(estadosDispositivosListEstadosDispositivosToAttach);
            }
            usuarioDispositivos.setEstadosDispositivosList(attachedEstadosDispositivosList);
            em.persist(usuarioDispositivos);
            if (dispositivo != null) {
                dispositivo.getUsuarioDispositivosList().add(usuarioDispositivos);
                dispositivo = em.merge(dispositivo);
            }
            if (sensor != null) {
                sensor.getUsuarioDispositivosList().add(usuarioDispositivos);
                sensor = em.merge(sensor);
            }
            if (usuario != null) {
                usuario.getUsuarioDispositivosList().add(usuarioDispositivos);
                usuario = em.merge(usuario);
            }
            for (EstadosDispositivos estadosDispositivosListEstadosDispositivos : usuarioDispositivos.getEstadosDispositivosList()) {
                UsuarioDispositivos oldUsuarioDispositivoOfEstadosDispositivosListEstadosDispositivos = estadosDispositivosListEstadosDispositivos.getUsuarioDispositivo();
                estadosDispositivosListEstadosDispositivos.setUsuarioDispositivo(usuarioDispositivos);
                estadosDispositivosListEstadosDispositivos = em.merge(estadosDispositivosListEstadosDispositivos);
                if (oldUsuarioDispositivoOfEstadosDispositivosListEstadosDispositivos != null) {
                    oldUsuarioDispositivoOfEstadosDispositivosListEstadosDispositivos.getEstadosDispositivosList().remove(estadosDispositivosListEstadosDispositivos);
                    oldUsuarioDispositivoOfEstadosDispositivosListEstadosDispositivos = em.merge(oldUsuarioDispositivoOfEstadosDispositivosListEstadosDispositivos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioDispositivos usuarioDispositivos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioDispositivos persistentUsuarioDispositivos = em.find(UsuarioDispositivos.class, usuarioDispositivos.getId());
            Dispositivos dispositivoOld = persistentUsuarioDispositivos.getDispositivo();
            Dispositivos dispositivoNew = usuarioDispositivos.getDispositivo();
            Sensores sensorOld = persistentUsuarioDispositivos.getSensor();
            Sensores sensorNew = usuarioDispositivos.getSensor();
            Usuarios usuarioOld = persistentUsuarioDispositivos.getUsuario();
            Usuarios usuarioNew = usuarioDispositivos.getUsuario();
            List<EstadosDispositivos> estadosDispositivosListOld = persistentUsuarioDispositivos.getEstadosDispositivosList();
            List<EstadosDispositivos> estadosDispositivosListNew = usuarioDispositivos.getEstadosDispositivosList();
            List<String> illegalOrphanMessages = null;
            for (EstadosDispositivos estadosDispositivosListOldEstadosDispositivos : estadosDispositivosListOld) {
                if (!estadosDispositivosListNew.contains(estadosDispositivosListOldEstadosDispositivos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EstadosDispositivos " + estadosDispositivosListOldEstadosDispositivos + " since its usuarioDispositivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (dispositivoNew != null) {
                dispositivoNew = em.getReference(dispositivoNew.getClass(), dispositivoNew.getId());
                usuarioDispositivos.setDispositivo(dispositivoNew);
            }
            if (sensorNew != null) {
                sensorNew = em.getReference(sensorNew.getClass(), sensorNew.getId());
                usuarioDispositivos.setSensor(sensorNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                usuarioDispositivos.setUsuario(usuarioNew);
            }
            List<EstadosDispositivos> attachedEstadosDispositivosListNew = new ArrayList<EstadosDispositivos>();
            for (EstadosDispositivos estadosDispositivosListNewEstadosDispositivosToAttach : estadosDispositivosListNew) {
                estadosDispositivosListNewEstadosDispositivosToAttach = em.getReference(estadosDispositivosListNewEstadosDispositivosToAttach.getClass(), estadosDispositivosListNewEstadosDispositivosToAttach.getId());
                attachedEstadosDispositivosListNew.add(estadosDispositivosListNewEstadosDispositivosToAttach);
            }
            estadosDispositivosListNew = attachedEstadosDispositivosListNew;
            usuarioDispositivos.setEstadosDispositivosList(estadosDispositivosListNew);
            usuarioDispositivos = em.merge(usuarioDispositivos);
            if (dispositivoOld != null && !dispositivoOld.equals(dispositivoNew)) {
                dispositivoOld.getUsuarioDispositivosList().remove(usuarioDispositivos);
                dispositivoOld = em.merge(dispositivoOld);
            }
            if (dispositivoNew != null && !dispositivoNew.equals(dispositivoOld)) {
                dispositivoNew.getUsuarioDispositivosList().add(usuarioDispositivos);
                dispositivoNew = em.merge(dispositivoNew);
            }
            if (sensorOld != null && !sensorOld.equals(sensorNew)) {
                sensorOld.getUsuarioDispositivosList().remove(usuarioDispositivos);
                sensorOld = em.merge(sensorOld);
            }
            if (sensorNew != null && !sensorNew.equals(sensorOld)) {
                sensorNew.getUsuarioDispositivosList().add(usuarioDispositivos);
                sensorNew = em.merge(sensorNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getUsuarioDispositivosList().remove(usuarioDispositivos);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getUsuarioDispositivosList().add(usuarioDispositivos);
                usuarioNew = em.merge(usuarioNew);
            }
            for (EstadosDispositivos estadosDispositivosListNewEstadosDispositivos : estadosDispositivosListNew) {
                if (!estadosDispositivosListOld.contains(estadosDispositivosListNewEstadosDispositivos)) {
                    UsuarioDispositivos oldUsuarioDispositivoOfEstadosDispositivosListNewEstadosDispositivos = estadosDispositivosListNewEstadosDispositivos.getUsuarioDispositivo();
                    estadosDispositivosListNewEstadosDispositivos.setUsuarioDispositivo(usuarioDispositivos);
                    estadosDispositivosListNewEstadosDispositivos = em.merge(estadosDispositivosListNewEstadosDispositivos);
                    if (oldUsuarioDispositivoOfEstadosDispositivosListNewEstadosDispositivos != null && !oldUsuarioDispositivoOfEstadosDispositivosListNewEstadosDispositivos.equals(usuarioDispositivos)) {
                        oldUsuarioDispositivoOfEstadosDispositivosListNewEstadosDispositivos.getEstadosDispositivosList().remove(estadosDispositivosListNewEstadosDispositivos);
                        oldUsuarioDispositivoOfEstadosDispositivosListNewEstadosDispositivos = em.merge(oldUsuarioDispositivoOfEstadosDispositivosListNewEstadosDispositivos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarioDispositivos.getId();
                if (findUsuarioDispositivos(id) == null) {
                    throw new NonexistentEntityException("The usuarioDispositivos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioDispositivos usuarioDispositivos;
            try {
                usuarioDispositivos = em.getReference(UsuarioDispositivos.class, id);
                usuarioDispositivos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioDispositivos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EstadosDispositivos> estadosDispositivosListOrphanCheck = usuarioDispositivos.getEstadosDispositivosList();
            for (EstadosDispositivos estadosDispositivosListOrphanCheckEstadosDispositivos : estadosDispositivosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UsuarioDispositivos (" + usuarioDispositivos + ") cannot be destroyed since the EstadosDispositivos " + estadosDispositivosListOrphanCheckEstadosDispositivos + " in its estadosDispositivosList field has a non-nullable usuarioDispositivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Dispositivos dispositivo = usuarioDispositivos.getDispositivo();
            if (dispositivo != null) {
                dispositivo.getUsuarioDispositivosList().remove(usuarioDispositivos);
                dispositivo = em.merge(dispositivo);
            }
            Sensores sensor = usuarioDispositivos.getSensor();
            if (sensor != null) {
                sensor.getUsuarioDispositivosList().remove(usuarioDispositivos);
                sensor = em.merge(sensor);
            }
            Usuarios usuario = usuarioDispositivos.getUsuario();
            if (usuario != null) {
                usuario.getUsuarioDispositivosList().remove(usuarioDispositivos);
                usuario = em.merge(usuario);
            }
            em.remove(usuarioDispositivos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioDispositivos> findUsuarioDispositivosEntities() {
        return findUsuarioDispositivosEntities(true, -1, -1);
    }

    public List<UsuarioDispositivos> findUsuarioDispositivosEntities(int maxResults, int firstResult) {
        return findUsuarioDispositivosEntities(false, maxResults, firstResult);
    }

    private List<UsuarioDispositivos> findUsuarioDispositivosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioDispositivos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UsuarioDispositivos findUsuarioDispositivos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioDispositivos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioDispositivosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioDispositivos> rt = cq.from(UsuarioDispositivos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
