package promoda.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inscripcion_dias database table.
 * 
 */
@Entity
@Table(name="inscripcion_dias")
@NamedQuery(name="InscripcionDia.findAll", query="SELECT i FROM InscripcionDia i")
public class InscripcionDia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String dia;

	//bi-directional many-to-one association to Inscripcione
	@ManyToOne
	@JoinColumn(name="id_insrcipcion")
	private Inscripcione inscripcione;

	public InscripcionDia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Inscripcione getInscripcione() {
		return this.inscripcione;
	}

	public void setInscripcione(Inscripcione inscripcione) {
		this.inscripcione = inscripcione;
	}

}