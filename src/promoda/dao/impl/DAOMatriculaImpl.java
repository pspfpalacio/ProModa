package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMatricula;
import promoda.model.Curso;
import promoda.model.Matricula;

public class DAOMatriculaImpl implements Serializable, DAOMatricula {

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

	public int insertar(Matricula matricula) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(matricula);
			tx.commit();
			return matricula.getId();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	public int update(Matricula matricula) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Matricula m SET m.costo = :pCosto, m.costoCurso = :pCostoCurso, m.curso = :pCurso, "
					+ "m.descripcion = :pDescripcion, m.enabled = :pEnabled, m.fechaAlta = :pFechaAlta, m.fechaBaja = :pFechaBaja, "
					+ "m.fechaFinalizacion = :pFechaFinalizacion, m.fechaFinCursado = :pFechaFinCursado, m.fechaInicio = :pFechaInicio, "
					+ "m.fechaMod = :pFechaMod, m.usuario1 = :pUsuario1, m.usuario2 = :pUsuario2, m.usuario3 = :pUsuario3 "
					+ "WHERE m.id = :pId", Matricula.class);
			locQuery.setParameter("pCosto", matricula.getCosto());
			locQuery.setParameter("pCostoCurso", matricula.getCostoCurso());
			locQuery.setParameter("pCurso", matricula.getCurso());
			locQuery.setParameter("pDescripcion", matricula.getDescripcion());
			locQuery.setParameter("pEnabled", matricula.getEnabled());
			locQuery.setParameter("pFechaAlta", matricula.getFechaAlta());
			locQuery.setParameter("pFechaBaja", matricula.getFechaBaja());
			locQuery.setParameter("pFechaFinalizacion", matricula.getFechaFinalizacion());
			locQuery.setParameter("pFechaFinCursado", matricula.getFechaFinCursado());
			locQuery.setParameter("pFechaInicio", matricula.getFechaInicio());
			locQuery.setParameter("pFechaMod", matricula.getFechaMod());
			locQuery.setParameter("pUsuario1", matricula.getUsuario1());
			locQuery.setParameter("pUsuario2", matricula.getUsuario2());
			locQuery.setParameter("pUsuario3", matricula.getUsuario3());
			locQuery.setParameter("pId", matricula.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return matricula.getId();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	public Matricula get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Matricula m WHERE m.id = :pId", Matricula.class);
		locQuery.setParameter("pId", id);
		Matricula matricula = new Matricula();
		try {
			matricula = (Matricula) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			matricula = new Matricula();
		}
		return matricula;
	}

	public List<Matricula> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Matricula m", Matricula.class);
		List<Matricula> lista = locQuery.getResultList();
		return lista;
	}

	public List<Matricula> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Matricula m WHERE m.enabled = :pEnabled", Matricula.class);
		locQuery.setParameter("pEnabled", estado);
		List<Matricula> lista = locQuery.getResultList();
		return lista;
	}

	public List<Matricula> getLista(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Matricula m WHERE m.curso = :pCurso", Matricula.class);
		locQuery.setParameter("pCurso", curso);
		List<Matricula> lista = locQuery.getResultList();
		return lista;
	}

	public List<Matricula> getLista(boolean estado, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Matricula m WHERE m.enabled = :pEnabled "
				+ "AND m.curso = :pCurso", Matricula.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pCurso", curso);
		List<Matricula> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Matricula> getListaDesc(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Matricula m WHERE m.curso = :pCurso "
				+ "ORDER BY m.id DESC", Matricula.class);
		locQuery.setParameter("pCurso", curso);
		List<Matricula> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Matricula> getListaFechaDesc(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Matricula m WHERE m.curso = :pCurso "
				+ "ORDER BY m.fechaFinalizacion DESC", Matricula.class);
		locQuery.setParameter("pCurso", curso);
		List<Matricula> lista = locQuery.getResultList();
		return lista;
	}

}
