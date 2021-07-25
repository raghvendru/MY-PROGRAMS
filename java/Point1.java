public class Point1{
    float x=0;
    float y=0;
    public Point1() {
    }
    public Point1(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float[] getXY() {
        float[] results = new float[2];
        results[0] = this.x;
        results[1] = this.y;
        return results;
     }
  
     // Sets both x and y 
     public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
     }
    @Override
    public String toString() {
        return "Point1 [x=" + x + ", y=" + y + "]";
    }
    
 }
 