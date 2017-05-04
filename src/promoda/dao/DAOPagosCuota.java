package promoda.dao;

import java.util.Date;
import java.util.List;

import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.PagosCuota;

public interface DAOPagosCuota {
	
	public int insertar(PagosCuota pagosCuota);
	
	public int update(PagosCuota pagosCuota);
	
	public PagosCuota get(int id);
	
	public PagosCuota get(Alumno alumno, Cuota cuota);
	
	public List<PagosCuota> getLista();
	
	public List<PagosCuota> getLista(boolean estado);
	
	public List<PagosCuota> getLista(Alumno alumno);
		
	public List<PagosCuota> getLista(boolean estado, Alumno alumno);	
	
	public List<PagosCuota> getLista(Alumno alumno, Date fechaI, Date fechaF);
	
	public List<PagosCuota> getLista(Alumno alumno, Date fecha, String tipo);
	
    public List<PagosCuota> getLista(Date fechaI, Date fechaF);
	
	public List<PagosCuota> getLista(Date fecha, String tipo);

}
