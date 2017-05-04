package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMotivo;
import promoda.model.Alumno;
import promoda.model.Motivo;

public class DAOMotivoImpl implements Serializable, DAOMotivo {

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

	public int insertar(Motivo motivo) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(motivo);
			tx.commit();
			return motivo.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public int update(Motivo motivo) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Motivo m SET m.motivo = :pMotivo "
					+ "WHERE m.id = :pId", Alumno.class);
			locQuery.setParameter("pMotivo", motivo.getMotivo());
			locQuery.setParameter("pId", motivo.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return motivo.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public Motivo get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Motivo m WHERE m.id = :pId", Motivo.class);
		locQuery.setParameter("pId", id);
		Motivo motivo = new Motivo();
		try {
			motivo = (Motivo) locQuery.getSingleResult();
		} catch (Exception e) {
			motivo = new Motivo();
		}
		return motivo;
	}

	public List<Motivo> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Motivo m", Motivo.class);
		List<Motivo> lista = locQuery.getResultList();
		return lista;
	}

}
