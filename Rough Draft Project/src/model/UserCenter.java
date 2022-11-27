package model;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import util.Utilities;

//Singleton Class
public class UserCenter implements Serializable {
	private static final long serialVersionUID = 1L;	
	private static UserCenter instance;
	private LinkedHashMap<String, User> userMap;
	
	private User currentUser;
	
	private UserCenter() {
		userMap = new LinkedHashMap<String, User>();
	}
	
	public static UserCenter getInstance() {
		if(instance == null && new File("backupData/Users.dat").exists() == false) { //Condition will be true on first ever launch of program
			instance = new UserCenter();
        } else if(instance == null) { //Condition will be true on every other launch of program besides first one
        	instance = Utilities.restoreUserCenter();
        }
		return instance;  
    }
	
	public void addUser(User user) {
		userMap.put(user.getUsername(), user);
	}
	
	public User removeUser(String username) {
		return userMap.remove(username);
	}
	
	public User getUser(String username) {
		return userMap.get(username);
	}
	
	public boolean containsUser(String username) {
		return userMap.containsKey(username);
	}
	
	public int size() {
		return userMap.size();
	}
	
	public Collection<User> getAllUsers(){
		return userMap.values();
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void display() {
		for(Map.Entry<String, User> entry: userMap.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
	}
	
	public static boolean isValidPassword(String password) {
		if(password.length() < 8) return false;
		boolean hasDigit = false;
		boolean hasUpperCase = false;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) hasUpperCase = true;
			if(Character.isDigit(password.charAt(i))) hasDigit = true;
		}
		return hasDigit && hasUpperCase;
	}

	public static boolean isValidEmail(String email) {
		if(email.isBlank()) return false;
		boolean hasAt = false;
		boolean hasDot = false;
		for(int i = 0; i < email.length(); i++) {
			if(email.charAt(i) == '@') hasAt = true;
			if(email.charAt(i) == '.') hasDot = true;	
		}
		return hasAt && hasDot;	
	}
	
	@Override
	public String toString() {
		String  s = "";	
		for(Map.Entry<String, User> entry: userMap.entrySet()) {
			s += entry.getValue().toString();
		}
		return s;
	}

}
