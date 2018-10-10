package ar.com.sge.comandos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.sge.dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue("encender")
public class ComandoEncender extends Comando{
	
	
	private String nombreComando;
	//private DispositivoInteligente dispositivo;
	
	public ComandoEncender(String nombreComando, DispositivoInteligente dispositivo) {
		
		this.nombreComando = nombreComando;
		//this.dispositivo = dispositivo;
	}

	public void ejecutar(DispositivoInteligente dispositivo) {
		
	}

	public String getNombre() {
		return nombreComando;
	}

	public void setNombrecomando(String nombrecomando) {
		this.nombreComando = nombrecomando;
	}

}
