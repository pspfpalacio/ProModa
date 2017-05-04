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

import promoda.dao.DAOAlumno;
import promoda.dao.DAODomicilio;
import promoda.dao.DAOLocalidad;
import promoda.dao.DAOProvincia;
import promoda.dao.DAOUsuario;
import promoda.model.Alumno;
import promoda.model.Domicilio;
import promoda.model.Localidade;
import promoda.model.Provincia;
import promoda.model.Role;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanAlumno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
	private DAOAlumno alumnoDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanProvinciaDAO}")
	private DAOProvincia provinciaDAO;
	
	@ManagedProperty(value = "#{BeanLocalidadDAO}")
	private DAOLocalidad localidadDAO;
	
	@ManagedProperty(value = "#{BeanDomicilioDAO}")
	private DAODomicilio domicilioDAO;
	
	private List<Alumno> listaAlumnos;
	private List<Alumno> filteredAlumnos;
	private List<Provincia> listaProvincias;
	private List<Localidade> listaLocalidades;
	private Alumno alumno;
	private Domicilio domicilio;
	private Usuario usuario;
	private String headerText;
	private int idProvincia;
	private int idLocalidad;
	private boolean editar;
	private boolean existe;
	
	private static String _PASSWORD = "WdUjxKFLegTkGNtfAJLVzAEZvIr4i5xSpct/V6SF9lkTA4gxrhsmONnCTOw3ic+i3LGYL58gWxOJnzZtmg1Ypw==";
	private static int _ROL = 2;

	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}

	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
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

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	public List<Alumno> getFilteredAlumnos() {
		return filteredAlumnos;
	}

	public void setFilteredAlumnos(List<Alumno> filteredAlumnos) {
		this.filteredAlumnos = filteredAlumnos;
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

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
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

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public String goAlumnos(Usuario user) {
		usuario = new Usuario();
		usuario = user;
		listaAlumnos = new ArrayList<Alumno>();
		filteredAlumnos = new ArrayList<Alumno>();
		listaAlumnos = alumnoDAO.getLista(true);
		filteredAlumnos = listaAlumnos;
		listaProvincias = new ArrayList<Provincia>();
		listaProvincias = provinciaDAO.getLista();
		listaLocalidades = new ArrayList<Localidade>();
		return "alumnos";
	}
	
	public String goAlumnoNuevo() {
		headerText = "Alumno Nuevo";
		alumno = new Alumno();
		domicilio = new Domicilio();
		idProvincia = 0;
		idLocalidad = 0;
		editar = false;
		existe = false;
		listaLocalidades = new ArrayList<Localidade>();
		return "alumno";
	}
	
	public String goAlumnoEditar(Alumno alum) {
		headerText = "Editar Alumno";
		alumno = new Alumno();
		domicilio = new Domicilio();
		alumno = alum;
		idProvincia = 0;
		idLocalidad = 0;
		editar = true;
		existe = false;
		domicilio = alum.getDomicilio();
		idLocalidad = domicilio.getLocalidade().getId();
		Localidade loc = localidadDAO.get(idLocalidad);
		idProvincia = loc.getProvincia().getId();
		listaLocalidades = new ArrayList<Localidade>();
		listaLocalidades = localidadDAO.getLista(loc.getProvincia());
		return "alumno";
	}
	
	public void baja(Alumno alum) {
		FacesMessage msg = null;
		alum.setEnabled(false);
		alum.setFechaBaja(new Date());
		alum.setUsuario3(usuario);
		Usuario user = usuarioDAO.get(alum);
		user.setEnabled(false);
		user.setFechaBaja(new Date());
		user.setUsuario2(usuario);
		int bajaUser = usuarioDAO.update(user);
		int bajaAlum = alumnoDAO.update(alum);
		if (bajaAlum != 0 && bajaUser != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE ALUMNO EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN LA BAJA DEL ALUMNO, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void alta(Alumno alum) {
		FacesMessage msg = null;
		alum.setEnabled(true);
		alum.setFechaAlta(new Date());
		alum.setUsuario2(usuario);
		Usuario user = usuarioDAO.get(alum);
		user.setEnabled(true);
		user.setFechaAlta(new Date());
		user.setUsuario1(usuario);
		int altaUser = usuarioDAO.update(user);
		int altaAlum = alumnoDAO.update(alum);
		if (altaAlum != 0 && altaUser != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALTA DE ALUMNO EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN EL ALTA DEL ALUMNO, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void getPorDni(int dni) {
		alumno = new Alumno();
		alumno = alumnoDAO.getPorDni(dni);
		if (alumno.getId() == 0) {
			alumno.setDni(dni);
			domicilio = new Domicilio();
			idLocalidad = 0;
			idProvincia = 0;
		} else {
			existe = true;
			domicilio = new Domicilio();
			domicilio = alumno.getDomicilio();
			idLocalidad = domicilio.getLocalidade().getId();
			Localidade loc = localidadDAO.get(idLocalidad);
			idProvincia = loc.getProvincia().getId();
		}
	}
	
	public void generarReporte() {
		
	}
	
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
			factor = -1; //Aun no celebra su cumpleaÃ±os
			}
			} else {
			factor = -1; //Aun no celebra su cumpleaÃ±os
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
		if (alumno.getDni() != 0 && (!alumno.getApellido().isEmpty() || alumno.getApellido() != "") 
				&& (!alumno.getNombres().isEmpty() || alumno.getNombres() != "") && alumno.getFechaNacimiento() != null 
				&& idProvincia != 0 && idLocalidad != 0 && (!domicilio.getCodigoPostal().isEmpty() || domicilio.getCodigoPostal() != "") 
				&& (!domicilio.getCalle().isEmpty() || domicilio.getCalle() != "") && (!domicilio.getNumero().isEmpty() || domicilio.getNumero() != "") 
				&& (!alumno.getTelefonoCel().isEmpty() || !alumno.getTelefonoFijo().isEmpty())) {
			if (alumno.getId() != 0) {				
				String nombreCompleto = alumno.getApellido() + ", " + alumno.getNombres();				
				alumno.setNombreCompleto(nombreCompleto);
				alumno.setUsuario4(usuario);
				alumno.setFechaMod(new Date());
				alumno.setEnabled(true);
				Localidade loc = new Localidade();
				loc.setId(idLocalidad);
				domicilio.setLocalidade(loc);
				domicilio.setUsuario3(usuario);
				domicilio.setFechaMod(new Date());
				if (!alumno.getEmail().isEmpty() || alumno.getEmail() != ""){
					Usuario user = usuarioDAO.get(alumno);
					if (user.getId() != 0) {
						user.setNombreCompleto(nombreCompleto);
						user.setNombre(alumno.getNombres());
						user.setApellido(alumno.getApellido());
						user.setUsername(alumno.getEmail());
						user.setEmail(alumno.getEmail());
						usuarioDAO.update(user);
					} else {
						Role rol = new Role();
						rol.setId(_ROL);
						user.setNombreCompleto(nombreCompleto);
//						String username = alumno.getNombres().substring(0,1) + alumno.getApellido().substring(0,1);
//						username = username.toLowerCase() + Integer.toString(alumno.getDni());
//						System.out.println(username);
						user.setUsername(alumno.getEmail());
						user.setPassword(_PASSWORD);
						user.setNombre(alumno.getNombres());
						user.setApellido(alumno.getApellido());
						user.setEmail(alumno.getEmail());
						user.setUsuario1(usuario);
						user.setFechaAlta(new Date());
						user.setEnabled(false);
						user.setRole(rol);
						user.setAlumno(alumno);
						int idUser = usuarioDAO.insertar(user);
						user.setId(idUser);
					}
				}				
				int updateDom = domicilioDAO.update(domicilio);
				int update = alumnoDAO.update(alumno);
				if (update != 0 && updateDom != 0) {
					listaAlumnos = new ArrayList<Alumno>();
					filteredAlumnos = new ArrayList<Alumno>();
					listaAlumnos = alumnoDAO.getLista(true);
					filteredAlumnos = listaAlumnos;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALUMNO REGISTRADO!", null);
					retorno = "alumnos";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL ALUMNO, "
							+ "INTENTELO NUEVAMENTE!", null);
				}
			} else {				
				Role rol = new Role();
				rol.setId(_ROL);
				String nombreCompleto = alumno.getApellido() + ", " + alumno.getNombres();
				Localidade loc = new Localidade();
				loc.setId(idLocalidad);
				domicilio.setLocalidade(loc);
				domicilio.setUsuario1(usuario);
				domicilio.setFechaAlta(new Date());
				int idDomicilio = domicilioDAO.insertar(domicilio);
				domicilio.setId(idDomicilio);
				alumno.setNombreCompleto(nombreCompleto);
				alumno.setDomicilio(domicilio);
				alumno.setUsuario2(usuario);
				alumno.setFechaAlta(new Date());
				alumno.setEnabled(true);
				int insert = alumnoDAO.insertar(alumno);
				if (insert != 0) {
					if (!alumno.getEmail().isEmpty() || alumno.getEmail() != ""){
						Usuario user = new Usuario();
						user.setNombreCompleto(nombreCompleto);
//						String username = alumno.getNombres().substring(0,1) + alumno.getApellido().substring(0,1);
//						username = username.toLowerCase() + Integer.toString(alumno.getDni());
//						System.out.println(username);
						user.setUsername(alumno.getEmail());
						user.setPassword(_PASSWORD);
						user.setNombre(alumno.getNombres());
						user.setApellido(alumno.getApellido());
						user.setEmail(alumno.getEmail());
						user.setUsuario1(usuario);
						user.setFechaAlta(new Date());
						user.setEnabled(false);
						user.setRole(rol);
						user.setAlumno(alumno);
						int idUser = usuarioDAO.insertar(user);
						user.setId(idUser);
					}
					listaAlumnos = new ArrayList<Alumno>();
					filteredAlumnos = new ArrayList<Alumno>();
					listaAlumnos = alumnoDAO.getLista(true);
					filteredAlumnos = listaAlumnos;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALUMNO REGISTRADO!", null);
					retorno = "alumnos";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL RESGITRAR EL ALUMNO, "
							+ "INTENTELO NUEVAMENTE!", null);
				}
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			String mensaje = "";
			if (alumno.getDni() == 0) {
				mensaje = mensaje + " DNI!";
			}
			if (alumno.getFechaNacimiento() == null) {
				mensaje = mensaje + " FECHA DE NACIMIENTO!";
			}
			if (alumno.getApellido().isEmpty() || alumno.getApellido() == "") {
				mensaje = mensaje + " APELLIDO!";
			}
			if (alumno.getNombres().isEmpty() || alumno.getNombres() == "") {
				mensaje = mensaje + " NOMBRES!";
			}
			if (idProvincia == 0) {
				mensaje = mensaje + " PROVINCIA!";
			}
			if (idLocalidad == 0) {
				mensaje = mensaje + " LOCALIDAD!";
			}
			if (domicilio.getCodigoPostal().isEmpty() || domicilio.getCodigoPostal() == "") {
				mensaje = mensaje + " CÓDIGO POSTAL!";
			}
			if (domicilio.getCalle().isEmpty() || domicilio.getCalle() == "") {
				mensaje = mensaje + " CALLE!";
			}
			if (domicilio.getNumero().isEmpty() || domicilio.getNumero() == "") {
				mensaje = mensaje + " NÚMERO DE CALLE!";
			}
			if ((alumno.getTelefonoCel().isEmpty() || alumno.getTelefonoCel() == "") 
					&& (alumno.getTelefonoFijo().isEmpty() || alumno.getTelefonoFijo() == "")) {
				mensaje = mensaje + " TELEFONO CEL O TELEFONO FIJO!";
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "LOS PARAMETROS" + mensaje + " SON OBLIGATORIOS PARA REGISTRAR UN ALUMNO!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
		return retorno;
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-alumno.jpg";
         
        pdf.add(Image.getInstance(logo));
    }

}
