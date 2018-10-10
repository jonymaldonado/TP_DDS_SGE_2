
package ar.com.sge.comandos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.sge.dispositivos.DispositivoInteligente;


//@Entity
//@DiscriminatorValue("adapterAhorro")

public class AdapterAhorroDeEnergia extends ComandoAhorroDeEnergia {
	
	//private DispositivoInteligente dispositivo;
	private String nombre;

	public AdapterAhorroDeEnergia(String nombreComando,
			DispositivoInteligente dispositivo) {
		super(nombreComando, dispositivo);
		//this.dispositivo=dispositivo;
	}
	
	@Override
	public void ejecutar(DispositivoInteligente dispositivo) {
		dispositivo.ahorroDeEnergia();
	}
	
	public String getNombre() {
		return nombre;
	}
}
