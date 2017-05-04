package promoda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the plan_pago database table.
 * 
 */
@Entity
@Table(name="plan_pago")
@NamedQuery(name="PlanPago.findAll", query="SELECT p FROM PlanPago p")
public class PlanPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cant_cuotas")
	private int cantCuotas;

	@Column(name="descuento_curso")
	private int descuentoCurso;
	
	@Column(name="descuento_matricula")
	private int descuentoMatricula;

	@Column(name="monto_cuota")
	private float montoCuota;
	
	@Column(name="monto_matricula")
	private float montoMatricula;
	
	@Temporal(TemporalType.DATE)
	@Column(name="primer_vencimiento")
	private Date primerVencimiento;

	//bi-directional many-to-one association to Inscripcione
	@ManyToOne
	@JoinColumn(name="id_inscripcion")
	private Inscripcione inscripcione;

	//bi-directional many-to-one association to Matricula
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;

	public PlanPago() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantCuotas() {
		return this.cantCuotas;
	}

	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public int getDescuentoCurso() {
		return this.descuentoCurso;
	}

	public void setDescuentoCurso(int descuentoCurso) {
		this.descuentoCurso = descuentoCurso;
	}

	public int getDescuentoMatricula() {
		return this.descuentoMatricula;
	}

	public void setDescuentoMatricula(int descuentoMatricula) {
		this.descuentoMatricula = descuentoMatricula;
	}

	public float getMontoCuota() {
		return this.montoCuota;
	}

	public void setMontoCuota(float montoCuota) {
		this.montoCuota = montoCuota;
	}
	
	public float getMontoMatricula() {
		return this.montoMatricula;
	}

	public void setMontoMatricula(float montoMatricula) {
		this.montoMatricula = montoMatricula;
	}
	
	public Date getPrimerVencimiento() {
		return this.primerVencimiento;
	}

	public void setPrimerVencimiento(Date primerVencimiento) {
		this.primerVencimiento = primerVencimiento;
	}

	public Inscripcione getInscripcione() {
		return this.inscripcione;
	}

	public void setInscripcione(Inscripcione inscripcione) {
		this.inscripcione = inscripcione;
	}

	public Matricula getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

}