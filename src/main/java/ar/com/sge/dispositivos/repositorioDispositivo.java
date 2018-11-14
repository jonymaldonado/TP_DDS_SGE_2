package ar.com.sge.dispositivos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.sge.reglas.Actuador;
import ar.com.sge.reglas.Sensor;
import ar.com.sge.usuarios.Cliente;
@Entity
@Table(name ="repositorio")
public class repositorioDispositivo {

	@Id
	@GeneratedValue
	@Column(nullable=false,unique=true)
	private int Id_Repositorio;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="Id_Int")
	List<DispositivoInteligente> listaActualInteligentes;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="Id_Stan")
	List<DispositivoEstandar> listaActualEstandar;
	
	
	
	
	public repositorioDispositivo() {
		listaActualInteligentes=new ArrayList<>();
	}
	
	

	public int getIdRepositorio() {
		return Id_Repositorio;
	}



	public void setIdRepositorio(int idRepositorio) {
		this.Id_Repositorio = idRepositorio;
	}



	public List<DispositivoInteligente> getListaActualInteligentes() {
		return listaActualInteligentes;
	}



	public void setListaActualInteligentes(List<DispositivoInteligente> listaActualInteligentes) {
		this.listaActualInteligentes = listaActualInteligentes;
	}



	public List<DispositivoEstandar> getListaActualEstandar() {
		return listaActualEstandar;
	}



	public void setListaActualEstandar(List<DispositivoEstandar> listaActualEstandar) {
		this.listaActualEstandar = listaActualEstandar;
	}



	public void agregar(DispositivoInteligente dispositivo) {
		if (listaActualInteligentes==null) {
			listaActualInteligentes=new ArrayList<>();
		}
		listaActualInteligentes.add(dispositivo);
	}
	
	public void agregarStandar(DispositivoEstandar dispositivo) {
		if (listaActualEstandar==null) {
			listaActualEstandar=new ArrayList<>();
		}
		listaActualEstandar.add(dispositivo);
	}
	public void seleccionarInteligente(Cliente cliente,String dispositivoSeleccionado,String regla,float valor, String accion) throws CloneNotSupportedException{
		List<DispositivoInteligente> lstDispEnc;
		lstDispEnc = listaActualInteligentes.stream().filter(a-> a.getNombre().equals(dispositivoSeleccionado)).collect(Collectors.toList());
		System.out.println("nombre "+dispositivoSeleccionado+" tamaño "+listaActualInteligentes.get(0).getNombre());
		DispositivoInteligente dispositivo1 = this.retornarNuevoDispositivo(lstDispEnc.get(0));
		Sensor sensor = new Sensor(regla,valor,accion);
		Actuador actuador = new Actuador();
		dispositivo1.setActuador(actuador);
		dispositivo1.setSensor(sensor);
		cliente.agregarDispositivosInteligentes(dispositivo1);
		
	}
	
	public void seleccionarStandar(Cliente cliente,String dispositivoSeleccionado) throws CloneNotSupportedException{
		List<DispositivoEstandar> lstDispEnc;
		lstDispEnc = listaActualEstandar.stream().filter(a-> a.getNombre().equalsIgnoreCase(dispositivoSeleccionado)).collect(Collectors.toList());
		System.out.print(lstDispEnc.get(0).getNombre());
		DispositivoEstandar dispositivo1 = this.retornarNuevoDispositivoEstandar(lstDispEnc.get(0));
		cliente.agregarDispositivosEstandares(dispositivo1);
	}
	
	/*
	 * public DispositivoInteligente buscarDispositivoInteligente(String dipositivo) {
		DispositivoInteligente dispo = null;
		//dispo = listaActualInteligentes.stream().filter(a-> a.getNombre().equals(dispositivoSeleccionado)).collect(Collectors.toList());
		for(DispositivoInteligente d: this.listaActualInteligentes) {
			if(d.getNombre().equals(dipositivo)) {
				dispo = d;
			}
		}
		return dispo;
	}*/
	
	public DispositivoInteligente retornarNuevoDispositivo(DispositivoInteligente dispo) {
		DispositivoInteligente dispo2 = new DispositivoInteligente();
		dispo2.setNombre(dispo.getNombre());
		dispo2.setKwPorHora(dispo.getKwPorHora());
		dispo2.setMinimoconsumo(dispo.getMinimoconsumo());
		dispo2.setMaximoconsumo(dispo.getMaximoconsumo());
		return dispo2;
	}
	
	public DispositivoEstandar retornarNuevoDispositivoEstandar(DispositivoEstandar dispo) {
		DispositivoEstandar dispo2 = new DispositivoEstandar();
		dispo2.setNombre(dispo.getNombre());
		dispo2.setKwPorHora(dispo.getKwPorHora());
		return dispo2;
	}

}
