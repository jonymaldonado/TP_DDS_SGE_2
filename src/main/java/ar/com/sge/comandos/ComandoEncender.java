package ar.com.sge.comandos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.eclipse.paho.client.mqttv3.MqttException;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.mqtt.PublisherMQTT;

@Entity
@DiscriminatorValue("encender")
public class ComandoEncender extends Comando{
	
	
	private String nombreComando;
	//private DispositivoInteligente dispositivo;
	
	public ComandoEncender() {
		this.nombreComando = "encender";
	}
	public ComandoEncender(String nombreComando, DispositivoInteligente dispositivo) {
		
		this.nombreComando = nombreComando;
		//this.dispositivo = dispositivo;
	}

	public void ejecutar(DispositivoInteligente dispositivo, PublisherMQTT publicador){
		
		try {
			dispositivo.encender();
			publicador.enviarComando(dispositivo.getNombre(),this.nombreComando);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNombre() {
		return nombreComando;
	}

	public void setNombrecomando(String nombrecomando) {
		this.nombreComando = nombrecomando;
	}
	
}
