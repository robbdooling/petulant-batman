package View;

import Command.ChangeStateCommand;
import Command.LeftCommand;
import Command.RightCommand;
import Command.SaveStudyCommand;
import State.StateHolder;
import Study.NoValidStudiesFoundException;
import Study.StudyFactory;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
	private List<String> images;
	private final JFileChooser fc = new JFileChooser();
	private LeftCommand left;
	private RightCommand right;
	private ChangeStateCommand changeState;
	private SaveStudyCommand saveStudy;
	private JList<Object> listOfStudies;
	
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
		listOfStudies = null;
		left = new LeftCommand();
		right = new RightCommand();
		changeState = new ChangeStateCommand();
		saveStudy = new SaveStudyCommand();
		setSize(900, 600);
		buildMenuBar();
		setResizable(false);
		startUpScreen();
		
		
		
		add(mainLayout);
		setVisible(true);
	}
	
	public void availableStudies()
	{
		
		final JFrame test = new JFrame();
		test.setSize(500, 500);
		test.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try {
			listOfStudies = new JList<Object>(StudyFactory.getStudyNames().toArray());
		} catch (NoValidStudiesFoundException e1) {
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
				StudyFactory.choseStudy(index);
				
				if(StateHolder.images() == 4)
				{
					fourTileMode();
				}
				else
				{
					singleTileMode();
				}
				images = StateHolder.getStudy().getImages();
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
					StudyFactory.setRoot(fc.getSelectedFile().getAbsolutePath());
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
				promptSave(0);
			}
			
		});
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				promptSave(1);
				
			}
			
		});
		
		// view menu
		fourTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				changeState.execute();
				curMode = StateHolder.images();
				images = StateHolder.getStudy().getImages();
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
				images = StateHolder.getStudy().getImages();
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
		
		// arrow Buttons
		BasicArrowButton rightArrow = new BasicArrowButton(BasicArrowButton.EAST);
		BasicArrowButton leftArrow = new BasicArrowButton(BasicArrowButton.WEST);
		
		// action listeners 

		
		//left and right button padding
		Border leftPadding = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		leftScreen.setBorder(leftPadding);
		Border rightPadding = BorderFactory.createEmptyBorder(0, 0, 0, 5);
		rightScreen.setBorder(rightPadding);
		
		// enable left and right buttons
		rightArrow.setEnabled(StateHolder.getStudy().canRight());
		leftArrow.setEnabled(StateHolder.getStudy().canLeft());
		
		// add to layouts
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
		
		// arrow Buttons
		rightArrow = new BasicArrowButton(BasicArrowButton.EAST);
		leftArrow = new BasicArrowButton(BasicArrowButton.WEST);
		
		// action listeners 
		rightArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				right.execute();
				images = StateHolder.getStudy().getImages();
				fillScreen(images);
				rightArrow.setEnabled(StateHolder.getStudy().canRight());
				leftArrow.setEnabled(StateHolder.getStudy().canLeft());
			}
			
		});
		
		leftArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				left.execute();
				images = StateHolder.getStudy().getImages();
				fillScreen(images);
				rightArrow.setEnabled(StateHolder.getStudy().canRight());
				leftArrow.setEnabled(StateHolder.getStudy().canLeft());
			}
			
		});
		
		//left and right button padding
		Border leftPadding = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		leftScreen.setBorder(leftPadding);
		Border rightPadding = BorderFactory.createEmptyBorder(0, 0, 0, 5);
		rightScreen.setBorder(rightPadding);
		
		
		
		// enable left and right buttons
		rightArrow.setEnabled(StateHolder.getStudy().canRight());
		leftArrow.setEnabled(StateHolder.getStudy().canLeft());
		
		
		// add to layouts
		
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
		rightArrow.setEnabled(StateHolder.getStudy().canRight());
		leftArrow.setEnabled(StateHolder.getStudy().canLeft());
		
	
		leftScreen.add(new JLabel(""));
		leftScreen.add(leftArrow);
		
		rightScreen.add(new JLabel(""));
		rightScreen.add(rightArrow);
		
		rightArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				right.execute();
				images = StateHolder.getStudy().getImages();
				fillScreen(images);
				rightArrow.setEnabled(StateHolder.getStudy().canRight());
				leftArrow.setEnabled(StateHolder.getStudy().canLeft());
			}
			
		});
		
		leftArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				left.execute();
				images = StateHolder.getStudy().getImages();
				fillScreen(images);
				rightArrow.setEnabled(StateHolder.getStudy().canRight());
				leftArrow.setEnabled(StateHolder.getStudy().canLeft());
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
	
	public void promptSave(final int flag)
	{
		final JFrame savePrompt = new JFrame();
		savePrompt.setSize(350,100);
		savePrompt.setResizable(false);
		
		JPanel promptLayout = new JPanel(new BorderLayout());
		JPanel centerFlow = new JPanel(new FlowLayout());
		JPanel buttonFlow = new JPanel(new FlowLayout());
		
		JLabel notSaved = new JLabel("Warning! Your current state is not saved");
		
		JButton closeAnyway = new JButton("Close Anyway");
		JButton saveNow = new JButton("Save");
		JButton cancelNow = new JButton("Cancel");
		
		centerFlow.add(notSaved);
		buttonFlow.add(closeAnyway);
		buttonFlow.add(saveNow);
		buttonFlow.add(cancelNow);
		
		promptLayout.add(centerFlow, BorderLayout.CENTER);
		promptLayout.add(buttonFlow, BorderLayout.SOUTH);
		
		savePrompt.add(promptLayout);
		
		closeAnyway.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				savePrompt.dispose();
				if(flag == 0)
				{
					startUpScreen();
				}
				else
				{
					System.exit(EXIT_ON_CLOSE);
				}
			}
			
			
		});
		
		saveNow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				saveStudy.execute();
				if(flag == 0)
				{
					startUpScreen();
				}
				else
				{
					System.exit(EXIT_ON_CLOSE);
				}
				savePrompt.dispose();
			}
			
			
		});
		
		cancelNow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				savePrompt.dispose();
				
			}
			
			
		});
		
		savePrompt.setVisible(true);
		
		
	}
	
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

	public void about()
	{
		final JFrame aboutFrame = new JFrame();
		aboutFrame.setSize(300, 200);
		aboutFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel aboutLayout = new JPanel(new BorderLayout());
		JPanel aboutInfo = new JPanel(new FlowLayout());
		JPanel aboutButtons = new JPanel(new FlowLayout());
		
		JButton cool = new JButton("Thats Awesome!");
		
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
