package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOCuotaImpaga;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.CuotaImpaga;
import promoda.model.Curso;
import promoda.model.Matricula;

public class DAOCuotaImpagaImpl implements DAOCuotaImpaga, Serializable {

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

	public int insertar(CuotaImpaga cuotaImpaga) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cuotaImpaga);
			tx.commit();
			return cuotaImpaga.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(CuotaImpaga cuotaImpaga) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE CuotaImpaga c SET c.alumno = :pAlumno, c.cuota = :pCuota, "
					+ "c.curso = :pCurso, c.detalle = :pDetalle, c.fechaAlta = :pFechaAlta, "
					+ "c.fechaVencimiento = :pFechaVencimiento, c.matricula = :pMatricula, c.matriculaAlumno = :pMatriculaAlumno, "
					+ "c.monto = :pMonto, c.segundoVencimiento = :pSegundoVencimiento, c.usuario = :pUsuario "
					+ "WHERE c.id = :pId", CuotaImpaga.class);
			locQuery.setParameter("pAlumno", cuotaImpaga.getAlumno());
			locQuery.setParameter("pCuota", cuotaImpaga.getCuota());
			locQuery.setParameter("pCurso", cuotaImpaga.getCurso());
			locQuery.setParameter("pDetalle", cuotaImpaga.getDetalle());
			locQuery.setParameter("pFechaAlta", cuotaImpaga.getFechaAlta());
			locQuery.setParameter("pFechaVencimiento", cuotaImpaga.getFechaVencimiento());
			locQuery.setParameter("pMatricula", cuotaImpaga.getMatricula());
			locQuery.setParameter("pMatriculaAlumno", cuotaImpaga.getMatriculaAlumno());
			locQuery.setParameter("pMonto", cuotaImpaga.getMonto());
			locQuery.setParameter("pSegundoVencimiento", cuotaImpaga.getSegundoVencimiento());
			locQuery.setParameter("pUsuario", cuotaImpaga.getUsuario());
			locQuery.setParameter("pId", cuotaImpaga.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cuotaImpaga.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int delete(CuotaImpaga cuotaImpaga) {
		try {
			inicializar();
			Query locQuery = em.createQuery("DELETE FROM CuotaImpaga c WHERE c.id = :pId", CuotaImpaga.class);
			locQuery.setParameter("pId", cuotaImpaga.getId());
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

	public CuotaImpaga get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.id = :pId", CuotaImpaga.class);
		locQuery.setParameter("pId", id);
		CuotaImpaga cuotaImpaga = new CuotaImpaga();
		try {
			cuotaImpaga = (CuotaImpaga) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			cuotaImpaga = new CuotaImpaga();
		}
		return cuotaImpaga;
	}

	public CuotaImpaga get(Cuota cuota) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.cuota = :pCuota", CuotaImpaga.class);
		locQuery.setParameter("pCuota", cuota);
		CuotaImpaga cuotaImpaga = new CuotaImpaga();
		try {
			cuotaImpaga = (CuotaImpaga) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			cuotaImpaga = new CuotaImpaga();
		}
		return cuotaImpaga;
	}

	public List<CuotaImpaga> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c", CuotaImpaga.class);
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotaImpaga> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.alumno = :pAlumno", CuotaImpaga.class);
		locQuery.setParameter("pAlumno", alumno);
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotaImpaga> getLista(Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.matricula = :pMatricula", CuotaImpaga.class);
		locQuery.setParameter("pMatricula", matricula);
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotaImpaga> getLista(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.curso = :pCurso", CuotaImpaga.class);
		locQuery.setParameter("pCurso", curso);
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotaImpaga> getLista(Alumno alumno, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.alumno = :pAlumno AND c.matricula = :pMatricula", CuotaImpaga.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pMatricula", matricula);
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotaImpaga> getLista(Curso curso, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.curso = :pCurso AND c.matricula = :pMatricula", CuotaImpaga.class);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotaImpaga> getLista(Alumno alumno, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE c.alumno = :pAlumno AND c.curso = :pCurso", CuotaImpaga.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);		
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<CuotaImpaga> getLista(Alumno alumno, Curso curso,
			Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM CuotaImpaga c WHERE  c.alumno = :pAlumno AND c.curso = :pCurso "
				+ "AND c.matricula = :pMatricula", CuotaImpaga.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		List<CuotaImpaga> lista = locQuery.getResultList();
		return lista;
	}

}
