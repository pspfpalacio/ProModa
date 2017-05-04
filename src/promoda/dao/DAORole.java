package promoda.dao;

import java.util.List;

import promoda.model.Role;

public interface DAORole {
	
	public int insertar(Role role);
	
	public int update(Role role);
	
	public Role get(int id);
	
	public List<Role> getLista();

}
