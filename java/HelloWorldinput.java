/** Simple hello world java  */

public class HelloWorldinput {
    public static void main(String[] args) {
        System.out.println("Please enter your name");
        String name = System.console().readLine();

        System.out.println("Hello"+ name+ "! Welcome to Programming World!");
    }
}