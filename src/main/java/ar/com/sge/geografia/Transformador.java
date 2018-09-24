package ar.com.sge.geografia;

import java.util.ArrayList;
//import java.util.Iterator;
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

import ar.com.sge.usuarios.Administrador;
import ar.com.sge.usuarios.Cliente;

@Entity
 
public class Transformador {
	@Id
	
	private int idtransformador; //por ahora va int, despues vemos se vera si queda asi o String
	@OneToOne
	private Coordenada posTransformador;
	@OneToOne
	private int idZonaCorrespondiente;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="transformador")	
	private List<Cliente> listaDeclientesConectados;
	//public Enum<Enum<E>> estado;  no si si vale la oena podner el atributo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idAdministrador")	
	private Administrador administrador;
	
	public Transformador(){
	}

	public Transformador(int id,double lat,double longitud,int unazona){
		this.setIdtransformador(id);
		this.setIdZonaCorrespondiente(unazona);
		this.setPosTransformador(new Coordenada(lat, longitud));

		this.listaDeclientesConectados = new ArrayList <>(); 
	}//constructor
	
	public float totalDeConsumoDelTransformadores() {
		float totalDeConsumoDeCadaCliente=0;
		for(Cliente unCliente : listaDeclientesConectados) {
			totalDeConsumoDeCadaCliente += unCliente.consumoDeEnergia();
		}//fin for
		return totalDeConsumoDeCadaCliente;
	}

	public int getIdtransformador() {
		return idtransformador;
	}
	public void setIdtransformador(int idtransformador) {
		this.idtransformador = idtransformador;
	}
	public Coordenada getPosTransformador() {
		return posTransformador;
	}
	public void setPosTransformador(Coordenada posTransformador) {
		this.posTransformador = posTransformador;
	}
	public int getIdZonaCorrespondiente() {
		return idZonaCorrespondiente;
	}
	public void setIdZonaCorrespondiente(int idZonaCorrespondiente) {
		this.idZonaCorrespondiente = idZonaCorrespondiente;
	}
	public List<Cliente> getListaDeclientesConectados() {
		return listaDeclientesConectados;
	}
	
	public void agregarCliente(Cliente unCliente) {
		this.getListaDeclientesConectados().add(unCliente);
	}

	
}