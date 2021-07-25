public class Point2D {//instance variables
   float x;
   float y;
   
   public Point2D() {
      this.x=0.0f;
      this.y=0.0f;
   }
   public Point2D(float x, float y) {
   
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
      return "Point2D [x=" + x + ", y=" + y + "]";
   }

}