package bd;

import java.util.ArrayList;

import javax.ejb.Singleton;

import model.Post;

@Singleton
public class BDPost {
	private ArrayList<Post> post  = new ArrayList<Post>();

	public BDPost() {
	}
	
	public int proximoId() {
		return post.size();
	}

	public ArrayList<Post> getPosts() {
		return post;
	}

	public void setPost(Post post) {
		this.post.add(post);
	}
}
