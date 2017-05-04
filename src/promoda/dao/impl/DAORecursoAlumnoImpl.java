package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAORecursoAlumno;
import promoda.model.Alumno;
import promoda.model.Recurso;
import promoda.model.RecursoAlumno;

public class DAORecursoAlumnoImpl implements Serializable, DAORecursoAlumno {

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

	public int insertar(RecursoAlumno recursoAlumno) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(recursoAlumno);
			tx.commit();
			return recursoAlumno.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(RecursoAlumno recursoAlumno) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE RecursoAlumno r SET r.alumno = :pAlumno, r.calificacion = :pCalificacion, "
					+ "r.comentarios = :pComentarios, r.fechaAlta = :pFechaAlta, r.recurso = :pRecurso, r.usuario = :pUsuario "
					+ "WHERE r.id = :pId", RecursoAlumno.class);			
			locQuery.setParameter("pAlumno", recursoAlumno.getAlumno());
			locQuery.setParameter("pCalificacion", recursoAlumno.getCalificacion());
			locQuery.setParameter("pComentarios", recursoAlumno.getComentarios());
			locQuery.setParameter("pFechaAlta", recursoAlumno.getFechaAlta());
			locQuery.setParameter("pRecurso", recursoAlumno.getRecurso());
			locQuery.setParameter("pUsuario", recursoAlumno.getUsuario());
			locQuery.setParameter("pId", recursoAlumno.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return recursoAlumno.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public RecursoAlumno get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RecursoAlumno r WHERE r.id = :pId", RecursoAlumno.class);
		locQuery.setParameter("pId", id);
		RecursoAlumno recursoAlumno = new RecursoAlumno();
		try {
			recursoAlumno = (RecursoAlumno) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			recursoAlumno = new RecursoAlumno();
		}
		return recursoAlumno;
	}

	public RecursoAlumno get(Recurso recurso, Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RecursoAlumno r WHERE r.recurso = :pRecurso "
				+ "AND r.alumno = :pAlumno", RecursoAlumno.class);
		locQuery.setParameter("pRecurso", recurso);
		locQuery.setParameter("pAlumno", alumno);
		RecursoAlumno recursoAlumno = new RecursoAlumno();
		try {
			recursoAlumno = (RecursoAlumno) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			recursoAlumno = new RecursoAlumno();
		}
		return recursoAlumno;
	}

	public List<RecursoAlumno> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RecursoAlumno r", RecursoAlumno.class);
		List<RecursoAlumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<RecursoAlumno> getLista(Recurso recurso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RecursoAlumno r WHERE r.recurso = :pRecurso", RecursoAlumno.class);
		locQuery.setParameter("pRecurso", recurso);
		List<RecursoAlumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<RecursoAlumno> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RecursoAlumno r WHERE r.alumno = :pAlumno", RecursoAlumno.class);
		locQuery.setParameter("pAlumno", alumno);
		List<RecursoAlumno> lista = locQuery.getResultList();
		return lista;
	}

}
