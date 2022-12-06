package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.AppState;

public class Utilities {
	//Takes a file (for this project mainly image files) and converts it into a byte array. This is done since by default, JavaFX images are not
	//serializable. So instead, we can convert the image into a byte array and save the byte array into a specified file.
	public static byte[] fileToByteArr(File file) {
		try {
			byte[] byteArr = Files.readAllBytes(file.toPath());
			return byteArr;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image byteArrToImage(byte[] imageBuffer) {
		return new Image(new ByteArrayInputStream(imageBuffer));
	}
	
	public static ImagePattern byteArrToImagePattern(byte[] imageBuffer) {
		return new ImagePattern(new Image(new ByteArrayInputStream(imageBuffer)));
	}
	
	public static void backupAppState() {
		try {
			FileOutputStream fos = new FileOutputStream("backupData/AppState.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(AppState.getInstance());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static AppState restoreAppState() {
		try {
			FileInputStream fis = new FileInputStream("backupData/AppState.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			AppState appState = (AppState) ois.readObject();
			ois.close();
			return appState;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
		
}
