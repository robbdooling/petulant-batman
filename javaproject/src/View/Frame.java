package View;

import Command.ChangeStateCommand;
import Command.LeftCommand;
import Command.RightCommand;
import Command.SaveStudyCommand;
import Director.Director;
import State.StateHolder;
import Study.NoValidStudiesFoundException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Style;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;



public class Frame extends JFrame {
	
	private JPanel mainLayout;
	private JPanel centerScreen;
	private JMenuItem fourTile;
	private JMenuItem singleTile;
	private BasicArrowButton rightArrow;
	private BasicArrowButton leftArrow;
	private int curMode;
	//private ArrayList<String> viewed;
	//private ArrayList<String> upcoming;
	private List<String> images;
	private final JFileChooser fc = new JFileChooser();
	
	private LeftCommand left;
	private RightCommand right;
	private ChangeStateCommand changeState;
	private SaveStudyCommand saveStudy;
	private JList<Object> listOfStudies = null;
	
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
	//	viewed = new ArrayList<String>();
	//	upcoming = new ArrayList<String>();
		setSize(900, 600);
		buildMenuBar();
		setResizable(false);
		startUpScreen();
		
		
		left = new LeftCommand();
		right = new RightCommand();
		changeState = new ChangeStateCommand();
		saveStudy = new SaveStudyCommand();
		
		add(mainLayout);
		setVisible(true);
	}
	
	public void availableStudies()
	{
		
		final JFrame test = new JFrame();
		test.setSize(500, 500);
		test.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try {
			listOfStudies = new JList<Object>(Director.getAvailStudies().toArray());
		} catch (NoValidStudiesFoundException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.err.println("No Available Studies");
			JFrame errorFrame = new JFrame();
			JOptionPane.showMessageDialog(errorFrame, "No Available Studies, please select a different directory");
			return;
		}
		

		JScrollPane listScroller = new JScrollPane(listOfStudies);
		
		JPanel availableStudyFrame = new JPanel(new BorderLayout());
		JPanel buttonFlow = new JPanel(new FlowLayout());
		
		JButton select = new JButton("Select");
		JButton cancel = new JButton("Cancel");
		
		buttonFlow.add(select);
		buttonFlow.add(cancel);
		
		availableStudyFrame.add(buttonFlow, BorderLayout.SOUTH);
		availableStudyFrame.add(listScroller);
		
		test.add(availableStudyFrame);
		
		select.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = listOfStudies.getSelectedIndex();
				Director.choseStudy(index);
				
				if(StateHolder.images() == 4)
				{
					fourTileMode();
				}
				else
				{
					singleTileMode();
				}
				images = Director.getImages();
				System.out.println(images.size());
				fillScreen(images);
				test.dispose();
				
			}
			
		});
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				test.dispose();
				
			}
			
		});
		
		
		test.setVisible(true);
		
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
		JMenuItem selectDirectory = new JMenuItem("Select Directory");
		JMenuItem openStudy = new JMenuItem("Open Study");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save As");
		JMenuItem close = new JMenuItem("Close Study");
		JMenuItem exit = new JMenuItem("Exit");
		
		saveAs.setEnabled(false);
		
		//create view menu items
		fourTile = new JMenuItem("Four Tile Mode");
		singleTile = new JMenuItem("Single Tile Mode");
		
		
		//create info menu items
		JMenuItem about = new JMenuItem("About");
		
		
		// add to the file menu
		file.add(selectDirectory);
		file.add(openStudy);
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
		selectDirectory.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//open();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					Director.setRoot(fc.getSelectedFile().getAbsolutePath());
					availableStudies();
				} 
				
			}
			
		});
		
		openStudy.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				availableStudies();
			}
			
		});
		
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				saveStudy.execute();
				
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
				changeState.execute();
				curMode = StateHolder.images();
				images = Director.getImages();
				System.out.println(images.size());
				fourTileMode();
				fillScreen(images);
				
			}
			
		});
		
		singleTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				changeState.execute();
				curMode = StateHolder.images();
				images = Director.getImages();
				singleTileMode();
				fillScreen(images);
				
			}
			
		});
		
		// info menu
		about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				about();
				
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
		rightArrow.setEnabled(Director.isRight());
		leftArrow.setEnabled(Director.isLeft());
		
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
				right.execute();
				images = Director.getImages();
				fillScreen(images);
				rightArrow.setEnabled(Director.isRight());
				leftArrow.setEnabled(Director.isLeft());
			}
			
		});
		
		leftArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				left.execute();
				images = Director.getImages();
				fillScreen(images);
				rightArrow.setEnabled(Director.isRight());
				leftArrow.setEnabled(Director.isLeft());
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
		rightArrow.setEnabled(Director.isRight());
		leftArrow.setEnabled(Director.isLeft());
		
		
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
		centerScreen = new JPanel(new GridLayout(2,2, 10, 10));
		JPanel topScreen = new JPanel(new FlowLayout());
		JPanel leftScreen = new JPanel(new GridLayout(3,1));
		JPanel rightScreen = new JPanel(new GridLayout(3,1));
		JPanel bottomScreen = new JPanel(new FlowLayout());
		
		
		// arrow Buttons
		rightArrow = new BasicArrowButton(BasicArrowButton.EAST);
		leftArrow = new BasicArrowButton(BasicArrowButton.WEST);
		
		// action listeners 

		
		//left and right button padding
		Border leftPadding = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		leftScreen.setBorder(leftPadding);
		Border rightPadding = BorderFactory.createEmptyBorder(0, 0, 0, 5);
		rightScreen.setBorder(rightPadding);
		
		// enable left and right buttons
		rightArrow.setEnabled(Director.isRight());
		leftArrow.setEnabled(Director.isLeft());
		
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
		
		rightArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				right.execute();
				images = Director.getImages();
				fillScreen(images);
				rightArrow.setEnabled(Director.isRight());
				leftArrow.setEnabled(Director.isLeft());
			}
			
		});
		
		leftArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				left.execute();
				images = Director.getImages();
				fillScreen(images);
				rightArrow.setEnabled(Director.isRight());
				leftArrow.setEnabled(Director.isLeft());
			}
			
		});
		
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
	
