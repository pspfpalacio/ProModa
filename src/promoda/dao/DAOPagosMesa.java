package promoda.dao;

import promoda.model.Alumno;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;
import promoda.model.PagosMesa;

public interface DAOPagosMesa {
	
	public int insertar(PagosMesa pagosMesa);
	
	public int update(PagosMesa pagosMesa);
	
	public PagosMesa get(MesasAlumno mesasAlumno);
	
	public PagosMesa get(Mesa mesa, Alumno alumno);//Estado en true

}
