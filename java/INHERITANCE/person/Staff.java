public class Staff extends Person {
    String school ;
    double pay ;
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public double getPay() {
        return pay;
    }
    public void setPay(double pay) {
        this.pay = pay;
    }
    public Staff(String name, String address, String school, double pay) {
        super(name, address);
        this.school = school;
        this.pay = pay;
    }
    @Override
    public String toString() {
        return "Staff [pay=" + pay + ", school=" + school + "]";
    }
}