package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;


import model.Post;
import model.Usuario;

@Stateless
public class ControladorPost {
	
	@PersistenceContext
	private EntityManager entity;
	
	private List<Post> lista = new ArrayList<Post>();

	public void create(Post post) {
		entity.persist(post);
	}
	
	public List<Post> listaPosts(){
		CriteriaQuery<Post> cq = entity.getCriteriaBuilder().createQuery(Post.class);
		cq.select(cq.from(Post.class));
		return entity.createQuery(cq).getResultList();
	}
	
	public List<Post> postUsuario(Usuario usuario){
		CriteriaQuery<Post> cq = entity.getCriteriaBuilder().createQuery(Post.class);
		cq.select(cq.from(Post.class));
		
		
		for(Post post : entity.createQuery(cq).getResultList()) {
			
			if(post.getUsuario().getNombreusuario().equals(usuario.getNombreusuario()))
				
				lista.add(post);
		}
		
		
		return lista;
	}
}
