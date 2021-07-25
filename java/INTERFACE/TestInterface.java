public class TestInterface  {
    static class crcl implements Drawable {
        public void draw() {
            System.out.println("implementation of draw crcl!");
        }
    }
    static class rect implements Drawable {
        public void draw() {
            System.out.println("implementation of draw rect!");
        }
    }
 
     

    public static void main(String args[]){
        crcl tst = new crcl();
        rect tst1 = new rect();
        tst.draw();
        tst1.draw();
    }
}