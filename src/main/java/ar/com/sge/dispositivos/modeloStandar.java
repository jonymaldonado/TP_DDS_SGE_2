package ar.com.sge.dispositivos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ModeloStandar")
public class modeloStandar {
	@Id
	@GeneratedValue
	private int id_standar;
	private String nombre;
	private double kwPorHora;
	private int horasDeUso;
	public modeloStandar(String nombre, double kwPorHora) {
		//super();
		this.nombre = nombre;
		this.kwPorHora = kwPorHora;
		//this.horasDeUso = horasDeUso;
	}
	public modeloStandar() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getKwPorHora() {
		return kwPorHora;
	}
	public void setKwPorHora(double kwPorHora) {
		this.kwPorHora = kwPorHora;
	}
	public int getHorasDeUso() {
		return horasDeUso;
	}
	public void setHorasDeUso(int horasDeUso) {
		this.horasDeUso = horasDeUso;
	}
	

}
