package ar.com.sge.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Scanner;

public class PublisherMQTT {
	

    public void enviarComando (String unTema, String unMensaje) throws MqttException {
       
        MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(unMensaje.getBytes());
        client.publish(unTema, message);
        client.disconnect();

    }
}
