import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Enumeration;

public class Array{
    public static void main(String[] args) {
        ArrayList<String> carList = new ArrayList<String>();
        carList.add("Fiat");
        carList.add("maruthi");
        carList.add("mahindra");
        System.out.println("initially adding"+carList);

        carList.contains("fiat");
        carList.indexOf("mahindra");
        carList.remove("maruthi");
        System.out.println(carList);
        
       
        System.out.println("first list"+carList.get(0));
        
        System.out.println("set first item"+carList.set(1,"Suzuki"));
        System.out.println(carList);

        carList.add("ambassador");
        System.out.println(carList);

        for(String Car:carList) { //Iterator
        System.out.println(Car);
        }

        for(int i=0;i<carList.size();i++){  //iterator
        System.out.println(carList.get(i));
        }

        ArrayList<String> list=new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add(1,"insert");

        list.forEach( (element) -> {
            System.out.println( element );
        });
        
        Collections.sort(carList);
        System.out.println(carList);
       

        Collections.shuffle(carList);
        System.out.println(carList);

        Collections.reverse(carList);
        System.out.println(carList);
        


        

        
    }
}