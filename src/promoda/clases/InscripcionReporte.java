package promoda.clases;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import promoda.model.InscripcionDia;

public class InscripcionReporte {
	
	private Date fecha;
	private String curso;
	private String matriculaDesc;
	private String dni;
	private Date fecha_nacimiento;
	private String nombre;
	private String apellido;
	private String provincia;
	private String localidad;
	private String cod_postal;
	private String calle;
	private String numero;
	private String piso;
	private String departamento;
	private String email;
	private String telcel;
	private String telfijo;
	private float matricula;
	private String cant_cuotas;
	private float costo_curso;
	private float costo_cuota;
	private Date horario1;
	private Date horario2;
	private Date primerVencimiento;
	private List<String> listaDias;
	private List<InscripcionDia> listaInscripcionDias;

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getMatriculaDesc() {
		return matriculaDesc;
	}
	public void setMatriculaDesc(String matriculaDesc) {
		this.matriculaDesc = matriculaDesc;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getCod_postal() {
		return cod_postal;
	}
	public void setCod_postal(String cod_postal) {
		this.cod_postal = cod_postal;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelcel() {
		return telcel;
	}
	public void setTelcel(String telcel) {
		this.telcel = telcel;
	}
	public String getTelfijo() {
		return telfijo;
	}
	public void setTelfijo(String telfijo) {
		this.telfijo = telfijo;
	}
	public float getMatricula() {
		return matricula;
	}
	public void setMatricula(float matricula) {
		this.matricula = matricula;
	}
	public String getCant_cuotas() {
		return cant_cuotas;
	}
	public void setCant_cuotas(String cant_cuotas) {
		this.cant_cuotas = cant_cuotas;
	}
	public float getCosto_curso() {
		return costo_curso;
	}
	public void setCosto_curso(float costo_curso) {
		this.costo_curso = costo_curso;
	}
	public float getCosto_cuota() {
		return costo_cuota;
	}
	public void setCosto_cuota(float costo_cuota) {
		this.costo_cuota = costo_cuota;
	}	
	public Date getHorario1() {
		return horario1;
	}
	public void setHorario1(Date horario1) {
		this.horario1 = horario1;
	}
	public Date getHorario2() {
		return horario2;
	}
	public void setHorario2(Date horario2) {
		this.horario2 = horario2;
	}	
	public Date getPrimerVencimiento() {
		return primerVencimiento;
	}
	public void setPrimerVencimiento(Date primerVencimiento) {
		this.primerVencimiento = primerVencimiento;
	}	
	public List<String> getListaDias() {
		return listaDias;
	}
	public void setListaDias(List<String> listaDias) {
		this.listaDias = listaDias;
	}
	public List<InscripcionDia> getListaInscripcionDias() {
		return listaInscripcionDias;
	}
	public void setListaInscripcionDias(
			List<InscripcionDia> listaInscripcionDias) {
		this.listaInscripcionDias = listaInscripcionDias;
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
	
	public String getFechaNacimientoString() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String retorno = dateFormat.format(fecha_nacimiento);
			return retorno;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getMatriculaString() {
		try {
			DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
			String monto = formatoMonto.format(matricula);
			return monto;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getCostoCursoString() {
		try {
			DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
			String monto = formatoMonto.format(costo_curso);
			return monto;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getCostoCuotaString() {
		try {
			DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
			String monto = formatoMonto.format(costo_cuota);
			return monto;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getHorarioString() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			String horario = "Entre " + dateFormat.format(horario1) + " y " + dateFormat.format(horario2);
			return horario;
		} catch(Exception e) {
			return "";
		}
	}
	
	public String getPrimerVencimientoString() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String date = formato.format(primerVencimiento);
			return date;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getDias() {
		String dias = "";
		if (listaDias == null) {
			for (InscripcionDia inscripcionDia : listaInscripcionDias) {
				dias = dias + inscripcionDia.getDia() + "; ";
			}
		} else {
			for (String dia : listaDias) {
				dias = dias + dia + "; ";
			}
		}	
		return dias;
	}

}
