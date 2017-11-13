package view;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import controller.ControladorUsuario;
import model.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
	
	public String registrar(){
		Usuario usuario = new Usuario();
		usuario.setNombreusuario(nombreusuario);
		usuario.setEmail(email);
		usuario.setPassword(password);
		
		    try{
		    	CtrlUsuario.create(usuario);
		    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Registrado Correctamente",null);
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    } catch (Exception e) {
		    	e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se a podida Registrar el usuario",null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
		    return "";
		    }
	
	}

