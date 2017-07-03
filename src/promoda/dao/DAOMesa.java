package promoda.dao;

import java.util.Date;
import java.util.List;

import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Matricula;
import promoda.model.Mesa;

public interface DAOMesa {
	
	public int insertar(Mesa mesa);
	
	public int update(Mesa mesa);
	
	public Mesa get(int id);	
	
	public List<Mesa> getLista();
	
	public List<Mesa> getLista(Curso curso);
	
	public List<Mesa> getLista(Curso curso, Matricula matricula);
	
	public List<Mesa> getLista(Curso curso, Matricula matricula, Materia materia);	
	
	public List<Mesa> getListaBetweenFecha(Curso curso, Matricula matricula, Materia materia, Date fecha);

}
