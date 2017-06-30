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

import promoda.clases.CajasMov;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOMesa;
import promoda.dao.DAOMesaAlumno;
import promoda.dao.DAOPagosMesa;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Matricula;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;
import promoda.model.PagosMesa;
import promoda.model.RecursoAlumno;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanMesaAlumno implements Serializable {

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
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
    private DAOAlumno alumnoDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
    private DAOMatriculaAlumno matriculaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanPagosMesaDAO}")
	private DAOPagosMesa mesaPagoDAO;
	
	private List<MesasAlumno> listaMesasAlumnos;
	private List<Mesa> listaMesas;
	private List<Alumno> listaAlumnos;
	private List<Curso> listaCursos;
	private List<Matricula> listaMatriculas;
	private List<Materia> listaMaterias;
	private Usuario usuario;
	private Mesa mesa;
	private int idAlumno;
	private int idCurso;
	private int idMatricula;
	private int idMateria;
	private int idMesa;
	private int cantidad;
	private float costo;
	
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
	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}
	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}
	public DAOMatriculaAlumno getMatriculaAlumnoDAO() {
		return matriculaAlumnoDAO;
	}
	public void setMatriculaAlumnoDAO(DAOMatriculaAlumno matriculaAlumnoDAO) {
		this.matriculaAlumnoDAO = matriculaAlumnoDAO;
	}
	public DAOPagosMesa getMesaPagoDAO() {
		return mesaPagoDAO;
	}
	public void setMesaPagoDAO(DAOPagosMesa mesaPagoDAO) {
		this.mesaPagoDAO = mesaPagoDAO;
	}
	public List<MesasAlumno> getListaMesasAlumnos() {
		return listaMesasAlumnos;
	}
	public void setListaMesasAlumnos(List<MesasAlumno> listaMesasAlumnos) {
		this.listaMesasAlumnos = listaMesasAlumnos;
	}
	public List<Mesa> getListaMesas() {
		return listaMesas;
	}
	public void setListaMesas(List<Mesa> listaMesas) {
		this.listaMesas = listaMesas;
	}
	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}
	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
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
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
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
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public String goMesaInscripcion(Usuario user) {
		try {
			listaAlumnos = new ArrayList<Alumno>();
			listaCursos = new ArrayList<Curso>();
			listaMatriculas = new ArrayList<Matricula>();
			listaMaterias = new ArrayList<Materia>();
			listaMesas = new ArrayList<Mesa>();
			idAlumno = 0;
			idCurso = 0;
			idMatricula = 0;
			idMateria = 0;
			idMesa = 0;
			cantidad = 0;
			costo = 0;
			usuario = new Usuario();
			usuario = user;
			listaAlumnos = alumnoDAO.getLista(true);
			return "mesaInscripcion";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurri� un error al cargar el formulario. Error: " + e.getMessage(), null));
			return "";
		}
	}
	
	public String goMesaInscriptos(Usuario user, Mesa me) {
		try {
			listaMesasAlumnos = new ArrayList<MesasAlumno>();
			mesa = new Mesa();
			usuario = new Usuario();
			mesa = me;
			usuario = user;
			listaMesasAlumnos = mesaAlumnoDAO.getLista(true, me);
			return "mesaInscriptos";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurri� un error al cargar el formulario. Error: " + e.getMessage(), null));
			return "";
		}
	}
	
	public void onChangeAlumno() {
		try {
			listaCursos = new ArrayList<Curso>();
			listaMatriculas = new ArrayList<Matricula>();
			listaMaterias = new ArrayList<Materia>();
			listaMesas = new ArrayList<Mesa>();
			idCurso = 0;
			idCurso = 0;
			idMatricula = 0;
			idMateria = 0;
			idMesa = 0;
			cantidad = 0;
			costo = 0;
			if (idAlumno != 0) {
				Alumno alum = alumnoDAO.get(idAlumno);
				listaCursos = matriculaAlumnoDAO.getListaCurso(alum);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurri� un error al cargar los cursos. Error: " + e.getMessage(), null));
		}    	
    }
	
	public void onChangeCurso() {
		try {
			listaMatriculas = new ArrayList<Matricula>();
			listaMaterias = new ArrayList<Materia>();	
			listaMesas = new ArrayList<Mesa>();
			idMatricula = 0;			
			idMateria = 0;
			idMesa = 0;
			cantidad = 0;
			costo = 0;
			if (idAlumno != 0 && idCurso != 0) {
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
			listaMesas = new ArrayList<Mesa>();
			idMateria = 0;
			idMesa = 0;
			cantidad = 0;
			costo = 0;
			if (idAlumno != 0 && idMatricula != 0 && idCurso != 0) {				
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
			listaMesas = new ArrayList<Mesa>();			
			idMesa = 0;
			cantidad = 0;
			costo = 0;
			if (idAlumno != 0 && idMatricula != 0 && idCurso != 0 && idMateria != 0) {				
				Curso cur = cursoDAO.get(idCurso);
				Matricula matr = matriculaDAO.get(idMatricula);
				Materia mat = materiaDAO.get(idMateria);
				listaMesas = mesaDAO.getLista(cur, matr, mat);
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LAS MESAS", null));
		}
	}
	
	public void onChangeMesa() {
		try {			
			cantidad = 0;			
			costo = 0;
			if (idAlumno != 0 && idMatricula != 0 && idCurso != 0 && idMateria != 0 && idMesa != 0) {				
				Alumno alum = alumnoDAO.get(idAlumno);
				Mesa me = mesaDAO.get(idMesa);
				List<MesasAlumno> listaAux = mesaAlumnoDAO.getLista(true, alum, me);
				cantidad = listaAux.size() + 1;
				if (cantidad > 3) {
					costo = me.getCosto();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LAS MESAS", null));
		}
	}
	
	public void onRowEdit(MesasAlumno mesaAlum) {
        List<MesasAlumno> listAux = listaMesasAlumnos;
        int dniAlum1 = mesaAlum.getAlumno().getDni();
        listaMesasAlumnos = new ArrayList<MesasAlumno>();
        for (MesasAlumno mAlumno : listAux) {
			int dniAlum2 = mAlumno.getAlumno().getDni();
			if (dniAlum1 == dniAlum2) {
				mAlumno.setCalificacion(mesaAlum.getCalificacion());
				mAlumno.setFechaAlta(new Date());
			}
			listaMesasAlumnos.add(mAlumno);
		}
    }
	
	public String guardarInscripcion() {
		try {
			if (idAlumno != 0 && idMatricula != 0 && idCurso != 0 && idMateria != 0 && idMesa != 0) {
				MesasAlumno mesaAlumno = new MesasAlumno();
				Alumno alum = alumnoDAO.get(idAlumno);
				Mesa me = mesaDAO.get(idMesa);
				mesaAlumno.setAlumno(alum);
				mesaAlumno.setAprobado(false);
				mesaAlumno.setContador(cantidad);
				mesaAlumno.setEnabled(true);
				mesaAlumno.setFechaAlta(new Date());
				mesaAlumno.setMesa(me);
				mesaAlumno.setUsuario1(usuario);
				if (mesaAlumnoDAO.insertar(mesaAlumno) != 0) {	
					if (cantidad > 3) {
						CajasMov cajaMov = new CajasMov();
						PagosMesa pagoMesa = new PagosMesa();
						pagoMesa.setAlumno(alum);
						pagoMesa.setEnabled(true);
						pagoMesa.setFecha(new Date());
						pagoMesa.setFechaAlta(new Date());
						pagoMesa.setMesa(me);
						pagoMesa.setMonto(costo);
						pagoMesa.setUsuario1(usuario);
						int idPagoMesa = mesaPagoDAO.insertar(pagoMesa);
						Curso cur = cursoDAO.get(idCurso);
						Materia mat = materiaDAO.get(idMateria);
						cajaMov.generarMovimiento(new Date(), 1, costo, idPagoMesa, "PagosMesa", "Pago Mesa", 
		        				"Pago de Mesa de Curso " + cur.getNombre() + " de Materia " + mat.getNombre() + " de " + alum.getNombreCompleto(), usuario);
					}
					return "mesas";
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
							"Ocurri� un error al registrar la mesa.", null));
					return "";
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						"Todos los campos son obligatorios.", null));
				return "";
			}			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"OCURRIO UN ERROR AL REGISTRAR LA INSCRIPCI�N. Error: " + e.getMessage(), null));
			return "";
		}
	}
	
	public void bajaInscripto(MesasAlumno mesaAlum) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"OCURRIO UN ERROR AL REGISTRAR LA BAJA DE LA INSCRIPCI�N. Error: " + e.getMessage(), null));
		}
	}
	
//	public void calificarInscriptos() {
//		FacesMessage msg = null;
//		try {
//			for (RecursoAlumno rAlumno : listaRecursoAlumnos) {
//				if (rAlumno.getId() != 0) {
//					rAlumno.setFechaAlta(new Date());
//					rAlumno.setUsuario(usuario);
//					recursoAlumnoDAO.update(rAlumno);
//				} else {
//					rAlumno.setFechaAlta(new Date());
//					rAlumno.setUsuario(usuario);
//					recursoAlumnoDAO.insertar(rAlumno);
//				}
//			}
//			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambios registrados con �xito!", null);
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		} catch (Exception e) {
//			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocurri� un error al registrar los cambios! Int�ntelo nuevamente!", null);
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		}
//	}

}
