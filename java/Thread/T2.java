import java.lang.Thread;
public class T2 extends Thread {
    public void run() {
        System.out.println("In thread class T2");
        for (int i = 1 ; i < 5 ; i++) {
            System.out.println("t2 i is " + i);
        }
        System.out.println("Exit T2");
    }
}
