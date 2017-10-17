package model;

import java.util.Date;

public class Post {
	private int id;
	private Usuario usuario;
	private Date fecha;
	private String contenido;

	public Post(Usuario usuario, Date fecha, String contenido) {
		super();
		this.usuario = usuario;
		this.fecha = fecha;
		this.contenido = contenido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
