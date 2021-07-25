import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class JArrayListProblems {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();
        list.add("Voilet");
        list.add("Indigo");
        list.add("Blue");
        list.add("Green");
        list.add("Yellow");
        list.add("Orange");
        list.add("Red");

        waitForUserInput();

        // 1. Write a Java program to create a new array list, add some colors (string)
        // and print out the collection.
        System.out.println("List of colors");
        printlist(list);

        waitForUserInput();
        // 2. Write a Java program to iterate through all elements in a array list.
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        waitForUserInput();
        // 3. Write a Java program to insert an element into the array list at the first
        // position.
        list.add(0, "Color0");
        printlist(list);

        // 4. Write a Java program to retrieve an element (at a specified index) from a
        // given array list.
        System.out.println("Please enter index to read from list");
        int index = Integer.parseInt(System.console().readLine());
        // Interger is a Wrapper Class which has methods to manipulate the int
        System.out.println("element at index " + index + "is " + list.get(index));

        System.out.println("5. Write a Java program to update specific array element by given element.");
        String element = System.console().readLine();
        String newelement = System.console().readLine();
        index = list.indexOf(element);
        if (index == -1) {
            System.out.println("element " + element + " not found");
        } else {
            list.set(index, newelement);
        }

        waitForUserInput();
        System.out.print("6. Write a Java program to remove the third element from a array list. ");
        list.remove(3);

        System.out.print("7. Write a Java program to search an element in a array list. ");
        element = System.console().readLine();
        String str = list.contains(element) ? " found " : " not found ";
        System.out.println(element + str);
        waitForUserInput();
        System.out.println("Write a Java program to sort a given array list. ");
        Collections.sort(list);
        printlist(list);
        waitForUserInput();
        System.out.println("9. Write a Java program to copy one array list into another. ");
        // you can create a new list
        // iterate thru the old list and add to new one

        ArrayList<String> nlist = new ArrayList<>(list);
        printlist(nlist);

        waitForUserInput();  
        System.out.println("1 0. Write a Java program to shuffle elements in a array list. ");
        Collections.shuffle(list);
        printlist(list);
        waitForUserInput();
        System.out.println("11. Write a Java program to reverse elements in a array list.");
        Collections.reverse(list);
        printlist(list);

        waitForUserInput();
        System.out.println("12. Write a Java program to extract a portion of a array list.");

        ArrayList<String> sublist = new ArrayList<String>(list.subList(2, 4));
        printlist(sublist);
        waitForUserInput();
        System.out.println("13. Write a Java program to compare two array lists. ");
        System.out.println(list.equals(sublist));
        waitForUserInput();
        System.out.println("14. Write a Java program of swap two elements in an array list.");
        String temp = list.get(1);
        list.set(1, list.get(2));
        list.set(2, temp);
        printlist(list);
        waitForUserInput();
        System.out.println("15. Write a Java program to join two array lists. ");
        list.addAll(sublist);
        printlist(list);
        waitForUserInput();
        System.out.println("16. Write a Java program to clone an array list to another array list.");
        nlist = (ArrayList) sublist.clone();
        printlist(nlist);
        waitForUserInput();
        System.out.println("17. Write a Java program to empty an array list. ");
        nlist.clear();
        printlist(nlist);
        waitForUserInput();
        System.out.println("18. Write a Java program to test an array list is empty or not.");
        System.out.println(list.isEmpty());
        waitForUserInput();
        System.out.println("19. Write a Java program to trim the capacity of an array list the current list size. ");
        list.trimToSize();
        waitForUserInput();
        System.out.println("20. Write a Java program to increase the size of an array list. ");
        list.ensureCapacity(100);
        System.out.println(list.size());
        waitForUserInput();
        System.out.println(
                "21. Write a Java program to replace the second element of a ArrayList with the specified element. ");
        list.set(2, "mew");
        waitForUserInput();
        System.out.println(
                "22. Write a Java program to print all the elements of a ArrayList using the position of the elements. ");
        printlistwithindex(list);

    }

    static void waitForUserInput() {
        System.out.println("Type anything to continue!");
        String str = System.console().readLine();
        return;
    }

    static void printlistwithindex(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }

    static void printlist(ArrayList<String> list) {
        list.forEach((element) -> {
            System.out.println(element);
        });
    }
}