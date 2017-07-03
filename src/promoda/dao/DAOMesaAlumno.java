package promoda.dao;

import java.util.List;

import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;

public interface DAOMesaAlumno {
	
	public int insertar(MesasAlumno mesasAlumno);
	
	public int update(MesasAlumno mesasAlumno);
	
	public MesasAlumno get(int id);
	
	public List<MesasAlumno> getLista();
	
	public List<MesasAlumno> getLista(boolean estado);
	
	public List<MesasAlumno> getLista(Mesa mesa);
	
	public List<MesasAlumno> getLista(Alumno alumno);
	
	public List<MesasAlumno> getLista(Alumno alumno, Mesa mesa);
	
	public List<MesasAlumno> getLista(boolean estado, Mesa mesa);
	
	public List<MesasAlumno> getLista(boolean estado, Alumno alumno);
			
	public List<MesasAlumno> getLista(boolean estado, Alumno alumno, Mesa mesa);
	
	public List<MesasAlumno> getLista(boolean estado, Alumno alumno, Curso curso, Materia materia);
	
	public List<MesasAlumno> getListaOrderByAlumno(boolean estado, Mesa mesa);

}
