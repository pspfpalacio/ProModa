package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAORolesVista;
import promoda.model.Role;
import promoda.model.RolesVista;
import promoda.model.Vista;

public class DAORolesVistaImpl implements Serializable, DAORolesVista {

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

	public int insertar(RolesVista rolesVista) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(rolesVista);
			tx.commit();
			return rolesVista.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(RolesVista rolesVista) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE RolesVista r SET r.role = :pRole, r.vista = :pVista "
					+ "WHERE r.id = :pId", RolesVista.class);
			locQuery.setParameter("pRole", rolesVista.getRole());
			locQuery.setParameter("pVista", rolesVista.getVista());
			locQuery.setParameter("pId", rolesVista.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return rolesVista.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public RolesVista get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r WHERE r.id = :pId", RolesVista.class);
		locQuery.setParameter("pId", id);
		RolesVista rolesVista = new RolesVista();
		try {
			rolesVista = (RolesVista) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			rolesVista = new RolesVista();
		}
		return rolesVista;
	}

	public RolesVista get(Role role, Vista vista) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r WHERE r.role = :pRole "
				+ "AND r.vista = :pVista", RolesVista.class);
		locQuery.setParameter("pRole", role);
		locQuery.setParameter("pVista", vista);
		RolesVista rolesVista = new RolesVista();
		try {
			rolesVista = (RolesVista) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			rolesVista = new RolesVista();
		}
		return rolesVista;
	}

	public List<RolesVista> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r", RolesVista.class);
		List<RolesVista> lista = locQuery.getResultList();
		return lista;
	}

	public List<RolesVista> getLista(Role role) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r WHERE r.role = :pRole", RolesVista.class);
		locQuery.setParameter("pRole", role);
		List<RolesVista> lista = locQuery.getResultList();
		return lista;
	}

	public List<RolesVista> getLista(Vista vista) {
		inicializar();
		Query locQuery = em.createQuery("SELECT r FROM RolesVista r WHERE r.vista = :pVista", RolesVista.class);
		locQuery.setParameter("pVista", vista);
		List<RolesVista> lista = locQuery.getResultList();
		return lista;
	}

}
