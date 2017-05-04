package promoda.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOGasto;
import promoda.model.Gasto;

public class DAOGastoImpl implements Serializable, DAOGasto {

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

	public int insertar(Gasto gasto) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(gasto);
			tx.commit();
			return gasto.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Gasto gasto) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Gasto g SET g.concepto = :pConcepto, g.enabled = :pEnabled, "
					+ "g.fecha = :pFecha, g.fechaAlta = :pFechaAlta, g.fechaBaja = :pFechaBaja, g.fechaMod = :pFechaMod, "
					+ "g.monto = :pMonto, g.usuario1 = :pUsuario1, g.usuario2 = :pUsuario2, g.usuario3 = :pUsuario3 "
					+ "WHERE g.id = :pId", Gasto.class);
			locQuery.setParameter("pConcepto", gasto.getConcepto());
			locQuery.setParameter("pEnabled", gasto.getEnabled());
			locQuery.setParameter("pFecha", gasto.getFecha());
			locQuery.setParameter("pFechaAlta", gasto.getFechaAlta());
			locQuery.setParameter("pFechaBaja", gasto.getFechaBaja());
			locQuery.setParameter("pFechaMod", gasto.getFechaMod());
			locQuery.setParameter("pMonto", gasto.getMonto());
			locQuery.setParameter("pUsuario1", gasto.getUsuario1());
			locQuery.setParameter("pUsuario2", gasto.getUsuario2());
			locQuery.setParameter("pUsuario3", gasto.getUsuario3());
			locQuery.setParameter("pId", gasto.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return gasto.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Gasto get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.id = :pId", Gasto.class);
		locQuery.setParameter("pId", id);
		Gasto gasto = new Gasto();
		try {
			gasto = (Gasto)	locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			gasto = new Gasto();
		}
		return gasto;
	}

	public List<Gasto> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g", Gasto.class);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Gasto> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.enabled = :pEnabled", Gasto.class);
		locQuery.setParameter("pEnabled", estado);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Gasto> getLista(Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.fecha BETWEEN :pInicio AND :pFin", Gasto.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Gasto> getLista(boolean estado, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT g FROM Gasto g WHERE g.enabled = :pEnabled "
				+ "AND g.fecha BETWEEN :pInicio AND :pFin", Gasto.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Gasto> lista = locQuery.getResultList();
		return lista;
	}

}
