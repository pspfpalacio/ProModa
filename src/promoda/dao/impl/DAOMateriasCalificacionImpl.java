package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMateriasCalificacion;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.MateriasCalificacion;
import promoda.model.Matricula;

public class DAOMateriasCalificacionImpl implements Serializable,
		DAOMateriasCalificacion {

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

	public int insertar(MateriasCalificacion materiasCalificacion) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(materiasCalificacion);
			tx.commit();
			return materiasCalificacion.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int update(MateriasCalificacion materiasCalificacion) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE MateriasCalificacion m SET m.alumno = :pAlumno, m.calificacion = :pCalificacion, m.curso = :pCurso, "
					+ "m.enabled = :pEnabled, m.estado = :pEstado, m.fechaAlta = :pFechaAlta, m.fechaBaja = :pFechaBaja, m.fechaMod = :pFechaMod, "
					+ "m.matricula = :pMatricula, m.usuario1 = :pUsuarioAlta, m.usuario2 = :pUsuarioBaja, m.usuario3 = :pUsuarioMod "
					+ "WHERE m.id = :pId", MateriasCalificacion.class);
			locQuery.setParameter("pAlumno", materiasCalificacion.getAlumno());
			locQuery.setParameter("pCalificacion", materiasCalificacion.getCalificacion());
			locQuery.setParameter("pCurso", materiasCalificacion.getCurso());			
			locQuery.setParameter("pEnabled", materiasCalificacion.getEnabled());
			locQuery.setParameter("pEstado", materiasCalificacion.getEstado());
			locQuery.setParameter("pFechaAlta", materiasCalificacion.getFechaAlta());
			locQuery.setParameter("pFechaBaja", materiasCalificacion.getFechaBaja());
			locQuery.setParameter("pFechaMod", materiasCalificacion.getFechaMod());
			locQuery.setParameter("pMatricula", materiasCalificacion.getMatricula());
			locQuery.setParameter("pUsuarioAlta", materiasCalificacion.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", materiasCalificacion.getUsuario2());
			locQuery.setParameter("pUsuarioMod", materiasCalificacion.getUsuario3());
			locQuery.setParameter("pId", materiasCalificacion.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return materiasCalificacion.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public MateriasCalificacion get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasCalificacion m WHERE m.id = :pId", MateriasCalificacion.class);
		locQuery.setParameter("pId", id);
		MateriasCalificacion matriculaCalifiacion = new MateriasCalificacion();
		try {
			matriculaCalifiacion = (MateriasCalificacion) locQuery.getSingleResult();
		} catch(Exception e) {
			matriculaCalifiacion = new MateriasCalificacion();
		}
		return matriculaCalifiacion;
	}
	
	public MateriasCalificacion get(Alumno alumno, Curso curso, Materia materia, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasCalificacion m WHERE m.alumno = :pAlumno AND m.curso = :pCurso "
				+ "AND m.enabled = :pEnabled AND m.materia = :pMateria AND m.matricula = :pMatricula", MateriasCalificacion.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pMateria", materia);
		locQuery.setParameter("pMatricula", matricula);
		MateriasCalificacion matriculaCalifiacion = new MateriasCalificacion();
		try {
			matriculaCalifiacion = (MateriasCalificacion) locQuery.getSingleResult();
		} catch(Exception e) {
			matriculaCalifiacion = new MateriasCalificacion();
		}
		return matriculaCalifiacion;
	}

	public List<MateriasCalificacion> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasCalificacion m WHERE m.enabled = :pEnabled", MateriasCalificacion.class);
		locQuery.setParameter("pEnabled", true);
		List<MateriasCalificacion> lista = locQuery.getResultList();
		return lista;
	}

	public List<MateriasCalificacion> getLista(Curso curso,
			Matricula matricula, Materia materia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasCalificacion m WHERE m.enabled = :pEnabled "
				+ "AND m.curso = :pCurso AND m.matricula = :pMatricula AND m.materia = :pMateria", MateriasCalificacion.class);		
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pMateria", materia);
		List<MateriasCalificacion> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<MateriasCalificacion> getListaOrberByAlumno(Curso curso,
			Matricula matricula, Materia materia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MateriasCalificacion m WHERE m.enabled = :pEnabled "
				+ "AND m.curso = :pCurso AND m.matricula = :pMatricula AND m.materia = :pMateria ORDER BY m.alumno.nombreCompleto DESC", MateriasCalificacion.class);		
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pMateria", materia);
		List<MateriasCalificacion> lista = locQuery.getResultList();
		return lista;
	}

}
