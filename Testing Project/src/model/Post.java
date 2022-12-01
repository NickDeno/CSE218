package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String topic;
	private String description;
	private LinkedList<FXImage> postImages;
	private User poster;
	private UUID uuid;
	
	private Date postDate;
	private LinkedHashMap<UUID, Post> postReplies;
	private Integer likes;

	public Post(String title, String topic, String description, LinkedList<FXImage> postImages, User poster, UUID uuid) {
		super();
		this.title = title;
		this.topic = topic;
		this.description = description;
		this.poster = poster;
		this.postImages = postImages;
		this.uuid = uuid;
		this.postDate = new Date();
		this.postReplies = new LinkedHashMap<UUID, Post>();
		this.likes = 0;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}
	
	public LinkedList<FXImage> getPostImages() {
		return postImages;
	}

	public void setPostImages(LinkedList<FXImage> postImages) {
		this.postImages = postImages;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Date getPostDate() {
		return postDate;
	}
	
	public LinkedHashMap<UUID, Post> getPostReplies() {
		return postReplies;
	}
	
	public void setPostReplies(LinkedHashMap<UUID, Post> postReplies) {
		this.postReplies = postReplies;
	}
	
	public Integer getLikes() {
		return likes;
	}
	
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	public void like(User user) {
		likes++;
//		var userCenterPostInstance = UserCenter.getInstance().getUser(user.getUsername()).getUserPosts().get(this.getUuid());
//		var postCenterPostInstance = PostCenter.getInstance().getPost(this.getUuid());
//		if(userCenterPostInstance == this) {
//			postCenterPostInstance.setLikes(this.likes);
//		} else {
//			userCenterPostInstance.setLikes(this.likes);
//		}
	}
	
	public void reply(Post post, User user) {
		postReplies.put(post.getUuid(), post);
//		var userCenterPostInstance = UserCenter.getInstance().getUser(user.getUsername()).getUserPosts().get(this.getUuid());
//		var postCenterPostInstance = PostCenter.getInstance().getPost(this.getUuid());
//		if(userCenterPostInstance == this) {
//			postCenterPostInstance.setPostReplies(this.postReplies);
//		} else {
//			userCenterPostInstance.setPostReplies(this.postReplies);
//		}
	}
	
	public void update(Post post, User user) {
//		var userCenterPostInstance = UserCenter.getInstance().getUser(user.getUsername()).getUserPosts().get(this.getUuid());
//		var postCenterPostInstance = PostCenter.getInstance().getPost(this.getUuid());
//		if(userCenterPostInstance == this) {
//			postCenterPostInstance.setTitle(post.getTitle());
//			postCenterPostInstance.setTopic(post.getTopic());
//			postCenterPostInstance.setDescription(post.getDescription());
//			postCenterPostInstance.setPostImages(post.getPostImages());
//		} else {
//			userCenterPostInstance.setTitle(post.getTitle());
//			userCenterPostInstance.setTopic(post.getTopic());
//			userCenterPostInstance.setDescription(post.getDescription());
//			userCenterPostInstance.setPostImages(post.getPostImages());
//		}
	}
	
	public void delete(Post post, User user) {
		UUID postUUID = this.getUuid();
		PostCenter.getInstance().removePost(postUUID);
		UserCenter.getInstance().getUser(user.getUsername()).getUserPosts().remove(postUUID);	
	}
	
	@Override
	public String toString() {
		String p = "";
		for(Map.Entry<UUID, Post> entry: postReplies.entrySet()) {
			p += entry.getValue().toString() + ", ";
		}
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		return "Post [Created by " + poster.getUsername() + ", title=" + title + ", topic= " + topic + ", description=" + description 
				+ ", postDate=" + df.format(postDate) + ", likes=" + likes +", postReplies=" + p + "]";
	}
	
}
