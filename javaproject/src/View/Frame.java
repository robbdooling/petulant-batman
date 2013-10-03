package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;



public class Frame extends JFrame {
	
	private JPanel mainLayout;
	private JPanel centerScreen;
	private JMenuItem fourTile;
	private JMenuItem singleTile;
	private BasicArrowButton rightArrow;
	private BasicArrowButton leftArrow;
	private int curMode;
	private ArrayList<String> viewed;
	private ArrayList<String> upcoming;
	private final JFileChooser fc = new JFileChooser();
	
	public Frame()
	{
		
		try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } catch(Exception e) {
		      System.out.println("Error setting native LAF: " + e);
		    }
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainLayout = new JPanel(new BorderLayout(20, 20));
		curMode = 1;
		viewed = new ArrayList<String>();
		upcoming = new ArrayList<String>();
		setSize(900, 600);
		buildMenuBar();
		setResizable(false);
		startUpScreen();
		add(mainLayout);
		setVisible(true);
	}
	
	public void availableStudies()
	{
		JOptionPane studyList = new JOptionPane();
		JFrame test = new JFrame();
		test.setSize(500, 500);
		
		@SuppressWarnings("unchecked")
		JList<Object> listOfStudies = new JList<Object>(upcoming.toArray());
		
		test.add(listOfStudies);
		studyList.add(test);
		
		studyList.setVisible(true);
		
	}
	
	public void buildMenuBar()
	{
		//create the menu bar
		JMenuBar menu = new JMenuBar();
		
		//create menu bar items
		JMenuItem file = new JMenu("File");
		JMenuItem view = new JMenu("View");
		JMenuItem info = new JMenu("Info");
		
		//create file menu items
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save As");
		JMenuItem close = new JMenuItem("Close Study");
		JMenuItem exit = new JMenuItem("Exit");
		
		
		//create view menu items
		fourTile = new JMenuItem("Four Tile Mode");
		singleTile = new JMenuItem("Single Tile Mode");
		
		
		//create info menu items
		JMenuItem about = new JMenuItem("About");
		
		
		// add to the file menu
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.add(close);
		file.add(exit);
		
		//add to the view menu
		view.add(fourTile);
		view.add(singleTile);
		
		//add to the info menu
		info.add(about);
		
		// add action listeners 
		
		// file menu
		open.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//open();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					open(fc.getSelectedFile().getAbsolutePath());
				} 
				availableStudies();
				singleTileMode();
				fillScreen(viewed);
			}
			
		});
		
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		saveAs.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				startUpScreen();
				
			}
			
		});
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
				
			}
			
		});
		
		// view menu
		fourTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				curMode = 4;
				fourTileMode();
				
			}
			
		});
		
		singleTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				curMode = 1;
				singleTileMode();
				
			}
			
		});
		
		// info menu
		about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//add to the menu bar
		menu.add(file);
		menu.add(view);
		menu.add(info);
		
		//set the menu bar for the frame
		this.setJMenuBar(menu);
	}
	
	public void startUpScreen()
	{
		mainLayout.removeAll();
		
		// layouts for the startup screen which is empty
		centerScreen = new JPanel(new GridLayout(1,1));
//		JPanel topScreen = new JPanel(new FlowLayout());
		JPanel leftScreen = new JPanel(new GridLayout(3,1));
		JPanel rightScreen = new JPanel(new GridLayout(3,1));
		JPanel bottomScreen = new JPanel(new FlowLayout());
		
		
//		// buttons for startup screen
//		JButton openStudy = new JButton("Open Study");
//		JButton fourTile = new JButton("Four Tile Mode");
//		JButton closeStudy = new JButton("Close Study");
//		JButton save = new JButton("Save");
//		JButton saveAs	= new JButton("Save As");
		
		// arrow Buttons
		BasicArrowButton rightArrow = new BasicArrowButton(BasicArrowButton.EAST);
		BasicArrowButton leftArrow = new BasicArrowButton(BasicArrowButton.WEST);
		
//		// action listeners 
//		fourTile.addActionListener(new ActionListener()
//		{
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fourTileMode();
//				
//			}
//			
//		});
		
		//left and right button padding
		Border leftPadding = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		leftScreen.setBorder(leftPadding);
		Border rightPadding = BorderFactory.createEmptyBorder(0, 0, 0, 5);
		rightScreen.setBorder(rightPadding);
		
		// enable left and right buttons
		rightArrow.setEnabled(isEnabled(0));
		leftArrow.setEnabled(isEnabled(1));
		
		// add to layouts
//		topScreen.add(openStudy);
//		topScreen.add(save);
//		topScreen.add(saveAs);
//		topScreen.add(fourTile);
//		topScreen.add(closeStudy);
		
		
	
		leftScreen.add(new JLabel(""));
		leftScreen.add(leftArrow);
		
		rightScreen.add(new JLabel(""));
		rightScreen.add(rightArrow);

		
//		mainLayout.add(topScreen, BorderLayout.NORTH);
		mainLayout.add(centerScreen, BorderLayout.CENTER);
		mainLayout.add(bottomScreen, BorderLayout.SOUTH);
		mainLayout.add(rightScreen, BorderLayout.EAST);
		mainLayout.add(leftScreen, BorderLayout.WEST);
		
		//disable menu item
		fourTile.setEnabled(false);
		singleTile.setEnabled(false);
		
		SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	public void singleTileMode()
	{
		mainLayout.removeAll();
		
		// layouts for the startup screen which is empty
		centerScreen = new JPanel(new GridLayout(1,1));
//		JPanel topScreen = new JPanel(new FlowLayout());
		JPanel leftScreen = new JPanel(new GridLayout(3,1));
		JPanel rightScreen = new JPanel(new GridLayout(3,1));
		JPanel bottomScreen = new JPanel(new FlowLayout());
		
		
//		// buttons for startup screen
//		JButton openStudy = new JButton("Open Study");
//		JButton fourTile = new JButton("Four Tile Mode");
//		JButton closeStudy = new JButton("Close Study");
//		JButton save = new JButton("Save");
//		JButton saveAs	= new JButton("Save As");
		
		// arrow Buttons
		rightArrow = new BasicArrowButton(BasicArrowButton.EAST);
		leftArrow = new BasicArrowButton(BasicArrowButton.WEST);
		
		// action listeners 
		rightArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fillScreen(right());
				rightArrow.setEnabled(isEnabled(0));
				leftArrow.setEnabled(isEnabled(1));
			}
			
		});
		
		leftArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fillScreen(left());
				rightArrow.setEnabled(isEnabled(0));
				leftArrow.setEnabled(isEnabled(1));
			}
			
		});
