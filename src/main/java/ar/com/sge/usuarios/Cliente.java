package ar.com.sge.usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.optim.PointValuePair;

import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.dispositivos.DispositivoInteligente;
//import ar.com.sge.dispositivos.IDispositivo;
import ar.com.sge.dispositivos.Modulo;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.util.servicioSimplex;
//import ar.com.sge.geografia.Coordenada;

public class Cliente extends Usuario {
	
	private String tipoDoc;
	private int numeroDoc;
//	private Coordenada domicilio;
	private int telefono;	
	private List<DispositivoInteligente> lstDispositivosInteligentes ;
	private List<DispositivoEstandar> lstDispositivosEstandares ;
	private Categoria categoria;
	private int puntos;
	//private int idTransformadorCorrespondiente;//despues se vera si vale la pena poner este atributo
	//private Transformador transformador;
	private servicioSimplex servicio;

	/*public Cliente(String _nombre, String _apellido,String _tipoDoc,int _numeroDoc,double latitud,double longitud) {		
=======
	public Cliente(String _nombre, String _apellido,String _tipoDoc,int _numeroDoc,float latitud,float longitud) {		
>>>>>>> 55d6153bb164c64abe9f3fcdd29e944e114d8019
		super(_nombre,_apellido,latitud,longitud);
		this.tipoDoc = _tipoDoc;
		this.numeroDoc = _numeroDoc;
		lstDispositivosInteligentes = new ArrayList<>();
		lstDispositivosEstandares = new ArrayList<>();
	}*/
	
	/*public Transformador getTransformador() {
		return transformador;
	}

	public void setTransformador(Transformador transformador) {
		this.transformador = transformador;
	}*/

	public Cliente(String _nombre, String _apellido, String tipoDoc, int numeroDoc, int telefono, Categoria categoria,int puntos,double latitud,double longitud ) {
		super(_nombre,_apellido,latitud,longitud);
		this.tipoDoc = tipoDoc;
		this.numeroDoc = numeroDoc;
		this.telefono = telefono;
		this.lstDispositivosInteligentes  = new ArrayList<>();
		this.lstDispositivosEstandares  = new ArrayList<>();
		this.categoria = categoria;
		this.puntos = 0;
	//	this.idTransformadorCorrespondiente = 0;
	}
	public Cliente(String _nombre, String _apellido, String tipoDoc, int numeroDoc, int telefono,double latitud,double longitud ) {
		super(_nombre,_apellido,latitud,longitud);
		this.tipoDoc = tipoDoc;
		this.numeroDoc = numeroDoc;
		this.telefono = telefono;
		this.lstDispositivosInteligentes  = new ArrayList<>();
		this.lstDispositivosEstandares  = new ArrayList<>();
		this.puntos = 0;
		//this.idTransformadorCorrespondiente = 0;
		
	}

	public String getTipoDoc() {
		return tipoDoc;
	}
	
	public int getNumeroDoc() {
		return numeroDoc;
	}
	
