package promoda.dao;

import java.util.List;

import promoda.model.Vista;

public interface DAOVista {
	
	public int insertar(Vista vista);
	
	public int update(Vista vista);
	
	public Vista get(int id);
	
	public List<Vista> getLista();

}
