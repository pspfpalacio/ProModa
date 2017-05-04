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

import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOProfesor;
import promoda.dao.DAOUsuario;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Profesore;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanMateria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanProfesorDAO}")
	private DAOProfesor profesorDAO;
	
	private List<Materia> listaMaterias;
	private List<Materia> filteredMaterias;
	private List<Curso> listaCursos;
	private List<Profesore> listaProfesores;
	private Materia materia;
	private Usuario usuario;
	private String headerText;
	private int idCurso;
	private int idTitular;
	private int idSuplente;

	public DAOMateria getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(DAOMateria materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public DAOCurso getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(DAOCurso cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public DAOProfesor getProfesorDAO() {
		return profesorDAO;
	}

	public void setProfesorDAO(DAOProfesor profesorDAO) {
		this.profesorDAO = profesorDAO;
	}

	public List<Materia> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}

	public List<Materia> getFilteredMaterias() {
		return filteredMaterias;
	}

	public void setFilteredMaterias(List<Materia> filteredMaterias) {
		this.filteredMaterias = filteredMaterias;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Profesore> getListaProfesores() {
		return listaProfesores;
	}

	public void setListaProfesores(List<Profesore> listaProfesores) {
		this.listaProfesores = listaProfesores;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public int getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(int idTitular) {
		this.idTitular = idTitular;
	}

	public int getIdSuplente() {
		return idSuplente;
	}

	public void setIdSuplente(int idSuplente) {
		this.idSuplente = idSuplente;
	}

	public String goMaterias(Usuario user) {
		usuario = new Usuario();
		usuario = user;
		listaMaterias = new ArrayList<Materia>();
		filteredMaterias = new ArrayList<Materia>();
		listaCursos = new ArrayList<Curso>();
		listaProfesores = new ArrayList<Profesore>();
		listaMaterias = materiaDAO.getLista(true);
		filteredMaterias = listaMaterias;
		return "materias";
	}
	
	public String goNuevo() {
		headerText = "Nueva Materia";
		materia = new Materia();
		idCurso = 0;
		idTitular = 0;
		idSuplente = 0;
		listaCursos = cursoDAO.getLista(true);
		listaProfesores = profesorDAO.getLista(true);
		return "materia";
	}
	
	public String goEditar(Materia mat) {
		headerText = "Editar Materia";
		materia = new Materia();
		materia = mat;
		listaCursos = cursoDAO.getLista(true);
		listaProfesores = profesorDAO.getLista(true);
		idCurso = mat.getCurso().getId();
		idTitular = mat.getProfesore2().getId();
		if (mat.getProfesore1() != null) {
			idSuplente = mat.getProfesore1().getId();
		} else {
			idSuplente = 0;
		}
		return "materia";
	}
	
	public void baja(Materia mat) {
		FacesMessage msg = null;
		mat.setEnabled(false);
		mat.setFechaBaja(new Date());
		mat.setUsuario2(usuario);
		if (materiaDAO.update(mat) != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE MATERIA EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN LA BAJA DE LA MATERIA, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void alta(Materia mat) {
		FacesMessage msg = null;
		mat.setEnabled(true);
		mat.setFechaAlta(new Date());
		mat.setUsuario1(usuario);
		if (materiaDAO.update(mat) != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALTA DE MATERIA EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN EL ALTA DE LA MATERIA, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void generarReporte() {
		
	}
	
	public String guardar() {
		FacesMessage msg = null;
		String retorno = "";
		if (materia.getId() != 0) {
			Profesore profesorTitular = new Profesore();
			Profesore profesorSuplente = new Profesore();
			Curso curso = new Curso();
			profesorTitular.setId(idTitular);
			curso.setId(idCurso);
			if (idSuplente != 0) {
				profesorSuplente.setId(idSuplente);
				materia.setProfesore1(profesorSuplente);
			}
			materia.setProfesore2(profesorTitular);
			materia.setCurso(curso);
			materia.setFechaMod(new Date());
			materia.setUsuario3(usuario);
			int updateMateria = materiaDAO.update(materia);
			if (updateMateria != 0) {
				listaMaterias = new ArrayList<Materia>();
				filteredMaterias = new ArrayList<Materia>();
				listaMaterias = materiaDAO.getLista(true);
				filteredMaterias = listaMaterias;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "MATERIA REGISTRADA!", null);
				retorno = "materias";
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LA MATERIA, "
						+ "INTENTELO NUEVAMENTE!", null);
			}
		} else {
			Profesore profesorTitular = new Profesore();
			Profesore profesorSuplente = new Profesore();
			Curso curso = new Curso();
			profesorTitular.setId(idTitular);
			curso.setId(idCurso);
			if (idSuplente != 0) {
				profesorSuplente.setId(idSuplente);
				materia.setProfesore1(profesorSuplente);
			}
			materia.setProfesore2(profesorTitular);
			materia.setCurso(curso);
			materia.setEnabled(true);
			materia.setFechaAlta(new Date());
			materia.setUsuario1(usuario);
			int idMateria = materiaDAO.insertar(materia);
			if (idMateria != 0) {
				listaMaterias = new ArrayList<Materia>();
				filteredMaterias = new ArrayList<Materia>();
				listaMaterias = materiaDAO.getLista(true);
				filteredMaterias = listaMaterias;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "MATERIA REGISTRADA!", null);
				retorno = "materias";
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LA MATERIA, "
						+ "INTENTELO NUEVAMENTE!", null);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarAsistencia() {
		
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-materia.jpg";
         
        pdf.add(Image.getInstance(logo));
    }

}
