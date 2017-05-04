package promoda.dao;

import java.util.List;

import promoda.model.Domicilio;
import promoda.model.Localidade;

public interface DAODomicilio {
	
	public int insertar(Domicilio domicilio);
	
	public int update(Domicilio domicilio);
	
	public Domicilio get(int id);
	
	public List<Domicilio> getLista();
	
	public List<Domicilio> getLista(boolean estado);
	
	public List<Domicilio> getLista(Localidade localidad);
	
	public List<Domicilio> getLista(boolean estado, Localidade localidad);

}
