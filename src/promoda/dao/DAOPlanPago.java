package promoda.dao;

import java.util.List;

import promoda.model.Inscripcione;
import promoda.model.Matricula;
import promoda.model.PlanPago;

public interface DAOPlanPago {
	
	public int insertar(PlanPago planPago);
	
	public int update(PlanPago planPago);
	
	public int delete(PlanPago planPago);
	
	public PlanPago get(int id);
	
	public PlanPago get(Inscripcione inscripcione, Matricula matricula);
	
	public List<PlanPago> getLista();
	
	public List<PlanPago> getLista(Inscripcione inscripcione);
	
	public List<PlanPago> getLista(Matricula matricula);

}
