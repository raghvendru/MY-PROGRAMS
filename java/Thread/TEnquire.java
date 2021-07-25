import java.lang.Thread;
public class TEnquire implements Runnable {
    Accounts1 accounts ;
    public TEnquire(Accounts1 acc) {
        accounts = acc ;
    }
    public void run() {
        System.out.println("In TEnquire Class!");
        int bal = accounts.doOperation('E',0) ;
        System.out.println(" Enquiry: balance is " + bal);
    }
}
