package ar.com.sge.reglas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.sge.comandos.Comando;
import ar.com.sge.dispositivos.DispositivoInteligente;


@Entity
@Table(name ="Actuador")
public class Actuador {
	@Id
	@GeneratedValue
	private int id_Actuador;
	@OneToOne
	private DispositivoInteligente inteligente;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="id_actuador")
	private List<Comando> listacomandos;
	public Actuador() {
		listacomandos= new ArrayList<>();
	}

	public void ejecutarAccion(String accion) {
		Comando comandobuscado;
		List<Comando> listafiltrada = new ArrayList<Comando>();
		listafiltrada = listacomandos.stream().filter(a -> (a.getNombre().equalsIgnoreCase(accion))).collect(Collectors.toList());
		comandobuscado = listafiltrada.get(0);
		comandobuscado.ejecutar(inteligente);
		

	}

	public List<Comando> getListacomandos() {
		return listacomandos;
	}
	
	

	public DispositivoInteligente getInteligente() {
		return inteligente;
	}

	public void setInteligente(DispositivoInteligente inteligente) {
		this.inteligente = inteligente;
	}

	public void setListacomandos(List<Comando> listacomandos) {
		this.listacomandos = listacomandos;
	}

	public void addcomando(Comando comandonuevo) {
		listacomandos.add(comandonuevo);
	}

}