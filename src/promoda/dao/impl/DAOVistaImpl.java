package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOVista;
import promoda.model.Vista;

public class DAOVistaImpl implements Serializable, DAOVista {

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

	public int insertar(Vista vista) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(vista);
			tx.commit();
			return vista.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Vista vista) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Vista v SET v.nombre = :pNombre "
					+ "WHERE v.id = :pId", Vista.class);
			locQuery.setParameter("pNombre", vista.getNombre());
			locQuery.setParameter("pId", vista.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return vista.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Vista get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Vista v WHERE v.id = :pId", Vista.class);
		locQuery.setParameter("pId", id);
		Vista vista = new Vista();
		try {
			vista = (Vista) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			vista = new Vista();
		}
		return vista;
	}

	public List<Vista> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT v FROM Vista v", Vista.class);
		List<Vista> lista = locQuery.getResultList();
		return lista;
	}

}
