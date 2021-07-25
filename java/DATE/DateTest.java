public class DateTest{
    public static void main(String[] args) {
       // Test constructor and toString()
       Date d1 = new Date(1, 2, 2014);
       System.out.println(d1.toString()); 
       Date d2 = d1.addDay(5);
       System.out.println(d2.toString());  // toString()
       Date d3 = d2.addDay(40);
       System.out.println(d3.addDay(40)); 
       System.out.println(d3.minDay(40).toString());
       Date ld  = new Date(3, 6, 2020);
       Date nd = new Date(3, 2, 2020);
       System.out.println(ld.toString());
       //System.out.println(d1.getDayName());
       //Date nd = Date.add(10);
       System.out.println(nd.toString());
      System.out.println(ld.diff(nd));
    }
      
   }