
/**
 * Ball that moves in 3d space
 *
 * David Neufeld
 * 2/2/19
 */
import java.awt.*;
import java.util.Random;
public class Ball
{
    // instance variables - replace the example below with your own
    double x;
    double y;
    double z;
    double rotation=Math.PI;
    double rotationY=Math.PI;
    int radius=20;
    Color color;
    
    Random r = new Random();
    Universal u = new Universal();
    public Ball(int sx,int sy,int sz,int r,Color c)
    {
        // initialise instance variables
        
        x = sx;
        y = sy;
        z = sz;
        radius = r;
        color=c;
    }
    
    public void paintComponent(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(color);
        u.setRotationX(rotation);
        u.setRotationY(rotationY);
        int dr=u.convertRadius(x,y,z,radius);
        int dx= u.convertX(x,y,z)-dr;
        int dy= u.convertY(x,y,z)-dr;
        g.fillOval(dx,dy,dr*2,dr*2);
        g.setColor(oldColor);
    }
    
    public void fill(Graphics g){
        //System.out.println(directionX);
        x+=Math.cos(directionX)*Math.cos(directionY)*speed;
        z+=Math.sin(directionX)*Math.cos(directionY)*speed;
        y+=Math.sin(directionY)*speed;
        
        Color oldColor = g.getColor();
        g.setColor(color);
        u.setRotationX(rotation);
        int dr= u.convertRadius(x+tranX,y+tranY,z+tranZ,radius);
        int dx= u.convertX(x+tranX,y+tranY,z+tranZ)-dr;
        int dy= u.convertY(x+tranX,y+tranY,z+tranZ)-dr;
        g.fillOval(dx,dy,dr*2,dr*2);
        
        g.setColor(oldColor);
    }
    
    double speed;
    public void setSpeed(int set){
        speed = set;
    }
    double directionX=0;
    double directionY=0;
    public void setDirectionX(double set){
        directionX=set;
    }
    public void setDirectionY(double set){
        directionY=set;
    }
    
    public void setX(int set){
        x = set;
    }
    public void setY(int set){
        y = set;
    }
    public void setZ(int set){
        z = set;
    }
    public void setRotation(double set){
        rotation = set;
    }
    public void setRotationY(double set){
        rotationY = set;
    }
    public void setRadius(int set){
        radius = set;
    }
    
    public void changeX(double set){
        x += set/2;
    }
    public void changeY(double set){
        y += set/2;
    }
    public void changeZ(double set){
        z += set/2;
    }
    public void changeRadius(int set){
        radius += set;
    }
    
    public void changeRotation(double set){
        rotation+=set;
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    public int getRadius(){
        return radius;
    }
    public Color getColor(){
        return color;
    }
    public double getSpeed(){
        return speed;
    }
    
    double tranX=0,tranY=0,tranZ=0;
    public void setTranslation(double x, double y, double z){
        tranX=x;
        tranY=y;
        tranZ=z;
    }
}
