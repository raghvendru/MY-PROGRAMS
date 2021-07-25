import java.util.ArrayList;
import java.util.Collections;

public class Array5 {

 public static void main(String a[]){
    ArrayList<String> al = new ArrayList<String>();
    al.add("red");
    al.add("blue");
    al.add("yellow");
    

    System.out.println("before Swap");
    for(String temp: al){
        System.out.println(temp);
    }
    Collections.swap(al, 1, 2);

    System.out.println("after swap");
    for(String temp: al){
       System.out.println(temp);
    }
  }
}