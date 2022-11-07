package backend;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String title;
	private String description;
	private Date postDate;
	
	public Post(String username, String title, String description) {
		super();
		this.username = username;
		this.title = title;
		this.description = description;
		this.postDate = new Date();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getPostDate() {
		return postDate;
	}

	@Override
	public String toString() {
		return "Post [username=" + username + ", title=" + title + ", description=" + description + ", postDate="
				+ postDate + "]";
	}
	
}
