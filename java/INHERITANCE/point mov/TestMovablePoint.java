public class TestMovablePoint{
    public static void main(String args[]){
        Point p1;
        MovablePoint p2;
        p1 =new Point(3.2f,8.2f);
        System.out.println(p1);
        p2=new MovablePoint(4.3f,3.4f,3.4f,5.3f);
        System.out.println(p2);
        System.out.println(p2.move());
    }
}