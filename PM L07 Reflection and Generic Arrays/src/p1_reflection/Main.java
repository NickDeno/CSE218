package p1_reflection;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
//		for(int i = 0; i < args.length; i ++) {
//			System.out.println("Hello " + args[i]);
//		}
		
		try {
			Class c = Class.forName(args[0]);
			Method[] m = c.getDeclaredMethods();
			for(int i = 0; i < m.length; i++) {
				System.out.println(m[i].toString());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
