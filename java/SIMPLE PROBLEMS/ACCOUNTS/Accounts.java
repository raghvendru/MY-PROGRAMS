public class Accounts{
    String   id;
    String name;
    int balance=0;
    public Accounts(String id, String name) { //condtructor
        this.id = id;
        this.name = name;
    }
    public Accounts(String id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public String getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBalance() {
        return balance;
    }
    public int credit(int ammount) {
        return ammount+balance;  
        }
    public int debit(int ammount) {
            if(ammount<=balance)  {
                return balance-ammount;
            
            }else{
                return balance;
                }
            }
                public int TransferTo(Account another,int ammount) {
                    if(ammount<=balance)  {
                        return balance-ammount;
                    
                    }else{
                        return balance;
                    }
                }


            public String toString() {
                return "Accounts[(" + id +  "," +name +","+balance+")]"; 
            }
        
    
}


    