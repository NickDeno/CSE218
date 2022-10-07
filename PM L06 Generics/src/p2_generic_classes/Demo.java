package p2_generic_classes;

public class Demo {
	
	public static void main(String[] args) {
		Point<Integer, Double> p1 = new Point<>(2, 4.0);
		Point<String, Double> p2 = new Point<>("Two", 2.2);
		Point<Object, Object> p3 = new Point<>(2, 4l );
		
//		display(p1);
//		display(p2);
		display(p3);
	}
	
//	public static void display(Point <? extends Number, ? extends Number> p) { Wildcard UpperBound Constraint
//		System.out.println(p);
//	}
	
	public static void display(Point <? super Integer, ? super Integer> p) { //LowerBound Constraint
	System.out.println(p);
	}	
}
