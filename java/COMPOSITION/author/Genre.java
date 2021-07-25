public class Genre {
    
    int id;
    String name;
    public Genre(String string, int i) {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Genre [id=" + id + ", name=" + name + "]";
    }
}
