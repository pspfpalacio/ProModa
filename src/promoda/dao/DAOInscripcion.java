package promoda.dao;

import java.util.List;

import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Inscripcione;
import promoda.model.Matricula;

public interface DAOInscripcion {
	
	public int insertar(Inscripcione inscripcione);
	
	public int update(Inscripcione inscripcione);
	
	public Inscripcione get(int id);
	
	public List<Inscripcione> getLista();
	
	public List<Inscripcione> getLista(boolean estado);
	
	public List<Inscripcione> getListaOrderByAlumno(boolean estado);
	
	public List<Inscripcione> getLista(Alumno alumno);
	
	public List<Inscripcione> getLista(Curso curso);
	
	public List<Inscripcione> getLista(boolean estado, Alumno alumno);
	
	public List<Inscripcione> getLista(boolean estado, Curso curso);
	
	public List<Inscripcione> getLista(Alumno alumno, Curso curso);
	
	public List<Inscripcione> getLista(boolean estado, Alumno alumno, Curso curso);
	
	public List<Inscripcione> getList(boolean valida, boolean estado);
	
	public List<Inscripcione> getLista(boolean estado, Curso curso, Matricula matricula);
	
	public List<Inscripcione> getListaOrderByAlumno(boolean estado, Curso curso, Matricula matricula);
	
	public List<Inscripcione> getListaOrderByFechaId(boolean estado, Curso curso);
	
	public List<Inscripcione> getListaOrderByAlumno(boolean estado, Curso curso);

}
