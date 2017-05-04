package promoda.clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import promoda.dao.DAOCaja;
import promoda.dao.impl.DAOCajaImpl;
import promoda.model.Caja;
import promoda.model.Usuario;

public class CajasMov implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DAOCaja cajaDAO = new DAOCajaImpl();
	
	public DAOCaja getCajaDAO() {
		return cajaDAO;
	}

	public void setCajaDAO(DAOCaja cajaDAO) {
		this.cajaDAO = cajaDAO;
	}

	//tipo = 1 ENTRADA, tipo = 2 SALIDA
	public int generarMovimiento(Date fecha, int tipo, float monto, int idMovimiento, String nombreMovimiento, 
			String concepto, String descripcion, Usuario user) {
		try {			
			List<Caja> lista = cajaDAO.getListaOrdenada();
			boolean empty = lista.isEmpty();
			if (empty) {
				Caja caja = new Caja();
				caja.setConcepto(descripcion);
				caja.setEnabled(true);
				if (tipo == 1) {
					caja.setEntrada(monto);
					caja.setSalida(0);
				}
				if (tipo == 2) {
					caja.setEntrada(0);
					caja.setSalida(monto);
				}
				caja.setFecha(fecha);
				caja.setFechaAlta(new Date());
				caja.setIdMovimiento(idMovimiento);
				caja.setMonto(monto);
				caja.setNombreMovimiento(nombreMovimiento);
				caja.setTipo(concepto);
				caja.setTotal(monto);
				caja.setUsuario1(user);
				int inserto = cajaDAO.insertar(caja);
				return inserto;
			} else {
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				List<Caja> listPosterior = new ArrayList<Caja>();
				Caja cajaAnterior = new Caja();		
				Caja cajaInsertar = new Caja();
				cajaInsertar.setConcepto(descripcion);
				cajaInsertar.setEnabled(true);
				if (tipo == 1) {
					cajaInsertar.setEntrada(monto);
					cajaInsertar.setSalida(0);
				}
				if (tipo == 2) {
					cajaInsertar.setEntrada(0);
					cajaInsertar.setSalida(monto);
				}
				cajaInsertar.setFecha(fecha);
				cajaInsertar.setFechaAlta(new Date());
				cajaInsertar.setIdMovimiento(idMovimiento);
				cajaInsertar.setMonto(monto);
				cajaInsertar.setNombreMovimiento(nombreMovimiento);
				cajaInsertar.setTipo(concepto);
				cajaInsertar.setUsuario1(user);
				for (Caja caja : lista) {
					String inicio = formatoFecha.format(caja.getFecha());
					Date fechaCuenta2 = formatoFecha.parse(inicio);
					fechaCuenta2.setHours(0);
					fechaCuenta2.setMinutes(0);
					fechaCuenta2.setSeconds(0);
					int comparaFecha = fechaCuenta2.compareTo(fecha);
					if(comparaFecha > 0){
						listPosterior.add(caja);
					}else{
						cajaAnterior = new Caja();
						cajaAnterior = caja;
					}
				}
				float total = cajaAnterior.getTotal();
				if(tipo == 1){
					total = total + monto;
				}
				if(tipo == 2){
					total = total - monto;
				}
				cajaInsertar.setTotal(total);
				int inserto = cajaDAO.insertar(cajaInsertar);
				if (inserto != 0) {
					boolean actualizo = true;
					for (Caja caja : listPosterior) {
//						float tot = caja.getTotal();
						if(caja.getEntrada() != 0){					
							total = total + caja.getEntrada();
						}
						if(caja.getSalida() != 0){
							total = total - caja.getSalida();
						}
						caja.setTotal(total);
						int updateCaja = cajaDAO.update(caja);
						if (updateCaja == 0) {
							actualizo = false;
						}
					}
					if (actualizo) {
						return inserto;
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int eliminarMovimiento(int idMovimiento, String nombreMovimiento, Usuario user) {		
		try {		
			Caja cajaEliminar = cajaDAO.get(idMovimiento, nombreMovimiento);			
			float entrada = cajaEliminar.getEntrada();
			float salida = cajaEliminar.getSalida();
			Date fechaInicio = cajaEliminar.getFecha();
			List<Caja> listAux = cajaDAO.getListaOrdenada(fechaInicio);
			List<Caja> listAux2 = new ArrayList<Caja>();			
			Caja ultimaCaja = new Caja();
			float total = 0;
			boolean pasoCajaAnterior = false;
			for (Caja caja : listAux) {
				if(caja.getId() != cajaEliminar.getId()){
					total = caja.getTotal();
					if(pasoCajaAnterior){
						listAux2.add(caja);
					}else{
						ultimaCaja = caja;
					}
				}else{
					pasoCajaAnterior = true;
				}
			}
			boolean update = true;
			if (listAux2.isEmpty()) {
				if (ultimaCaja.getId() != 0) {
					total = ultimaCaja.getTotal();
				} else {
					if (entrada != 0) {
						total = cajaEliminar.getTotal() - entrada;
					}
					if (salida != 0) {
						total = cajaEliminar.getTotal() + salida;
					}
				}				
			} else {
				for(Caja caja : listAux2){
					float tot = caja.getTotal();
					if(entrada != 0){					
						tot = tot - entrada;
					}
					if(salida != 0){
						tot = tot + salida;
					}
					caja.setTotal(tot);					
					if(cajaDAO.update(caja) == 0){
						update = false;
					}
				}
			}
			cajaEliminar.setEnabled(false);
			cajaEliminar.setFechaBaja(new Date());
			cajaEliminar.setUsuario2(user);
			int delete = cajaDAO.update(cajaEliminar);			
			if(delete != 0){				
				return 1;
			}else{
				return 0;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

}
