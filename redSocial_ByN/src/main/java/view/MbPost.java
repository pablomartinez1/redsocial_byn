package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.ControladorPost;
import model.Post;
import model.Usuario;

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
	
	public String crear(){
		Post post = new Post(usuarioLogeado(), new Date(), contenido);
		CtrlPost.crear(post);
		contenido = null;
		
		return "home?faces-redirect=true";
	}
	
	public ArrayList<Post> obtenerListaPosts() {
		return CtrlPost.listaPosts();
	}
	
	public ArrayList<Post> obtenerPostsUsuario() {
		return CtrlPost.listaPosts(usuarioLogeado());
	}
	
	public Usuario usuarioLogeado() {
		return mbAutenticar.usuarioLogeado();
 	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
