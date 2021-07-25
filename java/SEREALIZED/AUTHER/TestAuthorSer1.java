import java.io.*;
public class TestAuthorSer1 {

    static  void printArray(AuthorSer[] ar) {
        for (int i = 0; i < ar.length ; i++) {
            System.out.println("Name: " + ar[i].name);
            System.out.println("email: " + ar[i].email);
            System.out.println("gender: " + ar[i].gender);
        }
    }
   public static void main(String [] args) {
       AuthorSer[] authlist = new AuthorSer[5];
       authlist[0] = new AuthorSer("author1","email1",'M');
       authlist[1] = new AuthorSer("author2","email2",'M');
       authlist[2] = new AuthorSer("author3","email3",'M');
       authlist[3] = new AuthorSer("author4","email4",'M');
       authlist[4] = new AuthorSer("author5","email5",'M');
       System.out.println("Serialized AuthorSer...");
       printArray(authlist);
        try {
            FileOutputStream fileOut =
            new FileOutputStream("author.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(authlist);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./author1.data");
        } catch (IOException i) {
            i.printStackTrace();
        }

      // now deserialise
        AuthorSer[] author1 ;
        try {
            FileInputStream fileIn = new FileInputStream("author.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            author1 = (AuthorSer[]) in.readObject();
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
        printArray(author1);
    }
}