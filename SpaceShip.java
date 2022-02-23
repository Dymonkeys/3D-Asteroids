
/**
 * Write a description of class SpaceShip here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
public class SpaceShip
{
    
    Universal u = new Universal();
    Panel[] panel = new Panel[30];
    double rotation=3*Math.PI/2;
    double rotationY=0;
    
    //int[] O4 = new int[4];
    //int[] O3 = new int[3];
    /**
     * Constructor for objects of class SpaceShip
     */
    public SpaceShip()
    {
        //u.setTranZ(300);
        
        //wind Shield
        panel[0]=new Panel(
        new int[] {460,340,340},
        new int[] {400,360,400},
        new int[] {280,280,240}, ShadeColor(0,0,0,40));
        panel[1]=new Panel(
        new int[] {460,460,340,340},
        new int[] {400,400,360,360},
        new int[] {280,320,320,280}, Color.DARK_GRAY);
        panel[2]=new Panel(
        new int[] {460,340,340},
        new int[] {400,400,360},
        new int[] {320,360,320}, ShadeColor(0,0,0,80));
        //top
        panel[3]=new Panel(
        new int[] {340,340,180,180},
        new int[] {400,360,360,400},
        new int[] {240,280,280,240}, ShadeColor(255,0,0,-50));
        //new Color(205, 0, 0));
        panel[4]=new Panel(
        new int[] {340,340,180,180},
        new int[] {360,360,360,360},
        new int[] {280,320,320,280}, ShadeColor(255,0,0,0));
        //new Color(255, 0, 0));
        panel[5]=new Panel(
        new int[] {340,340,180,180},
        new int[] {360,400,400,360},
        new int[] {320,360,360,320}, ShadeColor(255,0,0,50));
        //new Color(255, 50, 50));
        //left wing
        panel[6]=new Panel(
        new int[] {340,300-20,180},
        new int[] {400,400,400},
        new int[] {240,160,240}, ShadeColor(255,0,0,20));
        panel[7]=new Panel(
        new int[] {300-20,140,180},
        new int[] {400,400,400},
        new int[] {160,80,240}, ShadeColor(255,0,0,20));
        //right wing
        panel[8]=new Panel(
        new int[] {340,300-20,180},
        new int[] {400,400,400},
        new int[] {360,440,360}, ShadeColor(255,0,0,10));
        panel[9]=new Panel(
        new int[] {300-20,140,180},
        new int[] {400,400,400},
        new int[] {440,520,360}, ShadeColor(255,0,0,10));
        //side walls
        panel[10]=new Panel(
        new int[] {340,180,180,340},
        new int[] {400,400,440,440},
        new int[] {240,240,240,240}, ShadeColor(255,0,0,-100));
        panel[11]=new Panel(
        new int[] {340,180,180,340},
        new int[] {400,400,440,440},
        new int[] {360,360,360,360}, ShadeColor(255,0,0,100));
        //front walls
        panel[12]=new Panel(
        new int[] {340,460,460-0,340},
        new int[] {400,400,420,440},
        new int[] {240,280,280,240}, ShadeColor(255,0,0,-50));
        panel[13]=new Panel(
        new int[] {460,460,460-0,460-0},
        new int[] {400,400,420,420},
        new int[] {280,320,320,280}, ShadeColor(255,0,0,0));
        panel[14]=new Panel(
        new int[] {340,460,460-0,340},
        new int[] {400,400,420,440},
        new int[] {360,320,320,360}, ShadeColor(255,0,0,50));
        //base front
        panel[15]=new Panel(
        new int[] {340,460,340},
        new int[] {440,420,460},
        new int[] {240,280,280}, ShadeColor(255,0,0,-25));
        panel[16]=new Panel(
        new int[] {460,460,340,340},
        new int[] {420,420,460,460},
        new int[] {280,320,320,280}, ShadeColor(255,0,0,0));
        panel[17]=new Panel(
        new int[] {340,460,340},
        new int[] {440,420,460},
        new int[] {360,320,320}, ShadeColor(255,0,0,25));
        // //botom
        panel[18]=new Panel(
        new int[] {340,340,180,180},
        new int[] {440,460,460,440},
        new int[] {240,280,280,240}, new Color(205, 0, 0));
        panel[19]=new Panel(
        new int[] {340,340,180,180},
        new int[] {460,460,460,460},
        new int[] {280,320,320,280}, new Color(255, 0, 0));
        panel[20]=new Panel(
        new int[] {340,340,180,180},
        new int[] {460,440,440,460},
        new int[] {320,360,360,320}, new Color(255, 50, 50));
        panel[21]=new Panel(
        //Thruster
        new int[] {180,180,140,140},
        new int[] {360,360,380,380},
        new int[] {280,320,310,290}, ShadeColor(65,65,65,0));
        panel[22]=new Panel(
        new int[] {180,180,140,140},
        new int[] {360,400,400,380},
        new int[] {320,360,330,310}, ShadeColor(65,65,65,-10));
        panel[23]=new Panel(
        new int[] {180,180,140,140},
        new int[] {400,440,420,400},
        new int[] {360,360,330,330}, ShadeColor(65,65,65,-20));
        panel[24]=new Panel(
        new int[] {180,180,140,140},
        new int[] {440,460,440,420},
        new int[] {360,320,310,330}, ShadeColor(65,65,65,-10));
        panel[25]=new Panel(
        new int[] {180,180,140,140},
        new int[] {460,460,440,440},
        new int[] {320,280,290,310}, ShadeColor(65,65,65,0));
        panel[26]=new Panel(
        new int[] {180,180,140,140},
        new int[] {460,440,420,440},
        new int[] {280,240,270,290}, ShadeColor(65,65,65,10));
        panel[27]=new Panel(
        new int[] {180,180,140,140},
        new int[] {440,400,400,420},
        new int[] {240,240,270,270}, ShadeColor(65,65,65,20));
        panel[28]=new Panel(
        new int[] {180,180,140,140},
        new int[] {400,360,380,400},
        new int[] {240,280,290,270}, ShadeColor(65,65,65,10));
        panel[29]=new Panel(
        new int[] {140,140,140,140,140,140,140,140},
        new int[] {380,400,420,440,440,420,400,380},
        new int[] {310,330,330,310,290,270,270,290}, Color.cyan);
        
        
        
    }
    
    public Color ShadeColor(int red, int green, int blue,int light){
        red+=light;
        green+=light;
        blue+=light;
        if(red>255) red=255;
        if(green>255) green=255;
        if(blue>255) blue=255;
        if(red<=0) red=0;
        if(green<0) green=0;
        if(blue<0) blue=0;
        return(new Color(red, green, blue));
    }

    double freeLook=0;
    public void fill(Graphics g){
        u.setRotationX(rotation+freeLook);
        //u.setRotationY(rotationY);
        int[] distance = new int[panel.length];
        for(int i=0;i<panel.length;i++){
            distance[i]=(int)u.camDistance(panel[i].getCenterX(),
            panel[i].getCenterY(), panel[i].getCenterZ());
            //distance[i]=panel[i].camDistance();
        }
        int[] order=u.arrayOrder(distance);
        int d;
            int count=0;
        for(int i=panel.length-1;i>=0;i--){
            d=order[i];
            //if(d==4&&centerY>u.getMidY()) d=1;
            panel[d].setRotation(rotation+freeLook);
            panel[d].fill(g);
                count+=order[i];
        }
    }
    
    public void setRotation(double set){
        rotation = set;
    }
    public void setRotationY(double set){
        rotationY = set;
    }
    public void changeRotation(double set){
        rotation += set;
    }
    
    public void changeRotationY(double set){
        rotationY += set;
    }
    public double getRotation(){
        return rotation;
    }
    public double getRotationY(){
        return rotationY;
    }
    public void setFreeLook(double set){
        freeLook=set;
    }
    
    public void setZoom(double set){
        for(int i=0;i<panel.length;i++){
            panel[i].setZoom(set);
        }
    }
}
