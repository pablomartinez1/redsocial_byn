package view;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import controller.ControladorImagen;
import controller.ControladorUsuario;
import model.Imagen;
import model.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@MultipartConfig(location="/tmp",
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5,
maxRequestSize=1024*1024*5*5)
public class MbRegistrar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ControladorUsuario CtrlUsuario;
	@Inject private ControladorImagen CtrlImagen;
	
	
	private String nombreusuario;
	private String email;
	private String password;
	private Part archivo;
	
	
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
	
	public String registrar() throws IOException{
		Usuario usuario = new Usuario();
		Imagen img = null;
		
		usuario.setNombreusuario(nombreusuario);
		usuario.setEmail(email);
		usuario.setPassword(password);
		if(archivo != null && archivo.getSize() > 0 && archivo.getContentType().startsWith("image/")){
			img = CtrlImagen.upload(archivo);
		}
		usuario.setImagen(img);
		
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
	
	public Part getArchivo() {
		return archivo;
	}

	public void setArchivo(Part archivo) {
		this.archivo = archivo;
	}
	
}

