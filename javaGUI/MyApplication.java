package javaGUI5;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;

import javaGUI5.OctButton.OctListener;

public class MyApplication extends JFrame{
  StateManager stateManager;
  MyCanvas canvas;
  private JMenuBar menuBar;
  private JMenu fillColorMenu,lineColorMenu;
  private JMenuItem whiteItem,redItem, blueItem, greenItem,blackItem,otherItem,spoitItem;
  private JMenuItem whiteItem2,redItem2, blueItem2, greenItem2,blackItem2,otherItem2;
  
  
	
  public MyApplication(){
    super("My Paint Application");

    canvas = new MyCanvas();
    canvas.setBackground(Color.white);
    
    JPanel jp = new JPanel();
    jp.setLayout(new GridLayout(3,0));
    
    stateManager = new StateManager(canvas);
	
    RectButton rectButton = new RectButton(stateManager);
    jp.add(rectButton);
    OvalButton ovalButton = new OvalButton(stateManager);
    jp.add(ovalButton);
    OctButton octButton = new OctButton(stateManager);
    jp.add(octButton);
    StarButton starButton = new StarButton(stateManager);
    jp.add(starButton);
    SelectButton selectButton = new SelectButton(stateManager);
    jp.add(selectButton);

    FileInButton fileinButton = new FileInButton(stateManager);
    jp.add(fileinButton);
    FileOutButton fileoutButton = new FileOutButton(stateManager);
    jp.add(fileoutButton);
    JCheckBox kageCheck = new JCheckBox("shadow");
	kageCheck.addItemListener(new KageCheckListener(stateManager));
	jp.add(kageCheck);
	JCheckBox hasenCheck = new JCheckBox("hasen");
	hasenCheck.addItemListener(new HasenCheckListener(stateManager));
	jp.add(hasenCheck);
	JCheckBox grayCheck = new JCheckBox("Gray");
	grayCheck.addItemListener(new GrayCheckListener(stateManager));
	jp.add(grayCheck);
	JCheckBox sepiaCheck = new JCheckBox("Sepia");
	sepiaCheck.addItemListener(new SepiaCheckListener(stateManager));
	jp.add(sepiaCheck);
	
	jp.add(new JLabel(" "));
	
	JSlider hasenSlider = new JSlider();
	hasenSlider.setMajorTickSpacing(10);
    hasenSlider.setPaintTicks(true);
	hasenSlider.addChangeListener(new HasenChangeListener(stateManager){
		public void stateChanged(ChangeEvent e) {
			stateManager.setHasen_type(hasenSlider.getValue());
		};
	});
	jp.add(hasenSlider);
	jp.add(new JLabel(":hasen"));
	
	JSlider hutosaSlider = new JSlider(0,10);
	hutosaSlider.setMajorTickSpacing(1);
    hutosaSlider.setPaintTicks(true);
    hutosaSlider.setLabelTable(hutosaSlider.createStandardLabels(2));
    hutosaSlider.setPaintLabels(true);
	hutosaSlider.addChangeListener(new HasenChangeListener(stateManager){
		public void stateChanged(ChangeEvent e) {
			stateManager.setHutosa((int)hutosaSlider.getValue());
		};
	});
	jp.add(hutosaSlider);
	jp.add(new JLabel(":hutosa"));
	
	JSlider lineJuuSlider = new JSlider(0,10);
	lineJuuSlider.setMajorTickSpacing(1);
    lineJuuSlider.setPaintTicks(true);
    lineJuuSlider.setLabelTable(lineJuuSlider.createStandardLabels(2));
    lineJuuSlider.setPaintLabels(true);
	lineJuuSlider.addChangeListener(new HasenChangeListener(stateManager){
		public void stateChanged(ChangeEvent e) {
			stateManager.setLinejuu((int)lineJuuSlider.getValue()) ;
		};
	});
	jp.add(lineJuuSlider);
	jp.add(new JLabel(":line_juu"));
	
	JSlider alphaSlider = new JSlider(0,255);
	alphaSlider.setMajorTickSpacing(25);
    alphaSlider.setPaintTicks(true);
    alphaSlider.setLabelTable(alphaSlider.createStandardLabels(50));
    alphaSlider.setPaintLabels(true);
	alphaSlider.addChangeListener(new HasenChangeListener(stateManager){
		public void stateChanged(ChangeEvent e) {
			stateManager.setAlpha((int)alphaSlider.getValue()) ;
		};
	});
	jp.add(alphaSlider);
	jp.add(new JLabel(":alpha"));
    
    menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    
    fillColorMenu = new JMenu("FillColor");
    whiteItem = new JMenuItem("white");
    redItem = new JMenuItem("Red");
    blueItem = new JMenuItem("Blue");
    greenItem = new JMenuItem("Green");
    blackItem = new JMenuItem("black");
    otherItem = new JMenuItem("other Color");
    spoitItem = new JMenuItem("Spoit");
    whiteItem.setActionCommand("0xffffff");
    redItem.setActionCommand("0xff0000");
    blueItem.setActionCommand("0x0000ff");
    greenItem.setActionCommand("0x00ff00");
    blackItem.setActionCommand("0x000000");
    fillColorMenu.add(whiteItem);
    fillColorMenu.add(redItem);
    fillColorMenu.add(blueItem);
    fillColorMenu.add(greenItem);
    fillColorMenu.add(blackItem);
    fillColorMenu.add(otherItem);
    fillColorMenu.add(spoitItem);
    whiteItem.addActionListener(new FillColorListener(stateManager));
    redItem.addActionListener(new FillColorListener(stateManager));
    blueItem.addActionListener(new FillColorListener(stateManager));
    greenItem.addActionListener(new FillColorListener(stateManager));
    blackItem.addActionListener(new FillColorListener(stateManager));
    otherItem.addActionListener(new FillColorListener(stateManager));
    spoitItem.addActionListener(new FillColorListener(stateManager));
    menuBar.add(fillColorMenu);
    
    
    lineColorMenu = new JMenu("LineColor");
    whiteItem2 = new JMenuItem("white");
    redItem2 = new JMenuItem("Red");
    blueItem2 = new JMenuItem("Blue");
    greenItem2 = new JMenuItem("Green");
    blackItem2 = new JMenuItem("black");
    otherItem2 = new JMenuItem("other Color");
    whiteItem2.setActionCommand("0xffffff");
    redItem2.setActionCommand("0xff0000");
    blueItem2.setActionCommand("0x0000ff");
    greenItem2.setActionCommand("0x00ff00");
    blackItem2.setActionCommand("0x000000");
    lineColorMenu.add(whiteItem2);
    lineColorMenu.add(redItem2);
    lineColorMenu.add(blueItem2);
    lineColorMenu.add(greenItem2);
    lineColorMenu.add(blackItem2);
    lineColorMenu.add(otherItem2);
    whiteItem2.addActionListener(new LineColorListener(stateManager));
    redItem2.addActionListener(new LineColorListener(stateManager));
    blueItem2.addActionListener(new LineColorListener(stateManager));
    greenItem2.addActionListener(new LineColorListener(stateManager));
    blackItem2.addActionListener(new LineColorListener(stateManager));
    otherItem2.addActionListener(new LineColorListener(stateManager));
    menuBar.add(lineColorMenu);
    
	canvas.setFocusable(true);
	
    canvas.addMouseListener(new MouseAdapter(){
	public void mousePressed(MouseEvent e){
	  stateManager.mouseDown(e.getX(),e.getY(),e.getButton());
	}
	public void mouseReleased(MouseEvent e){
		stateManager.mouseUp(e.getX(),e.getY(),e.getButton());
	}
      });
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(jp, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
    
    canvas.setFocusable(true);
    canvas.addKeyListener(new KeyAdapter(){
		public void keyPressed(KeyEvent e){
			switch (e.getKeyChar()) {
			case KeyEvent.VK_BACK_SPACE:
				for(int i=canvas.getMediator().selectedDrawings.size()-1; i>=0; i--)
				canvas.getMediator().removeDrawing(canvas.getMediator().selectedDrawings.elementAt(i));
				break;
			case KeyEvent.VK_C:
				canvas.getMediator().copy();
				break;
			case KeyEvent.VK_X:
				canvas.getMediator().cut();
				break;
			case KeyEvent.VK_1:
				stateManager.setState(new RectState(stateManager));
				break;
			case KeyEvent.VK_2:
				stateManager.setState(new OvalState(stateManager));
				break;
			case KeyEvent.VK_3:
				stateManager.setState(new OctState(stateManager));
				break;
			case KeyEvent.VK_4:
				stateManager.setState(new StarState(stateManager));
				break;
			case KeyEvent.VK_A:
				canvas.getMediator().to_mae();
				break;
			case KeyEvent.VK_S:
				canvas.getMediator().to_ato();
				break;
			default:
				break;
			}
			canvas.repaint();
		}
	});
	
    
    canvas.addMouseMotionListener(new MouseMotionAdapter(){
    	public void mouseDragged(MouseEvent e){
    	  stateManager.mouseDrag(e.getX(),e.getY(),e.getButton());
    	}
          });
  
  this.addWindowListener(new WindowAdapter(){
	  public void windowClosing(WindowEvent e){
		  System.exit(0);
	  }
  });
  
}
  
public Dimension getPreferredSize(){
	  return new Dimension(1300,1400);
  }


  
public static void main (String[] args){
	  MyApplication app = new MyApplication();
	  app.pack();
	  app.setVisible(true);
}
}
