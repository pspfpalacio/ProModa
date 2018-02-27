package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cuotas database table.
 * 
 */
@Entity
@Table(name="cuotas")
@NamedQuery(name="Cuota.findAll", query="SELECT c FROM Cuota c")
public class Cuota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String detalle;

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
	@Column(name="fecha_pago")
	private Date fechaPago;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private float monto;

	@Column(name="monto_pago")
	private float montoPago;

	private boolean paga;
	
	@Column(name="porcentaje_pv")
	private float porcentajePv;

	@Column(name="porcentaje_sv")
	private float porcentajeSv;
	
	@Temporal(TemporalType.DATE)
	@Column(name="segundo_vencimiento")
	private Date segundoVencimiento;
	
	//bi-directional many-to-one association to CuotaImpaga
	@OneToMany(mappedBy="cuota")
	private List<CuotaImpaga> cuotaImpagas;

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

	//bi-directional many-to-one association to PagosCuota
	@OneToMany(mappedBy="cuota")
	private List<PagosCuota> pagosCuotas;

	public Cuota() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
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

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public float getMontoPago() {
		return this.montoPago;
	}

	public void setMontoPago(float montoPago) {
		this.montoPago = montoPago;
	}

	public boolean getPaga() {
		return this.paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	
	public float getPorcentajePv() {
		return this.porcentajePv;
	}

	public void setPorcentajePv(float porcentajePv) {
		this.porcentajePv = porcentajePv;
	}

	public float getPorcentajeSv() {
		return this.porcentajeSv;
	}

	public void setPorcentajeSv(float porcentajeSv) {
		this.porcentajeSv = porcentajeSv;
	}
	
	public Date getSegundoVencimiento() {
		return this.segundoVencimiento;
	}

	public void setSegundoVencimiento(Date segundoVencimiento) {
		this.segundoVencimiento = segundoVencimiento;
	}
	
	public List<CuotaImpaga> getCuotaImpagas() {
		return this.cuotaImpagas;
	}

	public void setCuotaImpagas(List<CuotaImpaga> cuotaImpagas) {
		this.cuotaImpagas = cuotaImpagas;
	}

	public CuotaImpaga addCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().add(cuotaImpaga);
		cuotaImpaga.setCuota(this);

		return cuotaImpaga;
	}

	public CuotaImpaga removeCuotaImpaga(CuotaImpaga cuotaImpaga) {
		getCuotaImpagas().remove(cuotaImpaga);
		cuotaImpaga.setCuota(null);

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
	
	public MatriculaAlumno getMatriculaAlumno() {
		return this.matriculaAlumno;
	}

	public void setMatriculaAlumno(MatriculaAlumno matriculaAlumno) {
		this.matriculaAlumno = matriculaAlumno;
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

	public List<PagosCuota> getPagosCuotas() {
		return this.pagosCuotas;
	}

	public void setPagosCuotas(List<PagosCuota> pagosCuotas) {
		this.pagosCuotas = pagosCuotas;
	}

	public PagosCuota addPagosCuota(PagosCuota pagosCuota) {
		getPagosCuotas().add(pagosCuota);
		pagosCuota.setCuota(this);

		return pagosCuota;
	}

	public PagosCuota removePagosCuota(PagosCuota pagosCuota) {
		getPagosCuotas().remove(pagosCuota);
		pagosCuota.setCuota(null);

		return pagosCuota;
	}

}