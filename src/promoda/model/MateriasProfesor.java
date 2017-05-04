package promoda.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the materias_profesor database table.
 * 
 */
@Entity
@Table(name="materias_profesor")
@NamedQuery(name="MateriasProfesor.findAll", query="SELECT m FROM MateriasProfesor m")
public class MateriasProfesor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String tipo;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="id_materia")
	private Materia materia;

	//bi-directional many-to-one association to Profesore
	@ManyToOne
	@JoinColumn(name="id_profesor")
	private Profesore profesore;

	public MateriasProfesor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Profesore getProfesore() {
		return this.profesore;
	}

	public void setProfesore(Profesore profesore) {
		this.profesore = profesore;
	}

}