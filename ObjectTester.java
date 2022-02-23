
/**
 * Tests Objects in a soinning plain
 *
 * David Neufeld
 * 2/16/19
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
public class ObjectTester extends JPanel
{
    Universal u = new Universal();
    Random r = new Random();
    Scanner reader = new Scanner(System.in);

    double rotation=0;
    Box cube = new Box(200-100,200-100,200-100,400-100,400-100,400-100);
    Box box = new Box(350,400,300,400,550,400);
    Panel hypno;
    Tetrahedron tet;
    Ball[] b = new Ball[20];
    SpaceShip ship = new SpaceShip();
    Meteor meteor = new Meteor(300,150,300,100);
    UFO ufo=new UFO(300,300,300,200);
    public ObjectTester(Color backColor)
    {
        setBackground(backColor);
        int[] px = new int[8];
        int[] py = new int[8];
        int[] pz = new int[8];
        px[6]=200; py[0]=200; pz[0]=200;
        px[7]=400; py[1]=200; pz[1]=200;
        px[2]=400; py[2]=400; pz[2]=200;
        px[3]=200; py[3]=400; pz[3]=200;
        px[4]=200; py[4]=200; pz[4]=400;
        px[5]=400; py[5]=200; pz[5]=400;
        px[0]=400; py[6]=400; pz[6]=400;
        px[1]=200; py[7]=400; pz[7]=400;

        hypno=new Panel(px,py,pz,Color.white);

        int[] tx = new int[4];
        int[] ty = new int[4];
        int[] tz = new int[4];
        tx[0]=488;ty[0]=400;tz[0]=232;
        tx[1]=147;ty[1]=400;tz[1]=171;
        tx[2]=265;ty[2]=400;tz[2]=497;
        tx[3]=300;ty[3]=0;tz[3]=300;

        tet = new Tetrahedron(tx,ty,tz);

        for(int i=0;i<b.length;i++){
            b[i]=new Ball(r.nextInt(600),r.nextInt(600),r.nextInt(600),
                r.nextInt(6)+3,u.randomColor());
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //p.setRotation(rotation);
        //p.fill(g);
        cube.setRotation(rotation);
        cube.fill(g);
        box.setRotation(rotation);
        box.fill(g);
        tet.setRotation(rotation);
        //tet.fill(g);
        
        
        int[] distance = new int[b.length];
        for(int i=0;i<b.length;i++){
            distance[i]=u.rotateZ(b[i].getX(),b[i].getZ(),rotation);
        }
        int[] order = u.arrayOrder(distance);
        // for(int i=0;i<b.length;i++){
        // b[i].setRotation(rotation);
        // b[i].fill(g);
        // }
        //System.out.println();
        for(int i=0;i<b.length;i++){
            int d = order[b.length-i-1];
            //b[d].setRotation(rotation);
            //b[d].fill(g);
            //System.out.println(distance[i]+" "+d);
            
        }
        hypno.setRotation(rotation);
        hypno.fill(g);
        
        //meteor.setRotation(rotation);
        //meteor.fill(g);
        
        ship.setRotation(rotation);
        //ship.fill(g);
        ufo.setRotation(rotation);
        //ufo.fill(g);
        // try{
           // Thread.sleep(60);
        // }catch(InterruptedException ex){}
        rotation+=Math.PI/3000;
        repaint();
    }

}
