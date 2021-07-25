import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
public class LibTran implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String file = "./trans.data" ;

    int ID; // ideally this should be auto increment 
    Date date; // current date
    int LibraryID;
    int bookID;
    int Qty;
    int CustomerID ;
    char type; // 'I' or 'R'
    public LibTran(char type, int CustomerID, int iD, Date date, int libraryID, int bookID, int qty) {
        this.type = type;
        ID = iD;
        this.CustomerID = CustomerID;
        this.date = date;
        LibraryID = libraryID;
        this.bookID = bookID;
        Qty = qty;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getLibraryID() {
        return LibraryID;
    }
    public void setLibraryID(int libraryID) {
        LibraryID = libraryID;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public int getQty() {
        return Qty;
    }
    public void setQty(int qty) {
        Qty = qty;
    }
    @Override
    public String toString() {
        return "LibTran [Customer=" + CustomerID +  ", transType: " + type + ", LibraryID=" + LibraryID + ", Qty=" + Qty + ", bookID=" + bookID + ", date="
                + date + "]";
    }
    public char getType() {
        return type;
    }
    public void setType(char type) {
        this.type = type;
    }
    public static ArrayList<LibTran> initializeFromFile() {
        try {
            File f = new File(file) ;
            if (!f.exists()) {
                return new ArrayList<LibTran>();
            }
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<LibTran> list  = (ArrayList<LibTran>) in.readObject();
            in.close();
            fileIn.close();
            return list ;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("LibTran class not found");
            c.printStackTrace();
            return null;
        }
    }

    public static void save(ArrayList<LibTran> list) {
        System.out.print("Saving Trans LibTran list in the file!!!");
        try {
            FileOutputStream fileOut =new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.println("Bookstock data is saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public int getCustomerID() {
        return CustomerID;
    }
    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }
}
