package promoda.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the motivos database table.
 * 
 */
@Entity
@Table(name="motivos")
@NamedQuery(name="Motivo.findAll", query="SELECT m FROM Motivo m")
public class Motivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String motivo;

	//bi-directional many-to-one association to InscripcionMotivo
	@OneToMany(mappedBy="motivo")
	private List<InscripcionMotivo> inscripcionMotivos;

	public Motivo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<InscripcionMotivo> getInscripcionMotivos() {
		return this.inscripcionMotivos;
	}

	public void setInscripcionMotivos(List<InscripcionMotivo> inscripcionMotivos) {
		this.inscripcionMotivos = inscripcionMotivos;
	}

	public InscripcionMotivo addInscripcionMotivo(InscripcionMotivo inscripcionMotivo) {
		getInscripcionMotivos().add(inscripcionMotivo);
		inscripcionMotivo.setMotivo(this);

		return inscripcionMotivo;
	}

	public InscripcionMotivo removeInscripcionMotivo(InscripcionMotivo inscripcionMotivo) {
		getInscripcionMotivos().remove(inscripcionMotivo);
		inscripcionMotivo.setMotivo(null);

		return inscripcionMotivo;
	}

}