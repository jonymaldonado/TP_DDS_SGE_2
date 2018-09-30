package ar.com.sge.comandos;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class ComandoEncender implements Comando{
	
	
	private String nombreComando;
	private DispositivoInteligente dispositivo;
	
	public ComandoEncender(String nombreComando, DispositivoInteligente dispositivo) {
		
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
	
	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

	

}
