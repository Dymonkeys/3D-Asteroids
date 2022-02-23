//Note: to cut down on lag figure out what objects are visible and 
//only display those rather than doing that for the panels
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class AsteroidMain
{
    public static void main (String [] args) throws IOException{
        Asteroids panel;
        JFrame theGUI = new JFrame();
        theGUI.setTitle("Asteroids");
        theGUI.setSize(600,600);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Asteroids(Color.black);
        Container pane = theGUI.getContentPane();
        pane.add(panel);
        theGUI.setVisible(true);
        
    }
}
