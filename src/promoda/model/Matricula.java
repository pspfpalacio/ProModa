package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the matriculas database table.
 * 
 */
@Entity
@Table(name="matriculas")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private float costo;
	
	@Column(name="costo_curso")
	private float costoCurso;
	
	private String descripcion;

	private boolean enabled;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin_cursado")
	private Date fechaFinCursado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_finalizacion")
	private Date fechaFinalizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="matricula")
	private List<Cuota> cuotas;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="matricula")
	private List<Curso> cursos;
	
	//bi-directional many-to-one association to Inscripcione
	@OneToMany(mappedBy="matricula")
	private List<Inscripcione> inscripciones;
	
	//bi-directional many-to-one association to MateriasCalificacion
	@OneToMany(mappedBy="matricula")
	private List<MateriasCalificacion> materiasCalificacions;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_mod")
	private Usuario usuario3;

	//bi-directional many-to-one association to PagosMatricula
	@OneToMany(mappedBy="matricula")
	private List<PagosMatricula> pagosMatriculas;
	
	//bi-directional many-to-one association to PlanPago
	@OneToMany(mappedBy="matricula")
	private List<PlanPago> planPagos;

	//bi-directional many-to-one association to MatriculaAlumno
	@OneToMany(mappedBy="matricula")
	private List<MatriculaAlumno> matriculaAlumnos;
	
	//bi-directional many-to-one association to CuotaImpaga
	@OneToMany(mappedBy="matricula")
	private List<CuotaImpaga> cuotaImpagas;

	//bi-directional many-to-one association to MatriculaImpaga
	@OneToMany(mappedBy="matricula")
	private List<MatriculaImpaga> matriculaImpagas;
	
	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="matricula")
	private List<Recurso> recursos;
	
	//bi-directional many-to-one association to Mesa
	@OneToMany(mappedBy="matricula")
	private List<Mesa> mesas;

	public Matricula() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCosto() {
		return this.costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public float getCostoCurso() {
		return this.costoCurso;
	}

	public void setCostoCurso(float costoCurso) {
		this.costoCurso = costoCurso;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public Date getFechaFinCursado() {
		return this.fechaFinCursado;
	}

	public void setFechaFinCursado(Date fechaFinCursado) {
		this.fechaFinCursado = fechaFinCursado;
	}

	public Date getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public List<Cuota> getCuotas() {
		return this.cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public Cuota addCuota(Cuota cuota) {
		getCuotas().add(cuota);
		cuota.setMatricula(this);

		return cuota;
	}

	public Cuota removeCuota(Cuota cuota) {
		getCuotas().remove(cuota);
		cuota.setMatricula(null);

		return cuota;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setMatricula(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setMatricula(null);

		return curso;
	}
	
	public List<Inscripcione> getInscripciones() {
		return this.inscripciones;
	}

	public void setInscripciones(List<Inscripcione> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Inscripcione addInscripcione(Inscripcione inscripcione) {
		getInscripciones().add(inscripcione);
		inscripcione.setMatricula(this);

		return inscripcione;
	}

	public Inscripcione removeInscripcione(Inscripcione inscripcione) {
		getInscripciones().remove(inscripcione);
		inscripcione.setMatricula(null);

		return inscripcione;
	}

	public List<MateriasCalificacion> getMateriasCalificacions() {
		return this.materiasCalificacions;
	}

	public void setMateriasCalificacions(List<MateriasCalificacion> materiasCalificacions) {
		this.materiasCalificacions = materiasCalificacions;
	}

	public MateriasCalificacion addMateriasCalificacion(MateriasCalificacion materiasCalificacion) {
		getMateriasCalificacions().add(materiasCalificacion);
		materiasCalificacion.setMatricula(this);

		return materiasCalificacion;
	}

	public MateriasCalificacion removeMateriasCalificacion(MateriasCalificacion materiasCalificacion) {
		getMateriasCalificacions().remove(materiasCalificacion);
		materiasCalificacion.setMatricula(null);

		return materiasCalificacion;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}

	public List<PagosMatricula> getPagosMatriculas() {
		return this.pagosMatriculas;
	}

	public void setPagosMatriculas(List<PagosMatricula> pagosMatriculas) {
		this.pagosMatriculas = pagosMatriculas;
	}

	public PagosMatricula addPagosMatricula(PagosMatricula pagosMatricula) {
		getPagosMatriculas().add(pagosMatricula);
		pagosMatricula.setMatricula(this);

		return pagosMatricula;
	}

	public PagosMatricula removePagosMatricula(PagosMatricula pagosMatricula) {
		getPagosMatriculas().remove(pagosMatricula);
		pagosMatricula.setMatricula(null);

		return pagosMatricula;
	}
	
	public List<PlanPago> getPlanPagos() {
		return this.planPagos;
	}

	public void setPlanPagos(List<PlanPago> planPagos) {
		this.planPagos = planPagos;
	}

	public PlanPago addPlanPago(PlanPago planPago) {
		getPlanPagos().add(planPago);
		planPago.setMatricula(this);

		return planPago;
	}

	public PlanPago removePlanPago(PlanPago planPago) {
		getPlanPagos().remove(planPago);
		planPago.setMatricula(null);

		return planPago;
	}

	public List<MatriculaAlumno> getMatriculaAlumnos() {
		return this.matriculaAlumnos;
	}

	public void setMatriculaAlumnos(List<MatriculaAlumno> matriculaAlumnos) {
		this.matriculaAlumnos = matriculaAlumnos;
	}

	public MatriculaAlumno addMatriculaAlumno(MatriculaAlumno matriculaAlumno) {
		getMatriculaAlumnos().add(matriculaAlumno);
		matriculaAlumno.setMatricula(this);

		return matriculaAlumno;
	}

	public MatriculaAlumno removeMatriculaAlumno(MatriculaAlumno matriculaAlumno) {
		getMatriculaAlumnos().remove(matriculaAlumno);
		matriculaAlumno.setMatricula(null);

		return matriculaAlumno;
	}
	
	public List<CuotaImpaga> getCuotaImpagas() {
		return this.cuotaImpagas;
	}

	public void setCuotaImpagas(List<CuotaImpaga> cuotaImpagas) {
		this.cuotaImpagas = cuotaImpagas;
	}

	public CuotaImpaga addCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().add(cuotaImpaga);
		cuotaImpaga.setMatricula(this);

		return cuotaImpaga;
	}

	public CuotaImpaga removeCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().remove(cuotaImpaga);
		cuotaImpaga.setMatricula(null);

		return cuotaImpaga;
	}

	public List<MatriculaImpaga> getMatriculaImpagas() {
		return this.matriculaImpagas;
	}

	public void setMatriculaImpagas(List<MatriculaImpaga> matriculaImpagas) {
		this.matriculaImpagas = matriculaImpagas;
	}

	public MatriculaImpaga addMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		getMatriculaImpagas().add(matriculaImpaga);
		matriculaImpaga.setMatricula(this);

		return matriculaImpaga;
	}

	public MatriculaImpaga removeMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		getMatriculaImpagas().remove(matriculaImpaga);
		matriculaImpaga.setMatricula(null);

		return matriculaImpaga;
	}

	public List<Recurso> getRecursos() {
		return this.recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public Recurso addRecurso(Recurso recurso) {
		getRecursos().add(recurso);
		recurso.setMatricula(this);

		return recurso;
	}

	public Recurso removeRecurso(Recurso recurso) {
		getRecursos().remove(recurso);
		recurso.setMatricula(null);

		return recurso;
	}
	
	public List<Mesa> getMesas() {
		return this.mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public Mesa addMesa(Mesa mesa) {
		getMesas().add(mesa);
		mesa.setMatricula(this);

		return mesa;
	}

	public Mesa removeMesa(Mesa mesa) {
		getMesas().remove(mesa);
		mesa.setMatricula(null);

		return mesa;
	}

}