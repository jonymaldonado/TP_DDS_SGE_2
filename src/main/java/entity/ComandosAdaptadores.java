/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean Pierre
 */
@Entity
@Table(name = "comandos_adaptadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComandosAdaptadores.findAll", query = "SELECT c FROM ComandosAdaptadores c")
    , @NamedQuery(name = "ComandosAdaptadores.findById", query = "SELECT c FROM ComandosAdaptadores c WHERE c.id = :id")})
public class ComandosAdaptadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "adaptador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Adaptadores adaptador;
    @JoinColumn(name = "comando", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comandos comando;

    public ComandosAdaptadores() {
    }

    public ComandosAdaptadores(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adaptadores getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(Adaptadores adaptador) {
        this.adaptador = adaptador;
    }

    public Comandos getComando() {
        return comando;
    }

    public void setComando(Comandos comando) {
        this.comando = comando;
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
        if (!(object instanceof ComandosAdaptadores)) {
            return false;
        }
        ComandosAdaptadores other = (ComandosAdaptadores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ComandosAdaptadores[ id=" + id + " ]";
    }
    
}
