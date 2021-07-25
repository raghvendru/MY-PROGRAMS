import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AuthorSer1 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    // instance properties - 
    String  name;
    String  email;
    char    gender ;
    transient int test = 10;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public AuthorSer1(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
    public AuthorSer1() {
    }
    public String toString() {
            return "Author[Name= " + name +  ",Email = " + email + ", Gender = " + gender + "]"; 
    }
    public int getTest() {
        return test;
    }
    public void setTest(int test) {
        this.test = test;
    }

    private void writeObject(ObjectOutputStream oos) 
      throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this.getTest());
    }

    private void readObject(ObjectInputStream ois) 
      throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        Integer Test = (Integer) ois.readObject();
        this.test = Test.intValue();
    }
    
}