package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the alumnos database table.
 * 
 */
@Entity
@Table(name="alumnos")
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellido;

	private int dni;

	private int edad;

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

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	private String nombres;

	@Column(name="telefono_cel")
	private String telefonoCel;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	//bi-directional many-to-one association to Domicilio
	@ManyToOne
	@JoinColumn(name="id_domicilio")
	private Domicilio domicilio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario3;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_mod")
	private Usuario usuario4;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="alumno")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="alumno")
	private List<Cuota> cuotas;
	
	//bi-directional many-to-one association to MateriasCalificacion
	@OneToMany(mappedBy="alumno")
	private List<MateriasCalificacion> materiasCalificacions;

	//bi-directional many-to-one association to PagosCuota
	@OneToMany(mappedBy="alumno")
	private List<PagosCuota> pagosCuotas;

	//bi-directional many-to-one association to PagosMatricula
	@OneToMany(mappedBy="alumno")
	private List<PagosMatricula> pagosMatriculas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="alumno")
	private List<Usuario> usuarios;
	
	//bi-directional many-to-one association to CuotaImpaga
	@OneToMany(mappedBy="alumno")
	private List<CuotaImpaga> cuotaImpagas;

	//bi-directional many-to-one association to MatriculaImpaga
	@OneToMany(mappedBy="alumno")
	private List<MatriculaImpaga> matriculaImpagas;
	
	//bi-directional many-to-one association to RecursoAlumno
	@OneToMany(mappedBy="alumno")
	private List<RecursoAlumno> recursoAlumnos;
	
	//bi-directional many-to-one association to MesasAlumno
	@OneToMany(mappedBy="alumno")
	private List<MesasAlumno> mesasAlumnos;
	
	//bi-directional many-to-one association to PagosMesa
	@OneToMany(mappedBy="alumno")
	private List<PagosMesa> pagosMesas;

	public Alumno() {
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

	public int getDni() {
		return this.dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
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

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefonoCel() {
		return this.telefonoCel;
	}

	public void setTelefonoCel(String telefonoCel) {
		this.telefonoCel = telefonoCel;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public Domicilio getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
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

	public Usuario getUsuario4() {
		return this.usuario4;
	}

	public void setUsuario4(Usuario usuario4) {
		this.usuario4 = usuario4;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setAlumno(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setAlumno(null);

		return asistencia;
	}

	public List<Cuota> getCuotas() {
		return this.cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public Cuota addCuota(Cuota cuota) {
		getCuotas().add(cuota);
		cuota.setAlumno(this);

		return cuota;
	}

	public Cuota removeCuota(Cuota cuota) {
		getCuotas().remove(cuota);
		cuota.setAlumno(null);

		return cuota;
	}
	
	public List<MateriasCalificacion> getMateriasCalificacions() {
		return this.materiasCalificacions;
	}

	public void setMateriasCalificacions(List<MateriasCalificacion> materiasCalificacions) {
		this.materiasCalificacions = materiasCalificacions;
	}

	public MateriasCalificacion addMateriasCalificacion(MateriasCalificacion materiasCalificacion) {
		getMateriasCalificacions().add(materiasCalificacion);
		materiasCalificacion.setAlumno(this);

		return materiasCalificacion;
	}

	public MateriasCalificacion removeMateriasCalificacion(MateriasCalificacion materiasCalificacion) {
		getMateriasCalificacions().remove(materiasCalificacion);
		materiasCalificacion.setAlumno(null);

		return materiasCalificacion;
	}

	public List<PagosCuota> getPagosCuotas() {
		return this.pagosCuotas;
	}

	public void setPagosCuotas(List<PagosCuota> pagosCuotas) {
		this.pagosCuotas = pagosCuotas;
	}

	public PagosCuota addPagosCuota(PagosCuota pagosCuota) {
		getPagosCuotas().add(pagosCuota);
		pagosCuota.setAlumno(this);

		return pagosCuota;
	}

	public PagosCuota removePagosCuota(PagosCuota pagosCuota) {
		getPagosCuotas().remove(pagosCuota);
		pagosCuota.setAlumno(null);

		return pagosCuota;
	}

	public List<PagosMatricula> getPagosMatriculas() {
		return this.pagosMatriculas;
	}

	public void setPagosMatriculas(List<PagosMatricula> pagosMatriculas) {
		this.pagosMatriculas = pagosMatriculas;
	}

	public PagosMatricula addPagosMatricula(PagosMatricula pagosMatricula) {
		getPagosMatriculas().add(pagosMatricula);
		pagosMatricula.setAlumno(this);

		return pagosMatricula;
	}

	public PagosMatricula removePagosMatricula(PagosMatricula pagosMatricula) {
		getPagosMatriculas().remove(pagosMatricula);
		pagosMatricula.setAlumno(null);

		return pagosMatricula;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setAlumno(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setAlumno(null);

		return usuario;
	}
	
	public List<CuotaImpaga> getCuotaImpagas() {
		return this.cuotaImpagas;
	}

	public void setCuotaImpagas(List<CuotaImpaga> cuotaImpagas) {
		this.cuotaImpagas = cuotaImpagas;
	}

	public CuotaImpaga addCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().add(cuotaImpaga);
		cuotaImpaga.setAlumno(this);

		return cuotaImpaga;
	}

	public CuotaImpaga removeCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().remove(cuotaImpaga);
		cuotaImpaga.setAlumno(null);

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
		matriculaImpaga.setAlumno(this);

		return matriculaImpaga;
	}

	public MatriculaImpaga removeMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		getMatriculaImpagas().remove(matriculaImpaga);
		matriculaImpaga.setAlumno(null);

		return matriculaImpaga;
	}
	
	public List<RecursoAlumno> getRecursoAlumnos() {
		return this.recursoAlumnos;
	}

	public void setRecursoAlumnos(List<RecursoAlumno> recursoAlumnos) {
		this.recursoAlumnos = recursoAlumnos;
	}

	public RecursoAlumno addRecursoAlumno(RecursoAlumno recursoAlumno) {
		getRecursoAlumnos().add(recursoAlumno);
		recursoAlumno.setAlumno(this);

		return recursoAlumno;
	}

	public RecursoAlumno removeRecursoAlumno(RecursoAlumno recursoAlumno) {
		getRecursoAlumnos().remove(recursoAlumno);
		recursoAlumno.setAlumno(null);

		return recursoAlumno;
	}
	
	public List<MesasAlumno> getMesasAlumnos() {
		return this.mesasAlumnos;
	}

	public void setMesasAlumnos(List<MesasAlumno> mesasAlumnos) {
		this.mesasAlumnos = mesasAlumnos;
	}

	public MesasAlumno addMesasAlumno(MesasAlumno mesasAlumno) {
		getMesasAlumnos().add(mesasAlumno);
		mesasAlumno.setAlumno(this);

		return mesasAlumno;
	}

	public MesasAlumno removeMesasAlumno(MesasAlumno mesasAlumno) {
		getMesasAlumnos().remove(mesasAlumno);
		mesasAlumno.setAlumno(null);

		return mesasAlumno;
	}
	
	public List<PagosMesa> getPagosMesas() {
		return this.pagosMesas;
	}

	public void setPagosMesas(List<PagosMesa> pagosMesas) {
		this.pagosMesas = pagosMesas;
	}

	public PagosMesa addPagosMesa(PagosMesa pagosMesa) {
		getPagosMesas().add(pagosMesa);
		pagosMesa.setAlumno(this);

		return pagosMesa;
	}

	public PagosMesa removePagosMesa(PagosMesa pagosMesa) {
		getPagosMesas().remove(pagosMesa);
		pagosMesa.setAlumno(null);

		return pagosMesa;
	}

}