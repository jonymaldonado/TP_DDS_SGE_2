package ar.com.sge.mqtt;


import org.eclipse.paho.client.mqttv3.*;

import ar.com.sge.reglas.Sensor;

public class SubscriberMQTT {
    
	public void setUpMQTTSubscriber(Sensor sensor, String topico) throws MqttException, InterruptedException {
    	
		System.out.println("== START SUBSCRIBER ==");
        MqttClient client = new MqttClient("tcp://localhost:1883", "serverClientId");
        
        client.setCallback(new MqttCallback(){
            
        	public void connectionLost(Throwable throwable){
                System.out.println("Se ha perdido la Conexion con el MQTT Broker");
            }

            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception{
            	
            	System.out.println("Message Recibido:\t" + new String(mqttMessage.getPayload()));
            	String recibido = new String(mqttMessage.getPayload());
            	if(recibido.isEmpty()){
            		recibido = "0.0";
            	}
            	double valor = Double.parseDouble(recibido);
                sensor.notificarALosObservadores(valor);
            	
            }
            
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
            
        });
        
        client.connect();

        client.subscribe("sensor"+topico);
        System.out.println("Ok, Subscripcion!");
        
    }
}