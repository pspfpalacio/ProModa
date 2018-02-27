package promoda.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOAsistencia;
import promoda.model.Alumno;
import promoda.model.Asistencia;
import promoda.model.Curso;
import promoda.model.Materia;

public class DAOAsistenciaImpl implements Serializable, DAOAsistencia {

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

	public int insertar(Asistencia asistencia) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(asistencia);
			tx.commit();
			return asistencia.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public int update(Asistencia asistencia) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Asistencia a SET a.alumno = :pAlumno, a.curso = :pCurso, a.fechaAlta = :pFechaAlta, "
					+ "a.materia = :pMateria, a.nombreClase = :pNombreClase, a.nroClase = :pNroClase, "
					+ "a.presente = :pPresente, a.usuario = :pUsuario "
					+ "WHERE a.id = :pId", Asistencia.class);
			locQuery.setParameter("pAlumno", asistencia.getAlumno());
			locQuery.setParameter("pCurso", asistencia.getCurso());
			locQuery.setParameter("pFechaAlta", asistencia.getFechaAlta());
			locQuery.setParameter("pMateria", asistencia.getMateria());
			locQuery.setParameter("pNombreClase", asistencia.getNombreClase());
			locQuery.setParameter("pNroClase", asistencia.getNroClase());
			locQuery.setParameter("pPresente", asistencia.getPresente());
			locQuery.setParameter("pUsuario", asistencia.getUsuario());
			locQuery.setParameter("pId", asistencia.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return asistencia.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public Asistencia get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.id = :pId", Asistencia.class);
		locQuery.setParameter("pId", id);
		Asistencia asistencia = new Asistencia();
		try {
			asistencia = (Asistencia) locQuery.getSingleResult();
		} catch (Exception e) {
			asistencia = new Asistencia();
		}
		return asistencia;
	}
	
//	public Asistencia get(Curso curso, Matricula matricula,
//			Materia materia, Alumno alumno, int nroClase) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.curso = :pCurso AND a.matricula = :pMatricula "
//				+ "AND a.materia = :pMateria AND a.alumno = :pAlumno AND a.nroClase = :pNroClase ORDER BY a.alumno", Asistencia.class);
//		locQuery.setParameter("pCurso", curso);
//		locQuery.setParameter("pMatricula", matricula);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pAlumno", alumno);
//		locQuery.setParameter("pNroClase", nroClase);
//		Asistencia asistencia = new Asistencia();
//		try {
//			asistencia = (Asistencia) locQuery.getSingleResult();
//		} catch (Exception e) {
//			asistencia = new Asistencia();
//		}
//		return asistencia;
//	}
	
//	public Asistencia get(Curso curso, Matricula matricula,
//			Materia materia, Alumno alumno, int nroClase, Date fechaInicio, Date fechaFin) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.curso = :pCurso AND a.matricula = :pMatricula "
//				+ "AND a.materia = :pMateria AND a.alumno = :pAlumno AND a.nroClase = :pNroClase AND a.fechaAlta BETWEEN :pInicio AND :pFin "
//				+ "ORDER BY a.alumno", Asistencia.class);
//		locQuery.setParameter("pCurso", curso);
//		locQuery.setParameter("pMatricula", matricula);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pAlumno", alumno);
//		locQuery.setParameter("pNroClase", nroClase);
//		locQuery.setParameter("pInicio", fechaInicio);
//		locQuery.setParameter("pFin", fechaFin);
//		Asistencia asistencia = new Asistencia();
//		try {
//			asistencia = (Asistencia) locQuery.getSingleResult();
//		} catch (Exception e) {
//			asistencia = new Asistencia();
//		}
//		return asistencia;
//	}

	public List<Asistencia> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Asistencia a", Asistencia.class);
		List<Asistencia> lista = locQuery.getResultList();
		return lista;
	}

	public List<Asistencia> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.alumno = :pAlumno", Asistencia.class);
		locQuery.setParameter("pAlumno", alumno);
		List<Asistencia> lista = locQuery.getResultList();
		return lista;
	}

	public List<Asistencia> getLista(Materia materia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.materia = :pMateria", Asistencia.class);
		locQuery.setParameter("pMateria", materia);
		List<Asistencia> lista = locQuery.getResultList();
		return lista;
	}

	public List<Asistencia> getLista(Alumno alumno, Materia materia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.alumno = :pAlumno "
				+ "AND a.materia = :pMateria", Asistencia.class);
		locQuery.setParameter("pMateria", materia);
		locQuery.setParameter("pAlumno", alumno);
		List<Asistencia> lista = locQuery.getResultList();
		return lista;
	}
	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula,
//			Materia materia) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.curso = :pCurso AND a.matricula = :pMatricula "
//				+ "AND a.materia = :pMateria ORDER BY a.alumno.nombreCompleto", Asistencia.class);
//		locQuery.setParameter("pCurso", curso);
//		locQuery.setParameter("pMatricula", matricula);
//		locQuery.setParameter("pMateria", materia);
//		List<Asistencia> lista = locQuery.getResultList();
//		return lista;
//	}

//	public List<Asistencia> getLista(Curso curso, Matricula matricula,
//			Materia materia, int nroClase) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.curso = :pCurso AND a.matricula = :pMatricula "
//				+ "AND a.materia = :pMateria AND a.nroClase = :pNroClase ORDER BY a.alumno", Asistencia.class);
//		locQuery.setParameter("pCurso", curso);
//		locQuery.setParameter("pMatricula", matricula);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pNroClase", nroClase);
//		List<Asistencia> lista = locQuery.getResultList();
//		return lista;
//	}	
	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula,
//			Materia materia, Date fechaInicio, Date fechaFin) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.curso = :pCurso AND a.matricula = :pMatricula "
//				+ "AND a.materia = :pMateria AND a.fechaAlta BETWEEN :pInicio AND :pFin ORDER BY a.alumno.nombreCompleto", Asistencia.class);
//		locQuery.setParameter("pCurso", curso);
//		locQuery.setParameter("pMatricula", matricula);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pInicio", fechaInicio);
//		locQuery.setParameter("pFin", fechaFin);
//		List<Asistencia> lista = locQuery.getResultList();
//		return lista;
//	}
	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula,
//			Materia materia, int nroClase, Date fechaInicio, Date fechaFin) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.curso = :pCurso AND a.matricula = :pMatricula "
//				+ "AND a.materia = :pMateria AND a.nroClase = :pNroClase AND a.fechaAlta BETWEEN :pInicio AND :pFin "
//				+ "ORDER BY a.alumno.nombreCompleto", Asistencia.class);
//		locQuery.setParameter("pCurso", curso);
//		locQuery.setParameter("pMatricula", matricula);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pNroClase", nroClase);
//		locQuery.setParameter("pInicio", fechaInicio);
//		locQuery.setParameter("pFin", fechaFin);
//		List<Asistencia> lista = locQuery.getResultList();
//		return lista;
//	}
	
//	public List<Asistencia> getLista(Curso curso, Matricula matricula,
//			Materia materia, Alumno alumno) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT a FROM Asistencia a WHERE a.curso = :pCurso AND a.matricula = :pMatricula "
//				+ "AND a.materia = :pMateria AND a.alumno = :pAlumno", Asistencia.class);
//		locQuery.setParameter("pCurso", curso);
//		locQuery.setParameter("pMatricula", matricula);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pAlumno", alumno);
//		List<Asistencia> lista = locQuery.getResultList();
//		return lista;
//	}

}
