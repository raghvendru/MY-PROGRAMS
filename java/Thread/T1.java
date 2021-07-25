import java.lang.Thread;
public class T1 extends Thread {
    public void run() {
        System.out.println("In thread class T1");
        for (int i = 1 ; i < 5 ; i++) {
            System.out.println("t1 i is " + i);
        }
        System.out.println("Exit T1");
    }
}
