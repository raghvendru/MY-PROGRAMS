public class Invoice{
    Customer customer;
    int id;
    double amount;

    public Invoice(int id,Customer customer,  double amount) {
        this.customer = customer;
        this.id = id;
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
     this.amount=amount;
    }

    public String getCustomerName() {
        return customer.getName();
    }
    public int getCustomerID() {
        return customer.getID();
    }
    public double getCustomerDiscount() {
        return customer.discount;
    }

    public double getAmountAfterDiscount() {
        return amount=(amount+customer.getDiscount()/100);
    }


    public String toString() {
        return "Invoice[(" + id+  ","+customer.name+"," + customer.discount +")]"; 
    }
}