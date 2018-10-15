package testjava;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.*;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.com.sge.geografia.RepoTransformador;
import ar.com.sge.geografia.RepoZona;
import ar.com.sge.geografia.Transformador;
import ar.com.sge.geografia.Zona;
import ar.com.sge.util.DaoJsonTransformadores;
import ar.com.sge.util.DaoJsonZona;

public class TestTransformadorPersist {
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	Transformador unTransformador;
	private RepoZona repoZona;
	private List<Zona> listainicial;
	private RepoTransformador repoTans;
	private List<Transformador> lista2;
	
	@Before
	public void init() throws IOException{
		DaoJsonZona dao = new DaoJsonZona();
		DaoJsonTransformadores dao1 = new DaoJsonTransformadores();
		dao.setFilePath("jsonZona.json");
		dao1.setFilePath("document.json");
		repoZona = new RepoZona(dao);
		listainicial=repoZona.getAllZonas();
		repoTans = new RepoTransformador(dao1);
		lista2 = repoTans.getAllTransformadores();
	}
	/*
	@Test
	public void persistirTransformadores() throws IOException{
		//EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		repoZona.getAllZonas();
		repoTans.asignarTransformadores();
		repoZona.actualizarListasDeZonas(repoTans);
		repoZona.persistir();
		//transaction.commit();
	}*/
	
	@Test
	public void persistirZonas() throws IOException{
		//EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		repoTans.actualizarListasDeTransformadoresbase();
		repoZona.getAllZonas();
		//repoTans.asignarTransformadores();
		repoZona.actualizarZonas(repoTans);
		repoZona.persistir2();
		//transaction.commit();
	}

}
