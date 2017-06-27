package promoda.dao;

import java.util.List;

import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.MateriasCalificacion;
import promoda.model.Matricula;

public interface DAOMateriasCalificacion {
	
	public int insertar(MateriasCalificacion materiasCalificacion);
	
	public int update(MateriasCalificacion materiasCalificacion);
	
	public MateriasCalificacion get(int id);	
	
	public List<MateriasCalificacion> getLista();
	
	public List<MateriasCalificacion> getLista(Curso curso, Matricula matricula, Materia materia);
	
	public List<MateriasCalificacion> getListaOrberByAlumno(Curso curso, Matricula matricula, Materia materia);

}
