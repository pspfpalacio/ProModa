package promoda.managed.beans.alumno;

import java.io.Serializable;
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

import promoda.dao.DAOCuota;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOPagosMatricula;
import promoda.dao.DAOParametro;
import promoda.dao.DAOResgistroOnline;
import promoda.dao.DAOUsuario;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.Curso;
import promoda.model.MatriculaAlumno;
import promoda.model.PagosMatricula;
import promoda.model.Parametro;
import promoda.model.RegistroOnline;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanPagoAlum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
    private DAOMatriculaAlumno matriculaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanPagosMatriculaDAO}")
    private DAOPagosMatricula pagosMatriculaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
    private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanParametroDAO}")
	private DAOParametro parametroDAO;
	
	@ManagedProperty(value = "#{BeanRegistroOnlineDAO}")
	private DAOResgistroOnline registroOnlineDAO;
	
	private List<Curso> listaCursos;
	private List<Cuota> listaCuotas;
	private List<Cuota> listaCuotasCheck;
	private List<Cuota> selectionCuotas;
	private Curso curso;
	private Usuario usuario;
	private Alumno alumno;
	private PagosMatricula pagosMatricula;	
	private MatriculaAlumno matriculaAlumno;
	private Cuota cuota;
	private MP mercadoPago;
	private Parametro parametro;
	private String headerText;
	private String accesToken;
	private String checkoutURL;
	private String preapprovalPaymentURL;
	private String jsonId;
	private int idCurso;
	private boolean verCuotas;
	private boolean panelCuota;
	private boolean panelMatricula;
	private boolean pagoMultiple;

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

	public DAOMatriculaAlumno getMatriculaAlumnoDAO() {
		return matriculaAlumnoDAO;
	}

	public void setMatriculaAlumnoDAO(DAOMatriculaAlumno matriculaAlumnoDAO) {
		this.matriculaAlumnoDAO = matriculaAlumnoDAO;
	}

	public DAOPagosMatricula getPagosMatriculaDAO() {
		return pagosMatriculaDAO;
	}

	public void setPagosMatriculaDAO(DAOPagosMatricula pagosMatriculaDAO) {
		this.pagosMatriculaDAO = pagosMatriculaDAO;
	}

	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
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

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Cuota> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<Cuota> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public List<Cuota> getListaCuotasCheck() {
		return listaCuotasCheck;
	}

	public void setListaCuotasCheck(List<Cuota> listaCuotasCheck) {
		this.listaCuotasCheck = listaCuotasCheck;
	}

	public List<Cuota> getSelectionCuotas() {
		return selectionCuotas;
	}

	public void setSelectionCuotas(List<Cuota> selectionCuotas) {
		this.selectionCuotas = selectionCuotas;
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

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public PagosMatricula getPagosMatricula() {
		return pagosMatricula;
	}

	public void setPagosMatricula(PagosMatricula pagosMatricula) {
		this.pagosMatricula = pagosMatricula;
	}

	public MatriculaAlumno getMatriculaAlumno() {
		return matriculaAlumno;
	}

	public void setMatriculaAlumno(MatriculaAlumno matriculaAlumno) {
		this.matriculaAlumno = matriculaAlumno;
	}

	public Cuota getCuota() {
		return cuota;
	}

	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
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

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public String getCheckoutURL() {
		return checkoutURL;
	}

	public void setCheckoutURL(String checkoutURL) {
		this.checkoutURL = checkoutURL;
	}

	public String getPreapprovalPaymentURL() {
		return preapprovalPaymentURL;
	}

	public void setPreapprovalPaymentURL(String preapprovalPaymentURL) {
		this.preapprovalPaymentURL = preapprovalPaymentURL;
	}

	public String getJsonId() {
		return jsonId;
	}

	public void setJsonId(String jsonId) {
		this.jsonId = jsonId;
	}
	
	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public boolean isVerCuotas() {
		return verCuotas;
	}

	public void setVerCuotas(boolean verCuotas) {
		this.verCuotas = verCuotas;
	}

	public boolean isPanelCuota() {
		return panelCuota;
	}

	public void setPanelCuota(boolean panelCuota) {
		this.panelCuota = panelCuota;
	}

	public boolean isPanelMatricula() {
		return panelMatricula;
	}

	public void setPanelMatricula(boolean panelMatricula) {
		this.panelMatricula = panelMatricula;
	}

	public boolean isPagoMultiple() {
		return pagoMultiple;
	}

	public void setPagoMultiple(boolean pagoMultiple) {
		this.pagoMultiple = pagoMultiple;
	}

	public String goRealizarPago(Usuario user) {
		try {
			listaCursos = new ArrayList<Curso>();
			listaCuotas = new ArrayList<Cuota>();
			listaCuotasCheck = new ArrayList<Cuota>();
			selectionCuotas = new ArrayList<Cuota>();
			usuario = new Usuario();
			alumno = new Alumno();
//			matricula = new Matricula();
			matriculaAlumno = new MatriculaAlumno();
			cuota = new Cuota();		
			parametro = new Parametro();
			panelCuota = false;
			panelMatricula = false;
			verCuotas = false;
			pagoMultiple = false;
			idCurso = 0;
			usuario = user;
			alumno = user.getAlumno();
			listaCursos = matriculaAlumnoDAO.getListaCurso(alumno);				
			return "realizarpago";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE CARGAR EL FORMULARIO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public String goRealizaPago(Usuario user, Curso cur) {
		try {
			listaCursos = new ArrayList<Curso>();
			listaCuotas = new ArrayList<Cuota>();
			listaCuotasCheck = new ArrayList<Cuota>();
			selectionCuotas = new ArrayList<Cuota>();
			usuario = new Usuario();
			alumno = new Alumno();
//			matricula = new Matricula();
			matriculaAlumno = new MatriculaAlumno();
			cuota = new Cuota();		
			parametro = new Parametro();
			panelCuota = false;
			panelMatricula = false;
			verCuotas = false;
			pagoMultiple = false;
			idCurso = 0;
			usuario = user;
			alumno = user.getAlumno();
			listaCursos = matriculaAlumnoDAO.getListaCurso(alumno);		
			
			idCurso = cur.getId();
			if (idCurso != 0) {
				curso = cursoDAO.get(idCurso);
//				matricula = curso.getMatricula();
//				matriculaAlumno = matriculaAlumnoDAO.get(alumno, curso, matricula);
				if (matriculaAlumno.getId() != 0) {
					panelMatricula = true;
//					listaCuotas = cuotaDAO.getLista(alumno, matricula, curso);
					if(!listaCuotas.isEmpty()){
	        			verCuotas = true;	        			
	        		}
				}
			}
			
			return "realizarpago";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE CARGAR EL FORMULARIO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public void onChangeCurso() {
		try {
			listaCuotas = new ArrayList<Cuota>();
			listaCuotasCheck = new ArrayList<Cuota>();
			selectionCuotas = new ArrayList<Cuota>();
			matriculaAlumno = new MatriculaAlumno();
//			matricula = new Matricula();
			cuota = new Cuota();
			panelCuota = false;
			panelMatricula = false;
			verCuotas = false;
			pagoMultiple = false;
			if (idCurso != 0) {
				curso = cursoDAO.get(idCurso);
//				matricula = curso.getMatricula();
//				matriculaAlumno = matriculaAlumnoDAO.get(alumno, curso, matricula);
				if (matriculaAlumno.getId() != 0) {
					panelMatricula = true;
//					listaCuotas = cuotaDAO.getLista(alumno, matricula, curso);
					if(!listaCuotas.isEmpty()){
	        			verCuotas = true;	        			
	        		}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	public void cargarCuota(Cuota cuo) {
		try {
			cuota = new Cuota();
			cuota = cuo;
			panelCuota = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL CARGAR EL FORMULARIO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void generarListadoMultiple() {
		try {
			listaCuotasCheck = new ArrayList<Cuota>();
			selectionCuotas = new ArrayList<Cuota>();
//			listaCuotasCheck = cuotaDAO.getLista(alumno, matricula, curso, false);
			if (!listaCuotasCheck.isEmpty()) {
				pagoMultiple = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIó UN ERROR AL CARGAR EL FORMULARIO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void generarPagoMatricula() {
		try {
//			parametro = parametroDAO.get(1);
//			
//			mercadoPago = new MP(parametro.getUsuarioMp(), parametro.getPasswordMp());
//			accesToken = mercadoPago.getAccessToken();
//			System.out.println(accesToken);
//			
//			float porcentajeMp = parametro.getPorcentajeMp();
//			float montoP = matricula.getCosto();
//			
//			montoP = montoP + (montoP * (porcentajeMp / 100));
//						
//			JSONObject preference = mercadoPago.createPreference("{'items':[{'title':'" + curso.getNombre() + " - Matricula (" + alumno.getId() + ")','description':'" + curso.getNombre() + " - Matricula(" + alumno.getId() + ")','quantity':1,'currency_id':'ARS','unit_price':" + montoP + "}],"
//					+ "'payer':{'name':'" + alumno.getNombres() + "','surname':'" + alumno.getApellido() + "','email':'" + usuario.getEmail() + "','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}}}");
//			
//			System.out.println(preference.toString());
//			
//			jsonId = preference.getJSONObject("response").getString("id");
//			
//			checkoutURL = preference.getJSONObject("response").getString("init_point");
//			
//			System.out.println(checkoutURL);	
//			
//			RegistroOnline registroOnline = new RegistroOnline();
//			registroOnline.setAccesToken(accesToken);
//			registroOnline.setCheckOut(checkoutURL);
//			registroOnline.setDetalle(curso.getNombre() + " - Matricula (" + alumno.getNombreCompleto() + ")");
//			registroOnline.setFecha(new Date());
//			registroOnline.setIdAlumno(alumno.getDni());
//			registroOnline.setIdMatricula(matricula.getId());
//			registroOnline.setJsonId(jsonId);
//			registroOnline.setMonto(montoP);
//			registroOnline.setPreference(preference.toString());
//			registroOnlineDAO.insertar(registroOnline);
//			
////			String sandboxCheckoutURL = preference.getJSONObject("response").getString("sandbox_init_point");
//			
////			System.out.println(sandboxCheckoutURL);
//			
//			FacesContext.getCurrentInstance().getExternalContext().redirect(checkoutURL);
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "POR PROBLEMAS TECNICOS EN ESTE MOMENTO SE ENCUENTRA DESHABILITADO EL PAGO ONLINE. "
					+ "POR FAVOR COMUNIQUESE CON LA ADMINISTRACION. DISCULPE LAS MOLESTIAS.", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE CARGAR EL FORMULARIO, INTENTELO MAS TARDE!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void generarPagoCuota() {
		try {
//			parametro = parametroDAO.get(1);
//			
//			mercadoPago = new MP(parametro.getUsuarioMp(), parametro.getPasswordMp());
//			accesToken = mercadoPago.getAccessToken();
//			System.out.println(accesToken);
//			
//			float porcentajePV = cuota.getPorcentajePv();
//			float porcentajeSV = cuota.getPorcentajeSv();
//			float porcentajeMp = parametro.getPorcentajeMp();
//			float montoP = cuota.getMonto();
//			Date fechaPV = cuota.getFechaVencimiento();
//			Date fechaSV = cuota.getSegundoVencimiento();
//			Date fechaP = new Date();
//			if (fechaP != null) {
//				int comparacionPV = fechaP.compareTo(fechaPV);
//				int comparacionSV = fechaP.compareTo(fechaSV);
//				if (comparacionPV == 1) {
//					if (comparacionSV == 1) {
//						montoP = montoP + (montoP * (porcentajeSV / 100));
//					} else {
//						montoP = montoP + (montoP * (porcentajePV / 100));
//					}
//				} 
//			}
//			
//			montoP = montoP + (montoP * (porcentajeMp / 100));
//			
//			JSONObject preference = mercadoPago.createPreference("{'items':[{'title':'" + curso.getNombre() + " - " + cuota.getDetalle() + " (" + alumno.getId() + ")','description':'" + curso.getNombre() + " - " + cuota.getDetalle() + " (" + alumno.getId() + ")','quantity':1,'currency_id':'ARS','unit_price':" + montoP + "}],"
//					+ "'payer':{'name':'" + alumno.getNombres() + "','surname':'" + alumno.getApellido() + "','email':'" + usuario.getEmail() + "','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}}}");
//			
//			System.out.println(preference.toString());
//			
//			jsonId = preference.getJSONObject("response").getString("id");
//			
//			checkoutURL = preference.getJSONObject("response").getString("init_point");
//			
//			System.out.println(checkoutURL);
//			
//			RegistroOnline registroOnline = new RegistroOnline();
//			registroOnline.setAccesToken(accesToken);
//			registroOnline.setCheckOut(checkoutURL);
//			registroOnline.setDetalle(curso.getNombre() + " - " + cuota.getDetalle() + "(" + alumno.getNombreCompleto() + ")");
//			registroOnline.setFecha(new Date());
//			registroOnline.setIdAlumno(alumno.getDni());
//			registroOnline.setIdCuota(cuota.getId());
//			registroOnline.setJsonId(jsonId);
//			registroOnline.setMonto(montoP);
//			registroOnline.setPreference(preference.toString());
//			registroOnlineDAO.insertar(registroOnline);
//			
////			String sandboxCheckoutURL = preference.getJSONObject("response").getString("sandbox_init_point");
//			
////			System.out.println(sandboxCheckoutURL);
//			
//			FacesContext.getCurrentInstance().getExternalContext().redirect(checkoutURL);
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "POR PROBLEMAS TECNICOS EN ESTE MOMENTO SE ENCUENTRA DESHABILITADO EL PAGO ONLINE. "
					+ "POR FAVOR COMUNIQUESE CON LA ADMINISTRACION. DISCULPE LAS MOLESTIAS.", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE CARGAR EL FORMULARIO, INTENTELO MAS TARDE!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void generarPagoMultiple() {
		try {
			if (!selectionCuotas.isEmpty()) {
				parametro = parametroDAO.get(1);
				
				mercadoPago = new MP(parametro.getUsuarioMp(), parametro.getPasswordMp());
				accesToken = mercadoPago.getAccessToken();
				System.out.println(accesToken);
								
				float porcentajeMp = parametro.getPorcentajeMp();			
				float montoP = 0;	
				int cant = 1;
				String detalleCuotas = "Cuotas: ";
				for (Cuota cuo : selectionCuotas) {
					detalleCuotas = detalleCuotas + Integer.toString(cant) + "; ";
					cant = cant + 1;
					float porcentajePV = cuo.getPorcentajePv();
					float porcentajeSV = cuo.getPorcentajeSv();
					float montoC = cuo.getMonto();					
					Date fechaPV = cuo.getFechaVencimiento();
					Date fechaSV = cuo.getSegundoVencimiento();
					Date fechaP = new Date();
					if (fechaP != null) {
						int comparacionPV = fechaP.compareTo(fechaPV);
						int comparacionSV = fechaP.compareTo(fechaSV);
						if (comparacionPV == 1) {
							if (comparacionSV == 1) {
								montoC = montoC + (montoC * (porcentajeSV / 100));
							} else {
								montoC = montoC + (montoC * (porcentajePV / 100));
							}
						} 
					}					
					montoP = montoP + montoC;
				}
				
				montoP = montoP + (montoP * (porcentajeMp / 100));
				
				JSONObject preference = mercadoPago.createPreference("{'items':[{'id':1,'title':'" + curso.getNombre() + " - " + detalleCuotas + " (" + alumno.getId() + ")','description':'" + curso.getNombre() + " - " + detalleCuotas + " (" + alumno.getId() + ")','quantity':1,'currency_id':'ARS','unit_price':" + montoP + "}],"
						+ "'payer':{'name':'" + alumno.getNombres() + "','surname':'" + alumno.getApellido() + "','email':'" + usuario.getEmail() + "','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}}}");
				
				System.out.println(preference.toString());
				
				jsonId = preference.getJSONObject("response").getString("id");
				
				checkoutURL = preference.getJSONObject("response").getString("init_point");
				
				System.out.println(checkoutURL);
				
				RegistroOnline registroOnline = new RegistroOnline();
				registroOnline.setAccesToken(accesToken);
				registroOnline.setCheckOut(checkoutURL);
				registroOnline.setDetalle(curso.getNombre() + " - " + detalleCuotas + " (" + alumno.getNombreCompleto() + ")");
				registroOnline.setFecha(new Date());
				registroOnline.setIdAlumno(alumno.getDni());
				registroOnline.setJsonId(jsonId);
				registroOnline.setMonto(montoP);
				registroOnline.setPreference(preference.toString());
				registroOnlineDAO.insertar(registroOnline);
				
				FacesContext.getCurrentInstance().getExternalContext().redirect(checkoutURL);
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE GENERAR EL PAGO, DEBE SELECCIONAR AL MENOS UNA CUOTA!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE CARGAR EL FORMULARIO, INTENTELO MAS TARDE!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}

}
