public class JoinTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new JoinClass(), "t1");
        Thread t2 = new Thread(new JoinClass(), "t2");
        Thread t3 = new Thread(new JoinClass(), "t3");
            
        // Start first thread immediately
        t1.start();    
        try {
            System.out.println("t1.join is called ... the execution is blocked till t1 dies");
            t1.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        t2.start(); // t2 will not start till t1 dies.... j1.join() waits for t1 to die
        try {
            System.out.println("t2.join is called ... the execution is blocked till t1 dies");
            t2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        t3.start(); // t3 will not start till t2 dies
        try {
            System.out.println("t3.join is called ... the execution is blocked till t1 dies");
            t3.join(); // 
        } catch (InterruptedException ie) {
                ie.printStackTrace();
            }  
        System.out.println("All three threads have finished execution");
    }
 }b  