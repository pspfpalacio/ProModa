package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the materias database table.
 * 
 */
@Entity
@Table(name="materias")
@NamedQuery(name="Materia.findAll", query="SELECT m FROM Materia m")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cant_clases")
	private int cantClases;

	@Column(name="carga_horario_semanal")
	private String cargaHorarioSemanal;

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

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;
	
	//bi-directional many-to-one association to Profesore
	@ManyToOne
	@JoinColumn(name="id_profesor_suplente")
	private Profesore profesore1;

	//bi-directional many-to-one association to Profesore
	@ManyToOne
	@JoinColumn(name="id_profesor_titular")
	private Profesore profesore2;

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
	
	//bi-directional many-to-one association to MateriasCalificacion
	@OneToMany(mappedBy="materia")
	private List<MateriasCalificacion> materiasCalificacions;

	//bi-directional many-to-one association to MateriasProfesor
	@OneToMany(mappedBy="materia")
	private List<MateriasProfesor> materiasProfesors;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="materia")
	private List<Asistencia> asistencias;
	
	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="materia")
	private List<Recurso> recursos;
	
	//bi-directional many-to-one association to Mesa
	@OneToMany(mappedBy="materia")
	private List<Mesa> mesas;
	
	//bi-directional many-to-one association to MesasAlumno
	@OneToMany(mappedBy="materia")
	private List<MesasAlumno> mesasAlumnos;

	public Materia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCantClases() {
		return this.cantClases;
	}

	public void setCantClases(int cantClases) {
		this.cantClases = cantClases;
	}

	public String getCargaHorarioSemanal() {
		return this.cargaHorarioSemanal;
	}

	public void setCargaHorarioSemanal(String cargaHorarioSemanal) {
		this.cargaHorarioSemanal = cargaHorarioSemanal;
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

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Profesore getProfesore1() {
		return this.profesore1;
	}

	public void setProfesore1(Profesore profesore1) {
		this.profesore1 = profesore1;
	}

	public Profesore getProfesore2() {
		return this.profesore2;
	}

	public void setProfesore2(Profesore profesore2) {
		this.profesore2 = profesore2;
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
	
	public List<MateriasCalificacion> getMateriasCalificacions() {
		return this.materiasCalificacions;
	}

	public void setMateriasCalificacions(List<MateriasCalificacion> materiasCalificacions) {
		this.materiasCalificacions = materiasCalificacions;
	}

	public MateriasCalificacion addMateriasCalificacion(MateriasCalificacion materiasCalificacion) {
		getMateriasCalificacions().add(materiasCalificacion);
		materiasCalificacion.setMateria(this);

		return materiasCalificacion;
	}

	public MateriasCalificacion removeMateriasCalificacion(MateriasCalificacion materiasCalificacion) {
		getMateriasCalificacions().remove(materiasCalificacion);
		materiasCalificacion.setMateria(null);

		return materiasCalificacion;
	}

	public List<MateriasProfesor> getMateriasProfesors() {
		return this.materiasProfesors;
	}

	public void setMateriasProfesors(List<MateriasProfesor> materiasProfesors) {
		this.materiasProfesors = materiasProfesors;
	}

	public MateriasProfesor addMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().add(materiasProfesor);
		materiasProfesor.setMateria(this);

		return materiasProfesor;
	}

	public MateriasProfesor removeMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().remove(materiasProfesor);
		materiasProfesor.setMateria(null);

		return materiasProfesor;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setMateria(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setMateria(null);

		return asistencia;
	}

	public List<Recurso> getRecursos() {
		return this.recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public Recurso addRecurso(Recurso recurso) {
		getRecursos().add(recurso);
		recurso.setMateria(this);

		return recurso;
	}

	public Recurso removeRecurso(Recurso recurso) {
		getRecursos().remove(recurso);
		recurso.setMateria(null);

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
		mesa.setMateria(this);

		return mesa;
	}

	public Mesa removeMesa(Mesa mesa) {
		getMesas().remove(mesa);
		mesa.setMateria(null);

		return mesa;
	}
	
	public List<MesasAlumno> getMesasAlumnos() {
		return this.mesasAlumnos;
	}

	public void setMesasAlumnos(List<MesasAlumno> mesasAlumnos) {
		this.mesasAlumnos = mesasAlumnos;
	}

	public MesasAlumno addMesasAlumno(MesasAlumno mesasAlumno) {
		getMesasAlumnos().add(mesasAlumno);
		mesasAlumno.setMateria(this);

		return mesasAlumno;
	}

	public MesasAlumno removeMesasAlumno(MesasAlumno mesasAlumno) {
		getMesasAlumnos().remove(mesasAlumno);
		mesasAlumno.setMateria(null);

		return mesasAlumno;
	}

}