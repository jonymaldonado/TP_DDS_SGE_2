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

import ar.com.sge.geografia.Zona;

public class DaoJsonZona implements DaoZona {
	private String filePath;
	private Gson myGson;
	private BufferedReader bufferToReader;
	private BufferedWriter bufferToWrite;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public DaoJsonZona() {
		super();
		this.myGson = new GsonBuilder().setPrettyPrinting().create();
	}

	public void add(Zona zona) throws IOException{
		this.writeJson(zona);
	}

	public void delete(Zona zona) throws IOException{
		ArrayList<Zona> zonas= new ArrayList<Zona>();
		zonas.addAll((getAll()));
		zonas.removeIf(unaZona->unaZona.getIdZona() == (zona.getIdZona()));
		for(Zona otraZona:zonas)
			this.add(otraZona);

		
		
		

	}

	public ArrayList<Zona> getAll() throws IOException{
		FileReader reader = new	FileReader(this.filePath);
		this.bufferToReader = new BufferedReader(reader);
		ArrayList<Zona> zonas = myGson.fromJson(getJson(), new TypeToken<ArrayList<Zona>>(){}.getType());
		return zonas;
	}

	public void update(Zona zona){

	}
	
	private String getJson() throws IOException{
		StringBuilder zonaSerializada = new StringBuilder();
		String linea;
		while( (linea=this.bufferToReader.readLine()) != null){
			zonaSerializada.append(linea);
		};
		this.bufferToReader.close();
		return zonaSerializada.toString();
	}

	private void writeJson(Zona zona) throws IOException{
		ArrayList<Zona> zonas= new ArrayList<Zona>();
		
		if(getAll()!=null)
			zonas.addAll((getAll()));
		zonas.add(zona);
		
		String zonaString= this.myGson.toJson(zona);
	
		PrintWriter pw = new PrintWriter(filePath);
		pw.close();
		
		this.bufferToWrite = new BufferedWriter(new FileWriter(this.filePath, true));
		//this.bufferToWrite.append(empleadoSerialized);

		this.bufferToWrite.write(zonaString);
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
