package ar.com.sge.geografia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.sge.usuarios.Administrador;

@Entity
@Table(name = "zonas")
@XmlRootElement
public class Zona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "id")
    private int idZona;
	@Basic(optional = false)
    @Column(name = "nombre_zona")
    private String nombreDeLaZona;
    
    
	private Coordenada posZonaCentral;
	private int radioEnMetros;
	private List<Transformador> listaDeTransformadores; 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idAdministrador")	
	private Administrador administrador;
	
	public Zona() {
		//this.listaDeTransformadores=new ArrayList<>();
	}//constructor
	
	
	//}//fin consumoTotalDelaZona
	public float consumoTotalDeLaZona() {
		float consumoTotalEnLaZona = 0 ;
		for (Transformador unTransformador : listaDeTransformadores) {
			consumoTotalEnLaZona +=  unTransformador.totalDeConsumoDelTransformadores();
		}
		return consumoTotalEnLaZona;
	}

	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public String getNombreDeLaZona() {
		return nombreDeLaZona;
	}

	public void setNombreDeLaZona(String nombreDeLaZona) {
		this.nombreDeLaZona = nombreDeLaZona;
	}

	public Coordenada getPosZonaCentral() {
		return posZonaCentral;
	}

	public void setPosZonaCentral(Coordenada posZonaCentral) {
		this.posZonaCentral = posZonaCentral;
	}

	public int getRadioEnMetros() {
		return radioEnMetros;
	}

	public void setRadioEnMetros(int radioEnMetros) {
		this.radioEnMetros = radioEnMetros;
	}

	public List<Transformador> getListaDeTransformadores() {
		return listaDeTransformadores;
	}

	public void setListaDeTransformadores(List<Transformador> listaDeTransformadores) {
		this.listaDeTransformadores = listaDeTransformadores;
	}
	public void agregarTransformador(Transformador transformador) {
		listaDeTransformadores.add(transformador);
	}
	
	
	
	
	
}// fin zona