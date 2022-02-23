import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class TesterMain extends JFrame
{
    public static void main (String [] args){
        ObjectTester panel;
        JFrame theGUI = new JFrame();
        theGUI.setTitle("Object Tester");
        theGUI.setSize(600,600);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new ObjectTester(Color.black);
        Container pane = theGUI.getContentPane();
        pane.add(panel);
        theGUI.setVisible(true);
        
    }
}
