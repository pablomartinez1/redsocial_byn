package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import model.Comentario;
import model.Post;
import model.Usuario;

@Stateless
public class ControladorComentario {
	@PersistenceContext
	private EntityManager entityManager;
	private List<Comentario> lista = new ArrayList<Comentario>();
	
	public String Crear(Usuario usuario, Post post, String contenido) {
		post = entityManager.merge(post);
		Comentario comentario = new Comentario();
		comentario.setFecha(new Date());
		comentario.setPost(post);
		comentario.setUsuario(usuario);
		comentario.setContenido(contenido);
		try{
				entityManager.persist(comentario);
		    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comentario Creado Correctamente",null);
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    	return "home";
		    } catch (Exception e) {
		    	e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se a podido Crear Correctamente el Comentario",null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
		    return "";
    }
	
	public List<Comentario> listaComentarios(Post post){
		CriteriaQuery<Post> cq = entityManager.getCriteriaBuilder().createQuery(Post.class);
		cq.select(cq.from(Post.class));
		return lista;
	}
}

