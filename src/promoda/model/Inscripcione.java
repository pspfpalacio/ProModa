package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inscripciones database table.
 * 
 */
@Entity
@Table(name="inscripciones")
@NamedQuery(name="Inscripcione.findAll", query="SELECT i FROM Inscripcione i")
public class Inscripcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellido;

	@Column(name="como_entero")
	private String comoEntero;

	@Column(name="conocimientos_previos")
	private boolean conocimientosPrevios;

	private int dni;

	private int edad;

	private String email;

	private boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name="hora_cursado")
	private String horaCursado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="hora_cursado1")
	private Date horaCursado1;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="hora_cursado2")
	private Date horaCursado2;

	private String nombre;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	@Column(name="telefono_cel")
	private String telefonoCel;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	private boolean valida;
	
	//bi-directional many-to-one association to InscripcionMotivo
	@OneToMany(mappedBy="inscripcione")
	private List<InscripcionMotivo> inscripcionMotivos;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	//bi-directional many-to-one association to Domicilio
	@ManyToOne
	@JoinColumn(name="id_domicilio")
	private Domicilio domicilio;

	//bi-directional many-to-one association to Localidade
	@ManyToOne
	@JoinColumn(name="id_localidad")
	private Localidade localidade;
	
	//bi-directional many-to-one association to Matricula
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;

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

	//bi-directional many-to-one association to PlanPago
	@OneToMany(mappedBy="inscripcione")
	private List<PlanPago> planPagos;
	
	//bi-directional many-to-one association to InscripcionDia
	@OneToMany(mappedBy="inscripcione")
	private List<InscripcionDia> inscripcionDias;

	public Inscripcione() {
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

	public String getComoEntero() {
		return this.comoEntero;
	}

	public void setComoEntero(String comoEntero) {
		this.comoEntero = comoEntero;
	}

	public boolean getConocimientosPrevios() {
		return this.conocimientosPrevios;
	}

	public void setConocimientosPrevios(boolean conocimientosPrevios) {
		this.conocimientosPrevios = conocimientosPrevios;
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

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getHoraCursado() {
		return this.horaCursado;
	}

	public void setHoraCursado(String horaCursado) {
		this.horaCursado = horaCursado;
	}
	
	public Date getHoraCursado1() {
		return this.horaCursado1;
	}

	public void setHoraCursado1(Date horaCursado1) {
		this.horaCursado1 = horaCursado1;
	}

	public Date getHoraCursado2() {
		return this.horaCursado2;
	}

	public void setHoraCursado2(Date horaCursado2) {
		this.horaCursado2 = horaCursado2;
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

	public boolean getValida() {
		return this.valida;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}
	
	public List<InscripcionMotivo> getInscripcionMotivos() {
		return this.inscripcionMotivos;
	}

	public void setInscripcionMotivos(List<InscripcionMotivo> inscripcionMotivos) {
		this.inscripcionMotivos = inscripcionMotivos;
	}

	public InscripcionMotivo addInscripcionMotivo(InscripcionMotivo inscripcionMotivo) {
		getInscripcionMotivos().add(inscripcionMotivo);
		inscripcionMotivo.setInscripcione(this);

		return inscripcionMotivo;
	}

	public InscripcionMotivo removeInscripcionMotivo(InscripcionMotivo inscripcionMotivo) {
		getInscripcionMotivos().remove(inscripcionMotivo);
		inscripcionMotivo.setInscripcione(null);

		return inscripcionMotivo;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Domicilio getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	
	public Matricula getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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

	public List<PlanPago> getPlanPagos() {
		return this.planPagos;
	}

	public void setPlanPagos(List<PlanPago> planPagos) {
		this.planPagos = planPagos;
	}

	public PlanPago addPlanPago(PlanPago planPago) {
		getPlanPagos().add(planPago);
		planPago.setInscripcione(this);

		return planPago;
	}

	public PlanPago removePlanPago(PlanPago planPago) {
		getPlanPagos().remove(planPago);
		planPago.setInscripcione(null);

		return planPago;
	}
	
	public List<InscripcionDia> getInscripcionDias() {
		return this.inscripcionDias;
	}

	public void setInscripcionDias(List<InscripcionDia> inscripcionDias) {
		this.inscripcionDias = inscripcionDias;
	}

	public InscripcionDia addInscripcionDia(InscripcionDia inscripcionDia) {
		getInscripcionDias().add(inscripcionDia);
		inscripcionDia.setInscripcione(this);

		return inscripcionDia;
	}

	public InscripcionDia removeInscripcionDia(InscripcionDia inscripcionDia) {
		getInscripcionDias().remove(inscripcionDia);
		inscripcionDia.setInscripcione(null);

		return inscripcionDia;
	}

}