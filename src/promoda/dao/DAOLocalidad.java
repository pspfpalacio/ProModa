package promoda.dao;

import java.util.List;

import promoda.model.Localidade;
import promoda.model.Provincia;

public interface DAOLocalidad {
	
	public int insertar(Localidade localidad);
	
	public int update(Localidade localidad);
	
	public Localidade get(int id);
	
	public List<Localidade> getLista();
	
	public List<Localidade> getLista(Provincia provincia);

}
