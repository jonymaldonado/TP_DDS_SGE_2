package ar.com.sge.comandos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.eclipse.paho.client.mqttv3.MqttException;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.mqtt.PublisherMQTT;

@Entity
@DiscriminatorValue("ahorro")
public class ComandoAhorroDeEnergia extends Comando {
	//private AdapterAhorroDeEnergia adaptador;
	private String nombreComando;
	//private DispositivoInteligente dispositivo;
	
	public ComandoAhorroDeEnergia() {
		this.nombreComando = "ahorro";
	}
	
	public ComandoAhorroDeEnergia(String nombreComando, DispositivoInteligente dispositivo) {
		
		this.nombreComando = nombreComando;
		//this.dispositivo = dispositivo;
	}

	public void ejecutar(DispositivoInteligente dispositivo, PublisherMQTT publicador){
		
		try {
			dispositivo.ahorroDeEnergia();
			publicador.enviarComando(dispositivo.getNombre(),this.nombreComando);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
