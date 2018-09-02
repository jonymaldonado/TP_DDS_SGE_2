package ar.com.sge.comandos;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class AdapterApagar extends ComandoApagar {
	
	private String nombre;
	private DispositivoInteligente dispositivo;
	
	public AdapterApagar(String nombreComando, DispositivoInteligente dispositivo) {
		super(nombreComando, dispositivo);
		this.dispositivo=dispositivo;
	}
	
	@Override
	public void ejecutar() {
		dispositivo.apagar();
	}
	
	public String getNombre() {
		return nombre;
	}
}
