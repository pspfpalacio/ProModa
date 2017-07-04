package promoda.managed.beans.alumno;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.codehaus.jettison.json.JSONObject;

import com.mercadopago.MP;

import promoda.clases.CajasMov;
import promoda.clases.MatriculaAlum;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMateriasCalificacion;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOMesa;
import promoda.dao.DAOMesaAlumno;
import promoda.dao.DAOPagosMesa;
import promoda.dao.DAOParametro;
import promoda.dao.DAOResgistroOnline;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.MateriasCalificacion;
import promoda.model.Matricula;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;
import promoda.model.PagosMesa;
import promoda.model.Parametro;
import promoda.model.RegistroOnline;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanMesaAlum implements Serializable {

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
	
	@ManagedProperty(value = "#{BeanMateriasCalificacionDAO}")
	private DAOMateriasCalificacion materiaCalificacionDAO;
	
	@ManagedProperty(value = "#{BeanParametroDAO}")
	private DAOParametro parametroDAO;
	
	@ManagedProperty(value = "#{BeanRegistroOnlineDAO}")
	private DAOResgistroOnline registroOnlineDAO;
	
	private List<MesasAlumno> listaMesasAlumnos;
	private List<Curso> listaCursos;
	private List<Matricula> listaMatriculas;
	private List<Materia> listaMaterias;
	private List<MatriculaAlum> listaMatriculaAlums;
	private Usuario usuario;
	private Alumno alumno;
	private Mesa mesa;
	private MatriculaAlum matriculaAlum;
	private MP mercadoPago;
	private Parametro parametro;
	private String accesToken;
	private String jsonId;
	private String checkoutURL;
	private String condicion;
	private int idCurso;
	private int idMatricula;
	private int idMateria;
	private int cantidad;
	private float costo;
	private boolean verPeriodo;
	private boolean verPeriodos;
	private boolean verMesa;
	private boolean panelPago;
	private boolean actualizaPago;
	
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
	public DAOMateriasCalificacion getMateriaCalificacionDAO() {
		return materiaCalificacionDAO;
	}
	public void setMateriaCalificacionDAO(
			DAOMateriasCalificacion materiaCalificacionDAO) {
		this.materiaCalificacionDAO = materiaCalificacionDAO;
	}
	public DAOParametro getParametroDAO() {
		return parametroDAO;
	}
	public void setParametroDAO(DAOParametro parametroDAO) {
		this.parametroDAO = parametroDAO;
	}
	public DAOResgistroOnline getRegistroOnlineDAO() {
		return registroOnlineDAO;
	}
	public void setRegistroOnlineDAO(DAOResgistroOnline registroOnlineDAO) {
		this.registroOnlineDAO = registroOnlineDAO;
	}
	public List<MesasAlumno> getListaMesasAlumnos() {
		return listaMesasAlumnos;
	}
	public void setListaMesasAlumnos(List<MesasAlumno> listaMesasAlumnos) {
		this.listaMesasAlumnos = listaMesasAlumnos;
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
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}	
	public MatriculaAlum getMatriculaAlum() {
		return matriculaAlum;
	}
	public void setMatriculaAlum(MatriculaAlum matriculaAlum) {
		this.matriculaAlum = matriculaAlum;
	}
	public MP getMercadoPago() {
		return mercadoPago;
	}
	public void setMercadoPago(MP mercadoPago) {
		this.mercadoPago = mercadoPago;
	}
	public Parametro getParametro() {
		return parametro;
	}
	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}
	public String getAccesToken() {
		return accesToken;
	}
	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}
	public String getJsonId() {
		return jsonId;
	}
	public void setJsonId(String jsonId) {
		this.jsonId = jsonId;
	}
	public String getCheckoutURL() {
		return checkoutURL;
	}
	public void setCheckoutURL(String checkoutURL) {
		this.checkoutURL = checkoutURL;
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
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
	public boolean isVerPeriodo() {
		return verPeriodo;
	}
	public void setVerPeriodo(boolean verPeriodo) {
		this.verPeriodo = verPeriodo;
	}
	public boolean isVerPeriodos() {
		return verPeriodos;
	}
	public void setVerPeriodos(boolean verPeriodos) {
		this.verPeriodos = verPeriodos;
	}	
	public boolean isVerMesa() {
		return verMesa;
	}
	public void setVerMesa(boolean verMesa) {
		this.verMesa = verMesa;
	}	
	public boolean isPanelPago() {
		return panelPago;
	}
	public void setPanelPago(boolean panelPago) {
		this.panelPago = panelPago;
	}	
	public boolean isActualizaPago() {
		return actualizaPago;
	}
	public void setActualizaPago(boolean actualizaPago) {
		this.actualizaPago = actualizaPago;
	}
	
	public String goMesaInscripcion(Usuario user) {
		try {
			listaCursos = new ArrayList<Curso>();
			listaMatriculaAlums = new ArrayList<MatriculaAlum>();
			usuario = new Usuario();
			alumno = new Alumno();
			mesa = new Mesa();
			verPeriodo = false;
			verPeriodos = false;
			verMesa = false;
			panelPago = false;
			actualizaPago = false;
			idCurso = 0;
			idMatricula = 0;
			idMateria = 0;
			cantidad = 0;
			costo = 0;
			condicion = " - ";
			usuario = user;
			alumno = user.getAlumno();
			listaCursos = matriculaAlumnoDAO.getListaCursoDistinct(alumno);		
			return "mesasAlumno";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurrió un error al cargar el formulario.", null));
			return "";			
		}
	}
	
	public String goVerMesas(Usuario user) {
		try {
			listaCursos = new ArrayList<Curso>();
			listaMatriculaAlums = new ArrayList<MatriculaAlum>();
			listaMesasAlumnos = new ArrayList<MesasAlumno>();
			usuario = new Usuario();
			alumno = new Alumno();			
			verPeriodo = false;
			verPeriodos = false;			
			idCurso = 0;
			idMatricula = 0;
			idMateria = 0;
			usuario = user;
			alumno = user.getAlumno();
			listaCursos = matriculaAlumnoDAO.getListaCursoDistinct(alumno);		
			return "verMesas";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Ocurrió un error al cargar el formulario.", null));
			return "";
		}
	}
	
	public void onChangeCurso() {
		try {
			listaMatriculaAlums = new ArrayList<MatriculaAlum>();
			listaMaterias = new ArrayList<Materia>();
			listaMesasAlumnos = new ArrayList<MesasAlumno>();
			verPeriodo = false;
			verPeriodos = false;
			verMesa = false;
			panelPago = false;
			actualizaPago = false;
			idMatricula = 0;
			idMateria = 0;			
			cantidad = 0;
			costo = 0;
			condicion = " - ";
			mesa = new Mesa();
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
					verPeriodos = true;
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
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al generar los periodos para el curso seleccionado, "
					+ "intenté nuevamente más tarde.", null));
		}
	}
	
	public void onChangeMateria() {
		try {
			verMesa = false;
			panelPago = false;
			actualizaPago = false;
			cantidad = 0;
			costo = 0;
			condicion = " - ";
			mesa = new Mesa();
			if (idMatricula != 0 && idCurso != 0 && idMateria != 0) {				
				Curso cur = cursoDAO.get(idCurso);
				Matricula matr = matriculaDAO.get(idMatricula);
				Materia mat = materiaDAO.get(idMateria);				
				List<Mesa> listaMesas = mesaDAO.getListaBetweenFecha(cur, matr, mat, new Date());
				for (Mesa me : listaMesas) {
					mesa = me;		
					verMesa = true;
				}				
				List<MesasAlumno> listaAux = mesaAlumnoDAO.getLista(true, alumno, cur, mat);
				cantidad = listaAux.size() + 1;
				if (cantidad > 3) {
					PagosMesa pagosMesa = mesaPagoDAO.get(mesa, alumno);
					if (pagosMesa.getId() == 0) {
						costo = mesa.getCosto();
						panelPago = true;
					} else {
						actualizaPago = true;
					}
				}
				MateriasCalificacion matCalifica = materiaCalificacionDAO.get(alumno, cur, mat, matr);
				if (matCalifica.getId() != 0) {
					if (!matCalifica.getEstado().isEmpty() && matCalifica.getEstado() != null) {
						condicion = matCalifica.getEstado();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LA MESA", null));
		}
	}
	
	public void buscar() {
		try {
			if (idCurso != 0 && idMateria != 0 && idMatricula != 0) {
				listaMesasAlumnos = new ArrayList<MesasAlumno>();
				Curso cur = cursoDAO.get(idCurso);
				Materia mat = materiaDAO.get(idMateria);
				listaMesasAlumnos = mesaAlumnoDAO.getLista(true, alumno, cur, mat);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DEBE SELECCIONAR TODOS LOS CAMPOS", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LAS INSCRIPCIONES", null));
		}
	}
	
	public void bajaInscripcion(MesasAlumno mAlumno) {
		try {
			mAlumno.setEnabled(false);
			mAlumno.setFechaBaja(new Date());
			mAlumno.setUsuario2(usuario);
			if (mesaAlumnoDAO.update(mAlumno) != 0) {
				PagosMesa pagosMesa = mesaPagoDAO.get(mAlumno);
				if (pagosMesa.getId() != 0) {
					CajasMov cajaMov = new CajasMov();
					pagosMesa.setEnabled(false);
					pagosMesa.setFechaBaja(new Date());
					pagosMesa.setUsuario2(usuario);
					int idPagoMesa = mesaPagoDAO.update(pagosMesa);
					cajaMov.eliminarMovimiento(idPagoMesa, "PagosMesa", usuario);
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"BAJA REGISTRADA CON ÉXITO.", null));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						"OCURRIO UN ERROR AL REGISTRAR LA BAJA DE LA INSCRIPCIÓN.", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REALIZAR LA BAJA DE INSCRIPCIÓN", null));
		}
	}
	
	public void guardarInscripcion() {
		try {
			if (idMatricula != 0 && idCurso != 0 && idMateria != 0 && mesa.getId() != 0) {
				MesasAlumno mesaAlumno = new MesasAlumno();				
				Curso cur = cursoDAO.get(idCurso);
				Materia mat = materiaDAO.get(idMateria);
				Matricula matr = matriculaDAO.get(idMatricula);
				MateriasCalificacion matCalifica = materiaCalificacionDAO.get(alumno, cur, mat, matr);
				if (matCalifica.getId() != 0) {
					if (!matCalifica.getEstado().isEmpty() && matCalifica.getEstado() != null) {
						List<MesasAlumno> listAux = mesaAlumnoDAO.getLista(true, alumno, mesa);
						if (listAux.isEmpty()) {
							mesaAlumno.setAlumno(alumno);
							mesaAlumno.setAprobado(false);
							mesaAlumno.setCondicion(condicion);
							mesaAlumno.setContador(cantidad);
							mesaAlumno.setCurso(cur);
							mesaAlumno.setEnabled(true);
							mesaAlumno.setFechaAlta(new Date());
							mesaAlumno.setMateria(mat);
							mesaAlumno.setMesa(mesa);
							mesaAlumno.setUsuario1(usuario);
							int idInsert = mesaAlumnoDAO.insertar(mesaAlumno); 
							if (idInsert != 0) {	
								if (actualizaPago) {
									mesaAlumno.setId(idInsert);
									PagosMesa pagoMesa = mesaPagoDAO.get(mesa, alumno);						
									pagoMesa.setMesasAlumno(mesaAlumno);						
									mesaPagoDAO.update(pagoMesa);
								}
								
								listaMatriculaAlums = new ArrayList<MatriculaAlum>();
								mesa = new Mesa();
								verPeriodo = false;
								verPeriodos = false;
								verMesa = false;
								panelPago = false;
								actualizaPago = false;
								idCurso = 0;
								idMatricula = 0;
								idMateria = 0;
								cantidad = 0;
								costo = 0;	
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
										"Se registró con éxito la inscripción.", null));
							} else {
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
										"Ocurrió un error al registrar la mesa.", null));
							}
						} else {
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
									"Ya se encuentra inscripto a la mesa.", null));
						}
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
								"No es posible registrar la inscripción a la mesa. Aún no se encuentra calificado en esa materia.", null));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
							"No es posible registrar la inscripción a la mesa. Aún no se encuentra calificado en esa materia.", null));
				}								
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						"Todos los campos son obligatorios.", null));
			}			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"OCURRIO UN ERROR AL REGISTRAR LA INSCRIPCIÓN.", null));
		}
	}
	
	public void generarPagoMesa() {
		try {
			parametro = parametroDAO.get(1);
			
			mercadoPago = new MP(parametro.getUsuarioMp(), parametro.getPasswordMp());
			accesToken = mercadoPago.getAccessToken();
			System.out.println(accesToken);
			
			float porcentajeMp = parametro.getPorcentajeMp();
			float montoP = mesa.getCosto();
			
			montoP = montoP + (montoP * (porcentajeMp / 100));
						
			JSONObject preference = mercadoPago.createPreference("{'items':[{'title':'" + mesa.getCurso().getNombre() + " - " + mesa.getMateria().getNombre() + " - Mesa " + mesa.getFechaHoraString() + " (" + alumno.getId() + ")','description':'" + mesa.getCurso().getNombre() + " - " + mesa.getMateria().getNombre() + " - Mesa " + mesa.getFechaHoraString() + " (" + alumno.getId() + ")','quantity':1,'currency_id':'ARS','unit_price':" + montoP + "}],"
					+ "'payer':{'name':'" + alumno.getNombres() + "','surname':'" + alumno.getApellido() + "','email':'" + usuario.getEmail() + "','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}}}");
			
			System.out.println(preference.toString());
			
			jsonId = preference.getJSONObject("response").getString("id");
			
			checkoutURL = preference.getJSONObject("response").getString("init_point");
			
			System.out.println(checkoutURL);	
			
			RegistroOnline registroOnline = new RegistroOnline();
			registroOnline.setAccesToken(accesToken);
			registroOnline.setCheckOut(checkoutURL);
			registroOnline.setDetalle(mesa.getCurso().getNombre() + " - " + mesa.getMateria().getNombre() + " - Mesa " + mesa.getFechaHoraString() + " (" + alumno.getNombreCompleto() + ")");
			registroOnline.setFecha(new Date());
			registroOnline.setIdAlumno(alumno.getDni());
			registroOnline.setIdMatricula(mesa.getMatricula().getId());
			registroOnline.setJsonId(jsonId);
			registroOnline.setMonto(montoP);
			registroOnline.setPreference(preference.toString());
			registroOnlineDAO.insertar(registroOnline);
			
//			String sandboxCheckoutURL = preference.getJSONObject("response").getString("sandbox_init_point");
			
//			System.out.println(sandboxCheckoutURL);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(checkoutURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE CARGAR EL FORMULARIO, INTENTELO MAS TARDE!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}

}
