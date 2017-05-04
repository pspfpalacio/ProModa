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

import promoda.dao.DAODomicilio;
import promoda.dao.DAOLocalidad;
import promoda.dao.DAOProfesor;
import promoda.dao.DAOProvincia;
import promoda.dao.DAOUsuario;
import promoda.model.Domicilio;
import promoda.model.Localidade;
import promoda.model.Profesore;
import promoda.model.Provincia;
import promoda.model.Role;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanProfesor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanProfesorDAO}")
	private DAOProfesor profesorDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanProvinciaDAO}")
	private DAOProvincia provinciaDAO;
	
	@ManagedProperty(value = "#{BeanLocalidadDAO}")
	private DAOLocalidad localidadDAO;
	
	@ManagedProperty(value = "#{BeanDomicilioDAO}")
	private DAODomicilio domicilioDAO;
	
	private List<Profesore> listaProfesores;
	private List<Profesore> filteredProfesores;
	private List<Provincia> listaProvincias;
	private List<Localidade> listaLocalidades;
	private Profesore profesore;
	private Domicilio domicilio;
	private Usuario usuario;
	private String headerText;
	private int idProvincia;
	private int idLocalidad;
	private boolean editar;
	
	private static String _PASSWORD = "WdUjxKFLegTkGNtfAJLVzAEZvIr4i5xSpct/V6SF9lkTA4gxrhsmONnCTOw3ic+i3LGYL58gWxOJnzZtmg1Ypw==";
	private static int _ROL = 3;

	public DAOProfesor getProfesorDAO() {
		return profesorDAO;
	}

	public void setProfesorDAO(DAOProfesor profesorDAO) {
		this.profesorDAO = profesorDAO;
	}

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public DAOProvincia getProvinciaDAO() {
		return provinciaDAO;
	}

	public void setProvinciaDAO(DAOProvincia provinciaDAO) {
		this.provinciaDAO = provinciaDAO;
	}

	public DAOLocalidad getLocalidadDAO() {
		return localidadDAO;
	}

	public void setLocalidadDAO(DAOLocalidad localidadDAO) {
		this.localidadDAO = localidadDAO;
	}

	public DAODomicilio getDomicilioDAO() {
		return domicilioDAO;
	}

	public void setDomicilioDAO(DAODomicilio domicilioDAO) {
		this.domicilioDAO = domicilioDAO;
	}

	public List<Profesore> getListaProfesores() {
		return listaProfesores;
	}

	public void setListaProfesores(List<Profesore> listaProfesores) {
		this.listaProfesores = listaProfesores;
	}

	public List<Profesore> getFilteredProfesores() {
		return filteredProfesores;
	}

	public void setFilteredProfesores(List<Profesore> filteredProfesores) {
		this.filteredProfesores = filteredProfesores;
	}

	public List<Provincia> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(List<Provincia> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public List<Localidade> getListaLocalidades() {
		return listaLocalidades;
	}

	public void setListaLocalidades(List<Localidade> listaLocalidades) {
		this.listaLocalidades = listaLocalidades;
	}

	public Profesore getProfesore() {
		return profesore;
	}

	public void setProfesore(Profesore profesore) {
		this.profesore = profesore;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
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
	
	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public String goProfesores(Usuario user) {
		usuario = new Usuario();
		usuario = user;
		listaProfesores = new ArrayList<Profesore>();
		filteredProfesores = new ArrayList<Profesore>();
		listaProfesores = profesorDAO.getLista(true);
		filteredProfesores = listaProfesores;
		listaProvincias = new ArrayList<Provincia>();
		listaProvincias = provinciaDAO.getLista();
		listaLocalidades = new ArrayList<Localidade>();
		return "profesores";
	}
	
	public String goNuevo() {
		headerText = "Profesor Nuevo";
		profesore = new Profesore();
		domicilio = new Domicilio();
		idProvincia = 0;
		idLocalidad = 0;
		editar = false;
		listaLocalidades = new ArrayList<Localidade>();
		return "profesor";
	}
	
	public String goEditar(Profesore profe) {
		headerText = "Editar Profesor";
		profesore = new Profesore();
		domicilio = new Domicilio();
		profesore = profe;
		idProvincia = 0;
		idLocalidad = 0;
		editar = true;
		domicilio = profe.getDomicilio();
		idLocalidad = domicilio.getLocalidade().getId();
		Localidade loc = localidadDAO.get(idLocalidad);
		idProvincia = loc.getProvincia().getId();
		listaLocalidades = new ArrayList<Localidade>();
		listaLocalidades = localidadDAO.getLista(loc.getProvincia());
		return "profesor";
	}
	
	public void baja(Profesore profe) {
		FacesMessage msg = null;
		profe.setEnabled(false);
		profe.setFechaBaja(new Date());
		profe.setUsuario3(usuario);
		Usuario user = usuarioDAO.get(profe);
		user.setEnabled(false);
		user.setFechaBaja(new Date());
		user.setUsuario2(usuario);
		int bajaUser = usuarioDAO.update(user);
		int bajaProfe = profesorDAO.update(profe);
		if (bajaProfe != 0 && bajaUser != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE PROFESOR EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRI” UN ERROR EN LA BAJA DEL PROFESOR, "
					+ "INT…NTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void alta(Profesore profe) {
		FacesMessage msg = null;
		profe.setEnabled(true);
		profe.setFechaAlta(new Date());
		profe.setUsuario2(usuario);
		Usuario user = usuarioDAO.get(profe);
		user.setEnabled(true);
		user.setFechaAlta(new Date());
		user.setUsuario1(usuario);
		int altaUser = usuarioDAO.update(user);
		int altaProfe = profesorDAO.update(profe);
		if (altaProfe != 0 && altaUser != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALTA DE PROFESOR EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRI” UN ERROR EN EL ALTA DEL PROFESOR, "
					+ "INT…NTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void getPorDni(int dni) {
		profesore = new Profesore();
		profesore = profesorDAO.getPorDni(dni);		
		if (profesore.getId() == 0) {
			profesore.setDni(dni);
			domicilio = new Domicilio();
			idLocalidad = 0;
			idProvincia = 0;
		} else {
			domicilio = new Domicilio();
			domicilio = profesore.getDomicilio();
			idLocalidad = domicilio.getLocalidade().getId();
			Localidade loc = localidadDAO.get(idLocalidad);
			idProvincia = loc.getProvincia().getId();
		}
	}
	
	public void generarReporte() {
		
	}
	
	/*
	 * FALTA LA PARTE DE DONDE PODEMOS RELACIONAR LOS PROFESORES CON LAS MATERIAS
	 */
	
	/* Funcion para calcular edad*/
	/*
	 	public int edad(String fecha_nac) {     //fecha_nac debe tener el formato dd/MM/yyyy
	   
		    Date fechaActual = new Date();
		    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		    String hoy = formato.format(fechaActual);
		    String[] dat1 = fecha_nac.split("/");
		    String[] dat2 = hoy.split("/");
		    int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
		    int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
		    if (mes < 0) {
		      anos = anos - 1;
		    } else if (mes == 0) {
		      int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
		      if (dia > 0) {
		        anos = anos - 1;
		      }
	    	}
		    return anos;
		}
		
		public static int calcularEdad(String fecha) {
			String datetext = fecha;
			try {
			Calendar birth = new GregorianCalendar();
			Calendar today = new GregorianCalendar();
			int age=0;
			int factor=0;
			Date birthDate=new SimpleDateFormat("dd-MM-yyyy").parse(datetext);
			Date currentDate=new Date(); //current date
			birth.setTime(birthDate);
			today.setTime(currentDate);
			if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) {
			if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
			if (today.get(Calendar.DATE) > birth.get(Calendar.DATE)) {
			factor = -1; //Aun no celebra su cumplea√±os
			}
			} else {
			factor = -1; //Aun no celebra su cumplea√±os
			}
			}
			age=(today.get(Calendar.YEAR)-birth.get(Calendar.YEAR))+factor;
			return age;
			} catch (ParseException e) {
			return -1;
			}
			
		}
	 */
	
	public void onChangeProvincia() {
		listaLocalidades = new ArrayList<Localidade>();
		if (idProvincia != 0) {
			Provincia prov = new Provincia();
			prov = provinciaDAO.get(idProvincia);
			listaLocalidades = localidadDAO.getLista(prov);
		}
	}
	
	public String guardar() {
		String retorno = "";
		FacesMessage msg = null;
		if (profesore.getId() != 0) {
			Usuario user = usuarioDAO.get(profesore);
			String nombreCompleto = profesore.getApellido() + ", " + profesore.getNombre();
			user.setNombreCompleto(nombreCompleto);
			user.setNombre(profesore.getNombre());
			user.setApellido(profesore.getApellido());
			profesore.setNombreCompleto(nombreCompleto);
			profesore.setUsuario4(usuario);
			profesore.setFechaMod(new Date());
			profesore.setEnabled(true);
			Localidade loc = new Localidade();
			loc.setId(idLocalidad);
			domicilio.setLocalidade(loc);
			domicilio.setUsuario3(usuario);
			domicilio.setFechaMod(new Date());
			int updateDom = domicilioDAO.update(domicilio);
			int update = profesorDAO.update(profesore);
			if (update != 0 && updateDom != 0) {
				listaProfesores = new ArrayList<Profesore>();
				filteredProfesores = new ArrayList<Profesore>();
				listaProfesores = profesorDAO.getLista(true);
				filteredProfesores = listaProfesores;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "PROFESOR REGISTRADO!", null);
				retorno = "profesores";
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL PROFESOR, "
						+ "INTENTELO NUEVAMENTE!", null);
			}
		} else {
			Usuario user = new Usuario();
			Role rol = new Role();
			rol.setId(_ROL);
			String nombreCompleto = profesore.getApellido() + ", " + profesore.getNombre();
			user.setNombreCompleto(nombreCompleto);
			String username = Integer.toString(profesore.getDni());
			user.setUsername(username);
			user.setPassword(_PASSWORD);
			user.setUsuario1(usuario);
			user.setFechaAlta(new Date());
			user.setEnabled(true);
			user.setRole(rol);
			Localidade loc = new Localidade();
			loc.setId(idLocalidad);
			domicilio.setLocalidade(loc);
			domicilio.setUsuario1(usuario);
			domicilio.setFechaAlta(new Date());
			int idDomicilio = domicilioDAO.insertar(domicilio);			
			domicilio.setId(idDomicilio);
			profesore.setNombreCompleto(nombreCompleto);
			profesore.setDomicilio(domicilio);
			profesore.setUsuario2(usuario);
			profesore.setFechaAlta(new Date());
			profesore.setEnabled(true);
			int insert = profesorDAO.insertar(profesore);
			if (insert != 0) {
				user.setProfesore(profesore);
				int insertUser = usuarioDAO.insertar(user);
				if (insertUser != 0) {
					listaProfesores = new ArrayList<Profesore>();
					filteredProfesores = new ArrayList<Profesore>();
					listaProfesores = profesorDAO.getLista(true);
					filteredProfesores = listaProfesores;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "PROFESOR REGISTRADO!", null);
					retorno = "profesores";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "PROFESOR REGISTRADO CORRECTAMENTE, "
							+ "OCURRIO UN ERROR AL REGISTRAR EL USUARIO RELACIONADO!", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL PROFESOR, "
						+ "INTENTELO NUEVAMENTE!", null);
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
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-profesor.jpg";
         
        pdf.add(Image.getInstance(logo));
    }

}
