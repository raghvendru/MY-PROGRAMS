import java.util.Scanner;
public class Operation {

    int num;
    int a=5;
    int b=5;
    int c;
    double d;
    int e=-5;
    int m=102;
    int sum=0;
    
    int count=0;
  
    public static void main(String[] args) {
        Operation operation = new Operation();
        operation.add();
        operation.mul();
        operation.div();
        operation.mod();
        operation.pow();
        operation.negative();
        operation.isPrime();
        operation.even();
        operation.digit();
        // operation.even();
        // operation.odd();
        
    }
   

    void add(){
        // Scanner operation = new Scanner(System.in);
        // System.out.println("a and b to add");
        // a=operation.nextInt();
        // b=operation.nextInt();
        
        c=a+b;
        System.out.println("addition is "+ c);
        

    }
    void mul(){
        // Scanner mul = new Scanner(System.in);
        // System.out.println("a and b to multiply ");
        // a=mul.nextInt();
        // b=mul.nextInt();
        
        c=a*b;
        System.out.println("mul is "+ c);

    }
    void div(){
        // Scanner div = new Scanner(System.in);
        // System.out.println("a and b to divid ");
        // a=div.nextInt();
        // b=div.nextInt();
        
        c=a/b;
        System.out.println("div is "+ c);

    }
    void mod(){
        // Scanner div = new Scanner(System.in);
        // System.out.println("a and b to divid ");
        // a=div.nextInt();
        // b=div.nextInt();
        
        c=a%b;
        System.out.println("mod is "+ c);

    }
    void pow(){
        // Scanner div = new Scanner(System.in);
        // System.out.println("a and b to divid ");
        // a=div.nextInt();
        // b=div.nextInt();
        

        d=Math.pow(a, b);
        System.out.println(d);
    
    }
    void negative(){
        // Scanner div = new Scanner(System.in);
        // System.out.println("a and b to divid ");
        // a=div.nextInt();
        // b=div.nextInt();
        

        if (e<b) {
            System.out.println("negative is "+e);
        } else {
            System.out.println("positive"+b);
        }
    
    }
    void isPrime(){
        // Scanner div = new Scanner(System.in);
        // System.out.println("a and b to divid ");
        // a=div.nextInt();
        // b=div.nextInt();
        for (int i=1;i<=a;i++) {
           
            if (a%i==0) {
              count++;
            }
        }
            if (count==2) {
                System.out.println("its a prime number");
                
            }else{
                System.out.println("its not prime number");
            }
            
        }

        void even(){
            if(a%2==0){
                System.out.println("its even number");

            }
            else{
                System.out.println("its odd number");
            }
        }
        void digit(){
            while(m!=0){
                m=m/10;
                count++;}
                System.out.println("its digits are"+count);
        }
        void prfect(){
            for(int i=1;i<=a/2;i++){
                if(a%i==0){
                    sum=sum+i;
                }
            }
                if(sum==a){
                    System.out.println("its perfect");

                }
                else{
                    System.out.println("its not perfect");  
                }
            
        }
        

    
}
