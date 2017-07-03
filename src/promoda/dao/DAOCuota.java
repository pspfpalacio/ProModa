package promoda.dao;

import java.util.Date;
import java.util.List;

import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.Curso;
import promoda.model.Matricula;

public interface DAOCuota {
	
	public int insertar(Cuota cuota);
	
	public int update(Cuota cuota);
	
	public Cuota get(int id);
	
	public List<Cuota> getLista();
	
	public List<Cuota> getLista(boolean estado);
	
	public List<Cuota> getLista(boolean estado, boolean paga);
	
	public List<Cuota> getLista(Alumno alumno);
	
	public List<Cuota> getLista(Matricula matricula);
	
	public List<Cuota> getLista(boolean estado, Alumno alumno);
	
	public List<Cuota> getLista(boolean estado, Matricula matricula);
	
	public List<Cuota> getLista(Alumno alumno, Matricula matricula);
	
	public List<Cuota> getLista(boolean estado, Alumno alumno, Matricula matricula);
	
	public List<Cuota> getLista(Alumno alumno, Matricula matricula, Curso curso);
	
	public List<Cuota> getLista( Alumno alumno, Matricula matricula, Curso curso, boolean paga);
	
	public List<Cuota> getLista(Curso curso, Matricula matricula, boolean paga);
	
	public List<Cuota> getListaPorVencer(Date fecha);

}
