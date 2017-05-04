package promoda.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOParametro;
import promoda.model.Alumno;
import promoda.model.Parametro;

public class DAOParametroImpl implements Serializable, DAOParametro {

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

	public int insertar(Parametro parametro) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(parametro);
			tx.commit();
			return parametro.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public int update(Parametro parametro) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Parametro p SET p.diasPrimerVencimiento = :pDiasPrimerVencimiento, "
					+ "p.diasSegundoVencimiento = :pDiasSegundoVencimiento, p.passwordMp = :pPasswordMP, p.porcentajeMp = :pPorcentajeMP, "
					+ "p.porcentajePrimerVencimiento = :pPorcentajePrimerVencimiento, p.porcentajeSegundoVencimiento = :pPorcentajeSegundoVencimiento, "
					+ "p.usuarioMp = :pUsuarioMP "
					+ "WHERE p.id = :pId", Parametro.class);
			locQuery.setParameter("pDiasPrimerVencimiento", parametro.getDiasPrimerVencimiento());
			locQuery.setParameter("pDiasSegundoVencimiento", parametro.getDiasSegundoVencimiento());
			locQuery.setParameter("pPasswordMP", parametro.getPasswordMp());
			locQuery.setParameter("pPorcentajeMP", parametro.getPorcentajeMp());
			locQuery.setParameter("pPorcentajePrimerVencimiento", parametro.getPorcentajePrimerVencimiento());
			locQuery.setParameter("pPorcentajeSegundoVencimiento", parametro.getPorcentajeSegundoVencimiento());
			locQuery.setParameter("pUsuarioMP", parametro.getUsuarioMp());
			locQuery.setParameter("pId", parametro.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return parametro.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public Parametro get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Parametro p WHERE p.id = :pId", Parametro.class);
		locQuery.setParameter("pId", id);
		Parametro parametro = new Parametro();
		try {
			parametro = (Parametro) locQuery.getSingleResult();
		} catch (Exception e) {
			parametro = new Parametro();
		}
		return parametro;
	}

}
