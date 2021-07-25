import java.util.Scanner;
public class Circle {

    int r=5;
    double c;
    
  
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.area();
        circle.perimeter();
        circle.dia();
        circle.rad();

        
    }
   

    void area(){
        c=22/7*r*r;
        System.out.println("area "+ c);
    }

    void perimeter(){
        c=2*22/7*r;
        System.out.println("perimeter "+ c);
    }

    void dia(){
        c=2*r;
        System.out.println("dia "+ c);
    }

    void rad(){
        System.out.println("radius "+ r);
    }
}