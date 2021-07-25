public class Rectangle {
    float  length;
    float   width;

    public Rectangle() {
        length=1;
        width=1;

    }



    public Rectangle(float length, float width) {
        this.length = length;
        this.width = width;
    }
    public float getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    double getArea() {
     return length*width;
    }
    double getPerimeter() {
        return (2*length)+(2*width);
    }
    public String toString() {
        return "Rectangle[(" +length +  "," + width + ")]"; 
    }
    
}

    