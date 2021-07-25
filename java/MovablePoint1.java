public class MovablePoint1 extends Point1{
    float xSpeed=0.0f;
    float ySpeed=0.0f;
    public MovablePoint1(final float x, final float y, final float xSpeed, final float ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public MovablePoint1( float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public MovablePoint1() {
    }
    public float getxSpeed() {
        return xSpeed;
    }
    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }
    public float getySpeed() {
        return ySpeed;
    }
    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
    public float[] getXY() {
        float[] results = new float[2];
        results[0] = this.xSpeed;
        results[1] = this.ySpeed;
        return results;
     }
  
     // Sets both x and y 
     public void setXY(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
     }
    @Override
    public String toString() {
        return "MovablePoint1 [xSpeed=" + xSpeed + ", ySpeed=" + ySpeed + "]";
    }
   
    public void move(int x, int y)
    {
      super.move(x, y);
      makeDirty();
    }
    
 }
 