
/**
 * Line in 3D Space
 *
 * David Neufeld
 * 2/2/19
 */
import java.awt.*;
public class Line
{
    // instance variables - replace the example below with your own
    int startX;
    int startY;
    int startZ;
    int endX;
    int endY;
    int endZ;
    double rotation=Math.PI;
    Color color;
    Universal u;// = new Universal();
    public Line(int x1,int y1,int z1,int x2,int y2,int z2,Color c)
    {
        startX=x1;
        startY=y1;
        startZ=z1;
        endX=x2;
        endY=y2;
        endZ=z2;
        color=c;
        u = new Universal();
    }
    
    public void draw(Graphics g)
    {
        Color oldColor=g.getColor();
        g.setColor(color);
        u.setRotationX(rotation);
        int dx1=u.convertX(startX,startY,startZ);
        int dy1=u.convertY(startX,startY,startZ);
        
        int dx2=u.convertX(endX,endY,endZ);
        int dy2=u.convertY(endX,endY,endZ);
        
        g.drawLine(dx1,dy1,dx2,dy2);
        g.setColor(oldColor);
    }
    
    public void setRotation(double set){
        rotation = set;
    }
}
