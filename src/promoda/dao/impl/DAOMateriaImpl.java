package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMateria;
import promoda.model.Curso;
import promoda.model.Materia;

public class DAOMateriaImpl implements Serializable, DAOMateria {

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

	public int insertar(Materia materia) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(materia);
			tx.commit();
			return materia.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Materia materia) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Materia m SET m.cantClases = :pCantClases, m.cargaHorarioSemanal = :pCargaHorario, "
					+ "m.curso = :pCurso, m.enabled = :pEnabled, m.fechaAlta = :pFechaAlta, m.fechaBaja = :pFechaBaja, "
					+ "m.fechaMod = :pFechaMod, m.nombre = :pNombre, m.profesore1 = :pProfesorSuplente, m.profesore2 = :pProfesorTitular, "
					+ "m.usuario1 = :pUsuario1, m.usuario2 = :pUsuario2, m.usuario3 = :pUsuario3 "
					+ "WHERE m.id = :pId", Materia.class);
			locQuery.setParameter("pCantClases", materia.getCantClases());
			locQuery.setParameter("pCargaHorario", materia.getCargaHorarioSemanal());
			locQuery.setParameter("pCurso", materia.getCurso());
			locQuery.setParameter("pEnabled", materia.getEnabled());
			locQuery.setParameter("pFechaAlta", materia.getFechaAlta());
			locQuery.setParameter("pFechaBaja", materia.getFechaBaja());
			locQuery.setParameter("pFechaMod", materia.getFechaMod());
			locQuery.setParameter("pNombre", materia.getNombre());
			locQuery.setParameter("pProfesorSuplente", materia.getProfesore1());
			locQuery.setParameter("pProfesorTitular", materia.getProfesore2());
			locQuery.setParameter("pUsuario1", materia.getUsuario1());
			locQuery.setParameter("pUsuario2", materia.getUsuario2());
			locQuery.setParameter("pUsuario3", materia.getUsuario3());
			locQuery.setParameter("pId", materia.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return materia.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Materia get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Materia m WHERE m.id = :pId", Materia.class);
		locQuery.setParameter("pId", id);
		Materia materia = new Materia();
		try {
			materia = (Materia) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			materia = new Materia();
		}
		return materia;
	}

	public List<Materia> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Materia m", Materia.class);
		List<Materia> lista = locQuery.getResultList();
		return lista;
	}

	public List<Materia> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Materia m WHERE m.enabled = :pEnabled", Materia.class);
		locQuery.setParameter("pEnabled", estado);
		List<Materia> lista = locQuery.getResultList();
		return lista;
	}

	public List<Materia> getLista(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Materia m WHERE m.curso = :pCurso", Materia.class);
		locQuery.setParameter("pCurso", curso);
		List<Materia> lista = locQuery.getResultList();
		return lista;
	}

	public List<Materia> getLista(boolean estado, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Materia m WHERE m.enabled = :pEnabled "
				+ "AND m.curso = :pCurso", Materia.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pCurso", curso);
		List<Materia> lista = locQuery.getResultList();
		return lista;
	}

}
