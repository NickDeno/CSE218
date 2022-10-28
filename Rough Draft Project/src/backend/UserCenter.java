package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class UserCenter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<User> arr;
	
	public UserCenter(int prefSize) {
		arr = new ArrayList<>(prefSize);
	}
	
	public void insert(User user) {
		if(arr.isEmpty()) {
			arr.add(user);
			return;
		}
		for(int i = 0; i < arr.size(); i++) {
			if(user.getUsername().compareTo(arr.get(i).getUsername()) < 0) {
				arr.add(i, user);
				return;
			}
		}
		arr.add(user);
	}
	
	public void display() {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		System.out.println();
	}
	
	public User userSearch(String username) {
		int left = 0;
		int right = arr.size()-1;
		
		while(left <= right) {
			int mid = (left + right)/2;
			
			if(arr.get(mid).getUsername().compareTo(username) > 0) {
				right = mid - 1;
			} else if(arr.get(mid).getUsername().compareTo(username) < 0) {
				left = mid + 1 ;
			} else if(arr.get(mid).getUsername().equals(username)) {
				return arr.get(mid);
			}
		}
		return null;
	}
	
	public User userSearch(String username, String password) {
		int left = 0;
		int right = arr.size()-1;
		
		while(left <= right) {
			int mid = (left + right)/2;
			
			if(arr.get(mid).getUsername().compareTo(username) > 0) {
				right = mid - 1;
			} else if(arr.get(mid).getUsername().compareTo(username) < 0) {
				left = mid + 1 ;
			} else if(arr.get(mid).getUsername().equals(username) && arr.get(mid).getPassword().equals(password)) {
				return arr.get(mid);
			}
		}
		return null;
	}
	
	public boolean usernameIsUnique(String username) {
		if(username.isBlank()) return false;
		int left = 0;
		int right = arr.size()-1;
		
		while(left <= right) {
			int mid = (left + right)/2;
			
			if(arr.get(mid).getUsername().compareTo(username) > 0) {
				right = mid - 1;
			} else if(arr.get(mid).getUsername().compareTo(username) < 0) {
				left = mid + 1 ;
			} else if(arr.get(mid).getUsername().equals(username)) {
				return false;
			}
		}
		return true;
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
}
