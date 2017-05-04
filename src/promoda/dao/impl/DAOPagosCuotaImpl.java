package promoda.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOPagosCuota;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.PagosCuota;

public class DAOPagosCuotaImpl implements Serializable, DAOPagosCuota {

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

	public int insertar(PagosCuota pagosCuota) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(pagosCuota);
			tx.commit();
			return pagosCuota.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(PagosCuota pagosCuota) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE PagosCuota p SET p.alumno = :pAlumno, p.concepto = :pConcepto, p.cuota = :pCuota, "
					+ "p.enabled = :pEnabled, p.fecha = :pFecha, p.fechaAlta = :pFechaAlta, p.fechaBaja = :pFechaBaja, "
					+ "p.fechaMod = :pFechaMod, p.monto = :pMonto, p.usuario1 = :pUsuario1, p.usuario2 = :pUsuario2, p.usuario3 = :pUsuario3 "
					+ "WHERE p.id = :pId", PagosCuota.class);
			locQuery.setParameter("pAlumno", pagosCuota.getAlumno());
			locQuery.setParameter("pConcepto", pagosCuota.getConcepto());
			locQuery.setParameter("pCuota", pagosCuota.getCuota());
			locQuery.setParameter("pEnabled", pagosCuota.getEnabled());
			locQuery.setParameter("pFecha", pagosCuota.getFecha());
			locQuery.setParameter("pFechaAlta", pagosCuota.getFechaAlta());
			locQuery.setParameter("pFechaBaja", pagosCuota.getFechaBaja());
			locQuery.setParameter("pFechaMod", pagosCuota.getFechaMod());
			locQuery.setParameter("pMonto", pagosCuota.getMonto());
			locQuery.setParameter("pUsuario1", pagosCuota.getUsuario1());
			locQuery.setParameter("pUsuario2", pagosCuota.getUsuario2());
			locQuery.setParameter("pUsuario3", pagosCuota.getUsuario3());
			locQuery.setParameter("pId", pagosCuota.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return pagosCuota.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public PagosCuota get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE p.id = :pId", PagosCuota.class);
		locQuery.setParameter("pId", id);
		PagosCuota pagosCuota = new PagosCuota();
		try {
			pagosCuota = (PagosCuota) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pagosCuota = new PagosCuota();
		}
		return pagosCuota;
	}

	public PagosCuota get(Alumno alumno, Cuota cuota) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE p.alumno = :pAlumno "
				+ "AND p.cuota = :pCuota AND p.enabled = :pEnabled", PagosCuota.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCuota", cuota);
		locQuery.setParameter("pEnabled", true);
		PagosCuota pagosCuota = new PagosCuota();
		try {
			pagosCuota = (PagosCuota) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pagosCuota = new PagosCuota();
		}
		return pagosCuota;
	}

	public List<PagosCuota> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p", PagosCuota.class);
		List<PagosCuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosCuota> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosCuota.class);
		locQuery.setParameter("pEnabled", estado);
		List<PagosCuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosCuota> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE p.alumno = :pAlumno", PagosCuota.class);
		locQuery.setParameter("pAlumno", alumno);
		List<PagosCuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosCuota> getLista(boolean estado, Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE p.enabled = :pEnabled "
				+ "AND p.alumno = :pAlumno ORDER BY p.fecha DESC", PagosCuota.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pAlumno", alumno);
		List<PagosCuota> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<PagosCuota> getLista(Alumno alumno, Date fechaI, Date fechaF){
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE p.enabled = :pEnabled "
				+ "AND p.alumno = :pAlumno AND p.fecha >= :pFechaI AND p.fecha <= :pFechaF ORDER BY p.fecha DESC", PagosCuota.class);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pFechaI", fechaI);
		locQuery.setParameter("pFechaF", fechaF);
		List<PagosCuota> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<PagosCuota> getLista(Alumno alumno, Date fecha, String tipo){
		inicializar();
		List<PagosCuota> lista;
		lista = new ArrayList<PagosCuota>();
		
		if(tipo == "I"){
			Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE "
					+ "p.alumno = :pAlumno AND p.fecha >= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosCuota.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pAlumno", alumno);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		if(tipo == "F"){
			Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE "
					+ "p.alumno = :pAlumno AND p.fecha <= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosCuota.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pAlumno", alumno);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		return lista;
	}
	
    public List<PagosCuota> getLista(Date fechaI, Date fechaF){
    	inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE p.enabled = :pEnabled "
				+ "AND p.fecha >= :pFechaI AND p.fecha <= :pFechaF ORDER BY p.fecha DESC", PagosCuota.class);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pFechaI", fechaI);
		locQuery.setParameter("pFechaF", fechaF);
		List<PagosCuota> lista = locQuery.getResultList();
		return lista;
    }
	
	public List<PagosCuota> getLista(Date fecha, String tipo){
		inicializar();
		List<PagosCuota> lista;
		lista = new ArrayList<PagosCuota>();
		
		if(tipo == "I"){
			Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE "
					+ "p.fecha >= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosCuota.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		if(tipo == "F"){
			Query locQuery = em.createQuery("SELECT p FROM PagosCuota p WHERE "
					+ "p.fecha <= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosCuota.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		return lista;
		
	}

}
