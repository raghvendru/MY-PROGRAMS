public class MovablePoint extends Point{
    float xSpeed=0.0f; 
    float ySpeed=0.0f;
    public MovablePoint(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public MovablePoint(float x, float y, float xSpeed, float ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public MovablePoint() {
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
    public void setSpeed(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public float[] getSpeed(){
        float[] a=new float[2];
        a[0]=this.xSpeed;
        a[1]=this.ySpeed;
        return a;
    }
    @Override
    public String toString() {
        return "MovablePoint [xSpeed=" + xSpeed + ", ySpeed=" + ySpeed +  ", x="+ this.x+ ", y="+this.y+ "]";
    }
    public MovablePoint move() {
        this.x+=xSpeed;
        this.y+=ySpeed;
        return this;

        
    }
} 