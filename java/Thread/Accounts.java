public class Accounts  {
    int balance ;    
    public synchronized int doOperation(char op,  int amount) {
        switch (op) {
            case 'D' :
                balance += amount;
                break;
            case 'W':
                balance -= amount;        
                break ;
            case 'E' :
                break ;
            default :
                break ;
        }
        return balance;
    } 
}