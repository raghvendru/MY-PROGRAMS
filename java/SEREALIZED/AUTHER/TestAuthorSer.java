import java.io.*;
public class TestAuthorSer {

   public static void main(String [] args) {
        AuthorSer author = new AuthorSer("Vikram", "vikram@itfym.com",'M');
        System.out.println("AuthorSer before serialization...");
        System.out.println("Name: " + author.name);
        System.out.println("email: " + author.email);
        System.out.println("gender: " + author.gender);
        try {
            FileOutputStream fileOut =
            new FileOutputStream("./author.data");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(author);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./author.data");
        } catch (IOException i) {
            i.printStackTrace();
        }

      // now deserialise
        AuthorSer author1 ;
        try {
            FileInputStream fileIn = new FileInputStream("./author.data");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            author1 = (AuthorSer) in.readObject();
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
        System.out.println("Deserialized AuthorSer...");
        System.out.println("Name: " + author1.name);
        System.out.println("email: " + author1.email);
        System.out.println("gender: " + author1.gender);
    }
}