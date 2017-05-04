package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOInscripcionDia;
import promoda.model.InscripcionDia;
import promoda.model.Inscripcione;

public class DAOInscripcionDiaImpl implements Serializable, DAOInscripcionDia {

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

	public int insertar(InscripcionDia inscripcionDia) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(inscripcionDia);
			tx.commit();
			return inscripcionDia.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public int update(InscripcionDia inscripcionDia) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE InscripcionDia i SET i.inscripcione = :pInscripcion, i.dia = :pDia "
					+ "WHERE i.id = :pId", InscripcionDia.class);
			locQuery.setParameter("pInscripcion", inscripcionDia.getInscripcione());
			locQuery.setParameter("pDia", inscripcionDia.getDia());
			locQuery.setParameter("pId", inscripcionDia.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return inscripcionDia.getId();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int delete(Inscripcione inscripcion) {
		try {
			inicializar();
			Query locQuery = em.createQuery("DELETE FROM InscripcionDia i WHERE i.inscripcione = :pInscripcion", InscripcionDia.class);
			locQuery.setParameter("pInscripcion", inscripcion);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public InscripcionDia get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM InscripcionDia i WHERE i.id = :pId", InscripcionDia.class);
		locQuery.setParameter("pId", id);
		InscripcionDia inscripcionDia = new InscripcionDia();
		try {
			inscripcionDia = (InscripcionDia) locQuery.getSingleResult();
		} catch (Exception e) {
			inscripcionDia = new InscripcionDia();
		}
		return inscripcionDia;
	}

	public List<InscripcionDia> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM InscripcionDia i", InscripcionDia.class);
		List<InscripcionDia> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(String dia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i.inscripcione FROM InscripcionDia i WHERE i.dia = :pDia", Inscripcione.class);
		locQuery.setParameter("pDia", dia);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<InscripcionDia> getLista(Inscripcione inscripcione) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM InscripcionDia i WHERE i.inscripcione = :pInscripcion", InscripcionDia.class);
		locQuery.setParameter("pInscripcion", inscripcione);
		List<InscripcionDia> lista = locQuery.getResultList();
		return lista;
	}

}
