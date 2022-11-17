package backend;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Key field
	private String username;
	
	private String password;
	private String email;
	private PostCenter userPosts;
	private FXImage profilePic;
	
	public User(String username, String password, String email, FXImage profilePic) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profilePic = profilePic;
		this.userPosts = new PostCenter();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FXImage getProfilePic() {
		return profilePic;
	}

	public void setProfilePicPath(FXImage profilePic) {
		this.profilePic = profilePic;
	}

	public PostCenter getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(PostCenter userPosts) {
		this.userPosts = userPosts;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", userPosts=" + userPosts + "]";
	}
}
