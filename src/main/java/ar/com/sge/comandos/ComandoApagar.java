package ar.com.sge.comandos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.sge.dispositivos.DispositivoInteligente;


@Entity
@DiscriminatorValue("apagar")
public class ComandoApagar extends Comando {

	private String nombreComando;
	//private DispositivoInteligente dispositivo;
	
	
	
	public ComandoApagar(String nombreComando, DispositivoInteligente dispositivo) {
		
		this.nombreComando = nombreComando;
		//this.dispositivo = dispositivo;
	}

	public ComandoApagar() {
		
	}
	public void ejecutar(DispositivoInteligente dispositivo) {
		dispositivo.apagar();
	}



	public String getNombre() {
		return nombreComando;
	}

	public void setNombreComando(String nombrecomando) {
		this.nombreComando = nombrecomando;
	}
}
