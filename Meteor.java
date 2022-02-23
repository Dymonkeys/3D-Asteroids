
/**
 * 20 sided sape
 *
 * David Neufeld
 * 3/1/19
 */
import java.awt.*;
import java.util.Random;
public class Meteor
{
    // instance variables - replace the example below with your own
    //int changeX=0,changeY=0,changeZ=0;
    int x=0,y=0,z=0;
    //int startX,startY,startZ;
    int size;
    Universal u = new Universal();
    Panel[] panel = new Panel[26];//26 total
    double rotation=3*Math.PI/2;
    double rotationY=3*Math.PI/2;
        Random r = new Random();
    /**
     * Constructor for objects of class Icosahedron
     */
    public Meteor(int X, int Y,int Z, int s)
    {
        //startX=x;startY=y;startZ=z;
        size=s;
        s=s/2;
        panel[0]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+-1*s,y+-1*s,y+1*s,y+1*s},
        new int[] {z+-2*s,z+-2*s,z+-2*s,z+-2*s}, u.ShadeColor(65,65,65,0));
        panel[1]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+-2*s,y+-2*s,y+-2*s,y+-2*s},
        new int[] {z+-1*s,z+-1*s,z+1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[2]=new Panel(
        new int[] {x+-2*s,x+-2*s,x+-2*s,x+-2*s},
        new int[] {y+-1*s,y+-1*s,y+1*s,y+1*s},
        new int[] {z+-1*s,z+1*s,z+1*s,z+-1*s}, u.ShadeColor(65,65,65,0));
        panel[3]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+-1*s,y+-1*s,y+1*s,y+1*s},
        new int[] {z+2*s,z+2*s,z+2*s,z+2*s}, u.ShadeColor(65,65,65,0));
        panel[4]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+2*s,y+2*s,y+2*s,y+2*s},
        new int[] {z+-1*s,z+-1*s,z+1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[5]=new Panel(
        new int[] {x+2*s,x+2*s,x+2*s,x+2*s},
        new int[] {y+-1*s,y+-1*s,y+1*s,y+1*s},
        new int[] {z+-1*s,z+1*s,z+1*s,z+-1*s}, u.ShadeColor(65,65,65,0));
        
        //Edges
        panel[6]=new Panel(
        new int[] {x+-1*s,x+-1*s,x+-2*s,x+-2*s},
        new int[] {y+-1*s,y+1*s,y+1*s,y+-1*s},
        new int[] {z+-2*s,z+-2*s,z+-1*s,z+-1*s}, u.ShadeColor(65,65,65,0));
        panel[7]=new Panel(
        new int[] {x+1*s,x+1*s,x+2*s,x+2*s},
        new int[] {y+-1*s,y+1*s,y+1*s,y+-1*s},
        new int[] {z+-2*s,z+-2*s,z+-1*s,z+-1*s}, u.ShadeColor(65,65,65,0));
        panel[8]=new Panel(
        new int[] {x+-1*s,x+-1*s,x+-2*s,x+-2*s},
        new int[] {y+-1*s,y+1*s,y+1*s,y+-1*s},
        new int[] {z+2*s,z+2*s,z+1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[9]=new Panel(
        new int[] {x+1*s,x+1*s,x+2*s,x+2*s},
        new int[] {y+-1*s,y+1*s,y+1*s,y+-1*s},
        new int[] {z+2*s,z+2*s,z+1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[10]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+-1*s,y+-1*s,y+-2*s,y+-2*s},
        new int[] {z+-2*s,z+-2*s,z+-1*s,z+-1*s}, u.ShadeColor(65,65,65,0));
        panel[11]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+1*s,y+1*s,y+2*s,y+2*s},
        new int[] {z+-2*s,z+-2*s,z+-1*s,z+-1*s}, u.ShadeColor(65,65,65,0));
        panel[12]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+-1*s,y+-1*s,y+-2*s,y+-2*s},
        new int[] {z+2*s,z+2*s,z+1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[13]=new Panel(
        new int[] {x+-1*s,x+1*s,x+1*s,x+-1*s},
        new int[] {y+1*s,y+1*s,y+2*s,y+2*s},
        new int[] {z+2*s,z+2*s,z+1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[14]=new Panel(
        new int[] {x-1*s,x-1*s,x-2*s,x-2*s},
        new int[] {y-2*s,y-2*s,y-1*s,y-1*s},
        new int[] {z+1*s,z-1*s,z-1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[15]=new Panel(
        new int[] {x+1*s,x+1*s,x+2*s,x+2*s},
        new int[] {y-2*s,y-2*s,y-1*s,y-1*s},
        new int[] {z+1*s,z-1*s,z-1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[16]=new Panel(
        new int[] {x-1*s,x-1*s,x-2*s,x-2*s},
        new int[] {y+2*s,y+2*s,y+1*s,y+1*s},
        new int[] {z+1*s,z-1*s,z-1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        panel[17]=new Panel(
        new int[] {x+1*s,x+1*s,x+2*s,x+2*s},
        new int[] {y+2*s,y+2*s,y+1*s,y+1*s},
        new int[] {z+1*s,z-1*s,z-1*s,z+1*s}, u.ShadeColor(65,65,65,0));
        
        //Corners
        panel[18]=new Panel(
        new int[] {x+2*s,x+1*s,x+1*s},
        new int[] {y+1*s,y+2*s,y+1*s},
        new int[] {z+1*s,z+1*s,z+2*s}, u.ShadeColor(65,65,65,0));
        panel[19]=new Panel(
        new int[] {x-2*s,x-1*s,x-1*s},
        new int[] {y+1*s,y+2*s,y+1*s},
        new int[] {z+1*s,z+1*s,z+2*s}, u.ShadeColor(65,65,65,0));
        panel[20]=new Panel(
        new int[] {x+2*s,x+1*s,x+1*s},
        new int[] {y-1*s,y-2*s,y-1*s},
        new int[] {z+1*s,z+1*s,z+2*s}, u.ShadeColor(65,65,65,0));
        panel[21]=new Panel(
        new int[] {x-2*s,x-1*s,x-1*s},
        new int[] {y-1*s,y-2*s,y-1*s},
        new int[] {z+1*s,z+1*s,z+2*s}, u.ShadeColor(65,65,65,0));
        panel[22]=new Panel(
        new int[] {x+2*s,x+1*s,x+1*s},
        new int[] {y+1*s,y+2*s,y+1*s},
        new int[] {z-1*s,z-1*s,z-2*s}, u.ShadeColor(65,65,65,0));
        panel[23]=new Panel(
        new int[] {x-2*s,x-1*s,x-1*s},
        new int[] {y+1*s,y+2*s,y+1*s},
        new int[] {z-1*s,z-1*s,z-2*s}, u.ShadeColor(65,65,65,0));
        panel[24]=new Panel(
        new int[] {x+2*s,x+1*s,x+1*s},
        new int[] {y-1*s,y-2*s,y-1*s},
        new int[] {z-1*s,z-1*s,z-2*s}, u.ShadeColor(65,65,65,0));
        panel[25]=new Panel(
        new int[] {x-2*s,x-1*s,x-1*s},
        new int[] {y-1*s,y-2*s,y-1*s},
        new int[] {z-1*s,z-1*s,z-2*s}, u.ShadeColor(65,65,65,0));
        
        x=X;
        y=Y;
        z=Z;
        //doubleX=x;
        //doubleY=y;
        //doubleZ=z;
    }
    //double doubleX; double doubleY; double doubleZ;
     public void fill(Graphics g){
        //x+=1;
        
        x+=Math.cos(directionX)*Math.cos(directionY)*speed;
        z+=Math.sin(directionX)*Math.cos(directionY)*speed;
        y+=Math.sin(directionY)*speed;
        
        
        
        u.setRotationX(rotation);
        u.setRotationY(rotationY);
        u.setTranslation(tranX,tranY,tranZ);
        int[] distance = new int[panel.length];
        for(int i=0;i<panel.length;i++){
            
            distance[i]=(int)u.camDistance(panel[i].getCenterX(),
            panel[i].getCenterY(), panel[i].getCenterZ());
        }
        int[] order=u.arrayOrder(distance);
        int d;
        ///int count=0;
        for(int i=panel.length/2;i>=0;i--){
            d=order[i];
            //panel[d].setMultiply(size/5);
            panel[d].setRotation(rotation);
            panel[d].setRotationY(rotationY);
            panel[d].setTranslation(tranX,tranY,tranZ);
            panel[d].fill(g);
        }
        //sets the color of the panels based on cam distance
        for(int i=0;i<panel.length;i++){
            int light=(distance[i]-distance[order[0]])/6-100;
            panel[i].setColor(u.ShadeColor(65,65,65,-light));
        }
    }
    
    double directionX=r.nextDouble()*Math.PI*2;
    double directionY=r.nextDouble()*Math.PI*2;
    public void setDirectionX(double set){
        double directionX=set;
    }
    
    public void setDirectionY(double set){
        double directionX=set;
    }
    
    double speed=0;
    public void setSpeed(int set){
        speed=set;
    }
    
    public void setRotation(double set){
        rotation = set;
    }
    
    public void setRotationY(double set){
        rotationY = set;
    }
    
    double tranX=0,tranY=0,tranZ=0;
    public void setTranslation(double Sx, double Sy, double Sz){
        tranX=Sx+x;//+changeX;
        tranY=Sy+y;//+changeY;
        tranZ=Sz+z;//-changeZ;
    }
    
    public void changeX(int set){
        //doubleX+=set;
        x+=set;
    }
    public void changeY(int set){
        //doubleY+=set;
        y+=set;
    }
    public void changeZ(int set){
        //doubleZ+=set;
        z+=set;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }
    public int getSize(){
        return size;
    }
    
    
    public void setZoom(double set){
        for(int i=0;i<panel.length;i++){
            panel[i].setZoom(set);
        }
    }
}
