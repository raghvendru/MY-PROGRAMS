public class TestAnimal{
    public static void main(String args[]){
        Animal p1;
        Mammal p2;
        Cat p3;
        Dog p4,p5;
        p1=new Animal("cat");
        System.out.println(p1);
        p2=new Mammal("Dog");
        System.out.println(p2);
        p3=new Cat("cat");
        System.out.println(p3);
        p3.greets();
        p4=new Dog("dog");
        System.out.println(p4);
        p4.greets();   
        p5 = new Dog("dabba");
        System.out.println(p5);
        p4.greets(p5);  
        

    }



}
