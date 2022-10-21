package p5_key;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utilities {
	
	public static String[] fileToArray(String filename) {
		String[] arr;
		try {
			Scanner sc = new Scanner(new File(filename));
			String line = sc.nextLine();
			arr = line.split("\\s+");
			sc.close();
			return arr;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
