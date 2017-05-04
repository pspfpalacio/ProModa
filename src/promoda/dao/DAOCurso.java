package promoda.dao;

import java.util.Date;
import java.util.List;

import promoda.model.Curso;

public interface DAOCurso {
	
	public int insertar(Curso curso);
	
	public int update(Curso curso);
	
	public Curso get(int id);
	
	public List<Curso> getLista();
	
	public List<Curso> getLista(boolean estado);
	
	public List<Curso> getListaMatVig();
	
	public List<Curso> getListaMatVig(Date fechaHoy);
	
	public List<Curso> getListaMatVig(Date fechaUno, Date fechaDos);

}
