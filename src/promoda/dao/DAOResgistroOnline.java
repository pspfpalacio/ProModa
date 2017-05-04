package promoda.dao;

import java.util.List;

import promoda.model.RegistroOnline;

public interface DAOResgistroOnline {
	
	public int insertar(RegistroOnline registroOnline);
	
	public List<RegistroOnline> getLista();

}
