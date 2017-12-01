package view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import controller.ControladorImagen;
import controller.ControladorPost;
import model.Imagen;
import model.Post;

@Named
//@SessionScoped
@MultipartConfig(location="/tmp",
	fileSizeThreshold=1024*1024, 
	maxFileSize=1024*1024*5,
	maxRequestSize=1024*1024*5*5)
public class MbPost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject ControladorPost CtrlPost;
	@Inject MbAutenticar mbAutenticar;
	@Inject private ControladorImagen CtrlImagen;
	
	private Part archivo;
	
	private String contenido;
	
	public List<Post> listaPosts() {
		return CtrlPost.listaPosts();
	}
	
	public List<Post> lista() {
		return CtrlPost.postUsuario(mbAutenticar.getUsuarioActual());
	}

	public String crear() throws IOException {
		Post post = new Post();
		Imagen img = null;
		
		post.setUsuario(mbAutenticar.getUsuarioActual());
		post.setFecha(new Date ());
		post.setId_user(mbAutenticar.getUsuarioActual().getId());
		post.setContenido(getContenido());
		if(archivo != null && archivo.getSize() > 0 && archivo.getContentType().startsWith("image/")){
			img = CtrlImagen.upload(archivo);
		}
		post.setImagen(img);
		
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
	
	public Part getArchivo() {
		return archivo;
	}

	public void setArchivo(Part archivo) {
		this.archivo = archivo;
	}
}
