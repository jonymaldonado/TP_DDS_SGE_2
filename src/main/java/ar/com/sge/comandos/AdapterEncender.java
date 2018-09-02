package ar.com.sge.comandos;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class AdapterEncender extends ComandoEncender {
	
	private DispositivoInteligente dispositivo;
	
	public AdapterEncender(String nombreComando, DispositivoInteligente dispositivo) {
		super(nombreComando, dispositivo);
		this.dispositivo = dispositivo;
	}

	
	private String nombre;
	
	@Override
	public void ejecutar() {
		dispositivo.encender();
	}
	
	public String getNombre() {
		return nombre;
	}
}

