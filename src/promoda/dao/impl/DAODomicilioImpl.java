package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAODomicilio;
import promoda.model.Domicilio;
import promoda.model.Localidade;

public class DAODomicilioImpl implements Serializable, DAODomicilio {

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

	public int insertar(Domicilio domicilio) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(domicilio);
			tx.commit();
			return domicilio.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Domicilio domicilio) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Domicilio d SET d.calle = :pCalle, d.codigoPostal = :pCodigoPostal, "
					+ "d.departamento = :pDepartamento, d.enabled = :pEnabled, d.fechaAlta = :pFechaAlta, "
					+ "d.fechaBaja = :pFechaBaja, d.fechaMod = :pFechaMod, d.usuario1 = :pUsuario1, d.usuario2 = :pUsuario2, "
					+ "d.usuario3 = :pUsuario3, d.localidade = :pLocalidade, d.numero = :pNumero, d.piso = :pPiso "
					+ "WHERE d.id = :pId", Domicilio.class);
			locQuery.setParameter("pCalle", domicilio.getCalle());
			locQuery.setParameter("pCodigoPostal", domicilio.getCodigoPostal());
			locQuery.setParameter("pDepartamento", domicilio.getDepartamento());
			locQuery.setParameter("pEnabled", domicilio.getEnabled());
			locQuery.setParameter("pFechaAlta", domicilio.getFechaAlta());
			locQuery.setParameter("pFechaBaja", domicilio.getFechaBaja());
			locQuery.setParameter("pFechaMod", domicilio.getFechaMod());
			locQuery.setParameter("pUsuario1", domicilio.getUsuario1());
			locQuery.setParameter("pUsuario2", domicilio.getUsuario2());
			locQuery.setParameter("pUsuario3", domicilio.getUsuario3());
			locQuery.setParameter("pLocalidade", domicilio.getLocalidade());
			locQuery.setParameter("pNumero", domicilio.getNumero());
			locQuery.setParameter("pPiso", domicilio.getPiso());
			locQuery.setParameter("pId", domicilio.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return domicilio.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Domicilio get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Domicilio d WHERE d.id = :pId", Domicilio.class);
		locQuery.setParameter("pId", id);
		Domicilio domicilio = new Domicilio();
		try {
			domicilio = (Domicilio) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			domicilio = new Domicilio();
		}
		return domicilio;
	}

	public List<Domicilio> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Domicilio d", Domicilio.class);
		List<Domicilio> lista = locQuery.getResultList();
		return lista;
	}

	public List<Domicilio> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Domicilio d WHERE d.enabled = :pEnabled", Domicilio.class);
		locQuery.setParameter("pEnabled", estado);
		List<Domicilio> lista = locQuery.getResultList();
		return lista;
	}

	public List<Domicilio> getLista(Localidade localidad) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Domicilio d WHERE d.localidade = :pLocalidade", Domicilio.class);
		locQuery.setParameter("pLocalidade", localidad);
		List<Domicilio> lista = locQuery.getResultList();
		return lista;
	}

	public List<Domicilio> getLista(boolean estado, Localidade localidad) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Domicilio d WHERE d.enabled = :pEnabled "
				+ "AND d.localidade = :pLocalidade", Domicilio.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pLocalidade", localidad);
		List<Domicilio> lista = locQuery.getResultList();
		return lista;
	}

}
