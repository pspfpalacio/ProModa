package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cursos database table.
 * 
 */
@Entity
@Table(name="cursos")
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cant_horas")
	private int cantHoras;
	
	@Column(name="costo_curso")
	private float costoCurso;

	@Column(name="costo_matricula")
	private float costoMatricula;

	@Column(name="costo_total")
	private float costoTotal;

	@Column(name="dias_cursado")
	private String diasCursado;

	@Column(name="duracion_meses")
	private int duracionMeses;

	private boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	private String nombre;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="curso")
	private List<Cuota> cuotas;

	//bi-directional many-to-one association to Matricula
	@ManyToOne
	@JoinColumn(name="matricula_vigente")
	private Matricula matricula;

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

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="curso")
	private List<Materia> materias;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="curso")
	private List<Matricula> matriculas;
	
	//bi-directional many-to-one association to CuotaImpaga
	@OneToMany(mappedBy="curso")
	private List<CuotaImpaga> cuotaImpagas;

	//bi-directional many-to-one association to MatriculaImpaga
	@OneToMany(mappedBy="curso")
	private List<MatriculaImpaga> matriculaImpagas;
	
	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="curso")
	private List<Recurso> recursos;

	public Curso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantHoras() {
		return this.cantHoras;
	}

	public void setCantHoras(int cantHoras) {
		this.cantHoras = cantHoras;
	}
	
	public float getCostoCurso() {
		return this.costoCurso;
	}

	public void setCostoCurso(float costoCurso) {
		this.costoCurso = costoCurso;
	}

	public float getCostoMatricula() {
		return this.costoMatricula;
	}

	public void setCostoMatricula(float costoMatricula) {
		this.costoMatricula = costoMatricula;
	}

	public float getCostoTotal() {
		return this.costoTotal;
	}

	public void setCostoTotal(float costoTotal) {
		this.costoTotal = costoTotal;
	}

	public String getDiasCursado() {
		return this.diasCursado;
	}

	public void setDiasCursado(String diasCursado) {
		this.diasCursado = diasCursado;
	}

	public int getDuracionMeses() {
		return this.duracionMeses;
	}

	public void setDuracionMeses(int duracionMeses) {
		this.duracionMeses = duracionMeses;
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

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuota> getCuotas() {
		return this.cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public Cuota addCuota(Cuota cuota) {
		getCuotas().add(cuota);
		cuota.setCurso(this);

		return cuota;
	}

	public Cuota removeCuota(Cuota cuota) {
		getCuotas().remove(cuota);
		cuota.setCurso(null);

		return cuota;
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

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}

	public List<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Materia addMateria(Materia materia) {
		getMaterias().add(materia);
		materia.setCurso(this);

		return materia;
	}

	public Materia removeMateria(Materia materia) {
		getMaterias().remove(materia);
		materia.setCurso(null);

		return materia;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setCurso(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setCurso(null);

		return matricula;
	}
	
	public List<CuotaImpaga> getCuotaImpagas() {
		return this.cuotaImpagas;
	}

	public void setCuotaImpagas(List<CuotaImpaga> cuotaImpagas) {
		this.cuotaImpagas = cuotaImpagas;
	}

	public CuotaImpaga addCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().add(cuotaImpaga);
		cuotaImpaga.setCurso(this);

		return cuotaImpaga;
	}

	public CuotaImpaga removeCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().remove(cuotaImpaga);
		cuotaImpaga.setCurso(null);

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
		matriculaImpaga.setCurso(this);

		return matriculaImpaga;
	}

	public MatriculaImpaga removeMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		getMatriculaImpagas().remove(matriculaImpaga);
		matriculaImpaga.setCurso(null);

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
		recurso.setCurso(this);

		return recurso;
	}

	public Recurso removeRecurso(Recurso recurso) {
		getRecursos().remove(recurso);
		recurso.setCurso(null);

		return recurso;
	}

}