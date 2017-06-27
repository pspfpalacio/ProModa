package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMesa;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.MateriasCalificacion;
import promoda.model.Matricula;
import promoda.model.Mesa;

public class DAOMesaImpl implements Serializable, DAOMesa {

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

	public int insertar(Mesa mesa) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(mesa);
			tx.commit();
			return mesa.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int update(Mesa mesa) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Mesa m SET m.costo = :pCosto, m.curso = :pCurso, m.enabled = :pEnabled, "
					+ "m.fechaAlta = :pFechaAlta, m.fechaBaja = :pFechaBaja, m.fechaFin = :pFechaFin, m.fechaHoraMesa = :pFechaHoraMesa, "
					+ "m.fechaInicio = :pFechaInicio, m.fechaMod = :pFechaMod, m.materia = :pMateria, m.matricula = :pMatricula, "
					+ "m.usuario1 = :pUsuarioAlta, m.usuario2 = :pUsuarioBaja, m.usuario3 = :pUsuarioMod "
					+ "WHERE m.id = :pId", Mesa.class);
			locQuery.setParameter("pCosto", mesa.getCosto());
			locQuery.setParameter("pCurso", mesa.getCurso());
			locQuery.setParameter("pEnabled", mesa.getEnabled());			
			locQuery.setParameter("pFechaAlta", mesa.getFechaAlta());
			locQuery.setParameter("pFechaBaja", mesa.getFechaBaja());
			locQuery.setParameter("pFechaFin", mesa.getFechaFin());
			locQuery.setParameter("pFechaHoraMesa", mesa.getFechaHoraMesa());
			locQuery.setParameter("pFechaInicio", mesa.getFechaInicio());
			locQuery.setParameter("pFechaMod", mesa.getFechaMod());
			locQuery.setParameter("pMateria", mesa.getMateria());
			locQuery.setParameter("pMatricula", mesa.getMatricula());
			locQuery.setParameter("pUsuarioAlta", mesa.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", mesa.getUsuario2());
			locQuery.setParameter("pUsuarioMod", mesa.getUsuario3());
			locQuery.setParameter("pId", mesa.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return mesa.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Mesa get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mesa m WHERE m.id = :pId", Mesa.class);
		locQuery.setParameter("pId", id);
		Mesa mesa = new Mesa();
		try {
			mesa = (Mesa) locQuery.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
			mesa = new Mesa();
		}
		return mesa;
	}

	public List<Mesa> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mesa m WHERE m.enabled = :pEnabled", Mesa.class);
		locQuery.setParameter("pEnabled", true);
		List<Mesa> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Mesa> getLista(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mesa m WHERE m.curso = :pCurso AND m.enabled = :pEnabled", Mesa.class);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pEnabled", true);
		List<Mesa> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Mesa> getLista(Curso curso, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mesa m WHERE m.curso = :pCurso AND m.matricula = :pMatricula AND m.enabled = :pEnabled", Mesa.class);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pEnabled", true);
		List<Mesa> lista = locQuery.getResultList();
		return lista;
	}

	public List<Mesa> getLista(Curso curso, Matricula matricula, Materia materia) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mesa m WHERE m.curso = :pCurso AND m.matricula = :pMatricula AND m.materia = :pMateria "
				+ "AND m.enabled = :pEnabled", Mesa.class);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pMateria", materia);
		locQuery.setParameter("pEnabled", true);
		List<Mesa> lista = locQuery.getResultList();
		return lista;
	}

}