	public void setTelefono(int _unNumero) {
		this.telefono = _unNumero;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setCategoria(Categoria _categoria) {
		this.categoria = _categoria;
	}
	public String getCategoria() {
		return categoria.getNombre();		
	}
	
	/*public int getIdTransformadorCorrespondiente() {
		return idTransformadorCorrespondiente;
	}
	public void setIdTransformadorCorrespondiente(int idTransformadorCorrespondiente) {
		this.idTransformadorCorrespondiente = idTransformadorCorrespondiente;
	}*/
		
	public void agregarDispositivosEstandares(DispositivoEstandar unDispositivoEstandar) {
		lstDispositivosEstandares.add(unDispositivoEstandar);
	}
	public void agregarDispositivosInteligentes(DispositivoInteligente unDispositivoInteligente) {
		lstDispositivosInteligentes.add(unDispositivoInteligente);
		puntos += 15;
	}
	/*public void agregarDispositivosInteligentes(IDispositivo unDispositivoInteligente) {
		lstDispositivosInteligentes.add(unDispositivoInteligente);
		puntos += 15;
	}*/
	public void quitarDispositivosEstandares(DispositivoEstandar unDispositivoEstandar) {
		lstDispositivosEstandares.remove(unDispositivoEstandar);
	}
	public void quitarDispositivosInteligentes(DispositivoInteligente unDispositivoInteligente) {
		lstDispositivosInteligentes.remove(unDispositivoInteligente);
	}
	
	public void listarDispositivos(){
		this.lstDispositivosEstandares.forEach(dispositivoEstandar -> System.out.println(dispositivoEstandar.getNombre()));
		this.lstDispositivosInteligentes.forEach(dispositivosInteligente -> System.out.println(dispositivosInteligente.getNombre()));
	}
	
	public List<DispositivoInteligente> dispositivosEncendidos(){
		List<DispositivoInteligente> lstDispEnc;
		lstDispEnc = lstDispositivosInteligentes.stream().filter(d-> d.estasEncendido()==true).collect(Collectors.toList());
		return lstDispEnc;
	}
	
	public List<DispositivoInteligente> dispositivosApagados(){
		List<DispositivoInteligente> lstDispApag;
		lstDispApag = lstDispositivosInteligentes.stream().filter(d->d.estasApagado()==true).collect(Collectors.toList());
		return lstDispApag;
	}
	
	public int cantidadDispositivosEncendidos() {
		return this.dispositivosEncendidos().size();
	}
	
	public int cantidadDispositivosApagados() {
		return dispositivosApagados().size();
	}
	
	public int cantidadDeDispositivos() {
		return this.cantidadDedispositivosInteligentes() + this.cantidadDeDispositivosEstandares();
	}	
	
	public float consumoDeEnergia() {
		float sum = 0;
		for(DispositivoInteligente d: lstDispositivosInteligentes) { 
			sum += d.consumoEnKw();
		}
		for(DispositivoEstandar d: lstDispositivosEstandares) { 
			sum += d.consumoEnKw();
		}
		return sum;
	}
	public float estimarFacturacion() {
		float vf = categoria.getValorFijo();
		float vv = categoria.getValorVariable();
		float resultado = vf + consumoDeEnergia() * vv;
		return resultado;
	}
	public float facturacionEntre(LocalDateTime inicioPeriodo,LocalDateTime finPeriodo){
		float resultado=0;
		float resultadototal=0;
		
		float vf = categoria.getValorFijo();
		float vv = categoria.getValorVariable();
		for(DispositivoInteligente d: lstDispositivosInteligentes) { 
			resultado += d.consumidoComprendidoEntre(inicioPeriodo, finPeriodo);
		}
		resultadototal = vf + resultado * vv;
		return resultadototal;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public void agregarUnModuloA(DispositivoEstandar unDispositivoEstandar, Modulo unModulo) {
		unModulo.setDispositivoEstandar(unDispositivoEstandar);
		lstDispositivosInteligentes.add(unModulo);
		puntos += 10;
		quitarDispositivosEstandares(unDispositivoEstandar);		
	}
	
	public int cantidadDedispositivosInteligentes() {
		return lstDispositivosInteligentes.size();
	}
	
	public int cantidadDeDispositivosEstandares() {
		return lstDispositivosEstandares.size();
	}
	
	public PointValuePair consultarASimplex() {
		PointValuePair solucion = this.getServicioSimplex().consultarSimplex(getLstDispositivosInteligentes());
		return solucion;
	}

	public List<DispositivoInteligente> getLstDispositivosInteligentes() {
		return lstDispositivosInteligentes;
	}

	public void setLstDispositivosInteligentes(List<DispositivoInteligente> lstDispositivosInteligentes) {
		this.lstDispositivosInteligentes = lstDispositivosInteligentes;
	}

	public List<DispositivoEstandar> getLstDispositivosEstandares() {
		return lstDispositivosEstandares;
	}

	public void setLstDispositivosEstandares(List<DispositivoEstandar> lstDispositivosEstandares) {
		this.lstDispositivosEstandares = lstDispositivosEstandares;
	}
	
	public class IdDistancia{
		private int id;
		private double distancia;
		
		public IdDistancia (){
			
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getDistancia() {
			return distancia;
		}
		public void setDistancia(double distancia) {
			this.distancia = distancia;
		}
			
	}
	
	public int calcularMinimo (ArrayList<IdDistancia> lista) {
		double unValor=1000;
		int idlocal=0;
		for (IdDistancia idDistancia : lista) {
			if (idDistancia.getDistancia()<unValor) {
				unValor=idDistancia.getDistancia();
				unValor=idDistancia.getId();
			}
		}
		
		return idlocal;
	}
	
//	public IdDistancia[] inicializarArray(IdDistancia[] array){
//		for (int i = 0; i < array.length; i++) {
//			array[i].setId(0);
//			array[i].setDistancia(0);
//		}
//		return array;
//	}
	public servicioSimplex getServicioSimplex() {
		if(this.servicio == null) {
			this.servicio = new servicioSimplex();
		}
		return this.servicio;
	}

}