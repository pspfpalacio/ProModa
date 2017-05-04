package promoda.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOCaja;
import promoda.model.Caja;
import promoda.model.Inscripcione;

public class DAOCajaImpl implements Serializable, DAOCaja {

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

	public int insertar(Caja caja) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(caja);
			tx.commit();
			return caja.getId();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Caja caja) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Caja c SET c.concepto = :pConcepto, c.enabled = :pEnabled, c.entrada = :pEntrada, "
					+ "c.fecha = :pFecha, c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, c.idMovimiento = :pIdMovimiento, c.monto = :pMonto, "
					+ "c.nombreMovimiento = :pNombreMovimiento, c.salida = :pSalida, c.tipo = :pTipo, c.total = :pTotal, c.usuario1 = :pUsuarioAlta, "
					+ "c.usuario2 = :pUsuarioBaja "
					+ "WHERE c.id = :pId", Inscripcione.class);
			locQuery.setParameter("pConcepto", caja.getConcepto());
			locQuery.setParameter("pEnabled", caja.getEnabled());
			locQuery.setParameter("pEntrada", caja.getEntrada());
			locQuery.setParameter("pFecha", caja.getFecha());
			locQuery.setParameter("pFechaAlta", caja.getFechaAlta());
			locQuery.setParameter("pFechaBaja", caja.getFechaBaja());
			locQuery.setParameter("pIdMovimiento", caja.getIdMovimiento());
			locQuery.setParameter("pMonto", caja.getMonto());
			locQuery.setParameter("pNombreMovimiento", caja.getNombreMovimiento());
			locQuery.setParameter("pSalida", caja.getSalida());
			locQuery.setParameter("pTipo", caja.getTipo());
			locQuery.setParameter("pTotal", caja.getTotal());
			locQuery.setParameter("pUsuarioAlta", caja.getUsuario1());			
			locQuery.setParameter("pUsuarioBaja", caja.getUsuario2());
			locQuery.setParameter("pId", caja.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return caja.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Caja get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.id = :pId", Caja.class);
		locQuery.setParameter("pId", id);
		Caja caja = new Caja();
		try {
			caja = (Caja) locQuery.getSingleResult();
		} catch (Exception e) {
			caja = new Caja();
		}
		return caja;
	}

	public Caja get(int idMovimiento, String tipoMovimiento) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.idMovimiento = :pIdMovimiento "
				+ "AND c.nombreMovimiento = :pNombreMovimiento AND c.enabled = :pEnabled", Caja.class);
		locQuery.setParameter("pIdMovimiento", idMovimiento);
		locQuery.setParameter("pNombreMovimiento", tipoMovimiento);
		locQuery.setParameter("pEnabled", true);
		Caja caja = new Caja();
		try {
			caja = (Caja) locQuery.getSingleResult();
		} catch (Exception e) {
			caja = new Caja();
		}
		return caja;
	}

	public List<Caja> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.enabled = :pEnabled ORDER BY c.fecha DESC, c.id DESC", Caja.class);
		locQuery.setParameter("pEnabled", true);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}

	public List<Caja> getLista(Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.fecha = :pFecha "
				+ "AND c.enabled = :pEnabled ORDER BY c.fecha DESC, c.id DESC", Caja.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pEnabled", true);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}

	public List<Caja> getLista(Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.fecha BETWEEN :pInicio AND :pFin "
				+ "AND c.enabled = :pEnabled ORDER BY c.fecha DESC, c.id DESC", Caja.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pEnabled", true);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Caja> getLista(String tipo) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.tipo = :pTipo "
				+ "AND c.enabled = :pEnabled ORDER BY c.fecha DESC, c.id DESC", Caja.class);
		locQuery.setParameter("pTipo", tipo);
		locQuery.setParameter("pEnabled", true);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}

	public List<Caja> getLista(Date fechaInicio, Date fechaFin,
			String tipo) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.fecha BETWEEN :pInicio AND :pFin "
				+ "AND c.tipo = :pTipo AND c.enabled = :pEnabled ORDER BY c.fecha DESC, c.id DESC", Caja.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		locQuery.setParameter("pTipo", tipo);
		locQuery.setParameter("pEnabled", true);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Caja> getListaOrdenada() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.enabled = :pEnabled ORDER BY c.fecha, c.id", Caja.class);
		locQuery.setParameter("pEnabled", true);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Caja> getListaOrdenada(Date fechaInicio) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Caja c WHERE c.enabled = :pEnabled "
				+ "AND c.fecha >= :pFechaInicio ORDER BY c.fecha, c.id", Caja.class);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pFechaInicio", fechaInicio);
		List<Caja> lista = locQuery.getResultList();
		return lista;
	}

}
