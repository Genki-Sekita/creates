package javaGUI5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.Object;
import java.awt.Robot;

public class MyActionListener {
	//表向きの空クラス
}

class KageCheckListener implements ItemListener{
	StateManager stateManager;
	public KageCheckListener(StateManager stateManager){
		this.stateManager = stateManager;
	}
    public void itemStateChanged(ItemEvent e){
	int state = e.getStateChange();
	if(state == ItemEvent.SELECTED)
	    stateManager.setKage(true);
	else
		stateManager.setKage(false);
    }
}

class HasenCheckListener implements ItemListener{
	StateManager stateManager;
	public HasenCheckListener(StateManager stateManager){
		this.stateManager = stateManager;
	}
    public void itemStateChanged(ItemEvent e){
	int state = e.getStateChange();
	if(state == ItemEvent.SELECTED)
		stateManager.setHasen(true);
	else
		stateManager.setHasen(false);
    }
}

class GrayCheckListener implements ItemListener{
	StateManager stateManager;
	public GrayCheckListener(StateManager stateManager){
		this.stateManager = stateManager;
	}
    public void itemStateChanged(ItemEvent e){
	int state = e.getStateChange();
	if(state == ItemEvent.SELECTED)
		stateManager.setGray(true);
	else
		stateManager.setGray(false);
    }
}

class SepiaCheckListener implements ItemListener{
	StateManager stateManager;
	public SepiaCheckListener(StateManager stateManager){
		this.stateManager = stateManager;
	}
    public void itemStateChanged(ItemEvent e){
	int state = e.getStateChange();
	if(state == ItemEvent.SELECTED)
		stateManager.setSepia(true);
	else
		stateManager.setSepia(false);
    }
}

class HasenChangeListener implements ChangeListener{
	StateManager stateManager;
	public HasenChangeListener(StateManager stateManager){
		this.stateManager = stateManager;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	};
	
	
}

class FillColorListener implements ActionListener {
	StateManager stateManager;
	Mediator mediator;
	private Color color;

	FillColorListener(StateManager stateManager) {
		this.stateManager = stateManager;
		color = Color.white;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "other Color"){
			this.color = JColorChooser.showDialog(null, "Choose Color", Color.white);
			stateManager.setFillColor(color);
		}
		else if(e.getActionCommand() == "Spoit")
			stateManager.setState(new SpoitState(stateManager));
		else{
			this.color = Color.decode(e.getActionCommand());
			stateManager.setFillColor(color);
		}
	}
}

class LineColorListener implements ActionListener {
	StateManager stateManager;
	private Color color;

	LineColorListener(StateManager stateManager) {
		this.stateManager = stateManager;
		color = Color.white;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "other Color")
			this.color = JColorChooser.showDialog(null, "Choose Color", Color.white);
		else
			this.color = Color.decode(e.getActionCommand());
		stateManager.setLineColor(color);
	}
}

class SpoitListener implements ActionListener{
	StateManager stateManager;
	
	SpoitListener(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void actionPerformed(ActionEvent e){
		stateManager.setState(new OctState(stateManager));
	}
}

class SpoitState implements State {
	StateManager stateManager;
	Color color;
	
	public SpoitState (StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y){
		Robot r;
		x = (int) MouseInfo.getPointerInfo().getLocation().getX();
		y = (int) MouseInfo.getPointerInfo().getLocation().getY();
		try{ r = new Robot();}
		catch(AWTException e){
			e.printStackTrace();
			return;
		}
		color = r.getPixelColor(x,y);
		stateManager.setFillColor(color);
	}
	
	public void mouseUp(int x,int y){
	};
	
	public void mouseDrag(int x, int y){
	};

}


