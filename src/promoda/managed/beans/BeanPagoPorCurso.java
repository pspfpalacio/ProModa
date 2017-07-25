package promoda.managed.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import promoda.clases.CuotaReporte;
import promoda.clases.Reporte;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOCuota;
import promoda.dao.DAOCurso;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.Curso;
import promoda.model.Inscripcione;
import promoda.model.Matricula;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanPagoPorCurso implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
    private DAOAlumno alumnoDAO;
	
    @ManagedProperty(value = "#{BeanCuotaDAO}")
    private DAOCuota cuotaDAO;
	
	private List<Curso> listaCursos;
	private List<Cuota> listaCuotas;
	private Curso curso;
	private Usuario usuario;
	private int idCurso;
	private String nombreCurso;
	private String selectedMes;
	private String mesReporte;
	private boolean existenCuotas;
		
	public DAOCurso getCursoDAO() {
		return cursoDAO;
	}
	public void setCursoDAO(DAOCurso cursoDAO) {
		this.cursoDAO = cursoDAO;
	}
	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}
	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}
	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}
	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
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
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	public String getSelectedMes() {
		return selectedMes;
	}
	public void setSelectedMes(String selectedMes) {
		this.selectedMes = selectedMes;
	}
	public String getMesReporte() {
		return mesReporte;
	}
	public void setMesReporte(String mesReporte) {
		this.mesReporte = mesReporte;
	}
	public boolean isExistenCuotas() {
		return existenCuotas;
	}
	public void setExistenCuotas(boolean existenCuotas) {
		this.existenCuotas = existenCuotas;
	}
	
	
	public String goPagosPorCurso(Usuario user) {
		curso = new Curso();
		listaCursos = new ArrayList<Curso>();
		listaCursos = cursoDAO.getLista(true);
		idCurso = 0;
		selectedMes = "";
		existenCuotas = false;
		listaCuotas = new ArrayList<Cuota>();
		mesReporte = "";
		nombreCurso = "";
		
		return "pagosporcurso";
	}
	
	public void buscarListado() {
		if(idCurso == 0) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "DEBE SELECCIONAR UN CURSO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
			SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
			String anio = formatoAnio.format(new Date());
			String fechaInicial = "01" + selectedMes + anio;
			Date dateInicial = formato.parse(fechaInicial); 
			String siguienteMes = Integer.toString((Integer.parseInt(selectedMes) + 1));
			if(siguienteMes.length() < 2) {
				siguienteMes = "0" + siguienteMes;
			}
			String fechaFinal = "01" + siguienteMes + anio;
			Date dateFinal = formato.parse(fechaFinal);
			//cuotaDAO.
			Curso curso = new Curso();
			curso = cursoDAO.get(idCurso);
			listaCuotas = cuotaDAO.getLista(curso, dateInicial, dateFinal);
			existenCuotas = true;
			mesReporte = obtenerStringMes(selectedMes);
			nombreCurso = curso.getNombre();
			
		 } catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REALIZAR LA BUSQUEDA, ERROR: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void generarCuotasPorCursoXLS() {
		Reporte reporte = new Reporte();
		Map<String, Object> parametros = new HashMap<String, Object>();		
		if (idCurso != 0) {
			Curso cur = cursoDAO.get(idCurso);
			nombreCurso = cur.getNombre();
		}
		parametros.put("curso", nombreCurso);
		parametros.put("mes", mesReporte);
		List<CuotaReporte> listaCuotasReporte = new ArrayList<CuotaReporte>();
		for (Cuota cuota : listaCuotas) {
			CuotaReporte cr = new CuotaReporte();
			cr.setAlumno(cuota.getAlumno());
			cr.setCurso(cuota.getCurso());
			cr.setMatricula(cuota.getMatricula());
			cr.setDetalle(cuota.getDetalle());
			cr.setMonto(cuota.getMonto());
			cr.setPaga(cuota.getPaga());
			cr.setFechaPago(cuota.getFechaPago());
			cr.setFechaVencimiento(cuota.getFechaVencimiento());
			listaCuotasReporte.add(cr);
			
		}
		reporte.exportXls(parametros, listaCuotasReporte, "cuotasPorCursoExcel", "attachment");
	}
	
	public void generarCuotasPorCursoPDF() {
		Reporte reporte = new Reporte();
		Map<String, Object> parametros = new HashMap<String, Object>();		
		if (idCurso != 0) {
			Curso cur = cursoDAO.get(idCurso);
			nombreCurso = cur.getNombre();
		}
		parametros.put("curso", nombreCurso);
		parametros.put("mes", mesReporte);
		List<CuotaReporte> listaCuotasReporte = new ArrayList<CuotaReporte>();
		for (Cuota cuota : listaCuotas) {
			CuotaReporte cr = new CuotaReporte();
			cr.setAlumno(cuota.getAlumno());
			cr.setCurso(cuota.getCurso());
			cr.setMatricula(cuota.getMatricula());
			cr.setDetalle(cuota.getDetalle());
			cr.setMonto(cuota.getMonto());
			cr.setPaga(cuota.getPaga());
			cr.setFechaPago(cuota.getFechaPago());
			cr.setFechaVencimiento(cuota.getFechaVencimiento());
			listaCuotasReporte.add(cr);
			
		}
		reporte.generar(parametros, listaCuotasReporte, "cuotasPorCurso", "inline");
	}
	
	private String obtenerStringMes(String selectedMes) {
		String mes;
		switch (selectedMes) {
		case "01":
			mes = "Enero";
			break;
		case "02":
			mes = "Febrero";
			break;
		case "03":
			mes = "Marzo";
			break;
		case "04":
			mes = "Abril";
			break;
		case "05":
			mes = "Mayo";
			break;
		case "06":
			mes = "Junio";
			break;
		case "07":
			mes = "Julio";
			break;
		case "08":
			mes = "Agosto";
			break;
		case "09":
			mes = "Septiembre";
			break;
		case "10":
			mes = "Octubre";
			break;
		case "11":
			mes = "Noviembre";
			break;
		case "12":
			mes = "Diciembre";
			break;
		default:
			mes = "";
			break;
		}
		return mes;
	}
		
}
