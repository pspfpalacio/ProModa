package promoda.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inscripcion_motivos database table.
 * 
 */
@Entity
@Table(name="inscripcion_motivos")
@NamedQuery(name="InscripcionMotivo.findAll", query="SELECT i FROM InscripcionMotivo i")
public class InscripcionMotivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Inscripcione
	@ManyToOne
	@JoinColumn(name="id_inscripcion")
	private Inscripcione inscripcione;

	//bi-directional many-to-one association to Motivo
	@ManyToOne
	@JoinColumn(name="id_motivo")
	private Motivo motivo;

	public InscripcionMotivo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Inscripcione getInscripcione() {
		return this.inscripcione;
	}

	public void setInscripcione(Inscripcione inscripcione) {
		this.inscripcione = inscripcione;
	}

	public Motivo getMotivo() {
		return this.motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

}