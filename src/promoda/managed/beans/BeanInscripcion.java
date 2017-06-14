package promoda.managed.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import promoda.clases.InscripcionReporte;
import promoda.clases.Inscripto;
import promoda.clases.Reporte;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOAsistencia;
import promoda.dao.DAOCuota;
import promoda.dao.DAOCuotaImpaga;
import promoda.dao.DAOCurso;
import promoda.dao.DAODomicilio;
import promoda.dao.DAOInscripcion;
import promoda.dao.DAOInscripcionDia;
import promoda.dao.DAOInscripcionMotivo;
import promoda.dao.DAOLocalidad;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOMatriculaImpaga;
import promoda.dao.DAOMotivo;
import promoda.dao.DAOPagosCuota;
import promoda.dao.DAOPagosMatricula;
import promoda.dao.DAOParametro;
import promoda.dao.DAOPlanPago;
import promoda.dao.DAOProvincia;
import promoda.dao.DAOUsuario;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.CuotaImpaga;
import promoda.model.Curso;
import promoda.model.Domicilio;
import promoda.model.InscripcionDia;
import promoda.model.InscripcionMotivo;
import promoda.model.Inscripcione;
import promoda.model.Localidade;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.MatriculaImpaga;
import promoda.model.Motivo;
import promoda.model.Parametro;
import promoda.model.PlanPago;
import promoda.model.Provincia;
import promoda.model.Role;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanInscripcion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
	private DAOAlumno alumnoDAO;
	
	@ManagedProperty(value = "#{BeanInscripcionDAO}")
	private DAOInscripcion inscripcionDAO;
	
	@ManagedProperty(value = "#{BeanProvinciaDAO}")
	private DAOProvincia provinciaDAO;
	
	@ManagedProperty(value = "#{BeanLocalidadDAO}")
	private DAOLocalidad localidadDAO;
	
	@ManagedProperty(value = "#{BeanDomicilioDAO}")
	private DAODomicilio domicilioDAO;
	
	@ManagedProperty(value = "#{BeanPlanPagoDAO}")
	private DAOPlanPago planPagoDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaDAO}")
	private DAOMatricula matriculaDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	@ManagedProperty(value = "#{BeanAsistenciaDAO}")
	private DAOAsistencia asistenciaDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
	private DAOMatriculaAlumno matriculaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
	private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanMotivoDAO}")
	private DAOMotivo motivoDAO;
	
	@ManagedProperty(value = "#{BeanInscripcionMotivoDAO}")
	private DAOInscripcionMotivo inscripcionMotivoDAO;
	
	@ManagedProperty(value = "#{BeanInscripcionDiaDAO}")
	private DAOInscripcionDia inscripcionDiaDAO;
	
	@ManagedProperty(value = "#{BeanParametroDAO}")
	private DAOParametro parametroDAO;
	
	@ManagedProperty(value = "#{BeanPagosMatriculaDAO}")
    private DAOPagosMatricula pagosMatriculaDAO;
	
	@ManagedProperty(value = "#{BeanPagosCuotaDAO}")
    private DAOPagosCuota pagosCuotaDAO;
	
	@ManagedProperty(value = "#{BeanMatriculaImpagaDAO}")
	private DAOMatriculaImpaga matriculaImpagaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaImpagaDAO}")
	private DAOCuotaImpaga cuotaImpagaDAO;
	
	private List<Inscripcione> listaInscripciones;
	private List<Inscripcione> filteredInscripciones;
	private List<Inscripto> listaInscriptos;
	private List<Curso> listaCursos;
	private List<Provincia> listaProvincias;
	private List<Localidade> listaLocalidades;
	private List<Motivo> listaMotivos;
	private List<CuotaImpaga> listaCuotasImpagas;
	private List<MatriculaImpaga> listaMatriculasImpagas;
	private List<Matricula> listaMatriculas;
	private List<String> selectedMotivos; 
	private List<String> selectedDias;
	private Inscripcione inscripcion;
	private Alumno alumno;
	private Domicilio domicilio;
	private Matricula matricula;
	private Curso curso;
	private PlanPago planPago;
	private Usuario usuario;
	private InscripcionReporte inscripcionReporte;
	private Date primerVencimiento;
	private Date horaCursado1;
	private Date horaCursado2;
	private int idCurso;
	private int idProvincia;
	private int idLocalidad;
	private int idMatricula;
	private int cantCuotas;
	private int descuentoCurso;
	private int descuentoMatricula;
	private int dni;
	private float montoCurso;
	private float montoCuota;
	private float montoMatricula;
	private boolean porDefecto;
	private boolean deuda;
	private boolean editPP;
	private boolean editPD; 
	private boolean guardarPP;
	private boolean guardarPD;
	private boolean editPPM;
	private boolean editPPC;
	private boolean editPDC;
	private boolean muestraEdita;
	
	private static String _PASSWORD = "WdUjxKFLegTkGNtfAJLVzAEZvIr4i5xSpct/V6SF9lkTA4gxrhsmONnCTOw3ic+i3LGYL58gWxOJnzZtmg1Ypw==";
	private static int _ROL = 2;

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public DAOCurso getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(DAOCurso cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}

	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}

	public DAOInscripcion getInscripcionDAO() {
		return inscripcionDAO;
	}

	public void setInscripcionDAO(DAOInscripcion inscripcionDAO) {
		this.inscripcionDAO = inscripcionDAO;
	}

	public DAOProvincia getProvinciaDAO() {
		return provinciaDAO;
	}

	public void setProvinciaDAO(DAOProvincia provinciaDAO) {
		this.provinciaDAO = provinciaDAO;
	}

	public DAOLocalidad getLocalidadDAO() {
		return localidadDAO;
	}

	public void setLocalidadDAO(DAOLocalidad localidadDAO) {
		this.localidadDAO = localidadDAO;
	}

	public DAODomicilio getDomicilioDAO() {
		return domicilioDAO;
	}

	public void setDomicilioDAO(DAODomicilio domicilioDAO) {
		this.domicilioDAO = domicilioDAO;
	}

	public DAOPlanPago getPlanPagoDAO() {
		return planPagoDAO;
	}

	public void setPlanPagoDAO(DAOPlanPago planPagoDAO) {
		this.planPagoDAO = planPagoDAO;
	}

	public DAOMatricula getMatriculaDAO() {
		return matriculaDAO;
	}

	public void setMatriculaDAO(DAOMatricula matriculaDAO) {
		this.matriculaDAO = matriculaDAO;
	}

	public DAOMateria getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(DAOMateria materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public DAOAsistencia getAsistenciaDAO() {
		return asistenciaDAO;
	}

	public void setAsistenciaDAO(DAOAsistencia asistenciaDAO) {
		this.asistenciaDAO = asistenciaDAO;
	}

	public DAOMatriculaAlumno getMatriculaAlumnoDAO() {
		return matriculaAlumnoDAO;
	}

	public void setMatriculaAlumnoDAO(DAOMatriculaAlumno matriculaAlumnoDAO) {
		this.matriculaAlumnoDAO = matriculaAlumnoDAO;
	}

	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
	}

	public DAOMotivo getMotivoDAO() {
		return motivoDAO;
	}

	public void setMotivoDAO(DAOMotivo motivoDAO) {
		this.motivoDAO = motivoDAO;
	}

	public DAOInscripcionMotivo getInscripcionMotivoDAO() {
		return inscripcionMotivoDAO;
	}

	public void setInscripcionMotivoDAO(DAOInscripcionMotivo inscripcionMotivoDAO) {
		this.inscripcionMotivoDAO = inscripcionMotivoDAO;
	}

	public DAOInscripcionDia getInscripcionDiaDAO() {
		return inscripcionDiaDAO;
	}

	public void setInscripcionDiaDAO(DAOInscripcionDia inscripcionDiaDAO) {
		this.inscripcionDiaDAO = inscripcionDiaDAO;
	}

	public DAOParametro getParametroDAO() {
		return parametroDAO;
	}

	public void setParametroDAO(DAOParametro parametroDAO) {
		this.parametroDAO = parametroDAO;
	}

	public DAOPagosMatricula getPagosMatriculaDAO() {
		return pagosMatriculaDAO;
	}

	public void setPagosMatriculaDAO(DAOPagosMatricula pagosMatriculaDAO) {
		this.pagosMatriculaDAO = pagosMatriculaDAO;
	}

	public DAOPagosCuota getPagosCuotaDAO() {
		return pagosCuotaDAO;
	}

	public void setPagosCuotaDAO(DAOPagosCuota pagosCuotaDAO) {
		this.pagosCuotaDAO = pagosCuotaDAO;
	}

	public DAOMatriculaImpaga getMatriculaImpagaDAO() {
		return matriculaImpagaDAO;
	}

	public void setMatriculaImpagaDAO(DAOMatriculaImpaga matriculaImpagaDAO) {
		this.matriculaImpagaDAO = matriculaImpagaDAO;
	}

	public DAOCuotaImpaga getCuotaImpagaDAO() {
		return cuotaImpagaDAO;
	}

	public void setCuotaImpagaDAO(DAOCuotaImpaga cuotaImpagaDAO) {
		this.cuotaImpagaDAO = cuotaImpagaDAO;
	}

	public List<Inscripcione> getListaInscripciones() {
		return listaInscripciones;
	}

	public void setListaInscripciones(List<Inscripcione> listaInscripciones) {
		this.listaInscripciones = listaInscripciones;
	}

	public List<Inscripcione> getFilteredInscripciones() {
		return filteredInscripciones;
	}

	public void setFilteredInscripciones(List<Inscripcione> filteredInscripciones) {
		this.filteredInscripciones = filteredInscripciones;
	}

	public List<Inscripto> getListaInscriptos() {
		return listaInscriptos;
	}

	public void setListaInscriptos(List<Inscripto> listaInscriptos) {
		this.listaInscriptos = listaInscriptos;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Provincia> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(List<Provincia> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public List<Localidade> getListaLocalidades() {
		return listaLocalidades;
	}

	public void setListaLocalidades(List<Localidade> listaLocalidades) {
		this.listaLocalidades = listaLocalidades;
	}

	public List<Motivo> getListaMotivos() {
		return listaMotivos;
	}

	public void setListaMotivos(List<Motivo> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}

	public List<CuotaImpaga> getListaCuotasImpagas() {
		return listaCuotasImpagas;
	}

	public void setListaCuotasImpagas(List<CuotaImpaga> listaCuotasImpagas) {
		this.listaCuotasImpagas = listaCuotasImpagas;
	}

	public List<MatriculaImpaga> getListaMatriculasImpagas() {
		return listaMatriculasImpagas;
	}

	public void setListaMatriculasImpagas(
			List<MatriculaImpaga> listaMatriculasImpagas) {
		this.listaMatriculasImpagas = listaMatriculasImpagas;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public List<String> getSelectedMotivos() {
		return selectedMotivos;
	}

	public void setSelectedMotivos(List<String> selectedMotivos) {
		this.selectedMotivos = selectedMotivos;
	}

	public List<String> getSelectedDias() {
		return selectedDias;
	}

	public void setSelectedDias(List<String> selectedDias) {
		this.selectedDias = selectedDias;
	}

	public Inscripcione getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcione inscripcion) {
		this.inscripcion = inscripcion;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public PlanPago getPlanPago() {
		return planPago;
	}

	public void setPlanPago(PlanPago planPago) {
		this.planPago = planPago;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public InscripcionReporte getInscripcionReporte() {
		return inscripcionReporte;
	}

	public void setInscripcionReporte(InscripcionReporte inscripcionReporte) {
		this.inscripcionReporte = inscripcionReporte;
	}

	public Date getPrimerVencimiento() {
		return primerVencimiento;
	}

	public void setPrimerVencimiento(Date primerVencimiento) {
		this.primerVencimiento = primerVencimiento;
	}

	public Date getHoraCursado1() {
		return horaCursado1;
	}

	public void setHoraCursado1(Date horaCursado1) {
		this.horaCursado1 = horaCursado1;
	}

	public Date getHoraCursado2() {
		return horaCursado2;
	}

	public void setHoraCursado2(Date horaCursado2) {
		this.horaCursado2 = horaCursado2;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public int getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public int getDescuentoCurso() {
		return descuentoCurso;
	}

	public void setDescuentoCurso(int descuentoCurso) {
		this.descuentoCurso = descuentoCurso;
	}

	public int getDescuentoMatricula() {
		return descuentoMatricula;
	}

	public void setDescuentoMatricula(int descuentoMatricula) {
		this.descuentoMatricula = descuentoMatricula;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public float getMontoCurso() {
		return montoCurso;
	}

	public void setMontoCurso(float montoCurso) {
		this.montoCurso = montoCurso;
	}

	public float getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(float montoCuota) {
		this.montoCuota = montoCuota;
	}

	public float getMontoMatricula() {
		return montoMatricula;
	}

	public void setMontoMatricula(float montoMatricula) {
		this.montoMatricula = montoMatricula;
	}

	public boolean isPorDefecto() {
		return porDefecto;
	}

	public void setPorDefecto(boolean porDefecto) {
		this.porDefecto = porDefecto;
	}

	public boolean isDeuda() {
		return deuda;
	}

	public void setDeuda(boolean deuda) {
		this.deuda = deuda;
	}

	public boolean isEditPP() {
		return editPP;
	}

	public void setEditPP(boolean editPP) {
		this.editPP = editPP;
	}

	public boolean isEditPD() {
		return editPD;
	}

	public void setEditPD(boolean editPD) {
		this.editPD = editPD;
	}

	public boolean isGuardarPP() {
		return guardarPP;
	}

	public void setGuardarPP(boolean guardarPP) {
		this.guardarPP = guardarPP;
	}

	public boolean isGuardarPD() {
		return guardarPD;
	}

	public void setGuardarPD(boolean guardarPD) {
		this.guardarPD = guardarPD;
	}

	public boolean isEditPPM() {
		return editPPM;
	}

	public void setEditPPM(boolean editPPM) {
		this.editPPM = editPPM;
	}

	public boolean isEditPPC() {
		return editPPC;
	}

	public void setEditPPC(boolean editPPC) {
		this.editPPC = editPPC;
	}

	public boolean isEditPDC() {
		return editPDC;
	}

	public void setEditPDC(boolean editPDC) {
		this.editPDC = editPDC;
	}

	public boolean isMuestraEdita() {
		return muestraEdita;
	}

	public void setMuestraEdita(boolean muestraEdita) {
		this.muestraEdita = muestraEdita;
	}

	public String goInscripcion(Usuario user) {
		usuario = new Usuario();
		inscripcion = new Inscripcione();
		alumno = new Alumno();
		domicilio = new Domicilio();
		matricula = new Matricula();
		curso = new Curso();
		planPago = new PlanPago();	
		primerVencimiento = new Date();
		usuario = user;
		idCurso = 0;
		idLocalidad = 0;
		idProvincia = 0;
		cantCuotas = 0;
		descuentoCurso = 0;
		descuentoMatricula = 0;
		montoCurso = 0;
		montoCuota = 0;
		montoMatricula = 0;
		dni = 0;
		idMatricula = 0;
		porDefecto = true;
		deuda = false;
		inscripcion = new Inscripcione();
		inscripcion.setFecha(new Date());
		inscripcion.setDomicilio(new Domicilio());
		listaCursos = new ArrayList<Curso>();
		listaProvincias = new ArrayList<Provincia>();
		listaLocalidades = new ArrayList<Localidade>();
		listaMotivos = new ArrayList<Motivo>();
		selectedMotivos = new ArrayList<String>();
		selectedDias = new ArrayList<String>();
		listaMatriculas = new ArrayList<Matricula>();
		listaCursos = cursoDAO.getLista(true);
		listaProvincias = provinciaDAO.getLista();
		listaMotivos = motivoDAO.getLista();
		return "inscripcion";
	}
	
	public String goInscripciones(Usuario user) {
		usuario = new Usuario();
		usuario = user;
		idCurso = 0;
		idMatricula = 0;
		listaCursos = new ArrayList<Curso>();
		listaMatriculas = new ArrayList<Matricula>();
		listaInscripciones = new ArrayList<Inscripcione>();
		filteredInscripciones = new ArrayList<Inscripcione>();
		listaInscripciones = inscripcionDAO.getLista(true);
		filteredInscripciones = listaInscripciones;
		listaCursos = cursoDAO.getListaMatVig();
		return "inscripciones";
	}
	
	public String goInscriptos(Usuario user) {
		usuario = new Usuario();		
		listaInscriptos = new ArrayList<Inscripto>();
		listaCursos = new ArrayList<Curso>();
		listaMatriculas = new ArrayList<Matricula>();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		List<Inscripcione> listInscrips = inscripcionDAO.getListaOrderByAlumno(true);
		for (Inscripcione inscrip : listInscrips) {
			Inscripto inscripto = new Inscripto();
			Alumno alum = alumnoDAO.getPorDni(inscrip.getDni());
			if (alum.getId() != 0) {
				inscripto.setDni(inscrip.getDni());
				inscripto.setEmail(alum.getEmail());
				inscripto.setNombreCompleto(alum.getNombreCompleto());
				inscripto.setTelefonoCel(alum.getTelefonoCel());
				inscripto.setTelefonoFijo(alum.getTelefonoFijo());
				inscripto.setFechaNacimiento(formatoFecha.format(alum.getFechaNacimiento()));
				listaInscriptos.add(inscripto);
			}			
		}
		idCurso = 0;
		idMatricula = 0;
		usuario = user;
		listaCursos = cursoDAO.getListaMatVig();
		return "inscriptos";
	}
	
	public void getPorDni() {
		if (dni != 0) {
			alumno = new Alumno();
			domicilio = new Domicilio();			
			listaLocalidades = new ArrayList<Localidade>();
			idProvincia = 0;
			idLocalidad = 0;
			alumno = alumnoDAO.getPorDni(dni);
			if (alumno.getId() == 0) {
				alumno.setDni(dni);
			} else {
				domicilio = alumno.getDomicilio();
				idLocalidad = domicilio.getLocalidade().getId();
				Localidade loc = localidadDAO.get(idLocalidad);
				idProvincia = loc.getProvincia().getId();
				Provincia prov = provinciaDAO.get(idProvincia);
				listaLocalidades = localidadDAO.getLista(prov);
				listaCuotasImpagas = new ArrayList<CuotaImpaga>();
				listaMatriculasImpagas = new ArrayList<MatriculaImpaga>();
				listaCuotasImpagas = cuotaImpagaDAO.getLista(alumno);
				listaMatriculasImpagas = matriculaImpagaDAO.getLista(alumno);
				if (!listaCuotasImpagas.isEmpty() || !listaMatriculasImpagas.isEmpty()) {
					deuda = true;
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL ALUMNO POSEE DEUDA ANTERIORES, "
							+ "PARA VERLAS PRESIONE SOBRE 'DEUDAS ANTERIORES'.", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		} else {
			alumno = new Alumno();
			domicilio = new Domicilio();
			listaLocalidades = new ArrayList<Localidade>();
			idProvincia = 0;
			idLocalidad = 0;
		}
	}
	
	public void onChangeProvincia() {
		listaLocalidades = new ArrayList<Localidade>();
		if (idProvincia != 0) {
			Provincia prov = new Provincia();
			prov = provinciaDAO.get(idProvincia);
			listaLocalidades = localidadDAO.getLista(prov);
		}
	}
	
	public void onChangeCurso() {
		matricula = new Matricula();
		montoCurso = 0;
		montoMatricula = 0;
		cantCuotas = 0;
		montoCuota = 0;
		listaMatriculas = new ArrayList<Matricula>();
		if (idCurso != 0) {
			curso = new Curso();
			curso = cursoDAO.get(idCurso);
			idMatricula = 0;
			listaMatriculas = matriculaDAO.getLista(true, curso);
		}
	}
	
	public void onChangeMatricula() {
		matricula = new Matricula();
		montoCurso = 0;
		montoMatricula = 0;
		cantCuotas = 0;
		montoCuota = 0;
		if (idMatricula != 0) {
			try {
				matricula = matriculaDAO.get(idMatricula);
				if (matricula.getId() != 0) {
					montoCurso = matricula.getCostoCurso();
					montoMatricula = matricula.getCosto();
					cantCuotas = 0 + curso.getDuracionMeses();
					montoCuota = montoCurso / cantCuotas;
				} else {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL OBTENER LOS DATOS DE LA MATRICULA.", null));
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL OBTENER LOS DATOS DE LA MATRICULA. Error: " + e.getMessage(), null));
			}						
		}
	}
	
	public void onBlurDescuentoCurso() {
		float monto = (matricula.getCostoCurso() * descuentoCurso) / 100;
		float montoC = matricula.getCostoCurso();
		montoC = montoC - monto;
		montoCurso = montoC;
		if(cantCuotas == 0) {
			cantCuotas = 1;
		}
		montoCuota = montoCurso / cantCuotas;
	}
	
	public void onBlurDescuentoMatricula() {
		if (descuentoMatricula != 0) {
			float monto = (matricula.getCosto() * descuentoMatricula) / 100;
			float montoC = matricula.getCosto();
			montoC = montoC - monto;
			montoMatricula = montoC;
		}
	}
	
	public void bajaInscripcion(Inscripcione inscri) {
		try {
			FacesMessage msg = null;
			Matricula matr = matriculaDAO.get(inscri.getMatricula().getId());
			Alumno alum = alumnoDAO.getPorDni(inscri.getDni());
			Curso cur = cursoDAO.get(inscri.getCurso().getId());
											
			MatriculaAlumno matrAlumno = matriculaAlumnoDAO.get(alum, cur, matr);
			//Valido si Existen Pagos
			boolean validaPago = true;
			List<Cuota> listCuots = cuotaDAO.getLista(alum, matr, cur);
			for (Cuota cuot : listCuots) {
				if (cuot.getPaga()) {
					validaPago = false;
				}
			}
						
			if (matrAlumno.getPago()) {
				validaPago = false;
			}
			
			if (validaPago) {
				boolean actCuota = true;
				//Baja Cuota
				for (Cuota cuot : listCuots) {
					cuot.setEnabled(false);
					cuot.setFechaBaja(new Date());
					cuot.setUsuario2(usuario);
					int updtCuot = cuotaDAO.update(cuot);
					if (updtCuot == 0) {
						actCuota = false;
					}
				}
				
				//Baja MatriculaAlumno						
				matrAlumno.setEliminado(true);
				matrAlumno.setFechaBaja(new Date());			
				matrAlumno.setUsuario2(usuario);			
				int updtMatrAlumno = matriculaAlumnoDAO.update(matrAlumno);			
						
				inscri.setEnabled(false);
				inscri.setUsuario2(usuario);
				inscri.setFechaBaja(new Date());
				int updtInscri = inscripcionDAO.update(inscri);
				if (updtInscri != 0 && updtMatrAlumno != 0 && actCuota) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE INSCRIPCION EXITOSA!", null);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR EN LA BAJA DE LA INSCRIPCION, "
							+ "INTÉNTELO NUEVAMENTE", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "LA INSCRIPCIÓN POSEE PAGOS! NO ES POSIBLE DAR DE BAJA! "
						+ "CONTÁCTESE CON EL ADMINISTRADOR", null);
			}			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL REGISTRAR LA BAJA DE LA INSCRIPCION! "
					+ "ERROR ORIGINAL: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void bajaCurso(Inscripcione inscri) {
		try {
			FacesMessage msg = null;
			Matricula matr = matriculaDAO.get(inscri.getMatricula().getId());
			Alumno alum = alumnoDAO.getPorDni(inscri.getDni());
			Curso cur = cursoDAO.get(inscri.getCurso().getId());
											
			MatriculaAlumno matrAlumno = matriculaAlumnoDAO.get(alum, cur, matr);
			
			//Baja Cuota
			boolean actCuota = true;
			List<Cuota> listCuots = cuotaDAO.getLista(alum, matr, cur);
			for (Cuota cuot : listCuots) {
				if (!cuot.getPaga()) {
					CuotaImpaga cuotaImpaga = new CuotaImpaga();
					cuotaImpaga.setAlumno(alum);
					cuotaImpaga.setCuota(cuot);
					cuotaImpaga.setCurso(cur);
					cuotaImpaga.setDetalle(cuot.getDetalle());
					cuotaImpaga.setFechaAlta(new Date());
					cuotaImpaga.setFechaVencimiento(cuot.getFechaVencimiento());
					cuotaImpaga.setMatricula(matr);
					cuotaImpaga.setMatriculaAlumno(matrAlumno);
					cuotaImpaga.setMonto(cuot.getMonto());
					cuotaImpaga.setSegundoVencimiento(cuot.getSegundoVencimiento());
					cuotaImpaga.setUsuario(usuario);
					cuotaImpagaDAO.insertar(cuotaImpaga);
				}
				cuot.setEnabled(false);
				cuot.setFechaBaja(new Date());
				cuot.setUsuario2(usuario);
				int updtCuot = cuotaDAO.update(cuot);
				
				if (updtCuot == 0) {
					actCuota = false;
				}
			}
			
			//Baja MatriculaAlumno
			if (!matrAlumno.getPago()) {
				MatriculaImpaga matriculaImpaga = new MatriculaImpaga();
				matriculaImpaga.setAlumno(alum);
				matriculaImpaga.setCurso(cur);
				matriculaImpaga.setFechaAlta(new Date());
				matriculaImpaga.setMatricula(matr);
				matriculaImpaga.setMatriculaAlumno(matrAlumno);
				matriculaImpaga.setMonto(matr.getCosto());
				matriculaImpaga.setUsuario(usuario);
				matriculaImpagaDAO.insertar(matriculaImpaga);
			}			
			matrAlumno.setEliminado(true);
			matrAlumno.setFechaBaja(new Date());			
			matrAlumno.setUsuario2(usuario);			
			int updtMatrAlumno = matriculaAlumnoDAO.update(matrAlumno);			
			
			//Baja Inscripcion		
			inscri.setEnabled(false);
			inscri.setUsuario2(usuario);
			inscri.setFechaBaja(new Date());
			int updtInscri = inscripcionDAO.update(inscri);
			if (updtInscri != 0 && updtMatrAlumno != 0 && actCuota) {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE INSCRIPCION EXITOSA!", null);
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR EN LA BAJA DE LA INSCRIPCION, "
						+ "INTÉNTELO NUEVAMENTE", null);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL REGISTRAR LA BAJA DE LA INSCRIPCION! "
					+ "ERROR ORIGINAL: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void alta(Inscripcione inscri) {
		FacesMessage msg = null;
		inscri.setEnabled(true);
		inscri.setUsuario1(usuario);
		inscri.setFechaAlta(new Date());
		int idInscri = inscripcionDAO.update(inscri);
		if (idInscri != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ALTA DE INSCRIPCION EXITOSA!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR EN EL ALTA DE LA INSCRIPCION, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void verInscripcion(Inscripcione inscri) {
		inscripcion = new Inscripcione();
		inscripcionReporte = new InscripcionReporte();
		inscripcion = inscri;
		Matricula matri = inscri.getMatricula();
		PlanPago planP = planPagoDAO.get(inscri, matri);
		List<InscripcionDia> listaInscripcionDias = inscripcionDiaDAO.getLista(inscri);
		inscripcionReporte.setProvincia(inscri.getProvincia().getNombre());
		inscripcionReporte.setLocalidad(inscri.getLocalidade().getNombre());
		inscripcionReporte.setCalle(inscri.getDomicilio().getCalle());
		inscripcionReporte.setCod_postal(inscri.getDomicilio().getCodigoPostal());
		inscripcionReporte.setDepartamento(inscri.getDomicilio().getDepartamento());
		inscripcionReporte.setNumero(inscri.getDomicilio().getNumero());
		inscripcionReporte.setPiso(inscri.getDomicilio().getPiso());
		inscripcionReporte.setApellido(inscri.getApellido());
		inscripcionReporte.setDni(Integer.toString(inscri.getDni()));
		inscripcionReporte.setFecha_nacimiento(inscri.getFechaNacimiento());
		inscripcionReporte.setNombre(inscri.getNombre());
		inscripcionReporte.setTelcel(inscri.getTelefonoCel());
		inscripcionReporte.setTelfijo(inscri.getTelefonoFijo());
		inscripcionReporte.setEmail(inscri.getEmail());
		inscripcionReporte.setCant_cuotas(Integer.toString(planP.getCantCuotas()));
		inscripcionReporte.setCosto_cuota(planP.getMontoCuota());
		float cstoCurso = planP.getCantCuotas() * planP.getMontoCuota();
		inscripcionReporte.setCosto_curso(cstoCurso);
		inscripcionReporte.setCurso(inscri.getCurso().getNombre());
		inscripcionReporte.setMatriculaDesc(inscri.getMatricula().getDescripcion());
		inscripcionReporte.setFecha(inscri.getFecha());
		inscripcionReporte.setHorario1(inscri.getHoraCursado1());
		inscripcionReporte.setHorario2(inscri.getHoraCursado2());
		inscripcionReporte.setMatricula(planP.getMontoMatricula());
		inscripcionReporte.setPrimerVencimiento(planP.getPrimerVencimiento());
		inscripcionReporte.setListaInscripcionDias(listaInscripcionDias);
	}
	
	public void verPlanPago(Inscripcione inscri) {
		planPago = new PlanPago();
		matricula = new Matricula();
		inscripcion = new Inscripcione();
		curso = new Curso();		
		editPP = false;
		editPD = false;
		guardarPP = false;
		guardarPD = false;
		editPPM = false;
		editPPC = false;
		editPDC = false;
		descuentoMatricula = 0;
		montoMatricula = 0;
		cantCuotas = 0;
		primerVencimiento = null;
		descuentoCurso = 0;
		montoCurso = 0;
		montoCuota = 0;
		horaCursado1 = null;
		horaCursado2 = null;
		selectedDias = new ArrayList<String>();
		
		planPago = planPagoDAO.get(inscri, inscri.getMatricula());
		matricula = inscri.getMatricula();
		curso = inscri.getCurso();		
		inscripcion = inscri;		
		
		descuentoMatricula = planPago.getDescuentoMatricula();
		montoMatricula = planPago.getMontoMatricula();
		cantCuotas = planPago.getCantCuotas();
		primerVencimiento = planPago.getPrimerVencimiento();
		descuentoCurso = planPago.getDescuentoCurso();
		montoCurso = planPago.getCantCuotas() * planPago.getMontoCuota();
		montoCuota = planPago.getMontoCuota();
		
		horaCursado1 = inscri.getHoraCursado1();
		horaCursado2 = inscri.getHoraCursado2();
		List<InscripcionDia> listaInscripcionDias = inscripcionDiaDAO.getLista(inscri);
		for (InscripcionDia inscripcionDia : listaInscripcionDias) {
			String dia = inscripcionDia.getDia();
			selectedDias.add(dia);
		}
	}
	
	public void editarPlanPago() {
		Alumno alum = alumnoDAO.getPorDni(inscripcion.getDni());
		
		MatriculaAlumno matriculaAlumno = matriculaAlumnoDAO.get(alum, curso, matricula);	
		if (!matriculaAlumno.getPago()) {
			editPPM = true;
		}		
		editPPC = true;
		List<Cuota> listCuots = cuotaDAO.getLista(alum, matricula, curso);
		for (Cuota cuot : listCuots) {	
			if (cuot.getPaga()) {
				editPPC = false;
			}
		}
		if (!editPPM && !editPPC) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "NO ES POSIBLE EDITAR EL PLAN DE PAGO!"
					+ " POSEE MATRICULA PAGA Y CUOTAS!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			editPP = true;
			editPD = true;
			guardarPP = true;
		}		
	}
	
	public void editarHorarios() {
		editPDC = true;
		editPD = true;
		editPP = true;
		guardarPD = true;
	}
	
	public void guardarEdicionPlanPago() {
		try {
			//Baja de actual
			planPagoDAO.delete(planPago);		
			Alumno alum = alumnoDAO.getPorDni(inscripcion.getDni());		
			MatriculaAlumno matriculaAlumno = matriculaAlumnoDAO.get(alum, curso, matricula);				
			boolean actCuota = true;
			if (editPPC) {				
				List<Cuota> listCuots = cuotaDAO.getLista(alum, matricula, curso);
				for (Cuota cuot : listCuots) {			
					cuot.setEnabled(false);
					cuot.setFechaBaja(new Date());
					cuot.setUsuario2(usuario);
					int updtCuot = cuotaDAO.update(cuot);
					if (updtCuot == 0) {
						actCuota = false;
					}
				}
			}
			
			int updtMatrAlumno = 1;
			if (editPPM) {
				matriculaAlumno.setEliminado(true);
				matriculaAlumno.setFechaBaja(new Date());			
				matriculaAlumno.setUsuario2(usuario);			
				updtMatrAlumno = matriculaAlumnoDAO.update(matriculaAlumno);
			}		
			
			//Alta de nuevo			
			PlanPago planP = new PlanPago();
			MatriculaAlumno matriculaA = new MatriculaAlumno();
			
			planP.setCantCuotas(cantCuotas);
			planP.setDescuentoCurso(descuentoCurso);
			planP.setDescuentoMatricula(descuentoMatricula);
			planP.setInscripcione(inscripcion);
			planP.setMatricula(matricula);
			planP.setMontoCuota(montoCuota);
			planP.setMontoMatricula(montoMatricula);
			planP.setPrimerVencimiento(primerVencimiento);
			int idPlanPago = planPagoDAO.insertar(planP);
			
			int idMatriculaAlum = 1;
			if (editPPM) {
				matriculaA.setAlumno(alum);
				matriculaA.setCurso(curso);
				matriculaA.setEliminado(false);
				matriculaA.setEnabled(true);
				matriculaA.setFechaAlta(inscripcion.getFecha());
				matriculaA.setMatricula(matricula);
				matriculaA.setMontoPago(montoMatricula);
				matriculaA.setPago(false);
				matriculaA.setUsuario1(usuario);
				idMatriculaAlum = matriculaAlumnoDAO.insertar(matriculaA);			
			}
			
			boolean insertCuota = true;
			if (editPPC) {
				Parametro param = parametroDAO.get(1);
				float porcentajePV = param.getPorcentajePrimerVencimiento();
				float porcentajeSV = param.getPorcentajeSegundoVencimiento();
				String diasPrimerV = "";
				String diasSegundV = "";
				if (param.getDiasPrimerVencimiento() >= 10) {
					diasPrimerV = Integer.toString(param.getDiasPrimerVencimiento());
				} else {
					diasPrimerV = "0" + Integer.toString(param.getDiasPrimerVencimiento());
				}
				if (param.getDiasSegundoVencimiento() >= 10) {
					diasSegundV = Integer.toString(param.getDiasSegundoVencimiento());
				} else {
					diasSegundV = "0" + Integer.toString(param.getDiasSegundoVencimiento());
				}
				SimpleDateFormat formatoInicio = new SimpleDateFormat("MMyyyy");
				String mesanio = formatoInicio.format(primerVencimiento);						
				SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
				String priVencimiento = diasPrimerV + mesanio;
				String segVencimiento = diasSegundV + mesanio;						
				for (int i = 1; i <= cantCuotas; i++) {
					Date fechaVenc;
					Date segunVenc;
					fechaVenc = formato.parse(priVencimiento);
					segunVenc = formato.parse(segVencimiento);
					Cuota cuota = new Cuota();
					cuota.setAlumno(alum);
					cuota.setCurso(curso);
					cuota.setDetalle("Cuota " + i);
					cuota.setEnabled(true);
					cuota.setFechaAlta(new Date());
					cuota.setFechaVencimiento(fechaVenc);
					cuota.setMatricula(matricula);
					cuota.setMonto(montoCuota);
					cuota.setPaga(false);
					cuota.setPorcentajePv(porcentajePV);
					cuota.setPorcentajeSv(porcentajeSV);
					cuota.setSegundoVencimiento(segunVenc);
					cuota.setUsuario1(usuario);
					int idCuot = cuotaDAO.insertar(cuota);
					if (idCuot == 0) {
						insertCuota = false;
					}
					if (i < cantCuotas) {
						SimpleDateFormat formatoDiaSig = new SimpleDateFormat("dd");
						SimpleDateFormat formatoMesSig = new SimpleDateFormat("MM");
						SimpleDateFormat formatoAnioSig = new SimpleDateFormat("yyyy");
						int diaSig = Integer.parseInt(formatoDiaSig.format(fechaVenc));
						int mesSig = Integer.parseInt(formatoMesSig.format(fechaVenc));
						String daySig = "";
						String monthSig = "";
						String anioSig = formatoAnioSig.format(fechaVenc);
						mesSig = mesSig + 1;
						if (diaSig < 10) {
							daySig = "0" + Integer.toString(diaSig);
						} else {
							daySig = Integer.toString(diaSig);
						}
						if (mesSig < 10) {
							monthSig = "0" + Integer.toString(mesSig);
						} else {
							monthSig = Integer.toString(mesSig);
						}
						priVencimiento = daySig + monthSig + anioSig;
						segVencimiento = diasSegundV + monthSig + anioSig;
					}
				}
			}
			if (actCuota && updtMatrAlumno != 0 && idPlanPago != 0 && idMatriculaAlum != 0 && insertCuota) {
				listaInscripciones = new ArrayList<Inscripcione>();
				filteredInscripciones = new ArrayList<Inscripcione>();
				listaInscripciones = inscripcionDAO.getLista(true);
				filteredInscripciones = listaInscripciones;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SE REGISTRARON LOS CAMBIOS CON EXITO!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL EDITAR! "
						+ "CONTÁCTESE CON EL ADMINISTRADOR!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL EDITAR! ERROR: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void guardarEdicionHorarios() {
		try {
			//Baja de actual
			inscripcionDiaDAO.delete(inscripcion);	
			
			//Alta de nuevo
			for (String dia : selectedDias) {
				InscripcionDia inscripcionDia = new InscripcionDia();
				inscripcionDia.setDia(dia);
				inscripcionDia.setInscripcione(inscripcion);
				inscripcionDiaDAO.insertar(inscripcionDia);
			}
			inscripcion.setHoraCursado1(horaCursado1);
			inscripcion.setHoraCursado2(horaCursado2);
			int updtInscrip = inscripcionDAO.update(inscripcion);
			
			if (updtInscrip != 0) {
				listaInscripciones = new ArrayList<Inscripcione>();
				filteredInscripciones = new ArrayList<Inscripcione>();
				listaInscripciones = inscripcionDAO.getLista(true);
				filteredInscripciones = listaInscripciones;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SE REGISTRARON LOS CAMBIOS CON EXITO!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL EDITAR! "
						+ "CONTÁCTESE CON EL ADMINISTRADOR!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL EDITAR! ERROR: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void cancelarEdicionPlanPago() {
		editPP = false;
		editPD = false;
		editPPM = false;
		editPPC = false;
		guardarPP = false;
		guardarPD = false;
	}
	
	public void cancelarEdicionHorarios() {
		editPDC = false;
		editPD = false;
		editPP = false;
		guardarPP = false;
		guardarPD = false;
	}
	
	public String guardar() {
		try {
			FacesMessage msg = null;
			String retorno = "";
			String nombreCompleto = "";
			boolean existeEmail = true;
			if (alumno.getId() == 0 && (!alumno.getEmail().isEmpty() || alumno.getEmail() != "")) {
				Usuario u = usuarioDAO.get(alumno.getEmail());
				if (u.getId() != 0) {
					existeEmail = true;
				} else {
					existeEmail = false;
				}
			} else {
				existeEmail = false;
			}
			if (inscripcion.getFecha() != null && idCurso != 0 && idMatricula != 0 && dni != 0 && alumno.getFechaNacimiento() != null && primerVencimiento != null
					&& (!alumno.getApellido().isEmpty() || alumno.getApellido() != "") && (!alumno.getNombres().isEmpty() || alumno.getNombres() != "") 
					&& idProvincia != 0 && idLocalidad != 0	&& (!domicilio.getCodigoPostal().isEmpty() || domicilio.getCodigoPostal() != "") 
					&& (!domicilio.getCalle().isEmpty() || domicilio.getCalle() != "") && (!domicilio.getNumero().isEmpty() || domicilio.getNumero() != "")
					&& (!alumno.getTelefonoCel().isEmpty() || !alumno.getTelefonoFijo().isEmpty()) && !existeEmail) {
				Localidade loc = new Localidade();
				Provincia prov = new Provincia();
				Curso cur = new Curso();		
				inscripcionReporte = new InscripcionReporte();
				if (alumno.getId() != 0) {	
					loc = localidadDAO.get(idLocalidad);
					prov = provinciaDAO.get(idProvincia);
					domicilio.setLocalidade(loc);
					domicilio.setUsuario3(usuario);
					domicilio.setFechaMod(new Date());
					int updateDom = domicilioDAO.update(domicilio);
					domicilio.setId(updateDom);
					nombreCompleto = alumno.getApellido() + ", " + alumno.getNombres();
					alumno.setDomicilio(domicilio);
					alumno.setNombreCompleto(nombreCompleto);
					alumno.setUsuario4(usuario);
					alumno.setFechaMod(new Date());
					int updateAlum = alumnoDAO.update(alumno);
					alumno.setId(updateAlum);
				} else {					
					Role rol = new Role();
					rol.setId(_ROL);
					loc = localidadDAO.get(idLocalidad);
					prov = provinciaDAO.get(idProvincia);
					domicilio.setLocalidade(loc);
					domicilio.setEnabled(true);
					domicilio.setFechaAlta(new Date());
					domicilio.setUsuario1(usuario);
					int idDomicilio = domicilioDAO.insertar(domicilio);
					domicilio.setId(idDomicilio);
					nombreCompleto = alumno.getApellido() + ", " + alumno.getNombres();
					alumno.setDomicilio(domicilio);
					alumno.setEnabled(true);
					alumno.setFechaAlta(new Date());
					alumno.setNombreCompleto(nombreCompleto);
					alumno.setUsuario2(usuario);					
					int idAlumno = alumnoDAO.insertar(alumno);
					if (idAlumno != 0) {
						alumno.setId(idAlumno);
						
					}
					if (!alumno.getEmail().isEmpty() || alumno.getEmail() != ""){
						Usuario user = new Usuario();
						user.setNombreCompleto(nombreCompleto);
//						String username = alumno.getNombres().substring(0,1) + alumno.getApellido().substring(0,1);
//						username = username.toLowerCase() + Integer.toString(alumno.getDni());
//						System.out.println(username);
						user.setUsername(alumno.getEmail());
						user.setPassword(_PASSWORD);
						user.setNombre(alumno.getNombres());
						user.setApellido(alumno.getApellido());
						user.setEmail(alumno.getEmail());
						user.setUsuario1(usuario);
						user.setFechaAlta(new Date());
						user.setEnabled(false);
						user.setRole(rol);
						user.setAlumno(alumno);
						int idUser = usuarioDAO.insertar(user);
						user.setId(idUser);
					}
				}
				cur = cursoDAO.get(idCurso);
				Matricula matri = matriculaDAO.get(idMatricula);
				MatriculaAlumno matriAlum = matriculaAlumnoDAO.get(alumno, cur, matri);
				if (matriAlum.getId() == 0) {
					inscripcion.setCurso(cur);
					inscripcion.setMatricula(matri);
					inscripcion.setApellido(alumno.getApellido());
					inscripcion.setNombre(alumno.getNombres());
					inscripcion.setNombreCompleto(nombreCompleto); 
					inscripcion.setDni(alumno.getDni());
					inscripcion.setDomicilio(domicilio);
					inscripcion.setEmail(alumno.getEmail());
					inscripcion.setEnabled(true);
					inscripcion.setFechaNacimiento(alumno.getFechaNacimiento());
					inscripcion.setFechaAlta(new Date());
					inscripcion.setLocalidade(loc);
					inscripcion.setProvincia(prov);
					inscripcion.setTelefonoCel(alumno.getTelefonoCel());
					inscripcion.setTelefonoFijo(alumno.getTelefonoFijo());
					inscripcion.setUsuario1(usuario);
					inscripcion.setValida(false);
					int idInscripcion = inscripcionDAO.insertar(inscripcion);
					if (idInscripcion != 0) {
						inscripcion.setId(idInscripcion);
						planPago.setCantCuotas(cantCuotas);
						planPago.setDescuentoCurso(descuentoCurso);
						planPago.setDescuentoMatricula(descuentoMatricula);
						planPago.setInscripcione(inscripcion);
						planPago.setMatricula(matricula);
						planPago.setMontoCuota(montoCuota);
						planPago.setMontoMatricula(montoMatricula);
						planPago.setPrimerVencimiento(primerVencimiento);
						int idPlanPago = planPagoDAO.insertar(planPago);
						MatriculaAlumno matriculaAlumno = new MatriculaAlumno();
						matriculaAlumno.setAlumno(alumno);
						matriculaAlumno.setCurso(cur);
						matriculaAlumno.setEliminado(false);
						matriculaAlumno.setEnabled(true);
						matriculaAlumno.setFechaAlta(inscripcion.getFecha());
						matriculaAlumno.setMatricula(matri);
						matriculaAlumno.setMontoPago(montoMatricula);
						matriculaAlumno.setPago(false);
						matriculaAlumno.setUsuario1(usuario);
						int idMatriculaAlum = matriculaAlumnoDAO.insertar(matriculaAlumno);
						boolean insertCuota = true;
						
//						Date inicioMatri = matri.getFechaAlta();
						Parametro param = parametroDAO.get(1);
						float porcentajePV = param.getPorcentajePrimerVencimiento();
						float porcentajeSV = param.getPorcentajeSegundoVencimiento();
						String diasPrimerV = "";
						String diasSegundV = "";
						if (param.getDiasPrimerVencimiento() >= 10) {
							diasPrimerV = Integer.toString(param.getDiasPrimerVencimiento());
						} else {
							diasPrimerV = "0" + Integer.toString(param.getDiasPrimerVencimiento());
						}
						if (param.getDiasSegundoVencimiento() >= 10) {
							diasSegundV = Integer.toString(param.getDiasSegundoVencimiento());
						} else {
							diasSegundV = "0" + Integer.toString(param.getDiasSegundoVencimiento());
						}
						SimpleDateFormat formatoInicio = new SimpleDateFormat("MMyyyy");
						String mesanio = formatoInicio.format(primerVencimiento);
//						System.out.println(mesanio);						
						SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
//						String priVencimiento = formato.format(primerVencimiento);
						String priVencimiento = diasPrimerV + mesanio;
						String segVencimiento = diasSegundV + mesanio;						
						for (int i = 1; i <= cantCuotas; i++) {
							Date fechaVenc;
							Date segunVenc;
							fechaVenc = formato.parse(priVencimiento);
							segunVenc = formato.parse(segVencimiento);
							Cuota cuota = new Cuota();
							cuota.setAlumno(alumno);
							cuota.setCurso(cur);
							cuota.setDetalle("Cuota " + i);
							cuota.setEnabled(true);
							cuota.setFechaAlta(new Date());
							cuota.setFechaVencimiento(fechaVenc);
							cuota.setMatricula(matri);
							cuota.setMonto(montoCuota);
							cuota.setPaga(false);
							cuota.setPorcentajePv(porcentajePV);
							cuota.setPorcentajeSv(porcentajeSV);
							cuota.setSegundoVencimiento(segunVenc);
							cuota.setUsuario1(usuario);
							int idCuot = cuotaDAO.insertar(cuota);
							if (idCuot == 0) {
								insertCuota = false;
							}
							if (i < cantCuotas) {
								SimpleDateFormat formatoDiaSig = new SimpleDateFormat("dd");
								SimpleDateFormat formatoMesSig = new SimpleDateFormat("MM");
								SimpleDateFormat formatoAnioSig = new SimpleDateFormat("yyyy");
								int diaSig = Integer.parseInt(formatoDiaSig.format(fechaVenc));
								int mesSig = Integer.parseInt(formatoMesSig.format(fechaVenc));
								String daySig = "";
								String monthSig = "";
								String anioSig = formatoAnioSig.format(fechaVenc);
								mesSig = mesSig + 1;
								if (diaSig < 10) {
									daySig = "0" + Integer.toString(diaSig);
								} else {
									daySig = Integer.toString(diaSig);
								}
								if (mesSig < 10) {
									monthSig = "0" + Integer.toString(mesSig);
								} else {
									monthSig = Integer.toString(mesSig);
								}
								priVencimiento = daySig + monthSig + anioSig;
								segVencimiento = diasSegundV + monthSig + anioSig;
							}
						}
						for (String idMotivo : selectedMotivos) {
							Motivo motivo = motivoDAO.get(Integer.parseInt(idMotivo));
							InscripcionMotivo inscripcionMotivo = new InscripcionMotivo();
							inscripcionMotivo.setMotivo(motivo);
							inscripcionMotivo.setInscripcione(inscripcion);
							inscripcionMotivoDAO.insertar(inscripcionMotivo);
						}
						for (String dia : selectedDias) {
							InscripcionDia inscripcionDia = new InscripcionDia();
							inscripcionDia.setDia(dia);
							inscripcionDia.setInscripcione(inscripcion);
							inscripcionDiaDAO.insertar(inscripcionDia);
						}
						//Paso de valores para reporte
						inscripcionReporte.setProvincia(prov.getNombre());
						inscripcionReporte.setLocalidad(loc.getNombre());
						inscripcionReporte.setCalle(domicilio.getCalle());
						inscripcionReporte.setCod_postal(domicilio.getCodigoPostal());
						inscripcionReporte.setDepartamento(domicilio.getDepartamento());
						inscripcionReporte.setNumero(domicilio.getNumero());
						inscripcionReporte.setPiso(domicilio.getPiso());
						inscripcionReporte.setApellido(alumno.getApellido());
						inscripcionReporte.setDni(Integer.toString(alumno.getDni()));
						inscripcionReporte.setFecha_nacimiento(alumno.getFechaNacimiento());
						inscripcionReporte.setNombre(alumno.getNombres());
						inscripcionReporte.setTelcel(alumno.getTelefonoCel());
						inscripcionReporte.setTelfijo(alumno.getTelefonoFijo());
						inscripcionReporte.setEmail(alumno.getEmail());
						inscripcionReporte.setCant_cuotas(Integer.toString(cantCuotas));
						inscripcionReporte.setCosto_cuota(montoCuota);
						inscripcionReporte.setCosto_curso(montoCurso);
						inscripcionReporte.setCurso(cur.getNombre());
						inscripcionReporte.setFecha(inscripcion.getFecha());
						inscripcionReporte.setHorario1(inscripcion.getHoraCursado1());
						inscripcionReporte.setHorario2(inscripcion.getHoraCursado2());
						inscripcionReporte.setMatricula(montoMatricula);
						inscripcionReporte.setMatriculaDesc(matri.getDescripcion());
						String formatoPV = diasPrimerV + mesanio;
						Date primerV = formato.parse(formatoPV);
						inscripcionReporte.setPrimerVencimiento(primerV);
						inscripcionReporte.setListaDias(selectedDias);
						if (idPlanPago != 0 && insertCuota && idMatriculaAlum != 0) {
							msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LA INSCRIPCION SE REGISTRO CORRECTAMENTE", null);
							goInscripcion(usuario);
							retorno = "inscripcioncomprob";
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "LA INSCRIPCION Y LAS ASISTENCIAS SE REGISTRARON CORRECTAMENTE, "
									+ "OCURRIO UN ERROR AL REGISTRAR EL PLAN DE PAGO", null);
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIO UN ERROR AL REGISTRAR LA INSCRIPCION", null);
					}				
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "EL ALUMNO YA SE ENCUENTRA REGISTRADO EN EL CURSO!", null);
				}	
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				String mensaje = "";
				if (inscripcion.getFecha() == null) {
					mensaje = mensaje + " FECHA DE INSCRIPCION!";
				}
				if (idCurso == 0) {
					mensaje = mensaje + " CURSO!";
				}
				if (idMatricula == 0) {
					mensaje = mensaje + " MATRICULA!";
				}
				if (dni == 0) {
					mensaje = mensaje + " DNI DE ALUMNO!";
				}
				if (alumno.getFechaNacimiento() == null) {
					mensaje = mensaje + " FECHA DE NACIMIENTO!";
				}
				if (alumno.getApellido().isEmpty() || alumno.getApellido() == "") {
					mensaje = mensaje + " APELLIDO!";
				}
				if (alumno.getNombres().isEmpty() || alumno.getNombres() == "") {
					mensaje = mensaje + " NOMBRES!";
				}
				if (idProvincia == 0) {
					mensaje = mensaje + " PROVINCIA!";
				}
				if (idLocalidad == 0) {
					mensaje = mensaje + " LOCALIDAD!";
				}
				if (domicilio.getCodigoPostal().isEmpty() || domicilio.getCodigoPostal() == "") {
					mensaje = mensaje + " CÓDIGO POSTAL!";
				}
				if (domicilio.getCalle().isEmpty() || domicilio.getCalle() == "") {
					mensaje = mensaje + " CALLE!";
				}
				if (domicilio.getNumero().isEmpty() || domicilio.getNumero() == "") {
					mensaje = mensaje + " NÚMERO DE CALLE!";
				}
				if ((alumno.getTelefonoCel().isEmpty() || alumno.getTelefonoCel() == "") 
						&& (alumno.getTelefonoFijo().isEmpty() || alumno.getTelefonoFijo() == "")) {
					mensaje = mensaje + " TELEFONO CEL O TELEFONO FIJO!";
				}
				if ((alumno.getEmail().isEmpty() || alumno.getEmail() == "") 
						&& (alumno.getTelefonoFijo().isEmpty() || alumno.getTelefonoFijo() == "")) {
					mensaje = mensaje + " E-MAIL!";
				}
				if (primerVencimiento == null) {
					mensaje = mensaje + " PRIMER VENCIMIENTO!";
				}
				if (existeEmail) {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "YA EXISTE USUARIO CON ESE CORREO ELECTRÓNICO, POR FAVOR COLOQUE UNO DISTINTO!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "LOS PARAMETROS" + mensaje + " SON OBLIGATORIOS PARA UNA INSCRIPCIÓN", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}				
			}		
			return retorno;
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LA INSCRIPCION, ERROR: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public String cancelar() {
		goInscripcion(usuario);
		return "inscripcion";
	}
	
	public void filtroInscriptos() {
		listaInscriptos = new ArrayList<Inscripto>();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		if (idCurso == 0 && idMatricula == 0) {
			List<Inscripcione> listInscrips = inscripcionDAO.getListaOrderByAlumno(true);
			for (Inscripcione inscrip : listInscrips) {
				Inscripto inscripto = new Inscripto();
				Alumno alum = alumnoDAO.getPorDni(inscrip.getDni());
				if (alum.getId() != 0) {
					inscripto.setDni(inscrip.getDni());
					inscripto.setEmail(alum.getEmail());
					inscripto.setNombreCompleto(alum.getNombreCompleto());
					inscripto.setTelefonoCel(alum.getTelefonoCel());
					inscripto.setTelefonoFijo(alum.getTelefonoFijo());
					inscripto.setFechaNacimiento(formatoFecha.format(alum.getFechaNacimiento()));
					listaInscriptos.add(inscripto);
				}				
			}
		}
		if (idCurso != 0 && idMatricula == 0) {
			Curso cur = cursoDAO.get(idCurso);
			List<Inscripcione> listInscrips = inscripcionDAO.getListaOrderByAlumno(true, cur);
			for (Inscripcione inscrip : listInscrips) {
				Inscripto inscripto = new Inscripto();
				Alumno alum = alumnoDAO.getPorDni(inscrip.getDni());
				if (alum.getId() != 0) {
					inscripto.setDni(inscrip.getDni());
					inscripto.setEmail(alum.getEmail());
					inscripto.setNombreCompleto(alum.getNombreCompleto());
					inscripto.setTelefonoCel(alum.getTelefonoCel());
					inscripto.setTelefonoFijo(alum.getTelefonoFijo());
					inscripto.setFechaNacimiento(formatoFecha.format(alum.getFechaNacimiento()));
					listaInscriptos.add(inscripto);
				}				
			}
		}
		if (idCurso != 0 && idMatricula != 0) {
			Curso cur = cursoDAO.get(idCurso);
			Matricula mat = matriculaDAO.get(idMatricula);
			List<Inscripcione> listInscrips = inscripcionDAO.getListaOrderByAlumno(true, cur, mat);
			for (Inscripcione inscrip : listInscrips) {
				Inscripto inscripto = new Inscripto();
				Alumno alum = alumnoDAO.getPorDni(inscrip.getDni());
				if (alum.getId() != 0) {
					inscripto.setDni(inscrip.getDni());
					inscripto.setEmail(alum.getEmail());
					inscripto.setNombreCompleto(alum.getNombreCompleto());
					inscripto.setTelefonoCel(alum.getTelefonoCel());
					inscripto.setTelefonoFijo(alum.getTelefonoFijo());
					inscripto.setFechaNacimiento(formatoFecha.format(alum.getFechaNacimiento()));
					listaInscriptos.add(inscripto);
				}				
			}
		}
	}
	
	public void filtroInscripciones() {
		listaInscripciones = new ArrayList<Inscripcione>();
		if (idCurso == 0 && idMatricula == 0) {
			listaInscripciones = inscripcionDAO.getLista(true);
		}		
		if (idCurso != 0 && idMatricula == 0) {
			Curso cur = cursoDAO.get(idCurso);
			listaInscripciones = inscripcionDAO.getListaOrderByFechaId(true, cur);
		}
		if (idCurso != 0 && idMatricula != 0) {
			Curso cur = cursoDAO.get(idCurso);
			Matricula mat = matriculaDAO.get(idMatricula);
			listaInscripciones = inscripcionDAO.getListaOrderByFechaId(true, cur, mat);
		}
	}
	
	public void generarReporte() {
		Reporte reporte = new Reporte();
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<InscripcionReporte> lista = new ArrayList<InscripcionReporte>();
		InscripcionReporte inscripReporte = new InscripcionReporte();
		Parametro parametro = parametroDAO.get(1);
		lista.add(inscripReporte);
		parametros.put("apellido", inscripcionReporte.getApellido());
		parametros.put("calle", inscripcionReporte.getCalle());
		parametros.put("cant_cuotas", inscripcionReporte.getCant_cuotas());
		parametros.put("cod_postal", inscripcionReporte.getCod_postal());
		parametros.put("costo_cuota", inscripcionReporte.getCostoCuotaString());
		parametros.put("costo_curso", inscripcionReporte.getCostoCursoString());
		parametros.put("curso", inscripcionReporte.getCurso());
		parametros.put("departamento", inscripcionReporte.getDepartamento());
		parametros.put("dias", inscripcionReporte.getDias());
		parametros.put("dni", inscripcionReporte.getDni());
		parametros.put("email", inscripcionReporte.getEmail());
		parametros.put("fecha_nacimiento", inscripcionReporte.getFechaNacimientoString());
		parametros.put("fecha", inscripcionReporte.getFechaString());
		parametros.put("horario", inscripcionReporte.getHorarioString());
		parametros.put("localidad", inscripcionReporte.getLocalidad());
		parametros.put("matricula", inscripcionReporte.getMatriculaString());
		parametros.put("nombre", inscripcionReporte.getNombre());
		parametros.put("numero", inscripcionReporte.getNumero());
		parametros.put("piso", inscripcionReporte.getPiso());
		parametros.put("primerVencimientoString", inscripcionReporte.getPrimerVencimientoString());
		parametros.put("provincia", inscripcionReporte.getProvincia());
		parametros.put("telcel", inscripcionReporte.getTelcel());
		parametros.put("telfijo", inscripcionReporte.getTelfijo());
		parametros.put("primer_v", Integer.toString(parametro.getDiasPrimerVencimiento()));
		parametros.put("segundo_v", Integer.toString(parametro.getDiasSegundoVencimiento()));
		parametros.put("primer_p", Float.toString(parametro.getPorcentajePrimerVencimiento()));
		parametros.put("segundo_p", Float.toString(parametro.getPorcentajeSegundoVencimiento()));
		reporte.generar(parametros, lista, "inscripcion", "inline");
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-inscripcion.jpg";
         
        pdf.add(Image.getInstance(logo));
    }
	
	public void generarInscriptosPDF() {
		Reporte reporte = new Reporte();
		Map<String, Object> parametros = new HashMap<String, Object>();		
		String nombreCurso = "Todos";
		if (idCurso != 0) {
			Curso cur = cursoDAO.get(idCurso);
			nombreCurso = cur.getNombre();
		}
		parametros.put("curso", nombreCurso);
		reporte.generar(parametros, listaInscriptos, "inscriptos", "inline");
	}
	
	public void generarInscriptosSCPDF() {
		Reporte reporte = new Reporte();
		Map<String, Object> parametros = new HashMap<String, Object>();		
		String nombreCurso = "Todos";
		if (idCurso != 0) {
			Curso cur = cursoDAO.get(idCurso);
			nombreCurso = cur.getNombre();
		}
		parametros.put("curso", nombreCurso);
		reporte.generar(parametros, listaInscriptos, "inscriptosSC", "inline");
	}
	
	public void generarInscriptosXLS() {
		Reporte reporte = new Reporte();
		Map<String, Object> parametros = new HashMap<String, Object>();		
		String nombreCurso = "Todos";
		if (idCurso != 0) {
			Curso cur = cursoDAO.get(idCurso);
			nombreCurso = cur.getNombre();
		}
		parametros.put("curso", nombreCurso);
		reporte.exportXls(parametros, listaInscriptos, "inscriptosExcel", "attachment");
	}


}
