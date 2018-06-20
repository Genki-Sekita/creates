package javaGUI5;

import java.awt.event.*;
import javax.swing.*;

public class RectButton extends JButton {
	StateManager stateManager;
	
	public RectButton(StateManager stateManager){
		super("Rectangle");
		addActionListener(new RectListener());
		this.stateManager = stateManager;
	}
	
	class RectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new RectState(stateManager));
		}
	}
}

class RectState implements State {
	StateManager stateManager;
	
	public RectState (StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y){
		stateManager.x1 = x;
		stateManager.y1 = y;
		stateManager.obj = new MyRectangle(x,y,0,0);
		stateManager.obj.setFillColor(stateManager.fillcolor);
		stateManager.obj.setLineColor(stateManager.linecolor);
		stateManager.obj.setHasen_type(stateManager.hasen_type);
		stateManager.obj.setLineWidth(stateManager.hutosa);
		stateManager.obj.setLineJuu(stateManager.lineJuu);
		stateManager.obj.setKage(stateManager.kage);
		stateManager.obj.setJissenHasen(stateManager.hasen);
		stateManager.obj.setAlpha(stateManager.al);
		stateManager.obj.setGray(stateManager.gray);
		stateManager.obj.setSepia(stateManager.sepia);
		stateManager.addDrawing(stateManager.obj);
	}
	
	public void mouseUp(int x,int y){
		if(Math.abs(x-stateManager.x1) < 10. && Math.abs(y-stateManager.y1) < 10.){
			stateManager.removeDrawing(stateManager.obj);
		}
	};
	
	public void mouseDrag(int x, int y){
		stateManager.obj.setSize((x-stateManager.x1),(y-stateManager.y1));
	};

}
