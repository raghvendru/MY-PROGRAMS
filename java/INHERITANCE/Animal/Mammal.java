public class Mammal extends Animal{

    public Mammal(String name) {
        super(name);
    }
    public Mammal() {
        
    }
    @Override
    public String toString() {
        return "Mammal [Animal [name=" + name + "]]";
    }


}