package ar.com.sge.usuarios;

import java.time.LocalDateTime;

import ar.com.sge.dispositivos.DispositivoInteligente;
import ar.com.sge.estados.Encendido;
import ar.com.sge.geografia.Transformador;

public class AdministradorMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stu
		Transformador transformador1, transformador2, transformador3;
		Cliente cliente1,cliente2,cliente3,cliente4,cliente5; 
		Categoria unaCategoriaR1;
		DispositivoInteligente unTV40,aire1,unaHeladera,unaHeladera2, pc1, pc2, aire2; 
		Administrador admin;
		LocalDateTime inicioestado,finestado;
		Encendido estado1;
		
		admin= new Administrador("Jony", "Maldo",1986);
		
		transformador1=new Transformador(001, -43.55f, 3.44f,01);
		transformador2=new Transformador(002, 54.47f,-24.55f,02);
		transformador3=new Transformador(003, 55.55f, 3.23f,01);
		
		unaCategoriaR1= new Categoria("R1", (float) 18.76,(float) 0.644);
		
		cliente1= new Cliente("Carlos","Ligorria", "DNI", 14555666, 54363366, unaCategoriaR1, 0, 54.44f, 2.65f);
		cliente2= new Cliente("Leonardo","Silva", "DNI", 66664554, 86543345, unaCategoriaR1, 0, 55.44f, 65.65f);
		cliente3= new Cliente("Gustavo","Apaza", "DNI", 37543232, 54674744, unaCategoriaR1, 0, 22.44f, 6.87f);
		cliente4= new Cliente("Roberto","Castro", "DNI", 44545532, 54676744, unaCategoriaR1, 0, -5.44f, 33.65f);
		cliente5= new Cliente("Susana ","Mosquera", "DNI", 54876578, 78874744, unaCategoriaR1, 0, 9.44f, 65.00f);
		
		unTV40=new DispositivoInteligente("unTV40", 0.08f);
		aire1=new DispositivoInteligente("aire1", 1013f);
		unaHeladera=new DispositivoInteligente("unaHeladera", 0.075f);
		unaHeladera2=new DispositivoInteligente("unaHeladera2", 0.09f);
		pc1=new DispositivoInteligente("pc1", 0.4f);
		pc2=new DispositivoInteligente("pc2", 0.4f);
		aire2 =new DispositivoInteligente("aire2", 1613f);
		
		inicioestado=LocalDateTime.of(2018, 8, 21, 18, 00);
		finestado=LocalDateTime.of(2018, 8, 22, 19, 00);
		estado1=new Encendido("encendido",inicioestado,finestado,1.0f);
		//estado1=new Encendido("encendido",inicioestado,finestado,aire1.getKwPorHora());
		//>estado1=new Encendido("encendido",inicioestado,finestado,aire2.getKwPorHora());
		//estado1=new Encendido("encendido",inicioestado,finestado,unaHeladera.getKwPorHora());
		//estado1=new Encendido("encendido",inicioestado,finestado,pc1.getKwPorHora());
		unTV40.setEstado(estado1);
		
		
		cliente1.agregarDispositivosInteligentes(pc1);
		cliente1.agregarDispositivosInteligentes(aire2);
		cliente1.agregarDispositivosInteligentes(unaHeladera2);
		
		cliente2.agregarDispositivosInteligentes(unTV40);
		
		cliente3.agregarDispositivosInteligentes(aire1);
		cliente3.agregarDispositivosInteligentes(aire2);
		cliente3.agregarDispositivosInteligentes(pc2);
		cliente3.agregarDispositivosInteligentes(pc1);
		
		cliente4.agregarDispositivosInteligentes(aire2);
		cliente4.agregarDispositivosInteligentes(pc2);
		
		cliente5.agregarDispositivosInteligentes(unaHeladera);
		cliente5.agregarDispositivosInteligentes(pc1);
		cliente5.agregarDispositivosInteligentes(unTV40);
		
		admin.agregarTransformadores(transformador1);
		admin.agregarTransformadores(transformador2);
		admin.agregarTransformadores(transformador3);
		
		admin.agregarCliente(cliente1);
		admin.agregarCliente(cliente2);
		admin.agregarCliente(cliente3);
		admin.agregarCliente(cliente4);
		admin.agregarCliente(cliente5);
		
		admin.actualizarListasDeTransformadores();
		
		//PRUEBAS
		System.out.println("--------------------- DISTANCIA DE LOS CLIENTES CON LOS TRANSFORMADORES" );

			
		System.out.println("cliente1");
		System.out.print(cliente1.getDomicilio().distanciaAlPunto(transformador1.getPosTransformador()));System.out.println(" x");
		System.out.println(cliente1.getDomicilio().distanciaAlPunto(transformador2.getPosTransformador()));
		System.out.println(cliente1.getDomicilio().distanciaAlPunto(transformador3.getPosTransformador()));
		System.out.println("cliente2");
		System.out.print(cliente2.getDomicilio().distanciaAlPunto(transformador1.getPosTransformador()));System.out.println(" x");
		System.out.println(cliente2.getDomicilio().distanciaAlPunto(transformador2.getPosTransformador()));
		System.out.println(cliente2.getDomicilio().distanciaAlPunto(transformador3.getPosTransformador()));
		System.out.println("cliente3");
		System.out.println(cliente3.getDomicilio().distanciaAlPunto(transformador1.getPosTransformador()));
		System.out.print(cliente3.getDomicilio().distanciaAlPunto(transformador2.getPosTransformador()));System.out.println(" x");
		System.out.println(cliente3.getDomicilio().distanciaAlPunto(transformador3.getPosTransformador()));
		System.out.println("cliente4");
		System.out.println(cliente4.getDomicilio().distanciaAlPunto(transformador1.getPosTransformador()));
		System.out.println(cliente4.getDomicilio().distanciaAlPunto(transformador2.getPosTransformador()));
		System.out.print(cliente4.getDomicilio().distanciaAlPunto(transformador3.getPosTransformador()));System.out.println(" x");
		System.out.println("cliente5");
		System.out.println(cliente5.getDomicilio().distanciaAlPunto(transformador1.getPosTransformador()));
	System.out.println(cliente5.getDomicilio().distanciaAlPunto(transformador2.getPosTransformador()));
		System.out.print(cliente5.getDomicilio().distanciaAlPunto(transformador3.getPosTransformador()));System.out.println(" x");
		
		System.out.println("---------------------  CANTIDAD DE CLIENTES EN LAS LISTAS DE LOS TRANSFORMADORES ");
		
		
		
		System.out.print(transformador1.getListaDeclientesConectados().size());System.out.println("  DEBERIA SER 2");	
		System.out.print(transformador2.getListaDeclientesConectados().size());System.out.println("  DEBERIA SER 1");	
		System.out.print(transformador3.getListaDeclientesConectados().size());System.out.println("  DEBERIA SER 2");	
		
		System.out.println("--------------------- CONSUMO DE ENERGIA DE CADA CLIENTE ");
		
		
		System.out.print("cliente1: ");
		System.out.println(cliente1.consumoDeEnergia());
		System.out.print("cliente2: ");
		System.out.println(cliente2.consumoDeEnergia());
		System.out.print("cliente3: ");
		System.out.println(cliente3.consumoDeEnergia());
		System.out.print("cliente4: ");
		System.out.println(cliente4.consumoDeEnergia());
		System.out.print("cliente5: ");
		System.out.println(cliente5.consumoDeEnergia());
		
		
		System.out.println("---------------------");
		System.out.println(cliente1.getLstDispositivosInteligentes().size());
		System.out.println(cliente2.getLstDispositivosInteligentes().size());
		System.out.println(cliente3.getLstDispositivosInteligentes().size());
		System.out.println(cliente4.getLstDispositivosInteligentes().size());
		System.out.println(cliente5.getLstDispositivosInteligentes().size());
		System.out.println("---------------CANTIDAD DE CLIENTES EN LA LISTA-----");
		System.out.println(admin.getListaDeClientes().size());
		System.out.println("---------------------");
		System.out.println("---------------------");
		System.out.println("---------------------");	
		

	}

}
