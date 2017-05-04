package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOResgistroOnline;
import promoda.model.RegistroOnline;

public class DAORegistroOnlineImpl implements DAOResgistroOnline, Serializable {	
	
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

	public int insertar(RegistroOnline registroOnline) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(registroOnline);
			tx.commit();
			return registroOnline.getId();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public List<RegistroOnline> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RegistroOnline r ORDER BY r.fecha DESC", RegistroOnline.class);
		List<RegistroOnline> lista = locQuery.getResultList();
		return lista;
	}

}
