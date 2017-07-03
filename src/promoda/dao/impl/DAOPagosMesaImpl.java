package promoda.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOPagosMesa;
import promoda.model.Alumno;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;
import promoda.model.PagosMesa;

public class DAOPagosMesaImpl implements DAOPagosMesa, Serializable {

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

	public int insertar(PagosMesa pagosMesa) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(pagosMesa);
			tx.commit();
			return pagosMesa.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int update(PagosMesa pagosMesa) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE PagosMesa p SET p.alumno = :pAlumno, p.enabled = :pEnabled, "
					+ "p.fecha = :pFecha, p.fechaAlta = :pFechaAlta, p.fechaBaja = :pFechaBaja, p.mesa = :pMesa, "
					+ "p.mesasAlumno = :pMesasAlumno, p.monto = :pMonto, p.usuario1 = :pUsuario1, p.usuario2 = :pUsuario2 "
					+ "WHERE p.id = :pId", PagosMesa.class);
			locQuery.setParameter("pAlumno", pagosMesa.getAlumno());			
			locQuery.setParameter("pEnabled", pagosMesa.getEnabled());
			locQuery.setParameter("pFecha", pagosMesa.getFecha());
			locQuery.setParameter("pFechaAlta", pagosMesa.getFechaAlta());
			locQuery.setParameter("pFechaBaja", pagosMesa.getFechaBaja());			
			locQuery.setParameter("pMesa", pagosMesa.getMesa());
			locQuery.setParameter("pMesasAlumno", pagosMesa.getMesasAlumno());
			locQuery.setParameter("pMonto", pagosMesa.getMonto());
			locQuery.setParameter("pUsuario1", pagosMesa.getUsuario1());
			locQuery.setParameter("pUsuario2", pagosMesa.getUsuario2());
			locQuery.setParameter("pId", pagosMesa.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return pagosMesa.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public PagosMesa get(MesasAlumno mesasAlumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMesa p WHERE p.mesasAlumno = :pMesasAlumno", PagosMesa.class);
		locQuery.setParameter("pMesasAlumno", mesasAlumno);		
		PagosMesa pagosMesa = new PagosMesa();
		try {
			pagosMesa = (PagosMesa) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pagosMesa = new PagosMesa();
		}
		return pagosMesa;
	}
	
	public PagosMesa get(Mesa mesa, Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PagosMesa p WHERE p.alumno = :pAlumno AND p.enabled = :pEnabled "
				+ "AND p.mesa = :pMesa", PagosMesa.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pEnabled", true);	
		locQuery.setParameter("pMesa", mesa);	
		PagosMesa pagosMesa = new PagosMesa();
		try {
			pagosMesa = (PagosMesa) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pagosMesa = new PagosMesa();
		}
		return pagosMesa;
	}

}
