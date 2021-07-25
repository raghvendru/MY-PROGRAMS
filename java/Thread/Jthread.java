import java.lang.Thread;
public class Jthread extends Thread {
    public void run() {
        // while (true) {
            System.out.println("In thread class!!!!");
            System.out.println(this.getId());
        // }
    }
}
