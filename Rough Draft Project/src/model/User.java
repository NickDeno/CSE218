package model;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import util.Utilities;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Key field
	private String username;
	
	private String password;
	private String email;
	private String nickName;
	private String bio;
	private FXImage profilePic;
	private FXImage bannerPic;
	private LinkedHashMap<UUID, Post> userPosts;	
	private LinkedList<User> followers;
	private TreeMap<String, User> following;
	private TreeMap<String, User> blockedUsers;
	
	public User(String username, String password, String email, FXImage profilePic) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickName = null;
		this.bio = null;
		this.profilePic = profilePic;
		this.bannerPic = new FXImage(Utilities.fileToByteArr(new File("src/assets/DefaultBanner.png")));
		this.userPosts = new LinkedHashMap<UUID, Post>();
		this.followers = new LinkedList<User>();
		this.following =  new TreeMap<String, User>();
		this.blockedUsers = new TreeMap<String, User>();
		
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public FXImage getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(FXImage profilePic) {
		this.profilePic = profilePic;
	}
	
	public FXImage getBannerPic() {
		return bannerPic;
	}

	public void setBannerPic(FXImage bannerPic) {
		this.bannerPic = bannerPic;
	}

	public LinkedHashMap<UUID, Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(LinkedHashMap<UUID, Post> userPosts) {
		this.userPosts = userPosts;
	}
	
	public LinkedList<User> getFollowers(){
		return followers;
	}
	
	public TreeMap<String, User> getFollowing(){
		return following;
	}
	
	public void setBlockedUsers(TreeMap<String, User> blockedUsers) {
		this.blockedUsers = blockedUsers;
	}
		
	public TreeMap<String, User> getBlockedUsers(){
		return blockedUsers;
	}
	
	public void follow(User user) {
		following.put(user.getUsername(), user);
	}

	@Override
	public String toString() {
		String posts = "";
		String followers = "";
		String following = "";
		String blockedUsers = "";
		for(Map.Entry<UUID, Post> entry: userPosts.entrySet()) {
			posts += entry.getValue().toString() + ", ";
		}
//		for(Map.Entry<String, User> entry: this.followers.entrySet()) {
//			followers += entry.getValue().toString() + ", ";
//		}
//		for(Map.Entry<String, User> entry: this.following.entrySet()) {
//			following += entry.getValue().toString() + ", ";
//		}
//		for(Map.Entry<String, User> entry: this.blockedUsers.entrySet()) {
//			blockedUsers += entry.getValue().toString();
//		}
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", userPosts=" + posts + ", followers=" + followers 
				+ ", following=" + following + ", blockedUsers=" + blockedUsers + "]";
	}
}
