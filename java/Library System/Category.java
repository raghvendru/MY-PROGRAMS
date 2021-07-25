public class Category implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    int ID;
    String name;
    public Category(int iD, String name) {
        ID = iD;
        this.name = name;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Category [ID=" + ID + ", name=" + name + "]";
    }  
}
