package promoda.dao;

import java.util.Date;
import java.util.List;

import promoda.model.Alumno;
import promoda.model.Asistencia;
import promoda.model.Curso;
import promoda.model.Materia;

public interface DAOAsistencia {
	
	public int insertar(Asistencia asistencia);
	
	public int update(Asistencia asistencia);
	
	public Asistencia get(int id);
	
//	public Asistencia get(Curso curso, Matricula matricula, Materia materia, Alumno alumno, int nroClase);
//	
//	public Asistencia get(Curso curso, Matricula matricula, Materia materia, Alumno alumno, int nroClase, Date fechaInicio, Date fechaFin);
	
	public List<Asistencia> getLista();
	
	public List<Asistencia> getLista(Alumno alumno);
	
	public List<Asistencia> getLista(Materia materia);
	
	public List<Asistencia> getLista(Alumno alumno, Materia materia);
	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula, Materia materia);
//	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula, Materia materia, int nroClase);
//	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula, Materia materia, Date fechaInicio, Date fechaFin);
//	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula, Materia materia, int nroClase, Date fechaInicio, Date fechaFin);
//	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula, Materia materia, Alumno alumno);

}
