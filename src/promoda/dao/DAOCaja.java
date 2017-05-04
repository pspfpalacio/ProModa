package promoda.dao;

import java.util.Date;
import java.util.List;

import promoda.model.Caja;

public interface DAOCaja {
	
	public int insertar(Caja caja);
	
	public int update(Caja caja);
	
	public Caja get(int id);
	
	public Caja get(int idMovimiento, String tipoMovimiento);
	//Las listas poseen estado = true
	public List<Caja> getLista();
	
	public List<Caja> getLista(Date fecha);
	
	public List<Caja> getLista(Date fechaInicio, Date fechaFin);
	
	public List<Caja> getLista(String tipo);
	
	public List<Caja> getLista(Date fechaInicio, Date fechaFin, String tipo);
	
	public List<Caja> getListaOrdenada();
	
	public List<Caja> getListaOrdenada(Date fechaInicio);

}
