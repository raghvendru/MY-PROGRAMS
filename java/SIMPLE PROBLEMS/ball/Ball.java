public class Ball {
    float   x;
    float   y;
    int     radius;
    float   xDelta;
    float   yDelta ;

    public Ball(float x, float y, int radius, float xDelta, float yDelta) {
        this.x = x ;
        this.y = y ;
        this.radius = radius;
        this.xDelta = xDelta ;
        this.yDelta = yDelta ;
    }

    float getX() {
        return x ;
    }
    float getY() {
        return y ;
    }
    void setX(float x) {
        this.x = x ;
    }
    void setY(float y) {
        this.y = y ;
    }
    
    void setRadius(int radius) {
        this.radius = radius;
    }

    int getRadius() {
        return radius ;
    }

    float getXDelta() {
        return xDelta ;
    }
    float getYDelta() {
        return yDelta ;
    }
    void setXDelta(float xDelta) {
        this.xDelta = xDelta ;
    }
    void setYDelta(float yDelta) {
        this.yDelta = yDelta ;
    }

    void move() {
        x = x + xDelta ;
        y = y + yDelta ;
    }
    
    void reflectHorizontal() {
        xDelta = -(xDelta) ;
    }

    void reflectVertical() {
        yDelta = -(yDelta) ;
    }

    public String toString() {
        return "Ball[(" + x +  "," + y + ", speed=(" + xDelta + "," + yDelta +  ")]"; 
    }
 }

 