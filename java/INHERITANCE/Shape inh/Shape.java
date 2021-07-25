public class Shape{
    String Color="red";
    boolean filled=true;
    public Shape() {
    }
    public Shape(String color, boolean filled) {
        Color = color;
        this.filled = filled;
    }
    public String getColor() {
        return Color;
    }
    public void setColor(String color) {
        Color = color;
    }
    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    @Override
    public String toString() {
        return "Shape [Color=" + Color + ", filled=" + filled + "]";
    }
}