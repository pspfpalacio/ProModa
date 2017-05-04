package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOProfesor;
import promoda.model.Profesore;

public class DAOProfesorImpl implements Serializable, DAOProfesor {

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

	public int insertar(Profesore profesor) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(profesor);
			tx.commit();
			return profesor.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Profesore profesor) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Profesore p SET p.apellido = :pApellido, p.dni = :pDni, p.domicilio = :pDomicilio, "
					+ "p.email = :pEmail, p.enabled = :pEnabled, p.fechaAlta = :pFechaAlta, p.fechaBaja = :pFechaBaja, "
					+ "p.fechaMod = :pFechaMod, p.fechaNacimiento = :pFechaNacimiento, p.nombre = :pNombre, p.nombreCompleto = :pNombreCompleto, "
					+ "p.telefonoCel = :pTelefonoCel, p.telefonoFijo = :pTelefonoFijo, p.usuario1 = :pUsuario1, p.usuario2 = :pUsuario2, "
					+ "p.usuario3 = :pUsuario3, p.usuario4 = :pUsuario4 "
					+ "WHERE p.id = :pId", Profesore.class);
			locQuery.setParameter("pApellido", profesor.getApellido());
			locQuery.setParameter("pDni", profesor.getDni());
			locQuery.setParameter("pDomicilio", profesor.getDomicilio());
			locQuery.setParameter("pEmail", profesor.getEmail());
			locQuery.setParameter("pEnabled", profesor.getEnabled());
			locQuery.setParameter("pFechaAlta", profesor.getFechaAlta());
			locQuery.setParameter("pFechaBaja", profesor.getFechaBaja());
			locQuery.setParameter("pFechaMod", profesor.getFechaMod());
			locQuery.setParameter("pFechaNacimiento", profesor.getFechaNacimiento());
			locQuery.setParameter("pNombre", profesor.getNombre());
			locQuery.setParameter("pNombreCompleto", profesor.getNombreCompleto());
			locQuery.setParameter("pTelefonoCel", profesor.getTelefonoCel());
			locQuery.setParameter("pTelefonoFijo", profesor.getTelefonoFijo());
			locQuery.setParameter("pUsuario1", profesor.getUsuario1());
			locQuery.setParameter("pUsuario2", profesor.getUsuario2());
			locQuery.setParameter("pUsuario3", profesor.getUsuario3());
			locQuery.setParameter("pUsuario4", profesor.getUsuario4());
			locQuery.setParameter("pId", profesor.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return profesor.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Profesore get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Profesore p WHERE p.id = :pId", Profesore.class);
		locQuery.setParameter("pId", id);
		Profesore profesore = new Profesore();
		try {
			profesore = (Profesore) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			profesore = new Profesore();
		}
		return profesore;
	}
	
	public Profesore getPorDni(int dni) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Profesore p WHERE p.dni = :pDni", Profesore.class);
		locQuery.setParameter("pDni", dni);
		Profesore profesore = new Profesore();
		try {
			profesore = (Profesore) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			profesore = new Profesore();
		}
		return profesore;
	}

	public Profesore get(String dni) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Profesore p WHERE p.dni = :pDni", Profesore.class);
		locQuery.setParameter("pDni", dni);
		Profesore profesore = new Profesore();
		try {
			profesore = (Profesore) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			profesore = new Profesore();
		}
		return profesore;
	}

	public List<Profesore> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Profesore p", Profesore.class);
		List<Profesore> lista = locQuery.getResultList();
		return lista;
	}

	public List<Profesore> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Profesore p WHERE p.enabled = :pEnabled", Profesore.class);
		locQuery.setParameter("pEnabled", estado);
		List<Profesore> lista = locQuery.getResultList();
		return lista;
	}

}
