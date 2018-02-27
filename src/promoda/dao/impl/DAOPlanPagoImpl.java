package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOPlanPago;
import promoda.model.Inscripcione;
import promoda.model.MatriculaAlumno;
import promoda.model.PlanPago;

public class DAOPlanPagoImpl implements Serializable, DAOPlanPago {

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

	public int insertar(PlanPago planPago) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(planPago);
			tx.commit();
			return planPago.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(PlanPago planPago) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE PlanPago p SET p.cantCuotas = :pCantCuotas, p.descuentoCurso = :pDescuentoCurso, "
					+ "p.descuentoMatricula = :pDescuentoMatricula, p.inscripcione = :pInscripcion, "
					+ "p.montoCuota = :pMontoCuota, p.montoMatricula = :pMontoMatricula, p.primerVencimiento = :pPrimerVencimiento  "
					+ "WHERE p.id = :pId", MatriculaAlumno.class);
			locQuery.setParameter("pCantCuotas", planPago.getCantCuotas());
			locQuery.setParameter("pDescuentoCurso", planPago.getDescuentoCurso());
			locQuery.setParameter("pDescuentoMatricula", planPago.getDescuentoMatricula());
			locQuery.setParameter("pInscripcion", planPago.getInscripcione());
			locQuery.setParameter("pMontoCuota", planPago.getMontoCuota());
			locQuery.setParameter("pMontoMatricula", planPago.getMontoMatricula());
			locQuery.setParameter("pPrimerVencimiento", planPago.getPrimerVencimiento());
			locQuery.setParameter("pId", planPago.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return planPago.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int delete(PlanPago planPago) {
		try {
			inicializar();
			Query locQuery = em.createQuery("DELETE FROM PlanPago p WHERE p.id = :pId", PlanPago.class);
			locQuery.setParameter("pId", planPago.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;			
		}
	}

	public PlanPago get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PlanPago p WHERE p.id = :pId", PlanPago.class);
		locQuery.setParameter("pId", id);
		PlanPago planPago = new PlanPago();
		try {
			planPago = (PlanPago) locQuery.getSingleResult();
		} catch (Exception e) {
			planPago = new PlanPago();
		}
		return planPago;
	}

	public PlanPago get(Inscripcione inscripcione) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PlanPago p WHERE p.inscripcione = :pInscripcion", PlanPago.class);
		locQuery.setParameter("pInscripcion", inscripcione);
		PlanPago planPago = new PlanPago();
		try {
			planPago = (PlanPago) locQuery.getSingleResult();
		} catch (Exception e) {
			planPago = new PlanPago();
		}
		return planPago;
	}

	public List<PlanPago> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PlanPago p", PlanPago.class);
		List<PlanPago> lista = locQuery.getResultList();
		return lista;
	}

	public List<PlanPago> getLista(Inscripcione inscripcione) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM PlanPago p WHERE p.inscripcione = :pInscripcion", PlanPago.class);
		locQuery.setParameter("pInscripcion", inscripcione);
		List<PlanPago> lista = locQuery.getResultList();
		return lista;
	}

//	public List<PlanPago> getLista(Matricula matricula) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT p FROM PlanPago p WHERE p.matricula = :pMatricula", PlanPago.class);
//		locQuery.setParameter("pMatricula", matricula);
//		List<PlanPago> lista = locQuery.getResultList();
//		return lista;
//	}

}
