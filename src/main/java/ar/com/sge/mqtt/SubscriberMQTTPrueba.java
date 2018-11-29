package ar.com.sge.mqtt;


import org.eclipse.paho.client.mqttv3.*;

import ar.com.sge.reglas.Sensor;

public class SubscriberMQTTPrueba {
	
	private Sensor sensor;
	

    public SubscriberMQTTPrueba(Sensor sensor) {
		super();
		this.sensor = sensor;
	}




	public void main() throws MqttException, InterruptedException {

        System.out.println("== START SUBSCRIBER ==");

        MqttClient client = new MqttClient("tcp://localhost:1883", "serverClientId");
        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable throwable) {
                System.out.println("Connection to MQTT broker lost!");
            }

            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                //System.out.println("Message received:\t" + new String(mqttMessage.getPayload()));
            }
            
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });
        client.connect();

        client.subscribe("sensor");
        System.out.println("ok!");


    }

}
