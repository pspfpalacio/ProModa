package promoda.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the matricula_impaga database table.
 * 
 */
@Entity
@Table(name="matricula_impaga")
@NamedQuery(name="MatriculaImpaga.findAll", query="SELECT m FROM MatriculaImpaga m")
public class MatriculaImpaga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	private float monto;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="id_alumno")
	private Alumno alumno;

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

	public MatriculaImpaga() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
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