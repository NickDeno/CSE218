package backend;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String topic;
	private String description;
	private User postUser;
	private LinkedList<FXImage> postImages;
	private Date postDate;
	private PostList postReplies;
	private Integer likeCount;

	public Post(String title, String topic, String description, User postUser, LinkedList<FXImage> postImages) {
		super();
		this.title = title;
		this.topic = topic;
		this.description = description;
		this.postUser = postUser;
		this.postImages = postImages;
		this.postDate = new Date();
		this.postReplies = new PostList();
		this.likeCount = 0;
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
	
	public User getPostUser() {
		return postUser;
	}

	public void setPostUser(User postUser) {
		this.postUser = postUser;
	}
	
	public LinkedList<FXImage> getPostImages() {
		return postImages;
	}

	public void setPostImages(LinkedList<FXImage> postImages) {
		this.postImages = postImages;
	}

	public Date getPostDate() {
		return postDate;
	}
	
	public PostList getPostReplies() {
		return postReplies;
	}
	
	public Integer getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		return "Post [Created by " + postUser.getUsername() + ", title=" + title + ", topic= " + topic + ", description=" + description 
				+ ", postDate=" + df.format(postDate) + ", likeCount=" + likeCount +", postReplies=" + postReplies + "]";
	}
	
}
