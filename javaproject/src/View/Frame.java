package View;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Frame extends JFrame{
	
	private JLabel item1;
	
	public Frame(){
		super("Title Bar");
		setLayout(new FlowLayout());
		
		item1 = new JLabel("This is a label");
		item1.setToolTipText("Go Forward");
		add(item1);
		
	}
	
}
