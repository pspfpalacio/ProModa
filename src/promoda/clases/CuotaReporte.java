package promoda.clases;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Matricula;

public class CuotaReporte {
	
	private String detalle;
	private float monto;
	private boolean paga;
	private Date fechaPago;
	private Date fechaVencimiento;
	private Alumno alumno;
	private Curso curso;
	private Matricula matricula;
	
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public boolean isPaga() {
		return paga;
	}
	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public String getStringFechaVencimiento() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(fechaVencimiento);
	}
	
	public String getStringFechaPago() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if(fechaPago != null) {
			return formato.format(fechaPago);
		} else {
			return "";
		}
		
	}
	
	public String getStringMonto() {
		DecimalFormat formato = new DecimalFormat("$###,##0.00");
		return formato.format(monto);
	}
	
	public String getNombreCompletoAlumno() {
		return alumno.getNombreCompleto();
	}
	
	public String getStringPaga() {
		if(paga) {
			return "Si";
		}else {
			return "No";
		}
	}

}
