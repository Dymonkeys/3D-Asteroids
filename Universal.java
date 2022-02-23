 
/**
 * Contains Methods to be used in other classes
 *
 * David Neufeld 
 * 2/2/19
 */ 
import java.util.*;
import java.awt.*;
public class Universal
{
    Random r = new Random();

    double zoomin = 1;//0.7;
    int midx=300;
    int midy=300;
    double rotationX=Math.PI;
    double rotationY=Math.PI;
    int tranX;
    int tranY;
    int tranZ;
    public int zoomX(double x,double y,double z){
        double zoom=(600*zoomin)/z;
        double cx=(x-midx)/3;
        double cy=(y-midy)/3;
        double angle;
        if (cx==0) {
            if(x>midx) angle=Math.PI/2;
            else angle=3*Math.PI/2;
        }
        else
            angle=Math.atan(cy/cx);
        double zoomx;
        if(cx>0) zoomx=midx+Math.cos(angle)*Math.sqrt(cx*cx+cy*cy)*zoom;
        else zoomx=midx-Math.cos(angle)*Math.sqrt(cx*cx+cy*cy)*zoom;
        return (int)zoomx;
    }

    public int zoomY(double x,double y,double z){
        double zoom=(600*zoomin)/z;
        double cx=(x-midx)/4;
        double cy=(y-midy)/4;
        double angle;
        if (cx==0) {
            if(x>midx) angle=Math.PI/3;
            else angle=3*Math.PI/3;
        }
        else
            angle=Math.atan(cy/cx);
        double zoomy;
        if(cx>0) zoomy=midy+Math.sin(angle)*Math.sqrt(cx*cx+cy*cy)*zoom;
        else if(cx==0) zoomy= midy+cy*zoom;
        else zoomy=midy-Math.sin(angle)*Math.sqrt(cx*cx+cy*cy)*zoom;
        return (int)zoomy;
    }
 
    public int convertRadius(double x,double y, double z,double radius){
        // still need to set rotation here
        Complex R = qRotate(x,z,rotationX);
        
        int Z=(int)R.getImaginary();
        double zoom=Z/(600*zoomin);
        return (int)(radius/zoom);
        //return 2;
    }
   
    public Complex qRotate(double x, double y, double a){
        a+=Math.PI;
        x-=300;
        y-=300;
        Complex point = new Complex(x,y);
        Complex rotate = new Complex(Math.cos(a),Math.sin(a));
        Complex result = point.multiply(rotate);
        result.add(300,300);
        return result;
    }
    
    public int rotateX(double x, double y, double a){
        return (int)qRotate(x,y,a).getReal();
    }
    
    public int rotateZ(double x, double y, double a){
        return (int)qRotate(x,y,a).getImaginary();
    }

    public int convertX(double x, double y, double z){
        x+=tranX;
        y+=tranY;
        z+=tranZ;
        
        Complex R = qRotate(x,z,rotationX);
        
        int X=(int)R.getReal();
        int Z=(int)R.getImaginary();
        int Y=(int)y;
        return zoomX(X,Y,Z);
    }

     public int convertY(double x, double y, double z){
        x+=tranX;
        y+=tranY;
        z+=tranZ;
        Complex R = qRotate(x,z,rotationX);
        
        int X=(int)R.getReal();
        int Z=(int)R.getImaginary();
        int Y=(int)y;
        return zoomY(X,Y,Z);
    }
    

    public int[] arrayOrder(int[] arr){
        //returns the order of the index values as the value increases
        double[] sorted=new double[arr.length];
        int[] order=new int[arr.length];
        //Arrays.fill(order,0);
        for(int i=0;i<arr.length;i++){
            sorted[i]=arr[i];
        }
        Arrays.sort(sorted);
        for(int i=0;i<arr.length;i++){
            int pos = Arrays.binarySearch(sorted,arr[i]);
            if(pos>=0){
                order[pos]=i;
                if(pos>0){
                    if (sorted[pos]==sorted[pos-1]) sorted[pos]+=0.1;
                    else sorted[pos]-=0.1;
                }
                else sorted[pos] -= 0.1;
            }
        }
        
        return order;
    }

    public int getMidX(){
        return midx;
    }
    
    public int getMidY(){
        return midy;
    }

    public void setRotationX(double set){
        rotationX = set;
    }

    public void setRotationY(double set){
        rotationY = set;
    }

    public void setTranX(int set){
        tranX = set;
    }

    public void setTranY(int set){
        tranY = set;
    }

    public void setTranZ(int set){
        tranZ = set;
    }
    
    public void setZoom(double set){
        zoomin=set;
    }
    
    public void setTranslation(double x, double y,double z){
        tranX=(int)x;
        tranY=(int)y;
        tranZ=(int)z;
    }
    
    //public Point Cam(){
        
    //}
    
    // public int camX(){
        // Complex R = qRotate(300,0,-1*rotationX);
        // int X=(int)R.getReal();
        // return X;
    // }

    // public int camY(){
        // return 300;
    // }

    // public int camZ(){
        // Complex R = qRotate(300,0,-1*rotationX);
        // int Z=(int)R.getImaginary();
        // return Z;
    // }

    public double camDistance(int x, int y, int z){
        Complex R = qRotate(300,0,-1*rotationX);
        
        int camX=(int)R.getReal();
        int camZ=(int)R.getImaginary();
        int camY=300;
        
        return distance(x,y,z,camX-tranX,camY-tranY,camZ-tranZ);
    }

    public double distance(double x1, double y1, double z1, 
    double x2, double y2, double z2){
        double cx = x2-x1;
        double cy = y2-y1;
        double cz = z2-z1;
        return Math.sqrt(cx*cx+cy*cy+cz*cz);
    }
    
    public boolean visible(int x, int y, int z){
        Complex R = qRotate(x,z,-1*rotationX);
        int convertZ=(int)R.getImaginary();
        if(convertZ<0) return false;
        return true;
    }

    public Color randomColor(){
        Color[] colors = new Color[8];
        colors[0]=Color.red;
        colors[1]=Color.green;
        colors[2]=Color.blue;
        colors[3]=Color.yellow;
        colors[4]=Color.cyan;
        colors[5]=Color.magenta;
        colors[6]=Color.white;
        colors[7]=Color.orange;
        return colors[r.nextInt(8)];
    }
    
    public Color ShadeColor(int red, int green, int blue,int light){
        red+=light;
        green+=light;
        blue+=light;
        if(red>255) red=255;
        if(green>255) green=255;
        if(blue>255) blue=255;
        if(red<=0) red=0;
        if(green<0) green=0;
        if(blue<0) blue=0;
        return(new Color(red, green, blue));
    }
}
