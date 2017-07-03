package promoda.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOCuota;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.Curso;
import promoda.model.Matricula;

public class DAOCuotaImpl implements Serializable, DAOCuota {

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

	public int insertar(Cuota cuota) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuota);
			tx.commit();
			return cuota.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Cuota cuota) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Cuota c SET c.alumno = :pAlumno, c.curso = :pCurso, "
					+ "c.detalle = :pDetalle, c.enabled = :pEnabled, c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, "
					+ "c.fechaMod = :pFechaMod, c.fechaPago = :pFechaPago, c.fechaVencimiento = :pFechaVencimiento, "
					+ "c.matricula = :pMatricula, c.monto = :pMonto, c.montoPago = :pMontoPago, c.paga = :pPaga, "
					+ "c.porcentajePv = :pPorcentajePV, c.porcentajeSv = :pPorcentajeSV, c.segundoVencimiento = :pSegundoVencimiento, "
					+ "c.usuario1 = :pUsuario1, c.usuario2 = :pUsuario2, c.usuario3 = :pUsuario3 "
					+ "WHERE c.id = :pId", Cuota.class);
			locQuery.setParameter("pAlumno", cuota.getAlumno());
			locQuery.setParameter("pCurso", cuota.getCurso());
			locQuery.setParameter("pDetalle", cuota.getDetalle());			
			locQuery.setParameter("pEnabled", cuota.getEnabled());
			locQuery.setParameter("pFechaAlta", cuota.getFechaAlta());
			locQuery.setParameter("pFechaBaja", cuota.getFechaBaja());
			locQuery.setParameter("pFechaMod", cuota.getFechaMod());
			locQuery.setParameter("pFechaPago", cuota.getFechaPago());
			locQuery.setParameter("pFechaVencimiento", cuota.getFechaVencimiento());
			locQuery.setParameter("pMatricula", cuota.getMatricula());
			locQuery.setParameter("pMonto", cuota.getMonto());
			locQuery.setParameter("pMontoPago", cuota.getMontoPago());
			locQuery.setParameter("pPaga", cuota.getPaga());
			locQuery.setParameter("pPorcentajePV", cuota.getPorcentajePv());
			locQuery.setParameter("pPorcentajeSV", cuota.getPorcentajeSv());
			locQuery.setParameter("pSegundoVencimiento", cuota.getSegundoVencimiento());
			locQuery.setParameter("pUsuario1", cuota.getUsuario1());
			locQuery.setParameter("pUsuario2", cuota.getUsuario2());
			locQuery.setParameter("pUsuario3", cuota.getUsuario3());
			locQuery.setParameter("pId", cuota.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuota.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Cuota get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.id = :pId", Cuota.class);
		locQuery.setParameter("pId", id);
		Cuota cuota = new Cuota();
		try {
			cuota = (Cuota) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			cuota = new Cuota();
		}
		return cuota;
	}
	
	public List<Cuota> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c", Cuota.class);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.enabled = :pEnabled", Cuota.class);
		locQuery.setParameter("pEnabled", estado);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Cuota> getLista(boolean estado, boolean paga) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.enabled = :pEnabled AND c.paga = :pPaga", Cuota.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pPaga", paga);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.alumno = :pAlumno", Cuota.class);
		locQuery.setParameter("pAlumno", alumno);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.matricula = :pMatricula", Cuota.class);
		locQuery.setParameter("pMatricula", matricula);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(boolean estado, Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.enabled = :pEnabled "
				+ "AND c.alumno = :pAlumno", Cuota.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pAlumno", alumno);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(boolean estado, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.enabled = :pEnabled "
				+ "AND c.matricula = :pMatricula", Cuota.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pMatricula", matricula);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(Alumno alumno, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.alumno = :pAlumno "
				+ "AND c.matricula = :pMatricula", Cuota.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pMatricula", matricula);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cuota> getLista(boolean estado, Alumno alumno,
			Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.alumno = :pAlumno "
				+ "AND c.enabled = :pEnabled AND c.matricula = :pMatricula", Cuota.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pMatricula", matricula);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Cuota> getLista(Alumno alumno, Matricula matricula, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.alumno = :pAlumno AND c.curso = :pCurso "
				+ "AND c.matricula = :pMatricula AND c.enabled = :pEnabled", Cuota.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pEnabled", true);
		List<Cuota> lista = locQuery.getResultList();
		return lista;		
	}
	
	public List<Cuota> getLista(Alumno alumno, Matricula matricula, Curso curso, boolean paga) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.alumno = :pAlumno AND c.curso = :pCurso "
				+ "AND c.matricula = :pMatricula AND c.paga = :pPaga AND c.enabled = :pEnabled", Cuota.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pPaga", paga);
		locQuery.setParameter("pEnabled", true);
		List<Cuota> lista = locQuery.getResultList();
		return lista;		
	}
	
	public List<Cuota> getLista(Curso curso, Matricula matricula, boolean paga) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.curso = :pCurso "
				+ "AND c.matricula = :pMatricula AND c.paga = :pPaga AND c.enabled = :pEnabled", Cuota.class);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pPaga", paga);
		locQuery.setParameter("pEnabled", true);
		List<Cuota> lista = locQuery.getResultList();
		return lista;		
	}
	
	public List<Cuota> getListaPorVencer(Date fecha) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cuota c WHERE c.fechaVencimiento <= :pFecha "
				+ "AND c.paga = :pPaga AND c.enabled = :pEstado ORDER BY c.fechaVencimiento DESC", Cuota.class);
		locQuery.setParameter("pFecha", fecha);
		locQuery.setParameter("pPaga", false);
		locQuery.setParameter("pEstado", true);
		List<Cuota> lista = locQuery.getResultList();
		return lista;
	}

}
