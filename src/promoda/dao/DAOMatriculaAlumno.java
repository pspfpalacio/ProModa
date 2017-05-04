package promoda.dao;

import java.util.List;

import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;

public interface DAOMatriculaAlumno {
	
	public int insertar(MatriculaAlumno matriculaAlumno);
	
	public int update(MatriculaAlumno matriculaAlumno);
	
	public MatriculaAlumno get(int id);
	
	public MatriculaAlumno get(Alumno alumno, Curso curso, Matricula matricula);
	
	public List<MatriculaAlumno> getLista();
	
	public List<MatriculaAlumno> getLista(Alumno alumno);
	
	public List<MatriculaAlumno> getLista(Alumno alumno, Curso curso);
	
	public List<Curso> getListaCurso(Alumno alumno);
	
	public List<MatriculaAlumno> getLista(Curso curso, Matricula matricula, boolean pago);
	
	public List<MatriculaAlumno> getLista(boolean pago);

}
