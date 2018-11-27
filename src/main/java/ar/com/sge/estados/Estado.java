package ar.com.sge.estados;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;


@Entity
@Inheritance
@DiscriminatorColumn(name="tipo_Estado")
//@ForceDiscriminator
@Table(name ="estado_dispositivo")
public abstract class Estado {

	@Id
	@GeneratedValue
	private int id_estado;
	
	protected String nombre;
	protected LocalDateTime fechaInicio;
	protected LocalDateTime fechaFin;
	protected double consumo;
	//@ManyToOne
	//@OneToOne
	//@JoinColumn(name = "id_inteligente")
	//private DispositivoInteligente inteligente;
	/*private String nombre;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private double consumo;*/
	
	/*public Estado() {
		
	}*/
		
	/*public DispositivoInteligente getInteligente() {
		return inteligente;
	}

	public void setInteligente(DispositivoInteligente inteligente) {
		this.inteligente = inteligente;
	}*/

	public abstract void encender(DispositivoInteligente inteligente);

	public abstract void apagar(DispositivoInteligente inteligente);

	public abstract void ahorroDeEnergia(DispositivoInteligente inteligente);

	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDateTime getFechaInicio() {
		return this.fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return this.fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	public double getConsumo() {
		return this.consumo;
	}
	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}
}
