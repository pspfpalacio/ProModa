package promoda.managed.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
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

import promoda.clases.CajasMov;
import promoda.clases.Contacto;
import promoda.clases.DeudaCuota;
import promoda.clases.DeudaMatricula;
import promoda.clases.PagoOnline;
import promoda.clases.PagoReporte;
import promoda.clases.Reporte;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOCuota;
import promoda.dao.DAOCuotaImpaga;
import promoda.dao.DAOCurso;
import promoda.dao.DAOMateria;
import promoda.dao.DAOMatricula;
import promoda.dao.DAOMatriculaAlumno;
import promoda.dao.DAOMatriculaImpaga;
import promoda.dao.DAOMesa;
import promoda.dao.DAOMesaAlumno;
import promoda.dao.DAOPagosCuota;
import promoda.dao.DAOPagosMatricula;
import promoda.dao.DAOPagosMesa;
import promoda.dao.DAOParametro;
import promoda.dao.DAOPlanPago;
import promoda.dao.DAOResgistroOnline;
import promoda.model.Alumno;
import promoda.model.Cuota;
import promoda.model.CuotaImpaga;
import promoda.model.Curso;
import promoda.model.Materia;
import promoda.model.Matricula;
import promoda.model.MatriculaAlumno;
import promoda.model.MatriculaImpaga;
import promoda.model.Mesa;
import promoda.model.MesasAlumno;
import promoda.model.PagosCuota;
import promoda.model.PagosMatricula;
import promoda.model.PagosMesa;
import promoda.model.Parametro;
import promoda.model.RegistroOnline;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
    private DAOAlumno alumnoDAO;
     
    @ManagedProperty(value = "#{BeanPlanPagoDAO}")
    private DAOPlanPago planPagoDAO;
     
    @ManagedProperty(value = "#{BeanCursoDAO}")
    private DAOCurso cursoDAO;
    
    @ManagedProperty(value = "#{BeanMatriculaDAO}")
    private DAOMatricula matriculaDAO;
    
    @ManagedProperty(value = "#{BeanCuotaDAO}")
    private DAOCuota cuotaDAO;
     
    @ManagedProperty(value = "#{BeanMatriculaAlmunoDAO}")
    private DAOMatriculaAlumno matriculaAlumnoDAO;
    
    @ManagedProperty(value = "#{BeanPagosMatriculaDAO}")
    private DAOPagosMatricula pagosMatriculaDAO;
    
    @ManagedProperty(value = "#{BeanPagosCuotaDAO}")
    private DAOPagosCuota pagosCuotaDAO;
    
    @ManagedProperty(value = "#{BeanParametroDAO}")
	private DAOParametro parametroDAO;    
    
    @ManagedProperty(value = "#{BeanMatriculaImpagaDAO}")
	private DAOMatriculaImpaga matriculaImpagaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaImpagaDAO}")
	private DAOCuotaImpaga cuotaImpagaDAO;
	
	@ManagedProperty(value = "#{BeanRegistroOnlineDAO}")
	private DAOResgistroOnline registroOnlineDAO;
	
	@ManagedProperty(value = "#{BeanMesaDAO}")
	private DAOMesa mesaDAO;
	
	@ManagedProperty(value = "#{BeanMesaAlumnoDAO}")
	private DAOMesaAlumno mesaAlumnoDAO;
	
	@ManagedProperty(value = "#{BeanPagosMesaDAO}")
	private DAOPagosMesa pagoMesaDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
 
    private List<Alumno> listaAlumnos;
    private List<MatriculaAlumno> listaMatriculaAlumno;
    private List<MatriculaAlumno> filteredMatriculaAlumno;
    private List<Curso> listaCurso;
    private List<Cuota> listaCuota;
    private List<Cuota> filteredCuota;
    private List<Cuota> selectedCuota;
    private List<PagosMatricula> listaPagoMatricula;
    private List<PagosCuota> listaPagosCuota;
    private List<CuotaImpaga> listaCuotasImpagas;
    private List<MatriculaImpaga> listaMatriculasImpagas;
    private List<PagoOnline> listaRegistroOnlines;
    private List<Matricula> listaMatriculas;
    private List<Materia> listaMaterias;
    private List<Mesa> listaMesas;
    private Usuario usuario;
    private Alumno alumno;
    private Curso curso;
    private Matricula matricula;
    private MatriculaAlumno matalumno;
    private PagosMatricula pagosmatricula;
    private PagosCuota pagoscuota;
    private PagoReporte pagoReporte;
    private Parametro parametro;
    private MatriculaImpaga matriculaImpaga;
    private CuotaImpaga cuotaImpaga;
    private Mesa mesa;
    private Date fecha;
    private Date fechaInicio;
    private Date fechaFin;
    private int idAlumno;
    private int idCurso;
    private int idMatricula;
    private int controlMatAlumno;
    private int controlPagoMatricula;
    private int controlCuota;
    private int controlPagoCuota;
    private int idMateria;
    private int idMesa;
    private boolean siCursos;
    private boolean siMatAlumno;
    private boolean siMatPagada;
    private boolean siCuotas;
    private boolean validacionCoutas;
    private boolean siListaMatriculas;
    private boolean siListaCuotas;
    private boolean poseeDeuda;
    private boolean pagoMat;
    private boolean pagoCuota;
    private boolean pagoMesa;
    private boolean mesaPaga;
    private String tipoListado;
    private String concepto;
    private float montoImp;
 
    public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}

	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}

	public DAOPlanPago getPlanPagoDAO() {
		return planPagoDAO;
	}

	public void setPlanPagoDAO(DAOPlanPago planPagoDAO) {
		this.planPagoDAO = planPagoDAO;
	}

	public DAOCurso getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(DAOCurso cursoDAO) {
		this.cursoDAO = cursoDAO;
	}
	
	public DAOMatricula getMatriculaDAO() {
		return matriculaDAO;
	}

	public void setMatriculaDAO(DAOMatricula matriculaDAO) {
		this.matriculaDAO = matriculaDAO;
	}
	
	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
	}

	public DAOMatriculaAlumno getMatriculaAlumnoDAO() {
        return matriculaAlumnoDAO;
    }
 
    public void setMatriculaAlumnoDAO(DAOMatriculaAlumno matriculaAlumnoDAO) {
        this.matriculaAlumnoDAO = matriculaAlumnoDAO;
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

	public DAOParametro getParametroDAO() {
		return parametroDAO;
	}

	public void setParametroDAO(DAOParametro parametroDAO) {
		this.parametroDAO = parametroDAO;
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

	public DAOResgistroOnline getRegistroOnlineDAO() {
		return registroOnlineDAO;
	}

	public void setRegistroOnlineDAO(DAOResgistroOnline registroOnlineDAO) {
		this.registroOnlineDAO = registroOnlineDAO;
	}

	public DAOMesa getMesaDAO() {
		return mesaDAO;
	}

	public void setMesaDAO(DAOMesa mesaDAO) {
		this.mesaDAO = mesaDAO;
	}

	public DAOMesaAlumno getMesaAlumnoDAO() {
		return mesaAlumnoDAO;
	}

	public void setMesaAlumnoDAO(DAOMesaAlumno mesaAlumnoDAO) {
		this.mesaAlumnoDAO = mesaAlumnoDAO;
	}

	public DAOPagosMesa getPagoMesaDAO() {
		return pagoMesaDAO;
	}

	public void setPagoMesaDAO(DAOPagosMesa pagoMesaDAO) {
		this.pagoMesaDAO = pagoMesaDAO;
	}

	public DAOMateria getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(DAOMateria materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
 
    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }
    
    public List<MatriculaAlumno> getListaMatriculaAlumno() {
        return listaMatriculaAlumno;
    }
 
    public void setListaMatriculaAlumno(List<MatriculaAlumno> listaMatriculaAlumno) {
        this.listaMatriculaAlumno = listaMatriculaAlumno;
    }
     
    public List<MatriculaAlumno> getFilteredMatriculaAlumno() {
		return filteredMatriculaAlumno;
	}

	public void setFilteredMatriculaAlumno(
			List<MatriculaAlumno> filteredMatriculaAlumno) {
		this.filteredMatriculaAlumno = filteredMatriculaAlumno;
	}

	public List<Curso> getListaCurso() {
        return listaCurso;
    }
 
    public void setListaCurso(List<Curso> listaCurso) {
        this.listaCurso = listaCurso;
    }
 
    public List<Cuota> getListaCuota() {
		return listaCuota;
	}

	public void setListaCuota(List<Cuota> listaCuota) {
		this.listaCuota = listaCuota;
	}

	public List<Cuota> getFilteredCuota() {
		return filteredCuota;
	}

	public void setFilteredCuota(List<Cuota> filteredCuota) {
		this.filteredCuota = filteredCuota;
	}

	public List<Cuota> getSelectedCuota() {
		return selectedCuota;
	}

	public void setSelectedCuota(List<Cuota> selectedCuota) {
		this.selectedCuota = selectedCuota;
	}

	public List<PagosMatricula> getListaPagoMatricula() {
		return listaPagoMatricula;
	}

	public void setListaPagoMatricula(List<PagosMatricula> listaPagoMatricula) {
		this.listaPagoMatricula = listaPagoMatricula;
	}

	public List<PagosCuota> getListaPagosCuota() {
		return listaPagosCuota;
	}

	public void setListaPagosCuota(List<PagosCuota> listaPagosCuota) {
		this.listaPagosCuota = listaPagosCuota;
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

	public List<PagoOnline> getListaRegistroOnlines() {
		return listaRegistroOnlines;
	}

	public void setListaRegistroOnlines(List<PagoOnline> listaRegistroOnlines) {
		this.listaRegistroOnlines = listaRegistroOnlines;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public List<Materia> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}

	public List<Mesa> getListaMesas() {
		return listaMesas;
	}

	public void setListaMesas(List<Mesa> listaMesas) {
		this.listaMesas = listaMesas;
	}

	public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Alumno getAlumno() {
        return alumno;
    }
 
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public MatriculaAlumno getMatalumno() {
		return matalumno;
	}

	public PagosMatricula getPagosmatricula() {
		return pagosmatricula;
	}

	public void setPagosmatricula(PagosMatricula pagosmatricula) {
		this.pagosmatricula = pagosmatricula;
	}

	public PagosCuota getPagoscuota() {
		return pagoscuota;
	}

	public void setPagoscuota(PagosCuota pagoscuota) {
		this.pagoscuota = pagoscuota;
	}

	public PagoReporte getPagoReporte() {
		return pagoReporte;
	}

	public void setPagoReporte(PagoReporte pagoReporte) {
		this.pagoReporte = pagoReporte;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public MatriculaImpaga getMatriculaImpaga() {
		return matriculaImpaga;
	}

	public void setMatriculaImpaga(MatriculaImpaga matriculaImpaga) {
		this.matriculaImpaga = matriculaImpaga;
	}

	public CuotaImpaga getCuotaImpaga() {
		return cuotaImpaga;
	}

	public void setCuotaImpaga(CuotaImpaga cuotaImpaga) {
		this.cuotaImpaga = cuotaImpaga;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public void setMatalumno(MatriculaAlumno matalumno) {
		this.matalumno = matalumno;
	}

	public Date getFecha() {
        return fecha;
    }
 
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
 
    public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdAlumno() {
        return idAlumno;
    }
 
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
     
    
    public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public int getControlMatAlumno() {
		return controlMatAlumno;
	}

	public void setControlMatAlumno(int controlMatAlumno) {
		this.controlMatAlumno = controlMatAlumno;
	}

	public int getControlPagoMatricula() {
		return controlPagoMatricula;
	}

	public void setControlPagoMatricula(int controlPagoMatricula) {
		this.controlPagoMatricula = controlPagoMatricula;
	}

	public int getControlCuota() {
		return controlCuota;
	}

	public void setControlCuota(int controlCuota) {
		this.controlCuota = controlCuota;
	}

	public int getControlPagoCuota() {
		return controlPagoCuota;
	}

	public void setControlPagoCuota(int controlPagoCuota) {
		this.controlPagoCuota = controlPagoCuota;
	}

	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public boolean isSiCursos() {
        return siCursos;
    }
 
    public void setSiCursos(boolean siCursos) {
        this.siCursos = siCursos;
    }   
    
    public boolean isSiMatAlumno() {
		return siMatAlumno;
	}

	public void setSiMatAlumno(boolean siMatAlumno) {
		this.siMatAlumno = siMatAlumno;
	}

	public boolean isSiMatPagada() {
		return siMatPagada;
	}

	public void setSiMatPagada(boolean siMatPagada) {
		this.siMatPagada = siMatPagada;
	}

	public boolean isSiCuotas() {
		return siCuotas;
	}

	public void setSiCuotas(boolean siCuotas) {
		this.siCuotas = siCuotas;
	}

	public boolean isValidacionCoutas() {
		return validacionCoutas;
	}

	public void setValidacionCoutas(boolean validacionCoutas) {
		this.validacionCoutas = validacionCoutas;
	}

	public boolean isSiListaMatriculas() {
		return siListaMatriculas;
	}

	public void setSiListaMatriculas(boolean siListaMatriculas) {
		this.siListaMatriculas = siListaMatriculas;
	}

	public boolean isSiListaCuotas() {
		return siListaCuotas;
	}

	public void setSiListaCuotas(boolean siListaCuotas) {
		this.siListaCuotas = siListaCuotas;
	}

	public boolean isPoseeDeuda() {
		return poseeDeuda;
	}

	public void setPoseeDeuda(boolean poseeDeuda) {
		this.poseeDeuda = poseeDeuda;
	}

	public boolean isPagoMat() {
		return pagoMat;
	}

	public void setPagoMat(boolean pagoMat) {
		this.pagoMat = pagoMat;
	}

	public boolean isPagoCuota() {
		return pagoCuota;
	}

	public void setPagoCuota(boolean pagoCuota) {
		this.pagoCuota = pagoCuota;
	}

	public boolean isPagoMesa() {
		return pagoMesa;
	}

	public void setPagoMesa(boolean pagoMesa) {
		this.pagoMesa = pagoMesa;
	}

	public boolean isMesaPaga() {
		return mesaPaga;
	}

	public void setMesaPaga(boolean mesaPaga) {
		this.mesaPaga = mesaPaga;
	}

	public String getTipoListado() {
		return tipoListado;
	}

	public void setTipoListado(String tipoListado) {
		this.tipoListado = tipoListado;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public float getMontoImp() {
		return montoImp;
	}

	public void setMontoImp(float montoImp) {
		this.montoImp = montoImp;
	}

	public String goPago(Usuario user) {
		borrarDatosForm();
		parametro = new Parametro();
		listaAlumnos = new ArrayList<Alumno>();
		parametro = parametroDAO.get(1);
        usuario = user;
        listaAlumnos = alumnoDAO.getLista(true);
        idAlumno = 0;
        pagoMesa = false;
        mesaPaga = false;
        idMateria = 0;
        idMesa = 0;
        mesa = new Mesa();
        listaMaterias = new ArrayList<Materia>();
        listaMesas = new ArrayList<Mesa>();
        return "pagos";
    }
	
	public String goPagoDeuda(Usuario user) {
		listaCuotasImpagas = new ArrayList<CuotaImpaga>();
		listaMatriculasImpagas = new ArrayList<MatriculaImpaga>();
		listaAlumnos = new ArrayList<Alumno>();
		poseeDeuda = false;
		pagoMat = false;
		pagoCuota = false;
		montoImp = 0;
		idAlumno = 0;
		concepto = "";
		fecha = new Date();
		cuotaImpaga = new CuotaImpaga();
		matriculaImpaga = new MatriculaImpaga();
		listaAlumnos = alumnoDAO.getLista(true);
		return "pagosadeuda";
	}
	
	public String goPagosNoRealizados(Usuario user) {
		try {
			usuario = new Usuario();		
			usuario = user;
			listaCuota = new ArrayList<Cuota>();
			listaMatriculaAlumno = new ArrayList<MatriculaAlumno>();
			filteredMatriculaAlumno = new ArrayList<MatriculaAlumno>();
			filteredCuota = new ArrayList<Cuota>();
			SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
			SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
			SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
			Date date;
			int mes = Integer.parseInt(formatoMes.format(new Date()));
			String anio = formatoAnio.format(new Date());						
			mes = mes + 1;
			String fechaFiltro = "";
			if (mes < 10) {
				fechaFiltro = "010" + Integer.toString(mes) + anio;
			} else {
				fechaFiltro = "01" + Integer.toString(mes) + anio;
			}		
			date = formato.parse(fechaFiltro);
			listaCuota = cuotaDAO.getListaPorVencer(date);
			listaMatriculaAlumno = matriculaAlumnoDAO.getLista(false);
			filteredMatriculaAlumno = listaMatriculaAlumno;
			filteredCuota = listaCuota;
			return "pagosporvencer";
		} catch (Exception e){
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible cargar el formulario, Inténtelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public String goPagosRealizadosOnline(Usuario user) {
		try {
			usuario = new Usuario();		
			usuario = user;
			listaRegistroOnlines = new ArrayList<PagoOnline>();
			DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
			List<RegistroOnline> listaOnlines = registroOnlineDAO.getLista();			
			for (RegistroOnline registroOnline : listaOnlines) {
				PagoOnline pagoOnline = new PagoOnline();
				Alumno alum = alumnoDAO.getPorDni(registroOnline.getIdAlumno());
				Cuota cuo = cuotaDAO.get(registroOnline.getIdCuota());
				Matricula mat = matriculaDAO.get(registroOnline.getIdMatricula());
				pagoOnline.setAlumno(alum);
				pagoOnline.setCuota(cuo);
				pagoOnline.setDetalle(registroOnline.getDetalle());
				pagoOnline.setFecha(registroOnline.getFecha());
				pagoOnline.setHora(formatoHora.format(registroOnline.getFecha()));
				pagoOnline.setMatricula(mat);
				pagoOnline.setMonto(formatoMonto.format(registroOnline.getMonto()));
				listaRegistroOnlines.add(pagoOnline);
			}
			return "pagosonline";
		} catch (Exception e){
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible cargar el formulario, Inténtelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	     
    public void onChangeAlumno() {
    	siMatAlumno = false;
    	siMatPagada = true;
    	siCuotas = false;
    	pagoMesa = false;
    	mesaPaga = false;
    	idCurso = 0;
    	idMatricula = 0;    	
        idMateria = 0;
        idMesa = 0;
        mesa = new Mesa();
        alumno = new Alumno();
        alumno = alumnoDAO.get(idAlumno);        
        listaCuota = new ArrayList<Cuota>();
        listaMatriculas = new ArrayList<Matricula>();
        listaCurso = new ArrayList<Curso>();  
        listaMaterias = new ArrayList<Materia>();
        listaMesas = new ArrayList<Mesa>();
        if (alumno.getId() != 0) {
            listaCurso = matriculaAlumnoDAO.getListaCurso(alumno);
            if (listaCurso.size() > 0) {
                siCursos = false;
            }
        }
    }
    
    public void onChangeAlumnoDeuda() {
        alumno = new Alumno();   
        fecha = new Date();
        listaCuotasImpagas = new ArrayList<CuotaImpaga>();
		listaMatriculasImpagas = new ArrayList<MatriculaImpaga>();	
		cuotaImpaga = new CuotaImpaga();
		matriculaImpaga = new MatriculaImpaga();
		poseeDeuda = false;	
		pagoMat = false;
		pagoCuota = false;
		montoImp = 0;
		concepto = "";
		alumno = alumnoDAO.get(idAlumno);
        if (alumno.getId() != 0) {
        	listaCuotasImpagas = cuotaImpagaDAO.getLista(alumno);
			listaMatriculasImpagas = matriculaImpagaDAO.getLista(alumno);
            if (listaCuotasImpagas.isEmpty() && listaMatriculasImpagas.isEmpty()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EL ALUMNO SELECCIONADO NO TIENE DEUDAS!", null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
            	poseeDeuda = true;
            }
        }
    }
    
    public void onChangeCurso(){
    	siMatAlumno = false;
    	siMatPagada = true;
    	siCuotas = false;
    	pagoMesa = false;
    	mesaPaga = false;
    	idMatricula = 0;    	
        idMateria = 0;
        idMesa = 0;
        mesa = new Mesa();
    	alumno = new Alumno();
        alumno = alumnoDAO.get(idAlumno);
        curso = new Curso();
        matricula = new Matricula();        
        matalumno = new MatriculaAlumno();
        concepto = "";
        listaCuota = new ArrayList<Cuota>();
        listaMatriculas = new ArrayList<Matricula>();
        listaMaterias = new ArrayList<Materia>();
        listaMesas = new ArrayList<Mesa>();
        try {
        	if (idCurso != 0) {
        		curso = cursoDAO.get(idCurso);       
        		listaMatriculas = matriculaDAO.getLista(true, curso);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER EL CURSO", null));
        }
    }
    
    public void onChangeMatricula() {
    	siMatAlumno = false;
    	siMatPagada = true;
    	siCuotas = false;
    	pagoMesa = true;
    	mesaPaga = false;
        idMateria = 0;
        idMesa = 0;
        mesa = new Mesa();
    	matricula = new Matricula();        
        matalumno = new MatriculaAlumno();
        concepto = "";
        listaCuota = new ArrayList<Cuota>();
        listaMaterias = new ArrayList<Materia>();
        listaMesas = new ArrayList<Mesa>();
    	try {    		
    		if (idMatricula != 0) {
    			listaMaterias = materiaDAO.getLista(true, curso);
    			matricula = matriculaDAO.get(idMatricula);
    			if (alumno.getId() != 0 && curso.getId() != 0){
    	        	matalumno = matriculaAlumnoDAO.get(alumno, curso, matricula);
    	        	if(matalumno.getId() != 0){
    	        		siMatAlumno = true;
    	        		if(!matalumno.getPago()){
    	        			pagosmatricula = new PagosMatricula();
    	        			siMatPagada = matalumno.getPago();
    	        		} else {
    	        			fecha = matalumno.getFechaPago();
    	        	    	pagosmatricula = pagosMatriculaDAO.get(alumno, matricula);
    	        	    	fecha = pagosmatricula.getFecha();
    	        	    	concepto = pagosmatricula.getConcepto();
    	        		}    	        		
    	        		
    	        		listaCuota = cuotaDAO.getLista(alumno, matricula, curso);
    	        		if(listaCuota.size() > 0){
    	        			siCuotas = true;
    	        		}        		
    	        	}
    	        }
        	}
    	} catch (Exception e) {
    		e.printStackTrace();
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LA MATRICULA", null));
    	}    	
    }
    
    public void onChangeMateria() {
		try {			
			listaMesas = new ArrayList<Mesa>();		
			mesa = new Mesa();
	    	mesaPaga = false;
			idMesa = 0;
			if (idAlumno != 0 && idMatricula != 0 && idCurso != 0 && idMateria != 0) {				
				Curso cur = cursoDAO.get(idCurso);
				Matricula matr = matriculaDAO.get(idMatricula);
				Materia mat = materiaDAO.get(idMateria);
				listaMesas = mesaDAO.getLista(cur, matr, mat);
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LAS MESAS", null));
		}
	}
	
	public void onChangeMesa() {
		try {			
			mesa = new Mesa();
	    	mesaPaga = false;
			if (idAlumno != 0 && idMatricula != 0 && idCurso != 0 && idMateria != 0 && idMesa != 0) {				
				Alumno alum = alumnoDAO.get(idAlumno);
				mesa = mesaDAO.get(idMesa);
				PagosMesa pagosMesa = pagoMesaDAO.get(mesa, alum);
				if (pagosMesa.getId() != 0) {
					mesaPaga = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL OBTENER LAS MESAS", null));
		}
	}
    
	public void onChangeFechaPago(Cuota cuo) {
		//Parametro param = parametroDAO.get(1);
		float porcentajePV = cuo.getPorcentajePv();
		float porcentajeSV = cuo.getPorcentajeSv();
		float montoP = cuo.getMonto();
		Date fechaPV = cuo.getFechaVencimiento();
		Date fechaSV = cuo.getSegundoVencimiento();
		Date fechaP = cuo.getFechaPago();
		if (fechaP != null) {
			int comparacionPV = fechaP.compareTo(fechaPV);
			int comparacionSV = fechaP.compareTo(fechaSV);
			if (comparacionPV == 1) {
				if (comparacionSV == 1) {
					montoP = montoP + (montoP * (porcentajeSV / 100));
					cuo.setMontoPago(montoP);
				} else {
					montoP = montoP + (montoP * (porcentajePV / 100));
					cuo.setMontoPago(montoP);
				}
			} else {
				cuo.setMontoPago(montoP);
			}
		}		
	}
	
	public void onSelectMatricula(MatriculaImpaga matriculaI) {
		montoImp = 0;
		concepto = "";
		pagoMat = true;
		pagoCuota = false;
		fecha = new Date();
		cuotaImpaga = new CuotaImpaga();
		matriculaImpaga = new MatriculaImpaga();
		matriculaImpaga = matriculaI;
	}
	
	public void onSelectCuota(CuotaImpaga cuotaI) {
		montoImp = 0;
		concepto = "";
		pagoMat = false;
		pagoCuota = true;
		fecha = new Date();
		cuotaImpaga = new CuotaImpaga();
		matriculaImpaga = new MatriculaImpaga();
		cuotaImpaga = cuotaI;
	}
	
    public void bajaMatricula() {
    	FacesMessage msg = null;
    	PagosMatricula pago = new PagosMatricula();
    	CajasMov cajaMov = new CajasMov();
    	pago = pagosMatriculaDAO.get(alumno, matricula);
    	pago.setEnabled(false);
    	pago.setFechaBaja(new Date());
    	pago.setUsuario2(usuario);
    	matalumno.setMontoPago(0);
		matalumno.setPago(false);
		matalumno.setFechaPago(null);
		int updtMatriAlum = matriculaAlumnoDAO.update(matalumno);
		int idPagoMatri = pagosMatriculaDAO.update(pago);
		cajaMov.eliminarMovimiento(idPagoMatri, "PagosMatricula", usuario);
		if (updtMatriAlum != 0 && idPagoMatri != 0) {
			onChangeCurso();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE PAGO REGISTRADA", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN LA BAJA DE PAGO, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void bajaCuota(Cuota cuo) {
    	FacesMessage msg = null;
    	PagosCuota pago = new PagosCuota();
    	Alumno alum = new Alumno();
    	CajasMov cajaMov = new CajasMov();
    	alum = cuo.getAlumno();
    	pago = pagosCuotaDAO.get(alum, cuo);
    	pago.setEnabled(false);
    	pago.setFechaBaja(new Date());
    	pago.setUsuario2(usuario);
    	cuo.setFechaPago(null);
    	cuo.setFechaMod(new Date());
    	cuo.setMontoPago(0);
    	cuo.setPaga(false);
    	cuo.setUsuario3(usuario);
    	int idPagoCuo = pagosCuotaDAO.update(pago);
    	int updtCuota = cuotaDAO.update(cuo);
    	cajaMov.eliminarMovimiento(idPagoCuo, "PagosCuota", usuario);
    	if (idPagoCuo != 0 && updtCuota != 0) {
    		onChangeCurso();
    		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BAJA DE PAGO REGISTRADA", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OCURRIÓ UN ERROR EN LA BAJA DE PAGO, "
					+ "INTÉNTELO NUEVAMENTE", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void bajaMesa() {
    	try {
    		if (idAlumno != 0 && idMatricula != 0 && idCurso != 0 && idMateria != 0 && idMesa != 0) {
    			Alumno alum = alumnoDAO.get(idAlumno);
    			Mesa me = mesaDAO.get(idMesa);
    			PagosMesa pagosMesa = pagoMesaDAO.get(me, alum);
				if (pagosMesa.getId() != 0) {
					CajasMov cajaMov = new CajasMov();
					pagosMesa.setEnabled(false);
					pagosMesa.setFechaBaja(new Date());
					pagosMesa.setUsuario2(usuario);
					int idPagoMesa = pagoMesaDAO.update(pagosMesa);
					if (idPagoMesa != 0) {
						int deleteMov = cajaMov.eliminarMovimiento(idPagoMesa, "PagosMesa", usuario);
						if (deleteMov != 0) {
							mesaPaga = false;
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
									"BAJA DE PAGO REGISTRADA", null));							
						} else {
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
				    				"OCURRIÓ UN ERROR AL REGISTRAR LA BAJA DEL PAGO DE MESA EN CAJA", null));
						}
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
			    				"OCURRIÓ UN ERROR AL REGISTRAR LA BAJA DEL PAGO DE MESA", null));
					}				
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
		    				"OCURRIÓ UN ERROR AL OBTENER EL PAGO DE MESA", null));
				}
    		} else {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
        				"TODOS LOS CAMPOS SON OBLIGATORIOS", null));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
    				"OCURRIÓ UN ERROR AL REGISTRAR LA BAJA DEL PAGO DE MESA. Error: " + e.getMessage(), null));
    	}
    }

    public void pagoMatricula() {
    	FacesMessage msg = null;
    	try {
    		if(pagosmatricula.getMonto() > 0){  
        		CajasMov cajaMov = new CajasMov();
        		pagosmatricula.setFecha(fecha);
        		pagosmatricula.setMatricula(matricula);
        		pagosmatricula.setAlumno(alumno);
        		pagosmatricula.setUsuario1(usuario);
        		pagosmatricula.setFechaAlta(new Date());
        		pagosmatricula.setEnabled(true);
        		pagosmatricula.setConcepto(concepto);
        		matalumno.setPago(true);
        		matalumno.setFechaPago(fecha);    	
        		matalumno.setMontoPago(pagosmatricula.getMonto());
        		controlMatAlumno = matriculaAlumnoDAO.update(matalumno);
        		controlPagoMatricula = pagosMatriculaDAO.insertar(pagosmatricula);
        		int idPagoMatricula = controlPagoMatricula;
        		cajaMov.generarMovimiento(fecha, 1, pagosmatricula.getMonto(), idPagoMatricula, "PagosMatricula", "Pago Matricula", 
        				"Pago de Matrícula de Curso " + matalumno.getCurso().getNombre() + ", de " + matalumno.getAlumno().getNombreCompleto(), usuario);
        		if (controlMatAlumno != 0 && controlPagoMatricula != 0) {
        		    siMatPagada = true;
        			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "PAGO DE MATRICULA REGISTRADO", null);
        		} else {
        			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL PAGO", null);
        		}
        	}else{
        		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "EL MONTO DEBE SER MAYOR A CERO", null);
        	}
    	} catch (Exception e) {
    		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL RESGISTRAR EL PAGO, ERROR: " + e.getMessage(), null);    		
    	}    	
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void pagoMatriculaImpaga() {
    	FacesMessage msg = null;
    	try {    		
    		CajasMov cajaMov = new CajasMov();
    		if (montoImp > 0 && fecha != null) {
    			MatriculaAlumno matriculaA = matriculaImpaga.getMatriculaAlumno();
    			PagosMatricula pagosMat = new PagosMatricula();
    			pagosMat.setAlumno(matriculaImpaga.getAlumno());
    			pagosMat.setConcepto(concepto);
    			pagosMat.setEnabled(true);
    			pagosMat.setFecha(fecha);
    			pagosMat.setFechaAlta(new Date());
    			pagosMat.setMatricula(matriculaImpaga.getMatricula());
    			pagosMat.setMonto(montoImp);
    			pagosMat.setUsuario1(usuario);
    			matriculaA.setPago(true);
    			matriculaA.setFechaPago(fecha);
    			int idPagoMatricula = pagosMatriculaDAO.insertar(pagosMat);
    			int idMatriculaAlumno = matriculaAlumnoDAO.update(matriculaA);
    			cajaMov.generarMovimiento(fecha, 1, montoImp, idPagoMatricula, "PagosMatricula", "Pago Matricula", 
        				"Pago de Deuda de Matrícula de Curso " + matriculaA.getCurso().getNombre() + ", de " + matriculaA.getAlumno().getNombreCompleto(), usuario);
    			matriculaImpagaDAO.delete(matriculaImpaga);
    			if (idPagoMatricula != 0 && idMatriculaAlumno != 0) {
        		    pagoMat = false;
        		    listaMatriculasImpagas = new ArrayList<MatriculaImpaga>();
        		    listaMatriculasImpagas = matriculaImpagaDAO.getLista(alumno);
        			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "PAGO DE DEUDA DE MATRICULA REGISTRADO", null);
        		} else {
        			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL PAGO DE LA DEUDA", null);
        		}
    		} else {
    			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "EL MONTO Y LA FECHA DE PAGO DEBEN SER DISTINTAS DE CERO Y VACIO", null);
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO SE PUDO REGISTRAR EL PAGO DE MATRICULA, ERROR ORIGINAL: " + e.getMessage(), null);
    	}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void pagoCuotas() {
    	FacesMessage msg = null;
    	if(selectedCuota.size() > 0){
    		boolean pagas = false;
    		for (Cuota cuota : selectedCuota) {
    			if(cuota.getMontoPago() == 0 || cuota.getFechaPago() == null){
    				validacionCoutas = false;
    			}
    			if (cuota.getPaga()) {
    				pagas = true;
    			}
    		}
    		if(validacionCoutas && !pagas){
		    	for (Cuota cuota : selectedCuota) {
		    		CajasMov cajaMov = new CajasMov();
		    		cuota.setPaga(true);
		    		cuota.setUsuario3(usuario);
		    		cuota.setFechaMod(fecha);
		    		pagoscuota = new PagosCuota();
		    		pagoscuota.setAlumno(alumno);
		    		pagoscuota.setCuota(cuota);
		    		pagoscuota.setFecha(cuota.getFechaPago());
		    		pagoscuota.setConcepto(cuota.getDetalle());
		    		pagoscuota.setMonto(cuota.getMontoPago());
		    		pagoscuota.setUsuario1(usuario);
		    		pagoscuota.setFechaAlta(fecha);
		    		pagoscuota.setEnabled(true);
		    		controlCuota = cuotaDAO.update(cuota);
		    		controlPagoCuota = pagosCuotaDAO.insertar(pagoscuota);
		    		int idPagoCuot = controlPagoCuota;
		    		cajaMov.generarMovimiento(cuota.getFechaPago(), 1, cuota.getMontoPago(), idPagoCuot, "PagosCuota", "Pago Cuota", 
		    				"Pago de " + cuota.getDetalle() + " de Curso " + cuota.getCurso().getNombre() + " de " + cuota.getAlumno().getNombreCompleto(), usuario);
		    		if (controlCuota != 0 && controlPagoCuota != 0) {
		    			listaCuota = new ArrayList<Cuota>();
		        		listaCuota = cuotaDAO.getLista(alumno, matricula, curso);
		        		if(listaCuota.size() > 0){
		        			siCuotas = true;
		        		}
		    			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "PAGO DE CUOTA/S REGISTRADO", null);
		    		} else {
		    			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL PAGO", null);
		    		}
		    		FacesContext.getCurrentInstance().addMessage(null, msg);
		    	}
    		}else{
    			if(!validacionCoutas) {
    				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "DEBE INGRESAR MONTO Y FECHA PARA LAS CUOTAS SELECCIONADAS", null);
        			FacesContext.getCurrentInstance().addMessage(null, msg);
    			}
    			if (pagas) {
    				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "ALGUNA DE LAS CUOTAS SELECCIONADAS YA POSEE UN PAGO REGISTRADO!", null);
        			FacesContext.getCurrentInstance().addMessage(null, msg);
    			}
    		}
    	}else{
    		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "DEBE SELECCIONAR ALGUNA CUOTA", null);
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    	validacionCoutas = true;    	
    }
    
    public void pagoCuotaImpaga() {
    	FacesMessage msg = null;
    	try {
    		CajasMov cajaMov = new CajasMov();
    		if (montoImp > 0 && fecha != null) {
    			Cuota cuota = cuotaImpaga.getCuota();
    			PagosCuota pagosCuot = new PagosCuota();
    			cuota.setPaga(true);
    			cuota.setUsuario3(usuario);
    			cuota.setFechaMod(new Date());
    			pagosCuot.setAlumno(cuotaImpaga.getAlumno());
    			pagosCuot.setConcepto(concepto);
    			pagosCuot.setCuota(cuota);
    			pagosCuot.setEnabled(true);
    			pagosCuot.setFecha(fecha);
    			pagosCuot.setFechaAlta(new Date());
    			pagosCuot.setMonto(montoImp);
    			pagosCuot.setUsuario1(usuario);
    			int idCuota = cuotaDAO.update(cuota);
    			int idPagoCuota = pagosCuotaDAO.insertar(pagosCuot);
    			cajaMov.generarMovimiento(fecha, 1, montoImp, idPagoCuota, "PagosCuota", "Pago Cuota", 
	    				"Pago de Deuda de " + cuota.getDetalle() + " de Curso " + cuota.getCurso().getNombre() + " de " + cuota.getAlumno().getNombreCompleto(), usuario);
    			cuotaImpagaDAO.delete(cuotaImpaga);
    			if (idCuota != 0 && idPagoCuota != 0) {
    				pagoCuota = false;
    				listaCuotasImpagas = new ArrayList<CuotaImpaga>();
    				listaCuotasImpagas = cuotaImpagaDAO.getLista(alumno);
    				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "PAGO DE DEUDA DE CUOTA REGISTRADO", null);
    			} else {
    				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR EL PAGO DE DEUDA", null);
    			}
    		} else {
    			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "EL MONTO Y LA FECHA DE PAGO DEBEN SER DISTINTAS DE CERO Y VACIO", null);
    		}    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO SE PUDO REGISTRAR EL PAGO DE MATRICULA, ERROR ORIGINAL: " + e.getMessage(), null);
    	}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void pagarMesa() {
    	try {
    		if (idAlumno != 0 && idMatricula != 0 && idCurso != 0 && idMateria != 0 && idMesa != 0) {
    			CajasMov cajaMov = new CajasMov();
    			Alumno alum = alumnoDAO.get(idAlumno);
    			Mesa me = mesaDAO.get(idMesa);
    			Curso cur = cursoDAO.get(idCurso);
    			Materia mat = materiaDAO.get(idMateria);
    			PagosMesa pagosMesa = new PagosMesa();
				pagosMesa.setAlumno(alum);
				pagosMesa.setEnabled(true);
				pagosMesa.setFecha(new Date());
				pagosMesa.setFechaAlta(new Date());
				pagosMesa.setMesa(me);
				List<MesasAlumno> listAux = mesaAlumnoDAO.getLista(true, alum, me);
				MesasAlumno mesaAlumno = new MesasAlumno();
				for (MesasAlumno mAlumno : listAux) {
					mesaAlumno = mAlumno;
				}
				if (mesaAlumno.getId() != 0) {
					pagosMesa.setMesasAlumno(mesaAlumno);
				}
				pagosMesa.setMonto(me.getCosto());
				pagosMesa.setUsuario1(usuario);
				int idPagoMesa = pagoMesaDAO.insertar(pagosMesa);
				if (idPagoMesa != 0) {
					int insertoCaja = cajaMov.generarMovimiento(new Date(), 1, mesa.getCosto(), idPagoMesa, "PagosMesa", "Pago Mesa", 
	        				"Pago de Mesa de Curso " + cur.getNombre() + " de Materia " + mat.getNombre() + " de " + alum.getNombreCompleto(), usuario);
					if (insertoCaja != 0) {
						mesaPaga = true;
	        			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
	        					"PAGO DE MESA REGISTRADO", null));
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
		        				"OCURRIÓ UN ERROR AL REGISTRAR EL PAGO DE LA MESA EN LA CAJA.", null));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
	        				"OCURRIÓ UN ERROR AL REGISTRAR EL PAGO DE LA MESA.", null));
				}				
    		} else {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
        				"TODOS LOS CAMPOS SON OBLIGATORIOS.", null));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
    				"OCURRIÓ UN ERROR AL REGISTRAR EL PAGO DE LA MESA. Error: " + e.getMessage(), null));
    	}
    }   
    
    public void guardarParametro() {
    	try {
    		FacesMessage msg = null;
    		if(parametro.getDiasPrimerVencimiento() != 0 && parametro.getDiasSegundoVencimiento() != 0 
        			&& parametro.getPorcentajePrimerVencimiento() != 0 && parametro.getPorcentajeSegundoVencimiento() != 0) {
        		parametro.setId(1);
        		int updtParam = parametroDAO.update(parametro);
        		if (updtParam != 0) {
        			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "DATOS REGISTRADOS!", null);
        		} else {
        			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL REGISTRAR LOS DATOS! INTÉNTELO NUEVAMENTE!", null);
        		}
        	} else {
        		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "TODOS LOS CAMPOS SON OBLIGATORIOS!", null);
        	}
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	} catch (Exception e) {
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIÓ UN ERROR AL REGISTRAR LOS DATOS! ERROR ORIGINAL: " 
    				+ e.getMessage(), null);
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}    	
    }
    
    private void borrarDatosForm(){
    	listaAlumnos = new ArrayList<Alumno>();
    	listaMatriculaAlumno = new ArrayList<MatriculaAlumno>();
    	listaCurso = new ArrayList<Curso>();
    	listaCuota = new ArrayList<Cuota>();
    	listaPagoMatricula = new ArrayList<PagosMatricula>();
    	listaPagosCuota = new ArrayList<PagosCuota>();
    	selectedCuota = new ArrayList<Cuota>();
    	usuario = new Usuario();
    	alumno = new Alumno();
    	curso = new Curso();
    	matricula = new Matricula();
    	matalumno = new MatriculaAlumno();
    	pagosmatricula = new PagosMatricula();
    	pagoscuota = new PagosCuota();
    	fecha = new Date();
    	fechaInicio = new Date();
    	fechaInicio = null;
    	fechaFin = new Date();
    	fechaFin = null;
    	idAlumno = 0;
    	idCurso = 0;
    	controlMatAlumno = 0;
    	controlPagoMatricula = 0;
    	controlCuota = 0;
    	controlPagoCuota = 0;
    	siCursos = true;
    	siMatAlumno = false;
    	siMatPagada = true;
    	siCuotas = false;
    	siListaCuotas = false;
    	siListaMatriculas = false;
    	validacionCoutas = true;
    	tipoListado = "M";
    	concepto = null;
    }
    
    public void cancelarMatriculaImpaga() {
    	pagoMat = false;
    	matriculaImpaga = new MatriculaImpaga();
    }
    
    public void cancelarCuotaImpaga() {
    	pagoCuota = false;
    	cuotaImpaga = new CuotaImpaga();
    }
    
    //-------------> METODOS DE LISTADO PAGOS - SE COMPARTEN VARIABLES Y LISTAS Y METODO BORRAR DATOS FORM <------------
    
	public String goListadoPago(Usuario user) {
		borrarDatosForm();		
        usuario = user;
        listaAlumnos = alumnoDAO.getLista(true);
        return "listadopagos";
    }
	
	 public void onChangeAlumnoLista() {
	        alumno = new Alumno();
	        alumno = alumnoDAO.get(idAlumno);
	 }
	
	 public void onChangeRadio(){		 
		 if(tipoListado.equals("M")){
			 siListaCuotas = false;
		 }
		 
		 if(tipoListado.equals("C")){
			 siListaMatriculas = false;
		 }		 
	 }
	
	 public void buscarListados(){
		 if(tipoListado.equals("M")){
			 if (alumno.getId() != 0){
				 if(fechaInicio != null && fechaFin != null){
					 listaPagoMatricula = pagosMatriculaDAO.getLista(alumno, fechaInicio, fechaFin);
				 }else{
					 if(fechaInicio == null && fechaFin == null){
						 listaPagoMatricula = pagosMatriculaDAO.getLista(true, alumno);
					 }else{
						 if(fechaInicio == null){
							 listaPagoMatricula = pagosMatriculaDAO.getLista(alumno, fechaFin, "F");
						 }
						 if(fechaFin == null){
							 listaPagoMatricula = pagosMatriculaDAO.getLista(alumno, fechaInicio, "I");
						 }
					 }
				 }				 
			 }else{
				 if(fechaInicio != null && fechaFin != null){
					 listaPagoMatricula = pagosMatriculaDAO.getLista(fechaInicio, fechaFin);
				 }else{
					 if(fechaInicio == null && fechaFin == null){
						 listaPagoMatricula = pagosMatriculaDAO.getLista(true);
					 }else{
						 if(fechaInicio == null){
							 listaPagoMatricula = pagosMatriculaDAO.getLista(fechaFin, "F");
						 }
						 if(fechaFin == null){
							 listaPagoMatricula = pagosMatriculaDAO.getLista(fechaInicio, "I");
						 }
					 }
				 }
			 }
			siListaMatriculas = true; 
			siListaCuotas = false; 
		 }
		 
		 if(tipoListado.equals("C")){
			 if (alumno.getId() != 0){
				 if(fechaInicio != null && fechaFin != null){
					 listaPagosCuota = pagosCuotaDAO.getLista(alumno, fechaInicio, fechaFin);
				 }else{
					 if(fechaInicio == null && fechaFin == null){
						 listaPagosCuota = pagosCuotaDAO.getLista(true, alumno);
					 }else{
						 if(fechaInicio == null){
							 listaPagosCuota = pagosCuotaDAO.getLista(alumno, fechaFin, "F");
						 }
						 if(fechaFin == null){
							 listaPagosCuota = pagosCuotaDAO.getLista(alumno, fechaInicio, "I");
						 }
					 }
				 }				 
			 }else{
				 if(fechaInicio != null && fechaFin != null){
					 listaPagosCuota = pagosCuotaDAO.getLista(fechaInicio, fechaFin);
				 }else{
					 if(fechaInicio == null && fechaFin == null){
						 listaPagosCuota = pagosCuotaDAO.getLista(true);
					 }else{
						 if(fechaInicio == null){
							 listaPagosCuota = pagosCuotaDAO.getLista(fechaFin, "F");
						 }
						 if(fechaFin == null){
							 listaPagosCuota = pagosCuotaDAO.getLista(fechaInicio, "I");
						 }
					 }
				 }
			 }
			 siListaCuotas = true; 
			 siListaMatriculas = false; 
		 }
	 }
	 
	 public void verPagoMatricula(PagosMatricula pagoMatri) {
		 pagoReporte = new PagoReporte();
		 pagoReporte.setEncabezado("PAGO DE MATRICUlA");
		 pagoReporte.setAlumno(pagoMatri.getAlumno().getNombreCompleto());
		 pagoReporte.setConcepto(pagoMatri.getConcepto());
		 pagoReporte.setCurso(pagoMatri.getMatricula().getCurso().getNombre());
		 pagoReporte.setFecha(pagoMatri.getFecha());
		 pagoReporte.setMonto(pagoMatri.getMonto());
		 pagoReporte.setMatricula(pagoMatri.getMatricula().getDescripcion());
	 }
	 
	 public void verPagoCuota(PagosCuota pagoCuot) {
		 pagoReporte = new PagoReporte();
		 pagoReporte.setEncabezado("PAGO DE CUOTA");
		 pagoReporte.setAlumno(pagoCuot.getAlumno().getNombreCompleto());
		 pagoReporte.setConcepto(pagoCuot.getConcepto());
		 pagoReporte.setCurso(pagoCuot.getCuota().getCurso().getNombre());
		 pagoReporte.setFecha(pagoCuot.getFecha());
		 pagoReporte.setMonto(pagoCuot.getMonto());
		 pagoReporte.setMatricula(pagoCuot.getCuota().getMatricula().getDescripcion());
	 }
	 
	 public void generarReporte() {
		 Reporte reporte = new Reporte();
		 Map<String, Object> parametros = new HashMap<String, Object>();
		 List<PagoReporte> lista = new ArrayList<PagoReporte>();
		 PagoReporte pagoR = new PagoReporte();
		 lista.add(pagoR);
		 parametros.put("alumno", pagoReporte.getAlumno());
		 parametros.put("concepto", pagoReporte.getConcepto());
		 parametros.put("curso", pagoReporte.getCurso());
		 parametros.put("fecha", pagoReporte.getFechaString());
		 parametros.put("monto", pagoReporte.getMontoString());
		 reporte.generar(parametros, lista, "pago", "inline");
	 }
	 
	 public void preProcessPDFMatricula(Object document) throws IOException, BadElementException, DocumentException {
		 Document pdf = (Document) document;
	     pdf.open();
	     pdf.setPageSize(PageSize.A4);
	     
	     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	     String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-pago-matricula.jpg";
	     
	     pdf.add(Image.getInstance(logo));
	 }
	 
	 public void preProcessPDFCuotas(Object document) throws IOException, BadElementException, DocumentException {
		 Document pdf = (Document) document;
	     pdf.open();
	     pdf.setPageSize(PageSize.A4);
	     
	     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	     String logo = externalContext.getRealPath("") + File.separator + "images" + File.separator + "pm-pago-cuota.jpg";
	     
	     pdf.add(Image.getInstance(logo));
	 }
	 
	 public void reporteDeudaMatricula() {
		 Reporte reporte = new Reporte();
		 Map<String, Object> parametros = new HashMap<String, Object>();
		 DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		 List<DeudaMatricula> listaDeudaMatriculas = new ArrayList<DeudaMatricula>();		 
		 for (MatriculaAlumno mAlumno : filteredMatriculaAlumno) {
			 DeudaMatricula dMatricula = new DeudaMatricula();		 
			 Contacto contacto = new Contacto();
			 List<Contacto> listaContacto = new ArrayList<Contacto>();
			 dMatricula.setAlumno(mAlumno.getAlumno().getNombreCompleto());
			 dMatricula.setCurso(mAlumno.getCurso().getNombre());
			 dMatricula.setFechaInscripcion(formatoFecha.format(mAlumno.getFechaAlta()));
			 dMatricula.setMontoDeuda(formatoMonto.format(mAlumno.getMatricula().getCosto()));
			 contacto.setEmail(mAlumno.getAlumno().getEmail());
			 contacto.setTelCel(mAlumno.getAlumno().getTelefonoCel());
			 contacto.setTelFijo(mAlumno.getAlumno().getTelefonoFijo());
			 listaContacto.add(contacto);
			 dMatricula.setListaContacto(listaContacto);
			 listaDeudaMatriculas.add(dMatricula);
		 }
		 parametros.put("fecha", formatoFecha.format(new Date()));
		 reporte.generar(parametros, listaDeudaMatriculas, "deudaMatriculas", "inline");
	 }
	 
	 public void reporteDeudaCuota() {
		 Reporte reporte = new Reporte();
		 Map<String, Object> parametros = new HashMap<String, Object>();
		 DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		 List<DeudaCuota> listaDeudaCuotas = new ArrayList<DeudaCuota>();
		 for (Cuota cuo : filteredCuota) {
			 DeudaCuota dCuota = new DeudaCuota();
			 Contacto contacto = new Contacto();
			 List<Contacto> listaContacto = new ArrayList<Contacto>();
			 dCuota.setAlumno(cuo.getAlumno().getNombreCompleto());
			 dCuota.setCurso(cuo.getCurso().getNombre());
			 dCuota.setDetalle(cuo.getDetalle());
			 dCuota.setMonto(formatoMonto.format(cuo.getMonto()));
			 dCuota.setPrimerVencimiento(formatoFecha.format(cuo.getFechaVencimiento()));
			 dCuota.setSegundoVencimiento(formatoFecha.format(cuo.getSegundoVencimiento()));
			 contacto.setEmail(cuo.getAlumno().getEmail());
			 contacto.setTelCel(cuo.getAlumno().getTelefonoCel());
			 contacto.setTelFijo(cuo.getAlumno().getTelefonoFijo());
			 listaContacto.add(contacto);
			 dCuota.setListaContacto(listaContacto);		
			 listaDeudaCuotas.add(dCuota);
		 }
		 parametros.put("fecha", formatoFecha.format(new Date()));
		 reporte.generar(parametros, listaDeudaCuotas, "deudaCuotas", "inline");
	 }

}
