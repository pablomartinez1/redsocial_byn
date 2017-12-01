package view;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import controller.ControladorComentario;
import model.Post;
import model.Comentario;

@Named
public class MbComentario {
	@Inject
	private ControladorComentario CtrlComentario;
	
	@Inject
	private MbAutenticar mbAutenticar;
	
	@NotNull
	@Size(min=2, max=255)
	private String comentario;
	
	public void crear(Post post) {
		CtrlComentario.Crear(mbAutenticar.getUsuarioActual(), post, comentario);
	}
	
	public List<Comentario> listaComentarios(Post post) {
		return CtrlComentario.listaComentarios(post);
	}
	
	public List<Comentario> getComentarios() {
		return CtrlComentario.getComentario();
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
