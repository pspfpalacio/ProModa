package promoda.dao;

import java.util.List;

import promoda.model.Profesore;

public interface DAOProfesor {
	
	public int insertar(Profesore profesor);
	
	public int update(Profesore profesor);
	
	public Profesore get(int id);
	
	public Profesore getPorDni(int dni);
	
	public Profesore get(String dni);
	
	public List<Profesore> getLista();
	
	public List<Profesore> getLista(boolean estado);

}
