
import java.awt.*;
import java.util.Random;
public class UFO
{
    // instance variables - replace the example below with your own
    Universal u = new Universal();
    int x,y,z;
    double scale;
    Panel panel[] =new Panel[42];//42 total
    double rotation;
    Random r = new Random();
    /**
     * Constructor for objects of class UFO
     */
    public UFO(int X,int Y,int Z,double sc)
    {
        scale=sc;
        x=X;y=Y;z=Z;
        //Bottom
        panel[0]=new Panel(
        new int[] {-1,1,2,2,1,-1,-2,-2},
        new int[] {3,3,3,3,3,3,3,3},
        new int[] {-2,-2,-1,1,2,2,1,-1}, 
        u.ShadeColor(65,65,65,0));
        //Bottom Corners
        panel[1]=new Panel(
        new int[] {-5,-2,-1,-2}, 
        new int[] {1,1,3,3},
        new int[] {-2,-5,-2,-1}, 
        u.ShadeColor(65,65,65,0));
        panel[2]=new Panel(
        new int[] {5,2,1,2},
        new int[] {1,1,3,3},
        new int[] {-2,-5,-2,-1}, 
        u.ShadeColor(65,65,65,0));
        panel[3]=new Panel(
        new int[] {-5,-2,-1,-2},
        new int[] {1,1,3,3},
        new int[] {2,5,2,1}, 
        u.ShadeColor(65,65,65,0));
        panel[4]=new Panel(
        new int[] {5,2,1,2},
        new int[] {1,1,3,3},
        new int[] {2,5,2,1},  
        u.ShadeColor(65,65,65,0));
        //bottom Edges
        panel[5]=new Panel(
        new int[] {-5,-5,-2,-2},
        new int[] {1,1,3,3},
        new int[] {2,-2,-1,1}, 
        u.ShadeColor(65,65,65,0));
        panel[6]=new Panel(
        new int[] {5,5,2,2},
        new int[] {1,1,3,3},
        new int[] {2,-2,-1,1}, 
        u.ShadeColor(65,65,65,0));
        panel[7]=new Panel(
        new int[] {2,-2,-1,1},
        new int[] {1,1,3,3},
        new int[] {-5,-5,-2,-2}, 
        u.ShadeColor(65,65,65,0));
        panel[8]=new Panel(
        new int[] {2,-2,-1,1},
        new int[] {1,1,3,3},
        new int[] {5,5,2,2}, 
        u.ShadeColor(65,65,65,0));
                            //Top Rim Corners
        panel[9]=new Panel(
        new int[] {-5,-2,-1,-3},
        new int[] {0,0,-1,-1},
        new int[] {-2,-5,-3,-1}, 
        u.ShadeColor(65,65,65,0));
        panel[10]=new Panel(
        new int[] {5,2,1,3},
        new int[] {0,0,-1,-1},
        new int[] {-2,-5,-3,-1}, 
        u.ShadeColor(65,65,65,0));
        panel[11]=new Panel(
        new int[] {-5,-2,-1,-3},
        new int[] {0,0,-1,-1},
        new int[] {2,5,3,1}, 
        u.ShadeColor(65,65,65,0));
        panel[12]=new Panel(
        new int[] {5,2,1,3},
        new int[] {0,0,-1,-1},
        new int[] {2,5,3,1},  
        u.ShadeColor(65,65,65,0));
                            //top Edges
        panel[13]=new Panel(
        new int[] {-5,-5,-3,-3},
        new int[] {0,0,-1,-1},
        new int[] {2,-2,-1,1}, 
        u.ShadeColor(65,65,65,0));
        panel[14]=new Panel(
        new int[] {5,5,3,3},
        new int[] {0,0,-1,-1},
        new int[] {2,-2,-1,1}, 
        u.ShadeColor(65,65,65,0));
        panel[15]=new Panel(
        new int[] {2,-2,-1,1},
        new int[] {0,0,-1,-1},
        new int[] {-5,-5,-3,-3}, 
        u.ShadeColor(65,65,65,0));
        panel[16]=new Panel(
        new int[] {2,-2,-1,1},
        new int[] {0,0,-1,-1},
        new int[] {5,5,3,3}, 
        u.ShadeColor(65,65,65,0));
                        //Outer Edge corners
        panel[17]=new Panel(
        new int[] {-2,2,2,-2},
        new int[] {0,0,1,1},
        new int[] {-5,-5,-5,-5}, 
        u.ShadeColor(65,65,65,0));
        panel[18]=new Panel(
        new int[] {-2,2,2,-2},
        new int[] {0,0,1,1},
        new int[] {5,5,5,5}, 
        u.ShadeColor(65,65,65,0));
        panel[19]=new Panel(
        new int[] {-5,-5,-5,-5},
        new int[] {0,0,1,1},
        new int[] {-2,2,2,-2},
        u.ShadeColor(65,65,65,0));
        panel[20]=new Panel(
        new int[] {5,5,5,5},
        new int[] {0,0,1,1},
        new int[] {-2,2,2,-2},
        u.ShadeColor(65,65,65,0));
                        //Outer Edge Sides
        panel[21]=new Panel(
        new int[] {-5,-2,-2,-5},
        new int[] {0,0,1,1},
        new int[] {-2,-5,-5,-2},
        u.ShadeColor(65,65,65,0));
        panel[22]=new Panel(
        new int[] {-5,-2,-2,-5},
        new int[] {0,0,1,1},
        new int[] {2,5,5,2},
        u.ShadeColor(65,65,65,0));
        panel[23]=new Panel(
        new int[] {5,2,2,5},
        new int[] {0,0,1,1},
        new int[] {-2,-5,-5,-2},
        u.ShadeColor(65,65,65,0));
        panel[24]=new Panel(
        new int[] {5,2,2,5},
        new int[] {0,0,1,1},
        new int[] {2,5,5,2},
        u.ShadeColor(65,65,65,0));
        
                //Window Sides
        panel[25]=new Panel(
        new int[] {1,-1,-1,1},
        new int[] {-1,-1,-2,-2},
        new int[] {3,3,3,3},
        u.ShadeColor(65,65,65,0));
        panel[26]=new Panel(
        new int[] {1,-1,-1,1},
        new int[] {-1,-1,-2,-2},
        new int[] {-3,-3,-3,-3},
        u.ShadeColor(65,65,65,0));
        panel[27]=new Panel(
        new int[] {3,3,3,3},
        new int[] {-1,-1,-2,-2},
        new int[] {1,-1,-1,1},
        u.ShadeColor(65,65,65,0));
        panel[28]=new Panel(
        new int[] {-3,-3,-3,-3},
        new int[] {-1,-1,-2,-2},
        new int[] {1,-1,-1,1},
        u.ShadeColor(65,65,65,0));
        
        panel[29]=new Panel(
        new int[] {-3,-1,-1,-3},
        new int[] {-1,-1,-2,-2},
        new int[] {-1,-3,-3,-1},
        u.ShadeColor(65,65,65,0));
        panel[30]=new Panel(
        new int[] {-3,-1,-1,-3},
        new int[] {-1,-1,-2,-2},
        new int[] {1,3,3,1},
        u.ShadeColor(65,65,65,0));
        panel[31]=new Panel(
        new int[] {3,1,1,3},
        new int[] {-1,-1,-2,-2},
        new int[] {-1,-3,-3,-1},
        u.ShadeColor(65,65,65,0));
        panel[32]=new Panel(
        new int[] {3,1,1,3},
        new int[] {-1,-1,-2,-2},
        new int[] {1,3,3,1},
        u.ShadeColor(65,65,65,0));
        //Top of the window
        
        panel[33]=new Panel(
        new int[] {-3,-3,-2,-2},
        new int[] {-2,-2,-3,-3},
        new int[] {-1,1,1,-1},
        u.ShadeColor(65,65,65,0));
        panel[34]=new Panel(
        new int[] {3,3,2,2},
        new int[] {-2,-2,-3,-3},
        new int[] {-1,1,1,-1},
        u.ShadeColor(65,65,65,0));
        panel[35]=new Panel(
        new int[] {-1,1,1,-1},
        new int[] {-2,-2,-3,-3},
        new int[] {-3,-3,-2,-2},
        u.ShadeColor(65,65,65,0));
        panel[36]=new Panel(
        new int[] {-1,1,1,-1},
        new int[] {-2,-2,-3,-3},
        new int[] {3,3,2,2},
        u.ShadeColor(65,65,65,0));
        
        panel[37]=new Panel(
        new int[] {-3,-1,-1,-2},
        new int[] {-2,-2,-3,-3},
        new int[] {-1,-3,-2,-1},
        u.ShadeColor(65,65,65,0));
        panel[38]=new Panel(
        new int[] {-3,-1,-1,-2},
        new int[] {-2,-2,-3,-3},
        new int[] {1,3,2,1},
        u.ShadeColor(65,65,65,0));
        panel[39]=new Panel(
        new int[] {3,1,1,2},
        new int[] {-2,-2,-3,-3},
        new int[] {-1,-3,-2,-1},
        u.ShadeColor(65,65,65,0));
        panel[40]=new Panel(
        new int[] {3,1,1,2},
        new int[] {-2,-2,-3,-3},
        new int[] {1,3,2,1},
        u.ShadeColor(65,65,65,0));
        
        //Very Top
        panel[41]=new Panel(
        new int[] {-1,1,2,2,1,-1,-2,-2},
        new int[] {-3,-3,-3,-3,-3,-3,-3,-3},
        new int[] {-2,-2,-1,1,2,2,1,-1}, 
        u.ShadeColor(65,65,65,0));
        
        
    }

