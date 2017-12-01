package controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import model.Usuario;

@Stateless
public class ControladorUsuario {
	@PersistenceContext
	private EntityManager entity;

	public Usuario byId(int id) {
		return entity.find(Usuario.class, id);
	}

	public void create(Usuario user) {
		entity.persist(user);
	}

	public List<Usuario> getUsers() {
		CriteriaQuery<Usuario> cq = entity.getCriteriaBuilder().createQuery(Usuario.class);
		cq.select(cq.from(Usuario.class));
		return entity.createQuery(cq).getResultList();
	}

	public Usuario getUserAuth(String nombreusuario, String password,String email) {

		String hql = "Select u from Usuario u where u.nombreusuario = :nombreusuario AND u.password = :password AND u.email = :email";
		TypedQuery<Usuario> q = entity.createQuery(hql, Usuario.class);
		q.setParameter("nombreusuario", nombreusuario);
		q.setParameter("email", email);
		q.setParameter("password", password);

		if (q.getSingleResult().getNombreusuario().equals(nombreusuario) && q.getSingleResult().getPassword().equals(password) && q.getSingleResult().getEmail().equals(email)) {
			return q.getSingleResult();
		}

		return null;
	}
	
	
}
