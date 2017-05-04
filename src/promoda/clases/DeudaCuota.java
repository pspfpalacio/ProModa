package promoda.clases;

import java.io.Serializable;
import java.util.List;

public class DeudaCuota implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String alumno;
	private String curso;
	private String detalle;
	private String monto;
	private String primerVencimiento;
	private String segundoVencimiento;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getPrimerVencimiento() {
		return primerVencimiento;
	}
	public void setPrimerVencimiento(String primerVencimiento) {
		this.primerVencimiento = primerVencimiento;
	}
	public String getSegundoVencimiento() {
		return segundoVencimiento;
	}
	public void setSegundoVencimiento(String segundoVencimiento) {
		this.segundoVencimiento = segundoVencimiento;
	}
	public List<Contacto> getListaContacto() {
		return listaContacto;
	}
	public void setListaContacto(List<Contacto> listaContacto) {
		this.listaContacto = listaContacto;
	}

}
