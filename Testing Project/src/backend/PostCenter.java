package backend;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class PostCenter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LinkedHashMap<UUID, Post> postCenter;
//	private static PostCenter instance;
	
	
//	private PostCenter() {
//		postCenter = new LinkedHashMap<UUID, Post>();
//	}
//	
//	public static PostCenter getInstance() {
//        if(instance == null) {
//            instance = new PostCenter();
//        }
//        return instance;
//    }
	
	public PostCenter() {
		postCenter = new LinkedHashMap<UUID, Post>();
	}
	
	public void add(Post p) {
		postCenter.put(p.getUuid(), p);
	}
	
	public Post remove(UUID uuid) {
		return postCenter.remove(uuid);
	}
	
	public Post getPost(UUID uuid) {
		return postCenter.get(uuid);
	}
	
	public int size() {
		return postCenter.size();
	}
	
	public Collection<Post> getValues(){
		return postCenter.values();
	}

	public void display() {
		for(Map.Entry<UUID, Post> entry: postCenter.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
	}
	
	public PostCenter searchByTopic(String topic) {
		PostCenter temp = new PostCenter();
		for(Map.Entry<UUID, Post> entry: postCenter.entrySet()) {
			if(entry.getValue().getTopic().equals(topic)) {
				temp.add(entry.getValue());
			}
		}
		return temp;
	}
	
	public PostCenter searchByTitle(String title) {
		PostCenter temp = new PostCenter();
		for(Map.Entry<UUID, Post> entry: postCenter.entrySet()) {
			if(entry.getValue().getTitle().equals(title)) {
				temp.add(entry.getValue());
			}
		}
		return temp;
	}
	
	@Override
	public String toString() {
		String  s = "";	
		for(Map.Entry<UUID, Post> entry: postCenter.entrySet()) {
			s += entry.getValue().toString();
		}
		return s;
	}
	
}
