public class InvoiceItem{
  String   id;
    String desc;
    int qty;
    double unitPrice ;

   
    
    
    public String getID() {
        return id;
    }
    public String getDesc() {
        return desc;
    }

    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    double getTotal() {
        return (unitPrice) * (qty);
       }
       public String toString() {
        return "InvoiceItem[(" + id +  "," +desc+","+qty+","+unitPrice+")]"; 
    }
    public InvoiceItem(String id, String desc, int qty, double unitPrice) {
        this.id = id;
        this.desc = desc;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
    
}


    