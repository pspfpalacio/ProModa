package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMatriculaImpaga;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.MatriculaImpaga;

public class DAOMatriculaImpagaImpl implements Serializable, DAOMatriculaImpaga {

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

	public int insertar(MatriculaImpaga matriculaImpaga) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(matriculaImpaga);
			tx.commit();
			return matriculaImpaga.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(MatriculaImpaga matriculaImpaga) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE MatriculaImpaga m SET m.alumno = :pAlumno, m.curso = :pCurso, m.fechaAlta = :pFechaAlta, "
					+ "m.matricula = :pMatricula, m.matriculaAlumno = :pMatriculaAlumno, m.monto = :pMonto, m.usuario = :pUsuario "
					+ "WHERE m.id = :pId", MatriculaImpaga.class);
			locQuery.setParameter("pAlumno", matriculaImpaga.getAlumno());
			locQuery.setParameter("pCurso", matriculaImpaga.getCurso());
			locQuery.setParameter("pFechaAlta", matriculaImpaga.getFechaAlta());
			locQuery.setParameter("pMatricula", matriculaImpaga.getMatricula());
			locQuery.setParameter("pMatriculaAlumno", matriculaImpaga.getMatriculaAlumno());
			locQuery.setParameter("pMonto", matriculaImpaga.getMonto());
			locQuery.setParameter("pUsuario", matriculaImpaga.getUsuario());
			locQuery.setParameter("pId", matriculaImpaga.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return matriculaImpaga.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int delete(MatriculaImpaga matriculaImpaga) {
		try {
			inicializar();
			Query locQuery = em.createQuery("DELETE FROM MatriculaImpaga m WHERE m.id = :pId", MatriculaImpaga.class);
			locQuery.setParameter("pId", matriculaImpaga.getId());
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

	public MatriculaImpaga get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaImpaga m WHERE m.id = :pId", MatriculaImpaga.class);
		locQuery.setParameter("pId", id);
		MatriculaImpaga matriculaImpaga = new MatriculaImpaga();
		try {
			matriculaImpaga = (MatriculaImpaga) locQuery.getSingleResult();
		} catch(Exception e) {
			matriculaImpaga = new MatriculaImpaga();
		}
		return matriculaImpaga;
	}

	public MatriculaImpaga get(MatriculaAlumno matriculaAlumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaImpaga m WHERE m.matriculaAlumno = :pMatriculaAlumno", MatriculaImpaga.class);
		locQuery.setParameter("pMatriculaAlumno", matriculaAlumno);
		MatriculaImpaga matriculaImpaga = new MatriculaImpaga();
		try {
			matriculaImpaga = (MatriculaImpaga) locQuery.getSingleResult();
		} catch(Exception e) {
			matriculaImpaga = new MatriculaImpaga();
		}
		return matriculaImpaga;
	}

	public MatriculaImpaga get(Alumno alumno, Curso curso, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaImpaga m WHERE m.alumno = :pAlumno AND m.curso = :pCurso "
				+ "AND m.matricula = :pMatricula", MatriculaImpaga.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		MatriculaImpaga matriculaImpaga = new MatriculaImpaga();
		try {
			matriculaImpaga = (MatriculaImpaga) locQuery.getSingleResult();
		} catch(Exception e) {
			matriculaImpaga = new MatriculaImpaga();
		}
		return matriculaImpaga;
	}

	public List<MatriculaImpaga> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaImpaga m", MatriculaImpaga.class);
		List<MatriculaImpaga> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<MatriculaImpaga> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaImpaga m WHERE m.alumno = :pAlumno", MatriculaImpaga.class);
		locQuery.setParameter("pAlumno", alumno);
		List<MatriculaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<MatriculaImpaga> getLista(Alumno alumno, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaImpaga m WHERE m.alumno = :pAlumno AND m.curso = :pCurso", MatriculaImpaga.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		List<MatriculaImpaga> lista = locQuery.getResultList();
		return lista;
	}

	public List<MatriculaImpaga> getLista(Curso curso, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaImpaga m WHERE m.curso = :pCurso AND m.matricula = :pMatricula", MatriculaImpaga.class);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pCurso", curso);
		List<MatriculaImpaga> lista = locQuery.getResultList();
		return lista;
	}

}
