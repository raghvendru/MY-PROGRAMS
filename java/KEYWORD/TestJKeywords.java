public class TestJKeywords{
    public static void main(String args[]){

        System.out.println(JKeywords.staticint);
        System.out.println("Calling static method");
        JKeywords.commonMethod();

        // create instance of class
        JKeywords test = new JKeywords();
        
        // following line produces error trying to modify the final variable
        // test.finalaint = 1;

        // following line produces error
        System.out.println("Printing privateint without the method");
        // System.out.println(test.privateint);

        System.out.println("Printing protectedint without the method");
        System.out.println(test.protectedint);

        System.out.println("Printing publicint without the method");
        System.out.println(test.publicint);

        System.out.println("private member access");
        test.testPrivate();


    }
}