package view;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import controller.ControladorUsuario;
import model.Usuario;

@Named
public class MbRegistrar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ControladorUsuario CtrlUsuario;
	
	private String nombreusuario;
	private String email;
	private String password;
	
	public String registrar(){
		Usuario usuario = new Usuario(nombreusuario, password, email);
		CtrlUsuario.registrar(usuario);
		return "index";
	}
	
	public String getNombreUsuario() {
		return nombreusuario;
	}

	public void setNombreUsuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
