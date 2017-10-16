package view;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.ControladorUsuario;
import model.Usuario;

@Named
@SessionScoped
public class MbAutenticar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject ControladorUsuario CtrlUsuario;
	
	private String nombreusuario;
	private String password;
	private String email;

	private Usuario usuarioActual;
	
	public boolean estaLogeado(){ 
		return usuarioActual != null;
	}
	
	public ArrayList<Usuario> obtenerListaUsuarios() {
		return CtrlUsuario.listaUsuarios();
	}
	
	public String logear(){
		usuarioActual = CtrlUsuario.getUsuarioAutenticado(nombreusuario, password, email);
		nombreusuario = null;
		password = null;
		email = null;
		if(estaLogeado())
			return "index?faces-redirect=true";
		else 
			return null;
	}
	
	public String deslogear(){
		usuarioActual = null;
		return "index";
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNombreUsuario() {
		return nombreusuario;
	}
	
	public void setNombreUsuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
}
