package promoda.dao;

import java.util.List;

import promoda.model.Materia;
import promoda.model.MateriasProfesor;

public interface DAOMateriasProfesor {
	
	public int insertar(MateriasProfesor materiasProfesor);
	
	public int update(MateriasProfesor materiasProfesor);
	
	public MateriasProfesor get(int id);
	
	public MateriasProfesor get(Materia materia, String tipo);
	
	public List<MateriasProfesor> getLista();
	
	public List<MateriasProfesor> getLista(Materia materia);

}
