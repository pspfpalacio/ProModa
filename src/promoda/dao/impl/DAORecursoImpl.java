package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAORecurso;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Recurso;

public class DAORecursoImpl implements DAORecurso, Serializable {

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

	public int insertar(Recurso recurso) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(recurso);
			tx.commit();
			return recurso.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Recurso recurso) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Recurso r SET r.curso = :pCurso, r.descripcion = :pDescripcion, "
					+ "r.estado = :pEstado, r.fechaEntrega = :pFechaEntrega, r.fechaAlta = :pFechaAlta, r.fechaBaja = :pFechaBaja, "
					+ "r.fechaMod = :pFechaMod, r.materia = :pMateria, r.usuario1 = :pUsuarioAlta, "
					+ "r.usuario2 = :pUsuarioBaja, r.usuario3 = :pUsuarioMod, r.vigente = :pVigente "
					+ "WHERE r.id = :pId", Recurso.class);			
			locQuery.setParameter("pCurso", recurso.getCurso());
			locQuery.setParameter("pDescripcion", recurso.getDescripcion());
			locQuery.setParameter("pEstado", recurso.getEstado());
			locQuery.setParameter("pFechaEntrega", recurso.getFechaEntrega());
			locQuery.setParameter("pFechaAlta", recurso.getFechaAlta());
			locQuery.setParameter("pFechaBaja", recurso.getFechaBaja());
			locQuery.setParameter("pFechaMod", recurso.getFechaMod());
			locQuery.setParameter("pMateria", recurso.getMateria());
			locQuery.setParameter("pUsuarioAlta", recurso.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", recurso.getUsuario2());
			locQuery.setParameter("pUsuarioMod", recurso.getUsuario3());
			locQuery.setParameter("pVigente", recurso.getVigente());
			locQuery.setParameter("pId", recurso.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return recurso.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Recurso get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Recurso r WHERE r.id = :pId", Recurso.class);
		locQuery.setParameter("pId", id);
		Recurso recurso = new Recurso();
		try {
			recurso = (Recurso) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			recurso = new Recurso();
		}
		return recurso;
	}

	public List<Recurso> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Recurso r", Recurso.class);
		List<Recurso> lista = locQuery.getResultList();
		return lista;
	}

	public List<Recurso> getLista(Materia materia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Recurso r WHERE r.estado = :pEstado "
				+ "AND r.materia = :pMateria", Recurso.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pMateria", materia);
		List<Recurso> lista = locQuery.getResultList();
		return lista;
	}

	public List<Recurso> getLista(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM Recurso r WHERE r.estado = :pEstado "
				+ "AND r.curso = :pCurso", Recurso.class);
		locQuery.setParameter("pEstado", true);
		locQuery.setParameter("pCurso", curso);
		List<Recurso> lista = locQuery.getResultList();
		return lista;
	}

//	public List<Recurso> getLista(Matricula matricula) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT r FROM Recurso r WHERE r.estado = :pEstado "
//				+ "AND r.matricula = :pMatricula", Recurso.class);
//		locQuery.setParameter("pEstado", true);
//		locQuery.setParameter("pMatricula", matricula);
//		List<Recurso> lista = locQuery.getResultList();
//		return lista;
//	}

//	public List<Recurso> getLista(Materia materia, Matricula matricula) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT r FROM Recurso r WHERE r.estado = :pEstado "
//				+ "AND r.materia = :pMateria AND r.matricula = :pMatricula", Recurso.class);
//		locQuery.setParameter("pEstado", true);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pMatricula", matricula);
//		List<Recurso> lista = locQuery.getResultList();
//		return lista;
//	}
	
//	public List<Recurso> getListaHistoricos(Materia materia, Matricula matricula) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT r FROM Recurso r WHERE r.estado = :pEstado "
//				+ "AND r.materia = :pMateria AND r.matricula <> :pMatricula", Recurso.class);
//		locQuery.setParameter("pEstado", true);
//		locQuery.setParameter("pMateria", materia);
//		locQuery.setParameter("pMatricula", matricula);
//		List<Recurso> lista = locQuery.getResultList();
//		return lista;
//	}


}
