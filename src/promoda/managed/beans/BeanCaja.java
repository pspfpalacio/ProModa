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

import promoda.clases.CajasMov;
import promoda.dao.DAOCaja;
import promoda.model.Caja;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanCaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCajaDAO}")
	private DAOCaja cajaDAO;
	
	private List<Caja> listaCajas;
	private List<Caja> filteredCajas;
	private Caja caja;
	private Usuario usuario;
	private Date fecha;
	private Date fechaDesde;
	private Date fechaHasta;	 
	private String tipo;
	private String descripcion;
	private String tipoFiltro;
	private float monto;

	public DAOCaja getCajaDAO() {
		return cajaDAO;
	}

	public void setCajaDAO(DAOCaja cajaDAO) {
		this.cajaDAO = cajaDAO;
	}

	public List<Caja> getListaCajas() {
		return listaCajas;
	}

	public void setListaCajas(List<Caja> listaCajas) {
		this.listaCajas = listaCajas;
	}

	public List<Caja> getFilteredCajas() {
		return filteredCajas;
	}

	public void setFilteredCajas(List<Caja> filteredCajas) {
		this.filteredCajas = filteredCajas;
	}
	
	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String goCaja(Usuario user) {
		usuario = new Usuario();
		listaCajas = new ArrayList<Caja>();
		filteredCajas = new ArrayList<Caja>();
		usuario = user;
		listaCajas = cajaDAO.getLista(new Date());
		filteredCajas = listaCajas;
		tipoFiltro = "";
		fechaDesde = null;
		fechaHasta = null;
		return "caja";
	}

	public void goNuevo() {
		fecha = new Date();
		tipo = "ENTRADA";
		descripcion = "";
		monto = 0;
	}
	
	public void buscar() {
		listaCajas = new ArrayList<Caja>();
		filteredCajas = new ArrayList<Caja>();
		if (fechaDesde == null && fechaHasta == null && tipoFiltro.isEmpty()) {
			listaCajas = cajaDAO.getLista();
		}
		if (fechaDesde != null && fechaHasta != null && tipoFiltro.isEmpty()) {
			listaCajas = cajaDAO.getLista(fechaDesde, fechaHasta);
		}
		if (fechaDesde == null && fechaHasta == null && !tipoFiltro.isEmpty()) {
			listaCajas = cajaDAO.getLista(tipoFiltro);
		}
		if (fechaDesde != null && fechaHasta != null && !tipoFiltro.isEmpty()) {
			listaCajas = cajaDAO.getLista(fechaDesde, fechaHasta, tipoFiltro);
		}
		filteredCajas = listaCajas;
	}
	
	public void guardar() {
		try {
			FacesMessage msg = null;
			if (fecha != null && !descripcion.isEmpty() && descripcion != null && monto != 0) {
				CajasMov cajaMov = new CajasMov();
				int entrada = 1;
				if (tipo.equals("Salida")) {
					entrada = 2;
				}
				int inserto = cajaMov.generarMovimiento(fecha, entrada, monto, 0, "Caja", tipo, descripcion, usuario);
				if (inserto != 0) {
					listaCajas = new ArrayList<Caja>();
					listaCajas = cajaDAO.getLista(new Date());
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "MOVIMIENTO DE " + tipo + " REGISTRADO!", null);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR! NO SE PUDO REGISTRAR EL MOVIMIENTO DE " + tipo 
							+ ", INTÉNTELO NUEVAMENTE!", null);
				}
			} else {
				String mensaje = "";
				if (fecha == null) {
					mensaje = "FECHA ";
				}
				if (descripcion.isEmpty() || descripcion == null) {
					mensaje = "DESCRIPCION ";
				}
				if (monto == 0) {
					mensaje = "MONTO ";
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR! LOS CAMPOS " + mensaje + " NO PUEDEN ESTAR VACÍOS!", null);				
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL REGISTRAR EL MOVIMIENTO DE CAJA! "
					+ "ERROR ORIGINAL: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void generarReporte() {
		
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-caja.jpg";
         
        pdf.add(Image.getInstance(logo));
    }

}
