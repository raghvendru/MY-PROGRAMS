import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class Publisher implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String pubfile = "./Publisher.data" ;
    int ID;
    String name;
    Address address;
    String email;
    public Publisher(int ID, String name, Address address, String email) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.email = email;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
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
        return "Publisher [ID=" + ID + ", address=" + address.toString() + ", email=" + email + ", name=" + name + "]";
    } 
    public static Publisher add() {
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
        return new Publisher(id,name,a,email);
            
    }
    public static ArrayList<Publisher> initializeFromFile() {
        try {
            File f = new File(pubfile) ;
            if (!f.exists()) {
                return new ArrayList<Publisher>();
            }
            FileInputStream fileIn = new FileInputStream(pubfile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Publisher> list = (ArrayList<Publisher>) in.readObject();
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
    public static Publisher update(Publisher publisher) {
        int ID ;
        String name;
        String email;
        System.out.println("Author Information is:");
        System.out.println(publisher);  
        System.out.println("Please update Author name");
        name = System.console().readLine();
        System.out.println("read name is " + name);
        publisher.name = name ;
        System.out.println("Updated name is " +publisher.name);
        email =System.console().readLine();
        System.out.println("read email is"+email);
        System.out.println("Updated name is " + publisher.email);
        publisher.email=email;
        System.out.println("update address");
        System.out.println("update add1");
        String add1;
        String add2;
        add1= System.console().readLine();
        System.out.println("read name is " + add1);
        publisher.address.setAdd1(add1);
        add2= System.console().readLine();
        System.out.println("read name is " + add2);
        publisher.address.setAdd1(add2);
        return publisher ;
    }
    public static void save(ArrayList<Publisher>publist) {
        System.out.print("Saving master Author list in the file!!!");
        try {
            FileOutputStream fileOut =new FileOutputStream(pubfile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(publist);
            out.close();
            fileOut.close();
            System.out.println("Publisher data is saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static int findByID(ArrayList<Publisher> list, int id) {
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