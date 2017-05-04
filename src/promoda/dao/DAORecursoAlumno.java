package promoda.dao;

import java.util.List;

import promoda.model.Alumno;
import promoda.model.Recurso;
import promoda.model.RecursoAlumno;

public interface DAORecursoAlumno {
	
	public int insertar(RecursoAlumno recursoAlumno);
	
	public int update(RecursoAlumno recursoAlumno);
	
	public RecursoAlumno get(int id);
	
	public RecursoAlumno get(Recurso recurso, Alumno alumno);
	
	public List<RecursoAlumno> getLista();
	
	public List<RecursoAlumno> getLista(Recurso recurso);
	
	public List<RecursoAlumno> getLista(Alumno alumno);

}
