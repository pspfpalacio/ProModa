package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOInscripcion;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Inscripcione;
import promoda.model.Matricula;

public class DAOInscripcionImpl implements DAOInscripcion, Serializable {

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

	public int insertar(Inscripcione inscripcione) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(inscripcione);
			tx.commit();
			return inscripcione.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(Inscripcione inscripcione) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE Inscripcione i SET i.apellido = :pApellido, i.comoEntero = :pComoEntero, "
					+ "i.conocimientosPrevios = :pConocimientosPrevios, i.curso = :pCurso, i.dni = :pDni, i.domicilio = :pDomicilio, "
					+ "i.edad = :pEdad, i.email = :pEmail, i.enabled = :pEnabled, i.fecha = :pFecha, i.fechaAlta = :pFechaAlta, "
					+ "i.fechaBaja = :pFechaBaja, i.fechaMod = :pFechaMod, i.fechaNacimiento = :pFechaNacimiento, i.horaCursado = :pHoraCursado, "
					+ "i.horaCursado1 = :pHoraCursado1, i.horaCursado2 = :pHoraCursado2, i.localidade = :pLocalidad, i.matricula = :pMatricula, "
					+ "i.nombre = :pNombre, i.nombreCompleto = :pNombreCompleto, i.provincia = :pProvincia, i.telefonoCel = :pTelefonoCel, "
					+ "i.telefonoFijo = :pTelefonoFijo, i.usuario1 = :pUsuario1, i.usuario2 = :pUsuario2, i.usuario3 = :pUsuario3, i.valida = :pValida "
					+ "WHERE i.id = :pId", Inscripcione.class);
			locQuery.setParameter("pApellido", inscripcione.getApellido());
			locQuery.setParameter("pComoEntero", inscripcione.getComoEntero());
			locQuery.setParameter("pConocimientosPrevios", inscripcione.getConocimientosPrevios());
			locQuery.setParameter("pCurso", inscripcione.getCurso());
			locQuery.setParameter("pDni", inscripcione.getDni());
			locQuery.setParameter("pDomicilio", inscripcione.getDomicilio());
			locQuery.setParameter("pEdad", inscripcione.getEdad());
			locQuery.setParameter("pEmail", inscripcione.getEmail());
			locQuery.setParameter("pEnabled", inscripcione.getEnabled());
			locQuery.setParameter("pFecha", inscripcione.getFecha());
			locQuery.setParameter("pFechaAlta", inscripcione.getFechaAlta());			
			locQuery.setParameter("pFechaBaja", inscripcione.getFechaBaja());
			locQuery.setParameter("pFechaMod", inscripcione.getFechaMod());
			locQuery.setParameter("pFechaNacimiento", inscripcione.getFechaNacimiento());
			locQuery.setParameter("pHoraCursado", inscripcione.getHoraCursado());
			locQuery.setParameter("pHoraCursado1", inscripcione.getHoraCursado1());
			locQuery.setParameter("pHoraCursado2", inscripcione.getHoraCursado2());
			locQuery.setParameter("pLocalidad", inscripcione.getLocalidade());
			locQuery.setParameter("pMatricula", inscripcione.getMatricula());
			locQuery.setParameter("pNombre", inscripcione.getNombre());
			locQuery.setParameter("pNombreCompleto", inscripcione.getNombreCompleto());
			locQuery.setParameter("pProvincia", inscripcione.getProvincia());
			locQuery.setParameter("pTelefonoCel", inscripcione.getTelefonoCel());
			locQuery.setParameter("pTelefonoFijo", inscripcione.getTelefonoFijo());
			locQuery.setParameter("pUsuario1", inscripcione.getUsuario1());
			locQuery.setParameter("pUsuario2", inscripcione.getUsuario2());
			locQuery.setParameter("pUsuario3", inscripcione.getUsuario3());
			locQuery.setParameter("pValida", inscripcione.getValida());
			locQuery.setParameter("pId", inscripcione.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return inscripcione.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Inscripcione get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.id = :pId", Inscripcione.class);
		locQuery.setParameter("pId", id);
		Inscripcione inscripcione = new Inscripcione();
		try {
			inscripcione = (Inscripcione) locQuery.getSingleResult();
		} catch (Exception e) {
			inscripcione = new Inscripcione();
		}
		return inscripcione;
	}

	public List<Inscripcione> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i", Inscripcione.class);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado ORDER BY i.fecha DESC, i.id DESC", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Inscripcione> getListaOrderByAlumno(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado ORDER BY i.nombreCompleto", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.alumno = :pAlumno", Inscripcione.class);
		locQuery.setParameter("pAlumno", alumno);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.curso = :pCurso", Inscripcione.class);
		locQuery.setParameter("pCurso", curso);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(boolean estado, Alumno alumno) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado "
				+ "AND i.alumno = :pAlumno", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pAlumno", alumno);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(boolean estado, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado "
				+ "AND i.curso = :pCurso", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCurso", curso);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(Alumno alumno, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.alumno = :pAlumno "
				+ "AND i.curso = :pCurso", Inscripcione.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(boolean estado, Alumno alumno,
			Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado "
				+ "AND  i.alumno = :pAlumno AND i.curso = :pCurso", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getList(boolean valida, boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.valida = :pValida "
				+ "AND i.enabled = :pEstado", Inscripcione.class);
		locQuery.setParameter("pValida", valida);
		locQuery.setParameter("pEstado", estado);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Inscripcione> getLista(boolean estado, Curso curso,
			Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado AND i.curso = :pCurso "
				+ "AND i.matricula = :pMatricula", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Inscripcione> getListaOrderByAlumno(boolean estado, Curso curso,
			Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado AND i.curso = :pCurso "
				+ "AND i.matricula = :pMatricula ORDER BY i.nombreCompleto", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Inscripcione> getListaOrderByFechaId(boolean estado, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado "
				+ "AND i.curso = :pCurso ORDER BY i.fecha DESC, i.id DESC", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCurso", curso);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Inscripcione> getListaOrderByFechaId(boolean estado, Curso curso, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado "
				+ "AND i.curso = :pCurso AND i.matricula = :pMatricula ORDER BY i.fecha DESC, i.id DESC", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Inscripcione> getListaOrderByAlumno(boolean estado, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT i FROM Inscripcione i WHERE i.enabled = :pEstado "
				+ "AND i.curso = :pCurso ORDER BY i.nombreCompleto", Inscripcione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCurso", curso);
		List<Inscripcione> lista = locQuery.getResultList();
		return lista;
	}

}
