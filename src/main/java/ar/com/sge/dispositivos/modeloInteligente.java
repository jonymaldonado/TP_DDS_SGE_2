package ar.com.sge.dispositivos;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.sge.estados.Estado;

@Entity
@Table(name ="ModeloInteligente")
public class modeloInteligente {
	@Id
	@GeneratedValue
	private int id_inteligente;
	private String nombre;
	private double kwPorHora;
	private double coeficienteAhorroEnergia;
	private double maximoconsumo;
	private double minimoconsumo;
	public int getId_inteligente() {
		return id_inteligente;
	}
	public void setId_inteligente(int id_inteligente) {
		this.id_inteligente = id_inteligente;
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
	public double getCoeficienteAhorroEnergia() {
		return coeficienteAhorroEnergia;
	}
	public void setCoeficienteAhorroEnergia(double coeficienteAhorroEnergia) {
		this.coeficienteAhorroEnergia = coeficienteAhorroEnergia;
	}
	public double getMaximoconsumo() {
		return maximoconsumo;
	}
	public void setMaximoconsumo(double maximoconsumo) {
		this.maximoconsumo = maximoconsumo;
	}
	public double getMinimoconsumo() {
		return minimoconsumo;
	}
	public void setMinimoconsumo(double minimoconsumo) {
		this.minimoconsumo = minimoconsumo;
	}
	public modeloInteligente(String nombre, double kwPorHora, double coeficienteAhorroEnergia,
			double maximoconsumo, double minimoconsumo) {
		//super();
		this.id_inteligente = id_inteligente;
		this.nombre = nombre;
		this.kwPorHora = kwPorHora;
		this.coeficienteAhorroEnergia = coeficienteAhorroEnergia;
		this.maximoconsumo = maximoconsumo;
		this.minimoconsumo = minimoconsumo;
	}
	public modeloInteligente() {
		
	}
	
	

}
