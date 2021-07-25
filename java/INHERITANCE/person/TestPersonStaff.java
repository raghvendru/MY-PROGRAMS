public class TestPersonStaff{
    public static void main(String args[]){
        Person p1,p2;
        Student st1, st2 ;
        Staff stf1, stf2 ;

        p1 = new Person("Vikram", "BSK 3rd Stage");
        System.out.println(p1.toString());

        p2 = new Person();
        p2.setAddress("add1");
        p2.setName("name2");
        System.out.println(p2.toString());
    
        st1 = new Student("st1", "add1", "pgm1", 2020, 0);
        stf1 = new Staff("stf1", "stafadd1", "sch1", 120);
        System.out.println(st1.toString());
        System.out.println(stf1.toString());
    }
}