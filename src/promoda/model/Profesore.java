package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the profesores database table.
 * 
 */
@Entity
@Table(name="profesores")
@NamedQuery(name="Profesore.findAll", query="SELECT p FROM Profesore p")
public class Profesore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellido;

	private int dni;

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

	private String nombre;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	@Column(name="telefono_cel")
	private String telefonoCel;

	@Column(name="telefono_fijo")
	private String telefonoFijo;
	
	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="profesore1")
	private List<Materia> materias1;

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="profesore2")
	private List<Materia> materias2;

	//bi-directional many-to-one association to MateriasProfesor
	@OneToMany(mappedBy="profesore")
	private List<MateriasProfesor> materiasProfesors;

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

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="profesore")
	private List<Usuario> usuarios;

	public Profesore() {
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
	
	public List<Materia> getMaterias1() {
		return this.materias1;
	}

	public void setMaterias1(List<Materia> materias1) {
		this.materias1 = materias1;
	}

	public Materia addMaterias1(Materia materias1) {
		getMaterias1().add(materias1);
		materias1.setProfesore1(this);

		return materias1;
	}

	public Materia removeMaterias1(Materia materias1) {
		getMaterias1().remove(materias1);
		materias1.setProfesore1(null);

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
		materias2.setProfesore2(this);

		return materias2;
	}

	public Materia removeMaterias2(Materia materias2) {
		getMaterias2().remove(materias2);
		materias2.setProfesore2(null);

		return materias2;
	}

	public List<MateriasProfesor> getMateriasProfesors() {
		return this.materiasProfesors;
	}

	public void setMateriasProfesors(List<MateriasProfesor> materiasProfesors) {
		this.materiasProfesors = materiasProfesors;
	}

	public MateriasProfesor addMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().add(materiasProfesor);
		materiasProfesor.setProfesore(this);

		return materiasProfesor;
	}

	public MateriasProfesor removeMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().remove(materiasProfesor);
		materiasProfesor.setProfesore(null);

		return materiasProfesor;
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setProfesore(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setProfesore(null);

		return usuario;
	}

}