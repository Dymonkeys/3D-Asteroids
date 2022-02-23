
/**
 * Its a tetrahedron, what more do you want
 *
 * David Neufeld
 * 2/16/19
 */
import java.awt.*;
public class Tetrahedron
{
    Universal u = new Universal();
    int[] x;
    int[] y;
    int[] z;
    Panel[] p = new Panel[4];
    Line[] line = new Line[6];
    int[][] px = new int[4][3];
    int[][] py = new int[4][3];
    int[][] pz = new int[4][3];
    double rotation;
    public Tetrahedron(int[] X, int[] Y, int[] Z)
    {
        x=X;
        y=Y;
        z=Z;
        int a=0; int b=1; int c=2;
        Color color;
        for(int i=0;i<4;i++){
            if(i==0) color=Color.red;
            else if(i==1) color=Color.blue;
            else if(i==2) color=Color.green;
            else if(i==3) color=Color.orange;
            else color=Color.black;
            //System.out.println("a: "+a+" b: "+b+" c: "+c);
            px[i][0]=x[a]; py[i][0]=y[a]; pz[i][0]=z[a];
            px[i][1]=x[b]; py[i][1]=y[b]; pz[i][1]=z[b];
            px[i][2]=x[c]; py[i][2]=y[c]; pz[i][2]=z[c];
            p[i]=new Panel(px[i],py[i],pz[i],color);
            a++; b++; c++;
            if (a>3) a=0;
            if (b>3) b=0;
            if (c>3) c=0;
        }
        a=0;b=1;
        for(int i=0;i<4;i++){
            line[i]=new Line(x[a],y[a],z[a],x[b],y[b],z[b],Color.black);
            a++; b++;
            if (a>3) a=0;
            if (b>3) b=0;
        }
        line[4]=new Line(x[0],y[0],z[0],x[2],y[2],z[2],Color.black);
        line[5]=new Line(x[1],y[1],z[1],x[3],y[3],z[3],Color.black);
    }

    public void fill(Graphics g){
        
        int[] distance=new int[4];
        for(int i=0;i<4;i++){
            distance[i]=u.rotateZ(p[i].getCenterX(),p[i].getCenterZ(),
                    rotation);
        }
        int[] order = u.arrayOrder(distance);
        
        for(int i=3;i>=0;i--){
            int d=order[i];
            p[d].setRotation(rotation);
            p[d].fill(g);
        }
    }
    
    public void draw(Graphics g){
        for(int i=0;i<6;i++){
            line[i].setRotation(rotation);
            line[i].draw(g);
        }
    }
    
    public void setRotation(double set){
        rotation = set;
    }
}
