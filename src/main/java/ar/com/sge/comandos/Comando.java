package ar.com.sge.comandos;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.mqtt.PublisherMQTT;

@Entity
@Inheritance
@DiscriminatorColumn(name="tipo_Estado")
//@ForceDiscriminator
@Table(name ="Comandos")
public abstract class Comando {

	@Id
	@GeneratedValue
	private int Idcomando;
	public abstract void ejecutar(DispositivoInteligente dispositivo, PublisherMQTT publicador);
	public abstract String getNombre();
	
}

