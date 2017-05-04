package promoda.clases;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class AsistenciaReporte {
	
	private String nombreCompleto;	
	private String clase;
	private int nroClase;
	private HashMap<Integer, String> asistencias;
	private int idAlumno;
	
	public AsistenciaReporte() {
		asistencias = new LinkedHashMap<Integer, String>();
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public int getNroClase() {
		return nroClase;
	}

	public void setNroClase(int nroClase) {
		this.nroClase = nroClase;
	}

	public HashMap<Integer, String> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(HashMap<Integer, String> asistencias) {
		this.asistencias = asistencias;
	}
	
	public String getAsistencia(int nroClase) {		
		return asistencias.get(nroClase);
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

}
