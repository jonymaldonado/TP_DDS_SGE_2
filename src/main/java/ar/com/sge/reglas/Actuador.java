package ar.com.sge.reglas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.com.sge.comandos.Comando;


public class Actuador {
	

	private List<Comando> listacomandos;
	public Actuador() {
		listacomandos= new ArrayList<>();
	}

	public void ejecutarAccion(String accion) {
		Comando comandobuscado;
		List<Comando> listafiltrada;
		listafiltrada = listacomandos.stream().filter(a -> a.getNombre().equalsIgnoreCase(accion))
				.collect(Collectors.toList());
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