//		fourTile.addActionListener(new ActionListener()
//		{
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fourTileMode();
//				
//			}
//			
//		});
		
		//left and right button padding
		Border leftPadding = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		leftScreen.setBorder(leftPadding);
		Border rightPadding = BorderFactory.createEmptyBorder(0, 0, 0, 5);
		rightScreen.setBorder(rightPadding);
		
		
		
		// enable left and right buttons
		rightArrow.setEnabled(isEnabled(0));
		leftArrow.setEnabled(isEnabled(1));
		
		
		// add to layouts
//		topScreen.add(openStudy);
//		topScreen.add(save);
//		topScreen.add(saveAs);
//		topScreen.add(fourTile);
//		topScreen.add(closeStudy);
		
		
		
		leftScreen.add(new JLabel(""));
		leftScreen.add(leftArrow);
		
		rightScreen.add(new JLabel(""));
		rightScreen.add(rightArrow);

		
//		mainLayout.add(topScreen, BorderLayout.NORTH);
		mainLayout.add(centerScreen, BorderLayout.CENTER);
		mainLayout.add(bottomScreen, BorderLayout.SOUTH);
		mainLayout.add(rightScreen, BorderLayout.EAST);
		mainLayout.add(leftScreen, BorderLayout.WEST);
		
		//disable menu item
		fourTile.setEnabled(true);
		singleTile.setEnabled(false);
		
		SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	
	
	public void fourTileMode()
	{
		mainLayout.removeAll();
		
		// layouts for the startup screen which is empty
		centerScreen = new JPanel(new GridLayout(2,2));
//		JPanel topScreen = new JPanel(new FlowLayout());
		JPanel leftScreen = new JPanel(new GridLayout(3,1));
		JPanel rightScreen = new JPanel(new GridLayout(3,1));
		JPanel bottomScreen = new JPanel(new FlowLayout());
		
		
//		// buttons for startup screen
//		JButton openStudy = new JButton("Open Study");
//		JButton fourTile = new JButton("Four Tile Mode");
//		JButton closeStudy = new JButton("Close Study");
//		JButton save = new JButton("Save");
//		JButton saveAs	= new JButton("Save As");
		
		// arrow Buttons
		BasicArrowButton rightArrow = new BasicArrowButton(BasicArrowButton.EAST);
		BasicArrowButton leftArrow = new BasicArrowButton(BasicArrowButton.WEST);
		
//		// action listeners 
//		fourTile.addActionListener(new ActionListener()
//		{
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fourTileMode();
//				
//			}
//			
//		});
		
		//left and right button padding
		Border leftPadding = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		leftScreen.setBorder(leftPadding);
		Border rightPadding = BorderFactory.createEmptyBorder(0, 0, 0, 5);
		rightScreen.setBorder(rightPadding);
		
		// enable left and right buttons
		rightArrow.setEnabled(isEnabled(0));
		leftArrow.setEnabled(isEnabled(1));
		
		// add to layouts
//		topScreen.add(openStudy);
//		topScreen.add(save);
//		topScreen.add(saveAs);
//		topScreen.add(fourTile);
//		topScreen.add(closeStudy);
		
		
	
		leftScreen.add(new JLabel(""));
		leftScreen.add(leftArrow);
		
		rightScreen.add(new JLabel(""));
		rightScreen.add(rightArrow);

		
//		mainLayout.add(topScreen, BorderLayout.NORTH);
		mainLayout.add(centerScreen, BorderLayout.CENTER);
		mainLayout.add(bottomScreen, BorderLayout.SOUTH);
		mainLayout.add(rightScreen, BorderLayout.EAST);
		mainLayout.add(leftScreen, BorderLayout.WEST);
		
		//disable menu button
		fourTile.setEnabled(false);
		singleTile.setEnabled(true);
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void addToScreen()
	{
		
	}
	
	public void open(String directory)
	{
		viewed = new ArrayList<String>();
		upcoming = new ArrayList<String>();
		
		File dir = new File(directory);
		int curFile = 0;
		
		for (File child : dir.listFiles())
		{
		    if(child.getName().toLowerCase().endsWith(".jpg"))
		    {
		    	if(curFile == 0)
		    	{
		    		viewed.add(child.getAbsolutePath());
		    	}
		    	else
		    	{
		    		upcoming.add(child.getAbsolutePath());
		    	}
		    	++curFile;
		    }
		}
	}
	
	public ArrayList<String> right()
	{
		ArrayList<String> tempArray = new ArrayList<String>();
		
		if(curMode > upcoming.size())
		{
			int size = upcoming.size();
			for(int imagePaths = 0; imagePaths < size; ++imagePaths)
			{
				String curPath = upcoming.get(0);
				tempArray.add(curPath);
				viewed.add(0, curPath);
				upcoming.remove(0);
			}
		}
		else
		{
			for(int images = 0; images < curMode; ++images)
			{
				String curPath = upcoming.get(0);
				tempArray.add(curPath);
				viewed.add(0, curPath);
				upcoming.remove(0);
			}
		}
		return tempArray;
	}
	
	public ArrayList<String> left()
	{
		ArrayList<String> tempArray = new ArrayList<String>();
		
		if(curMode > viewed.size())
		{
			int size = viewed.size();
			for(int imagePaths = 0; imagePaths < size; ++imagePaths)
			{
				String curPath = viewed.get(0);
				tempArray.add(curPath);
				upcoming.add(0, curPath);
				viewed.remove(0);
			}
		}
		else
		{
			for(int images = 0; images < curMode; ++images)
			{
				String curPath = viewed.get(0);
				tempArray.add(curPath);
				upcoming.add(0, curPath);
				viewed.remove(0);
			}
		}
		return tempArray;
	}
	
	
	
	public JButton openImage(String filePath)
	{
		JButton icon = new JButton(new ImageIcon(filePath));
		
		return icon;
	}
	
	public void fillScreen(ArrayList<String> images)
	{
		centerScreen.removeAll();
		for(String paths: images)
		{
			centerScreen.add(openImage(paths));
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public boolean isEnabled(int flag)
	{
		boolean result = true;
		
		if(flag == 0)
		{
			if(upcoming.size() < 1)
			{
				result = false;
			}
		}
		else
		{
			if(viewed.size() < 2)
			{
				result = false;
			}
		}
		
		return result;
	}
	
	public static void main(String [] args)
	{
		Frame test = new Frame();
	}

}
