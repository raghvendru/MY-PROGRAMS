import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Book implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String bookfile = "./book.data" ;
    String title;
    Author author;
    Cat cat;
    int ISBN;
    Publisher publisher;
    double price;
    int bookId;
    int yearOfPublication;
    double editionNumber;
    String language;
    public Book(String title, Author author, Cat cat, int iSBN, Publisher publisher, double price,
            int bookId, int yearOfPublication, double editionNumber, String language) {
        this.title = title;
        this.author = author;
        this.cat = cat;
        ISBN = iSBN;
        this.publisher = publisher;
        this.price = price;
        this.bookId = bookId;
        this.yearOfPublication = yearOfPublication;
        this.editionNumber = editionNumber;
        this.language = language;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public Cat getCat() {
        return cat;
    }
    public void setCategory(Cat cat) {
        this.cat = cat;
    }
    public int getISBN() {
        return ISBN;
    }
    public void setISBN(int iSBN) {
        ISBN = iSBN;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getYearOfPublication() {
        return yearOfPublication;
    }
    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
    public double getEditionNumber() {
        return editionNumber;
    }
    public void setEditionNumber(double editionNumber) {
        this.editionNumber = editionNumber;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    @Override
    public String toString() {
        return "Book [ISBN=" + ISBN + ", author=" + author + ", bookId=" + bookId + ", cat="
                + cat.toString() + ", editionNumber=" + editionNumber + ", language=" + language + ", price="
                + price + ", publisher=" + publisher + ", title=" + title + ", yearOfPublication=" + yearOfPublication
                + "]";
    };
    public static Book add(ArrayList<Author>authlist ,ArrayList<Publisher> publist, ArrayList<Cat> catlist){
        String title;
        Author author;
        Cat cat;
        Publisher publisher;
        int ISBN;        
        double price;
        int bookId;
        int yearOfPublication;
        double editionNumber;
        String language;
        System.out.println("please enter title");
        title = System.console().readLine();
        System.out.println("please enter ISBN");
        ISBN = Integer.parseInt(System.console().readLine());
        System.out.println("please enter book id");
        bookId = Integer.parseInt(System.console().readLine());
        System.out.println("please enter book price");
        price = Double.parseDouble(System.console().readLine());
        System.out.println("please enter year of publication");
        yearOfPublication = Integer.parseInt(System.console().readLine());    
        System.out.println("enter edition number");
        editionNumber = Double.parseDouble(System.console().readLine());
        System.out.println("enter language");
        language = System.console().readLine();    

        //author
        int id;
        int idx;
        System.out.println("Please enter Author id");
        id = Integer.parseInt(System.console().readLine());
        idx = Author.findByID(authlist, id);
        if (idx == -1) {
            author = Author.add();
        } else {
            author = authlist.get(idx);
        }
        //publisher
        System.out.println("Please enter publisher id");
        id = Integer.parseInt(System.console().readLine());
        idx = Publisher.findByID(publist, id);
        if (idx == -1) {
            publisher = Publisher.add();
        } else {
            publisher = publist.get(idx);
        }    
        //category
        System.out.println("Please enter category id");
        id = Integer.parseInt(System.console().readLine());
        idx = Cat.findByID(catlist, id);
        if (idx == -1) {
            cat = Cat.add();
        } else {
            cat = catlist.get(idx);
        }  
        return new Book(title, author, cat,ISBN, publisher, price, bookId, yearOfPublication, editionNumber, language);
             
    }
    public static ArrayList<Book> initializeFromFile() {
        try {
            File f = new File(bookfile) ;
            if (!f.exists()) {
                return new ArrayList<Book>();
            }
            FileInputStream fileIn = new FileInputStream(bookfile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Book> list = (ArrayList<Book>) in.readObject();
            in.close();
            fileIn.close();
            return list;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Book class not found");
            c.printStackTrace();
            return null;
        }

    }
    
    public static void save(ArrayList<Book> booklist) {
        System.out.print("Saving master Book list in the file!!!");
        try {
            FileOutputStream fileOut =new FileOutputStream(bookfile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(booklist);
            out.close();
            fileOut.close();
            System.out.println("Book data is saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static int findByID(ArrayList<Book> list, int BookId) {
        int idx = -1;
        int size = list.size() ;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getBookId() == BookId) {
                idx = i ;
            }
        }
        return idx ;
    }
    public static Book update(Book book,ArrayList<Author>authlist ,ArrayList<Publisher> publist, ArrayList<Cat> catlist){
        Author author;
        Cat cat;
        Publisher publisher;
        String title;
        int ISBN;        
        double price;
        int yearOfPublication;
        double editionNumber;
        String language;
        System.out.println("Book information");
        System.out.println(book);
        System.out.println("update title");
        title = System.console().readLine();
        System.out.println("read title is " + title);
        book.title=title;
        System.out.println("update Isbn");
        ISBN = Integer.parseInt(System.console().readLine());
        System.out.println("read isbn is " + ISBN);
        book.ISBN=ISBN;
        System.out.println("update price");
        price = Double.parseDouble(System.console().readLine());
        System.out.println("read price is " + price);
        book.price=price;
        System.out.println("update year of publication");
        yearOfPublication = Integer.parseInt(System.console().readLine());
        System.out.println("read year of publication is " + yearOfPublication);
        book.yearOfPublication=yearOfPublication;
        System.out.println("update edition number");
        editionNumber = Double.parseDouble(System.console().readLine());
        System.out.println("read edition numis " +editionNumber );
        book.editionNumber=editionNumber;
        System.out.println("update language");
        language = System.console().readLine();
        System.out.println("read language numis " +language );
        book.language=language;
        //author
        book.author = Author.update(book.author);
        book.publisher=Publisher.update(book.publisher);
        book.cat=Cat.update(book.cat); 
        return book;
    }

}
