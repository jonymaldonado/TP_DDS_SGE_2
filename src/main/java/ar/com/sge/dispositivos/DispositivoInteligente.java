package ar.com.sge.dispositivos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.sge.estados.Apagado;
import ar.com.sge.estados.Estado;
import ar.com.sge.reglas.Actuador;
import ar.com.sge.reglas.Sensor;
import ar.com.sge.usuarios.Administrador;
import ar.com.sge.usuarios.Cliente;
import ar.com.sge.usuarios.Hogar;


@Entity
@DiscriminatorValue("inteligente")

public class DispositivoInteligente extends IDispositivo{

	/*@Id
	@GeneratedValue
	private int id_inteligente;*/
	//@Column(name="Nombre",nullable=false,length=50)
	private String nombre;
	private double kwPorHora;
	@Column(nullable=true)
	private Boolean encendido ;
	@OneToOne(cascade={CascadeType.ALL})
	//@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="id_estado1")
	//@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="inteligente")
	private Estado estado;
	//@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="inteligente")
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="id_estado")
	private List<Estado> listaDeEstados;
	//private static final float coeficienteAhorroEnergia = (float) 0.6;
	private double coeficienteAhorroEnergia;
	private LocalDateTime inicioPeriodo;
	private double maximoconsumo;
	private double minimoconsumo;
	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="inteligente")
	private Sensor sensor;
	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="inteligente")
	private Actuador actuador;
	private boolean apagarPorSimplex;
	private boolean estadoDispositivo;
	@ManyToOne(fetch=FetchType.LAZY)
	//@ManyToOne()
	@JoinColumn(name = "id_Usuario")
	private Cliente cliente;
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idAdministrador")	
	private Administrador administrador;*/
	@ManyToMany(mappedBy = "listaDispositivos")
    private List<Hogar> hogares;
 

	public DispositivoInteligente(String nombre, double kw) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		coeficienteAhorroEnergia=0.6;
		listaDeEstados = new ArrayList<Estado>();
		this.setEstado(new Apagado());
		//this.estado = new Apagado();
		this.hogares = new ArrayList<>();
	}
	
	public DispositivoInteligente() {
		listaDeEstados = new ArrayList<>();
		this.setEstado(new Apagado());
	}
	@Override
	public IDispositivo clone() throws CloneNotSupportedException{
		DispositivoInteligente inteligente=null;
		try {
			inteligente=(DispositivoInteligente) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return inteligente;
	}

	public String getNombre() {
		return nombre;
	}
		
	public double getKwPorHora() {
		return kwPorHora;
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
	public void setEstado(Estado e) {
		this.estado = e;
		//e.setInteligente(this);
	}
	
	public List<Estado> getEstados(){
		return this.listaDeEstados;
	}
	
	public Estado getEstado() {
		return estado;
	}

	//Enciende el Dispositivo
	public void encender() {
		estado.encender(this);
	}

	//Apaga el Dispositivo
	public void apagar() {
		estado.apagar(this);
	}

	//Pone el Dispositivo en Modo Ahorro de Energia
	public void ahorroDeEnergia() {
		estado.ahorroDeEnergia(this);
	}

	//Devuelve si esta encendido
	public Boolean estasEncendido() {
		return this.estadoDispositivo;
	}
	
	//Devuelve si esta apagado
	public Boolean estasApagado() {
		return this.estadoDispositivo;
	}

	//Agrega un estado a la lista de estados
	public void agregarEstado(Estado e) {
		listaDeEstados.add(e);
	}
	
	public void setInicioPeriodo(LocalDateTime periodo) {
		this.inicioPeriodo = periodo;
	}
	
	public LocalDateTime getInicioPeriodo() {
		return inicioPeriodo;
	}
	
	//consumo en lo que va del mes
	public double consumoEnKw() {
		return consumidoComprendidoEntre(this.inicioPeriodo, LocalDateTime.now());		
	}
	
	//Devuelve lo consumido en las N horas
	public double consumidoUltimasNhoras (int cantHoras) {
		LocalDateTime fechaInicio = LocalDateTime.now().minusHours(cantHoras);
		LocalDateTime fechaFin = LocalDateTime.now();
		return this.consumidoComprendidoEntre(fechaInicio, fechaFin);
	}
	
	//Devuelve lo que consumio el Dispotivo entre dos fechas
	public double consumidoComprendidoEntre(LocalDateTime fechaInicio , LocalDateTime fechaFin) {
		double totalConsumo ;
		float totalHoras ;
		List<Estado> lstEstados;
		
		//Calculo consumo encendidos 		
		lstEstados = this.listaDeEstadosSegun(fechaInicio, fechaFin, "encendido");
		totalHoras = this.totalDeHoras(lstEstados, fechaInicio, fechaFin);
		totalConsumo = (totalHoras )* this.getKwPorHora();
		
		
		//Calculo consumo ahorro de energia
		/*lstEstados = this.listaDeEstadosSegun(fechaInicio, fechaFin, "Ahorro de enegia");
		totalHoras = this.totalDeHoras(lstEstados, fechaInicio, fechaFin);
		totalConsumo += totalHoras * this.getKwPorHora() * coeficienteAhorroEnergia;
		*/
		return totalConsumo;
		
	}
	
	//Devuelve una lista de los estados que cumplan con cumpleCondicion y el nombre del estado sea igual a tipoDeEstado
	public List<Estado> listaDeEstadosSegun(LocalDateTime fechaInicio , LocalDateTime fechaFin, String tipoDeEstado) {
		List<Estado> lstEstadosSegun;
		lstEstadosSegun = listaDeEstados.stream().filter(e -> (cumpleCondicion(e,fechaInicio,fechaFin)) 
				&& ((e.getNombre().equals(tipoDeEstado)))).collect(Collectors.toList());
		return lstEstadosSegun;
		 
	}
	
	/*public float totalDeConsumo(List<Estado> lstEstados) {		
		float totalConsumo = 0;		
		for (Estado estado : lstEstados){
			totalConsumo += estado.getConsumo();
		}
		return totalConsumo;
	}*/
		
	//Verifica que la fecha de inicio de un estados sea antes de fechaFin y que la fecha de inicio del estado sea despues de fechaInicio
	public boolean cumpleCondicion(Estado e, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return (e.getFechaInicio().isBefore(fechaFin) && e.getFechaFin().isAfter(fechaInicio));
	}
	
	//
	public float totalDeHoras (List<Estado> lstEstados,  LocalDateTime fechaInicio, LocalDateTime fechaFin) {		
		LocalDateTime fechaMinima;
		LocalDateTime fechaMaxima;
		float totalHoras = 0;
			
		for (Estado estado : lstEstados){
			fechaMinima = this.fechaMaxima(fechaInicio, estado.getFechaInicio());
			fechaMaxima = this.fechaMinima(fechaFin, estado.getFechaFin());
			totalHoras += diferenciaHoras(fechaMinima, fechaMaxima);
		}
		return totalHoras;
	}
	
	public float diferenciaHoras(LocalDateTime unahora, LocalDateTime otrahora){
		float dif=  ChronoUnit.MINUTES.between(unahora, otrahora);
		dif=dif/60;
		return dif;
	}
	
	public LocalDateTime fechaMaxima (LocalDateTime unaHora, LocalDateTime otraHora){
		if (unaHora.isAfter(otraHora)){
			return unaHora;
		}
		else {
			return otraHora;		
		}
	}
	
	public LocalDateTime fechaMinima (LocalDateTime unaHora, LocalDateTime otraHora){
		if (unaHora.isBefore(otraHora)){
			return unaHora;
		}
		else {
			return otraHora;		
		}
	}
	
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
		sensor.setInteligente(this);
	}

	//activo el sensor y como parametro le indico las cada cuantas horas se va a ejecutar
	public void activarSensor(int valor){
		sensor.activate(this,valor);
	}
	
	public void desactivarSensor() {
		//sensor.desactivate();
	}
	
	public void activarApagadoAutomaticoSimplex() {
		this.apagarPorSimplex = true;
	}
	
	public void desactivarApagadoAutomaticoSimplex() {
		this.apagarPorSimplex = false;
	}
	
	public boolean apagadoAutomaticoPorSimplex() {
		return this.apagarPorSimplex;
	}
	
	public void setEstadoDipositivo(boolean valor) {
		this.estadoDispositivo = valor;
	}
	public Actuador getActuador() {
		return actuador;
	}
	public void setActuador(Actuador acuador) {
		this.actuador = acuador;
		actuador.setInteligente(this);
	}
	public boolean isEstadoDispositivo() {
		return estadoDispositivo;
	}
	public void setEstadoDispositivo(boolean estadoDispositivo) {
		this.estadoDispositivo = estadoDispositivo;
	}
	public Sensor getSensor() {
		return sensor;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getEncendido() {
		return encendido;
	}
	public void setEncendido(Boolean encendido) {
		this.encendido = encendido;
	}
	public List<Estado> getListaDeEstados() {
		return listaDeEstados;
	}
	public void setListaDeEstados(List<Estado> listaDeEstados) {
		this.listaDeEstados = listaDeEstados;
	}
	public boolean isApagarPorSimplex() {
		return apagarPorSimplex;
	}
	public void setApagarPorSimplex(boolean apagarPorSimplex) {
		this.apagarPorSimplex = apagarPorSimplex;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/*public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}*/
	public double getCoeficienteahorroenergia() {
		return coeficienteAhorroEnergia;
	}
	public void setKwPorHora(double kwPorHora) {
		this.kwPorHora = kwPorHora;
	}
	

}