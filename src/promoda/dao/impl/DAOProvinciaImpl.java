package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOProvincia;
import promoda.model.Provincia;

public class DAOProvinciaImpl implements Serializable, DAOProvincia {

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

	public int insertar(Provincia provincia) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(provincia);
			tx.commit();
			return provincia.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Provincia provincia) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Provincia p SET p.nombre = :pNombre "
					+ "WHERE p.id = :pId", Provincia.class);
			locQuery.setParameter("pNombre", provincia.getNombre());
			locQuery.setParameter("pId", provincia.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return provincia.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Provincia get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Provincia p WHERE p.id = :pId", Provincia.class);
		locQuery.setParameter("pId", id);
		Provincia provincia = new Provincia();
		try {
			provincia = (Provincia) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			provincia = new Provincia();
		}
		return provincia;
	}

	public List<Provincia> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Provincia p", Provincia.class);
		List<Provincia> lista = locQuery.getResultList();
		return lista;
	}

}
