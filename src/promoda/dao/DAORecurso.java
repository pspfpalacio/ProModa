package promoda.dao;

import java.util.List;

import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Recurso;

public interface DAORecurso {
	
	public int insertar(Recurso recurso);
	
	public int update(Recurso recurso);
	
	public Recurso get(int id);
	
	public List<Recurso> getLista();
	
	public List<Recurso> getLista(Materia materia);
	
	public List<Recurso> getLista(Curso curso);
	
//	public List<Recurso> getLista(Matricula matricula);
	
//	public List<Recurso> getLista(Materia materia, Matricula matricula);
	
//	public List<Recurso> getListaHistoricos(Materia materia, Matricula matricula);

}
