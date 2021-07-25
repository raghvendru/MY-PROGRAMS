public class Customer {
    String name;
    int id;
    int discount;
    public Customer( int id,String name, int discount) {
        this.name = name;
        this.id = id;
        this.discount = discount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getID() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public String toString() {
        return "Customer[(" +id +  "," + name + "," + discount+ ")]"; 
    }
    
}