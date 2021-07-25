public class PointTest {
  public static void main(String[] args) {
     /* Test Point2D */
     // Test constructors and toString()
     Point2D p2a = new Point2D(1, 2);
     System.out.println(p2a); // toString()
     Point2D p2b = new Point2D(); // default constructor
     System.out.println(p2b);
     // Test Setters and Getters
     p2a.setX(3); // Test setters
     p2a.setY(4);
     System.out.println(p2a); // toString()
     System.out.println("x is: " + p2a.getX());
     System.out.println("x is: " + p2a.getY());

     /* Test Point3D */
     // Test constructors and toString()
     Point3D p3a = new Point3D(11, 12, 13);
     System.out.println(p3a); // toString()
     Point2D p3b = new Point3D(); // default constructor
     System.out.println(p3b);
     // Test Setters and Getters
     p3a.setX(21); // in superclass
     p3a.setY(22); // in superclass
     p3a.setZ(23); // in this class
     System.out.println(p3a); // toString()
     System.out.println("x is: " + p3a.getX()); // in superclass
     System.out.println("y is: " + p3a.getY()); // in superclass
     System.out.println("z is: " + p3a.getZ()); // in this class
  }
}
