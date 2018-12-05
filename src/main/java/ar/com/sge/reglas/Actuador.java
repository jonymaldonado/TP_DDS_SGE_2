package ar.com.sge.reglas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.sge.comandos.Comando;
import ar.com.sge.comandos.ComandoAhorroDeEnergia;
import ar.com.sge.comandos.ComandoApagar;
import ar.com.sge.comandos.ComandoEncender;
import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.mqtt.PublisherMQTT;


@Entity
@Table(name ="actuador")
public class Actuador {
	@Id
	@GeneratedValue
	private int id_Actuador;
	@OneToOne
	private DispositivoInteligente inteligente;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="id_actuador")
	private List<Comando> listacomandos;
	@Transient
	private PublisherMQTT publicador;
	
	/*
	public Actuador() {
		listacomandos= new ArrayList<>();
	}*/

	public Actuador() {
		listacomandos= new ArrayList<>();
		this.addcomando(new ComandoApagar());
		this.addcomando(new ComandoEncender());
		this.addcomando(new ComandoAhorroDeEnergia());
		this.publicador = new PublisherMQTT();
	}
	
	public void ejecutarAccion(String accion) {
		Comando comandobuscado;
		List<Comando> listafiltrada = new ArrayList<Comando>();
		listafiltrada = listacomandos.stream().filter(a -> (a.getNombre().equalsIgnoreCase(accion))).collect(Collectors.toList());
		comandobuscado = listafiltrada.get(0);
		comandobuscado.ejecutar(inteligente, publicador);
	}

	public List<Comando> getListacomandos() {
		return listacomandos;
	}
	
	public DispositivoInteligente getInteligente() {
		return inteligente;
	}

	public void setInteligente(DispositivoInteligente inteligente) {
		this.inteligente = inteligente;
	}

	public void setListacomandos(List<Comando> listacomandos) {
		this.listacomandos = listacomandos;
	}

	public void addcomando(Comando comandonuevo) {
		listacomandos.add(comandonuevo);
	}
	
	public PublisherMQTT getPublicador() {
		return publicador;
	}

	public void setPublicador(PublisherMQTT publicador) {
		this.publicador = publicador;
	}

}