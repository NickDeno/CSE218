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
		postList.addFirst(p);
	}
	
	public Post get(int idx) {
		return postList.get(idx);
	}
	
	public void display() {
		for(Post p: postList) {
			System.out.println(p.toString());
		}
		System.out.println();
	}
	
	public int size() {
		return postList.size();
	}
	
	@Override
	public String toString() {
		String  s = "";
		for(int i = 0; i < postList.size(); i++) {
			s += postList.get(i).toString() + ", ";
		}
		return s;
	}
}
