package backend;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.UUID;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Key field
	private String username;
	
	private String password;
	private String email;
	private LinkedHashMap<UUID, Post> userPosts;
	private FXImage profilePic;
	
	public User(String username, String password, String email, FXImage profilePic) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profilePic = profilePic;
		this.userPosts = new LinkedHashMap<UUID, Post>();
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

	public LinkedHashMap<UUID, Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(LinkedHashMap<UUID, Post> userPosts) {
		this.userPosts = userPosts;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", userPosts=" + userPosts + "]";
	}
}