//	public void open(String directory)
//	{
//		viewed = new ArrayList<String>();
//		upcoming = new ArrayList<String>();
//		
//		File dir = new File(directory);
//		int curFile = 0;
//		
//		for (File child : dir.listFiles())
//		{
//		    if(child.getName().toLowerCase().endsWith(".jpg"))
//		    {
//		    	if(curFile == 0)
//		    	{
//		    		viewed.add(child.getAbsolutePath());
//		    	}
//		    	else
//		    	{
//		    		upcoming.add(child.getAbsolutePath());
//		    	}
//		    	++curFile;
//		    }
//		}
//	}
	
//	public ArrayList<String> right()
//	{
//		ArrayList<String> tempArray = new ArrayList<String>();
//		
//		if(curMode > upcoming.size())
//		{
//			int size = upcoming.size();
//			for(int imagePaths = 0; imagePaths < size; ++imagePaths)
//			{
//				String curPath = upcoming.get(0);
//				tempArray.add(curPath);
//				viewed.add(0, curPath);
//				upcoming.remove(0);
//			}
//		}
//		else
//		{
//			for(int images = 0; images < curMode; ++images)
//			{
//				String curPath = upcoming.get(0);
//				tempArray.add(curPath);
//				viewed.add(0, curPath);
//				upcoming.remove(0);
//			}
//		}
//		return tempArray;
//	}
	
//	public ArrayList<String> left()
//	{
//		ArrayList<String> tempArray = new ArrayList<String>();
//		
//		if(curMode > viewed.size())
//		{
//			int size = viewed.size();
//			for(int imagePaths = 0; imagePaths < size; ++imagePaths)
//			{
//				String curPath = viewed.get(0);
//				tempArray.add(curPath);
//				upcoming.add(0, curPath);
//				viewed.remove(0);
//			}
//		}
//		else
//		{
//			for(int images = 0; images < curMode; ++images)
//			{
//				String curPath = viewed.get(0);
//				tempArray.add(curPath);
//				upcoming.add(0, curPath);
//				viewed.remove(0);
//			}
//		}
//		return tempArray;
//	}
//	
//	
	
	public JLabel openImage(String filePath)
	{
		JLabel icon = new JLabel(new ImageIcon(filePath));
		
		return icon;
	}
	
	public void fillScreen(List<String> listOfImages)
	{
		centerScreen.removeAll();
		for(String paths: listOfImages)
		{
			centerScreen.add(openImage(paths));
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
//	public boolean isEnabled(int flag)
//	{
//		boolean result = true;
//		
//		if(flag == 0)
//		{
//			if(upcoming.size() < 1)
//			{
//				result = false;
//			}
//		}
//		else
//		{
//			if(viewed.size() < 2)
//			{
//				result = false;
//			}
//		}
//		
//		return result;
//	}
//	
	public void about()
	{
		final JFrame aboutFrame = new JFrame();
		aboutFrame.setSize(300, 200);
		aboutFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel aboutLayout = new JPanel(new BorderLayout());
		JPanel aboutInfo = new JPanel(new FlowLayout());
		JPanel aboutButtons = new JPanel(new FlowLayout());
		
		JButton cool = new JButton("Thats Fucking Awesome!");
		
		StyledDocument document = new DefaultStyledDocument();
		MutableAttributeSet defaultStyle = document.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_CENTER);
		
		JTextPane info = new JTextPane(document);
		info.setEditable(false);
		
		
		
		info.setText("\n First Implementation of the  \n " +
					"Medical Image Viewing Console \n" +
					" by Team Petulant-Batman");
		
		
		info.setSize(200, 200);
		
		aboutButtons.add(cool);
		aboutInfo.add(info);
		
		aboutLayout.add(aboutButtons, BorderLayout.SOUTH);
		aboutLayout.add(info, BorderLayout.CENTER);
		
		cool.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				aboutFrame.dispose();
				
			}
			
		});
		
		aboutFrame.add(aboutLayout);
		
		aboutFrame.setVisible(true);
		
		
		
		
	}
	
	public static void main(String [] args)
	{
		Frame test = new Frame();
	}

}
