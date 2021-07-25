public class Employee {
    int   id;
    String firstName;
    String lastName;
    int salary ;
    
    public int getID() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
   
    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return ("firstName,lastName");
    }
    
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getAnnualSalary() {
        return salary*12;
    }
    public int raiseSalary(int percent) {
    int raise= salary*percent/100; 
    this.salary=salary+raise;
    return this.salary; 
    }
    public String toString() {
        return "Employee[(" + id +  "," +firstName +","+lastName+","+salary+")]"; 
    }

    public Employee(int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }
}


    