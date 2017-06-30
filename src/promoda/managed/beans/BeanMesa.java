package promoda.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMesa;
import promoda.dao.DAOMesaAlumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Matricula;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanMesa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanMesaDAO}")
	private DAOMesa mesaDAO;
	
	@ManagedProperty(value = "#{BeanMesaAlumnoDAO}")
	private DAOMesaAlumno mesaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaDAO}")
	private DAOMatricula matriculaDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	private List<Mesa> listaMesas;
	private List<Curso> listaCursos;
	private List<Matricula> listaMatriculas;
	private List<Materia> listaMaterias;
	private Usuario usuario;
	private Mesa mesa;
	private String headerText;
	private int idCurso;
	private int idMatricula;
	private int idMateria;
	
	public DAOMesa getMesaDAO() {
		return mesaDAO;
	}
	public void setMesaDAO(DAOMesa mesaDAO) {
		this.mesaDAO = mesaDAO;
	}
	public DAOMesaAlumno getMesaAlumnoDAO() {
		return mesaAlumnoDAO;
	}
	public void setMesaAlumnoDAO(DAOMesaAlumno mesaAlumnoDAO) {
		this.mesaAlumnoDAO = mesaAlumnoDAO;
	}
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
	public DAOMateria getMateriaDAO() {
		return materiaDAO;
	}
	public void setMateriaDAO(DAOMateria materiaDAO) {
		this.materiaDAO = materiaDAO;
	}
	public List<Mesa> getListaMesas() {
		return listaMesas;
	}
	public void setListaMesas(List<Mesa> listaMesas) {
		this.listaMesas = listaMesas;
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
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
	
	public String goMesas(Usuario user) {
		try {
			listaMesas = new ArrayList<Mesa>();
			listaCursos = new ArrayList<Curso>();
			listaMatriculas = new ArrayList<Matricula>();
			listaMaterias = new ArrayList<Materia>();
			usuario = new Usuario();
			mesa = new Mesa();
			idCurso = 0;
			idMatricula = 0;
			idMateria = 0;
			usuario = user;
			listaCursos = cursoDAO.getLista(true);			
			return "mesas";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"No es posible cargar el formulario! Error: " + e.getMessage(), null));
			return "";
		}
	}
	
	public String goNuevaMesa() {
		try {
			listaCursos = new ArrayList<Curso>();
			listaMaterias = new ArrayList<Materia>();
			listaMatriculas = new ArrayList<Matricula>();	
			mesa = new Mesa();
			headerText = "Nueva mesa";
			idCurso = 0;
			idMatricula = 0;
			idMateria = 0;
			listaCursos = cursoDAO.getLista(true);
			return "mesa";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurrió un error al cargar el formulario! Error: " + e.getMessage(), null));
			return "";
		}
	}
	
	public String goEditarMesa(Mesa me) {
		try {
			listaCursos = new ArrayList<Curso>();
			listaMaterias = new ArrayList<Materia>();
			listaMatriculas = new ArrayList<Matricula>();	
			mesa = new Mesa();
			mesa = me;
			headerText = "Editar mesa";
			idCurso = me.getCurso().getId();
			idMatricula = me.getMatricula().getId();
			idMateria = me.getMateria().getId();
			listaCursos = cursoDAO.getLista(true);
			Curso cur = cursoDAO.get(idCurso);
			listaMatriculas = matriculaDAO.getLista(true, cur);
			listaMaterias = materiaDAO.getLista(true, cur);
			return "mesa";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurrió un error al cargar el formulario! Error: " + e.getMessage(), null));
			return "";
		}
	}
	
	public void onChangeCurso() {
		try {
			listaMatriculas = new ArrayList<Matricula>();
			listaMaterias = new ArrayList<Materia>();			
			idMatricula = 0;			
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
			listaMaterias = new ArrayList<Materia>();			
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
	
	public void buscar() {
		listaMesas = new ArrayList<Mesa>();
		try {
			if (idCurso == 0 && idMatricula == 0 && idMateria == 0) {
				listaMesas = mesaDAO.getLista();
			}
			if (idCurso != 0 && idMatricula == 0 && idMateria == 0) {
				Curso cur = cursoDAO.get(idCurso);
				listaMesas = mesaDAO.getLista(cur);
			}
			if (idCurso != 0 && idMatricula != 0 && idMateria == 0) {
				Curso cur = cursoDAO.get(idCurso);
				Matricula matr = matriculaDAO.get(idMatricula);
				listaMesas = mesaDAO.getLista(cur, matr);
			}
			if (idCurso != 0 && idMatricula != 0 && idMateria != 0) {
				Curso cur = cursoDAO.get(idCurso);
				Matricula matr = matriculaDAO.get(idMatricula);
				Materia mat = materiaDAO.get(idMateria);
				listaMesas = mesaDAO.getLista(cur, matr, mat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurrió un error a cargar las mesas! Error: " + e.getMessage(), null));
		}
	}
	
	public String guardar() {
		try {
			if (idCurso != 0 && idMatricula != 0 && idMateria != 0 && mesa.getFechaHoraMesa() != null && mesa.getFechaInicio() != null && mesa.getFechaFin() != null && mesa.getCosto() != 0) {
				if (mesa.getId() != 0) {
					mesa.setUsuario3(usuario);
					mesa.setFechaMod(new Date());
					if (mesaDAO.update(mesa) != 0) {
						Curso cur = cursoDAO.get(idCurso);
						Matricula matr = matriculaDAO.get(idMatricula);
						Materia mat = materiaDAO.get(idMateria);
						listaMesas = mesaDAO.getLista(cur, matr, mat);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
								"Matrícula registrada con éxito.", null));
						return "mesas";
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
								"Ocurrió un error al registrar la matrícula.", null));
						return "";
					}
				} else {
					Curso cur = cursoDAO.get(idCurso);
					Matricula matr = matriculaDAO.get(idMatricula);
					Materia mat = materiaDAO.get(idMateria);
					mesa.setCurso(cur);
					mesa.setMatricula(matr);
					mesa.setMateria(mat);
					mesa.setEnabled(true);
					mesa.setFechaAlta(new Date());
					mesa.setUsuario1(usuario);
					if (mesaDAO.insertar(mesa) != 0) {
						listaMesas = mesaDAO.getLista(cur, matr, mat);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
								"Matrícula registrada con éxito.", null));
						return "mesas";
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
								"Ocurrió un error al registrar la matrícula.", null));
						return "";
					}
				}				
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						"Error: Todos los campos son requeridos.", null));
				return "";
			}			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurrió un error al registrar la matrícula. Error: " + e.getMessage(), null));
			return "";
		}
	}
	
	public void baja(Mesa me) {
		try {
			List<MesasAlumno> listaMesasAlumnos = mesaAlumnoDAO.getLista(true, me);
			if (listaMesasAlumnos.isEmpty()) {
				me.setEnabled(false);
				me.setFechaBaja(new Date());
				me.setUsuario2(usuario);
				if (mesaDAO.update(me) != 0) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"Se registró la baja de matrícula con éxito.", null));
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
							"Ocurrió un error al registrar la baja.", null));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						"La mesa tiene alumnos inscriptos, realice la baja de estos primero.", null));
			}			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurrió un error al registrar la baja. Error: " + e.getMessage(), null));
		}
	}

}
