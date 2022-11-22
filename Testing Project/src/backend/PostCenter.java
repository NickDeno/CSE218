package backend;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

//Singleton Class
public class PostCenter implements Serializable{
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<UUID, Post> postMap;	
	private static PostCenter instance;

	private PostCenter() {
		postMap = new LinkedHashMap<UUID, Post>();
	}
	
	public static PostCenter getInstance() {
		if(instance == null && new File("backupData/Posts.dat").exists() == false) { //Condition will be true on first ever launch of program
			instance = new PostCenter();
        } else if(instance == null) { //Condition will be true on every other launch of program besides first one
        	instance = Utilities.restorePostCenter();
        }
		return instance; 
    }
	
	public void addPost(Post p) {
		postMap.put(p.getUuid(), p);
	}
	
	public Post removePost(UUID uuid) {
		return postMap.remove(uuid);
	}
	
	public Post getPost(UUID uuid) {
		return postMap.get(uuid);
	}
	
	public int size() {
		return postMap.size();
	}
	
	public LinkedHashMap<UUID, Post> getMap(){
		return postMap;
	}

	public void display() {
		for(Map.Entry<UUID, Post> entry: postMap.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
	}
	
	public LinkedHashMap<UUID, Post> searchByTopic(String topic) {
		LinkedHashMap<UUID, Post> temp = new LinkedHashMap<UUID, Post>();
		for(Map.Entry<UUID, Post> entry: postMap.entrySet()) {
			if(entry.getValue().getTopic().equals(topic)) {
				temp.put(entry.getValue().getUuid(), entry.getValue());
			}
		}
		return temp;
	}
	
	public LinkedHashMap<UUID, Post> searchByTitle(String title) {
		LinkedHashMap<UUID, Post> temp = new LinkedHashMap<UUID, Post>();
		for(Map.Entry<UUID, Post> entry: postMap.entrySet()) {
			if(entry.getValue().getTitle().equals(title)) {
				temp.put(entry.getValue().getUuid(), entry.getValue());
			}
		}
		return temp;
	}
	
	@Override
	public String toString() {
		String  s = "";	
		for(Map.Entry<UUID, Post> entry: postMap.entrySet()) {
			s += entry.getValue().toString();
		}
		return s;
	}
	
}
