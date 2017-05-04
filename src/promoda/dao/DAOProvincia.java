package promoda.dao;

import java.util.List;

import promoda.model.Provincia;

public interface DAOProvincia {
	
	public int insertar(Provincia provincia);
	
	public int update(Provincia provincia);
	
	public Provincia get(int id);
	
	public List<Provincia> getLista();

}
