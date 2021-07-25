 import java.util.*;

public class StudentTest  {
    public static void main(String args[]){
        ArrayList<Student> arraylist = new ArrayList<>();
        arraylist.add(new Student("Vikram", "progam1",2021,10));
        arraylist.add(new Student("ABC", "progam2",2019,100));
        arraylist.add(new Student("XYZ", "progam3",2011,1000));
        System.out.println("Sort by Comparable - Name");
        Collections.sort(arraylist);
        for(Student str: arraylist){
             System.out.println(str);
        }
        System.out.println("Sort by Comparator - Name");
        Collections.sort(arraylist, Student.compareByName);
        for(Student str: arraylist){
            System.out.println(str);
        }
        System.out.println("Sort by Comparator - Year");
        Collections.sort(arraylist, Student.compareByYear);
        for(Student str: arraylist){
            System.out.println(str);
        }

        
        ArrayList<Student> list1 = new ArrayList <Student>();
        list1.add(new Student("abc","pgm1",2019,100));
        list1.add(new Student("bca","pgm2",2011,100));
        list1.add(new Student("cbc","pgm3",2009,100));
        
        ArrayList<Student> list2 = new ArrayList <>();
        list2.add(new Student("abc","pgm1",2019,100));
        list2.add(new Student("bca","pgm2",2011,100));
        list2.add(new Student("cbc","pgm3",2009,100));
        
        Comparator<Student> comp = new CompareStudentByName();
        Comparator<Student> comp1 = new CompareStudentByYear();
        
        System.out.println("Sort by Comparator - Name");
        Collections.sort(list1, comp);
        for(Student str: list1){
            System.out.println(str);
        }
        System.out.println("Sort by Comparator - Year");
        Collections.sort(list2, comp1);
        for(Student str: list2){
            System.out.println(str);
        }

    }
    public static class CompareStudentByName implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
            return s1.getName().compareTo(s2.getName());
        }
    }
    public static class CompareStudentByYear implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
            return s1.getYear() - s2.getYear();
        }
    }
}
