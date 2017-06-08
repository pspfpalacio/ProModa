package promoda.managed.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import promoda.dao.DAOCuota;
import promoda.dao.DAOCuotaImpaga;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOMatriculaImpaga;
import promoda.dao.DAOUsuario;
import promoda.model.Cuota;
import promoda.model.CuotaImpaga;
import promoda.model.Curso;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.MatriculaImpaga;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanMatricula implements Serializable {

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
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
    private DAOMatriculaAlumno matriculaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
    private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaImpagaDAO}")
	private DAOMatriculaImpaga matriculaImpagaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaImpagaDAO}")
	private DAOCuotaImpaga cuotaImpagaDAO;
	
	private List<Matricula> listaMatricula;
	private List<Matricula> filteredMatricula;
	private Matricula matricula;
	private Curso curso;
	private Usuario usuario;
	private String headerText;

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

	public DAOMatriculaAlumno getMatriculaAlumnoDAO() {
		return matriculaAlumnoDAO;
	}

	public void setMatriculaAlumnoDAO(DAOMatriculaAlumno matriculaAlumnoDAO) {
		this.matriculaAlumnoDAO = matriculaAlumnoDAO;
	}

	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
	}

	public DAOMatriculaImpaga getMatriculaImpagaDAO() {
		return matriculaImpagaDAO;
	}

	public void setMatriculaImpagaDAO(DAOMatriculaImpaga matriculaImpagaDAO) {
		this.matriculaImpagaDAO = matriculaImpagaDAO;
	}

	public DAOCuotaImpaga getCuotaImpagaDAO() {
		return cuotaImpagaDAO;
	}

	public void setCuotaImpagaDAO(DAOCuotaImpaga cuotaImpagaDAO) {
		this.cuotaImpagaDAO = cuotaImpagaDAO;
	}

	public List<Matricula> getListaMatricula() {
		return listaMatricula;
	}

	public void setListaMatricula(List<Matricula> listaMatricula) {
		this.listaMatricula = listaMatricula;
	}

	public List<Matricula> getFilteredMatricula() {
		return filteredMatricula;
	}

	public void setFilteredMatricula(List<Matricula> filteredMatricula) {
		this.filteredMatricula = filteredMatricula;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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
	
	public String goEditarMatricula(Curso cur, Usuario user) {
		headerText = "MATRICULA";
		curso = new Curso();
		matricula = new Matricula();
		usuario = new Usuario();
		curso = cur;
		matricula.setCosto(cur.getCostoMatricula());
		usuario = user;
		listaMatricula = new ArrayList<Matricula>();
		filteredMatricula = new ArrayList<Matricula>();
		listaMatricula = matriculaDAO.getListaDesc(cur);
		filteredMatricula = listaMatricula;
		return "matriculas";
	}
	
	public void generarReporte() {
		
	}
	
	public void guardar() {
		FacesMessage msg = null;
		try {
			if (matricula.getFechaAlta() != null && matricula.getFechaFinalizacion() != null 
					&& matricula.getFechaInicio() != null && matricula.getFechaFinCursado() != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String fecha_inicio = dateFormat.format(matricula.getFechaInicio());
				String fecha_fin = dateFormat.format(matricula.getFechaFinCursado());
				
				if (matricula.getId() != 0) {				
					String descripcion = Integer.toString(matricula.getId()) + " - (" + fecha_inicio + " - " + fecha_fin + ")";
					matricula.setFechaMod(new Date());
					matricula.setUsuario3(usuario);
					matricula.setDescripcion(descripcion);
					int idMatricula = matriculaDAO.update(matricula);
					
					if (idMatricula != 0) {
						listaMatricula = new ArrayList<Matricula>();
						listaMatricula = matriculaDAO.getListaDesc(curso);
						matricula = new Matricula();
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SE REGISTRO LA MATRICULA EXITOSAMENTE.", null);						
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LA MATRICULA.", null);
					}
				} else {
					matricula.setCurso(curso);
					matricula.setEnabled(true);
					matricula.setFechaMod(new Date());
					matricula.setUsuario1(usuario);
					int idMatricula = matriculaDAO.insertar(matricula);
					String descripcion = Integer.toString(idMatricula) + " - (" + fecha_inicio + " - " + fecha_fin + ")";
					matricula.setId(idMatricula);
					matricula.setDescripcion(descripcion);
					int updtMatricula = matriculaDAO.update(matricula);
					
					if (idMatricula != 0 && updtMatricula != 0) {
						listaMatricula = new ArrayList<Matricula>();
						listaMatricula = matriculaDAO.getListaDesc(curso);
						matricula = new Matricula();
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SE REGISTRO LA MATRICULA EXITOSAMENTE.", null);						
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LA MATRICULA.", null);
					}		
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO SE PUEDE REGISTRAR LA NUEVA MATRICULA! "
						+ "TODOS LOS CAMPOS SON OBLIGATORIOS.", null);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LA MATRICULA. Error: " + e.getMessage(), null);
		}				
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void editar(Matricula matric) {
		matricula = new Matricula();
		matricula = matric;
	}
	
	public void cancelar() {
		float costoC = matricula.getCosto();
		matricula = new Matricula();
		matricula.setCosto(costoC);
	}
	
	public void alta(Matricula matric) {
		FacesMessage msg = null;
		if (curso.getMatricula() != null) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "EL CURSO POSEE UNA MATRICULA VIGENTE, "
					+ "REALICE LA BAJA CORRESPONDIENTE ANTES DEL ALTA!", null);
		} else {
			matric.setEnabled(true);
			matric.setFechaMod(new Date());
			matric.setUsuario3(usuario);
			int idMatric = matriculaDAO.update(matric);
			if (idMatric != 0) {
				curso.setMatricula(matric);
				int updateCurso = cursoDAO.update(curso);
				if (updateCurso != 0) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SE REGISTRO EL ALTA DE LA MATRICULA!", null);
				} else {
					matric.setEnabled(false);
					matric.setFechaBaja(new Date());
					matric.setUsuario2(usuario);
					matriculaDAO.update(matric);
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL ACTUALIZAR EL CURSO PARA ESA MATRICULA!", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL ACTUALIZAR LA MATRICULA!", null);
			}
		}
		listaMatricula = new ArrayList<Matricula>();
		listaMatricula = matriculaDAO.getListaDesc(curso);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void baja(Matricula matric) {
		FacesMessage msg = null;
		matric.setEnabled(false);
		matric.setFechaBaja(new Date());
		matric.setUsuario2(usuario);		
		List<Cuota> listCuot = cuotaDAO.getLista(curso, matric, false);
		if (!listCuot.isEmpty()) {
			for (Cuota cuota : listCuot) {
				CuotaImpaga cuotaImpaga = new CuotaImpaga();
				MatriculaAlumno mAumno = matriculaAlumnoDAO.get(cuota.getAlumno(), curso, matric);
				cuotaImpaga.setAlumno(cuota.getAlumno());
				cuotaImpaga.setCuota(cuota);
				cuotaImpaga.setCurso(curso);
				cuotaImpaga.setDetalle(cuota.getDetalle());
				cuotaImpaga.setFechaAlta(new Date());
				cuotaImpaga.setFechaVencimiento(cuota.getFechaVencimiento());
				cuotaImpaga.setMatricula(matric);
				cuotaImpaga.setMatriculaAlumno(mAumno);
				cuotaImpaga.setMonto(cuota.getMonto());
				cuotaImpaga.setSegundoVencimiento(cuota.getSegundoVencimiento());
				cuotaImpaga.setUsuario(usuario);
				cuotaImpagaDAO.insertar(cuotaImpaga);
			}
		}
		List<MatriculaAlumno> listaMatAlum = matriculaAlumnoDAO.getLista(curso, matric, false);
		if (!listaMatAlum.isEmpty()) {
			for (MatriculaAlumno matriculaAlumno : listaMatAlum) {
				MatriculaImpaga matriculaImpaga = new MatriculaImpaga();
				matriculaImpaga.setAlumno(matriculaAlumno.getAlumno());
				matriculaImpaga.setCurso(curso);
				matriculaImpaga.setFechaAlta(new Date());
				matriculaImpaga.setMatricula(matric);
				matriculaImpaga.setMatriculaAlumno(matriculaAlumno);
				matriculaImpaga.setMonto(matric.getCosto());
				matriculaImpaga.setUsuario(usuario);
				matriculaImpagaDAO.insertar(matriculaImpaga);
				matriculaAlumno.setEnabled(false);
				matriculaAlumnoDAO.update(matriculaAlumno);				
			}
		}		
		int idMatric = matriculaDAO.update(matric);
		curso.setMatricula(null);
		int updateCurso = cursoDAO.update(curso);
		if (idMatric != 0 && updateCurso != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LA MATRICULA SE DIO DE BAJA CON EXITO", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL DAR DE BAJA LA MATRICULA", null);
		}
		listaMatricula = new ArrayList<Matricula>();
		listaMatricula = matriculaDAO.getListaDesc(curso);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-matricula.jpg";
         
        pdf.add(Image.getInstance(logo));
    }

}
