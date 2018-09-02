
package ar.com.sge.comandos;

import ar.com.sge.dispositivos.DispositivoInteligente;

public class AdapterAhorroDeEnergia extends ComandoAhorroDeEnergia {
	
	private DispositivoInteligente dispositivo;
	private String nombre;

	public AdapterAhorroDeEnergia(String nombreComando,
			DispositivoInteligente dispositivo) {
		super(nombreComando, dispositivo);
		this.dispositivo=dispositivo;
	}
	
	@Override
	public void ejecutar() {
		dispositivo.ahorroDeEnergia();
	}
	
	public String getNombre() {
		return nombre;
	}
}
