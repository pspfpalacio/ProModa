package promoda.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import promoda.dao.DAOAlumno;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMateriasCalificacion;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.MateriasCalificacion;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanCalificarAlumno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaDAO}")
	private DAOMatricula matriculaDAO;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
	private DAOAlumno alumnoDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
	private DAOMatriculaAlumno matriculaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanMateriasCalificacionDAO}")
	private DAOMateriasCalificacion materiaCalificacionDAO;
	
	private List<Curso> listaCursos;
	private List<Matricula> listaMatriculas;
	private List<Materia> listaMaterias;
	private List<MateriasCalificacion> listaCalificaAlumno;
	private List<MateriasCalificacion> selectionCalificaAlumno;
	private Usuario usuario;
	private String estado;
	private int idCurso;
	private int idMatricula;
	private int idMateria;
	private float calificacion;	

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

	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}

	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}

	public DAOMateria getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(DAOMateria materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public DAOMatriculaAlumno getMatriculaAlumnoDAO() {
		return matriculaAlumnoDAO;
	}

	public void setMatriculaAlumnoDAO(DAOMatriculaAlumno matriculaAlumnoDAO) {
		this.matriculaAlumnoDAO = matriculaAlumnoDAO;
	}

	public DAOMateriasCalificacion getMateriaCalificacionDAO() {
		return materiaCalificacionDAO;
	}

	public void setMateriaCalificacionDAO(
			DAOMateriasCalificacion materiaCalificacionDAO) {
		this.materiaCalificacionDAO = materiaCalificacionDAO;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public List<Materia> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}

	public List<MateriasCalificacion> getListaCalificaAlumno() {
		return listaCalificaAlumno;
	}

	public void setListaCalificaAlumno(List<MateriasCalificacion> listaCalificaAlumno) {
		this.listaCalificaAlumno = listaCalificaAlumno;
	}

	public List<MateriasCalificacion> getSelectionCalificaAlumno() {
		return selectionCalificaAlumno;
	}

	public void setSelectionCalificaAlumno(
			List<MateriasCalificacion> selectionCalificaAlumno) {
		this.selectionCalificaAlumno = selectionCalificaAlumno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}
	
	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String goCalificarAlumnos(Usuario user) {
		try {
			listaCursos = new ArrayList<Curso>();
			listaMatriculas = new ArrayList<Matricula>();
			listaMaterias = new ArrayList<Materia>();
			listaCalificaAlumno = new ArrayList<MateriasCalificacion>();
			selectionCalificaAlumno = new ArrayList<MateriasCalificacion>();
			listaCursos = cursoDAO.getLista(true);
			usuario = new Usuario();
			usuario = user;
			estado = "";
			idCurso = 0;
			idMatricula = 0;
			idMateria = 0;
			calificacion = 0;
			return "calificarAlumnos";
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REDIRIGIR AL FORMULARIO", null));
			return "";
		}
	}
	
	public void onChangeCurso() {
		try {
			listaCalificaAlumno = new ArrayList<MateriasCalificacion>();
			selectionCalificaAlumno = new ArrayList<MateriasCalificacion>();
			listaMatriculas = new ArrayList<Matricula>();
			listaMaterias = new ArrayList<Materia>();
			estado = "";
			idMatricula = 0;
			calificacion = 0;
			idMateria = 0;
			if (idCurso != 0) {
				Curso cur = cursoDAO.get(idCurso);
				listaMatriculas = matriculaDAO.getLista(true, cur);
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LAS MATRICULAS", null));
		}
	}
	
	public void onChangeMatricula() {
		try {
			listaCalificaAlumno = new ArrayList<MateriasCalificacion>();
			selectionCalificaAlumno = new ArrayList<MateriasCalificacion>();
			listaMaterias = new ArrayList<Materia>();
			estado = "";
			calificacion = 0;
			idMateria = 0;
			if (idMatricula != 0 && idCurso != 0) {				
				Curso cur = cursoDAO.get(idCurso);
				listaMaterias = materiaDAO.getLista(true, cur);
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LAS MATERIAS", null));
		}
	}
	
	public void onChangeMateria() {
		try {
			listaCalificaAlumno = new ArrayList<MateriasCalificacion>();
			selectionCalificaAlumno = new ArrayList<MateriasCalificacion>();
			estado = "";
			calificacion = 0;
			if (idCurso != 0 && idMatricula != 0 && idMateria != 0) {
				Curso cur = cursoDAO.get(idCurso);
				Matricula matr = matriculaDAO.get(idMatricula);
				Materia mat = materiaDAO.get(idMateria);
				List<MateriasCalificacion> listAuxCalifica = new ArrayList<MateriasCalificacion>();
				List<MatriculaAlumno> listAuxMatriculaAlumno = new ArrayList<MatriculaAlumno>();
				
				listAuxCalifica = materiaCalificacionDAO.getLista(cur, matr, mat);
				if (listAuxCalifica.isEmpty()) {
					listAuxMatriculaAlumno = matriculaAlumnoDAO.getLista(matr);
					for (MatriculaAlumno matriculaAlumno : listAuxMatriculaAlumno) {
						MateriasCalificacion matCalificacion = new MateriasCalificacion();
						matCalificacion.setAlumno(matriculaAlumno.getAlumno());
						matCalificacion.setCurso(cur);
						matCalificacion.setEstado("");
						matCalificacion.setMatricula(matr);
						listAuxCalifica.add(matCalificacion);
					}
				} else {
					List<MateriasCalificacion> listaAux = listAuxCalifica;
					listAuxMatriculaAlumno = matriculaAlumnoDAO.getLista(matr);
					for (MatriculaAlumno matriculaAlumno : listAuxMatriculaAlumno) {
						MateriasCalificacion matCalificacion = new MateriasCalificacion();
						boolean noExiste = true;
						for (MateriasCalificacion materiasCalificacion : listaAux) {
							if (matriculaAlumno.getAlumno().getId() == materiasCalificacion.getAlumno().getId()) {
								noExiste = false;
							}
						}
						if (noExiste) {
							matCalificacion.setAlumno(matriculaAlumno.getAlumno());
							matCalificacion.setCurso(cur);
							matCalificacion.setEstado("");
							matCalificacion.setMatricula(matr);
							listAuxCalifica.add(matCalificacion);
						}
					}
				}
				
				Collections.sort(listAuxCalifica, new Comparator<MateriasCalificacion>() {
					public int compare(MateriasCalificacion obj1, MateriasCalificacion obj2) {
						return obj1.getAlumno().getNombreCompleto().compareTo(obj2.getAlumno().getNombreCompleto());
					}
				});
				
				listaCalificaAlumno = listAuxCalifica;
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LOS ALUMNOS", null));
		}
	}
	
	public void actualizacionMasiva() {
    	System.out.println("actualizacionMasiva() - " + calificacion + " - " + estado);
    	if (!selectionCalificaAlumno.isEmpty()) {
    		List<MateriasCalificacion> listaSeleccion = new ArrayList<MateriasCalificacion>();
    		List<MateriasCalificacion> listaCalificaAnterior = new ArrayList<MateriasCalificacion>();
        	listaSeleccion = selectionCalificaAlumno;
        	listaCalificaAnterior = listaCalificaAlumno;
        	selectionCalificaAlumno = new ArrayList<MateriasCalificacion>();
        	listaCalificaAlumno = new ArrayList<MateriasCalificacion>();
        	for (MateriasCalificacion calificaAnterior : listaCalificaAnterior) {
				for (MateriasCalificacion seleccionCalifica : listaSeleccion) {
					seleccionCalifica.setCalificacion(calificacion);
					seleccionCalifica.setEstado(estado);
					selectionCalificaAlumno.add(seleccionCalifica);
	    			if (calificaAnterior.getAlumno().getDni() == seleccionCalifica.getAlumno().getDni()) {
	    				calificaAnterior.setCalificacion(calificacion);
	    				calificaAnterior.setEstado(estado);
	    			}	    			
	    		}				
				listaCalificaAlumno.add(calificaAnterior);
			}
        	
    	} else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Atención! Debe seleccionar al menos un item.", null));
    	}
    }
	
	public void onRowEdit(MateriasCalificacion califica) {
        List<MateriasCalificacion> listAux = listaCalificaAlumno;
        int idAlum1 = califica.getAlumno().getId();
        listaCalificaAlumno = new ArrayList<MateriasCalificacion>();
        for (MateriasCalificacion califica2 : listAux) {
			int idAlum2 = califica2.getAlumno().getId();
			if (idAlum1 == idAlum2) {
				califica2.setCalificacion(califica.getCalificacion());
				califica2.setEstado(califica.getEstado());
			}
			listaCalificaAlumno.add(califica2);
		}
    }
	
	public void guardarCambios() {
    	FacesMessage msg = null;
    	if (!listaCalificaAlumno.isEmpty()) {
    		boolean inserto = true;
    		Curso cur = cursoDAO.get(idCurso);
    		Materia mat = materiaDAO.get(idMateria);
    		Matricula matr = matriculaDAO.get(idMatricula);
    		for (MateriasCalificacion calificaAlumno : listaCalificaAlumno) {
				if (calificaAlumno.getId() != 0) {
					calificaAlumno.setCurso(cur);
					calificaAlumno.setMateria(mat);
					calificaAlumno.setMatricula(matr);
					calificaAlumno.setFechaMod(new Date());
					calificaAlumno.setUsuario3(usuario);
					int idCalifica = materiaCalificacionDAO.update(calificaAlumno);
					if (idCalifica == 0) {
						inserto = false;
					}
				} else {
					calificaAlumno.setCurso(cur);
					calificaAlumno.setMateria(mat);
					calificaAlumno.setMatricula(matr);
					calificaAlumno.setEnabled(true);
					calificaAlumno.setFechaAlta(new Date());
					calificaAlumno.setUsuario1(usuario);
					int idCalifica = materiaCalificacionDAO.insertar(calificaAlumno);
					if (idCalifica == 0) {
						inserto = false;
					}
				}
			}
    		if (inserto) {
    			listaCalificaAlumno = new ArrayList<MateriasCalificacion>();
    			idMateria = 0;
    			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "CALIFICACION REGISTRADA!", null);
    		} else {
    			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LAS CALIFICACIONES", null);
    		}
    	} else {
    		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "DEBEN EXISTIR ALUMNOS INSCRIPTOS PARA CARGAR CALIFICACIONES", null);
    	}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
