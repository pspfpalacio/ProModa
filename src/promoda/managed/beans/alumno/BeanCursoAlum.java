package promoda.managed.beans.alumno;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.codehaus.jettison.json.JSONObject;

import com.mercadopago.MP;

import promoda.dao.DAOCuota;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOPagosMatricula;
import promoda.dao.DAOParametro;
import promoda.dao.DAOUsuario;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.Curso;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.PagosMatricula;
import promoda.model.Parametro;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanCursoAlum implements Serializable {

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
	
	@ManagedProperty(value = "#{BeanPagosMatriculaDAO}")
    private DAOPagosMatricula pagosMatriculaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
    private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanParametroDAO}")
	private DAOParametro parametroDAO;
	
	private List<Curso> listaCursos;
	private List<Curso> filteredCursos;
	private List<Cuota> listaCuotas;
	private List<Cuota> filteredCuotas;
	private Curso curso;
	private Matricula matricula;
	private Usuario usuario;
	private Alumno alumno;
	private PagosMatricula pagosMatricula;	
	private MatriculaAlumno matriculaAlumno;
	private MP mercadoPago;
	private Parametro parametro;
	private String headerText;
	private String accesToken;
	private String checkoutURL;
	private String preapprovalPaymentURL;
	private String jsonId;

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

	public List<Cuota> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<Cuota> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public List<Cuota> getFilteredCuotas() {
		return filteredCuotas;
	}

	public void setFilteredCuotas(List<Cuota> filteredCuotas) {
		this.filteredCuotas = filteredCuotas;
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
	
	public List<Curso> getListaPorInscripcion() {
		List<Curso> listAuxCursos = new ArrayList<Curso>();
		try {
			SimpleDateFormat formatoDia = new SimpleDateFormat("dd");
			SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
			SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy");
			Date fechaUno;
			Date fechaDos = new Date();
			int mesInicio = Integer.parseInt(formatoMes.format(fechaDos));
			mesInicio = mesInicio + 6;
			String dia = formatoDia.format(fechaDos);
			String mes;
			String anio = formatoAnio.format(fechaDos);
			String fechaInicio;
			if (mesInicio < 10) {
				mes = "0" + Integer.toString(mesInicio);  
			} else {
				mes = Integer.toString(mesInicio);
			}
			fechaInicio = dia + mes + anio;
			fechaUno = formatoFecha.parse(fechaInicio);
			listAuxCursos = cursoDAO.getListaMatVig(fechaUno, fechaDos);
			return listAuxCursos;
		} catch (Exception e) {
			return listAuxCursos;
		}		
	}

	public String goInscripto(Usuario user) {
		usuario = new Usuario();
		alumno = new Alumno();
		parametro = new Parametro();
		usuario = user;
		alumno = user.getAlumno();
//		parametro = parametroDAO.get(1);
		listaCursos = new ArrayList<Curso>();
		filteredCursos = new ArrayList<Curso>();
		listaCursos = matriculaAlumnoDAO.getListaCursoDistinct(alumno);
		filteredCursos = listaCursos;
//		try {
//			mercadoPago = new MP(parametro.getUsuarioMp(), parametro.getPasswordMp());
//			accesToken = mercadoPago.getAccessToken();
//			System.out.println(accesToken);
//			MP mp = new MP ("2128126708610174", "iJOnLNyV4uTP9pd7XO1ZCavev7qrxxMD");
//			
//			JSONObject preference = mp.createPreference("{'items':[{'id':1,'title':'Prueba Ejemplo','description':'Esta es una descripcion','quantity':1,'currency_id':'ARS','unit_price':100.5}],"
//					+ "'payer':{'name':'Franco','surname':'Palacio','email':'franko_1514@hotmail.com','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}},"
//					+ "'expires':true,"
//					+ "'expiration_date_from':'2016-10-15T12:00:12.914-04:00',"
//					+ "'expiration_date_to':'2016-10-16T14:00:12.914-04:00'}");
//			
//			System.out.println(preference.toString());
//			
//			jsonId = preference.getJSONObject("response").getString("id");
//			
//			checkoutURL = preference.getJSONObject("response").getString("init_point");
//			
//			System.out.println(checkoutURL);
			
			// Sets the filters you want
//	        Map<String, Object> filters = new HashMap<String, Object> ();
//	            filters.put("range", "date_created");
//	            filters.put("begin_date", "NOW-1MONTH");
//	            filters.put("end_date", "NOW");
//	            filters.put("status", "approved");
//	            filters.put("operation_type", "regular_payment");
//	        // Search payment data according to filters
//	        JSONObject searchResult = mp.searchPayment (filters);
//	        JSONArray results = searchResult.getJSONObject("response").getJSONArray("results");
//	        System.out.println(results);
//			JSONObject preapprovalPayment = mp.createPreapprovalPayment("{'payer_email':'franko_1514@hotmail.com','back_url':'http://www.my_site.com','reason':'Pago de Curso','external_reference':'OP-1234','auto_recurring':{'frequency':1,'frequency_type':'months','transaction_amount':60,'currency_id':'BRL','start_date':'2012-12-10T14:58:11.778-03:00','end_date':'2013-06-10T14:58:11.778-03:00'}}");
//		    preapprovalPaymentURL = preapprovalPayment.getJSONObject("response").getString("init_point");
			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		return "inscripto";
	}
	
	public void verMatricula(Curso cur) {
		matricula = new Matricula();
		curso = new Curso();
		matriculaAlumno = new MatriculaAlumno();
		matricula = cur.getMatricula();
		curso = cur;
		matriculaAlumno = matriculaAlumnoDAO.get(alumno, cur, matricula);
		if(matriculaAlumno.getId() != 0){			
    		if(!matriculaAlumno.getPago()){
    			pagosMatricula = new PagosMatricula();    			
    		} else {
    	    	pagosMatricula = pagosMatriculaDAO.get(alumno, matricula);
    		}       		
    	}
	}
	
	public void verCuotas(Curso cur) {
		matricula = new Matricula();
		curso = new Curso();
		matriculaAlumno = new MatriculaAlumno();
		matricula = cur.getMatricula();
		curso = cur;
		listaCuotas = new ArrayList<Cuota>();
		filteredCuotas = new ArrayList<Cuota>();
		listaCuotas = cuotaDAO.getLista(alumno, matricula, cur);
		filteredCuotas = listaCuotas;
//		for (Cuota cuota : listaCuotas) {
//			System.out.println(cuota.getDetalle());
//		}
	}
	
	public void generarPagoCuota(Cuota cuo) {
		try {
			//mercadoPago = new MP("2128126708610174", "iJOnLNyV4uTP9pd7XO1ZCavev7qrxxMD");
//			MP mp = new MP ("2128126708610174", "iJOnLNyV4uTP9pd7XO1ZCavev7qrxxMD");
//			
//			JSONObject preference = mp.getPreference(jsonId);
//
//			System.out.println(preference.toString());
//			
//			System.out.println(preference.get("status").toString());
//			
//			if (Integer.parseInt (preference.get("status").toString()) == 200) {
//			    System.out.println(preference.get("response"));
//			}
			
			
//			MP mp = new MP ("2128126708610174", "iJOnLNyV4uTP9pd7XO1ZCavev7qrxxMD");
			float porcentajePV = cuo.getPorcentajePv();
			float porcentajeSV = cuo.getPorcentajeSv();
			float montoP = cuo.getMonto();
			Date fechaPV = cuo.getFechaVencimiento();
			Date fechaSV = cuo.getSegundoVencimiento();
			Date fechaP = cuo.getFechaPago();
			if (fechaP != null) {
				int comparacionPV = fechaP.compareTo(fechaPV);
				int comparacionSV = fechaP.compareTo(fechaSV);
				if (comparacionPV == 1) {
					if (comparacionSV == 1) {
						montoP = montoP + (montoP * (porcentajeSV / 100));
					} else {
						montoP = montoP + (montoP * (porcentajePV / 100));
					}
				} 
			}
//			Date fechaHoy = new Date();
//			SimpleDateFormat formatoFecha = new SimpleDateFormat("aaaa-MM-dd");
//			String fechaPreference = formatoFecha.format(fechaHoy);
//			System.out.println(fechaPreference);
			
			JSONObject preference = mercadoPago.createPreference("{'items':[{'id':1,'title':'" + curso.getNombre() + " - " + cuo.getDetalle() + "','description':'" + curso.getNombre() + " - " + cuo.getDetalle() + "','quantity':1,'currency_id':'ARS','unit_price':" + montoP + "}],"
					+ "'payer':{'name':'" + alumno.getNombres() + "','surname':'" + alumno.getApellido() + "','email':'" + usuario.getEmail() + "','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}}}");
			
//			System.out.println(preference.toString());
			
			jsonId = preference.getJSONObject("response").getString("id");
			
			checkoutURL = preference.getJSONObject("response").getString("init_point");
			
//			System.out.println(checkoutURL);
			
//			String sandboxCheckoutURL = preference.getJSONObject("response").getString("sandbox_init_point");
			
//			System.out.println(sandboxCheckoutURL);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(checkoutURL);
			
			
//			JSONObject createPreferenceResult = mp.createPreference("{'items':[{'id':1,'title':'Prueba Ejemplo','description':'Esta es una descripcion','quantity':1,'currency_id':'ARS','unit_price':10.5}],"
//					+ "'payer':{'name':'Franco','surname':'Palacio','email':'franko_1514@hotmail.com','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}}}");
//			
//			System.out.println(createPreferenceResult.toString());
			
//			JSONObject payment_info = mp.getPayment(jsonId);
//
//			// Show payment information
//			System.out.println(payment_info.get("status").toString());
//			if (Integer.parseInt (payment_info.get("status").toString()) == 200) {
//			    System.out.println(payment_info.get("response"));
//			}
			
//			String JSON = "{'items':[{'id':1,'title':'Prueba Ejemplo','description':'Esta es una descripcion','category_id':'','picture_url':'','currency_id':'ARS','quantity':1,'unit_price':10.5}],"
//					+ "'payer':{'name':'Franco','surname':'Palacio','email':'franko_1514@hotmail.com','date_created':'','phone':{'area_code':'','number':''},'identification':{'type':'','number':''},'address':{'street_name':'','street_number':null,'zip_code':''}},"
//					+ "'back_urls':{'success':'www.cursospromoda.com','pending':'','failure':'www.cursospromoda.com'},"
//					+ "'auto_return':'',"
//					+ "'payment_methods':{'excluded_payment_methods':[{'id':'visa'}],'excluded_payment_types':[{'id':''}],'installments':null,'default_payment_method_id':null,'default_installments':null},"
//					+ "'client_id':'963',"
//					+ "'marketplace':'NONE',"
//					+ "'marketplace_fee':0,"
//					+ "'shipments':{'receiver_address':{'zip_code':'','street_number':null,'street_name':'','floor':'','apartment':''}},"
//					+ "'notification_url':null,"
//					+ "'external_reference':'',"
//					+ "'additional_info':'',"
//					+ "'expires':false,"
//					+ "'expiration_date_from':null,"
//					+ "'expiration_date_to':null,"
//					+ "'date_created':'2016-10-15T11:22:39.417-04:00',"
//					+ "'id':'189479576-f60d0e0e-ae91-4dcd-aeda-1f18f6afea0a',"
//					+ "'init_point':'https:\/\/www.mercadopago.com\/mla\/checkout\/start?pref_id=189479576-f60d0e0e-ae91-4dcd-aeda-1f18f6afea0a',"
//					+ "'sandbox_init_point':'https:\/\/sandbox.mercadopago.com\/mla\/checkout\/pay?pref_id=189479576-f60d0e0e-ae91-4dcd-aeda-1f18f6afea0a'}}";

//			String accessToken = mp.getAccessToken();
//
//			System.out.println(accessToken);
//
//			JSONObject paymentMethods = mp.get ("/v1/payment_methods");
//
//			System.out.println(paymentMethods.toString());
//			
//			JSONObject payment = mp.get ("/v1/payments/rapipago");
//
//			System.out.println(payment.toString());
//			
//
//			JSONObject preference = mp.createPreference("{'items':[{'title':'Curso Ejemplo','quantity':1,'currency_id':'ARS','unit_price':200}]}");
//			
//			checkoutURL = preference.getJSONObject("response").getString("init_point");
//			
//			System.out.println(checkoutURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}		
	}

}
