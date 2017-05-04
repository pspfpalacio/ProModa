package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOUsuario;
import promoda.model.Alumno;
import promoda.model.Profesore;
import promoda.model.Usuario;

public class DAOUsuarioImpl implements Serializable, DAOUsuario {

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

	public int insertar(Usuario usuario) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(usuario);
			tx.commit();
			return usuario.getId();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Usuario usuario) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Usuario u SET u.alumno = :pAlumno, u.apellido = :pApellido, "
					+ "u.email = :pEmail, u.enabled = :pEnabled, u.fechaAlta = :pFechaAlta, u.fechaBaja = :pFechaBaja, "
					+ "u.fechaMod = :pFechaMod, u.role = :pRole, u.nombre = :pNombre, u.nombreCompleto = :pNombreCompleto, "
					+ "u.password = :pPassword, u.profesore = :pProfesore, u.username = :pUsername, u.usuario1 = :pUsuario1, "
					+ "u.usuario2 = :pUsuario2, u.usuario3 = :pUsuario3 "
					+ "WHERE u.id = :pId", Usuario.class);
			locQuery.setParameter("pAlumno", usuario.getAlumno());
			locQuery.setParameter("pApellido", usuario.getApellido());
			locQuery.setParameter("pEmail", usuario.getEmail());
			locQuery.setParameter("pEnabled", usuario.getEnabled());
			locQuery.setParameter("pFechaAlta", usuario.getFechaAlta());
			locQuery.setParameter("pFechaBaja", usuario.getFechaBaja());
			locQuery.setParameter("pFechaMod", usuario.getFechaMod());
			locQuery.setParameter("pRole", usuario.getRole());
			locQuery.setParameter("pNombre", usuario.getNombre());
			locQuery.setParameter("pNombreCompleto", usuario.getNombreCompleto());
			locQuery.setParameter("pPassword", usuario.getPassword());
			locQuery.setParameter("pProfesore", usuario.getProfesore());
			locQuery.setParameter("pUsername", usuario.getUsername());
			locQuery.setParameter("pUsuario1", usuario.getUsuario1());
			locQuery.setParameter("pUsuario2", usuario.getUsuario2());
			locQuery.setParameter("pUsuario3", usuario.getUsuario3());
			locQuery.setParameter("pId", usuario.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return usuario.getId();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Usuario get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :pId", Usuario.class);
		locQuery.setParameter("pId", id);
		Usuario usuario = new Usuario();
		try {
			usuario = (Usuario) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public Usuario get(String username, String hash) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :pUsername "
				+ "AND u.password = :pPassword", Usuario.class);
		locQuery.setParameter("pUsername", username);
		locQuery.setParameter("pPassword", hash);
		Usuario usuario = new Usuario();
		try {
			usuario = (Usuario) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public Usuario get(String username) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :pUsername", Usuario.class);
		locQuery.setParameter("pUsername", username);
		Usuario usuario = new Usuario();
		try {
			usuario = (Usuario) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public Usuario getLogin(String username, String hash) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :pUsername "
				+ "AND u.password = :pPassword AND u.enabled = :pEnabled", Usuario.class);
		locQuery.setParameter("pUsername", username);
		locQuery.setParameter("pPassword", hash);
		locQuery.setParameter("pEnabled", true);
		Usuario usuario = new Usuario();
		try {
			usuario = (Usuario) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			usuario = new Usuario();
		}
		return usuario;
	}

	public Usuario get(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.alumno = :pAlumno", Usuario.class);
		locQuery.setParameter("pAlumno", alumno);
		Usuario usuario = new Usuario();
		try {
			usuario = (Usuario) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			usuario = new Usuario();
		}
		return usuario;
	}

	public Usuario get(Profesore profesor) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.profesore = :pProfesor", Usuario.class);
		locQuery.setParameter("pProfesor", profesor);
		Usuario usuario = new Usuario();
		try {
			usuario = (Usuario) locQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			usuario = new Usuario();
		}
		return usuario;
	}

	public List<Usuario> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.id <> 1", Usuario.class);
		List<Usuario> lista = locQuery.getResultList();
		return lista;
	}

	public List<Usuario> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.id <> 1 AND u.enabled = :pEnabled", Usuario.class);
		locQuery.setParameter("pEnabled", estado);
		List<Usuario> lista = locQuery.getResultList();
		return lista;
	}

}
