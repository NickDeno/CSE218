package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utilities {	
	public static void backupUserCenter() {
		try {
			FileOutputStream fos = new FileOutputStream("backupData/Users.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(UserCenter.getInstance());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserCenter restoreUserCenter() {
		try {
			FileInputStream fis = new FileInputStream("backupData/Users.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			UserCenter userCenter = (UserCenter) ois.readObject();
			ois.close();
			return userCenter;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void backupPostCenter() {
		try {
			FileOutputStream fos = new FileOutputStream("backupData/Posts.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(PostCenter.getInstance());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PostCenter restorePostCenter() {
		try {
			FileInputStream fis = new FileInputStream("backupData/Posts.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			PostCenter postCenter = (PostCenter) ois.readObject();
			ois.close();
			return postCenter;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
