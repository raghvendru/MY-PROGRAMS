import java.io.*;
public class TestAuthorSer2 {
   public static void main(String [] args) {
       AuthorSer1 auth = new AuthorSer1("author5","email5",'M');
       System.out.println("Serialized AuthorSer1...");
       System.out.println("Name: " + auth.getName());
       System.out.println("email: " + auth.getEmail());
       System.out.println("gender: " + auth.getGender());
       System.out.println("test: " + auth.getTest());
        try {
            FileOutputStream fileOut =
            new FileOutputStream("./author2.data");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(auth);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./author2.data");
        } catch (IOException i) {
            i.printStackTrace();
        }

      // now deserialise
        AuthorSer1 author1 ;
        try {
            FileInputStream fileIn = new FileInputStream("./author2.data");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            author1 = (AuthorSer1) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("AuthorSer class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized AuthorSer1...");
        System.out.println("Name: " + author1.getName());
        System.out.println("email: " + author1.getEmail());
        System.out.println("gender: " + author1.getGender());
        System.out.println("test: " + author1.getTest());
    }
}