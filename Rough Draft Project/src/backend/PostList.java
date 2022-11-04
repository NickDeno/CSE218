package backend;

import java.io.Serializable;
import java.util.LinkedList;

public class PostList implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Post> postList;
	
	public PostList() {
		postList = new LinkedList<Post>();
	}
	
	public void add(Post p) {
		postList.add(p);
	}
	
	public void display() {
		for(Post p: postList) {
			System.out.println(p.toString());
		}
		System.out.println();
	}
}
