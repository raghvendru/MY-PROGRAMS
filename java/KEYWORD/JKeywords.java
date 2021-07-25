public class JKeywords{
    final   int finalaint = 100 ; 
    private int privateint = 1 ;
    protected int protectedint = 2;
    public int publicint = 3;

    static  int staticint = 200 ;
    
    static void commonMethod() {
        System.out.println("Class method shared among all instances !!!");
        System.out.println("value of class instance is " + staticint);
    }

    public int getPrivateInt() {
        return privateint ;
    }

    public void testPrivate() {
        System.out.println("Private method !!!");
        System.out.println("protectdInt is " + protectedint + " privateint is " + privateint + " publicint is " + publicint);
    }

    public void getAllPublic() {
        System.out.println("Public method !!!");
        System.out.println("protectdInt is " + protectedint + " privateint is " + privateint + " publicint is " + publicint);
    }
}