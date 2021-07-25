public class AuthorSer implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    // instance properties - 
    String  name;
    String  email;
    char    gender ;
    
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
    public AuthorSer(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
    public AuthorSer() {
    }
    public String toString() {
            return "Author[Name= " + name +  ",Email = " + email + ", Gender = " + gender + "]"; 
    }
    
}