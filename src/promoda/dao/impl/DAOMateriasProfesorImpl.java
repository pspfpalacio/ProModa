package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMateriasProfesor;
import promoda.model.Materia;
import promoda.model.MateriasProfesor;

public class DAOMateriasProfesorImpl implements Serializable,
		DAOMateriasProfesor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private void inicializar() {
		emf = Persistence.createEntityManagerFactory("ProModa");
		em = emf.createEntityManager();
	}
	
//	private void cerrarSesion() {
//		em.close();
//		emf.close();
//	}

	public int insertar(MateriasProfesor materiasProfesor) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(materiasProfesor);
			tx.commit();
			return materiasProfesor.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(MateriasProfesor materiasProfesor) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE MateriasProfesor m SET m.materia = :pMateria, m.profesore = :pProfesor, "
					+ "m.tipo = :pTipo "
					+ "WHERE m.id = :pId", MateriasProfesor.class);
			locQuery.setParameter("pMateria", materiasProfesor.getMateria());
			locQuery.setParameter("pProfesor", materiasProfesor.getProfesore());
			locQuery.setParameter("pTipo", materiasProfesor.getTipo());
			locQuery.setParameter("pId", materiasProfesor.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return materiasProfesor.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public MateriasProfesor get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasProfesor m WHERE m.id = :pId", MateriasProfesor.class);
		locQuery.setParameter("pId", id);
		MateriasProfesor materiasProfesor = new MateriasProfesor();
		try {
			materiasProfesor = (MateriasProfesor) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			materiasProfesor = new MateriasProfesor();
		}
		return materiasProfesor;
	}

	public MateriasProfesor get(Materia materia, String tipo) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasProfesor m WHERE m.materia = :pMateria AND m.tipo = :pTipo", MateriasProfesor.class);
		locQuery.setParameter("pMateria", materia);
		locQuery.setParameter("pTipo", tipo);
		MateriasProfesor materiasProfesor = new MateriasProfesor();
		try {
			materiasProfesor = (MateriasProfesor) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			materiasProfesor = new MateriasProfesor();
		}
		return materiasProfesor;
	}

	public List<MateriasProfesor> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasProfesor m", MateriasProfesor.class);
		List<MateriasProfesor> lista = locQuery.getResultList();
		return lista;
	}

	public List<MateriasProfesor> getLista(Materia materia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasProfesor m WHERE m.materia = :pMateria", MateriasProfesor.class);
		locQuery.setParameter("pMateria", materia);
		List<MateriasProfesor> lista = locQuery.getResultList();
		return lista;
	}

}
