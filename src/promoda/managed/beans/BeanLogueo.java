package promoda.managed.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import promoda.clases.CajasMov;
import promoda.clases.Helper;
import promoda.dao.DAOCuota;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOPagosCuota;
import promoda.dao.DAOPagosMatricula;
import promoda.dao.DAORole;
import promoda.dao.DAORolesVista;
import promoda.dao.DAOUsuario;
import promoda.model.Cuota;
import promoda.model.PagosCuota;
import promoda.model.Role;
import promoda.model.Usuario;

@ManagedBean(name = "beanLogueo")
@SessionScoped
public class BeanLogueo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanRoleDAO}")
	private DAORole roleDAO;
	
	@ManagedProperty(value = "#{BeanRolesVistaDAO}")
	private DAORolesVista roleVistaDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
    private DAOMatriculaAlumno matriculaAlumnoDAO;
    
    @ManagedProperty(value = "#{BeanCuotaDAO}")
    private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanPagosMatriculaDAO}")
    private DAOPagosMatricula pagosMatriculaDAO;
    
    @ManagedProperty(value = "#{BeanPagosCuotaDAO}")
    private DAOPagosCuota pagosCuotaDAO;
	
	private List<Role> listaRoles; 
	private Usuario usuario;
	private String nombreLogin;
	private String passLogin;
	private String passNueva;
	private String confirmPass;
	private String textOk;	
	private String welcomeFile = "login.xhtml";
	private int idRol;
	private boolean checkButton;
	private boolean logeado;
	private boolean admin;
	private boolean alum;
	private boolean prof;

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public DAORole getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(DAORole roleDAO) {
		this.roleDAO = roleDAO;
	}

	public DAORolesVista getRoleVistaDAO() {
		return roleVistaDAO;
	}

	public void setRoleVistaDAO(DAORolesVista roleVistaDAO) {
		this.roleVistaDAO = roleVistaDAO;
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

	public DAOPagosMatricula getPagosMatriculaDAO() {
		return pagosMatriculaDAO;
	}

	public void setPagosMatriculaDAO(DAOPagosMatricula pagosMatriculaDAO) {
		this.pagosMatriculaDAO = pagosMatriculaDAO;
	}

	public DAOPagosCuota getPagosCuotaDAO() {
		return pagosCuotaDAO;
	}

	public void setPagosCuotaDAO(DAOPagosCuota pagosCuotaDAO) {
		this.pagosCuotaDAO = pagosCuotaDAO;
	}

	public List<Role> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Role> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreLogin() {
		return nombreLogin;
	}

	public void setNombreLogin(String nombreLogin) {
		this.nombreLogin = nombreLogin;
	}

	public String getPassLogin() {
		return passLogin;
	}

	public void setPassLogin(String passLogin) {
		this.passLogin = passLogin;
	}

	public String getPassNueva() {
		return passNueva;
	}

	public void setPassNueva(String passNueva) {
		this.passNueva = passNueva;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public String getTextOk() {
		return textOk;
	}

	public void setTextOk(String textOk) {
		this.textOk = textOk;
	}

	public String getWelcomeFile() {
		return welcomeFile;
	}

	public void setWelcomeFile(String welcomeFile) {
		this.welcomeFile = welcomeFile;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public boolean isCheckButton() {
		return checkButton;
	}

	public void setCheckButton(boolean checkButton) {
		this.checkButton = checkButton;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isAlum() {
		return alum;
	}

	public void setAlum(boolean alum) {
		this.alum = alum;
	}

	public boolean isProf() {
		return prof;
	}

	public void setProf(boolean prof) {
		this.prof = prof;
	}

	public void login() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		usuario = new Usuario();
		Helper helper = new Helper();
		String hash = helper.EncodePassword(passLogin);
		usuario = usuarioDAO.getLogin(nombreLogin, hash);
		welcomeFile = "login.xhtml";
		String retorno = "";
		if (usuario.getId() != 0) {
			Role rol = usuario.getRole();
			if (rol.getId() == 1) {
				admin = true;
				alum = false;
				prof = false;
				welcomeFile = "index.xhtml";
				retorno = "index.xhtml";
			}
			if (rol.getId() == 2) {
				admin = false;
				alum = true;
				prof = false;
				welcomeFile = "inicio.xhtml";
				retorno = "inicio.xhtml";
			}
			if (rol.getId() == 3) {
				admin = false;
				alum = false;
				prof = true;
				retorno = "";
			}
			listaRoles = new ArrayList<Role>();
			listaRoles = roleDAO.getLista();
			logeado = true;
			verificarAcceso(usuario);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ " + usuario.getNombreCompleto(),
					null);
		} else {
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error! User y Pass no validas",
					null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(retorno);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void logout() {
		String host = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServerName();
		int port = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServerPort();
		StringBuffer url = new StringBuffer("http://");
		url.append(host);
		url.append(":");
		url.append(port);
		//Beta
//		url.append("/ProModa/login.xhtml");
		//Produccion
		url.append("/login.xhtml");
		String urlFinal = url.toString();
		FacesContext contexto = FacesContext.getCurrentInstance();
		try {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.invalidate();
			usuario = new Usuario();
			logeado = false;
			cambiarEstado(false);
			contexto.getExternalContext().redirect(urlFinal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setting(){
		passNueva = "";
		confirmPass = "";
		listaRoles = roleDAO.getLista();
		idRol = usuario.getRole().getId();
		checkButton = false;
	}
	
	public void handleKeyEvent() {
		if (passNueva.equals(confirmPass)) {
			textOk = "Ok";
			checkButton = true;
		} else {
			textOk = "No";
			checkButton = false;
		}
	}
	
	public void editarUsuario(){
		FacesMessage msg = null;
		if(!usuario.getUsername().isEmpty()){
			Usuario user = usuarioDAO.get(usuario.getUsername());
			if (user.getId() != 0 && user.getId() != usuario.getId()) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "YA EXISTEN USUARIOS CON ESE USERNAME! MODIFIQUELO POR FAVOR!", null);
			} else {
				Role role = new Role();
				role.setId(idRol);
				usuario.setFechaMod(new Date());
				usuario.setRole(role);
				usuario.setUsuario3(usuario);
				if(!passNueva.isEmpty() && textOk.equals("Ok")){
					Helper helper = new Helper();
					String password = helper.EncodePassword(passNueva);
					usuario.setPassword(password);
				}
				if(usuarioDAO.update(usuario) != 0){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO MODIFICADO!", null);
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL MODIFICAR EL USUARIO, "
							+ "INTÉNTELO NUEVAMENTE!", null);
				}
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "EL CAMPO USERNAME NO PUEDE ESTAR VACÍO!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private void cambiarEstado(boolean estado){
//		menuCaja = estado;
//		menuCliente = estado;
//		menuCompra = estado;
//		menuConsignacion = estado;
//		menuCreditoDebitoCliente = estado;
//		menuCreditoDebitoProveedor = estado;
//		menuGasto = estado;
//		menuListaPrecio = estado;
//		menuPagoCliente = estado;
//		menuPagoProveedor = estado;
//		menuProducto = estado;
//		menuProveedor = estado;
//		menuRol = estado;
//		menuUsuario = estado;
//		menuVenta = estado;
//		menuGarantia = estado;
//		menuDevolucion = estado;
//		menuRankingCliente = estado;
//		menuRankingProducto = estado;
//		menuRankingProveedor = estado;
//		menuGanancia = estado;
//		menuPatrimonio = estado;
//		menuAccesorio = estado;
//		menuUsado = estado;
//		menuBusqueda = estado;
//		submenuCreditoDebito = estado;
//		submenuPago = estado;
//		submenuPagoMovimiento = estado;
//		submenuPersona = estado;
//		submenuProducto = estado;
//		submenuUsuario = estado;
//		submenuVenta = estado;
//		submenuGarantiasDevoluciones = estado;
//		submenuReportes = estado;
	}
	
	private void verificarAcceso(Usuario user){
		cambiarEstado(false);
		Role role = user.getRole();
		if(role.getId() == 1){
			cambiarEstado(true);
		}
		if(role.getId() != 1){
//			List<RolesVista> listAux = roleVistaDAO.getLista(role);
//			for (RolesVista rolesVista : listAux) {
//				Vista vista = rolesVista.getVista();
//				if(vista.getNombre().equals("Caja")){
//					menuCaja = true;
//				}
//				if(vista.getNombre().equals("Clientes")){
//					menuCliente = true;
//				}
//				if(vista.getNombre().equals("Compras")){
//					menuCompra = true;
//				}
//				if(vista.getNombre().equals("Consignacion")){
//					menuConsignacion = true;
//				}
//				if(vista.getNombre().equals("Credito/Debito Cliente")){
//					menuCreditoDebitoCliente = true;
//				}
//				if(vista.getNombre().equals("Credito/Debito Proveedor")){
//					menuCreditoDebitoProveedor = true;
//				}
//				if(vista.getNombre().equals("Gastos")){
//					menuGasto = true;
//				}
//				if(vista.getNombre().equals("Listas de Precio")){
//					menuListaPrecio = true;
//				}
//				if(vista.getNombre().equals("Pagos de Cliente")){
//					menuPagoCliente = true;
//				}
//				if(vista.getNombre().equals("Pagos de Proveedor")){
//					menuPagoProveedor = true;
//				}
//				if(vista.getNombre().equals("Moviles")){
//					menuProducto = true;
//				}
//				if(vista.getNombre().equals("Proveedores")){
//					menuProveedor = true;
//				}
//				if(vista.getNombre().equals("Roles")){
//					menuRol = true;
//				}
//				if(vista.getNombre().equals("Usuarios")){
//					menuUsuario = true;
//				}
//				if(vista.getNombre().equals("Ventas")){
//					menuVenta = true;
//				}
//				if(vista.getNombre().equals("Garantia")){
//					menuGarantia = true;
//				}
//				if(vista.getNombre().equals("Devolucion")){
//					menuDevolucion = true;
//				}
//				if(vista.getNombre().equals("Ranking Clientes")){
//					menuRankingCliente = true;
//				}
//				if(vista.getNombre().equals("Ranking Proveedores")){
//					menuRankingProveedor = true;
//				}
//				if(vista.getNombre().equals("Ranking Productos")){
//					menuRankingProducto = true;
//				}
//				if(vista.getNombre().equals("Ganancias")){
//					menuGanancia = true;
//				}
//				if(vista.getNombre().equals("Patrimonio")){
//					menuPatrimonio = true;
//				}
//				if(vista.getNombre().equals("Accesorios")){
//					menuAccesorio = true;
//				}
//				if(vista.getNombre().equals("Moviles Usados")){
//					menuUsado = true;
//				}
//				if(vista.getNombre().equals("Busqueda")){
//					menuBusqueda = true;
//				}
//			}
//			if(menuCliente || menuProveedor){
//				submenuPersona = true;
//			}
//			if(menuProducto || menuListaPrecio || menuCompra || menuAccesorio || menuUsado){
//				submenuProducto = true;
//			}
//			if(menuRol || menuUsuario){
//				submenuUsuario = true;
//			}
//			if(menuPagoCliente || menuPagoProveedor){
//				submenuPago = true;
//			}
//			if(menuCreditoDebitoCliente || menuCreditoDebitoProveedor){
//				submenuCreditoDebito = true;
//			}
//			if(menuGasto || menuCaja || submenuPago || submenuCreditoDebito){
//				submenuPagoMovimiento = true;
//			}
//			if(menuConsignacion || menuVenta){
//				submenuVenta = true;
//			}
//			if(menuGarantia || menuDevolucion){
//				submenuGarantiasDevoluciones = true;
//			}
//			if(menuRankingCliente || menuRankingProveedor || menuRankingProducto || menuPatrimonio || menuGanancia){
//				submenuReportes = true;
//			}
		}
	}
	
	public void postLoad() {
		if (logeado) {
			String retorno = "";
			if (admin) {
				retorno = "index.xhtml";
			}
			if (alum) {
				retorno = "inicio.xhtml";
			}
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(retorno);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void actualizaPagosCuotas() {
		try {			
			List<PagosCuota> listaPagosCuota = pagosCuotaDAO.getLista(true);
			System.out.println("listaPagosCuota size() " + listaPagosCuota.size());
			for (PagosCuota pagosCuota : listaPagosCuota) {
				CajasMov cajaMov = new CajasMov();
				pagosCuota.setEnabled(false);
				pagosCuota.setFechaBaja(new Date());
				pagosCuota.setUsuario2(usuario);
				int idPagoCuo = pagosCuotaDAO.update(pagosCuota);
				int idMovCaja = cajaMov.eliminarMovimiento(idPagoCuo, "PagosCuota", usuario);
				System.out.println("Pagos Cuota Id " + pagosCuota.getId());
				System.out.println("Update pagos cuota " + idPagoCuo);
				System.out.println("Movimiento caja " + idMovCaja);
			}
			System.out.println("Finalizo delete listaPagosCuota");
			List<Cuota> listaCuotas = cuotaDAO.getLista(true, true);
			System.out.println("listaCuotas size() " + listaCuotas.size());
			for (Cuota cuota : listaCuotas) {
				CajasMov cajaMov = new CajasMov();
				PagosCuota pagoscuota = new PagosCuota();
	    		pagoscuota.setAlumno(cuota.getAlumno());
	    		pagoscuota.setCuota(cuota);
	    		pagoscuota.setFecha(cuota.getFechaPago());
	    		pagoscuota.setConcepto(cuota.getDetalle());
	    		pagoscuota.setMonto(cuota.getMontoPago());
	    		pagoscuota.setUsuario1(usuario);
	    		pagoscuota.setFechaAlta(cuota.getFechaPago());
	    		pagoscuota.setEnabled(true);
	    		int idPagoCuot = pagosCuotaDAO.insertar(pagoscuota);
	    		int idMovCaja = cajaMov.generarMovimiento(cuota.getFechaPago(), 1, cuota.getMontoPago(), idPagoCuot, "PagosCuota", "Pago Cuota", 
	    				"Pago de " + cuota.getDetalle() + " de Curso " + cuota.getCurso().getNombre() + " de " + cuota.getAlumno().getNombreCompleto(), usuario);
				System.out.println("Insert pagos cuota " + idPagoCuot);
				System.out.println("Movimiento caja " + idMovCaja);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso finalizado", null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
		}
	}
	
//	public void actualizaPagosMatriculas() {
//		try {
//			List<PagosMatricula> listaPagosMatriculas = pagosMatriculaDAO.getLista(true);
//			int size = listaPagosMatriculas.size();
//			System.out.println("listaPagosMatriculas size() " + size);
//			int c = 1;
//			for (PagosMatricula pagosMatricula : listaPagosMatriculas) {
//				CajasMov cajaMov = new CajasMov();
//				pagosMatricula.setEnabled(false);
//				pagosMatricula.setFechaBaja(new Date());
//				pagosMatricula.setUsuario2(usuario);
//				if (pagosMatricula.getAlumno() != null && pagosMatricula.getMatricula() != null) {
//					MatriculaAlumno matriculaAlumno = matriculaAlumnoDAO.get(pagosMatricula.getAlumno(), pagosMatricula.getMatricula().getCurso(), pagosMatricula.getMatricula());
//					matriculaAlumno.setMontoPago(pagosMatricula.getMonto());
//					int idMatAlum = matriculaAlumnoDAO.update(matriculaAlumno);
//					System.out.println("Update matricula alumno" + idMatAlum);
//				}				
//				int idPagoMatri = pagosMatriculaDAO.update(pagosMatricula);
//				int actualizoCaja = cajaMov.eliminarMovimiento(idPagoMatri, "PagosMatricula", usuario);
//				System.out.println("Pagos Matricula Id " + pagosMatricula.getId());				
//				System.out.println("Update pagos matricula " + idPagoMatri);
//				System.out.println("Actualizo caja " + actualizoCaja);
//				System.out.println("Movimiento " + c + " de " + size);
//				c++;
//			}
//			System.out.println("Finalizo delete listaPagosMatriculas");
//			List<MatriculaAlumno> listaMatriculaAlumnos = matriculaAlumnoDAO.getLista(true);
//			int sizeM = listaMatriculaAlumnos.size();
//			System.out.println("listaMatriculaAlumnos size() " + sizeM);
//			int cM = 1;
//			for (MatriculaAlumno matriculaAlumno : listaMatriculaAlumnos) {
//				CajasMov cajaMov = new CajasMov();
//				PagosMatricula pagosMatricula = new PagosMatricula();
//				pagosMatricula.setFecha(matriculaAlumno.getFechaPago());
//				pagosMatricula.setMatricula(matriculaAlumno.getMatricula());
//				pagosMatricula.setAlumno(matriculaAlumno.getAlumno());
//				pagosMatricula.setUsuario1(usuario);
//				pagosMatricula.setFechaAlta(new Date());
//				pagosMatricula.setEnabled(true);
//				pagosMatricula.setConcepto("");        		
//				pagosMatricula.setMonto(matriculaAlumno.getMontoPago());				
//				int idPagoMatricula = pagosMatriculaDAO.insertar(pagosMatricula);
//				int actualizoCaja = cajaMov.generarMovimiento(matriculaAlumno.getFechaPago(), 1, pagosMatricula.getMonto(), idPagoMatricula, "PagosMatricula", "Pago Matricula", 
//        				"Pago de Matrícula de Curso " + matriculaAlumno.getCurso().getNombre() + ", de " + matriculaAlumno.getAlumno().getNombreCompleto(), usuario);				
//				System.out.println("Insert pagos matricula " + idPagoMatricula);
//				System.out.println("Actualizo caja " + actualizoCaja);
//				System.out.println("Movimiento " + cM + " de " + sizeM);
//				cM++;
//			}
//			System.out.println("Finalizo insert listaMatriculaAlumnos");
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso finalizado", null));
//		} catch (Exception e) {
//			e.printStackTrace();
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
//		}
//	}

}
