package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMesaAlumno;
import promoda.model.Alumno;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;

public class DAOMesaAlumnoImpl implements DAOMesaAlumno, Serializable {

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

	public int insertar(MesasAlumno mesasAlumno) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(mesasAlumno);
			tx.commit();
			return mesasAlumno.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int update(MesasAlumno mesasAlumno) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE MesasAlumno m SET m.alumno = :pAlumno, m.aprobado = :pAprobado, m.calificacion = :pCalificacion, "
					+ "m.contador = :pContador, m.enabled = :pEnabled, m.fechaAlta = :pFechaAlta, m.fechaBaja = :pFechaBaja, "
					+ "m.fechaMod = :pFechaMod, m.mesa = :pMesa, m.usuario1 = :pUsuarioAlta, m.usuario2 = :pUsuarioBaja, m.usuario3 = :pUsuarioMod "
					+ "WHERE m.id = :pId", MesasAlumno.class);
			locQuery.setParameter("pAlumno", mesasAlumno.getAlumno());
			locQuery.setParameter("pAprobado", mesasAlumno.getAprobado());
			locQuery.setParameter("pCalificacion", mesasAlumno.getCalificacion());			
			locQuery.setParameter("pContador", mesasAlumno.getContador());
			locQuery.setParameter("pEnabled", mesasAlumno.getEnabled());
			locQuery.setParameter("pFechaAlta", mesasAlumno.getFechaAlta());
			locQuery.setParameter("pFechaBaja", mesasAlumno.getFechaBaja());
			locQuery.setParameter("pFechaMod", mesasAlumno.getFechaMod());
			locQuery.setParameter("pMesa", mesasAlumno.getMesa());
			locQuery.setParameter("pUsuarioAlta", mesasAlumno.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", mesasAlumno.getUsuario2());
			locQuery.setParameter("pUsuarioMod", mesasAlumno.getUsuario3());
			locQuery.setParameter("pId", mesasAlumno.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return mesasAlumno.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public MesasAlumno get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.id = :pId", MesasAlumno.class);
		locQuery.setParameter("pId", id);
		MesasAlumno mesasAlumno = new MesasAlumno();
		try {
			mesasAlumno = (MesasAlumno) locQuery.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
			mesasAlumno = new MesasAlumno();
		}
		return mesasAlumno;
	}

	public List<MesasAlumno> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m", MesasAlumno.class);		
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<MesasAlumno> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.enabled = :pEnabled", MesasAlumno.class);
		locQuery.setParameter("pEnabled", estado);
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<MesasAlumno> getLista(Mesa mesa) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.mesa = :pMesa", MesasAlumno.class);
		locQuery.setParameter("pMesa", mesa);
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<MesasAlumno> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.alumno = :pAlumno", MesasAlumno.class);
		locQuery.setParameter("pAlumno", alumno);
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<MesasAlumno> getLista(Alumno alumno, Mesa mesa) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.alumno = :pAlumno AND m.mesa = :pMesa", MesasAlumno.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pMesa", mesa);
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<MesasAlumno> getLista(boolean estado, Mesa mesa) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.enabled = :pEnabled AND m.mesa = :pMesa", MesasAlumno.class);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pMesa", mesa);
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}

	public List<MesasAlumno> getLista(boolean estado, Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.alumno = :pAlumno AND m.enabled = :pEnabled", MesasAlumno.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pEnabled", estado);		
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<MesasAlumno> getLista(boolean estado, Alumno alumno, Mesa mesa) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MesasAlumno m WHERE m.alumno = :pAlumno AND m.enabled = :pEnabled "
				+ "AND m.mesa = :pMesa", MesasAlumno.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pEnabled", estado);
		locQuery.setParameter("pMesa", mesa);
		List<MesasAlumno> lista = locQuery.getResultList();
		return lista;
	}

}
