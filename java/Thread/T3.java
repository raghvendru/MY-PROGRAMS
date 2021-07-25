import java.lang.Thread;
public class T3 extends Thread {
    public void run() {
        System.out.println("In thread class T3");
        for (int i = 1 ; i < 5 ; i++) {
            System.out.println("t3 i is " + i);
        }
        System.out.println("Exit T3");
    }
}
