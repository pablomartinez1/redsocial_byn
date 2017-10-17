package controller;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import bd.BDPost;
import model.Post;
import model.Usuario;

@Stateless
public class ControladorPost {
	@Inject BDPost bd;
	
	public void crear(Post post) {
		post.setId(bd.proximoId());
		bd.setPost(post);
	}
	
	public ArrayList<Post> listaPosts(){
		return bd.getPosts();
	}
	
	public ArrayList<Post> listaPosts(Usuario usuario){
		ArrayList<Post> lista = new ArrayList<Post>();
		for(Post post : bd.getPosts()){
			if(post.getUsuario().getUsuario() == usuario.getUsuario()) {
				lista.add(post);
			}
		}
		
		return lista;
	}
}
