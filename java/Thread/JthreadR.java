public class JthreadR implements Runnable {
    int id ;
    public void run() {
        System.out.println("In thread class with Runnable interface!!!!");
        System.out.println(this.toString());
        System.out.println(this.id);
    }
    public JthreadR(int id) {
        this.id = id;
    }    
}
