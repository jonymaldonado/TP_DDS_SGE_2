package ar.com.sge.dispositivos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.com.sge.estados.Apagado;
import ar.com.sge.estados.Estado;
import ar.com.sge.reglas.Sensor;


public class DispositivoInteligente implements IDispositivo{

	private String nombre;
	private double kwPorHora;
	private Boolean encendido ;
	private Estado estado;
	private List<Estado> listaDeEstados = new ArrayList<Estado>();
	private static final float coeficienteAhorroEnergia = (float) 0.6;
	private LocalDateTime inicioPeriodo;
	private double maximoconsumo;
	private double minimoconsumo;
	private Sensor sensor;
	private boolean apagarPorSimplex;
	private boolean estadoDispositivo;

	public DispositivoInteligente(String nombre, double kw) {
		this.nombre = nombre;
		this.kwPorHora = kw;
		this.estado = new Apagado();
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
	}
	public List<Estado> getEstados(){
		return this.listaDeEstados;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void encender() {
		estado.encender(this);
	}

	public void apagar() {
		estado.apagar(this);
	}

	public void ahorroDeEnergia() {
		estado.ahorroDeEnergia(this);
	}

	public Boolean estasEncendido() {
		return this.estadoDispositivo;
	}
	public Boolean estasApagado() {
		return this.estadoDispositivo;
	}

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
		return consumidoComprendidoEntre(inicioPeriodo, LocalDateTime.now());		
	}
			
	public double consumidoUltimasNhoras (int cantHoras) {
		LocalDateTime fechaInicio = LocalDateTime.now().minusHours(cantHoras);
		LocalDateTime fechaFin = LocalDateTime.now();
		return this.consumidoComprendidoEntre(fechaInicio, fechaFin);
	}
	
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
		
	public boolean cumpleCondicion(Estado e, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return (e.getFechaInicio().isBefore(fechaFin) && e.getFechaFin().isAfter(fechaInicio));
	}
	
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
	}

	//activo el sensor y como parametro le indico las cada cuantas horas se va a ejecutar
	public void activarSensor(int valor){
		sensor.activate(this,valor);
	}
	
	public void desactivarSensor() {
		sensor.desactivate();
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


}

