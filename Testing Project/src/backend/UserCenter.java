package backend;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserCenter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LinkedHashMap<String, User> userCenter;
	
	public UserCenter() {
		userCenter = new LinkedHashMap<String, User>();
	}
	
	public void addUser(User user) {
		userCenter.put(user.getUsername(), user);
	}
	
	public User removeUser(String username) {
		return userCenter.remove(username);
	}
	
	public User getUser(String username) {
		return userCenter.get(username);
	}
	
	public boolean containsUser(String username) {
		return userCenter.containsKey(username);
	}
	
	public int size() {
		return userCenter.size();
	}
	
	public Collection<User> getValues(){
		return userCenter.values();
	}
	
	public void display() {
		for(Map.Entry<String, User> entry: userCenter.entrySet()) {
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
		for(Map.Entry<String, User> entry: userCenter.entrySet()) {
			s += entry.getValue().toString();
		}
		return s;
	}

}
