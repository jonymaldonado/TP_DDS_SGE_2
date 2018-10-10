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
import javax.persistence.Table;

import ar.com.sge.usuarios.Administrador;
import ar.com.sge.usuarios.Cliente;


@Entity
@Table(name ="Transformador")
 
public class Transformador {
	@Id
	@GeneratedValue
	private int idtransformador; //por ahora va int, despues vemos se vera si queda asi o String
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "id_coordenada")
	private Coordenada coordenada;

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="transformador")	
	private List<Cliente> listaDeclientesConectados;
	//public Enum<Enum<E>> estado;  no si si vale la oena podner el atributo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idAdministrador")	
	private Administrador administrador;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_Zona")
	private Zona zona;
	
	
	public Transformador(){
	}

	public Transformador(double lat,double longitud,Zona unazona){
		//this.setIdtransformador(id);
		this.setZona(unazona);
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
		return coordenada;
	}
	public void setPosTransformador(Coordenada posTransformador) {
		this.coordenada = posTransformador;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona1) {
		this.zona = zona1;
	}
	public List<Cliente> getListaDeclientesConectados() {
		return listaDeclientesConectados;
	}
	
	public void agregarCliente(Cliente unCliente) {
		this.getListaDeclientesConectados().add(unCliente);
		unCliente.setTransformador(this);
	}

	
}