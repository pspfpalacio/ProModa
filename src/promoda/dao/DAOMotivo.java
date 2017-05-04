package promoda.dao;

import java.util.List;

import promoda.model.Motivo;

public interface DAOMotivo {
	
	public int insertar(Motivo motivo);
	
	public int update(Motivo motivo);
	
	public Motivo get(int id);
	
	public List<Motivo> getLista();

}
