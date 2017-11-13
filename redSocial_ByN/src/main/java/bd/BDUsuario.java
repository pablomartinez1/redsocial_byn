package bd;

import java.util.ArrayList;

import javax.ejb.Singleton;

import model.Usuario;

public class BDUsuario {
	private ArrayList<Usuario> usuario = new ArrayList<Usuario>();
	
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
