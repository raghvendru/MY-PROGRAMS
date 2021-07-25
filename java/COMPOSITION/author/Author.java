public class Author {
    String name;
    String Email;
    char Gender;
    public Author(String name, String Email, char Gender) {
        this.Email=Email;
        this.name=name;
        this.Gender=Gender;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = Email;
    }
    @Override
    public String toString() {
        return "Author [Email=" + Email + ", Gender=" + Gender + ", name=" + name + "]";
    }
    public char getGender() {
        return Gender;
    }
    public void setGender(char gender) {
        Gender = gender;
    }
}