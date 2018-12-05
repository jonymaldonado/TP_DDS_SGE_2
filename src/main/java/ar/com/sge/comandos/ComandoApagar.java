package ar.com.sge.comandos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.eclipse.paho.client.mqttv3.MqttException;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.mqtt.PublisherMQTT;


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
		this.nombreComando = "apagar";
	}

	public void ejecutar(DispositivoInteligente dispositivo, PublisherMQTT publicador){
		
		try {
			dispositivo.apagar();
			publicador.enviarComando(dispositivo.getNombre(),this.nombreComando);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNombre() {
		return nombreComando;
	}

	public void setNombreComando(String nombrecomando) {
		this.nombreComando = nombrecomando;
	}
}
