package ar.com.sge.estados;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue("apagado")
//@Table(name ="Apagado")
public class Apagado extends Estado{


	/*@Id
	@GeneratedValue
	private int Id;*/
	
	public Apagado() {

		this.nombre = "apagado";
		this.fechaInicio =LocalDateTime.now();
	}
	public Apagado (String nombre,LocalDateTime inicio,LocalDateTime fin) {
		this.nombre = nombre;
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.consumo = 0;
	}
	
	public void encender(DispositivoInteligente dispositivo) {
		fechaFin = LocalDateTime.now();
		consumo = 0;
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Encendido());	
		dispositivo.setEstadoDipositivo(true);
	}


	public void apagar(DispositivoInteligente inteligente) {
		
	}

	public void ahorroDeEnergia(DispositivoInteligente inteligente) {


	}
}