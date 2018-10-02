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
@Table(name = "dispositivos")
public class Dispositivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre_dispositivo")
    private String nombreDispositivo;
    @Basic(optional = false)
    @Column(name = "inteligente")
    private String inteligente;
    @Basic(optional = false)
    @Column(name = "bajo_consumo")
    private String bajoConsumo;
    @Basic(optional = false)
    @Column(name = "consumo_kwh")
    private float consumoKwh;
    @Column(name = "consumo_min")
    private Integer consumoMin;
    @Column(name = "consumo_max")
    private Integer consumoMax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dispositivo")
    private List<UsuarioDispositivos> usuarioDispositivosList;

    public Dispositivos() {
    }

    public Dispositivos(Integer id) {
        this.id = id;
    }

    public Dispositivos(Integer id, String nombreDispositivo, String inteligente, String bajoConsumo, float consumoKwh) {
        this.id = id;
        this.nombreDispositivo = nombreDispositivo;
        this.inteligente = inteligente;
        this.bajoConsumo = bajoConsumo;
        this.consumoKwh = consumoKwh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDispositivo() {
        return nombreDispositivo;
    }

    public void setNombreDispositivo(String nombreDispositivo) {
        this.nombreDispositivo = nombreDispositivo;
    }

    public String getInteligente() {
        return inteligente;
    }

    public void setInteligente(String inteligente) {
        this.inteligente = inteligente;
    }

    public String getBajoConsumo() {
        return bajoConsumo;
    }

    public void setBajoConsumo(String bajoConsumo) {
        this.bajoConsumo = bajoConsumo;
    }

    public float getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(float consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public Integer getConsumoMin() {
        return consumoMin;
    }

    public void setConsumoMin(Integer consumoMin) {
        this.consumoMin = consumoMin;
    }

    public Integer getConsumoMax() {
        return consumoMax;
    }

    public void setConsumoMax(Integer consumoMax) {
        this.consumoMax = consumoMax;
    }

    @XmlTransient
    public List<UsuarioDispositivos> getUsuarioDispositivosList() {
        return usuarioDispositivosList;
    }

    public void setUsuarioDispositivosList(List<UsuarioDispositivos> usuarioDispositivosList) {
        this.usuarioDispositivosList = usuarioDispositivosList;
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
        if (!(object instanceof Dispositivos)) {
            return false;
        }
        Dispositivos other = (Dispositivos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dispositivos[ id=" + id + " ]";
    }
    
}
