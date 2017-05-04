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
import promoda.dao.DAOMatricula;
import promoda.dao.DAOUsuario;
import promoda.model.Curso;
import promoda.model.Matricula;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanCurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaDAO}")
	private DAOMatricula matriculaDAO;
	
	private List<Curso> listaCursos;
	private List<Curso> filteredCursos;
	private Curso curso;
	private Matricula matricula;
	private Usuario usuario;
	private String headerText;
	private boolean nuevo;

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

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Curso> getFilteredCursos() {
		return filteredCursos;
	}

	public void setFilteredCursos(List<Curso> filteredCursos) {
		this.filteredCursos = filteredCursos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
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
	
	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public String goCursos(Usuario user) {
		usuario = new Usuario();
		usuario = user;
		listaCursos = new ArrayList<Curso>();
		filteredCursos = new ArrayList<Curso>();
		listaCursos = cursoDAO.getLista(true);
		filteredCursos = listaCursos;
		return "cursos";
	}
	
	public String goNuevo() {
		headerText = "Nuevo Curso";
		curso = new Curso();
		nuevo = true;
		return "curso";
	}
	
	public String goEditar(Curso cur) {
		headerText = "Editar Curso";
		curso = new Curso();
		curso = cur;
		nuevo = false;
		return "curso";
	}
	
	public void baja(Curso cur) {
		FacesMessage msg = null;
		Matricula matr = new Matricula();
		cur.setEnabled(false);
		cur.setFechaBaja(new Date());
		cur.setUsuario2(usuario);
		int bajaMatricula = 1;
		if (cur.getMatricula() != null) {
			matr = cur.getMatricula();
			matr.setEnabled(false);
			matr.setFechaBaja(new Date());
			matr.setUsuario2(usuario);
			bajaMatricula = matriculaDAO.update(matr);
		}
		int bajaCurso = cursoDAO.update(cur); 
		if (bajaCurso != 0 && bajaMatricula != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE CURSO EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN LA BAJA DEL CURSO, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void alta(Curso cur) {
		FacesMessage msg = null;
		Matricula matri = new Matricula();
		cur.setEnabled(true);
		cur.setFechaAlta(new Date());
		cur.setUsuario1(usuario);
		int altaMatricula = 1;
		if (cur.getMatricula() != null) {
			matri = cur.getMatricula();
			matri.setEnabled(true);
			matri.setFechaAlta(new Date());
			matri.setUsuario1(usuario);
			altaMatricula = matriculaDAO.update(matri);
		}		
		int altaCurso = cursoDAO.update(cur); 
		if (altaCurso != 0 && altaMatricula != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALTA DE CURSO EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN EL ALTA DEL CURSO, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void generarReporte() {
		
	}
	
	public String guardar() {
		FacesMessage msg = null;
		String retorno = "";
		if ((!curso.getNombre().isEmpty() || curso.getNombre() != "") && curso.getCostoCurso() != 0 && curso.getCostoMatricula() != 0) {
			if (curso.getId() != 0) {
				float costoTotal = curso.getCostoCurso() + curso.getCostoMatricula();
				curso.setFechaMod(new Date());
				curso.setUsuario3(usuario);
				curso.setCostoTotal(costoTotal);
				int updateCur = cursoDAO.update(curso);
				if (updateCur != 0) {
					filteredCursos = new ArrayList<Curso>();
					listaCursos = new ArrayList<Curso>();
					listaCursos = cursoDAO.getLista(true);
					filteredCursos = listaCursos;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "CURSO REGISTRADO!", null);
					retorno = "cursos";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL CURSO, "
							+ "INTENTELO NUEVAMENTE", null);
				}
			} else {
				float costoTotal = curso.getCostoCurso() + curso.getCostoMatricula();
				curso.setEnabled(true);
				curso.setFechaAlta(new Date());
				curso.setUsuario1(usuario);
				curso.setCostoTotal(costoTotal);
				int idCurso = cursoDAO.insertar(curso);
				if (idCurso != 0) {
					filteredCursos = new ArrayList<Curso>();
					listaCursos = new ArrayList<Curso>();
					listaCursos = cursoDAO.getLista(true);
					filteredCursos = listaCursos;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL CURSO "
							+ "SE REGISTO CON EXITO!", null);
					retorno = "cursos";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL CURSO, "
							+ "INTENTELO NUEVAMENTE", null);
				}
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			String mensaje = "";
			if (curso.getNombre().isEmpty() || curso.getNombre() == "") {
				mensaje = mensaje + " NOMBRE!";
			}
			if (curso.getCostoCurso() == 0) {
				mensaje = mensaje + " COSTO DE CURSO!";
			}
			if (curso.getCostoMatricula() == 0) {
				mensaje = mensaje + " COSTO DE MATRÍCULA!";
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "LOS PARAMETROS" + mensaje + " SON OBLIGATORIOS PARA UN CURSO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
 		}		
		return retorno;
	}
	
	/*
	 * CUANDO SE VENCE LA MATRICULA DEJA DE ESTAR EN VIGENCIA COLOCARLA EN NULL EN EL CURSO ASI CUANDO SE RELACIONA SE
	 * PREGUNTA POR NULL EN LA MATRICULA VIGENTE DEL CURSO
	 */
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-curso.jpg";
         
        pdf.add(Image.getInstance(logo));
    }

}
