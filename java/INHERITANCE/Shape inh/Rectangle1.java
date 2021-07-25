public class Rectangle1 extends Shape{
    double width=1.0;
    double length=1.0;
    public Rectangle1(double width, double length) {
        this.width = width;
        this.length = length;
    }
    public Rectangle1(String color, boolean filled, double width, double length) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }
    public Rectangle1() {
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getArea(){
        return length*width;
    }
    public double getPerimeter(){
        return 2*(length+width);
    }
    public String toString() {
        return "Rectangle1 [shape[Color="+Color+", filled="+filled+"], length=" + length + ",width=" + width +"]";
    }
}