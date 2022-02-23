
/**
 * Chos Game but 3D
 *
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class ChaosGame extends JPanel
{
    int m=1;
    int d=2;
    double rotation=0;
    Ball[] c;
    Ball[] corner;
    Line[] line = new Line[12];
    Random r = new Random();
    int h=400;
    Universal u = new Universal();
    
    Tetrahedron tet;
    /**
     * Constructor for objects of class Chaos
     */
    public ChaosGame(int s,int points, Color backColor){
        
        c = new Ball[points];
        setBackground(backColor);
        corner = new Ball[s];
        corner[0] = new Ball(488,h,232,1,Color.red);
        corner[1] = new Ball(147,h,171,1,Color.blue);
        corner[2] = new Ball(265,h,497,1,Color.green);
        corner[3] = new Ball(300,h-400,300,1,Color.black);
        
        x=(int)corner[0].getX();
        y=(int)corner[0].getY();
        z=(int)corner[0].getZ();
        
        int[] tx = new int[4];
        int[] ty = new int[4];
        int[] tz = new int[4];
        tx[0]=488;ty[0]=400;tz[0]=232;
        tx[1]=147;ty[1]=400;tz[1]=171;
        tx[2]=265;ty[2]=400;tz[2]=497;
        tx[3]=300;ty[3]=0;tz[3]=300;
        
        tet = new Tetrahedron(tx,ty,tz);
    }
    
    int count=0;
    int x=100;
    int y=100;
    int z=100;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw the corners
        for(int i=0; i<corner.length;i++){
            corner[i].setRotation(rotation);
            corner[i].fill(g);
            //corner[i].changeRotation(Math.PI/300);
        }
        int rand = r.nextInt(corner.length);
        // x=(int)(x+corner[rand].getX())/2;
        // y=(int)(x+corner[rand].getY())/2;
        // z=(int)(x+corner[rand].getZ())/2;
        x=(int)(m*(corner[rand].getX()-x)/d+x);
        y=(int)(m*(corner[rand].getY()-y)/d+y);
        z=(int)(m*(corner[rand].getZ()-z)/d+z);
        if(count<c.length){
            c[count]=new Ball(x,y,z,1,corner[rand].getColor());
            g.drawString(Integer.toString(count),10,30);
        }
        
        //create an order array
        int[] distance = new int[count];
        for(int i=0;i<count;i++){
            if(i<c.length){
                distance[i]=u.rotateZ(c[i].getX(),c[i].getZ(),rotation);
            }
        }
        int[] order = u.arrayOrder(distance);
        for(int i=0;i<count;i++){
            if(i<c.length){
                int d = order[count-i-1];
                c[i].setRotation(rotation);
                c[i].fill(g);
            }
        }
        rotation+=Math.PI/300;
        count++;
        
        //tet.setRotation(rotation);
        //tet.draw(g);
        repaint();
    }
}
