package promoda.clases;

import java.io.Serializable;
import java.util.Date;

import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.Matricula;

public class PagoOnline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Alumno alumno;
	private Cuota cuota;
	private Matricula matricula;
	private Date fecha;
	private String monto;	
	private String hora;
	private String detalle;
	
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Cuota getCuota() {
		return cuota;
	}
	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}
