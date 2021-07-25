public class Dog extends Mammal{

   
    public Dog(String name) {
        super(name);
    }
    
    public void greets(){
        System.out.println("woof");
    }

    public void greets(Dog anothor){
        System.out.println("woooof");
    }

    @Override
    public String toString() {
        return "Dog [Mammal[Animal [name=" + name + "]]]";
    }

    

}