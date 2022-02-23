
/**
 * Serpinski Tetrahedran to be viewed in my space ship
 *
 * David Neufeld
 * 3/3/19
 */
import java.awt.*;
import java.util.Random;
public class sierpinski
{
    // instance variables - replace the example below with your own
    
    Random r = new Random();
    Universal u = new Universal();
    int x;
    int y;
    int z;
    double rotation=0;
    int points;
    Ball[] corner;
    Ball[] c;
    /**
     * Constructor for objects of class sierpinski
     */
    public sierpinski(int X,int Y, int Z,int size,int p)
    {
        // initialise instance variables
        x = X;
        y = Y;
        z = Z;
        points=p;
        c = new Ball[points];
        corner = new Ball[4];
        corner[0] = new Ball(x,y,z,10,Color.red);
        corner[1] = new Ball(x+size,y,z,10,Color.blue);
        corner[2] = new Ball(x+size/2,y,z+(int)(((size+0.0)/2)*Math.sqrt(3)),10,Color.green);
        corner[3] = new Ball(x+size/2,y-(int)((size/2)*Math.sqrt(3)),
        z+(int)(((size+0.0)/2)*Math.sqrt(3))/2,10,Color.white);
        
        int placeX=x;
        int placeY=y;
        int placeZ=z;
        
        for(int i=0;i<points;i++){
            int rand = r.nextInt(corner.length);
            placeX=(int)(corner[rand].getX()+placeX)/2;
            placeY=(int)(corner[rand].getY()+placeY)/2;
            placeZ=(int)(corner[rand].getZ()+placeZ)/2;
            c[i] = new Ball(placeX,placeY,placeZ,10,corner[rand].getColor());
        }
    }

    
    public void fill(Graphics g){
        //u.setRotationX(rotation);
        for(int i=0; i<corner.length;i++){
            corner[i].setRotation(rotation);
            corner[i].setTranslation(tranX,tranY,tranZ);
            corner[i].fill(g);
            //corner[i].changeRotation(Math.PI/300);
        }
        int[] distance = new int[points];
        for(int i=0;i<points;i++){
            
            distance[i]=(int)u.camDistance((int)c[i].getX(),
            (int)c[i].getY(), (int)c[i].getZ());
        }
        
        int[] order=u.arrayOrder(distance);
        int d;
        for(int i=points-1;i>=0;i--){
            d=order[i];
            c[d].setRotation(rotation);
            c[d].setTranslation(tranX,tranY,tranZ);
            c[d].fill(g);
        }
    }
    
    public void setRotation(double set){
        rotation = set;
    }
    double tranX=0,tranY=0,tranZ=0;
    public void setTranslation(double x, double y, double z){
        tranX=x;
        tranY=y;
        tranZ=z;
    }
}
