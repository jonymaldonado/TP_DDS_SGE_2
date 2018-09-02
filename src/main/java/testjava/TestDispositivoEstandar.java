package testjava;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.com.sge.dispositivos.DispositivoEstandar;
import ar.com.sge.usuarios.Categoria;
import ar.com.sge.usuarios.Cliente;

public class TestDispositivoEstandar {
	Categoria unaCategoriaR1;
	Cliente unCliente;
	DispositivoEstandar unDispoEstandar;
	
	@Before
	public void initialize() {

		unaCategoriaR1= new Categoria("R1", (float) 18.76,(float) 0.644);
		unCliente= new Cliente("John", "Doe", "dni", 12345678,234423,54.8,43.8);

		unDispoEstandar= new DispositivoEstandar("Ventilador de techo", (float) 17.5, 7);
		unCliente.agregarDispositivosEstandares(unDispoEstandar);
	}//fin initialize

	@Test
	public void testControlDeConsumoDeDispEstandar() {
		assertNotEquals("No corresponde al consumo que se esta esperando", 120.0, unCliente.consumoDeEnergia(), 1.0);
	}//fin testControlDeConsumoDeDispEstandar

}
