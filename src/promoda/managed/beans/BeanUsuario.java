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

import promoda.clases.Helper;
import promoda.dao.DAORole;
import promoda.dao.DAOUsuario;
import promoda.model.Role;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanRoleDAO}")
	private DAORole roleDAO;
	
	private List<Usuario> listaUsuarios;
	private List<Usuario> filteredUsuarios;
	private List<Role> listaRoles;
	private Usuario usuario;
	private Usuario user;
	private String headerText;
	private String pass;
	private String repeatPass;
	private String texto;
	private int idRol;
	private boolean checkPass;

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

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
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

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public boolean isCheckPass() {
		return checkPass;
	}

	public void setCheckPass(boolean checkPass) {
		this.checkPass = checkPass;
	}

	public String goUsuarios(Usuario u) {
		user = new Usuario();
		user = u;
		listaUsuarios = new ArrayList<Usuario>();
		filteredUsuarios = new ArrayList<Usuario>();
		listaUsuarios = usuarioDAO.getLista();
		filteredUsuarios = listaUsuarios;
		listaRoles = new ArrayList<Role>();
		listaRoles = roleDAO.getLista();
		return "usuarios";
	}
	
	public String goNuevo() {
		headerText = "Usuario Nuevo";
		usuario = new Usuario();
		checkPass = false;
		idRol = 0;
		return "usuario";
	}
	
	public String goEditar(Usuario u) {
		headerText = "Editar Usuario";
		usuario = new Usuario();
		usuario = u;
		checkPass = false;
		idRol = 0;
		idRol = u.getRole().getId();
		repeatPass = "";
		return "usuario";
	}
	
	public void baja(Usuario u) {
		FacesMessage msg = null;
		u.setEnabled(false);
		u.setFechaBaja(new Date());
		u.setUsuario2(user);
		Role r = roleDAO.get(u.getRole().getId());
		if (usuarioDAO.update(u) != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE USUARIO " + r.getNombre() + " EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN LA BAJA DEL USUARIO" + r.getNombre() + ", "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void alta(Usuario u) {
		FacesMessage msg = null;
		u.setEnabled(true);
		u.setFechaAlta(new Date());
		u.setUsuario1(user);
		Role r = roleDAO.get(u.getRole().getId());
		if (usuarioDAO.update(u) != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALTA DE USUARIO " + r.getNombre() + " EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN EL ALTA DEL USUARIO " + r.getNombre() + ", "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void generarReporte() {
		
	}
	
	public void handleKeyEvent() {
		if (pass.equals(repeatPass)) {
			texto = "Ok";
			checkPass = false;
		} else {
			texto = "No";
			checkPass = true;
		}
	}
	
	public String guardar() {
		FacesMessage msg = null;
		String retorno = "";	
		Usuario userIngresa = usuarioDAO.get(usuario.getUsername());
		System.out.println(userIngresa.getId());
		System.out.println(user.getId());		
		if (usuario.getId() != 0) {
			if (!repeatPass.isEmpty()) {
				Helper helper = new Helper();
				String password = helper.EncodePassword(repeatPass);
				usuario.setPassword(password);
			}
			String nombreCompleto = usuario.getApellido() + ", " + usuario.getNombre();
			Role rol = new Role();
			rol.setId(idRol);
			usuario.setNombreCompleto(nombreCompleto);
			usuario.setRole(rol);
			usuario.setUsuario3(usuario);
			usuario.setFechaMod(new Date());
			int updateUsuario = usuarioDAO.update(usuario);
			if (updateUsuario != 0) {
				listaUsuarios = new ArrayList<Usuario>();
				filteredUsuarios = new ArrayList<Usuario>();
				listaUsuarios = usuarioDAO.getLista();
				filteredUsuarios = listaUsuarios;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO REGISTRADO!", null);
				retorno = "usuarios";
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL USUARIO, "
						+ "INTENTELO NUEVAMENTE!", null);
			}
		} else {
			if (pass.isEmpty()) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "LA CONTRASEÑA ES OBLIGATORIA", null);
			} else {
				Helper helper = new Helper();
				String password = helper.EncodePassword(repeatPass);
				String nombreCompleto = usuario.getApellido() + ", " + usuario.getNombre();
				Role rol = new Role();
				rol.setId(idRol);
				usuario.setPassword(password);
				usuario.setNombreCompleto(nombreCompleto);
				usuario.setRole(rol);
				usuario.setUsuario1(user);
				usuario.setEnabled(true);
				usuario.setFechaAlta(new Date());
				int idUsuario = usuarioDAO.insertar(usuario);
				if (idUsuario != 0) {
					listaUsuarios = new ArrayList<Usuario>();
					filteredUsuarios = new ArrayList<Usuario>();
					listaUsuarios = usuarioDAO.getLista();
					filteredUsuarios = listaUsuarios;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO REGISTRADO!", null);
					retorno = "usuarios";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL USUARIO, "
							+ "INTENTELO NUEVAMENTE!", null);
				}
			}
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-usuario.jpg";
         
        pdf.add(Image.getInstance(logo));
    }

}
