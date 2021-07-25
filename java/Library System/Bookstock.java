
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bookstock implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String file = "./bookstock.data" ;
    private int LibraryID ;
    private int BookID ;
    private int OpeningStock;
    private Date OpeningDate ;
    private int StockOnHand; // initially this is equal to opening stock at the beginning
    private Date lastUpdateDate;

    public int getLibraryID() {
        return LibraryID;
    }
    public void setLibraryID(int libraryID) {
        LibraryID = libraryID;
    }
    public int getBookID() {
        return BookID;
    }
    public void setBookID(int bookID) {
        BookID = bookID;
    }
    public int getOpeningStock() {
        return OpeningStock;
    }
    public void setOpeningStock(int openingStock) {
        OpeningStock = openingStock;
    }
    public Date getOpeningDate() {
        return OpeningDate;
    }
    public void setOpeningDate(Date openingDate) {
        OpeningDate = openingDate;
    }
    public int getStockOnHand() {
        return StockOnHand;
    }
    public void setStockOnHand(int stockOnHand) {
        StockOnHand = stockOnHand;
    }
    public Bookstock(int libraryID, int bookID, int openingStock, Date openingDate, int stockOnHand) {
        LibraryID = libraryID;
        BookID = bookID;
        OpeningStock = openingStock;
        OpeningDate = openingDate;
        StockOnHand = stockOnHand;
    }
    @Override
    public String toString() {
        return "Bookstock [BookID=" + BookID + ", LibraryID=" + LibraryID + ", OpeningDate=" + OpeningDate
                + ", OpeningStock=" + OpeningStock + ", StockOnHand=" + StockOnHand + ", Last Update Date=" + lastUpdateDate + "]";
    }
    

    // user interaction for Bookstock Object
    public static Bookstock add() {
        int     id, boookid,qty;
        String  name;
        System.out.println("Please enter LibraryID for which you want to add Books");
        id = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter BookID ");
        boookid = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter how many you are adding ");
        qty = Integer.parseInt(System.console().readLine());
        return new Bookstock(id, boookid, qty, new Date(), qty);
    }

    public static Bookstock IssueOrReceipt(Bookstock bkstk, int qty) {
        System.out.println("BookStock Information is:");
        System.out.println(bkstk);  
        bkstk.StockOnHand += qty ;
        bkstk.lastUpdateDate = new Date();
        System.out.println("BookStock Information now is:");
        return bkstk;
    }

    // callee will pass the citylist contianing list of cities to be saved / serialized
    public static void save(ArrayList<Bookstock> list) {
        System.out.print("Saving tran Bookstock list in the file!!!");
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

    public static ArrayList<Bookstock> initializeFromFile() {
        try {
            File f = new File(file) ;
            if (!f.exists()) {
                return new ArrayList<Bookstock>();
            }
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Bookstock> list  = (ArrayList<Bookstock>) in.readObject();
            in.close();
            fileIn.close();
            return list ;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Bookstock class not found");
            c.printStackTrace();
            return null;
        }
    }

    public static void viewAvaiableBooks(ArrayList<Bookstock> list, int libraryID) {
        int size = list.size() ;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getLibraryID() == libraryID || libraryID == -1) {
                System.out.println(list.get(i));
            }
        }
    }
    public static int findByID(ArrayList<Bookstock> list, int libraryID, int BookID) {
        int idx = -1;
        int size = list.size() ;
        for (int i = 0; i < size; i++) {
            if ((list.get(i).getBookID() == BookID) && (list.get(i).getLibraryID() == libraryID)) {
                idx = i ;
            }
        }
        return idx ;
    }
}
 