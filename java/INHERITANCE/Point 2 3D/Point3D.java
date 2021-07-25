public class Point3D extends Point2D{//instance variables
    float z;

    public Point3D() {
    super();
    this.z=0;
    }

    public Point3D(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
        
    }
    public float[] getXYZ() {
        float[] results = new float[3];
        results[0] = this.x;
        results[1] = this.y;
        results[2] = this.z;
        return results;
     }
  
     // Sets both x and y 
     public void setXYZ(float x, float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        
     }

    @Override
    public String toString() {
        return "Point3D [x=" + x + ",y="+y+",z="+z+"]";
    }

   
 }