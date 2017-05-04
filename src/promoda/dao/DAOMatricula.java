package promoda.dao;

import java.util.List;

import promoda.model.Curso;
import promoda.model.Matricula;

public interface DAOMatricula {
	
	public int insertar(Matricula matricula);
	
	public int update(Matricula matricula);
	
	public Matricula get(int id);
	
	public List<Matricula> getLista();
	
	public List<Matricula> getLista(boolean estado);
	
	public List<Matricula> getLista(Curso curso);
	
	public List<Matricula> getLista(boolean estado, Curso curso);
	
	public List<Matricula> getListaDesc(Curso curso);
	
	public List<Matricula> getListaFechaDesc(Curso curso);

}
