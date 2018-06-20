package javaGUI5;

import java.awt.*;
import java.awt.event.*;

public class StateManager {
	MyCanvas canvas;
	State state = new RectState(this);
	int x1,y1;
	int x_mae,y_mae;
	MyDrawing obj;
	boolean hasen;
	boolean kage;
	float hasen_type = 3.0f;
	int hutosa = 1;
	int lineJuu = 1;
	Color fillcolor = Color.white;
	Color linecolor = Color.black;
	MyRectangle sentaku;
	int al = 255;
	boolean gray;
	boolean sepia;
	
	public void mouseDown(int x, int y, int button){
		if (button != 1){
			canvas.getMediator().paste(x,y);
			canvas.repaint();
			state.mouseUp(x, y);
			return;
		}
		state.mouseDown(x, y);
		canvas.requestFocusInWindow();
		canvas.repaint();
	};
	public void mouseUp(int x, int y, int button){
		if(button != 1) return;
		state.mouseUp(x, y);
		canvas.requestFocusInWindow();
		canvas.repaint();
	};
	public void mouseDrag(int x, int y, int button){
		if(button != 1) return;
		state.mouseDrag(x, y);
		canvas.repaint();
	};
	
	public StateManager(MyCanvas canvas){
		this.canvas = canvas;
	};
	
	public void addDrawing(MyDrawing d){
		canvas.getMediator().addDrawing(d);
	}
	
	public void removeDrawing(MyDrawing d){
		canvas.getMediator().removeDrawing(d);
	}
	
	public void setState(State state){
		this.state = state;
	};
	
	void setKage(boolean kage){
		this.kage = kage;
		if(canvas.getMediator() != null)
		if (canvas.getMediator().selectedDrawings != null)
			for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setKage(kage);
		canvas.repaint();
	}
	
	boolean getKage(){
		return kage;
	}
	
	void setHasen(boolean hasen){
		this.hasen = hasen;
		if(canvas.getMediator() != null)
		if (canvas.getMediator().selectedDrawings != null)
			for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setJissenHasen(hasen);
		canvas.repaint();
	}
	
	void setHasen_type(float linetype){
		this.hasen_type = linetype;
		if(canvas.getMediator() != null)
			if (canvas.getMediator().selectedDrawings != null)
				for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setHasen_type(linetype);
			canvas.repaint();
	}
	
	void setHutosa(int hutosa){
		this.hutosa = hutosa;
		if(canvas.getMediator() != null)
			if (canvas.getMediator().selectedDrawings != null)
				for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setLineWidth(hutosa);
			canvas.repaint();
	}
	
	void setFillColor(Color color){
		fillcolor = color;
		if(canvas.getMediator() != null)
		if (canvas.getMediator().selectedDrawings != null){
			for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
			canvas.getMediator().selectedDrawings.elementAt(i).setFillColor(color);
		}
		canvas.repaint();
	};
	
	void setLineColor(Color color){
		linecolor = color;
		if(canvas.getMediator() != null)
		if (canvas.getMediator().selectedDrawings != null){
			for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
			canvas.getMediator().selectedDrawings.elementAt(i).setLineColor(color);
		}
		canvas.repaint();
	};
	
	void setLinejuu(int line){
		this.lineJuu = line;
		if(canvas.getMediator() != null)
			if (canvas.getMediator().selectedDrawings != null)
				for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setLineJuu(line);
			canvas.repaint();
	}
	
	void setAlpha(int alpha){
		this.al = alpha;
		if(canvas.getMediator() != null)
			if (canvas.getMediator().selectedDrawings != null)
				for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setAlpha(alpha);
			canvas.repaint();
	}
	
	void setGray(boolean g){
		this.gray = g;
		if(canvas.getMediator() != null)
			if (canvas.getMediator().selectedDrawings != null)
				for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setGray(g);
			canvas.repaint();
	}
	
	void setSepia(boolean s){
		this.sepia = s;
		if(canvas.getMediator() != null)
			if (canvas.getMediator().selectedDrawings != null)
				for (int i = 0; i < canvas.getMediator().selectedDrawings.size(); i++)
				canvas.getMediator().selectedDrawings.elementAt(i).setSepia(s);
			canvas.repaint();
	}


}

