package p2;
import java.util.TreeMap;

public class UserStore {
	private TreeMap<String, User> theMap;
	
	public UserStore() {
		theMap = new TreeMap<>();
		
	}
	
	public void insert(User user) {
		theMap.put("userName", user);
	}
}
