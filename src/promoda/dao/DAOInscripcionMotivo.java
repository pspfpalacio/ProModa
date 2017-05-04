package promoda.dao;

import java.util.List;

import promoda.model.InscripcionMotivo;
import promoda.model.Inscripcione;
import promoda.model.Motivo;

public interface DAOInscripcionMotivo {
	
	public int insertar(InscripcionMotivo inscripcionMotivo);
	
	public int update(InscripcionMotivo inscripcionMotivo);
	
	public InscripcionMotivo get(int id);
	
	public List<InscripcionMotivo> getLista();
	
	public List<Inscripcione> getLista(Motivo motivo);
	
	public List<Motivo> getLista(Inscripcione inscripcione);

}
