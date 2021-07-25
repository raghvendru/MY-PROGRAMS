public class Add {
   int add;//instance variables
   int a;
   int b;

   public static void main(String args[]){
      Add ad = new Add();
      ad.add();
   }

   void add(){
      a=10;
      b=20;
      add=a+b;
      System.out.println("is "+add);
   }
}