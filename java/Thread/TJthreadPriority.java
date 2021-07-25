import java.lang.Thread;
public class TJthreadPriority  {
    public static void main(String[] args) {
        T1 t11 = new T1();
        T2 t12 = new T2();
        T3 t13 = new T3();
        t11.setPriority(Thread.MAX_PRIORITY);
        t12.setPriority(Thread.MIN_PRIORITY);
        t13.setPriority(t12.getPriority()+1);
        t12.start();
        t13.start();
        t11.start();

    }








}
