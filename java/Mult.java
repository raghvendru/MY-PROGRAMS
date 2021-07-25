public class Mult {
   int mult;//instance variables
   int a;
   int b;

   public static void main(String args[]){
      Mult mul = new Mult();
      mul.mult();
   }

   void mult(){
      a=10;
      b=20;
      mult=a*b;
      System.out.println("is "+mult);
   }
}