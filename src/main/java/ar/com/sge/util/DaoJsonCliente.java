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

import ar.com.sge.usuarios.Cliente;

public class DaoJsonCliente implements Dao {
	private String filePath;
	private Gson myGson;
	private BufferedReader bufferToReader;
	private BufferedWriter bufferToWrite;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public DaoJsonCliente() {
		super();
		this.myGson = new GsonBuilder().setPrettyPrinting().create();
	}

	public void add(Cliente cliente) throws IOException{
		this.writeJson(cliente);
	}

	public void delete(Cliente cliente) throws IOException{
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		clientes.addAll((getAll()));
		clientes.removeIf(c->c.getNombre().equals(cliente.getNombre()));
		for(Cliente c:clientes)
			this.add(c);

		
		
		

	}

	public ArrayList<Cliente> getAll() throws IOException{
		FileReader reader = new	FileReader(this.filePath);
		this.bufferToReader = new BufferedReader(reader);
		ArrayList<Cliente> clientes = myGson.fromJson(getJson(), new TypeToken<ArrayList<Cliente>>(){}.getType());
		return clientes;
	}

	public void update(Cliente cliente){

	}
	
	private String getJson() throws IOException{
		StringBuilder clienteSerializada = new StringBuilder();
		String linea;
		while( (linea=this.bufferToReader.readLine()) != null){
			clienteSerializada.append(linea);
		};
		this.bufferToReader.close();
		return clienteSerializada.toString();
	}

	private void writeJson(Cliente cliente) throws IOException{
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		
		if(getAll()!=null)
			clientes.addAll((getAll()));
		clientes.add(cliente);
		
		String clientesString= this.myGson.toJson(clientes);
	
		PrintWriter pw = new PrintWriter(filePath);
		pw.close();
		
		this.bufferToWrite = new BufferedWriter(new FileWriter(this.filePath, true));
		//this.bufferToWrite.append(empleadoSerialized);

		this.bufferToWrite.write(clientesString);
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
