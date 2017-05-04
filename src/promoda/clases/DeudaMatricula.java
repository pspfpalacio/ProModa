package promoda.clases;

import java.io.Serializable;
import java.util.List;

public class DeudaMatricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String alumno;
	private String curso;
	private String fechaInscripcion;
	private String montoDeuda;
	private List<Contacto> listaContacto;
	
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getMontoDeuda() {
		return montoDeuda;
	}
	public void setMontoDeuda(String montoDeuda) {
		this.montoDeuda = montoDeuda;
	}
	public List<Contacto> getListaContacto() {
		return listaContacto;
	}
	public void setListaContacto(List<Contacto> listaContacto) {
		this.listaContacto = listaContacto;
	}

}
