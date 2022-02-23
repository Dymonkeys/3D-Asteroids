
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class spaceMain
{
    public static void main (String [] args){
        spaceColorPanel panel;
        JFrame theGUI = new JFrame();
        theGUI.setTitle("3D Space");
        theGUI.setSize(600,600);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new spaceColorPanel(Color.white);
        Container pane = theGUI.getContentPane();
        pane.add(panel);
        theGUI.setVisible(true);
        
    }
}
