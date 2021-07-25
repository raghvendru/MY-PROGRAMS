public class TestShape{
    public static void main(String args[]){
        Circle1 p1;
        Rectangle1 p2;
        Square1 p3;
        p1=new Circle1(3);
        System.out.println(p1);
        System.out.println("Area of Circle="+p1.getArea());
        System.out.println("Perimeter of circle="+p1.getPerimeter());
        p2=new Rectangle1(3,3);
        System.out.println(p2);
        System.out.println("Area of Rectangle="+p2.getArea());
        System.out.println("Perimeter of Rectangle="+p2.getPerimeter());
        p3=new Square1(2);
        System.out.println(p3);
        System.out.println("Area of Square="+p3.getArea());
        System.out.println("Perimeter of Square="+p3.getPerimeter());        
 
    }
}