package promoda.dao;

import java.util.List;

import promoda.model.Role;
import promoda.model.RolesVista;
import promoda.model.Vista;

public interface DAORolesVista {
	
	public int insertar(RolesVista rolesVista);
	
	public int update(RolesVista rolesVista);
	
	public RolesVista get(int id);
	
	public RolesVista get(Role role, Vista vista);
	
	public List<RolesVista> getLista();
	
	public List<RolesVista> getLista(Role role);
	
	public List<RolesVista> getLista(Vista vista);

}
