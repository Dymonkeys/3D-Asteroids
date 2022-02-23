
/**
 * Manuver a space ship in 3d space
 *
 * David Neufeld
 * 3/1/19
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.*;
import java.awt.event.*;
public class SpaceFlight extends JPanel implements KeyListener
{
    Universal u = new Universal();
    
    SpaceShip ship = new SpaceShip();
    javax.swing.Timer timer;
    double rotation=0;
    double x,y,z;
    double speedX;
    double speedZ;
    double speedY;
    double speed=40;
    double direction;
    int grid = 8;
    Meteor meteor[] = new Meteor[grid*grid];
    sierpinski s = new sierpinski(0,400,0,700*8,4000);
    /**
     * Constructor for objects of class SpaceFlight
     */
    public SpaceFlight(Color backColor)
    {
        // initialise instance variables
        setBackground(backColor);
        Random r = new Random();
        this.addKeyListener(this);
        this.setFocusable(true);
        int count=0;
        for(int i=0;i<grid;i++){
            for(int h=0;h<grid;h++){
                meteor[count]=new Meteor(i*700*2,600+r.nextInt(3*1200),h*700*2,200);
                count++;
            }
            
        }
        timer = new javax.swing.Timer(5,new MoveListener());
    }
    
    double freeLook=0;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        direction=-rotation-Math.PI/2;
        if(keyD){
            if(ship.getRotation()>Math.PI/3+Math.PI)
            ship.changeRotation(-1*Math.PI/300);
            rotation+=Math.PI/300;
        } else if(keyA){
            if(ship.getRotation()<2*Math.PI/3+Math.PI)
            ship.changeRotation(Math.PI/300);
            rotation-=Math.PI/300;
        } else {
            if(ship.getRotation()>Math.PI/2+Math.PI){
                ship.changeRotation(-Math.PI/300);
            } else if(ship.getRotation()<Math.PI/2+Math.PI){
                ship.changeRotation(Math.PI/300);
            }
        }
        if(keyW&&Math.sqrt(speedX*speedX+speedZ*speedZ)<60) {
            //x+=Math.cos(direction)*speed;
            //z+=Math.sin(direction)*speed;
            speedX+=Math.cos(direction);
            speedZ+=Math.sin(direction);
        }
        else if(keyS&&Math.sqrt(speedX*speedX+speedZ*speedZ)<60) {
            //x+=Math.cos(direction+Math.PI)*speed/2;
            //z+=Math.sin(direction+Math.PI)*speed/2;
            speedX-=Math.cos(direction+freeLook);
            speedZ-=Math.sin(direction+freeLook);
        }
        if(keySpace&&speedY>-30){
            //y-=speed/2;
            speedY-=0.8;
        } else if(keyShift&&speedY<30){
            //y+=speed/2;
            speedY+=0.8;
        }
        if(keyQ) freeLook-=Math.PI/300;
        else if(keyE) freeLook+=Math.PI/300;
        x+=speedX;
        z+=speedZ;
        y+=speedY;
        speedX-=speedX/100;
        speedZ-=speedZ/100;
        speedY-=speedY/100;
        
            //ship.changeRotation(Math.PI/30);
        // for(int i=0;i<6;i++){
            // meteor[i].setRotation(rotation+freeLook);
            // meteor[i].setTranslation(-x,-y,-z);
            // meteor[i].fill(g);
        // }
        s.setRotation(rotation+freeLook);
        s.setTranslation(-x,-y,-z);
        s.fill(g);
        
        //fillMeteors(g);
        ship.setFreeLook(freeLook);
        ship.fill(g);
        timer.start();
    }
    
    public void fillMeteors(Graphics g){
        u.setRotationX(rotation);
        u.setTranslation(-x,-y,-z);
        //u.setTranslation(x,y,z);
        int[] distance = new int[meteor.length];
        for(int i=0;i<meteor.length;i++){
            //System.out.println(i);
            distance[i]=(int)u.camDistance(meteor[i].getX(),
            meteor[i].getY(), meteor[i].getZ());
        }
        int[] order=u.arrayOrder(distance);
        int d;
        for(int i=meteor.length-1;i>=0;i--){
            d=order[i];
            meteor[d].setRotation(rotation+freeLook);
            meteor[d].setTranslation(-x,-y,-z);
            meteor[d].fill(g);
            
        }
    }
    
    private class MoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            repaint();
        }
    }
    
    boolean keyW=false; boolean keyS=false; 
    boolean keyA=false; boolean keyD=false; 
    boolean keySpace=false; boolean keyShift=false; 
    boolean keyQ=false; boolean keyE=false;
    public void keyTyped(KeyEvent keyt) {}
    
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT : keyA = true; break;
            case KeyEvent.VK_RIGHT : keyD = true; break;
            case KeyEvent.VK_UP : keyW = true; break;
            case KeyEvent.VK_DOWN : keyS = true; break;
            case KeyEvent.VK_B : freeLook = Math.PI; break;
            case KeyEvent.VK_SPACE : keySpace = true; break;
            case KeyEvent.VK_SHIFT : keyShift = true; break;
            case KeyEvent.VK_Q : keyQ = true; break;
            case KeyEvent.VK_E : keyE = true; break;
        }
        
        repaint();
    }
    
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT : keyA = false; break;
            case KeyEvent.VK_RIGHT : keyD = false; break;
            case KeyEvent.VK_UP : keyW = false; break;
            case KeyEvent.VK_DOWN : keyS = false; break;
            case KeyEvent.VK_B : freeLook = 0; break;
            case KeyEvent.VK_SPACE : keySpace = false; break;
            case KeyEvent.VK_SHIFT : keyShift = false; break;
            case KeyEvent.VK_Q : keyQ = false; break;
            case KeyEvent.VK_E : keyE = false; break;
        }
        
        repaint();
    }
}
