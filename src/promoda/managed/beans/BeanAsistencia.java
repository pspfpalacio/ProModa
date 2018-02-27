package promoda.managed.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import promoda.clases.AsistenciaReporte;
import promoda.clases.Clase;
import promoda.dao.DAOAlumno;
import promoda.dao.DAOAsistencia;
import promoda.dao.DAOCurso;
import promoda.dao.DAOInscripcion;
import promoda.dao.DAOMateria;
import promoda.model.Alumno;
import promoda.model.Asistencia;
import promoda.model.Curso;
import promoda.model.Inscripcione;
import promoda.model.Materia;
import promoda.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanAsistencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanAsistenciaDAO}")
	private DAOAsistencia asistenciaDAO;
	
	@ManagedProperty(value = "#{BeanCursoDAO}")
	private DAOCurso cursoDAO;
	
	@ManagedProperty(value = "#{BeanMateriaDAO}")
	private DAOMateria materiaDAO;
	
	@ManagedProperty(value = "#{BeanInscripcionDAO}")
	private DAOInscripcion inscripcionDAO;
	
	@ManagedProperty(value = "#{BeanAlumnoDAO}")
	private DAOAlumno alumnoDAO;
	
	private List<Asistencia> listaAsistencias;
	private List<Asistencia> selectionAsistencias;
	private List<Materia> listaMaterias;
	private List<Curso> listaCursos;
	private List<Clase> listaClases;
	private List<AsistenciaReporte> listaAsistenciaReporte;
	private List<AsistenciaReporte> listaAsistenciaPlanilla;
	private List<AsistenciaReporte> selectionAsistenciaPlanilla;
	private Curso curso;
	private Materia materia;
	private Usuario usuario;
	private Date fecha;
	private Date fechaDesde;
	private Date fechaHasta;
	private String asistenciaMasiva;
	private int idCurso;
	private int idMateria;
	private int idClase;
	private int cantClases;
	private int clasesDesde;
	private int clasesHasta;
	private boolean intervalo;
	private boolean reporte;

	public DAOAsistencia getAsistenciaDAO() {
		return asistenciaDAO;
	}

	public void setAsistenciaDAO(DAOAsistencia asistenciaDAO) {
		this.asistenciaDAO = asistenciaDAO;
	}

	public DAOCurso getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(DAOCurso cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public DAOMateria getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(DAOMateria materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public DAOInscripcion getInscripcionDAO() {
		return inscripcionDAO;
	}

	public void setInscripcionDAO(DAOInscripcion inscripcionDAO) {
		this.inscripcionDAO = inscripcionDAO;
	}

	public DAOAlumno getAlumnoDAO() {
		return alumnoDAO;
	}

	public void setAlumnoDAO(DAOAlumno alumnoDAO) {
		this.alumnoDAO = alumnoDAO;
	}

	public List<Asistencia> getListaAsistencias() {
		return listaAsistencias;
	}

	public void setListaAsistencias(List<Asistencia> listaAsistencias) {
		this.listaAsistencias = listaAsistencias;
	}

	public List<Asistencia> getSelectionAsistencias() {
		return selectionAsistencias;
	}

	public void setSelectionAsistencias(List<Asistencia> selectionAsistencias) {
		this.selectionAsistencias = selectionAsistencias;
	}

	public List<Materia> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(List<Materia> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Clase> getListaClases() {
		return listaClases;
	}

	public void setListaClases(List<Clase> listaClases) {
		this.listaClases = listaClases;
	}

	public List<AsistenciaReporte> getListaAsistenciaReporte() {
		return listaAsistenciaReporte;
	}

	public void setListaAsistenciaReporte(
			List<AsistenciaReporte> listaAsistenciaReporte) {
		this.listaAsistenciaReporte = listaAsistenciaReporte;
	}

	public List<AsistenciaReporte> getListaAsistenciaPlanilla() {
		return listaAsistenciaPlanilla;
	}

	public void setListaAsistenciaPlanilla(
			List<AsistenciaReporte> listaAsistenciaPlanilla) {
		this.listaAsistenciaPlanilla = listaAsistenciaPlanilla;
	}

	public List<AsistenciaReporte> getSelectionAsistenciaPlanilla() {
		return selectionAsistenciaPlanilla;
	}

	public void setSelectionAsistenciaPlanilla(
			List<AsistenciaReporte> selectionAsistenciaPlanilla) {
		this.selectionAsistenciaPlanilla = selectionAsistenciaPlanilla;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getAsistenciaMasiva() {
		return asistenciaMasiva;
	}

	public void setAsistenciaMasiva(String asistenciaMasiva) {
		this.asistenciaMasiva = asistenciaMasiva;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public int getCantClases() {
		return cantClases;
	}

	public void setCantClases(int cantClases) {
		this.cantClases = cantClases;
	}

	public int getClasesDesde() {
		return clasesDesde;
	}

	public void setClasesDesde(int clasesDesde) {
		this.clasesDesde = clasesDesde;
	}

	public int getClasesHasta() {
		return clasesHasta;
	}

	public void setClasesHasta(int clasesHasta) {
		this.clasesHasta = clasesHasta;
	}

	public boolean isIntervalo() {
		return intervalo;
	}

	public void setIntervalo(boolean intervalo) {
		this.intervalo = intervalo;
	}

	public boolean isReporte() {
		return reporte;
	}

	public void setReporte(boolean reporte) {
		this.reporte = reporte;
	}

	public String goAsistencias(Usuario user) {
		usuario = new Usuario();
		curso = new Curso();
		materia = new Materia();
		usuario = user;
		listaCursos = new ArrayList<Curso>();
		listaMaterias = new ArrayList<Materia>();
		listaClases = new ArrayList<Clase>();
		listaAsistencias = new ArrayList<Asistencia>();
		selectionAsistencias = new ArrayList<Asistencia>();
//		listaCursos = cursoDAO.getListaMatVig();
		listaCursos = cursoDAO.getLista(true);
		idCurso = 0;
		idMateria = 0;
		idClase = 0;
		fecha = new Date();
		return "asistencias";
	}
	
	public String goAsistencia(Usuario user) {
		usuario = new Usuario();
		curso = new Curso();
		materia = new Materia();
		fechaDesde = null;
		fechaHasta = null;
		usuario = user;
		listaCursos = new ArrayList<Curso>();
		listaMaterias = new ArrayList<Materia>();
		listaClases = new ArrayList<Clase>();		
		listaAsistencias = new ArrayList<Asistencia>();
		listaAsistenciaReporte = new ArrayList<AsistenciaReporte>();
		listaAsistenciaPlanilla = new ArrayList<AsistenciaReporte>();
		selectionAsistenciaPlanilla = new ArrayList<AsistenciaReporte>();
//		listaCursos = cursoDAO.getListaMatVig();
		listaCursos = cursoDAO.getLista(true);
		idCurso = 0;
		idMateria = 0;
		idClase = 0;
		clasesDesde = 0;
		clasesHasta = 0;
		intervalo = false;
		reporte = false;
		fecha = new Date();
		return "asistencia";
	}
	
	public void onChangeCurso() {
		listaMaterias = new ArrayList<Materia>();
		curso = new Curso();
		materia = new Materia();
		intervalo = false;
		reporte = false;
		idMateria = 0;
		idClase = 0;
		cantClases = 0;
		clasesDesde = 0;
		clasesHasta = 0;
		if (idCurso != 0) {
			curso = cursoDAO.get(idCurso);
			listaMaterias = materiaDAO.getLista(true, curso);
		}
	}
	
	public void onChangeMateria() {
		listaClases = new ArrayList<Clase>();
		materia = new Materia();
		idClase = 0;
		if (idMateria != 0) {
			materia = materiaDAO.get(idMateria);
			int cantClases = materia.getCantClases();
			for (int i = 1; i <= cantClases; i++) {
				Clase clase = new Clase();
				clase.setId(i);
				clase.setNombre("Clase " + i);
				listaClases.add(clase);
			}
		}
	}
	
	public void onCompleteAsistencia() {
		listaClases = new ArrayList<Clase>();
		materia = new Materia();
		cantClases = 0;
		if (idMateria != 0) {
			materia = materiaDAO.get(idMateria);
			int cClases = materia.getCantClases();
			if (cClases > 8 && clasesDesde == 0 && clasesHasta == 0) {
				intervalo = true;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LA MATERIA POSEE MAS DE 8 CLASES, POR FAVOR SELECCIONE UN INTERVALO", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {				
				int diferencia = clasesHasta - clasesDesde;
				if (clasesDesde != 0 && clasesHasta != 0) {
					if (diferencia < 8) {
						reporte = true;
						cantClases = diferencia;
						for (int i = clasesDesde; i <= clasesHasta; i++) {
							Clase clase = new Clase();
							clase.setId(i);
							clase.setNombre("Clase " + i);
							listaClases.add(clase);
						}
					} else {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "EL INTERVALO DEBE SER DE 7, POR EJ: DESDE 1 HASTA 8", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}					
				} else {
					cantClases = cClases - 1;
					reporte = true;
					for (int i = 1; i <= cClases; i++) {
						Clase clase = new Clase();
						clase.setId(i);
						clase.setNombre("Clase " + i);
						listaClases.add(clase);
					}
				}
				
//				int idMatri = curso.getMatricula().getId();
//				matricula = matriculaDAO.get(idMatri);
				listaAsistenciaReporte = new ArrayList<AsistenciaReporte>();
//				if (fechaDesde != null && fechaHasta != null) {
//					listaAsistencias = asistenciaDAO.getLista(curso, matricula, materia, fechaDesde, fechaHasta);
//				} else {
//					listaAsistencias = asistenciaDAO.getLista(curso, matricula, materia);
//				}			
				if (listaAsistencias.isEmpty()) {				
//					List<Inscripcione> listaInscripciones = inscripcionDAO.getListaOrderByAlumno(true, curso, matricula);
//					for (Inscripcione inscripcione : listaInscripciones) {
//						Asistencia asistencia = new Asistencia();
//						int dni = inscripcione.getDni();
//						Alumno alum = alumnoDAO.getPorDni(dni);
//						if (alum.getId() != 0) {
//							asistencia.setAlumno(alum);
//							asistencia.setNroClase(idClase);
//							asistencia.setNombreClase("Clase " + idClase);
//							listaAsistencias.add(asistencia);
//						}						
//					}
				} else {
					List<Asistencia> listAux = listaAsistencias;
//					List<Inscripcione> listaInscripciones = inscripcionDAO.getListaOrderByAlumno(true, curso, matricula);				
//					for (Inscripcione inscripcione : listaInscripciones) {
//						Asistencia asist = new Asistencia();
//						int dni = inscripcione.getDni();
//						Alumno alum = alumnoDAO.getPorDni(dni);
//						if (alum.getId() != 0) {
//							boolean noExiste = true;
//							for (Asistencia asistencia : listAux) {
//								Alumno alu = asistencia.getAlumno();
//								if (alum.getId() == alu.getId()) {
//									noExiste = false;
//								}
//							}
//							if (noExiste) {
//								asist.setAlumno(alum);
//								asist.setNroClase(idClase);
//								asist.setNombreClase("Clase " + idClase);
//								listaAsistencias.add(asist);
//							}
//						}						
//					}
					for (Asistencia asistencia : listaAsistencias) {
						fecha = asistencia.getFechaAlta();
					}
				}
				int idAlumno = 0;
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				for (Asistencia asist : listaAsistencias) {
					if (asist.getAlumno().getId() != idAlumno) {
						String nombreAlumno = asist.getAlumno().getNombreCompleto();
						HashMap<Integer, String> mapAsistencia = new LinkedHashMap<Integer, String>();
						AsistenciaReporte asisteReporte = new AsistenciaReporte();
						for (Clase clase : listaClases) {					
							int key = clase.getId();
							String valor = " - ";
							Asistencia asisten = new Asistencia();
//							if (fechaDesde != null && fechaHasta != null) {
//								asisten = asistenciaDAO.get(curso, matricula, materia, asist.getAlumno(), key, fechaDesde, fechaHasta);
//							} else {
//								asisten = asistenciaDAO.get(curso, matricula, materia, asist.getAlumno(), key);
//							}					
							if (asisten.getId() != 0) {						
								if (asisten.getFechaAlta() != null && !asisten.getPresente().isEmpty()) {
									valor = asisten.getPresente();
									valor = valor + " - " + formatoFecha.format(asisten.getFechaAlta());
								}						
							}					
							mapAsistencia.put(key, valor);
						}
						asisteReporte.setIdAlumno(asist.getAlumno().getId());
						asisteReporte.setAsistencias(mapAsistencia);
						asisteReporte.setNombreCompleto(nombreAlumno);
						listaAsistenciaReporte.add(asisteReporte);
					}
					idAlumno = asist.getAlumno().getId();
				}
				if (fechaDesde == null && fechaHasta == null) {
					listaAsistenciaPlanilla = listaAsistenciaReporte;
				}
			}			
		}		
	}	
	
	public void onChangeClase() {
		listaAsistencias = new ArrayList<Asistencia>();
		selectionAsistencias = new ArrayList<Asistencia>();
//		matricula = new Matricula();
		asistenciaMasiva = "-";
		if (idClase != 0) {			
//			int idMatri = curso.getMatricula().getId();
//			matricula = matriculaDAO.get(idMatri);
//			listaAsistencias = asistenciaDAO.getLista(curso, matricula, materia, idClase);
			if (listaAsistencias.isEmpty()) {
//				List<Inscripcione> listaInscripciones = inscripcionDAO.getLista(true, curso, matricula);
//				for (Inscripcione inscripcione : listaInscripciones) {
//					Asistencia asistencia = new Asistencia();
//					int dni = inscripcione.getDni();
//					Alumno alum = alumnoDAO.getPorDni(dni);
//					if (alum.getId() != 0) {
//						asistencia.setAlumno(alum);
//						asistencia.setNroClase(idClase);
//						asistencia.setNombreClase("Clase " + idClase);
//						listaAsistencias.add(asistencia);
//					}					
//				}
			} else {
				List<Asistencia> listAux = listaAsistencias;
//				List<Inscripcione> listaInscripciones = inscripcionDAO.getLista(true, curso, matricula);				
//				for (Inscripcione inscripcione : listaInscripciones) {
//					Asistencia asist = new Asistencia();
//					int dni = inscripcione.getDni();
//					Alumno alum = alumnoDAO.getPorDni(dni);
//					if (alum.getId() != 0) {
//						boolean noExiste = true;
//						for (Asistencia asistencia : listAux) {
//							Alumno alu = asistencia.getAlumno();
//							if (alum.getId() == alu.getId()) {
//								noExiste = false;
//							}
//						}
//						if (noExiste) {
//							asist.setAlumno(alum);
//							asist.setNroClase(idClase);
//							asist.setNombreClase("Clase " + idClase);
//							listaAsistencias.add(asist);
//						}
//					}					
//				}
				for (Asistencia asistencia : listaAsistencias) {
					fecha = asistencia.getFechaAlta();
				}
			}
		}
	}
	
	public void onRowEdit(Asistencia asistencia) {
        List<Asistencia> listAux = listaAsistencias;
        int idAlum1 = asistencia.getAlumno().getId();
        listaAsistencias = new ArrayList<Asistencia>();
        for (Asistencia asistencia2 : listAux) {
			int idAlum2 = asistencia2.getAlumno().getId();
			if (idAlum1 == idAlum2) {
				asistencia2.setPresente(asistencia.getPresente());
				asistencia2.setFechaAlta(asistencia.getFechaAlta());
			}
			listaAsistencias.add(asistencia2);
		}
    }
     
    public void onRowCancel() {
    	System.out.println("onRowCancel()");
    }
    
    public void actualizacionMasiva() {
    	System.out.println("actualizacionMasiva() - " + asistenciaMasiva + " - " + fecha);
    	if (!selectionAsistencias.isEmpty()) {
    		List<Asistencia> listaSeleccion = new ArrayList<Asistencia>();
    		List<Asistencia> listaAsistenciaAnterior = new ArrayList<Asistencia>();
        	listaSeleccion = selectionAsistencias;
        	listaAsistenciaAnterior = listaAsistencias;
        	selectionAsistencias = new ArrayList<Asistencia>();
        	listaAsistencias = new ArrayList<Asistencia>();
        	for (Asistencia asistenciaAnterior : listaAsistenciaAnterior) {
				for (Asistencia seleccionAsistencia : listaSeleccion) {
	    			seleccionAsistencia.setFechaAlta(fecha);
	    			seleccionAsistencia.setPresente(asistenciaMasiva);
	    			selectionAsistencias.add(seleccionAsistencia);
	    			if (asistenciaAnterior.getAlumno().getDni() == seleccionAsistencia.getAlumno().getDni()) {
						asistenciaAnterior.setFechaAlta(fecha);
						asistenciaAnterior.setPresente(asistenciaMasiva);
	    			}	    			
	    		}				
				listaAsistencias.add(asistenciaAnterior);
			}
        	
    	} else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Atención!", "Debe seleccionar al menos un item."));
    	}
    }
    
    public void guardarCambios() {
    	FacesMessage msg = null;
    	if (!listaAsistencias.isEmpty()) {
    		boolean inserto = true;
    		for (Asistencia asistencia : listaAsistencias) {
				if (asistencia.getId() != 0) {
					asistencia.setCurso(curso);
					//asistencia.setFechaAlta(fecha);					
					asistencia.setMateria(materia);
//					asistencia.setMatricula(matricula);
					asistencia.setNombreClase("Clase " + idClase);
					asistencia.setNroClase(idClase);
					asistencia.setUsuario(usuario);
					int idAsistencia = asistenciaDAO.update(asistencia);
					if (idAsistencia == 0) {
						inserto = false;
					}
				} else {
					asistencia.setCurso(curso);
					//asistencia.setFechaAlta(fecha);
					asistencia.setMateria(materia);
//					asistencia.setMatricula(matricula);
					asistencia.setNombreClase("Clase " + idClase);
					asistencia.setNroClase(idClase);
					asistencia.setUsuario(usuario);
					int idAsistencia = asistenciaDAO.insertar(asistencia);
					if (idAsistencia == 0) {
						inserto = false;
					}
				}
			}
    		if (inserto) {
    			listaAsistencias = new ArrayList<Asistencia>();
    			idClase = 0;
    			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ASISTENCIA REGISTRADA!", null);
    		} else {
    			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL REGISTRAR LAS ASISTENCIAS", null);
    		}
    	} else {
    		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "DEBEN EXISTIR ALUMNOS INSCRIPTOS PARA CARGAR ASISTENCIAS", null);
    	}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void generarReporte() {
    	try {
    		//Declaramos documento como un objeto Document
    	    //Asignamos el tamaño de hoja y los margenes	      
        	Document documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
        	
        	//writer es declarado como el método utilizado para escribir en el archivo
    	    PdfWriter writer = null;
    	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    	    
    	    //Obtenemos la instancia del archivo a utilizar
		    ServletOutputStream servletOutputStream = response.getOutputStream();
		    writer = PdfWriter.getInstance(documento, servletOutputStream);
		    facesContext.responseComplete();
    	    
		    //Agregamos un titulo al archivo
		    documento.addTitle("Asistencias");
		    
		    //Agregamos el autor del archivo
		    documento.addAuthor("fpalacio");
		    
		    //Abrimos el documento para edición
		    documento.open();
		    
		  //Obtenemos la instancia de la imagen
	        Image imagen = null;
						
//			imagen = Image.getInstance("C:/Source/NotificacionActas/WebContent/images/logo.png");				
			imagen = Image.getInstance(facesContext.getExternalContext().getResource("/images/logo_reporte.jpg"));
			//Alineamos la imagen a la derecha
		    imagen.setAlignment(Image.ALIGN_LEFT);
		    //Escalamos la imagen al 20%
		    imagen.scalePercent(5);
		    //Agregamos la imagen al documento
//		    documento.add(imagen);
		    		    
		    //Declaramos un texto como Paragraph
		    //Le podemos dar formado como alineación, tamaño y color a la fuente.
		    Paragraph parrafo = new Paragraph();
		    parrafo.setAlignment(Paragraph.ALIGN_LEFT);
		    parrafo.setFont(FontFactory.getFont("Sans",20,Font.BOLD, BaseColor.BLACK));
		    
		    Paragraph parrafoTit = new Paragraph();
		    parrafoTit.setAlignment(Paragraph.ALIGN_CENTER);
		    parrafoTit.setFont(FontFactory.getFont("Sans-Serif",20,Font.BOLD, BaseColor.BLACK));
		    parrafoTit.add(imagen);
		    parrafoTit.add("ASISTENCIAS");
		    parrafoTit.add("\n");
		    parrafoTit.add("\n");
		    
		    Paragraph parrafoEncab = new Paragraph();
		    parrafoEncab.setAlignment(Paragraph.ALIGN_LEFT);
		    parrafoEncab.setFont(FontFactory.getFont("Sans-Serif",15,Font.NORMAL, BaseColor.BLACK));
		    parrafoEncab.add("CURSO: ");
		    parrafoEncab.add(curso.getNombre());
		    parrafoEncab.add("   MATERIA: ");
		    parrafoEncab.add(materia.getNombre());		    
		    parrafoEncab.add("\n");		    
		    
		    if (fechaDesde != null && fechaHasta != null) {
		    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		    	parrafoEncab.add("FECHA DESDE: ");
		    	parrafoEncab.add(formato.format(fechaDesde));
		    	parrafoEncab.add("           HASTA: ");
		    	parrafoEncab.add(formato.format(fechaHasta));
		    	parrafoEncab.add("\n");
		    }
		    
		    parrafoEncab.add("\n");
		    
		    int colum = cantClases + 2;
		    int clase = 1;
		    if (clasesHasta != 0 && clasesDesde != 0) {
		    	colum = clasesHasta - clasesDesde + 2;
		    	clase = clasesDesde;
		    }
		    
		    PdfPTable tableOrd = new PdfPTable(colum);
	    	tableOrd.setWidthPercentage(100);
	    	tableOrd.setSpacingAfter(5);
	    	tableOrd.setSpacingBefore(10);
	    	
	    	for (int i = 0; i < colum; i++) {
	    		Phrase fraseOrd = new Phrase();
		    	fraseOrd.setFont(FontFactory.getFont("Sans-Serif",10,Font.NORMAL, BaseColor.WHITE));
		    	if (i == 0) {
		    		fraseOrd.add("ALUMNOS");
		    	} else {
		    		fraseOrd.add("CLASE " + clase);
		    		clase++;
		    	}
		    	PdfPCell cellOrd = new PdfPCell(fraseOrd);
		    	cellOrd.setBorderColor(BaseColor.BLACK);
		    	cellOrd.setBorderWidth(1);
		    	cellOrd.setBorderWidthLeft(1);
		    	cellOrd.setBackgroundColor(BaseColor.GRAY);
		    	cellOrd.setLeft(1);
		    	cellOrd.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		    	cellOrd.disableBorderSide(PdfPCell.LEFT);
		    	tableOrd.addCell(cellOrd);
			}
	    	
	    	List<AsistenciaReporte> lista = listaAsistenciaReporte;
	    	if (!selectionAsistenciaPlanilla.isEmpty()) {
	    		lista = selectionAsistenciaPlanilla;
	    	}
	    	
	    	for (AsistenciaReporte asistReporte : lista) {
	    		Phrase fraseOrd = new Phrase();	    		
		    	fraseOrd.setFont(FontFactory.getFont("Sans-Serif",9,Font.NORMAL, BaseColor.BLACK));
		    	fraseOrd.add(asistReporte.getNombreCompleto());		    	
		    	PdfPCell cellOrd = new PdfPCell(fraseOrd);
		    	cellOrd.setBorderColor(BaseColor.BLACK);
		    	cellOrd.setBorderWidth(1);
		    	cellOrd.setBorderWidthLeft(1);
		    	cellOrd.setBackgroundColor(BaseColor.WHITE);
		    	cellOrd.setLeft(1);
		    	cellOrd.setFixedHeight(30);
		    	cellOrd.setMinimumHeight(30);
		    	cellOrd.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		    	cellOrd.disableBorderSide(PdfPCell.LEFT);
		    	tableOrd.addCell(cellOrd);
		    	int inferior = 1;
		    	int superior = cantClases + 1;
		    	if (clasesDesde != 0 && clasesHasta != 0) {
		    		inferior = clasesDesde;
		    		superior = clasesHasta;
		    	}
		    	for (int i = inferior; i <= superior; i++) {
		    		Phrase fraseOrd1 = new Phrase();
			    	fraseOrd1.setFont(FontFactory.getFont("Sans-Serif",9,Font.NORMAL, BaseColor.BLACK));
			    	fraseOrd1.add(asistReporte.getAsistencia(i));		    	
			    	PdfPCell cellOrd1 = new PdfPCell(fraseOrd1);
			    	cellOrd1.setBorderColor(BaseColor.BLACK);
			    	cellOrd1.setBorderWidth(1);
			    	cellOrd1.setBorderWidthLeft(1);
			    	cellOrd1.setBackgroundColor(BaseColor.WHITE);
			    	cellOrd1.setLeft(1);
			    	cellOrd1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			    	cellOrd1.disableBorderSide(PdfPCell.LEFT);
			    	tableOrd.addCell(cellOrd1);
				}
	    	}
		    parrafo.add(tableOrd);
		    
		    documento.add(parrafoTit);
		    documento.add(parrafoEncab);
		    documento.add(parrafo);
    	    
		    
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
    	} catch (Exception e) {
    		e.printStackTrace();    		
    	}
    }
    
    public void generarPlanilla() {
    	try {
    		//Declaramos documento como un objeto Document
    	    //Asignamos el tamaño de hoja y los margenes	      
        	Document documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
        	
        	//writer es declarado como el método utilizado para escribir en el archivo
    	    PdfWriter writer = null;
    	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    	    
    	    //Obtenemos la instancia del archivo a utilizar
		    ServletOutputStream servletOutputStream = response.getOutputStream();
		    writer = PdfWriter.getInstance(documento, servletOutputStream);
		    facesContext.responseComplete();
    	    
		    //Agregamos un titulo al archivo
		    documento.addTitle("PlanillasAsistencias");
		    
		    //Agregamos el autor del archivo
		    documento.addAuthor("fpalacio");		    
		    
		    //Abrimos el documento para edición
		    documento.open();
		    
		  //Obtenemos la instancia de la imagen
	        Image imagen = null;
						
//			imagen = Image.getInstance("C:/Source/NotificacionActas/WebContent/images/logo.png");				
			imagen = Image.getInstance(facesContext.getExternalContext().getResource("/images/logo_reporte.jpg"));
			//Alineamos la imagen a la derecha
		    imagen.setAlignment(Image.ALIGN_LEFT);
		    //Escalamos la imagen al 20%
		    imagen.scalePercent(5);
		    //Agregamos la imagen al documento
//		    documento.add(imagen);
		    		    
		    //Declaramos un texto como Paragraph
		    //Le podemos dar formado como alineación, tamaño y color a la fuente.
		    Paragraph parrafo = new Paragraph();
		    parrafo.setAlignment(Paragraph.ALIGN_LEFT);
		    parrafo.setFont(FontFactory.getFont("Sans",20,Font.BOLD, BaseColor.BLACK));
		    
		    Paragraph parrafoTit = new Paragraph();
		    parrafoTit.setAlignment(Paragraph.ALIGN_CENTER);
		    parrafoTit.setFont(FontFactory.getFont("Sans-Serif",20,Font.BOLD, BaseColor.BLACK));
		    parrafoTit.add(imagen);
		    parrafoTit.add("PLANILLAS DE ASISTENCIAS");
		    parrafoTit.add("\n");
		    parrafoTit.add("\n");
		    
		    Paragraph parrafoEncab = new Paragraph();
		    parrafoEncab.setAlignment(Paragraph.ALIGN_LEFT);
		    parrafoEncab.setFont(FontFactory.getFont("Sans-Serif",15,Font.NORMAL, BaseColor.BLACK));
		    parrafoEncab.add("CURSO: ");
		    parrafoEncab.add(curso.getNombre());
		    parrafoEncab.add("   MATERIA: ");
		    parrafoEncab.add(materia.getNombre());
		    parrafoEncab.add("\n");
		    parrafoEncab.add("\n");
		    
		    int colum = cantClases + 2;
		    int clase = 1;
		    if (clasesHasta != 0 && clasesDesde != 0) {
		    	colum = clasesHasta - clasesDesde + 2;
		    	clase = clasesDesde;
		    }
		    
		    PdfPTable tableOrd = new PdfPTable(colum);
	    	tableOrd.setWidthPercentage(100);
	    	tableOrd.setSpacingAfter(5);
	    	tableOrd.setSpacingBefore(10);	  	    	
	    	
	    	for (int i = 0; i < colum; i++) {
	    		Phrase fraseOrd = new Phrase();
		    	fraseOrd.setFont(FontFactory.getFont("Sans-Serif",10,Font.NORMAL, BaseColor.WHITE));
		    	if (i == 0) {
		    		fraseOrd.add("ALUMNOS");
		    	} else {
		    		fraseOrd.add("CLASE " + clase);
		    		clase++;
		    	}
		    	PdfPCell cellOrd = new PdfPCell(fraseOrd);
		    	cellOrd.setBorderColor(BaseColor.BLACK);
		    	cellOrd.setBorderWidth(1);
		    	cellOrd.setBorderWidthLeft(1);
		    	cellOrd.setBackgroundColor(BaseColor.GRAY);
		    	cellOrd.setLeft(1);
		    	cellOrd.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		    	cellOrd.disableBorderSide(PdfPCell.LEFT);
		    	tableOrd.addCell(cellOrd);
			}
	    	
	    	List<AsistenciaReporte> lista = listaAsistenciaPlanilla;
	    	if (!selectionAsistenciaPlanilla.isEmpty()) {
	    		lista = selectionAsistenciaPlanilla;
	    	}
	    	
//	    	for (AsistenciaReporte asistReporte : listaAsistenciaPlanilla) {
	    	for (AsistenciaReporte asistReporte : lista) {
	    		Phrase fraseOrd = new Phrase();
		    	fraseOrd.setFont(FontFactory.getFont("Sans-Serif",9,Font.NORMAL, BaseColor.BLACK));
		    	fraseOrd.add(asistReporte.getNombreCompleto());		    	
		    	PdfPCell cellOrd = new PdfPCell(fraseOrd);
		    	cellOrd.setBorderColor(BaseColor.BLACK);
		    	cellOrd.setBorderWidth(1);
		    	cellOrd.setBorderWidthLeft(1);
		    	cellOrd.setBackgroundColor(BaseColor.WHITE);
		    	cellOrd.setLeft(1);
		    	cellOrd.setFixedHeight(30);
//		    	cellOrd.setMinimumHeight(30);
		    	cellOrd.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		    	cellOrd.disableBorderSide(PdfPCell.LEFT);
		    	tableOrd.addCell(cellOrd);
		    	int inferior = 1;
		    	int superior = cantClases + 1;
		    	if (clasesDesde != 0 && clasesHasta != 0) {
		    		inferior = clasesDesde;
		    		superior = clasesHasta;
		    	}
		    	for (int i = inferior; i <= superior; i++) {
		    		Phrase fraseOrd1 = new Phrase();		    		
			    	fraseOrd1.setFont(FontFactory.getFont("Sans-Serif",9,Font.NORMAL, BaseColor.BLACK));
			    	fraseOrd1.add(" ");		    	
			    	PdfPCell cellOrd1 = new PdfPCell(fraseOrd1);
			    	cellOrd1.setBorderColor(BaseColor.BLACK);
			    	cellOrd1.setBorderWidth(1);
			    	cellOrd1.setBorderWidthLeft(1);
			    	cellOrd1.setBackgroundColor(BaseColor.WHITE);
			    	cellOrd1.setLeft(1);
			    	cellOrd1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			    	cellOrd1.disableBorderSide(PdfPCell.LEFT);			    	
			    	tableOrd.addCell(cellOrd1);			    	
				}
	    	}
		    parrafo.add(tableOrd);
		    
		    documento.add(parrafoTit);
		    documento.add(parrafoEncab);
		    documento.add(parrafo);
    	    
		    
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }    
    

}
