package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

public class PostCenter implements Serializable{
	private static final long serialVersionUID = 1L;
	private LinkedList<Post> postList;	

	public PostCenter() {
		postList = new LinkedList<Post>();
	}
	
	public void addPost(Post post) {
		postList.add(post);
	}
	
	public boolean removePost(Post post) {
		return postList.remove(post);
	}
	
	public boolean removePost(UUID uuid) {
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getUuid().equals(uuid)) {
				postList.remove(post);
				return true;
			}
		}
		return false;

	}

	public Post getPost(UUID uuid) {
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getUuid().equals(uuid)) {
				return post;
			}
		}
		return null;
	}
	
	public LinkedList<Post> getPosts(){
		return postList;
	}

	public void display() {
		postList.forEach(post -> System.out.println(post.toString()));
	}
	
	//O(n) since each posts' topic has to be checked to see if it matches given topic
	public LinkedList<Post> searchByTopic(String topic) {
		LinkedList<Post> temp = new LinkedList<Post>();
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getTopic().equals(topic)) {
				temp.add(post);
			}
		}
		return temp;
	}
	
	//O(n) since each posts' title has to be searched to see if matches given title
	public LinkedList<Post> searchByTitle(String title) {
		LinkedList<Post> temp = new LinkedList<Post>();
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getTitle().equals(title)) {
				temp.add(post);
			}
		}
		return temp;
	}
	
	@Override
	public String toString() {
		String  s = "";	
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			s += itr.next().toString();
		}
		return s;
	}
	
}
