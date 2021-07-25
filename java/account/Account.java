public class Account {
    int number;//instance variables
    float balance;
    int trans=0;
    char[] transtype;
    float[] transamt;
    static int numOfAccount=0;

    public static void main(String args[]){
        Account account = new Account();
        account.initialize(123);
        account.deposit(1000);
        account.withdraw(50);
        account.printTrans();
        Account.numOfAccount +=1;

        Account account2 = new Account();
        account2.initialize(325);
        account2.deposit(2000);
        account2.withdraw(50);
        account2.printTrans();
        Account.numOfAccount +=1;
        Account.printNumberOfAccount();
    }
    static void printNumberOfAccount(){
        System.out.println("number of account="+Account.numOfAccount);
    }
     
    void initialize(int num){
        transtype = new char[100];
        transamt = new float[100];
        trans = 0;
        number =num;
    }
    
    void withdraw(float amount){
        if(balance>amount){
            System.out.println("withdrawing amount" + amount);
            transamt[trans] = amount;
            transtype[trans] = 'W';
            trans++;
            balance = balance-amount;   
        }else{
            System.out.println("balance low ! can not withdraw amount" + amount  + "from account number" + number);
        }
    }

    void deposit(float amount){
        System.out.println("depositing amount" + amount + "from account number" + number);
        transamt[trans] = amount;
        transtype[trans] = 'D';
        trans++;
        balance = balance + amount;
    }

  
     void printTrans(){
         System.out.println("==================");
         System.out.println("Account Transaction on number:"+number);
         System.out.println("====================");
         System.out.println("\tSl No \t\t Description \t Deposit \t Widthdraw \t Balance");;
         float bal=0.0f;
         for (int i = 0; i < trans; i++) {
             if (transtype[i]=='W') {
                 bal=bal-transamt[i];
                 System.out.println("\t"+i+"\t\t widthdrawl \t \t \t "+transamt[i]+"\t\t"+bal);

             }else{
                 bal=bal+transamt[i];
                 System.out.println("\t"+i+"\t\t Deposit\t"+transamt[i]+"\t\t\t"+bal);
             }
         }
         System.out.println("=================");
     }

}
