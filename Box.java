
/**
 * Cube made of Panels
 *
 * David Neufeld
 * 2/15/19
 */
import java.awt.*;
import java.util.Arrays;
public class Box
{
    int startX;
    int startY;
    int startZ;
    int endX;
    int endY;
    int endZ;
    double rotation=Math.PI;
    
    int centerX;
    int centerY;
    int centerZ;
    
    Panel[] panel = new Panel[6];
    Line[] line = new Line[12];
    
    Universal u = new Universal();
    int[][] px = new int[6][4];
    int[][] py = new int[6][4];
    int[][] pz = new int[6][4];
    /**
     * Constructor for objects of class Cube
     */
    public Box(int x1, int y1, int z1, int x2, int y2, int z2)
    {
        startX = x1;
        startY = y1;
        startZ = z1;
        endX = x2;
        endY = y2;
        endZ = z2;
        
        centerX=(startX+endX)/2;
        centerY=(startY+endY)/2;
        centerZ=(startZ+endZ)/2;
        
        px[0][0]=startX; py[0][0]=startY; pz[0][0]=startZ;
        px[0][1]=endX; py[0][1]=startY; pz[0][1]=startZ;
        px[0][2]=endX; py[0][2]=endY; pz[0][2]=startZ;
        px[0][3]=startX; py[0][3]=endY; pz[0][3]=startZ;
        panel[0] = new Panel(px[0], py[0], pz[0], Color.pink);
        
        px[1][0]=startX; py[1][0]=startY; pz[1][0]=startZ;
        px[1][1]=endX; py[1][1]=startY; pz[1][1]=startZ;
        px[1][2]=endX; py[1][2]=startY; pz[1][2]=endZ;
        px[1][3]=startX; py[1][3]=startY; pz[1][3]=endZ;
        panel[1] = new Panel(px[1], py[1], pz[1], Color.red);
        
        px[2][0]=startX; py[2][0]=startY; pz[2][0]=startZ;
        px[2][1]=startX; py[2][1]=startY; pz[2][1]=endZ;
        px[2][2]=startX; py[2][2]=endY; pz[2][2]=endZ;
        px[2][3]=startX; py[2][3]=endY; pz[2][3]=startZ;
        panel[2] = new Panel(px[2], py[2], pz[2], Color.blue);
        
        px[3][0]=startX; py[3][0]=startY; pz[3][0]=endZ;
        px[3][1]=endX; py[3][1]=startY; pz[3][1]=endZ;
        px[3][2]=endX; py[3][2]=endY; pz[3][2]=endZ;
        px[3][3]=startX; py[3][3]=endY; pz[3][3]=endZ;
        panel[3] = new Panel(px[3], py[3], pz[3], Color.pink);
        
        px[4][0]=startX; py[4][0]=endY; pz[4][0]=startZ;
        px[4][1]=endX; py[4][1]=endY; pz[4][1]=startZ;
        px[4][2]=endX; py[4][2]=endY; pz[4][2]=endZ;
        px[4][3]=startX; py[4][3]=endY; pz[4][3]=endZ;
        panel[4] = new Panel(px[4], py[4], pz[4], Color.red);
        
        px[5][0]=endX; py[5][0]=startY; pz[5][0]=startZ;
        px[5][1]=endX; py[5][1]=startY; pz[5][1]=endZ;
        px[5][2]=endX; py[5][2]=endY; pz[5][2]=endZ;
        px[5][3]=endX; py[5][3]=endY; pz[5][3]=startZ;
        panel[5] = new Panel(px[5], py[5], pz[5], Color.blue);
        
        //px[2][0]=endX; py[2][0]=20; pz[2][0]=20;
        
        
        line[0]=new Line(startX,startY,startZ,endX,startY,startZ,Color.black);
        line[1]=new Line(endX,endY,startZ,endX,startY,startZ,Color.black);
        line[2]=new Line(endX,endY,startZ,startX,endY,startZ,Color.black);
        line[3]=new Line(startX,startY,startZ,startX,endY,startZ,Color.black);
        
        line[4]=new Line(startX,startY,endZ,endX,startY,endZ,Color.black);
        line[5]=new Line(endX,endY,endZ,endX,startY,endZ,Color.black);
        line[6]=new Line(endX,endY,endZ,startX,endY,endZ,Color.black);
        line[7]=new Line(startX,startY,endZ,startX,endY,endZ,Color.black);
        
        line[8]=new Line(startX,startY,startZ,startX,startY,endZ,Color.black);
        line[9]=new Line(endX,startY,startZ,endX,startY,endZ,Color.black);
        line[10]=new Line(endX,endY,startZ,endX,endY,endZ,Color.black);
        line[11]=new Line(startX,endY,startZ,startX,endY,endZ,Color.black);
        
    }
    
    public void fill(Graphics g){
        u.setRotationX(rotation);
        int[] distance = new int[6];
        for(int i=0;i<6;i++){
            distance[i]=(int)u.camDistance(panel[i].getCenterX(),
            panel[i].getCenterY(), panel[i].getCenterZ());
            //if (distance[i]==300) System.out.println("Flicker");
        }
        int[] order=u.arrayOrder(distance);
        int d;
        for(int i=4;i>=0;i--){
            d=order[i];
            if(d==4&&centerY>u.getMidY()) d=1;
            panel[d].setRotation(rotation);
            panel[d].fill(g);
        }
    }
    
    public void draw(Graphics g){
        for(int i=0;i<12;i++){
            line[i].setRotation(rotation);
            line[i].draw(g);
        }
    }
    
    public void setRotation(double set){
        rotation = set;
    }
    
}
