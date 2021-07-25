public class Square1 extends Shape{
    double side=1.0;

    public Square1(double side) {
        this.side = side;
    }

    public Square1(String color, boolean filled, double side) {
        super(color, filled);
        this.side = side;
    }

    public Square1() {
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }   
    public double getArea(){
        return side*side;
    }
    public double getPerimeter(){
        return 4*side;
    }
    public String toString() {
        return "Square1 [shape[Color="+Color+", filled="+filled+"], side=" + side+ "]";
    }

}