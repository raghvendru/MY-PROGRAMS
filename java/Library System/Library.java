import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class Library implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String libfile = "./Library.data" ;
    int ID;
    String Name;
    Address address;
    Person Librarian;
    public Library(int iD, String name, Address address, Person librarian) {
        ID = iD;
        Name = name;
        this.address = address;
        Librarian = librarian;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Person getLibrarian() {
        return Librarian;
    }
    public void setLibrarian(Person librarian) {
        Librarian = librarian;
    }
    @Override
    public String toString() {
        return "Library [ID=" + ID + ", Librarian=" + Librarian + ", Name=" + Name + ", address=" + address + "]";
    }
    public static Library add() {
        int id;
        String Name;
        Address address;
        Person person;
        System.out.println("Please enter library id");
        id = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter Library name");
        Name = System.console().readLine();
        System.out.println("Please enter add1");
        String add1= System.console().readLine();
        System.out.println("Please enter add2");
        String add2= System.console().readLine();
        System.out.println("Please enter city id");
        int cityid = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter state id");
        int stateid = Integer.parseInt(System.console().readLine());
        Address a = new Address(add1,add2,cityid,stateid);
        System.out.println("please enter person id");
        int ID=Integer.parseInt(System.console().readLine());
        System.out.println("please enter person name");
        String name=System.console().readLine();
        System.out.println("please enter email id");
        String emailId=System.console().readLine();
        System.out.println("please enter phone num");
        int phoneNum=Integer.parseInt(System.console().readLine());
        Person p = new Person(ID,Name,emailId,phoneNum);
        return new Library(id,name,a,p);       
    }
    public static ArrayList<Library> initializeFromFile() {
        try {
            File f = new File(libfile) ;
            if (!f.exists()) {
                return new ArrayList<Library>();
            }
            FileInputStream fileIn = new FileInputStream(libfile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Library> list  = (ArrayList<Library>) in.readObject();
            in.close();
            fileIn.close();
            return list;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Library class not found");
            c.printStackTrace();
            return null;
        }
    }    
    public static Library update(Library library){
        int ID;
        String Name;
        System.out.println("Library Information is:");
        System.out.println(library);  
        System.out.println("Please update library name");
        Name = System.console().readLine();
        System.out.println("read name is " + Name);
        library.Name = Name ;
        System.out.println("update address");
        System.out.println("update add1");
        String add1= System.console().readLine();
        System.out.println("read name is " + add1);
        library.address.setAdd1(add1);
        System.out.println("update add1");
        String add2= System.console().readLine();
        System.out.println("read name is " + add2);
        library.address.setAdd2(add2);
        String name;
        System.out.println("update name");
        name = System.console().readLine();
        System.out.println("read name is " + name);
        library.Librarian.setName(name);
        System.out.println("update name");
        String email = System.console().readLine();
        System.out.println("read name is " + email);
        library.Librarian.setEmailID(email);
        System.out.println("update Phone num");
        int phonenum;
        phonenum = Integer.parseInt(System.console().readLine());
        System.out.println("read name is " + phonenum);
        library.Librarian.setPhoneNum(phonenum);
        return library;                     
    }    
    public static void save(ArrayList<Library> liblist) {
        System.out.print("Saving master Library list in the file!!!");
        try {
            FileOutputStream fileOut =new FileOutputStream(libfile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(liblist);
            out.close();
            fileOut.close();
            System.out.println("Library data is saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static int findByID(ArrayList<Library> list, int id) {
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