package promoda.clases;

import java.io.Serializable;

public class Contacto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String telCel;
	private String telFijo;
	private String email;
	
	public String getTelCel() {
		return telCel;
	}
	public void setTelCel(String telCel) {
		this.telCel = telCel;
	}
	public String getTelFijo() {
		return telFijo;
	}
	public void setTelFijo(String telFijo) {
		this.telFijo = telFijo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
