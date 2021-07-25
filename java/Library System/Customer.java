import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

import java.time.format.DateTimeFormatter;
public class Customer extends Person implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private static final String custfile = "./Customer.data" ;
    int AccountID;
    Date joinDate;
    Date TransDate;
    int numOfBooks;
    public Customer(int iD, String name, String emailID, int phoneNum, int accountID, Date joinDate, Date transDate,
            int numOfBooks) {
        super(iD, name, emailID, phoneNum);
        AccountID = accountID;
        this.joinDate = joinDate;
        TransDate = transDate;
        this.numOfBooks = numOfBooks;
    }
    public int getAccountID() {
        return AccountID;
    }
    public void setAccountID(int accountID) {
        AccountID = accountID;
    }
    public Date getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public Date getTransDate() {
        return TransDate;
    }
    public void setTransDate(Date transDate) {
        TransDate = transDate;
    }
    public int getNumOfBooks() {
        return numOfBooks;
    }
    public void setNumOfBooks(int numOfBooks) {
        this.numOfBooks = numOfBooks;
    }
    @Override
    public String toString() {
        return "Customer [AccountID=" + AccountID + ", TransDate=" + TransDate + ", joinDate=" + joinDate
                + ", numOfBooks=" + numOfBooks + "]";
    };
    public static Author add() {
        int AccountID;
        Date joinDate;
        Date TransDate;
        int numOfBooks;
    
        System.out.println("Please enter customer account id");
        AccountID = Integer.parseInt(System.console().readLine());
        DateFormatter sdf = new DateFormatter();
        // Scanner sc = new Scanner(System.in);
        // String str = sc.nextLine();
        // System.out.println("Please enter customer joindate");
        // Date date = Formatter.parse(D));
        System.out.println("Please enter email id");
        email = System.console().readLine();
        System.out.println("Please enter add1");
        String add1= System.console().readLine();
        System.out.println("Please enter add2");
        String add2= System.console().readLine();
        System.out.println("Please enter city id");
        int cityid = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter state id");
        int stateid = Integer.parseInt(System.console().readLine());
        Address a = new Address(add1,add2,cityid,stateid);
        return new Author(id,name,a,email);
            
    }
    public static ArrayList<Customer> initializeFromFile() {
        try {
            File f = new File(custfile) ;
            if (!f.exists()) {
                return new ArrayList<Customer>();
            }
            FileInputStream fileIn = new FileInputStream(custfile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Customer> list  = (ArrayList<Customer>) in.readObject();
            in.close();
            fileIn.close();
            return list;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("customer class not found");
            c.printStackTrace();
            return null;
        }
    }
    public static Customer update(Customer customer) {
        int ID ;
        String name;
        String email;
        System.out.println("Author Information is:");
        System.out.println(author);  
        System.out.println("Please update Author name");
        name = System.console().readLine();
        System.out.println("read name is " + name);
        author.name = name ;
        System.out.println("Updated name is " + author.name);
        email =System.console().readLine();
        System.out.println("read email is"+email);
 
 
        System.out.println("Updated name is " + author.email);
        author.email=email;
        System.out.println("update address");
        System.out.println("update add1");
        String add1;
        String add2;
        add1= System.console().readLine();
        System.out.println("read name is " + add1);
        author.address.setAdd1(add1);
        add2= System.console().readLine();
        System.out.println("read name is " + add2);
        author.address.setAdd1(add2);
        return author ;
    }
    public static void save(ArrayList<Customer> custlist) {
        System.out.print("Saving master Author list in the file!!!");
        try {
            FileOutputStream fileOut =new FileOutputStream(custfile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(custlist);
            out.close();
            fileOut.close();
            System.out.println("customer data is saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static int findByID(ArrayList<Customer> list, int id) {
        int idx = -1;
        int size = list.size() ;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getID() == id) {
                idx = i ;
            }
        }
        return idx ;
    }
}