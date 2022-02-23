
/**
 * Write a description of class Explosion here.
 *
 * David Neufeld
 * 4/2/19
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
//import java.awt.geom.*;
public class ExplosionGif
{
    // instance variables - replace the example below with your own
    Universal u = new Universal();
    int x;
    int y;
    int z;
    double rotation;
    int radius;
    
    BufferedImage pic[];
    /**
     * Constructor for objects of class Explosion
     */
    public ExplosionGif(int X, int Y, int Z,int r)throws IOException
    {
        // initialise instance variables
        x = X;
        y = Y;
        z = Z;
        pic = new BufferedImage[10];
        for(int i=0;i<10;i++){
            pic[i]=ImageIO.read(new File("explosion"+
            Integer.toString(i+1)+".gif"));
        }
        //pic = ImageIO.read(new File("explosion.gif"));
        radius=r;
    }

    double age=0;
    public void fill(Graphics g){
        //if(age<pic.length){
        u.setRotationX(rotation);
        //u.setTranslation(tranX,tranY,tranZ);
        int dr= u.convertRadius(x+tranX,y+tranY,z+tranZ,radius);
        int dx= u.convertX(x+tranX,y+tranY,z+tranZ)-dr;
        int dy= u.convertY(x+tranX,y+tranY,z+tranZ)-dr;
        //boolean show=true;
        g.setColor(Color.white);
        //g.drawString(Integer.toString(dr),10,10);
        if(dr>0&&(int)age<pic.length)
        g.drawImage(pic[(int)age],dx,dy,dr*2,dr*2,null);
        if(age<pic.length) age+=0.2;
        //if(age>=10) age=0;
        //}
    }
    
    public boolean expired(){
        return (age>=10);
    }
    
    public void setRotation(double set){
        rotation = set;
    }
    
    double tranX=0,tranY=0,tranZ=0;
    public void setTranslation(double Sx, double Sy, double Sz){
        tranX=Sx;
        tranY=Sy;
        tranZ=Sz;
    }
    
    public double getRadius(){
        return radius;
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
    public void setX(int set){
        x = set;
    }
    public void setY(int set){
        y = set;
    }
    public void setZ(int set){
        z = set;
    }
    
    public void set(int sx,int sy,int sz,int r){
        age=0;
        x=sx;y=sy;z=sz;
        radius=r;
    }
}
