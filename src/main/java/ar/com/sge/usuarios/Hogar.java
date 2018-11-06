package ar.com.sge.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;



@Entity
@Table(name = "Hogares")
public class Hogar {

	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private int id;
	private String calle;
	private int numero;
	@Column(nullable = true)
	private int piso;
	@Column(nullable = true)
	private String departamento;
	@Column(nullable = true)
	private String coordenada_x;
	@Column(nullable = true)
	private String corrdenada_y;
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Hogar_Dispositivo", 
        joinColumns = { @JoinColumn(name = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "Id_Dispositivo") }
    )
	private List<DispositivoInteligente> listaDispositivos;
	
	
	public Hogar() {
		this.listaDispositivos  = new ArrayList<>();
	}

	public Hogar(int id, String calle, int numero, int piso, String departamento) {
		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.departamento = departamento;
		this.listaDispositivos  = new ArrayList<>();
	}

	public Hogar( String calle, int numero, int piso, String departamento) {
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.departamento = departamento;
		this.listaDispositivos  = new ArrayList<>();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCoordenada_x() {
		return coordenada_x;
	}

	public void setCoordenada_x(String coordenada_x) {
		this.coordenada_x = coordenada_x;
	}

	public String getCorrdenada_y() {
		return corrdenada_y;
	}

	public void setCorrdenada_y(String corrdenada_y) {
		this.corrdenada_y = corrdenada_y;
	}
	
	public void agregarDispositivo(DispositivoInteligente dispositivo) {
		this.listaDispositivos.add(dispositivo);
	}
	
	public List<DispositivoInteligente> getListaDispositivos(){
		return this.listaDispositivos;
	}
	/*
	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	public DispositivoInteligente getDispositivo(){
		return this.dispositivo;
	}*/
	

}
