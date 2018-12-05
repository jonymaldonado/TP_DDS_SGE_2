package testjava;

import org.eclipse.paho.client.mqttv3.MqttException;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.reglas.Actuador;
import ar.com.sge.reglas.Regla;
import ar.com.sge.reglas.Sensor;

public class TestMQTT {

	public static void main(String[] args) throws MqttException, InterruptedException {
		
		DispositivoInteligente dispositivo = new DispositivoInteligente("TV", 1.1);
		Actuador actuador = new Actuador();
		Regla regla = new Regla("menor", 1.0 , "encender");
		Sensor sensor = new Sensor();
		sensor.agregarObservador(regla);
		sensor.setInteligente(dispositivo);		
		dispositivo.setActuador(actuador);
		sensor.suscripcion();
		
	}
}