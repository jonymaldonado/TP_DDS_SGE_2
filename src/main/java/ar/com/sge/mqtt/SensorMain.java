package ar.com.sge.mqtt;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SensorMain {

	public static void main(String[] args) throws MqttException{
		
		Scanner scanner = new Scanner(System.in);


        System.out.println("== START Publisher ==");


        MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        client.connect();


        while (true) {


            System.out.print("Ingresar mensaje:\t");
            String selection = scanner.nextLine();

            if (selection.equals("salir")) {
                break;
            } else {
                MqttMessage message = new MqttMessage();
                message.setPayload(selection.getBytes());
                client.publish("sensorTV", message);
            }
        }

        client.disconnect();

        System.out.println("== END PUBLISHER ==");

    }

}


