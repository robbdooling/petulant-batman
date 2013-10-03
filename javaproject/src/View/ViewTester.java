package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class ViewTester {

	
	public static JFileChooser saveChooser(){
		JFrame a = new JFrame();
		JFileChooser b = new JFileChooser();
		
		return b;
	}
	
	public static JFileChooser openChooser(){
		JFrame a = new JFrame();
		JFileChooser b = new JFileChooser();
		
		return b;
	}
	
	/**	public void actionPerformed(ActionEvent e) {
		 
        //Handle open button action.
        if (e.getSource() == openb) {
            int returnVal = fc.showOpenDialog(FileChooserDemo.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
            } else {
                //log.append("Open command cancelled by user." + newline);
            }
            //log.setCaretPosition(log.getDocument().getLength());
 
        //Handle save button action.
        } else if (e.getSource() == saveb) {
            int returnVal = fc.showSaveDialog(FileChooserDemo.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                //log.append("Save command cancelled by user." + newline);
            }
            //log.setCaretPosition(log.getDocument().getLength());
        }
    }*/
	
	public static void main(String[] args) {
		
		Frame pgm = new Frame();
		pgm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pgm.setSize(960,720);
		pgm.setVisible(true);
		
	}

}
