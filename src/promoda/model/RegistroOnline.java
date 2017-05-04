package promoda.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the registro_online database table.
 * 
 */
@Entity
@Table(name="registro_online")
@NamedQuery(name="RegistroOnline.findAll", query="SELECT r FROM RegistroOnline r")
public class RegistroOnline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String accesToken;

	@Column(name="check_out")
	private String checkOut;

	private String detalle;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name="id_alumno")
	private int idAlumno;

	@Column(name="id_cuota")
	private int idCuota;

	@Column(name="id_matricula")
	private int idMatricula;

	@Column(name="json_id")
	private String jsonId;

	private float monto;

	private String preference;

	public RegistroOnline() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccesToken() {
		return this.accesToken;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public String getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdAlumno() {
		return this.idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public int getIdCuota() {
		return this.idCuota;
	}

	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}

	public int getIdMatricula() {
		return this.idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public String getJsonId() {
		return this.jsonId;
	}

	public void setJsonId(String jsonId) {
		this.jsonId = jsonId;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getPreference() {
		return this.preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

}