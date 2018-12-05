package testjava;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.reglas.Actuador;
import ar.com.sge.reglas.Regla;
import ar.com.sge.reglas.Sensor;
import ar.com.sge.usuarios.Cliente;

public class TestMQTT {

	public static void main(String[] args) throws MqttException, InterruptedException {
		
		
		
		EntityManager entityManager;
		entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		
		Cliente clientebase=(Cliente) entityManager.createNativeQuery("select * from usuario where nombre_usuario = 'juan'", Cliente.class).getResultList().get(0);
		DispositivoInteligente dispositivo = clientebase.getLstDispositivosInteligentes().get(0);
		Actuador actuador = dispositivo.getActuador();
		Sensor sensor = dispositivo.getSensor();
		//sensor.notificarALosObservadores(80.0);
		sensor.suscripcion();
		
	while(true)	{		
		transaction.begin();
		transaction.commit();
	}
}
}
