package backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Key field
	private String username;
	private String password;
	private String email;
	private FXImage profilePic;
	private LinkedHashMap<UUID, Post> userPosts;
	private HashMap<String, User> followedUsers;
	private HashMap<String, User> blockedUsers;
	
	public User(String username, String password, String email, FXImage profilePic) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profilePic = profilePic;
		this.userPosts = new LinkedHashMap<UUID, Post>();
		this.followedUsers = new HashMap<String, User>();
		this.blockedUsers = new HashMap<String, User>();
		
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

	public void setProfilePic(FXImage profilePic) {
		this.profilePic = profilePic;
	}

	public LinkedHashMap<UUID, Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(LinkedHashMap<UUID, Post> userPosts) {
		this.userPosts = userPosts;
	}
	
	public HashMap<String, User> getFollowedUsers(){
		return followedUsers;
	}
	
	public void follow(User user) {
		followedUsers.put(user.getUsername(), user);
	}
	
	public HashMap<String, User> getBlockedUsers(){
		return blockedUsers;
	}
	

	@Override
	public String toString() {
		String posts = "";
		String blockedUsers = "";
		for(Map.Entry<UUID, Post> entry: userPosts.entrySet()) {
			posts += entry.getValue().toString() + ", ";
		}
		for(Map.Entry<String, User> entry: this.blockedUsers.entrySet()) {
			blockedUsers += entry.getValue().toString();
		}
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", userPosts=" + posts + ", blockedUsers=" + blockedUsers + "]";
	}
}
