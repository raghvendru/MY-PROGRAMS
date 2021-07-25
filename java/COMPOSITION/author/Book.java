public class Book {
    String name;
    Author[] Author;
    Double price;
    int qty;
    Genre Genre;
    public Book(String name,Author[] author,Double price,Genre genre){
        this.Author=author;
        this.name=name;
        this.price=price;
        this.Genre=genre;

    }
    public Book(String name,Author[] author,Double price,int qty,Genre genre){
        this.Author=author;
        this.name=name;
        this.price=price;
        this.qty=qty;
        this.Genre=genre;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Author[] getAuthor() {
        return Author;
    }
    public void setAuthor(Author[] author) {
        Author = author;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public Genre getGenre() {
        return Genre;
    }
    public void setGenre(Genre genre) {
        this.Genre = genre;
    }
    public String toString() {
        String str=Author[0].toString+ "" +Author[1].toString();
        return "Author [name=" + name + ", Author=" + "]";
    }

    
}
