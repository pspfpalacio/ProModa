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

import promoda.dao.DAOPagosMatricula;
import promoda.model.Alumno;
import promoda.model.Matricula;
import promoda.model.PagosMatricula;

public class DAOPagosMatriculaImpl implements Serializable, DAOPagosMatricula {

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

	public int insertar(PagosMatricula pagosMatricula) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(pagosMatricula);
			tx.commit();
			return pagosMatricula.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(PagosMatricula pagosMatricula) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE PagosMatricula p SET p.alumno = :pAlumno, p.concepto = :pConcepto, "
					+ "p.enabled = :pEnabled, p.fecha = :pFecha, p.fechaAlta = :pFechaAlta, p.fechaBaja = :pFechaBaja, "
					+ "p.fechaMod = :pFechaMod, p.matricula = :pMatricula, p.monto = :pMonto, p.usuario1 = :pUsuario1, "
					+ "p.usuario2 = :pUsuario2, p.usuario3 = :pUsuario3 "
					+ "WHERE p.id = :pId", PagosMatricula.class);
			locQuery.setParameter("pAlumno", pagosMatricula.getAlumno());
			locQuery.setParameter("pConcepto", pagosMatricula.getConcepto());
			locQuery.setParameter("pEnabled", pagosMatricula.getEnabled());
			locQuery.setParameter("pFecha", pagosMatricula.getFecha());
			locQuery.setParameter("pFechaAlta", pagosMatricula.getFechaAlta());
			locQuery.setParameter("pFechaBaja", pagosMatricula.getFechaBaja());
			locQuery.setParameter("pFechaMod", pagosMatricula.getFechaMod());
			locQuery.setParameter("pMatricula", pagosMatricula.getMatricula());
			locQuery.setParameter("pMonto", pagosMatricula.getMonto());
			locQuery.setParameter("pUsuario1", pagosMatricula.getUsuario1());
			locQuery.setParameter("pUsuario2", pagosMatricula.getUsuario2());
			locQuery.setParameter("pUsuario3", pagosMatricula.getUsuario3());
			locQuery.setParameter("pId", pagosMatricula.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return pagosMatricula.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public PagosMatricula get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE p.id = :pId", PagosMatricula.class);
		locQuery.setParameter("pId", id);
		PagosMatricula pagosMatricula = new PagosMatricula();
		try {
			pagosMatricula = (PagosMatricula) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pagosMatricula = new PagosMatricula();
		}
		return pagosMatricula;
	}

	public PagosMatricula get(Alumno alumno, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE p.alumno = :pAlumno "
				+ "AND p.matricula = :pMatricula AND p.enabled = :pEnabled", PagosMatricula.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pEnabled", true);
		PagosMatricula pagosMatricula = new PagosMatricula();
		try {
			pagosMatricula = (PagosMatricula) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pagosMatricula = new PagosMatricula();
		}
		return pagosMatricula;
	}

	public List<PagosMatricula> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p", PagosMatricula.class);
		List<PagosMatricula> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosMatricula> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosMatricula.class);
		locQuery.setParameter("pEnabled", estado);
		List<PagosMatricula> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosMatricula> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE p.alumno = :pAlumno", PagosMatricula.class);
		locQuery.setParameter("pAlumno", alumno);
		List<PagosMatricula> lista = locQuery.getResultList();
		return lista;
	}

	public List<PagosMatricula> getLista(boolean estado, Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE p.enabled = :pEnabled "
				+ "AND p.alumno = :pAlumno ORDER BY p.fecha DESC", PagosMatricula.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pAlumno", alumno);
		List<PagosMatricula> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<PagosMatricula> getLista(Alumno alumno, Date fechaI, Date fechaF) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE "
				+ "p.alumno = :pAlumno AND p.fecha >= :pFechaI AND p.fecha <= :pFechaF AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosMatricula.class);
		locQuery.setParameter("pFechaI", fechaI);
		locQuery.setParameter("pFechaF", fechaF);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pEnabled", true);
		List<PagosMatricula> lista = locQuery.getResultList();
		return lista;
		
	}

	public List<PagosMatricula> getLista(Alumno alumno, Date fecha, String tipo){
		inicializar();
		List<PagosMatricula> lista;
		lista = new ArrayList<PagosMatricula>();		
		if(tipo == "I"){
			Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE "
					+ "p.alumno = :pAlumno AND p.fecha >= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosMatricula.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pAlumno", alumno);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		if(tipo == "F"){
			Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE "
					+ "p.alumno = :pAlumno AND p.fecha <= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosMatricula.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pAlumno", alumno);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		return lista;
	}
	
	public List<PagosMatricula> getLista(Date fechaI, Date fechaF) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE "
				+ " p.fecha >= :pFechaI AND p.fecha <= :pFechaF AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosMatricula.class);
		locQuery.setParameter("pFechaI", fechaI);
		locQuery.setParameter("pFechaF", fechaF);
		locQuery.setParameter("pEnabled", true);
		List<PagosMatricula> lista = locQuery.getResultList();
		return lista;		
	}
	
	public List<PagosMatricula> getLista(Date fecha, String tipo){
		inicializar();
		List<PagosMatricula> lista;
		lista = new ArrayList<PagosMatricula>();
		
		if(tipo == "I"){
			Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE "
					+ "p.fecha >= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosMatricula.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		if(tipo == "F"){
			Query locQuery = em.createQuery("SELECT p FROM PagosMatricula p WHERE "
					+ "p.fecha <= :pFecha AND p.enabled = :pEnabled ORDER BY p.fecha DESC", PagosMatricula.class);
			locQuery.setParameter("pFecha", fecha);
			locQuery.setParameter("pEnabled", true);
			lista = locQuery.getResultList();
			
		}
		return lista;
	}
}
