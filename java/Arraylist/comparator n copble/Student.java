import java.util.Comparator;
import java.lang.Comparable;
public class Student implements Comparable {
    String program ;
    String name ;
    int    year ;
    int    fee ;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
    public Student(String name, String program, int year, int fee) {
        this.name = name;
        this.program = program;
        this.year = year;
        this.fee = fee;
    }
    @Override
    public String toString() {
        return "Student [Name=" + name + "fee=" + fee + ", program=" + program + ", year=" + year + "]";
    }

    public int compareTo(Object s) {
        // System.out.println( this.toString() + " comparing with " + ((Student)s).toString());
        return name.compareTo(((Student)s).name) ;
    }

    // public int compareTo(Object s) {
    //     return (year - ((Student)s).year) ;
    // }    

    public static Comparator<Student> compareByName = new Comparator<Student>() {
    
        public int compare(Student s1, Student s2) {
            return s1.name.compareTo(s2.name);
        }
    };

    public static Comparator<Student> compareByYear = new Comparator<Student>() {
    
        public int compare(Student s1, Student s2) {
            return s1.year - s2.year;
        }
    };
}

