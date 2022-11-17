package backend;

import java.io.Serializable;

public class AppState implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UserCenter allUsers;
	private PostList allPosts;
	
	public AppState(UserCenter allUsers, PostList allPosts) {
		this.allUsers = allUsers;
		this.allPosts = allPosts;
	}

	public UserCenter getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(UserCenter allUsers) {
		this.allUsers = allUsers;
	}

	public PostList getAllPosts() {
		return allPosts;
	}

	public void setAllPosts(PostList allPosts) {
		this.allPosts = allPosts;
	}
	
	
}
