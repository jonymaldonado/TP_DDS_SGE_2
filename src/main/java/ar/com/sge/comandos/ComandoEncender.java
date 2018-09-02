package ar.com.sge.comandos;

import ar.com.sge.dispositivos.DispositivoInteligente;

public abstract class ComandoEncender implements Comando{
	
	
	private String nombreComando;
	private DispositivoInteligente dispositivo;
	
	public ComandoEncender(String nombreComando, DispositivoInteligente dispositivo) {
		super();
		this.nombreComando = nombreComando;
		this.dispositivo = dispositivo;
	}

	public void ejecutar() {}

	public String getNombre() {
		return nombreComando;
	}

	public void setNombrecomando(String nombrecomando) {
		this.nombreComando = nombrecomando;
	}

}
