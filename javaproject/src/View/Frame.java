package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Command.LeftCommand;

public class Frame extends JFrame{
	
	JButton leftb = new JButton("left");
	JButton rightb = new JButton("right");
	JButton saveb = new JButton("save");
	JButton loadb = new JButton("open");
	JButton changeb = new JButton("change");
	
	JFileChooser load = new JFileChooser();
	JFileChooser save = new JFileChooser();
	
	
	
	public Frame(){
		
		Handler e = new Handler();
		leftb.addActionListener(e);
		rightb.addActionListener(e);
		saveb.addActionListener(e);
		loadb.addActionListener(e);
		changeb.addActionListener(e);
		
		
		
		JPanel lbh = new JPanel();
		JPanel rbh = new JPanel();
		JPanel sbh = new JPanel();
		JPanel lobh = new JPanel();
		JPanel cbh = new JPanel();
		
		lbh.add(leftb);
		rbh.add(rightb);
		sbh.add(saveb);
		lobh.add(loadb);
		cbh.add(changeb);
		
		JPanel np = new JPanel();
		np.setLayout(new FlowLayout());
		np.add(sbh);
		np.add(lobh);
		np.add(cbh);
		
		JPanel content = new JPanel();
		
		JPanel pannel = new JPanel();
		pannel.setLayout(new BorderLayout());
		pannel.add(rbh,BorderLayout.EAST);
		pannel.add(lbh,BorderLayout.WEST);
		pannel.add(np,BorderLayout.NORTH);
		pannel.add(content,BorderLayout.CENTER);
		
		
		add(pannel);
	}
	
	private class Handler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == leftb){
				//Go one image backward
			}
			
			if(e.getSource() == rightb){
				//Go one image forward
			}
			
			if(e.getSource() == saveb){
				
			}
			
			if(e.getSource() == loadb){
				
			}
			
			if(e.getSource() == changeb){
				//ChangeMode
			}
			
		}
		
	}
	
}
