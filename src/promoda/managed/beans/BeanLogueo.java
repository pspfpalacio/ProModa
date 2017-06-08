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

import promoda.clases.Helper;
import promoda.dao.DAORole;
import promoda.dao.DAORolesVista;
import promoda.dao.DAOUsuario;
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
		url.append("/ProModa/login.xhtml");
		//Produccion
//		url.append("/login.xhtml");
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

}
