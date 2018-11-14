package ar.com.sge.comandos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.sge.dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue("ahorro")
public class ComandoAhorroDeEnergia extends Comando {
	//private AdapterAhorroDeEnergia adaptador;
	private String nombreComando;
	//private DispositivoInteligente dispositivo;
	
	public ComandoAhorroDeEnergia() {
		
	}
	
	public ComandoAhorroDeEnergia(String nombreComando, DispositivoInteligente dispositivo) {
		
		this.nombreComando = nombreComando;
		//this.dispositivo = dispositivo;
	}

	public void ejecutar(DispositivoInteligente dispositivo) {
		dispositivo.ahorroDeEnergia();
		
	}

	/*public AdapterAhorroDeEnergia getAdaptador() {
		return adaptador;
	}

	public void setAdaptador(AdapterAhorroDeEnergia adaptador) {
		this.adaptador = adaptador;
	}*/

	public String getNombre() {
		return nombreComando;
	}

	public void setNombreComando(String nombrecomando) {
		this.nombreComando = nombrecomando;
	}
	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
}
