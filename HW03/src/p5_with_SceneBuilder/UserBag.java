package p5_with_SceneBuilder;

import java.util.ArrayList;

public class UserBag {
	private ArrayList<User> arr;
	
	public UserBag(int prefSize) { 
		arr = new ArrayList<>(prefSize);
	}
	
	public void insert(User user) { //Sorts users by username when inserting 
			if(arr.isEmpty()) {
				arr.add(user);
			} else {
				for(int i = 0; i < arr.size(); i++) { 
					if(user.getUsername().compareTo(arr.get(i).getUsername()) < 0) {
						arr.add(i, user);
						break;
					}
				}
			}
	}
	
	public User binarySearchByUsername(User user) {
		int left = 0;
		int right = arr.size()-1;
		
		while(left <= right) {
			int mid = (left + right)/2;

			if(arr.get(mid).getUsername().compareTo(user.getUsername()) > 0) {
				right = mid - 1;
			} 
			else if(arr.get(mid).getUsername().compareTo(user.getUsername()) < 0) {
				left = mid + 1;
			} 
			else {
				return arr.get(mid);
			}
		}
		return null;	
	}
	
	public User peekUser(int idx) {
		return arr.get(idx);
	}
	
	public int getSize() {
		return arr.size();
	}	
}
