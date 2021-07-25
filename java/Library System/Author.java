import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class Author implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String authfile = "./Author.data" ;
    int ID ;
    String name;
    Address address;
    String email;
    public Author(int iD, String name, Address address, String email) {
        ID = iD;
        this.name = name;
        this.address = address;
        this.email = email;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getName() {
        return name ;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Author [ID=" + ID + ", address=" + address + ", email=" + email + "]" ;
    }
    // user interaction for City Object
    public static Author add() {
        int id;
        String name;
        Address address;
        String email;
    
        System.out.println("Please enter Author id");
        id = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter Author name");
        name = System.console().readLine();
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
    public static ArrayList<Author> initializeFromFile() {
        try {
            File f = new File(authfile) ;
            if (!f.exists()) {
                return new ArrayList<Author>();
            }
            FileInputStream fileIn = new FileInputStream(authfile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Author> list  = (ArrayList<Author>) in.readObject();
            in.close();
            fileIn.close();
            return list;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Author class not found");
            c.printStackTrace();
            return null;
        }
    }
    public static Author update(Author author) {
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
    public static void save(ArrayList<Author> authlist) {
        System.out.print("Saving master Author list in the file!!!");
        try {
            FileOutputStream fileOut =new FileOutputStream(authfile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(authlist);
            out.close();
            fileOut.close();
            System.out.println("Author data is saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static int findByID(ArrayList<Author> list, int id) {
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