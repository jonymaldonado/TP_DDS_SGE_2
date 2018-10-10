package ar.com.sge.geografia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.sge.usuarios.Administrador;


@Entity
@Table(name ="Zona")
public class Zona {
	@Id
	//@GeneratedValue
	private int idZona;
	private String nombreDeLaZona;
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "id_coordenada")
	private Coordenada coordenada;
	private int radioEnMetros;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="zona")	
	private List<Transformador> listaDeTransformadores; 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idAdministrador")	
	private Administrador administrador;
	
/*	public Zona() {
		
	}*/
	public Zona() {
		this.listaDeTransformadores=new ArrayList<>();
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
		return coordenada;
	}

	public void setPosZonaCentral(Coordenada posZonaCentral) {
		this.coordenada = posZonaCentral;
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
		transformador.setZona(this);
	}
	
	
	
	
	
}// fin zona