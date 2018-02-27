package promoda.dao;

import java.util.Date;
import java.util.List;

import promoda.model.Alumno;
import promoda.model.PagosMatricula;

public interface DAOPagosMatricula {
	
	public int insertar(PagosMatricula pagosMatricula);
	
	public int update(PagosMatricula pagosMatricula);
	
	public PagosMatricula get(int id);
	
//	public PagosMatricula get(Alumno alumno, Matricula matricula);
	
	public List<PagosMatricula> getLista();
	
	public List<PagosMatricula> getLista(boolean estado);
	
	public List<PagosMatricula> getLista(Alumno alumno);
		
	public List<PagosMatricula> getLista(boolean estado, Alumno alumno);
	
	public List<PagosMatricula> getLista(Alumno alumno, Date fechaI, Date fechaF);
	
	public List<PagosMatricula> getLista(Alumno alumno, Date fecha, String tipo);
	
	public List<PagosMatricula> getLista(Date fechaI, Date fechaF);
	
	public List<PagosMatricula> getLista(Date fecha, String tipo);

}
