package promoda.dao;

import promoda.model.Parametro;

public interface DAOParametro {
	
	public int insertar(Parametro parametro);
	
	public int update(Parametro parametro);
	
	public Parametro get(int id);

}
