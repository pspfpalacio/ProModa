package promoda.managed.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import promoda.dao.DAOAlumno;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMatricula;
import promoda.dao.DAORecurso;
import promoda.dao.DAORecursoAlumno;
import promoda.dao.DAOUsuario;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Matricula;
import promoda.model.Recurso;
import promoda.model.RecursoAlumno;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanRecurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanRecursoDAO}")
	private DAORecurso recursoDAO;
	
	@ManagedProperty(value = "#{BeanRecursoAlumnoDAO}")
	private DAORecursoAlumno recursoAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaDAO}")
	private DAOMatricula matriculaDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
	private DAOAlumno alumnoDAO;
	
	private List<Recurso> listaRecursos;
	private List<RecursoAlumno> listaRecursoAlumnos;
	private List<Curso> listaCursos;
	private List<Materia> listaMaterias;
	private Recurso recurso;
	private RecursoAlumno recursoAlumno;
	private Usuario usuario;
	private Curso curso;
	private Materia materia;
	private Matricula matricula;
	private Date fechaEntrega;
	private String headerText;
	private String pagina;
	private String descripcion;
	private int idCurso;
	private int idMateria;
	private int idEstado;
	private int idRecurso;
	private boolean edit;

	public DAORecurso getRecursoDAO() {
		return recursoDAO;
	}

	public void setRecursoDAO(DAORecurso recursoDAO) {
		this.recursoDAO = recursoDAO;
	}

	public DAORecursoAlumno getRecursoAlumnoDAO() {
		return recursoAlumnoDAO;
	}

	public void setRecursoAlumnoDAO(DAORecursoAlumno recursoAlumnoDAO) {
		this.recursoAlumnoDAO = recursoAlumnoDAO;
	}

	public DAOCurso getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(DAOCurso cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
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

	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}

	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}

	public List<Recurso> getListaRecursos() {
		return listaRecursos;
	}

	public void setListaRecursos(List<Recurso> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}

	public List<RecursoAlumno> getListaRecursoAlumnos() {
		return listaRecursoAlumnos;
	}

	public void setListaRecursoAlumnos(List<RecursoAlumno> listaRecursoAlumnos) {
		this.listaRecursoAlumnos = listaRecursoAlumnos;
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

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public RecursoAlumno getRecursoAlumno() {
		return recursoAlumno;
	}

	public void setRecursoAlumno(RecursoAlumno recursoAlumno) {
		this.recursoAlumno = recursoAlumno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	public int getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String goRecurso(Usuario user) {
		headerText = "Recursos - Nuevo Recurso";
		listaCursos = new ArrayList<Curso>();
		listaMaterias = new ArrayList<Materia>();
		listaRecursos = new ArrayList<Recurso>();
		usuario = new Usuario();
		idCurso = 0;
		idMateria = 0;
		descripcion = "";
		fechaEntrega = null;
		edit = false;
		usuario = user;
		listaCursos = cursoDAO.getLista(true);
		return "recurso"; 
	}
	
	public String goRecursos(Usuario user) {
		headerText = "Recursos - Seleccione opciones para listar";
		listaCursos = new ArrayList<Curso>();
		listaMaterias = new ArrayList<Materia>();
		listaRecursos = new ArrayList<Recurso>();
		usuario = new Usuario();
		idCurso = 0;
		idMateria = 0;
		idEstado = 0;
		usuario = user;
		listaCursos = cursoDAO.getLista(true);
		return "recursos";
	}
	
	public String goCalificar(Recurso rec, String pag) {
		try {
			listaRecursoAlumnos = new ArrayList<RecursoAlumno>();
			recurso = new Recurso();
			headerText = "Calificaciones";
			recurso = rec;
			Matricula mat = rec.getMatricula();
			List<RecursoAlumno> listAux = recursoAlumnoDAO.getLista(rec);
			List<Alumno> listAlumnos = alumnoDAO.getLista(mat);
			if (!listAlumnos.isEmpty()) {
				for (Alumno alumno : listAlumnos) {
					RecursoAlumno recAlumno = new RecursoAlumno();
					int dniAlumno = alumno.getDni();
					boolean noExiste = true;
					for (RecursoAlumno recAlumno2 : listAux) {
						if (dniAlumno == recAlumno2.getAlumno().getDni()) {
							noExiste = false;
							recAlumno = recAlumno2;
						}					
					}
					if (noExiste) {
						recAlumno.setRecurso(rec);
						recAlumno.setAlumno(alumno);
					}
					listaRecursoAlumnos.add(recAlumno);
				}
				pagina = pag;
				return "calificaciones";
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existen Alumnos inscriptos para esa Matricula!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error, no se pudo cargar el formulario!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public void onChangeCurso() {
		listaMaterias = new ArrayList<Materia>();
		listaRecursos = new ArrayList<Recurso>();
		idMateria = 0;
		idEstado = 0;	
		edit = false;
		if (idCurso != 0) {			
			curso = new Curso();
			curso = cursoDAO.get(idCurso);
			if (curso.getMatricula() != null) {
				listaMaterias = materiaDAO.getLista(true, curso);
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible obtener las materias relacionadas, El curso no posee Matrícula Vigente!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public void onChangeMateria() {
		listaRecursos = new ArrayList<Recurso>();
		edit = false;
		if (idMateria != 0) {
			materia = materiaDAO.get(idMateria);
			matricula = matriculaDAO.get(curso.getMatricula().getId());
			listaRecursos = recursoDAO.getLista(materia, matricula);
		}
	}
	
	public void onRowEdit(RecursoAlumno recAlumno) {
        List<RecursoAlumno> listAux = listaRecursoAlumnos;
        int dniAlum1 = recAlumno.getAlumno().getDni();
        listaRecursoAlumnos = new ArrayList<RecursoAlumno>();
        for (RecursoAlumno rAlumno : listAux) {
			int dniAlum2 = rAlumno.getAlumno().getDni();
			if (dniAlum1 == dniAlum2) {
				rAlumno.setCalificacion(recAlumno.getCalificacion());
				rAlumno.setFechaAlta(new Date());
			}
			listaRecursoAlumnos.add(rAlumno);
		}
    }
	
	public void onEditar(Recurso rec) {
		headerText = "Recursos - Editar Recurso";
		idRecurso = rec.getId();
		idCurso = rec.getCurso().getId();
		idMateria = rec.getMateria().getId();
		edit = true;
		fechaEntrega = rec.getFechaEntrega();
		descripcion = rec.getDescripcion();
	}
	
	public void listar() {
		FacesMessage msg = null;
		listaRecursos = new ArrayList<Recurso>();
		if (idCurso != 0 && idMateria != 0) {
			curso = cursoDAO.get(idCurso);
			materia = materiaDAO.get(idMateria);
			matricula = curso.getMatricula();
			String message = "";
			if (idEstado == 0) { //Todos
				listaRecursos = recursoDAO.getLista(materia);
				message = "Todos los Recursos";
			}
			if (idEstado == 1) { //Vigentes
				listaRecursos = recursoDAO.getLista(materia, matricula);
				message = "Recursos Vigentes";
			}
			if (idEstado == 2) { //Históricos
				listaRecursos = recursoDAO.getListaHistoricos(materia, matricula);
				message = "Recursos Históricos";
			}
			if (!listaRecursos.isEmpty()) {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen Recursos "
						+ "para los filtros seleccionados", null);
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar un Curso y una Materia", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void baja(Recurso rec) {
		listaRecursos = new ArrayList<Recurso>();
		FacesMessage msg = null;
		rec.setEstado(false);
		rec.setFechaBaja(new Date());
		rec.setUsuario2(usuario);
		int updtRecurso = recursoDAO.update(rec);
		if (updtRecurso != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Recurso!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de baja el Recurso!", null);
		}
		listaRecursos = recursoDAO.getLista(materia, matricula);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String volver() {
		if (pagina.equals("recurso")) {
			headerText = "Recursos - Nuevo Recurso";
			descripcion = "";
			fechaEntrega = null;
			edit = false;
		} else {
			headerText = "Recursos - Seleccione opciones para listar";			
		}
		return pagina;
	}
	
	public void cancelar() {
		headerText = "Recursos - Nuevo Recurso";
		edit = false;
		fechaEntrega = null;
		descripcion = "";		
	}
	
	public void calificar() {
		FacesMessage msg = null;
		try {
			for (RecursoAlumno rAlumno : listaRecursoAlumnos) {
				if (rAlumno.getId() != 0) {
					rAlumno.setFechaAlta(new Date());
					rAlumno.setUsuario(usuario);
					recursoAlumnoDAO.update(rAlumno);
				} else {
					rAlumno.setFechaAlta(new Date());
					rAlumno.setUsuario(usuario);
					recursoAlumnoDAO.insertar(rAlumno);
				}
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambios registrados con éxito!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocurrió un error al registrar los cambios! Inténtelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void guardar() {
		FacesMessage msg = null;
		try {
			if (idCurso != 0 && idMateria != 0 && fechaEntrega != null && !descripcion.isEmpty()) {
				Recurso rec = new Recurso();
				curso = cursoDAO.get(idCurso);
				materia = materiaDAO.get(idMateria);
				matricula = matriculaDAO.get(curso.getMatricula().getId());
				rec.setCurso(curso);
				rec.setDescripcion(descripcion);
				rec.setEstado(true);
				rec.setFechaAlta(new Date());
				rec.setFechaEntrega(fechaEntrega);
				rec.setMateria(materia);
				rec.setMatricula(matricula);
				rec.setUsuario1(usuario);
				int insRecurso = recursoDAO.insertar(rec);
				if (insRecurso != 0) {
					listaRecursos = new ArrayList<Recurso>();
					listaRecursos = recursoDAO.getLista(materia, matricula);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Recurso se Registró!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al registrar el Recurso!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar un Curso, una Materia, colocar una Descripción y una Fecha de Entrega!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al registrar el Recurso! Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void editar() {
		FacesMessage msg = null;
		try {
			if (idCurso != 0 && idMateria != 0 && fechaEntrega != null && !descripcion.isEmpty()) {
				Recurso rec = new Recurso();
				curso = cursoDAO.get(idCurso);
				materia = materiaDAO.get(idMateria);
				matricula = matriculaDAO.get(curso.getMatricula().getId());
				rec.setId(idRecurso);
				rec.setCurso(curso);
				rec.setDescripcion(descripcion);
				rec.setEstado(true);
				rec.setFechaMod(new Date());
				rec.setFechaEntrega(fechaEntrega);
				rec.setMateria(materia);
				rec.setMatricula(matricula);
				rec.setUsuario3(usuario);
				int updRecurso = recursoDAO.update(rec);
				if (updRecurso != 0) {
					listaRecursos = new ArrayList<Recurso>();
					listaRecursos = recursoDAO.getLista(materia, matricula);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Recurso se Registró!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al registrar el Recurso!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar una Descripción y una Fecha de Entrega!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al registrar el Recurso! Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-recursos.jpg";
         
        pdf.add(Image.getInstance(logo));
    }
	
	public void preProcessCalificacionesPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-calificacion.jpg";
         
        pdf.add(Image.getInstance(logo));
    }
	
	public void generarReporte() {
		
	}

}
