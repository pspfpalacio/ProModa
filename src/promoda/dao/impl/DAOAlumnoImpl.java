package promoda.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOAlumno;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Inscripcione;

public class DAOAlumnoImpl implements Serializable, DAOAlumno {

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

	public int insertar(Alumno alumno) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(alumno);
			tx.commit();
			return alumno.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public int update(Alumno alumno) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Alumno a SET a.apellido = :pApellido, a.dni = :pDni, a.domicilio = :pDomicilio, "
					+ "a.edad = :pEdad, a.email = :pEmail, a.enabled = :pEnabled, a.fechaAlta = :pFechaAlta, a.fechaBaja = :pFechaBaja, "
					+ "a.fechaMod = :pFechaMod, a.fechaNacimiento = :pFechaNacimiento, a.nombreCompleto = :pNombreCompleto, "
					+ "a.nombres = :pNombres, a.telefonoCel = :pTelefonoCel, a.telefonoFijo = :pTelefonoFijo, a.usuario1 = :pUsuario1, "
					+ "a.usuario2 = :pUsuario2, a.usuario3 = :pUsuario3, a.usuario4 = :pUsuario4 "
					+ "WHERE a.id = :pId", Alumno.class);
			locQuery.setParameter("pApellido", alumno.getApellido());
			locQuery.setParameter("pDni", alumno.getDni());
			locQuery.setParameter("pDomicilio", alumno.getDomicilio());
			locQuery.setParameter("pEdad", alumno.getEdad());
			locQuery.setParameter("pEmail", alumno.getEmail());
			locQuery.setParameter("pEnabled", alumno.getEnabled());
			locQuery.setParameter("pFechaAlta", alumno.getFechaAlta());
			locQuery.setParameter("pFechaBaja", alumno.getFechaBaja());
			locQuery.setParameter("pFechaMod", alumno.getFechaMod());
			locQuery.setParameter("pFechaNacimiento", alumno.getFechaNacimiento());
			locQuery.setParameter("pNombreCompleto", alumno.getNombreCompleto());
			locQuery.setParameter("pNombres", alumno.getNombres());
			locQuery.setParameter("pTelefonoCel", alumno.getTelefonoCel());
			locQuery.setParameter("pTelefonoFijo", alumno.getTelefonoFijo());
			locQuery.setParameter("pUsuario1", alumno.getUsuario1());
			locQuery.setParameter("pUsuario2", alumno.getUsuario2());
			locQuery.setParameter("pUsuario3", alumno.getUsuario3());
			locQuery.setParameter("pUsuario4", alumno.getUsuario4());
			locQuery.setParameter("pId", alumno.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return alumno.getId();
		} catch (Exception e) {
			return 0;
		}
	}

	public Alumno get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Alumno a WHERE a.id = :pId", Alumno.class);
		locQuery.setParameter("pId", id);
		Alumno alumno = new Alumno();
		try {
			alumno = (Alumno) locQuery.getSingleResult();
		} catch (Exception e) {
			alumno = new Alumno();
		}
		return alumno;
	}
	
	public Alumno getPorDni(int dni) {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Alumno a WHERE a.dni = :pDni AND a.enabled = :pEnabled", Alumno.class);
		locQuery.setParameter("pDni", dni);
		locQuery.setParameter("pEnabled", true);
		Alumno alumno = new Alumno();
		try {
			alumno = (Alumno) locQuery.getSingleResult();
		} catch (Exception e) {
			alumno = new Alumno();
		}
		return alumno;
	}

	public List<Alumno> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Alumno a", Alumno.class);
		List<Alumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<Alumno> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT a FROM Alumno a WHERE a.enabled = :pEnabled ORDER BY a.nombreCompleto", Alumno.class);
		locQuery.setParameter("pEnabled", estado);
		List<Alumno> lista = locQuery.getResultList();
		return lista;
	}
	
//	public List<Alumno> getLista(Matricula matricula) {
//		inicializar();
//		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado "
//				+ "AND i.matricula = :pMatricula", Inscripcione.class);
//		locQuery.setParameter("pEstado", true);
//		locQuery.setParameter("pMatricula", matricula);
//		List<Inscripcione> lista = locQuery.getResultList();
//		List<Alumno> listAlumnos = new ArrayList<Alumno>();
//		for (Inscripcione inscripcione : lista) {
//			Alumno alumno = new Alumno();
//			alumno = getPorDni(inscripcione.getDni());
//			listAlumnos.add(alumno);
//		}
//		return listAlumnos;
//	}

}
