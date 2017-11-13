package view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import controller.ControladorPost;
import model.Post;

@Named
@SessionScoped
public class MbPost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject ControladorPost CtrlPost;
	@Inject MbAutenticar mbAutenticar;
	
	private String contenido;
	
	public List<Post> listaPosts() {
		return CtrlPost.listaPosts();
	}
	
	public List<Post> lista() {
		return CtrlPost.postUsuario(mbAutenticar.getUsuarioActual());
	}

	public String crear() {
		Post post = new Post();
		post.setUsuario(mbAutenticar.getUsuarioActual());
		post.setFecha(new Date ());
		post.setId_user(mbAutenticar.getUsuarioActual().getId());
		post.setContenido(getContenido());
		
		 try{
			 CtrlPost.create(post);
		    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Post Creado Correctamente",null);
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    	return "home";
		    } catch (Exception e) {
		    	e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se a podido Crear Correctamente el Post",null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
		    return "";
		    }
		
	
	
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
