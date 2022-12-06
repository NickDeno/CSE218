package model;

import java.io.File;
import java.io.Serializable;

import util.Utilities;

//Singleton Class
public class AppState implements Serializable {
	private static final long serialVersionUID = 1L;
	private UserCenter userCenter;
	private PostCenter postCenter;
	
	private static AppState instance;
	
	private AppState(UserCenter userCenter, PostCenter postCenter) {
		this.userCenter = userCenter;
		this.postCenter = postCenter;
	}
	
	public static AppState getInstance() {
		if(instance == null && new File("backupData/AppState.dat").exists() == false) { //Condition will be true on first ever launch of program
			instance = new AppState(new UserCenter(), new PostCenter());
		} else if(instance == null) { //Condition will be true on every other launch of program besides first one
			instance = Utilities.restoreAppState();
		} //Condition will be true after launch of program
		return instance; 	
	}
	
	public UserCenter getUserCenter() {
		return userCenter;
	}

	public void setUserCenter(UserCenter userCenter) {
		this.userCenter = userCenter;
	}

	public PostCenter getPostCenter() {
		return postCenter;
	}

	public void setAllPosts(PostCenter postCenter) {
		this.postCenter = postCenter;
	}
	

}
