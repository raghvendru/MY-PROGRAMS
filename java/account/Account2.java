public class Account2{
    int     number;
    float   balance;
    static int count = 0 ;

    static class SavingsAccount extends Account2 {
        int rate(){
            return 5;
        }
    }

    static class FDAccount extends Account2 {
        void withdraw(){
            // super.withdraw(10000);
            System.out.println("Calling FD Account withdraw" );
        }
    }

    public static void main(String args[]){
       Account2 account = new Account2();
       account.deposit(100);
       account.withdraw(50);
       Account2.count++;
       Account2 account1 = new Account2();
       account1.deposit(100);
       account1.withdraw(50);
       Account2.count++;
       Account2.printCount();
       SavingsAccount sa1 = new SavingsAccount();
       Account2.count++;
       Account2.printCount();
       sa1.rate();
       sa1.deposit(100);
       sa1.withdraw(20);
       FDAccount fd = new FDAccount();
       Account2.count++;
       Account2.printCount();
       fd.deposit(100);
       fd.withdraw();
    }

    static void printCount() {
        System.out.println("Total number of accounts " + count);
    }
    void withdraw(float amount) {
        if (balance > amount) {
            System.out.println("withdrawing amount " + amount);
            balance = balance - amount;
        } else {
            System.out.println("Balance low ! Can not withdraw amount " + amount );
        }
    }
    void deposit(float amount) {
        System.out.println("depositing amount " + amount);
        balance = balance + amount;
    }
 }

 