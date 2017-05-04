package promoda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the localidades database table.
 * 
 */
@Entity
@Table(name="localidades")
@NamedQuery(name="Localidade.findAll", query="SELECT l FROM Localidade l")
public class Localidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Domicilio
	@OneToMany(mappedBy="localidade")
	private List<Domicilio> domicilios;
	
	//bi-directional many-to-one association to Inscripcione
	@OneToMany(mappedBy="localidade")
	private List<Inscripcione> inscripciones;

	//bi-directional many-to-one association to Provinicia
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;

	public Localidade() {
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

	public List<Domicilio> getDomicilios() {
		return this.domicilios;
	}

	public void setDomicilios(List<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public Domicilio addDomicilio(Domicilio domicilio) {
		getDomicilios().add(domicilio);
		domicilio.setLocalidade(this);

		return domicilio;
	}

	public Domicilio removeDomicilio(Domicilio domicilio) {
		getDomicilios().remove(domicilio);
		domicilio.setLocalidade(null);

		return domicilio;
	}
	
	public List<Inscripcione> getInscripciones() {
		return this.inscripciones;
	}

	public void setInscripciones(List<Inscripcione> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Inscripcione addInscripcione(Inscripcione inscripcione) {
		getInscripciones().add(inscripcione);
		inscripcione.setLocalidade(this);

		return inscripcione;
	}

	public Inscripcione removeInscripcione(Inscripcione inscripcione) {
		getInscripciones().remove(inscripcione);
		inscripcione.setLocalidade(null);

		return inscripcione;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}