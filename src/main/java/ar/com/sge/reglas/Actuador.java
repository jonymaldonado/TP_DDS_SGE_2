package ar.com.sge.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.sge.comandos.Comando;
import entity.ActuadoresComandos;
import entity.Reglas;

@Entity
@Table(name = "actuadores")
public class Actuador implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "descripcion")
	private String descripcion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "actuador")
	private List<Comando> listacomandos;

	public Actuador() {
		listacomandos= new ArrayList<>();
	}
	
	public Actuador(String descripcion) {
		this.descripcion = descripcion;
		listacomandos= new ArrayList<>();
	}

	public void ejecutarAccion(String accion) {
		Comando comandobuscado;
		List<Comando> listafiltrada = new ArrayList<Comando>();
		listafiltrada = listacomandos.stream().filter(a -> (a.getNombre().equalsIgnoreCase(accion))).collect(Collectors.toList());
		comandobuscado = listafiltrada.get(0);
		comandobuscado.ejecutar();

	}

	public List<Comando> getListacomandos() {
		return listacomandos;
	}

	public void setListacomandos(List<Comando> listacomandos) {
		this.listacomandos = listacomandos;
	}

	public void addcomando(Comando comandonuevo) {
		listacomandos.add(comandonuevo);
	}

}