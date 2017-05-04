package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOInscripcionMotivo;
import promoda.model.InscripcionMotivo;
import promoda.model.Inscripcione;
import promoda.model.Motivo;

public class DAOInscripcionMotivoImpl implements Serializable,
		DAOInscripcionMotivo {

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

	public int insertar(InscripcionMotivo inscripcionMotivo) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(inscripcionMotivo);
			tx.commit();
			return inscripcionMotivo.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public int update(InscripcionMotivo inscripcionMotivo) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE InscripcionMotivo i SET i.inscripcione = :pInscripcion, i.motivo = :pMotivo "
					+ "WHERE i.id = :pId", InscripcionMotivo.class);
			locQuery.setParameter("pInscripcion", inscripcionMotivo.getInscripcione());
			locQuery.setParameter("pMotivo", inscripcionMotivo.getMotivo());
			locQuery.setParameter("pId", inscripcionMotivo.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return inscripcionMotivo.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public InscripcionMotivo get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM InscripcionMotivo i WHERE i.id = :pId", InscripcionMotivo.class);
		locQuery.setParameter("pId", id);
		InscripcionMotivo inscripcionMotivo = new InscripcionMotivo();
		try {
			inscripcionMotivo = (InscripcionMotivo) locQuery.getSingleResult();
		} catch (Exception e) {
			inscripcionMotivo = new InscripcionMotivo();
		}
		return inscripcionMotivo;
	}

	public List<InscripcionMotivo> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM InscripcionMotivo i", InscripcionMotivo.class);
		List<InscripcionMotivo> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(Motivo motivo) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i.inscripcione FROM InscripcionMotivo i WHERE i.motivo = :pMotivo", Inscripcione.class);
		locQuery.setParameter("pMotivo", motivo);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Motivo> getLista(Inscripcione inscripcione) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i.motivo FROM InscripcionMotivo i WHERE i.inscripcione = :pInscripcion", Motivo.class);
		locQuery.setParameter("pInscripcion", inscripcione);
		List<Motivo> lista = locQuery.getResultList();
		return lista;
	}

}
