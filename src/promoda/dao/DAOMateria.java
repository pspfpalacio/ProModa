package promoda.dao;

import java.util.List;

import promoda.model.Curso;
import promoda.model.Materia;

public interface DAOMateria {
	
	public int insertar(Materia materia);
	
	public int update(Materia materia);
	
	public Materia get(int id);
	
	public List<Materia> getLista();
	
	public List<Materia> getLista(boolean estado);
	
	public List<Materia> getLista(Curso curso);
	
	public List<Materia> getLista(boolean estado, Curso curso);

}
