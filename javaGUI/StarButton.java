package javaGUI5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StarButton extends JButton {
	StateManager stateManager;
	
	public StarButton(StateManager stateManager){
		super("Star");
		addActionListener(new StarListener());
		this.stateManager = stateManager;
	}
	
	class StarListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new StarState(stateManager));
		}
	}
}

class StarState implements State {
	StateManager stateManager;
	
	public StarState (StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y){
		stateManager.x1 = x;
		stateManager.y1 = y;
		stateManager.obj = new MyStar(x,y,0,0);
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
			stateManager.removeDrawing(stateManager.obj);//オブジェクトが小さければ表示しない
		}
	};
	
	public void mouseDrag(int x, int y){
		//ドラッグしたら、それにあわせて大きさを変更する。
		stateManager.obj.setSize((x-stateManager.x1),(y-stateManager.y1));
	};

}
