package promoda.clases;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PagoReporte {
	
	private String encabezado;
	private Date fecha;
	private String alumno;
	private String curso;
	private String concepto;
	private String matricula;
	private float monto;

	public String getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
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
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public String getFechaString() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String retorno = dateFormat.format(fecha);
			return retorno;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getMontoString() {
		try {
			DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
			String valor = formatoMonto.format(monto);
			return valor;
		} catch (Exception e) {
			return "";
		}
	}

}
