package promoda.dao;

import java.util.List;

import promoda.model.InscripcionDia;
import promoda.model.Inscripcione;

public interface DAOInscripcionDia {
	
	public int insertar(InscripcionDia inscripcionDia);
	
	public int update(InscripcionDia inscripcionDia);
	
	public int delete(Inscripcione inscripcion);
	
	public InscripcionDia get(int id);
	
	public List<InscripcionDia> getLista();
	
	public List<Inscripcione> getLista(String dia);
	
	public List<InscripcionDia> getLista(Inscripcione inscripcione);

}
