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

import promoda.clases.AsistenciaReporte;
import promoda.clases.Clase;
import promoda.clases.MatriculaAlum;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOAsistencia;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.model.Alumno;
import promoda.model.Asistencia;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Matricula;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanAsistenciaAlum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(BeanAsistenciaAlum.class);
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaDAO}")
	private DAOMatricula matriculaDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
    private DAOMatriculaAlumno matriculaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanAsistenciaDAO}")
	private DAOAsistencia asistenciaDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
	private DAOAlumno alumnoDAO;
	
	private List<Curso> listaCursos;
	private List<Materia> listaMaterias;
	private List<Clase> listaClases;
	private List<Asistencia> listaAsistencias;
	private List<AsistenciaReporte> listaAsistenciaReporte;
	private List<MatriculaAlum> listaMatriculaAlums;
	private Usuario usuario;
	private Alumno alumno;
	private MatriculaAlum matriculaAlum;
	private int idCurso;
	private int idMateria;
	private int idMatricula;
	private float porcentaje;
	private boolean verPeriodo;

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

	public DAOAsistencia getAsistenciaDAO() {
		return asistenciaDAO;
	}

	public void setAsistenciaDAO(DAOAsistencia asistenciaDAO) {
		this.asistenciaDAO = asistenciaDAO;
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

	public List<Clase> getListaClases() {
		return listaClases;
	}

	public void setListaClases(List<Clase> listaClases) {
		this.listaClases = listaClases;
	}

	public List<Asistencia> getListaAsistencias() {
		return listaAsistencias;
	}

	public void setListaAsistencias(List<Asistencia> listaAsistencias) {
		this.listaAsistencias = listaAsistencias;
	}

	public List<AsistenciaReporte> getListaAsistenciaReporte() {
		return listaAsistenciaReporte;
	}

	public void setListaAsistenciaReporte(
			List<AsistenciaReporte> listaAsistenciaReporte) {
		this.listaAsistenciaReporte = listaAsistenciaReporte;
	}

	public List<MatriculaAlum> getListaMatriculaAlums() {
		return listaMatriculaAlums;
	}

	public void setListaMatriculaAlums(List<MatriculaAlum> listaMatriculaAlums) {
		this.listaMatriculaAlums = listaMatriculaAlums;
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

	public MatriculaAlum getMatriculaAlum() {
		return matriculaAlum;
	}

	public void setMatriculaAlum(MatriculaAlum matriculaAlum) {
		this.matriculaAlum = matriculaAlum;
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

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public boolean isVerPeriodo() {
		return verPeriodo;
	}

	public void setVerPeriodo(boolean verPeriodo) {
		this.verPeriodo = verPeriodo;
	}

	public String goAsistencia(Usuario user) {
		log.info("Intento redireccionar al formulario de asistencias - Usuario id: " + user.getId() + " username: " + user.getUsername());
		try {			
			usuario = new Usuario();
			alumno = new Alumno();
			listaCursos = new ArrayList<Curso>();
			listaMaterias = new ArrayList<Materia>();
			listaMatriculaAlums = new ArrayList<MatriculaAlum>();
			listaAsistencias = new ArrayList<Asistencia>();
			listaClases = new ArrayList<Clase>();
			idCurso = 0;
			idMateria = 0;
			porcentaje = 0;
			verPeriodo = false;
			matriculaAlum = new MatriculaAlum();
			usuario = user;
			alumno = user.getAlumno();
			listaCursos = matriculaAlumnoDAO.getListaCurso(alumno);
			return "asistenciaAlumno";
		} catch (Exception e) {
			log.error("Ocurrio un error al redireccionar a asistencias. Error: " + e);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE CARGAR EL FORMULARIO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public void onChangeCurso() {
		log.info("onChangeCurso idCurso: " + idCurso);
		try {
			listaAsistencias = new ArrayList<Asistencia>();
			listaMatriculaAlums = new ArrayList<MatriculaAlum>();
			listaMaterias = new ArrayList<Materia>();			
			verPeriodo = false;		
			idMatricula = 0;
			idMateria = 0;	
			porcentaje = 0;			
			if (idCurso != 0) {
				Curso cur = cursoDAO.get(idCurso);
				List<Matricula> listaMat = matriculaAlumnoDAO.getListaMatricula(alumno, cur);
				listaMaterias = materiaDAO.getLista(true, cur);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				if (listaMat.size() == 1) {
					for (Matricula mat : listaMat) {
						matriculaAlum = new MatriculaAlum();						
						String descripcion = dateFormat.format(mat.getFechaInicio()) + " - " + dateFormat.format(mat.getFechaFinCursado());
						matriculaAlum.setId(mat.getId());
						matriculaAlum.setDescripcion(descripcion);		
						idMatricula = mat.getId();
					}
					verPeriodo = true;					
				} else {
					verPeriodo = false;
					for (Matricula mat : listaMat) {
						MatriculaAlum matrAlum = new MatriculaAlum();						
						String descripcion = dateFormat.format(mat.getFechaInicio()) + " - " + dateFormat.format(mat.getFechaFinCursado());
						matrAlum.setId(mat.getId());
						matrAlum.setDescripcion(descripcion);
						listaMatriculaAlums.add(matrAlum);
					}
				}				
			}
		} catch (Exception e) {
			log.error("Ocurrio un error en onChangeCurso. Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurri� un error al generar los periodos para el curso seleccionado, "
					+ "intent� nuevamente m�s tarde.", null));
		}
	}
	
	public void onCompleteAsistencia() {
		log.info("onCompleteAsistencia idCurso: " + idCurso + " idMatricula: " + idMatricula + " idMateria: " + idMateria);
		listaClases = new ArrayList<Clase>();
		listaAsistencias = new ArrayList<Asistencia>();
		Materia materia = new Materia();
		Matricula matricula = new Matricula();		
		Curso curso = new Curso();		
		porcentaje = 0;
		if (idMateria != 0) {
			curso = cursoDAO.get(idCurso);
			materia = materiaDAO.get(idMateria);
			int cClases = materia.getCantClases();
			for (int i = 1; i <= cClases; i++) {
				Clase clase = new Clase();
				clase.setId(i);
				clase.setNombre("Clase " + i);
				listaClases.add(clase);
			}
			matricula = matriculaDAO.get(idMatricula);
			
			listaAsistencias = asistenciaDAO.getLista(curso, matricula, materia, alumno);			
			if (listaAsistencias.isEmpty()) {
				porcentaje = 0;
				List<Asistencia> listAux = new ArrayList<Asistencia>();
				for (Clase clas : listaClases) {
					boolean noExiste = true;
					for (Asistencia asistencia : listaAsistencias) {						
						if (asistencia.getNroClase() == clas.getId()) {
							noExiste = false;
						}
					}
					if (noExiste) {
						Asistencia asist = new Asistencia();
						asist.setAlumno(alumno);
						asist.setCurso(curso);
						asist.setMateria(materia);
						asist.setMatricula(matricula);
						asist.setNombreClase("Clase " + clas.getId());
						asist.setNroClase(clas.getId());
						asist.setPresente(" - ");
						asist.setUsuario(usuario);
						listAux.add(asist);
					}
				}
				for (Asistencia asistencia : listAux) {
					listaAsistencias.add(asistencia);
				}
			} else {
				float cantPresente = 0;
				float cantAsistencia = listaAsistencias.size();
				for (Asistencia asistencia : listaAsistencias) {
					String presente = "";
					if (asistencia.getPresente() != null) {
						presente = asistencia.getPresente().toUpperCase();
					}
					if (presente.equals("P")) {
						cantPresente = cantPresente + 1;
					}
				}
				float decimalAsist = cantPresente/cantAsistencia;
				porcentaje = decimalAsist * 100;
				List<Asistencia> listAux = new ArrayList<Asistencia>();
				for (Clase clas : listaClases) {
					boolean noExiste = true;
					for (Asistencia asistencia : listaAsistencias) {						
						if (asistencia.getNroClase() == clas.getId()) {
							noExiste = false;
						}
					}
					if (noExiste) {
						Asistencia asist = new Asistencia();
						asist.setAlumno(alumno);
						asist.setCurso(curso);
						asist.setMateria(materia);
						asist.setMatricula(matricula);
						asist.setNombreClase("Clase " + clas.getId());
						asist.setNroClase(clas.getId());
						asist.setPresente(" - ");
						asist.setUsuario(usuario);
						listAux.add(asist);
					}
				}
				for (Asistencia asistencia : listAux) {
					listaAsistencias.add(asistencia);
				}
			}			
			
		}		
	}	

}
