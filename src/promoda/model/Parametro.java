package promoda.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the parametros database table.
 * 
 */
@Entity
@Table(name="parametros")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="dias_primer_vencimiento")
	private int diasPrimerVencimiento;

	@Column(name="dias_segundo_vencimiento")
	private int diasSegundoVencimiento;
	
	@Column(name="password_mp")
	private String passwordMp;
	
	@Column(name="porcentaje_mp")
	private float porcentajeMp;

	@Column(name="porcentaje_primer_vencimiento")
	private float porcentajePrimerVencimiento;

	@Column(name="porcentaje_segundo_vencimiento")
	private float porcentajeSegundoVencimiento;
	
	@Column(name="usuario_mp")
	private String usuarioMp;

	public Parametro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiasPrimerVencimiento() {
		return this.diasPrimerVencimiento;
	}

	public void setDiasPrimerVencimiento(int diasPrimerVencimiento) {
		this.diasPrimerVencimiento = diasPrimerVencimiento;
	}

	public int getDiasSegundoVencimiento() {
		return this.diasSegundoVencimiento;
	}

	public void setDiasSegundoVencimiento(int diasSegundoVencimiento) {
		this.diasSegundoVencimiento = diasSegundoVencimiento;
	}
	
	public String getPasswordMp() {
		return this.passwordMp;
	}

	public void setPasswordMp(String passwordMp) {
		this.passwordMp = passwordMp;
	}
	
	public float getPorcentajeMp() {
		return this.porcentajeMp;
	}

	public void setPorcentajeMp(float porcentajeMp) {
		this.porcentajeMp = porcentajeMp;
	}

	public float getPorcentajePrimerVencimiento() {
		return this.porcentajePrimerVencimiento;
	}

	public void setPorcentajePrimerVencimiento(float porcentajePrimerVencimiento) {
		this.porcentajePrimerVencimiento = porcentajePrimerVencimiento;
	}

	public float getPorcentajeSegundoVencimiento() {
		return this.porcentajeSegundoVencimiento;
	}

	public void setPorcentajeSegundoVencimiento(float porcentajeSegundoVencimiento) {
		this.porcentajeSegundoVencimiento = porcentajeSegundoVencimiento;
	}
	
	public String getUsuarioMp() {
		return this.usuarioMp;
	}

	public void setUsuarioMp(String usuarioMp) {
		this.usuarioMp = usuarioMp;
	}

}