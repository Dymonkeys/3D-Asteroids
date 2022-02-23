/**
 * ColorPanel for 3d space
 *
 * David Neufeld
 * 2/2/19
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class spaceColorPanel extends JPanel implements KeyListener
{
    Ball b;
    Line[] line = new Line[12];
    //Panel panel;
    Box cube = new Box(200,200,200,400,400,400);
    
    int speed=10;
    javax.swing.Timer timer;
    public spaceColorPanel (Color backColor){
        setBackground(backColor);
        b = new Ball(300,300,300,20,Color.blue);
        line[0] = new Line(000,600,800,600,600,800,Color.red);
        line[1] = new Line(000,600,800,000,000,800,Color.green);
        line[2] = new Line(000,600,800,000,600,250,Color.blue);
        
        line[3] = new Line(000,000,800,600,000,800,Color.red);
        line[4] = new Line(600,600,800,600,000,800,Color.green);
        line[5] = new Line(600,600,800,600,600,250,Color.blue);
        
        line[6] = new Line(000,600,250,600,600,250,Color.red);
        line[7] = new Line(000,600,250,000,000,250,Color.green);
        line[8] = new Line(000,000,800,000,000,250,Color.blue);
        
        line[9] = new Line(000,000,250,600,000,250,Color.red);
        line[10] = new Line(600,600,250,600,000,250,Color.green);
        line[11] = new Line(600,000,800,600,000,250,Color.blue);
        
        int[] px = new int[4];
        int[] py = new int[4];
        int[] pz = new int[4];
        px[0]=200; py[0]=100; pz[0]=200;
        px[1]=400; py[1]=100; pz[1]=200;
        px[2]=400; py[2]=100; pz[2]=400;
        px[3]=200; py[3]=100; pz[3]=400;
        //panel = new Panel(px, py, pz, Color.pink);
        
        this.addKeyListener(this);
        this.setFocusable(true);
        
        timer = new javax.swing.Timer(5,new MoveListener());
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //move in direction of key press
        b.changeX(changeX*speed);
        b.changeY(changeY*speed);
        b.changeZ(changeZ*speed);
        
        //panel.fill(g);
        //cube.fill(g);
        //draw all lines
        for (int i=0;i<line.length;i++){
            line[i].draw(g);
        }
        b.fill(g);
        
        //Wall Colisions
        if (b.getX()+b.getRadius()>600) b.setX(600-b.getRadius());
        else if(b.getX()-b.getRadius()<0) b.setX(b.getRadius());
        if (b.getY()+b.getRadius()>600) b.setY(600-b.getRadius());
        else if(b.getY()-b.getRadius()<0) b.setY(b.getRadius());
        if (b.getZ()+b.getRadius()>800) b.setZ(800-b.getRadius());
        else if(b.getZ()-b.getRadius()*2<250) b.setZ(250+b.getRadius()*2);
        g.drawString(Integer.toString(changeX),10,30);
        g.drawString(Integer.toString(changeY),10,50);
        g.drawString(Integer.toString(changeZ),10,70);
        timer.start();
        //repaint();
    }
    
    
    public void keyTyped(KeyEvent keyt) {}
    
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A : changeX = -1; break;
            case KeyEvent.VK_D : changeX = 1; break;
            case KeyEvent.VK_W : changeZ = 1; break;
            case KeyEvent.VK_S : changeZ = -1; break;
            case KeyEvent.VK_SPACE : changeY = -1; break;
            case KeyEvent.VK_SHIFT : changeY = 1; break;
        }
        
        repaint();
    }
    
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A : changeX = 0; break;
            case KeyEvent.VK_D : changeX = 0; break;
            case KeyEvent.VK_W : changeZ = 0; break;
            case KeyEvent.VK_S : changeZ = 0; break;
            case KeyEvent.VK_SPACE : changeY = 0; break;
            case KeyEvent.VK_SHIFT : changeY = 0; break;
        }
        
        repaint();
    }
    
    private class MoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            repaint();
        }
    }
    int changeX = 0;
    int changeY = 0;
    int changeZ = 0;
    public void setChangeX(int set){
        changeX = set;
    }
    public void setChangeY(int set){
        changeY = set;
    }
    public void setChangeZ(int set){
        changeZ = set;
    }
}