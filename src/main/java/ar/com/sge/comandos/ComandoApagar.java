package ar.com.sge.comandos;

import ar.com.sge.dispositivos.DispositivoInteligente;

public abstract class ComandoApagar implements Comando {

	private String nombreComando;
	private DispositivoInteligente dispositivo;
	
	
	
	public ComandoApagar(String nombreComando, DispositivoInteligente dispositivo) {
		super();
		this.nombreComando = nombreComando;
		this.dispositivo = dispositivo;
	}

	public void ejecutar() {}



	public String getNombre() {
		return nombreComando;
	}

	public void setNombreComando(String nombrecomando) {
		this.nombreComando = nombrecomando;
	}
}
