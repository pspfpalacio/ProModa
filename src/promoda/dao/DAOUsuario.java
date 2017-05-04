package promoda.dao;

import java.util.List;

import promoda.model.Alumno;
import promoda.model.Profesore;
import promoda.model.Usuario;

public interface DAOUsuario {
	
	public int insertar(Usuario usuario);
	
	public int update(Usuario usuario);
	
	public Usuario get(int id);
	
	public Usuario get(String username, String hash);
	
	public Usuario get(String username);
	
	public Usuario getLogin(String username, String hash);
	
	public Usuario get(Alumno alumno);
	
	public Usuario get(Profesore profesor);
	
	public List<Usuario> getLista();
	
	public List<Usuario> getLista(boolean estado);

}