    double spinSpeed=Math.PI/300;
    public void fill(Graphics g){
        x+=Math.cos(directionX)*Math.cos(directionY)*speed;
        z+=Math.sin(directionX)*Math.cos(directionY)*speed;
        y+=Math.sin(directionY)*speed;
        
        spin+=spinSpeed;
        u.setRotationX(rotation);
        u.setTranslation(tranX+x,tranY+y,tranZ+z);
        int[] distance = new int[panel.length];
        for(int i=0;i<panel.length;i++){
            distance[i]=(int)u.camDistance(panel[i].getCenterX(),
            panel[i].getCenterY(), panel[i].getCenterZ());
        }
        int[] order=u.arrayOrder(distance);
        int d;
        ///int count=0;
        for(int i=panel.length-1;i>=0;i--){
            d=order[i];
            panel[d].setMultiply(scale/5);
            panel[d].setRotation(rotation);
            panel[d].setTranslation(tranX+x,tranY+y,tranZ+z);
            panel[d].setSpin(spin);
            panel[d].fill(g);
        }
        
        //sets the color of the panels based on cam distance
        for(int i=0;i<panel.length;i++){
            int light=(distance[i]-distance[order[0]])/6-100;
            if(i==0)
            panel[i].setColor(u.ShadeColor(51,204,255,-light*3-100));
            else if(i<25)
            panel[i].setColor(u.ShadeColor(65,65,65,-light));
            else
            panel[i].setColor(u.ShadeColor(51,204,255,-light*3-100));
            
        }
    }
    
    double speed=0;
    public void setSpeed(int set){
        speed=set;
    }
    
    public int getSize(){
        return (int)(scale*10);
    }
    
    double directionX=r.nextDouble()*Math.PI*2;
    double directionY=0;
    public void setDirectionX(double set){
        directionX=set;
    }
    public void setDirectionY(double set){
        directionY=set;
    }
    public void addDirectionX(double set){
        directionX+=set;
    }
    public void addDirectionY(double set){
        directionY+=set;
    }
    
    public void setRotation(double set){
        rotation = set;
    }
    
    public void addSpin(double set){
        spin+=set;
    }
    double spin=0;
    
    public double random(){
        return Math.random();
    }
    
    double tranX=0,tranY=0,tranZ=0;
    public void setTranslation(double Sx, double Sy, double Sz){
        tranX=Sx;
        tranY=Sy;
        tranZ=Sz;
    }
    
    public void changeX(int set){
        x+=set;
    }
    
    public void changeY(int set){
        y+=set;
    }
    
    public void changeZ(int set){
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
}
