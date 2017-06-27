package promoda.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import promoda.dao.DAOMatriculaAlumno;
import promoda.model.Alumno;
import promoda.model.Curso;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;

public class DAOMatriculaAlumnoImpl implements DAOMatriculaAlumno, Serializable {

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

	public int insertar(MatriculaAlumno matriculaAlumno) {
		try {
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(matriculaAlumno);
			tx.commit();
			return matriculaAlumno.getId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int update(MatriculaAlumno matriculaAlumno) {
		try {
			inicializar();
			Query locQuery = em.createQuery("UPDATE MatriculaAlumno m SET m.alumno = :pAlumno, m.calificacion = :pCalificacion, m.curso = :pCurso, "
					+ "m.eliminado = :pEliminado, m.enabled = :pEnabled, m.fechaAlta = :pFechaAlta, m.fechaBaja = :pFechaBaja, m.fechaPago = :pFechaPago, "
					+ "m.matricula = :pMatricula, m.montoPago = :pMontoPago, m.state = :pState, m.pago = :pPago, m.usuario1 = :pUsuario1, m.usuario2 = :pUsuario2 "
					+ "WHERE m.id = :pId", MatriculaAlumno.class);
			locQuery.setParameter("pAlumno", matriculaAlumno.getAlumno());
			locQuery.setParameter("pCalificacion", matriculaAlumno.getCalificacion());
			locQuery.setParameter("pCurso", matriculaAlumno.getCurso());
			locQuery.setParameter("pEliminado", matriculaAlumno.getEliminado());
			locQuery.setParameter("pEnabled", matriculaAlumno.getEnabled());
			locQuery.setParameter("pFechaAlta", matriculaAlumno.getFechaAlta());
			locQuery.setParameter("pFechaBaja", matriculaAlumno.getFechaBaja());
			locQuery.setParameter("pFechaPago", matriculaAlumno.getFechaPago());
			locQuery.setParameter("pMatricula", matriculaAlumno.getMatricula());
			locQuery.setParameter("pMontoPago", matriculaAlumno.getMontoPago());
			locQuery.setParameter("pState", matriculaAlumno.getState());
			locQuery.setParameter("pPago", matriculaAlumno.getPago());
			locQuery.setParameter("pUsuario1", matriculaAlumno.getUsuario1());
			locQuery.setParameter("pUsuario2", matriculaAlumno.getUsuario2());
			locQuery.setParameter("pId", matriculaAlumno.getId());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return matriculaAlumno.getId();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public MatriculaAlumno get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m WHERE m.id = :pId", MatriculaAlumno.class);
		locQuery.setParameter("pId", id);
		MatriculaAlumno matriculaAlumno = new MatriculaAlumno();
		try {
			matriculaAlumno = (MatriculaAlumno) locQuery.getSingleResult();
		} catch(Exception e) {
			matriculaAlumno = new MatriculaAlumno();
		}
		return matriculaAlumno;
	}
	
	public MatriculaAlumno get(Alumno alumno, Curso curso, Matricula matricula) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m WHERE m.alumno = :pAlumno AND m.curso = :pCurso "
				+ "AND m.matricula = :pMatricula AND m.eliminado = :pEliminado AND m.enabled = :pEnabled", MatriculaAlumno.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEnabled", true);
		MatriculaAlumno matriculaAlumno = new MatriculaAlumno();
		try {
			matriculaAlumno = (MatriculaAlumno) locQuery.getSingleResult();
		} catch(Exception e) {
			matriculaAlumno = new MatriculaAlumno();
		}
		return matriculaAlumno;
	}

	public List<MatriculaAlumno> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m WHERE m.eliminado = :pEliminado "
				+ "AND m.enabled = :pEnabled", MatriculaAlumno.class);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEnabled", true);
		List<MatriculaAlumno> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<MatriculaAlumno> getLista(Alumno alumno) {
        inicializar();
        Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m "
                + "WHERE m.alumno = :pAlumno AND m.eliminado = :pEliminado AND m.enabled = :pEnabled", Curso.class);
        locQuery.setParameter("pAlumno", alumno);
        locQuery.setParameter("pEliminado", false);
        locQuery.setParameter("pEnabled", true);
        List<MatriculaAlumno> lista = locQuery.getResultList();
        return lista;
    }
	
