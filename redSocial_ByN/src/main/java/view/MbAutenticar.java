package view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}



	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public Usuario usuarioLogeado(){ 
		return usuarioActual;
	}


	public boolean estaLogeado(){ 
		return usuarioActual != null;
	}
	


	public String loggin() {
		try{
	    	usuarioActual = CtrlUsuario.getUserAuth(nombreusuario, password, email);
	    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Logeado Correctamente",null);
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
	    	
	    	return "home";
	    } catch (Exception e) {
	    	e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se a podido Inciar Sesion",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			nombreusuario = null;
			password = null;
			email = null;
			return "";
			
		}
	}

	public String logout() {
		usuarioActual = null;
		return "index?home-faces-redirect=true";
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	


}
