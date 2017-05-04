package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOLocalidad;
import promoda.model.Localidade;
import promoda.model.Provincia;

public class DAOLocalidadImpl implements Serializable, DAOLocalidad {

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

	public int insertar(Localidade localidad) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(localidad);
			tx.commit();
			return localidad.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Localidade localidad) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Localidade l SET l.nombre = :pNombre, l.provincia = :pProvincia "
					+ "WHERE l.id = :pId", Localidade.class);
			locQuery.setParameter("pNombre", localidad.getNombre());
			locQuery.setParameter("pProvincia", localidad.getProvincia());
			locQuery.setParameter("pId", localidad.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return localidad.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Localidade get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM Localidade l WHERE l.id = :pId", Localidade.class);
		locQuery.setParameter("pId", id);
		Localidade localidade = new Localidade();
		try {
			localidade = (Localidade) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			localidade = new Localidade();
		}
		return localidade;
	}

	public List<Localidade> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM Localidade l", Localidade.class);
		List<Localidade> lista = locQuery.getResultList();
		return lista;
	}

	public List<Localidade> getLista(Provincia provincia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM Localidade l WHERE l.provincia = :pProvincia", Localidade.class);
		locQuery.setParameter("pProvincia", provincia);
		List<Localidade> lista = locQuery.getResultList();
		return lista;
	}

}
