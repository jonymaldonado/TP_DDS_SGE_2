package ar.com.sge.util;

import java.io.IOException;
import java.util.ArrayList;

import ar.com.sge.geografia.Zona;

public interface DaoZona {
	public void add(Zona zona) throws IOException;
	public void delete(Zona zona) throws IOException;
	public ArrayList<Zona> getAll() throws IOException;
	public void update(Zona zona);

}
