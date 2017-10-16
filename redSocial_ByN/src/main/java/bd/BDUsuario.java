package bd;

import java.util.ArrayList;

import javax.ejb.Singleton;

import model.Usuario;

@Singleton
public class BDUsuario {
	private ArrayList<Usuario> usuario = new ArrayList<Usuario>();

	public BDUsuario() {
		Usuario usuario = new Usuario("admin","12345", "admin@admin.com");
		usuario.setId(proximoId());
		
		setUsuario(usuario);
	}
	
	public int proximoId() {
		return usuario.size();
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario.add(usuario);
	}
}
