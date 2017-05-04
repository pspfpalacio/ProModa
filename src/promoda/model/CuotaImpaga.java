package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cuota_impaga database table.
 * 
 */
@Entity
@Table(name="cuota_impaga")
@NamedQuery(name="CuotaImpaga.findAll", query="SELECT c FROM CuotaImpaga c")
public class CuotaImpaga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String detalle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private float monto;

	@Temporal(TemporalType.DATE)
	@Column(name="segundo_vencimiento")
	private Date segundoVencimiento;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="id_alumno")
	private Alumno alumno;

	//bi-directional many-to-one association to Cuota
	@ManyToOne
	@JoinColumn(name="id_cuota")
	private Cuota cuota;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;
	
	//bi-directional many-to-one association to MatriculaAlumno
	@ManyToOne
	@JoinColumn(name="id_matricula_alumno")
	private MatriculaAlumno matriculaAlumno;

	//bi-directional many-to-one association to Matricula
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario;

	public CuotaImpaga() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Date getSegundoVencimiento() {
		return this.segundoVencimiento;
	}

	public void setSegundoVencimiento(Date segundoVencimiento) {
		this.segundoVencimiento = segundoVencimiento;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Cuota getCuota() {
		return this.cuota;
	}

	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public MatriculaAlumno getMatriculaAlumno() {
		return this.matriculaAlumno;
	}

	public void setMatriculaAlumno(MatriculaAlumno matriculaAlumno) {
		this.matriculaAlumno = matriculaAlumno;
	}

	public Matricula getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}