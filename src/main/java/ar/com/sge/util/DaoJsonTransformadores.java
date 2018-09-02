package ar.com.sge.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ar.com.sge.geografia.Transformador;
import ar.com.sge.usuarios.Cliente;

public class DaoJsonTransformadores implements DaoTransFormadores{
	private String filePath;
	private Gson myGson;
	private BufferedReader bufferToReader;
	private BufferedWriter bufferToWrite;

	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public DaoJsonTransformadores() {
		super();
		this.myGson = new GsonBuilder().setPrettyPrinting().create();
	}

	public void add(Transformador unTransformador) throws IOException{
		this.writeJson(unTransformador);
	}

	public void delete(Transformador unTransformador) throws IOException{
		ArrayList<Transformador> listaTransformadores= new ArrayList<Transformador>();
		listaTransformadores.addAll((getAll()));
		listaTransformadores.removeIf(transformador->transformador.getIdtransformador()==(unTransformador.getIdtransformador()));
		for(Transformador otroTransformador:listaTransformadores)
			this.add(otroTransformador);

		
		
		

	}

	public ArrayList<Transformador> getAll() throws IOException{
		FileReader reader = new	FileReader(this.filePath);
		this.bufferToReader = new BufferedReader(reader);
		ArrayList<Transformador> listaTranformadores= myGson.fromJson(getJson(), new TypeToken<ArrayList<Transformador>>(){}.getType());
		return listaTranformadores;
	}

	public void update(Transformador untransformador){
		//aca deberia poner la actualizacion de la lista, creo

	}
	
	private String getJson() throws IOException{
		StringBuilder transformadorSerializada = new StringBuilder();
		String linea;
		while( (linea=this.bufferToReader.readLine()) != null){
			transformadorSerializada.append(linea);
		};
		this.bufferToReader.close();
		return transformadorSerializada.toString();
	}

	private void writeJson(Transformador unTransformador) throws IOException{
		ArrayList<Transformador>listaTransformadores= new ArrayList<Transformador>();
		
		if(getAll()!=null)
			listaTransformadores.addAll((getAll()));
		listaTransformadores.add(unTransformador);
		
		String transformadorString= this.myGson.toJson(listaTransformadores);
	
		PrintWriter pw = new PrintWriter(filePath);
		pw.close();
		
		this.bufferToWrite = new BufferedWriter(new FileWriter(this.filePath, true));
		//this.bufferToWrite.append(empleadoSerialized);

		this.bufferToWrite.write(transformadorString);
		this.bufferToWrite.close();
	}

	public Gson getMyGson() {
		return myGson;
	}

	public BufferedReader getBufferToReader() {
		return bufferToReader;
	}


	public BufferedWriter getBufferToWrite() {
		return bufferToWrite;
	}

	
	public String getFilePath() {
		return filePath;
	}


}
