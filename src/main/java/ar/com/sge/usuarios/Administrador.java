package ar.com.sge.usuarios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import ar.com.sge.geografia.*;
import ar.com.sge.usuarios.Cliente.IdDistancia;
import ar.com.sge.util.DaoJsonTransformadores;
//import ar.com.sge.util.DaoTransFormadores;

@Entity
@DiscriminatorValue("administrador")
//@Table(name ="Administradores")
public class Administrador extends Usuario{
	
	/*@Id
	@GeneratedValue
	@Column(nullable=false,unique=true)
	private int idAdministrador;*/
	@OneToMany(fetch=FetchType.LAZY,mappedBy="administrador")
	private List<Cliente>listaDeClientes;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="administrador")
	private List<Transformador> listaDeTransformadoresActivos;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="administrador")
	private List<Zona> listaDeZonas;
	
	
	public Administrador() {
		
	}
	
	public Administrador(String _nombre, String _apellido, int _numeroId) {
		super(_nombre,_apellido);
		//this.idAdministrador = _numeroId;
		this.listaDeClientes= new ArrayList<>();
		this.listaDeTransformadoresActivos= new ArrayList<>();
		this.listaDeZonas= new ArrayList<>();
	}
	public List<Transformador> getListaDeTransformadoresActivos() {
		return listaDeTransformadoresActivos;
	}
	public List<Zona> getListaDeZonas() {
		return listaDeZonas;
	}
	public void setListaDeClientes(List<Cliente> listaDeClientes) {
		this.listaDeClientes = listaDeClientes;
	}
	public void setListaDeTransformadoresActivos(List<Transformador> listaDeTransformadoresActivos) {
		this.listaDeTransformadoresActivos = listaDeTransformadoresActivos;
	}
	public void setListaDeZonas(List<Zona> listaDeZonas) {
		this.listaDeZonas = listaDeZonas;
	}
	
	/*public int getNumeroId() {
		return idAdministrador;
	}*/
	public void tiempoEnElCargo() {
		LocalDate ini = getAlta();
		LocalDate fin = LocalDate.now();
		int days = Period.between(ini,fin).getDays();
		int months = Period.between(ini,fin).getMonths();
		int years = Period.between(ini,fin).getYears();
		System.out.println(years+" anio, "+ months +" meses, "+days+" dias");
	}
	public List<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}
	public void agregarCliente(Cliente unClientes) {
		this.listaDeClientes.add(unClientes);
	}
	public void agregarTransformadores(Transformador unTransformador) {
		this.listaDeTransformadoresActivos.add(unTransformador);
	}
	public  List<Transformador> cargarJsonTransformadores2(String archivoJson) throws FileNotFoundException{
		List<Transformador> transformadoresActivos= new ArrayList<Transformador>();
		Type type= new TypeToken<ArrayList<Transformador>>() {}.getType();
		
		Gson gson =new Gson();
		JsonReader json= new JsonReader(new FileReader(archivoJson));
		transformadoresActivos =gson.fromJson(json,type);
		return transformadoresActivos;
	}
	public  List<Transformador> cargarJsonTransformadores(String archivoJson) throws FileNotFoundException{
		List<Transformador> transformadoresActivos= new ArrayList<Transformador>();
		DaoJsonTransformadores dao = null;
		
		try {
			dao.setFilePath(archivoJson);
			transformadoresActivos= dao.getAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transformadoresActivos;
	}
	
	
	public List<Zona> cargarJsonZonas(String archivoJson) throws FileNotFoundException{
		List<Zona> listaDeZona= new ArrayList<Zona>();
		Type type= new TypeToken<ArrayList<Zona>>() { }.getType();
		Gson gson =new Gson();
		JsonReader json= new JsonReader(new FileReader(archivoJson));
		listaDeZona =gson.fromJson(json,type);
		return listaDeZona;
	}
	
	public void cargarJson(String archivoJsonTransformadores, String archivoJsonZonas) throws FileNotFoundException {
		this.setListaDeTransformadoresActivos(this.cargarJsonTransformadores(archivoJsonTransformadores));
		this.setListaDeZonas(this.cargarJsonZonas(archivoJsonZonas));
	}
	
	public Transformador buscarTransformador (int idTransformador) {
		Transformador transformadorElegido=null;
		for (Transformador t : listaDeTransformadoresActivos) {
			if (t.getIdtransformador()==idTransformador) {
				transformadorElegido=t;
			}//fin if
		}//fin for
		return transformadorElegido;
	}
	
	/*public void actualizarListasDeTransformadores() {
		try {
			Transformador transfElegido;
			double distancia;
			for (Cliente cliente : listaDeClientes) {
				double transformadorMasCercano = cliente.getDomicilio().distanciaAlPunto(listaDeTransformadoresActivos.get(0).getPosTransformador());
				for (Transformador transformador : listaDeTransformadoresActivos) {
					distancia=cliente.getDomicilio().distanciaAlPunto(transformador.getPosTransformador());
					distancia=Math.abs(distancia);
					transformadorMasCercano=Math.abs(transformadorMasCercano);
					if (transformadorMasCercano>=distancia)  {
						transformadorMasCercano=distancia;
						cliente.setIdTransformadorCorrespondiente(transformador.getIdtransformador()); 
					};//fin if
				};//fin for transfo
				transfElegido=this.buscarTransformador(cliente.getIdTransformadorCorrespondiente());
				transfElegido.agregarCliente(cliente);
			};//fin for clientes
		} catch (Exception e) {
			// TODO: handle exception
		}
	}// fin actializarlistas*/
	
	public void actualizarListasDeTransformadores(RepoTransformador repoTransformador,RepoCliente repo) throws IOException{
		repoTransformador.actualizarListasDeTransformadores(repo);
		
		}
	
	public void actualizarListasZona(RepoZona repoZona,RepoTransformador repo) throws IOException{
		repoZona.actualizarListasDeZonas(repo);
	}

}
 