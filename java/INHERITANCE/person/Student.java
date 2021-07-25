public class Student extends Person {
    String program ;
    int    year ;
    int    fee ;
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }
    public Student(String name, String address, String program, int year, int fee) {
        super(name, address);
        this.program = program;
        this.year = year;
        this.fee = fee;
    }
    @Override
    public String toString() {
        return "Student [fee=" + fee + ", program=" + program + ", year=" + year + "]";
    }
    
}
