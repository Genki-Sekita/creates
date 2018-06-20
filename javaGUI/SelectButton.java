package javaGUI5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

public class SelectButton extends JButton {
	StateManager stateManager;

	public SelectButton(StateManager stateManager) {
		super("Select");
		addActionListener(new SelectListener());
		this.stateManager = stateManager;
	}

	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new SelectState(stateManager));
		}
	}
}

class SelectState implements State {
	StateManager stateManager;
	boolean sentaku_hanni;

	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		stateManager.canvas.getMediator().setSelected(x,y);
		if (stateManager.canvas.mediator.selectedDrawings.size() == 0) {
			stateManager.x1 = x;
			stateManager.y1 = y;
			stateManager.x_mae = x;
			stateManager.y_mae = y;
			stateManager.sentaku = new MyRectangle(x, y, 0, 0,Color.black, Color.blue, 1);
			stateManager.sentaku.setAlpha(20);
			stateManager.sentaku.setJissenHasen(true);
			stateManager.addDrawing(stateManager.sentaku);
			sentaku_hanni = true;
		}else{
			stateManager.x_mae = x;
			stateManager.y_mae = y;
				for(int i=stateManager.canvas.getMediator().selectedDrawings.size()-1; i>=0; i--){
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setFillColor(stateManager.fillcolor);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setLineColor(stateManager.linecolor);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setJissenHasen(stateManager.hasen);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setKage(stateManager.kage);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setLineJuu(stateManager.lineJuu);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setHasen_type(stateManager.hasen_type);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setLineWidth(stateManager.hutosa);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setAlpha(stateManager.al);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setGray(stateManager.gray);
					stateManager.canvas.getMediator().selectedDrawings.elementAt(i).setSepia(stateManager.sepia);
			}
		}
	}

	public void mouseUp(int x, int y) {
		if (sentaku_hanni) {
			stateManager.removeDrawing(stateManager.sentaku);
			stateManager.canvas.getMediator().selectedDrawings.remove(stateManager.sentaku);
			sentaku_hanni = false;
	}
	}

	public void mouseDrag(int x, int y) {
		if(sentaku_hanni){
			stateManager.sentaku.setSize(x-stateManager.x1, y-stateManager.y1);
			stateManager.canvas.getMediator().selectedDrawings = new Vector<MyDrawing>();
			int xa,xb,ya,yb;
			if(x-stateManager.x1 < 0){
				xa = x;
				xb = stateManager.x1;
			}else{
				xb = x;
				xa = stateManager.x1;
			}
			if(y-stateManager.y1 < 0){
				ya = y;
				yb = stateManager.y1;
			}else{
				yb = y;
				ya = stateManager.y1;
			}
			//Shape shape = new Rectangle(stateManager.x1,stateManager.y1, x-stateManager.x1, y-stateManager.y1); 
			Shape shape = new Rectangle(xa,ya,xb-xa,yb-ya);
			for(int i = stateManager.canvas.getMediator().drawings.size()-1; i>=0; i--){
    			if(shape.contains(stateManager.canvas.getMediator().drawings.elementAt(i).getX(), 
    					stateManager.canvas.getMediator().drawings.elementAt(i).getY(), 
    					stateManager.canvas.getMediator().drawings.elementAt(i).getW(), 
    					stateManager.canvas.getMediator().drawings.elementAt(i).getH())){
    				stateManager.canvas.getMediator().drawings.elementAt(i).setSelected(true);
    				stateManager.canvas.getMediator().selectedDrawings.add(stateManager.canvas.getMediator().drawings.elementAt(i));
    			} else{
    				stateManager.canvas.getMediator().drawings.elementAt(i).setSelected(false);
    				stateManager.canvas.getMediator().selectedDrawings.remove(stateManager.canvas.getMediator().drawings.elementAt(i));
    			}
    		}
		}else{
		stateManager.canvas.getMediator().move(x-stateManager.x_mae,y-stateManager.y_mae);
		stateManager.x_mae = x;
		stateManager.y_mae = y;
		}
	}
}