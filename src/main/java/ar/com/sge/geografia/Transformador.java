package ar.com.sge.geografia;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

import ar.com.sge.usuarios.Cliente;

public class Transformador {
	private int idtransformador; //por ahora va int, despues vemos se vera si queda asi o String
	private Coordenada posTransformador;
	private int idZonaCorrespondiente;
	private List <Cliente> listaDeclientesConectados;
	//public Enum<Enum<E>> estado;  no si si vale la oena podner el atributo

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
		for (Cliente unCliente : listaDeclientesConectados) {
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