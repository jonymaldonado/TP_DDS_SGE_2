package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class server {
	public static void main(String[] args) {
		Spark.port(10000);
		DebugScreen.enableDebugScreen();
		router.configure();
	}

}


