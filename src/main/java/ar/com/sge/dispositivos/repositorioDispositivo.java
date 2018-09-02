package ar.com.sge.dispositivos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.com.sge.usuarios.Cliente;

public class repositorioDispositivo {
	List<DispositivoInteligente> listaActualInteligentes;
	List<DispositivoEstandar> listaActualEstandar;
	public void agregar(DispositivoInteligente dispositivo) {
		if (listaActualInteligentes==null) {
			listaActualInteligentes=new ArrayList<DispositivoInteligente>();
		}
		listaActualInteligentes.add(dispositivo);
	}
	
	public void agregarStandar(DispositivoEstandar dispositivo) {
		if (listaActualEstandar==null) {
			listaActualEstandar=new ArrayList<DispositivoEstandar>();
		}
		listaActualEstandar.add(dispositivo);
	}
	
	public void seleccionarInteligente(Cliente cliente,String dispositivoSeleccionado) throws CloneNotSupportedException{
		List<DispositivoInteligente> lstDispEnc;
		lstDispEnc = listaActualInteligentes.stream().filter(a-> a.getNombre().equalsIgnoreCase(dispositivoSeleccionado)).collect(Collectors.toList());
		cliente.agregarDispositivosInteligentes((DispositivoInteligente)lstDispEnc.get(0).clone());
	}
	
	public void seleccionarStandar(Cliente cliente,String dispositivoSeleccionado) throws CloneNotSupportedException{
		List<DispositivoEstandar> lstDispEnc;
		lstDispEnc = listaActualEstandar.stream().filter(a-> a.getNombre().equalsIgnoreCase(dispositivoSeleccionado)).collect(Collectors.toList());
		cliente.agregarDispositivosEstandares((DispositivoEstandar)lstDispEnc.get(0).clone());
	}

}
