
/**
 * Panel object in 3d space
 *
 * David Neufeld
 * 2/15/19
 */
import java.awt.*;
import java.util.Arrays;
public class Panel
{
    int[] x;
    int[] y;
    int[] z;
    int centerX;
    int centerY;
    int centerZ;
    Color color;
    double rotation;
    double rotationY=Math.PI;
    double multiply=1;
    boolean border=false;
    Universal u = new Universal();

    public Panel(int[] X, int[] Y, int[] Z, Color c){
        x=X;
        y=Y;
        z=Z;
        color=c;
        if(x.length==4){
            centerX=(x[1]+x[3])/2;
            centerY=(y[1]+y[3])/2;
            centerZ=(z[1]+z[3])/2;
        }
        int xSum=0;
        int ySum=0;
        int zSum=0;
        for(int i=0;i<x.length;i++){
            xSum+=x[i];
            ySum+=y[i];
            zSum+=z[i];
        }
        centerX=xSum/x.length;
        centerY=ySum/x.length;
        centerZ=zSum/x.length;
        multiply=1;
    }

    public void fill(Graphics g){
        //if(u.visible(centerX-(int)tranX,centerY-(int)tranY,centerZ-(int)tranZ)){
        Color oldColor = g.getColor();
        g.setColor(color);
        //u.setTranslation(tranX,tranY,tranZ);
        
        boolean show = true;
        int[] dx = new int[x.length];
        int[] dy = new int[y.length];
        int[] dz = new int[z.length];
        u.setRotationX(rotation);
        u.setRotationY(rotationY);
        //multiply=1;
        //System.out.println(multiply);
        for(int i=0; i<x.length;i++){
            double spunX=spinX(x[i],z[i]);
            double spunZ=spinZ(x[i],z[i]);
            dx[i]=u.convertX(multiply*spunX+tranX
                ,multiply*y[i]+tranY,multiply*spunZ+tranZ);
            dy[i]=u.convertY(multiply*spunX+tranX
                ,multiply*y[i]+tranY,multiply*spunZ+tranZ);
            //int r = u.convertRadius(x[i]+tranX,y[i]+tranY,z[i]+tranZ,10);
            //g.drawString(Integer.toString(r),10,10);
            if(multiply*y[i]+tranY<300&&dy[i]>300) show=false;
            if(multiply*y[i]+tranY>300&&dy[i]<300) show=false;
            //show=true;
        }
        if(show)
        g.fillPolygon(dx,dy,x.length);
        if(border){
            g.setColor(new Color(0,0,0));
            g.drawPolygon(dx,dy,x.length);
        }
        g.setColor(oldColor);
        //}
    }
    
    public void giveBorders(){
        border = true;
    }
    
    public void addSpin(double set){
        spin+=set;
    }
    public void setSpin(double set){
        spin=set;
    }
    double spin=0;
    public double spinX(double X, double Z){
        if (spin==0) return X;
        if(X<0) return (Math.cos(Math.atan(Z/X)+spin)*Math.sqrt(X*X+Z*Z));
        else return -(Math.cos(Math.atan(Z/X)+spin)*Math.sqrt(X*X+Z*Z));
    }
    
    public double spinZ(double X, double Z){
        if (spin==0) return Z;
        if(X<0) return (Math.sin(Math.atan(Z/X)+spin)*Math.sqrt(X*X+Z*Z));
        else return -(Math.sin(Math.atan(Z/X)+spin)*Math.sqrt(X*X+Z*Z));
    }
    
    
    public void setMultiply(double set){
        multiply = set;
    }

    public void setRotation(double set){
        rotation = set;
    }
    
    public void setRotationY(double set){
        rotationY = set;
    }

    public int getCenterX(){
        return (int)spinX((multiply*centerX),(multiply*centerZ));
    }

    public int getCenterY(){
        return (int)(multiply*centerY);
    }

    public int getCenterZ(){
        return (int)spinZ((multiply*centerX),(multiply*centerZ));
    }
    
    public int camDistance(){
        int shortest=10000;
        for(int i=0; i<x.length;i++){
            int test=(int)u.camDistance(x[i],y[i],z[i]);
            if(test<shortest) shortest=test;
        }
        return shortest;
    }

    public int getClosestX(){
        int[] sx=new int[x.length];
        for(int i=0;i<x.length;i++){
            sx[i]=(int)u.qRotate(x[i],z[i],rotation).getReal();
        }
        Arrays.sort(sx);
        return sx[y.length-1];
    }

    public int getClosestY(){
        int[] sy=new int[y.length];
        for(int i=0;i<y.length;i++){
            sy[i]=y[i];
        }
        Arrays.sort(sy);
        return sy[y.length-1];
    }

    public int getClosestZ(){
        int[] sz=new int[z.length];
        for(int i=0;i<z.length;i++){
            sz[i]=(int)u.qRotate(x[i],z[i],rotation).getImaginary();
        }
        Arrays.sort(sz);
        return sz[y.length-1];
    }
    
    public void setColor(Color c){
        color = c;
    }
    
    double tranX=0,tranY=0,tranZ=0;
    public void setTranslation(double x, double y, double z){
        tranX=x;
        tranY=y;
        tranZ=z;
    }
    
    public void setZoom(double set){
        u.setZoom(set);
    }
}
