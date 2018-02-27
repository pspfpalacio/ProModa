package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellido;
	
	private String email;

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

	@Column(name="nombre_completo")
	private String nombreCompleto;

	private String password;

	private String username;

	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="usuario1")
	private List<Alumno> alumnos1;

	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="usuario2")
	private List<Alumno> alumnos2;

	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="usuario3")
	private List<Alumno> alumnos3;

	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="usuario4")
	private List<Alumno> alumnos4;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="usuario")
	private List<Asistencia> asistencias;
	
	//bi-directional many-to-one association to Caja
	@OneToMany(mappedBy="usuario1")
	private List<Caja> cajas1;

	//bi-directional many-to-one association to Caja
	@OneToMany(mappedBy="usuario2")
	private List<Caja> cajas2;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="usuario1")
	private List<Cuota> cuotas1;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="usuario2")
	private List<Cuota> cuotas2;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="usuario3")
	private List<Cuota> cuotas3;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="usuario1")
	private List<Curso> cursos1;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="usuario2")
	private List<Curso> cursos2;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="usuario3")
	private List<Curso> cursos3;

	//bi-directional many-to-one association to Domicilio
	@OneToMany(mappedBy="usuario1")
	private List<Domicilio> domicilios1;

	//bi-directional many-to-one association to Domicilio
	@OneToMany(mappedBy="usuario2")
	private List<Domicilio> domicilios2;

	//bi-directional many-to-one association to Domicilio
	@OneToMany(mappedBy="usuario3")
	private List<Domicilio> domicilios3;

	//bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy="usuario1")
	private List<Gasto> gastos1;

	//bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy="usuario2")
	private List<Gasto> gastos2;

	//bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy="usuario3")
	private List<Gasto> gastos3;
	
	//bi-directional many-to-one association to Inscripcione
	@OneToMany(mappedBy="usuario1")
	private List<Inscripcione> inscripciones1;

	//bi-directional many-to-one association to Inscripcione
	@OneToMany(mappedBy="usuario2")
	private List<Inscripcione> inscripciones2;

	//bi-directional many-to-one association to Inscripcione
	@OneToMany(mappedBy="usuario3")
	private List<Inscripcione> inscripciones3;

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="usuario1")
	private List<Materia> materias1;

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="usuario2")
	private List<Materia> materias2;

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="usuario3")
	private List<Materia> materias3;
	
	//bi-directional many-to-one association to MatriculaAlumno
	@OneToMany(mappedBy="usuario1")
	private List<MatriculaAlumno> matriculaAlumnos1;

	//bi-directional many-to-one association to MatriculaAlumno
	@OneToMany(mappedBy="usuario2")
	private List<MatriculaAlumno> matriculaAlumnos2;

	//bi-directional many-to-one association to PagosCuota
	@OneToMany(mappedBy="usuario1")
	private List<PagosCuota> pagosCuotas1;

	//bi-directional many-to-one association to PagosCuota
	@OneToMany(mappedBy="usuario2")
	private List<PagosCuota> pagosCuotas2;

	//bi-directional many-to-one association to PagosCuota
	@OneToMany(mappedBy="usuario3")
	private List<PagosCuota> pagosCuotas3;

	//bi-directional many-to-one association to PagosMatricula
	@OneToMany(mappedBy="usuario1")
	private List<PagosMatricula> pagosMatriculas1;

	//bi-directional many-to-one association to PagosMatricula
	@OneToMany(mappedBy="usuario2")
	private List<PagosMatricula> pagosMatriculas2;

	//bi-directional many-to-one association to PagosMatricula
	@OneToMany(mappedBy="usuario3")
	private List<PagosMatricula> pagosMatriculas3;

	//bi-directional many-to-one association to Profesore
	@OneToMany(mappedBy="usuario1")
	private List<Profesore> profesores1;

	//bi-directional many-to-one association to Profesore
	@OneToMany(mappedBy="usuario2")
	private List<Profesore> profesores2;

	//bi-directional many-to-one association to Profesore
	@OneToMany(mappedBy="usuario3")
	private List<Profesore> profesores3;

	//bi-directional many-to-one association to Profesore
	@OneToMany(mappedBy="usuario4")
	private List<Profesore> profesores4;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="id_alumno")
	private Alumno alumno;

	//bi-directional many-to-one association to Profesore
	@ManyToOne
	@JoinColumn(name="id_profesor")
	private Profesore profesore;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Role role;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario1")
	private List<Usuario> usuarios1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario2")
	private List<Usuario> usuarios2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_mod")
	private Usuario usuario3;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario3")
	private List<Usuario> usuarios3;
	
	//bi-directional many-to-one association to CuotaImpaga
	@OneToMany(mappedBy="usuario")
	private List<CuotaImpaga> cuotaImpagas;

	//bi-directional many-to-one association to MatriculaImpaga
	@OneToMany(mappedBy="usuario")
	private List<MatriculaImpaga> matriculaImpagas;
	
	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="usuario1")
	private List<Recurso> recursos1;

	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="usuario2")
	private List<Recurso> recursos2;

	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="usuario3")
	private List<Recurso> recursos3;
	
	//bi-directional many-to-one association to RecursoAlumno
	@OneToMany(mappedBy="usuario")
	private List<RecursoAlumno> recursoAlumnos;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Alumno> getAlumnos1() {
		return this.alumnos1;
	}

	public void setAlumnos1(List<Alumno> alumnos1) {
		this.alumnos1 = alumnos1;
	}

	public Alumno addAlumnos1(Alumno alumnos1) {
		getAlumnos1().add(alumnos1);
		alumnos1.setUsuario1(this);

		return alumnos1;
	}

	public Alumno removeAlumnos1(Alumno alumnos1) {
		getAlumnos1().remove(alumnos1);
		alumnos1.setUsuario1(null);

		return alumnos1;
	}

	public List<Alumno> getAlumnos2() {
		return this.alumnos2;
	}

	public void setAlumnos2(List<Alumno> alumnos2) {
		this.alumnos2 = alumnos2;
	}

	public Alumno addAlumnos2(Alumno alumnos2) {
		getAlumnos2().add(alumnos2);
		alumnos2.setUsuario2(this);

		return alumnos2;
	}

	public Alumno removeAlumnos2(Alumno alumnos2) {
		getAlumnos2().remove(alumnos2);
		alumnos2.setUsuario2(null);

		return alumnos2;
	}

	public List<Alumno> getAlumnos3() {
		return this.alumnos3;
	}

	public void setAlumnos3(List<Alumno> alumnos3) {
		this.alumnos3 = alumnos3;
	}

	public Alumno addAlumnos3(Alumno alumnos3) {
		getAlumnos3().add(alumnos3);
		alumnos3.setUsuario3(this);

		return alumnos3;
	}

	public Alumno removeAlumnos3(Alumno alumnos3) {
		getAlumnos3().remove(alumnos3);
		alumnos3.setUsuario3(null);

		return alumnos3;
	}

	public List<Alumno> getAlumnos4() {
		return this.alumnos4;
	}

	public void setAlumnos4(List<Alumno> alumnos4) {
		this.alumnos4 = alumnos4;
	}

	public Alumno addAlumnos4(Alumno alumnos4) {
		getAlumnos4().add(alumnos4);
		alumnos4.setUsuario4(this);

		return alumnos4;
	}

	public Alumno removeAlumnos4(Alumno alumnos4) {
		getAlumnos4().remove(alumnos4);
		alumnos4.setUsuario4(null);

		return alumnos4;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setUsuario(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setUsuario(null);

		return asistencia;
	}
	
	public List<Caja> getCajas1() {
		return this.cajas1;
	}

	public void setCajas1(List<Caja> cajas1) {
		this.cajas1 = cajas1;
	}

	public Caja addCajas1(Caja cajas1) {
		getCajas1().add(cajas1);
		cajas1.setUsuario1(this);

		return cajas1;
	}

	public Caja removeCajas1(Caja cajas1) {
		getCajas1().remove(cajas1);
		cajas1.setUsuario1(null);

		return cajas1;
	}

	public List<Caja> getCajas2() {
		return this.cajas2;
	}

	public void setCajas2(List<Caja> cajas2) {
		this.cajas2 = cajas2;
	}

	public Caja addCajas2(Caja cajas2) {
		getCajas2().add(cajas2);
		cajas2.setUsuario2(this);

		return cajas2;
	}

	public Caja removeCajas2(Caja cajas2) {
		getCajas2().remove(cajas2);
		cajas2.setUsuario2(null);

		return cajas2;
	}

	public List<Cuota> getCuotas1() {
		return this.cuotas1;
	}

	public void setCuotas1(List<Cuota> cuotas1) {
		this.cuotas1 = cuotas1;
	}

	public Cuota addCuotas1(Cuota cuotas1) {
		getCuotas1().add(cuotas1);
		cuotas1.setUsuario1(this);

		return cuotas1;
	}

	public Cuota removeCuotas1(Cuota cuotas1) {
		getCuotas1().remove(cuotas1);
		cuotas1.setUsuario1(null);

		return cuotas1;
	}

	public List<Cuota> getCuotas2() {
		return this.cuotas2;
	}

	public void setCuotas2(List<Cuota> cuotas2) {
		this.cuotas2 = cuotas2;
	}

	public Cuota addCuotas2(Cuota cuotas2) {
		getCuotas2().add(cuotas2);
		cuotas2.setUsuario2(this);

		return cuotas2;
	}

	public Cuota removeCuotas2(Cuota cuotas2) {
		getCuotas2().remove(cuotas2);
		cuotas2.setUsuario2(null);

		return cuotas2;
	}

	public List<Cuota> getCuotas3() {
		return this.cuotas3;
	}

	public void setCuotas3(List<Cuota> cuotas3) {
		this.cuotas3 = cuotas3;
	}

	public Cuota addCuotas3(Cuota cuotas3) {
		getCuotas3().add(cuotas3);
		cuotas3.setUsuario3(this);

		return cuotas3;
	}

	public Cuota removeCuotas3(Cuota cuotas3) {
		getCuotas3().remove(cuotas3);
		cuotas3.setUsuario3(null);

		return cuotas3;
	}

	public List<Curso> getCursos1() {
		return this.cursos1;
	}

	public void setCursos1(List<Curso> cursos1) {
		this.cursos1 = cursos1;
	}

	public Curso addCursos1(Curso cursos1) {
		getCursos1().add(cursos1);
		cursos1.setUsuario1(this);

		return cursos1;
	}

	public Curso removeCursos1(Curso cursos1) {
		getCursos1().remove(cursos1);
		cursos1.setUsuario1(null);

		return cursos1;
	}

	public List<Curso> getCursos2() {
		return this.cursos2;
	}

	public void setCursos2(List<Curso> cursos2) {
		this.cursos2 = cursos2;
	}

	public Curso addCursos2(Curso cursos2) {
		getCursos2().add(cursos2);
		cursos2.setUsuario2(this);

		return cursos2;
	}

	public Curso removeCursos2(Curso cursos2) {
		getCursos2().remove(cursos2);
		cursos2.setUsuario2(null);

		return cursos2;
	}

	public List<Curso> getCursos3() {
		return this.cursos3;
	}

	public void setCursos3(List<Curso> cursos3) {
		this.cursos3 = cursos3;
	}

	public Curso addCursos3(Curso cursos3) {
		getCursos3().add(cursos3);
		cursos3.setUsuario3(this);

		return cursos3;
	}

	public Curso removeCursos3(Curso cursos3) {
		getCursos3().remove(cursos3);
		cursos3.setUsuario3(null);

		return cursos3;
	}

	public List<Domicilio> getDomicilios1() {
		return this.domicilios1;
	}

	public void setDomicilios1(List<Domicilio> domicilios1) {
		this.domicilios1 = domicilios1;
	}

	public Domicilio addDomicilios1(Domicilio domicilios1) {
		getDomicilios1().add(domicilios1);
		domicilios1.setUsuario1(this);

		return domicilios1;
	}

	public Domicilio removeDomicilios1(Domicilio domicilios1) {
		getDomicilios1().remove(domicilios1);
		domicilios1.setUsuario1(null);

		return domicilios1;
	}

	public List<Domicilio> getDomicilios2() {
		return this.domicilios2;
	}

	public void setDomicilios2(List<Domicilio> domicilios2) {
		this.domicilios2 = domicilios2;
	}

	public Domicilio addDomicilios2(Domicilio domicilios2) {
		getDomicilios2().add(domicilios2);
		domicilios2.setUsuario2(this);

		return domicilios2;
	}

	public Domicilio removeDomicilios2(Domicilio domicilios2) {
		getDomicilios2().remove(domicilios2);
		domicilios2.setUsuario2(null);

		return domicilios2;
	}

	public List<Domicilio> getDomicilios3() {
		return this.domicilios3;
	}

	public void setDomicilios3(List<Domicilio> domicilios3) {
		this.domicilios3 = domicilios3;
	}

	public Domicilio addDomicilios3(Domicilio domicilios3) {
		getDomicilios3().add(domicilios3);
		domicilios3.setUsuario3(this);

		return domicilios3;
	}

	public Domicilio removeDomicilios3(Domicilio domicilios3) {
		getDomicilios3().remove(domicilios3);
		domicilios3.setUsuario3(null);

		return domicilios3;
	}

	public List<Gasto> getGastos1() {
		return this.gastos1;
	}

	public void setGastos1(List<Gasto> gastos1) {
		this.gastos1 = gastos1;
	}

	public Gasto addGastos1(Gasto gastos1) {
		getGastos1().add(gastos1);
		gastos1.setUsuario1(this);

		return gastos1;
	}

	public Gasto removeGastos1(Gasto gastos1) {
		getGastos1().remove(gastos1);
		gastos1.setUsuario1(null);

		return gastos1;
	}

	public List<Gasto> getGastos2() {
		return this.gastos2;
	}

	public void setGastos2(List<Gasto> gastos2) {
		this.gastos2 = gastos2;
	}

	public Gasto addGastos2(Gasto gastos2) {
		getGastos2().add(gastos2);
		gastos2.setUsuario2(this);

		return gastos2;
	}

	public Gasto removeGastos2(Gasto gastos2) {
		getGastos2().remove(gastos2);
		gastos2.setUsuario2(null);

		return gastos2;
	}

	public List<Gasto> getGastos3() {
		return this.gastos3;
	}

	public void setGastos3(List<Gasto> gastos3) {
		this.gastos3 = gastos3;
	}

	public Gasto addGastos3(Gasto gastos3) {
		getGastos3().add(gastos3);
		gastos3.setUsuario3(this);

		return gastos3;
	}

	public Gasto removeGastos3(Gasto gastos3) {
		getGastos3().remove(gastos3);
		gastos3.setUsuario3(null);

		return gastos3;
	}
	
	public List<Inscripcione> getInscripciones1() {
		return this.inscripciones1;
	}

	public void setInscripciones1(List<Inscripcione> inscripciones1) {
		this.inscripciones1 = inscripciones1;
	}

	public Inscripcione addInscripciones1(Inscripcione inscripciones1) {
		getInscripciones1().add(inscripciones1);
		inscripciones1.setUsuario1(this);

		return inscripciones1;
	}

	public Inscripcione removeInscripciones1(Inscripcione inscripciones1) {
		getInscripciones1().remove(inscripciones1);
		inscripciones1.setUsuario1(null);

		return inscripciones1;
	}

	public List<Inscripcione> getInscripciones2() {
		return this.inscripciones2;
	}

	public void setInscripciones2(List<Inscripcione> inscripciones2) {
		this.inscripciones2 = inscripciones2;
	}

	public Inscripcione addInscripciones2(Inscripcione inscripciones2) {
		getInscripciones2().add(inscripciones2);
		inscripciones2.setUsuario2(this);

		return inscripciones2;
	}

	public Inscripcione removeInscripciones2(Inscripcione inscripciones2) {
		getInscripciones2().remove(inscripciones2);
		inscripciones2.setUsuario2(null);

		return inscripciones2;
	}

	public List<Inscripcione> getInscripciones3() {
		return this.inscripciones3;
	}

	public void setInscripciones3(List<Inscripcione> inscripciones3) {
		this.inscripciones3 = inscripciones3;
	}

	public Inscripcione addInscripciones3(Inscripcione inscripciones3) {
		getInscripciones3().add(inscripciones3);
		inscripciones3.setUsuario3(this);

		return inscripciones3;
	}

	public Inscripcione removeInscripciones3(Inscripcione inscripciones3) {
		getInscripciones3().remove(inscripciones3);
		inscripciones3.setUsuario3(null);

		return inscripciones3;
	}

	public List<Materia> getMaterias1() {
		return this.materias1;
	}

	public void setMaterias1(List<Materia> materias1) {
		this.materias1 = materias1;
	}

	public Materia addMaterias1(Materia materias1) {
		getMaterias1().add(materias1);
		materias1.setUsuario1(this);

		return materias1;
	}

	public Materia removeMaterias1(Materia materias1) {
		getMaterias1().remove(materias1);
		materias1.setUsuario1(null);

		return materias1;
	}

	public List<Materia> getMaterias2() {
		return this.materias2;
	}

	public void setMaterias2(List<Materia> materias2) {
		this.materias2 = materias2;
	}

	public Materia addMaterias2(Materia materias2) {
		getMaterias2().add(materias2);
		materias2.setUsuario2(this);

		return materias2;
	}

	public Materia removeMaterias2(Materia materias2) {
		getMaterias2().remove(materias2);
		materias2.setUsuario2(null);

		return materias2;
	}

	public List<Materia> getMaterias3() {
		return this.materias3;
	}

	public void setMaterias3(List<Materia> materias3) {
		this.materias3 = materias3;
	}

	public Materia addMaterias3(Materia materias3) {
		getMaterias3().add(materias3);
		materias3.setUsuario3(this);

		return materias3;
	}

	public Materia removeMaterias3(Materia materias3) {
		getMaterias3().remove(materias3);
		materias3.setUsuario3(null);

		return materias3;
	}
	
	public List<MatriculaAlumno> getMatriculaAlumnos1() {
		return this.matriculaAlumnos1;
	}

	public void setMatriculaAlumnos1(List<MatriculaAlumno> matriculaAlumnos1) {
		this.matriculaAlumnos1 = matriculaAlumnos1;
	}

	public MatriculaAlumno addMatriculaAlumnos1(MatriculaAlumno matriculaAlumnos1) {
		getMatriculaAlumnos1().add(matriculaAlumnos1);
		matriculaAlumnos1.setUsuario1(this);

		return matriculaAlumnos1;
	}

	public MatriculaAlumno removeMatriculaAlumnos1(MatriculaAlumno matriculaAlumnos1) {
		getMatriculaAlumnos1().remove(matriculaAlumnos1);
		matriculaAlumnos1.setUsuario1(null);

		return matriculaAlumnos1;
	}

	public List<MatriculaAlumno> getMatriculaAlumnos2() {
		return this.matriculaAlumnos2;
	}

	public void setMatriculaAlumnos2(List<MatriculaAlumno> matriculaAlumnos2) {
		this.matriculaAlumnos2 = matriculaAlumnos2;
	}

	public MatriculaAlumno addMatriculaAlumnos2(MatriculaAlumno matriculaAlumnos2) {
		getMatriculaAlumnos2().add(matriculaAlumnos2);
		matriculaAlumnos2.setUsuario2(this);

		return matriculaAlumnos2;
	}

	public MatriculaAlumno removeMatriculaAlumnos2(MatriculaAlumno matriculaAlumnos2) {
		getMatriculaAlumnos2().remove(matriculaAlumnos2);
		matriculaAlumnos2.setUsuario2(null);

		return matriculaAlumnos2;
	}

	public List<PagosCuota> getPagosCuotas1() {
		return this.pagosCuotas1;
	}

	public void setPagosCuotas1(List<PagosCuota> pagosCuotas1) {
		this.pagosCuotas1 = pagosCuotas1;
	}

	public PagosCuota addPagosCuotas1(PagosCuota pagosCuotas1) {
		getPagosCuotas1().add(pagosCuotas1);
		pagosCuotas1.setUsuario1(this);

		return pagosCuotas1;
	}

	public PagosCuota removePagosCuotas1(PagosCuota pagosCuotas1) {
		getPagosCuotas1().remove(pagosCuotas1);
		pagosCuotas1.setUsuario1(null);

		return pagosCuotas1;
	}

	public List<PagosCuota> getPagosCuotas2() {
		return this.pagosCuotas2;
	}

	public void setPagosCuotas2(List<PagosCuota> pagosCuotas2) {
		this.pagosCuotas2 = pagosCuotas2;
	}

	public PagosCuota addPagosCuotas2(PagosCuota pagosCuotas2) {
		getPagosCuotas2().add(pagosCuotas2);
		pagosCuotas2.setUsuario2(this);

		return pagosCuotas2;
	}

	public PagosCuota removePagosCuotas2(PagosCuota pagosCuotas2) {
		getPagosCuotas2().remove(pagosCuotas2);
		pagosCuotas2.setUsuario2(null);

		return pagosCuotas2;
	}

	public List<PagosCuota> getPagosCuotas3() {
		return this.pagosCuotas3;
	}

	public void setPagosCuotas3(List<PagosCuota> pagosCuotas3) {
		this.pagosCuotas3 = pagosCuotas3;
	}

	public PagosCuota addPagosCuotas3(PagosCuota pagosCuotas3) {
		getPagosCuotas3().add(pagosCuotas3);
		pagosCuotas3.setUsuario3(this);

		return pagosCuotas3;
	}

	public PagosCuota removePagosCuotas3(PagosCuota pagosCuotas3) {
		getPagosCuotas3().remove(pagosCuotas3);
		pagosCuotas3.setUsuario3(null);

		return pagosCuotas3;
	}

	public List<PagosMatricula> getPagosMatriculas1() {
		return this.pagosMatriculas1;
	}

	public void setPagosMatriculas1(List<PagosMatricula> pagosMatriculas1) {
		this.pagosMatriculas1 = pagosMatriculas1;
	}

	public PagosMatricula addPagosMatriculas1(PagosMatricula pagosMatriculas1) {
		getPagosMatriculas1().add(pagosMatriculas1);
		pagosMatriculas1.setUsuario1(this);

		return pagosMatriculas1;
	}

	public PagosMatricula removePagosMatriculas1(PagosMatricula pagosMatriculas1) {
		getPagosMatriculas1().remove(pagosMatriculas1);
		pagosMatriculas1.setUsuario1(null);

		return pagosMatriculas1;
	}

	public List<PagosMatricula> getPagosMatriculas2() {
		return this.pagosMatriculas2;
	}

	public void setPagosMatriculas2(List<PagosMatricula> pagosMatriculas2) {
		this.pagosMatriculas2 = pagosMatriculas2;
	}

	public PagosMatricula addPagosMatriculas2(PagosMatricula pagosMatriculas2) {
		getPagosMatriculas2().add(pagosMatriculas2);
		pagosMatriculas2.setUsuario2(this);

		return pagosMatriculas2;
	}

	public PagosMatricula removePagosMatriculas2(PagosMatricula pagosMatriculas2) {
		getPagosMatriculas2().remove(pagosMatriculas2);
		pagosMatriculas2.setUsuario2(null);

		return pagosMatriculas2;
	}

	public List<PagosMatricula> getPagosMatriculas3() {
		return this.pagosMatriculas3;
	}

	public void setPagosMatriculas3(List<PagosMatricula> pagosMatriculas3) {
		this.pagosMatriculas3 = pagosMatriculas3;
	}

	public PagosMatricula addPagosMatriculas3(PagosMatricula pagosMatriculas3) {
		getPagosMatriculas3().add(pagosMatriculas3);
		pagosMatriculas3.setUsuario3(this);

		return pagosMatriculas3;
	}

	public PagosMatricula removePagosMatriculas3(PagosMatricula pagosMatriculas3) {
		getPagosMatriculas3().remove(pagosMatriculas3);
		pagosMatriculas3.setUsuario3(null);

		return pagosMatriculas3;
	}

	public List<Profesore> getProfesores1() {
		return this.profesores1;
	}

	public void setProfesores1(List<Profesore> profesores1) {
		this.profesores1 = profesores1;
	}

	public Profesore addProfesores1(Profesore profesores1) {
		getProfesores1().add(profesores1);
		profesores1.setUsuario1(this);

		return profesores1;
	}

	public Profesore removeProfesores1(Profesore profesores1) {
		getProfesores1().remove(profesores1);
		profesores1.setUsuario1(null);

		return profesores1;
	}

	public List<Profesore> getProfesores2() {
		return this.profesores2;
	}

	public void setProfesores2(List<Profesore> profesores2) {
		this.profesores2 = profesores2;
	}

	public Profesore addProfesores2(Profesore profesores2) {
		getProfesores2().add(profesores2);
		profesores2.setUsuario2(this);

		return profesores2;
	}

	public Profesore removeProfesores2(Profesore profesores2) {
		getProfesores2().remove(profesores2);
		profesores2.setUsuario2(null);

		return profesores2;
	}

	public List<Profesore> getProfesores3() {
		return this.profesores3;
	}

	public void setProfesores3(List<Profesore> profesores3) {
		this.profesores3 = profesores3;
	}

	public Profesore addProfesores3(Profesore profesores3) {
		getProfesores3().add(profesores3);
		profesores3.setUsuario3(this);

		return profesores3;
	}

	public Profesore removeProfesores3(Profesore profesores3) {
		getProfesores3().remove(profesores3);
		profesores3.setUsuario3(null);

		return profesores3;
	}

	public List<Profesore> getProfesores4() {
		return this.profesores4;
	}

	public void setProfesores4(List<Profesore> profesores4) {
		this.profesores4 = profesores4;
	}

	public Profesore addProfesores4(Profesore profesores4) {
		getProfesores4().add(profesores4);
		profesores4.setUsuario4(this);

		return profesores4;
	}

	public Profesore removeProfesores4(Profesore profesores4) {
		getProfesores4().remove(profesores4);
		profesores4.setUsuario4(null);

		return profesores4;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Profesore getProfesore() {
		return this.profesore;
	}

	public void setProfesore(Profesore profesore) {
		this.profesore = profesore;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public List<Usuario> getUsuarios1() {
		return this.usuarios1;
	}

	public void setUsuarios1(List<Usuario> usuarios1) {
		this.usuarios1 = usuarios1;
	}

	public Usuario addUsuarios1(Usuario usuarios1) {
		getUsuarios1().add(usuarios1);
		usuarios1.setUsuario1(this);

		return usuarios1;
	}

	public Usuario removeUsuarios1(Usuario usuarios1) {
		getUsuarios1().remove(usuarios1);
		usuarios1.setUsuario1(null);

		return usuarios1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public List<Usuario> getUsuarios2() {
		return this.usuarios2;
	}

	public void setUsuarios2(List<Usuario> usuarios2) {
		this.usuarios2 = usuarios2;
	}

	public Usuario addUsuarios2(Usuario usuarios2) {
		getUsuarios2().add(usuarios2);
		usuarios2.setUsuario2(this);

		return usuarios2;
	}

	public Usuario removeUsuarios2(Usuario usuarios2) {
		getUsuarios2().remove(usuarios2);
		usuarios2.setUsuario2(null);

		return usuarios2;
	}

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}

	public List<Usuario> getUsuarios3() {
		return this.usuarios3;
	}

	public void setUsuarios3(List<Usuario> usuarios3) {
		this.usuarios3 = usuarios3;
	}

	public Usuario addUsuarios3(Usuario usuarios3) {
		getUsuarios3().add(usuarios3);
		usuarios3.setUsuario3(this);

		return usuarios3;
	}

	public Usuario removeUsuarios3(Usuario usuarios3) {
		getUsuarios3().remove(usuarios3);
		usuarios3.setUsuario3(null);

		return usuarios3;
	}
	
	public List<CuotaImpaga> getCuotaImpagas() {
		return this.cuotaImpagas;
	}

	public void setCuotaImpagas(List<CuotaImpaga> cuotaImpagas) {
		this.cuotaImpagas = cuotaImpagas;
	}

	public CuotaImpaga addCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().add(cuotaImpaga);
		cuotaImpaga.setUsuario(this);

		return cuotaImpaga;
	}

	public CuotaImpaga removeCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().remove(cuotaImpaga);
		cuotaImpaga.setUsuario(null);

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
		matriculaImpaga.setUsuario(this);

		return matriculaImpaga;
	}

	public MatriculaImpaga removeMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		getMatriculaImpagas().remove(matriculaImpaga);
		matriculaImpaga.setUsuario(null);

		return matriculaImpaga;
	}
	
	public List<Recurso> getRecursos1() {
		return this.recursos1;
	}

	public void setRecursos1(List<Recurso> recursos1) {
		this.recursos1 = recursos1;
	}

	public Recurso addRecursos1(Recurso recursos1) {
		getRecursos1().add(recursos1);
		recursos1.setUsuario1(this);

		return recursos1;
	}

	public Recurso removeRecursos1(Recurso recursos1) {
		getRecursos1().remove(recursos1);
		recursos1.setUsuario1(null);

		return recursos1;
	}

	public List<Recurso> getRecursos2() {
		return this.recursos2;
	}

	public void setRecursos2(List<Recurso> recursos2) {
		this.recursos2 = recursos2;
	}

	public Recurso addRecursos2(Recurso recursos2) {
		getRecursos2().add(recursos2);
		recursos2.setUsuario2(this);

		return recursos2;
	}

	public Recurso removeRecursos2(Recurso recursos2) {
		getRecursos2().remove(recursos2);
		recursos2.setUsuario2(null);

		return recursos2;
	}

	public List<Recurso> getRecursos3() {
		return this.recursos3;
	}

	public void setRecursos3(List<Recurso> recursos3) {
		this.recursos3 = recursos3;
	}

	public Recurso addRecursos3(Recurso recursos3) {
		getRecursos3().add(recursos3);
		recursos3.setUsuario3(this);

		return recursos3;
	}

	public Recurso removeRecursos3(Recurso recursos3) {
		getRecursos3().remove(recursos3);
		recursos3.setUsuario3(null);

		return recursos3;
	}
	
	public List<RecursoAlumno> getRecursoAlumnos() {
		return this.recursoAlumnos;
	}

	public void setRecursoAlumnos(List<RecursoAlumno> recursoAlumnos) {
		this.recursoAlumnos = recursoAlumnos;
	}

	public RecursoAlumno addRecursoAlumno(RecursoAlumno recursoAlumno) {
		getRecursoAlumnos().add(recursoAlumno);
		recursoAlumno.setUsuario(this);

		return recursoAlumno;
	}

	public RecursoAlumno removeRecursoAlumno(RecursoAlumno recursoAlumno) {
		getRecursoAlumnos().remove(recursoAlumno);
		recursoAlumno.setUsuario(null);

		return recursoAlumno;
	}

}