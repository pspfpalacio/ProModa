package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the domicilios database table.
 * 
 */
@Entity
@Table(name="domicilios")
@NamedQuery(name="Domicilio.findAll", query="SELECT d FROM Domicilio d")
public class Domicilio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String calle;

	@Column(name="codigo_postal")
	private String codigoPostal;

	private String departamento;

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

	private String numero;

	private String piso;

	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="domicilio")
	private List<Alumno> alumnos;

	//bi-directional many-to-one association to Localidade
	@ManyToOne
	@JoinColumn(name="id_localidad")
	private Localidade localidade;

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
	
	//bi-directional many-to-one association to Inscripcione
	@OneToMany(mappedBy="domicilio")
	private List<Inscripcione> inscripciones;

	//bi-directional many-to-one association to Profesore
	@OneToMany(mappedBy="domicilio")
	private List<Profesore> profesores;

	public Domicilio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return this.piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Alumno addAlumno(Alumno alumno) {
		getAlumnos().add(alumno);
		alumno.setDomicilio(this);

		return alumno;
	}

	public Alumno removeAlumno(Alumno alumno) {
		getAlumnos().remove(alumno);
		alumno.setDomicilio(null);

		return alumno;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
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
	
	public List<Inscripcione> getInscripciones() {
		return this.inscripciones;
	}

	public void setInscripciones(List<Inscripcione> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Inscripcione addInscripcione(Inscripcione inscripcione) {
		getInscripciones().add(inscripcione);
		inscripcione.setDomicilio(this);

		return inscripcione;
	}

	public Inscripcione removeInscripcione(Inscripcione inscripcione) {
		getInscripciones().remove(inscripcione);
		inscripcione.setDomicilio(null);

		return inscripcione;
	}

	public List<Profesore> getProfesores() {
		return this.profesores;
	}

	public void setProfesores(List<Profesore> profesores) {
		this.profesores = profesores;
	}

	public Profesore addProfesore(Profesore profesore) {
		getProfesores().add(profesore);
		profesore.setDomicilio(this);

		return profesore;
	}

	public Profesore removeProfesore(Profesore profesore) {
		getProfesores().remove(profesore);
		profesore.setDomicilio(null);

		return profesore;
	}

}