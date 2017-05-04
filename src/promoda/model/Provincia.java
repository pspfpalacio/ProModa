package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the provincias database table.
 * 
 */
@Entity
@Table(name="provincias")
@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;
	
	//bi-directional many-to-one association to Inscripcione
	@OneToMany(mappedBy="provincia")
	private List<Inscripcione> inscripciones;

	//bi-directional many-to-one association to Localidade
	@OneToMany(mappedBy="provincia")
	private List<Localidade> localidades;

	public Provincia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Inscripcione> getInscripciones() {
		return this.inscripciones;
	}

	public void setInscripciones(List<Inscripcione> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Inscripcione addInscripcione(Inscripcione inscripcione) {
		getInscripciones().add(inscripcione);
		inscripcione.setProvincia(this);

		return inscripcione;
	}

	public Inscripcione removeInscripcione(Inscripcione inscripcione) {
		getInscripciones().remove(inscripcione);
		inscripcione.setProvincia(null);

		return inscripcione;
	}

	public List<Localidade> getLocalidades() {
		return this.localidades;
	}

	public void setLocalidades(List<Localidade> localidades) {
		this.localidades = localidades;
	}

	public Localidade addLocalidade(Localidade localidade) {
		getLocalidades().add(localidade);
		localidade.setProvincia(this);

		return localidade;
	}

	public Localidade removeLocalidade(Localidade localidade) {
		getLocalidades().remove(localidade);
		localidade.setProvincia(null);

		return localidade;
	}

}