	public List<MatriculaAlumno> getLista(Matricula matricula) {
        inicializar();
        Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m "
                + "WHERE m.matricula = :pMatricula AND m.eliminado = :pEliminado AND m.enabled = :pEnabled", Curso.class);
        locQuery.setParameter("pMatricula", matricula);
        locQuery.setParameter("pEliminado", false);
        locQuery.setParameter("pEnabled", true);
        List<MatriculaAlumno> lista = locQuery.getResultList();
        return lista;
    }
	
	public List<MatriculaAlumno> getListaOrderByAlumno(Matricula matricula) {
        inicializar();
        Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m "
                + "WHERE m.matricula = :pMatricula AND m.eliminado = :pEliminado AND m.enabled = :pEnabled "
                + "ORDER BY m.alumno.nombreCompleto DESC", Curso.class);
        locQuery.setParameter("pMatricula", matricula);
        locQuery.setParameter("pEliminado", false);
        locQuery.setParameter("pEnabled", true);
        List<MatriculaAlumno> lista = locQuery.getResultList();
        return lista;
    }

	public List<MatriculaAlumno> getLista(Alumno alumno, Curso curso) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m "
				+ "WHERE m.alumno = :pAlumno AND m.curso = :pCurso AND m.eliminado = :pEliminado AND m.enabled = :pEnabled", MatriculaAlumno.class);
		locQuery.setParameter("pAlumno", alumno);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEnabled", true);
		List<MatriculaAlumno> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Curso> getListaCurso(Alumno alumno) {
        inicializar();
        Query locQuery = em.createQuery("SELECT m.curso FROM MatriculaAlumno m "
                + "WHERE m.alumno = :pAlumno AND m.eliminado = :pEliminado AND m.enabled = :pEnabled", Curso.class);
        locQuery.setParameter("pAlumno", alumno);
        locQuery.setParameter("pEliminado", false);
        locQuery.setParameter("pEnabled", true);
        List<Curso> lista = locQuery.getResultList();
        return lista;
    }
	
	public List<Curso> getListaCursoDistinct(Alumno alumno) {
        inicializar();
        Query locQuery = em.createQuery("SELECT DISTINCT m.curso FROM MatriculaAlumno m "
                + "WHERE m.alumno = :pAlumno AND m.eliminado = :pEliminado AND m.enabled = :pEnabled", Curso.class);
        locQuery.setParameter("pAlumno", alumno);
        locQuery.setParameter("pEliminado", false);
        locQuery.setParameter("pEnabled", true);
        List<Curso> lista = locQuery.getResultList();
        return lista;
    }
	
	public List<Matricula> getListaMatricula(Alumno alumno, Curso curso) {
        inicializar();
        Query locQuery = em.createQuery("SELECT DISTINCT m.matricula FROM MatriculaAlumno m WHERE m.alumno = :pAlumno "
                + "AND m.curso = :pCurso AND m.eliminado = :pEliminado AND m.enabled = :pEnabled", Curso.class);
        locQuery.setParameter("pAlumno", alumno);
        locQuery.setParameter("pCurso", curso);
        locQuery.setParameter("pEliminado", false);
        locQuery.setParameter("pEnabled", true);
        List<Matricula> lista = locQuery.getResultList();
        return lista;
    }
	
	public List<MatriculaAlumno> getLista(Curso curso, Matricula matricula, boolean pago) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m WHERE m.eliminado = :pEliminado AND m.curso = :pCurso "
				+ "AND m.matricula = :pMatricula AND m.pago = :pPago AND m.enabled = :pEnabled", MatriculaAlumno.class);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pCurso", curso);
		locQuery.setParameter("pMatricula", matricula);
		locQuery.setParameter("pPago", pago);
		List<MatriculaAlumno> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<MatriculaAlumno> getLista(boolean pago) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM MatriculaAlumno m WHERE m.eliminado = :pEliminado "
				+ "AND m.enabled = :pEnabled AND m.pago = :pPago ORDER BY m.fechaAlta DESC", MatriculaAlumno.class);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEnabled", true);
		locQuery.setParameter("pPago", pago);
		List<MatriculaAlumno> lista = locQuery.getResultList();
		return lista;
	}

}
