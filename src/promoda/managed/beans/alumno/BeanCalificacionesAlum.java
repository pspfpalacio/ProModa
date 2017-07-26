package promoda.managed.beans.alumno;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import promoda.clases.Calificacion;
import promoda.clases.MatriculaAlum;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAORecurso;
import promoda.dao.DAORecursoAlumno;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.Recurso;
import promoda.model.RecursoAlumno;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanCalificacionesAlum implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(BeanCalificacionesAlum.class);
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaDAO}")
	private DAOMatricula matriculaDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
    private DAOMatriculaAlumno matriculaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
	private DAOAlumno alumnoDAO;
	
	@ManagedProperty(value = "#{BeanRecursoDAO}")
	private DAORecurso recursoDAO;
	
	@ManagedProperty(value = "#{BeanRecursoAlumnoDAO}")
	private DAORecursoAlumno alumnoRecursoDAO;
	
	private List<Curso> listaCursos;
	private List<Materia> listaMaterias;
	private List<Matricula> listaMatriculas;
	private List<Recurso> listaRecursos;
	private List<Calificacion> listaCalificaciones;
	private Usuario usuario;
	private Alumno alumno;
	private int idCurso;
	private int idMateria;
	private int idMatricula;
	
	
	public DAOCurso getCursoDAO() {
		return cursoDAO;
	}
	public void setCursoDAO(DAOCurso cursoDAO) {
		this.cursoDAO = cursoDAO;
	}
	public DAOMatricula getMatriculaDAO() {
		return matriculaDAO;
	}
	public void setMatriculaDAO(DAOMatricula matriculaDAO) {
		this.matriculaDAO = matriculaDAO;
	}
	public DAOMatriculaAlumno getMatriculaAlumnoDAO() {
		return matriculaAlumnoDAO;
	}
	public void setMatriculaAlumnoDAO(DAOMatriculaAlumno matriculaAlumnoDAO) {
		this.matriculaAlumnoDAO = matriculaAlumnoDAO;
	}
	public DAOMateria getMateriaDAO() {
		return materiaDAO;
	}
	public void setMateriaDAO(DAOMateria materiaDAO) {
		this.materiaDAO = materiaDAO;
	}
	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}
	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}
	public DAORecurso getRecursoDAO() {
		return recursoDAO;
	}
	public void setRecursoDAO(DAORecurso recursoDAO) {
		this.recursoDAO = recursoDAO;
	}
	public DAORecursoAlumno getAlumnoRecursoDAO() {
		return alumnoRecursoDAO;
	}
	public void setAlumnoRecursoDAO(DAORecursoAlumno alumnoRecursoDAO) {
		this.alumnoRecursoDAO = alumnoRecursoDAO;
	}
	public List<Curso> getListaCursos() {
		return listaCursos;
	}
	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}
	public List<Materia> getListaMaterias() {
		return listaMaterias;
	}
	public void setListaMaterias(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}
	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}
	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}
	public List<Recurso> getListaRecursos() {
		return listaRecursos;
	}
	public void setListaRecursos(List<Recurso> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}
	public List<Calificacion> getListaCalificaciones() {
		return listaCalificaciones;
	}
	public void setListaCalificaciones(List<Calificacion> listaCalificaciones) {
		this.listaCalificaciones = listaCalificaciones;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	public int getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}
	
	
	
	public String goCalificaciones(Usuario user) {
		log.info("goCalificaciones uderId: " + Integer.toString(user.getId()));
		usuario = new Usuario();
		alumno = new Alumno();
		listaCursos = new ArrayList<Curso>();
		listaMaterias = new ArrayList<Materia>();
		listaMatriculas = new ArrayList<Matricula>();
		listaRecursos = new ArrayList<Recurso>();
		listaCalificaciones = new ArrayList<Calificacion>();
		idCurso = 0;
		idMateria = 0;
		idMatricula = 0;
		alumno = user.getAlumno();
		listaCursos = matriculaAlumnoDAO.getListaCurso(alumno);
		
		return "calificaciones";
	}
	
	public void onChangeCurso() {
		try {	
			if (idCurso != 0) {	
				log.info("onChangeCurso idCurso: " + Integer.toString(idCurso));
				Curso curso = cursoDAO.get(idCurso);
				listaMaterias = curso.getMaterias();
				listaMatriculas = matriculaAlumnoDAO.getListaMatricula(alumno, curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("onChangeCurso ERROR: " + e.toString());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al buscar datos del curso, "
					+ "intente nuevamente mas tarde.", null));
		}
	}
	
	public void buscarCalificaciones() {
		try {
			if (idCurso != 0 && idMateria != 0 && idMatricula != 0) {
				listaCalificaciones = new ArrayList<Calificacion>();
				log.info("buscarCalificaciones idCurso: " + Integer.toString(idCurso) + " - idMatricula: " + Integer.toString(idMatricula) + " - idMateria: " + Integer.toString(idMateria));
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Curso curso = cursoDAO.get(idCurso);
				Materia materia = materiaDAO.get(idMateria);
				Matricula matricula = matriculaDAO.get(idMatricula);
				listaRecursos = recursoDAO.getLista(curso, matricula, materia);
				for(Recurso recurso : listaRecursos) {
					Calificacion cal = new Calificacion();
					cal.setFechaEntrega(formato.format(recurso.getFechaEntrega()));
					cal.setMateria(recurso.getMateria().getNombre());
					cal.setRecurso(recurso.getDescripcion());
					float nota = alumnoRecursoDAO.get(recurso, alumno).getCalificacion();
					if(nota != 0) {
						cal.setNota(Float.toString(nota));
					}else {
						cal.setNota("No calificado");
					}
					listaCalificaciones.add(cal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("buscarCalificaciones ERROR: " + e.toString());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al buscar calificaciones, "
					+ "intente nuevamente mas tarde.", null));
		}
	}
}
