
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class ChaosMain extends JFrame
{
    public static void main (String [] args){
        ChaosGame panel;
        JFrame theGUI = new JFrame();
        theGUI.setTitle("3D Chaos");
        theGUI.setSize(600,600);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new ChaosGame(4,4000,Color.white);
        Container pane = theGUI.getContentPane();
        pane.add(panel);
        theGUI.setVisible(true);
        
    }
}
