package ar.com.sge.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.dispositivos.IDispositivo;
import ar.com.sge.usuarios.Cliente;
import simplex.facade.SimplexFacade;

public class servicioSimplex {

	private SimplexFacade simplex;
	private TimerTask tarea;

	public PointValuePair consultarSimplex(List<DispositivoInteligente> lista) {
		ArrayList<Double> listavariables = new ArrayList<Double>();
		simplex = new SimplexFacade(GoalType.MAXIMIZE, true);
		double[] variables = new double[lista.size()];
		double[] variableskw = new double[lista.size()];
		int i = 0;
		int tamanio = lista.size() - 1;

		for (DispositivoInteligente elemento : lista) {
			// listavariables.add(1);
			variables[i] = 1;
			variableskw[tamanio] = elemento.getKwPorHora();
			i++;
			tamanio--;

		}
		simplex.crearFuncionEconomica(variables);
		simplex.agregarRestriccion(Relationship.LEQ, 440640, variableskw);
		for (DispositivoInteligente elemento : lista) {

			for (int a = 0; a < lista.size(); a++) {
				if (elemento.getKwPorHora() == variableskw[a]) {
					variables[a] = 1;
				} else {
					variables[a] = 0;
				}

			}
			simplex.agregarRestriccion(Relationship.GEQ, elemento.getMinimoconsumo(), variables);
			simplex.agregarRestriccion(Relationship.LEQ, elemento.getMaximoconsumo(), variables);
		}
		PointValuePair solucion = simplex.resolver();
		return solucion;
	}

	public void ejecutarSimplex(Cliente un_cliente) {

		PointValuePair solucion = consultarSimplex(un_cliente.getLstDispositivosInteligentes());
		List<DispositivoInteligente> lista = un_cliente.getLstDispositivosInteligentes();
		int i = lista.size() - 1;
		for (DispositivoInteligente elemento : lista) {
			if (solucion.getPoint()[i] < elemento.consumoEnKw()) {
				if (elemento.apagadoAutomaticoPorSimplex()) {
					elemento.apagar();
				}
			}

		}

	}

	public void activarSimplex(Cliente cliente, int horas) {
		Timer timer = new Timer();
		this.tarea = new TimerTask() {
			public void run() {
				ejecutarSimplex(cliente);
			}
		};
		int tiempo = horas * 3600000;
		timer.schedule(tarea, tiempo, tiempo);// se jecuta cada N horas
	}

	public void desactivarSimplex() {
		tarea.cancel();
	}

}
