package promoda.dao;

import java.util.List;

import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.MatriculaImpaga;

public interface DAOMatriculaImpaga {
	
	public int insertar(MatriculaImpaga matriculaImpaga);
	
	public int update(MatriculaImpaga matriculaImpaga);
	
	public int delete(MatriculaImpaga matriculaImpaga);
	
	public MatriculaImpaga get(int id);
	
	public MatriculaImpaga get(MatriculaAlumno matriculaAlumno);
	
	public MatriculaImpaga get(Alumno alumno, Curso curso, Matricula matricula);
	
	public List<MatriculaImpaga> getLista();
	
	public List<MatriculaImpaga> getLista(Alumno alumno);
	
	public List<MatriculaImpaga> getLista(Alumno alumno, Curso curso);
	
	public List<MatriculaImpaga> getLista(Curso curso, Matricula matricula);

}
