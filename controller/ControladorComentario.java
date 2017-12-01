package controller;

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
	
	public void Crear(Usuario usuario, Post post, String contenido) {
		post = entityManager.merge(post);
		Comentario comentario = new Comentario();
		comentario.setFecha(new Date());
		comentario.setPost(post);
		comentario.setUsuario(usuario);
		comentario.setContenido(contenido);
		
		 try{
			 	entityManager.persist(comentario);
		    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comentario Correctamente Creado",null);
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
		    	
		    } catch (Exception e) {
		    	e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se a podido Crear Correctamente el Comentario",null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				}
		    
		    }
		
		    
    
	
	public List<Comentario> getComentario() {
		CriteriaQuery<Comentario> cq = entityManager.getCriteriaBuilder().createQuery(Comentario.class);
		cq.select(cq.from(Comentario.class));
		return entityManager.createQuery(cq).getResultList();
	}
	
	public List<Comentario> listaComentarios(Post post){
		
		String queryComment ="Select c From Comentario c where c.post = :post";
		TypedQuery<Comentario> tq = entityManager.createQuery(queryComment, Comentario.class);
		tq.setParameter("post", post);
		return tq.getResultList();
		
	}
}

