package ar.com.sge.reglas;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.mqtt.SubscriberMQTTPrueba;


@Entity
@Table(name ="sensor")
public class Sensor {
	@Id
	@GeneratedValue
	private int id_sensor;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="id_sensor")
	private List<Regla> observadores ;
	@OneToOne
	private DispositivoInteligente inteligente;
	/*@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "id_tarea")
	private TimerTask tarea;*/
	private SubscriberMQTTPrueba subscriberSensor;
	
	
	public Sensor() {
		this.observadores = new ArrayList<>();
		this.subscriberSensor = new SubscriberMQTTPrueba();
	}
	
	public Sensor(String comparador,float valor,String accion) {
		this.observadores = new ArrayList<>();
		this.agregarObservador(new Regla(comparador,valor,accion));
	}
	
	public void activate(DispositivoInteligente dispositivo,int horas){
		programarTarea(dispositivo, horas);
	}

	public void programarTarea(DispositivoInteligente dispositivo, int horas) {
		Timer timer = new Timer();
		TimerTask tarea = new TimerTask(){
			public void run() {
				medir(dispositivo);
			}
		};
		int tiempo = horas * 3600000;
		timer.schedule(tarea,tiempo,tiempo);//se jecuta cada N horas 
	}

	public void medir(DispositivoInteligente dispositivo){
		double valor = dispositivo.consumoEnKw();
		//observadores.forEach(r -> r.verificarRegla(valor));
		observadores.forEach(r -> r.verificarRegla(valor,inteligente));
	}
	public void notificarALosObservadores(double valor) {
		//this.getObservadores().forEach(r -> r.verificarRegla(valor));
		this.observadores.forEach(r -> r.verificarRegla(valor,inteligente));
		
	}
	
	public void eliminarRegla(int valor) {
		//this.getObservadores().forEach(r -> r.verificarRegla(valor));
		this.observadores.removeIf(i->i.getId_regla()==valor);
		
	}
	
	
	
	/*public void desactivate(){
		tarea.cancel();
	}*/

	public DispositivoInteligente getInteligente() {
		return inteligente;
	}

	public void setInteligente(DispositivoInteligente inteligente) {
		this.inteligente = inteligente;
	}

	public void agregarObservador(Regla regla){
		observadores.add(regla);
	}
	public void setValor(double unValor) {
		this.notificarALosObservadores(unValor);
	}
	public List<Regla> getObservadores() {
		return observadores;
	}
}











