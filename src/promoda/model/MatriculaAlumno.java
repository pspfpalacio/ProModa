package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the matricula_alumnos database table.
 * 
 */
@Entity
@Table(name="matricula_alumnos")
@NamedQuery(name="MatriculaAlumno.findAll", query="SELECT m FROM MatriculaAlumno m")
public class MatriculaAlumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean eliminado;
	
	private boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_pago")
	private Date fechaPago;

	@Column(name="monto_pago")
	private float montoPago;

	private boolean pago;
	
	//bi-directional many-to-one association to CuotaImpaga
	@OneToMany(mappedBy="matriculaAlumno")
	private List<CuotaImpaga> cuotaImpagas;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="id_alumno")
	private Alumno alumno;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	//bi-directional many-to-one association to Matricula
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario2;
	
	//bi-directional many-to-one association to MatriculaImpaga
	@OneToMany(mappedBy="matriculaAlumno")
	private List<MatriculaImpaga> matriculaImpagas;

	public MatriculaAlumno() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEliminado() {
		return this.eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public float getMontoPago() {
		return this.montoPago;
	}

	public void setMontoPago(float montoPago) {
		this.montoPago = montoPago;
	}

	public boolean getPago() {
		return this.pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public List<CuotaImpaga> getCuotaImpagas() {
		return this.cuotaImpagas;
	}

	public void setCuotaImpagas(List<CuotaImpaga> cuotaImpagas) {
		this.cuotaImpagas = cuotaImpagas;
	}

	public CuotaImpaga addCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().add(cuotaImpaga);
		cuotaImpaga.setMatriculaAlumno(this);

		return cuotaImpaga;
	}

	public CuotaImpaga removeCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().remove(cuotaImpaga);
		cuotaImpaga.setMatriculaAlumno(null);

		return cuotaImpaga;
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

	public Matricula getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}
	
	public List<MatriculaImpaga> getMatriculaImpagas() {
		return this.matriculaImpagas;
	}

	public void setMatriculaImpagas(List<MatriculaImpaga> matriculaImpagas) {
		this.matriculaImpagas = matriculaImpagas;
	}

	public MatriculaImpaga addMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		getMatriculaImpagas().add(matriculaImpaga);
		matriculaImpaga.setMatriculaAlumno(this);

		return matriculaImpaga;
	}

	public MatriculaImpaga removeMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		getMatriculaImpagas().remove(matriculaImpaga);
		matriculaImpaga.setMatriculaAlumno(null);

		return matriculaImpaga;
	}

}