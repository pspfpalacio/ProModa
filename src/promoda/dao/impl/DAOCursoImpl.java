package promoda.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOCurso;
import promoda.model.Curso;

public class DAOCursoImpl implements Serializable, DAOCurso {

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

	public int insertar(Curso curso) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(curso);
			tx.commit();
			return curso.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Curso curso) {
		try {
			Query locQuery = em.createQuery("UPDATE Curso c SET c.cantHoras = :pCantHoras, c.costoCurso = :pCostoCurso, "
					+ "c.costoMatricula = :pCostoMatricula, c.costoTotal = :pCostoTotal, c.diasCursado = :pDiasCursado, "
					+ "c.duracionMeses = :pDuracionMeses, c.enabled = :pEnabled, c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, "
					+ "c.fechaMod = :pFechaMod, c.matricula = :pMatriculaVigente, c.nombre = :pNombre, c.usuario1 = :pUsuario1, "
					+ "c.usuario2 = :pUsuario2, c.usuario3 = :pUsuario3 "
					+ "WHERE c.id = :pId", Curso.class);
			locQuery.setParameter("pCantHoras", curso.getCantHoras());
			locQuery.setParameter("pCostoCurso", curso.getCostoCurso());
			locQuery.setParameter("pCostoMatricula", curso.getCostoMatricula());
			locQuery.setParameter("pCostoTotal", curso.getCostoTotal());
			locQuery.setParameter("pDiasCursado", curso.getDiasCursado());
			locQuery.setParameter("pDuracionMeses", curso.getDuracionMeses());
			locQuery.setParameter("pEnabled", curso.getEnabled());
			locQuery.setParameter("pFechaAlta", curso.getFechaAlta());
			locQuery.setParameter("pFechaBaja", curso.getFechaBaja());
			locQuery.setParameter("pFechaMod", curso.getFechaMod());
			locQuery.setParameter("pMatriculaVigente", curso.getMatricula());
			locQuery.setParameter("pNombre", curso.getNombre());
			locQuery.setParameter("pUsuario1", curso.getUsuario1());
			locQuery.setParameter("pUsuario2", curso.getUsuario2());
			locQuery.setParameter("pUsuario3", curso.getUsuario3());
			locQuery.setParameter("pId", curso.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return curso.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Curso get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Curso c WHERE c.id = :pId", Curso.class);
		locQuery.setParameter("pId", id);
		Curso curso = new Curso();
		try {
			curso = (Curso) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			curso = new Curso();
		}
		return curso;
	}

	public List<Curso> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Curso c", Curso.class);
		List<Curso> lista = locQuery.getResultList();
		return lista;
	}

	public List<Curso> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Curso c WHERE c.enabled = :pEnabled", Curso.class);
		locQuery.setParameter("pEnabled", estado);
		List<Curso> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Curso> getListaMatVig() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Curso c WHERE c.matricula <> NULL "
				+ "AND c.enabled = :pEnabled", Curso.class);
		locQuery.setParameter("pEnabled", true);
		List<Curso> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Curso> getListaMatVig(Date fechaHoy) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Curso c WHERE c.matricula <> NULL "
				+ "AND c.enabled = :pEnabled AND c.matricula.fechaAlta <= :pFecha AND c.matricula.fechaFinalizacion >= :pFecha", Curso.class);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pFecha", fechaHoy);
		List<Curso> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Curso> getListaMatVig(Date fechaUno, Date fechaDos) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Curso c WHERE c.matricula <> NULL "
				+ "AND c.enabled = :pEnabled AND c.matricula.fechaAlta <= :pFechaUno AND c.matricula.fechaFinalizacion >= :pFechaDos "
				+ "ORDER BY c.matricula.fechaAlta DESC", Curso.class);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pFechaUno", fechaUno);
		locQuery.setParameter("pFechaDos", fechaDos);
		List<Curso> lista = locQuery.getResultList();
		return lista;
	}

}
