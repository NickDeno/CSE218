package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utilities {
	public static void backupUsers(UserCenter users) {
		try {
			FileOutputStream fos = new FileOutputStream("backupData/Users.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserCenter restoreUsers() {
		try {
			FileInputStream fis = new FileInputStream("backupData/Users.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			UserCenter users = (UserCenter) ois.readObject();
			ois.close();
			return users;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void backupPosts(PostList postList) {
		try {
			FileOutputStream fos = new FileOutputStream("backupData/AllPosts.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(postList);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PostList restorePosts() {
		try {
			FileInputStream fis = new FileInputStream("backupData/AllPosts.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			PostList postList = (PostList) ois.readObject();
			ois.close();
			return postList;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
