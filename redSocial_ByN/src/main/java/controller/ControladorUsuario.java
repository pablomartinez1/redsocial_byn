package controller;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import bd.BDUsuario;
import model.Usuario;

@Stateless
public class ControladorUsuario {
	@Inject BDUsuario bd;
	
	public Usuario getUsuarioAutenticado(String nombreusuario, String password, String email){
		for(Usuario usuario : bd.getUsuarios()){
			if (usuario.getUsuario().equals(nombreusuario) 
			 && usuario.getPassword().equals(password)
			 && usuario.getEmail().equals(email)){
					return usuario;
			}
		}
		return null;
	}
	
	public boolean existeNombreUsuario(String nombreusuario){
		for(Usuario usuario : bd.getUsuarios()){
			if (usuario.getUsuario().equals(nombreusuario)){
					return true;
			}
		}
		return false;
	}
	
	public boolean existeEmailUsuario(String email){
		for(Usuario usuario : bd.getUsuarios()){
			if (usuario.getEmail().equals(email)){
					return true;
			}
		}
		return false;
	}
	
	public void registrar(Usuario usuario) {
		usuario.setId(bd.proximoId());
		bd.setUsuario(usuario);
	}
	
	public ArrayList<Usuario> listaUsuarios() {
		return bd.getUsuarios();
	}
}
