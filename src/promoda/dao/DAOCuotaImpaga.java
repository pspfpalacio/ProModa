package promoda.dao;

import java.util.List;

import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.CuotaImpaga;
import promoda.model.Curso;
import promoda.model.Matricula;

public interface DAOCuotaImpaga {
	
	public int insertar(CuotaImpaga cuotaImpaga);
	
	public int update(CuotaImpaga cuotaImpaga);
	
	public int delete(CuotaImpaga cuotaImpaga);
	
	public CuotaImpaga get(int id);
	
	public CuotaImpaga get(Cuota cuota);
	
	public List<CuotaImpaga> getLista();
	
	public List<CuotaImpaga> getLista(Alumno alumno);
	
	public List<CuotaImpaga> getLista(Matricula matricula);
	
	public List<CuotaImpaga> getLista(Curso curso);
	
	public List<CuotaImpaga> getLista(Alumno alumno, Matricula matricula);
	
	public List<CuotaImpaga> getLista(Curso curso, Matricula matricula);
	
	public List<CuotaImpaga> getLista(Alumno alumno, Curso curso);
	
	public List<CuotaImpaga> getLista(Alumno alumno, Curso curso, Matricula matricula);

}
