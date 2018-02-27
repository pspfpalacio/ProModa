package promoda.dao;

import java.util.List;

import promoda.model.Alumno;

public interface DAOAlumno {
	
	public int insertar(Alumno alumno);
	
	public int update(Alumno alumno);
	
	public Alumno get(int id);
	
	public Alumno getPorDni(int dni);
	
	public List<Alumno> getLista();
	
	public List<Alumno> getLista(boolean estado);
	
//	public List<Alumno> getLista(Matricula matricula);

}
