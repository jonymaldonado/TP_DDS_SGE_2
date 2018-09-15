package ar.com.sge.estados;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;

@Entity
@Table(name ="Apagado")
public class Apagado extends Estado{

	@Id
	@GeneratedValue
	private int Id;
	
	public Apagado(DispositivoInteligente inteligente) {
		super(inteligente);
		this.nombre = "apagado";
		fechaInicio =LocalDateTime.now();
	}
	public Apagado (String nombre,LocalDateTime inicio,LocalDateTime fin) {
		super(nombre,inicio,fin,0);
	}
	
	public void encender(DispositivoInteligente dispositivo) {
		fechaFin = LocalDateTime.now();
		consumo = 0;
		dispositivo.agregarEstado(this);
		dispositivo.setEstado(new Encendido(dispositivo));			
	}

	public void apagar(DispositivoInteligente inteligente) {
		
	}

	public void ahorroDeEnergia(DispositivoInteligente inteligente) {

	}
}
