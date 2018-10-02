/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jean Pierre
 */
@Entity
@Table(name = "comandos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comandos.findAll", query = "SELECT c FROM Comandos c")
    , @NamedQuery(name = "Comandos.findById", query = "SELECT c FROM Comandos c WHERE c.id = :id")
    , @NamedQuery(name = "Comandos.findByDescripcion", query = "SELECT c FROM Comandos c WHERE c.descripcion = :descripcion")})
public class Comandos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comando")
    private List<ComandosAdaptadores> comandosAdaptadoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comando")
    private List<ActuadoresComandos> actuadoresComandosList;

    public Comandos() {
    }

    public Comandos(Integer id) {
        this.id = id;
    }

    public Comandos(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<ComandosAdaptadores> getComandosAdaptadoresList() {
        return comandosAdaptadoresList;
    }

    public void setComandosAdaptadoresList(List<ComandosAdaptadores> comandosAdaptadoresList) {
        this.comandosAdaptadoresList = comandosAdaptadoresList;
    }

    @XmlTransient
    public List<ActuadoresComandos> getActuadoresComandosList() {
        return actuadoresComandosList;
    }

    public void setActuadoresComandosList(List<ActuadoresComandos> actuadoresComandosList) {
        this.actuadoresComandosList = actuadoresComandosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comandos)) {
            return false;
        }
        Comandos other = (Comandos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Comandos[ id=" + id + " ]";
    }
    
}
