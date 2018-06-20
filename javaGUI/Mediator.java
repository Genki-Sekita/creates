package javaGUI5;

import java.util.Enumeration;
import java.util.Vector;
import java.io.*;

public class Mediator {
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings = new Vector<MyDrawing>();
	Vector<MyDrawing> buffers = new Vector<MyDrawing>();
	Vector<MyDrawing> buffer_clone = new Vector<MyDrawing>();
	File file = new File("file.txt");

	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
	}

	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}

	public void addDrawing(MyDrawing d) {
		drawings.add(d);
		setSelectedDrawings(d);
	}

	public void removeDrawing(MyDrawing d) {
		if(d != null)
		drawings.remove(d);
	}

	public Vector<MyDrawing> getSelectedDrawings() {
		return selectedDrawings;
	}

	public void move(int dx, int dy) {
		for (int i = 0; i < selectedDrawings.size(); i++)
			selectedDrawings.elementAt(i).move(dx, dy);
	}

	public void repaint() {
		canvas.repaint();
	}
	
	public void clearSelectedDrawings() {
		for (int i = selectedDrawings.size() - 1; i >= 0; i--) {
			selectedDrawings.elementAt(i).setSelected(false);
			selectedDrawings.remove(i);
		}
	}

	public void setSelected(int x, int y){
		for(int i=0; i < selectedDrawings.size(); i++)
			if(selectedDrawings.elementAt(i).contains(x,y))
				return;
		boolean select = true;
		selectedDrawings = new Vector<MyDrawing>();
		for(int i=drawings.size()-1; i>=0; i--){
			if(drawings.elementAt(i).contains(x,y) && select){
				drawings.elementAt(i).setSelected(true);
				selectedDrawings.add(drawings.elementAt(i));
				select = false;
			}else{
				drawings.elementAt(i).setSelected(false);
			}
		}
		if(select) selectedDrawings = new Vector<MyDrawing>();
	}

	public void setSelectedDrawings(MyDrawing d){
		setSelected(-13,-13);//初期化
		d.setSelected(true);
		selectedDrawings.add(d);
	}
	
	public void clearBuffer() {
		buffers = new Vector<MyDrawing>();
	}

	public void copy() {
		clearBuffer();
			for(int i=selectedDrawings.size()-1; i>=0; i--)
				buffers.add((MyDrawing) selectedDrawings.elementAt(i).clone());
	}

	public void cut() {
		copy();
			for(int i=selectedDrawings.size()-1; i>=0; i--)
				removeDrawing(selectedDrawings.elementAt(i));
	}

	public void paste(int x, int y) {
		buffer_clone = new Vector<MyDrawing>();
			for(int i=0; i < buffers.size(); i++){
				buffer_clone.add((MyDrawing)buffers.elementAt(i).clone());
				buffer_clone.elementAt(i).setLocation(x, y);
				addDrawing(buffer_clone.elementAt(i));
			}
	}
	
	public void fileIn(){
		try {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fin);
		    drawings = (Vector)in.readObject();
		    fin.close();
		    canvas.repaint();
		}catch (Exception ex){
		}
	}
	
	public void fileOut(){
		try {
		    FileOutputStream fout = new FileOutputStream(file);
		    ObjectOutputStream out = new ObjectOutputStream(fout);
	
		    out.writeObject(drawings);
		    out.flush();
		    fout.close();
		    canvas.repaint();
		} catch (Exception ex) {
		}
	}
	
	public void to_mae(){
		int basho;
		for(int i=0; i < selectedDrawings.size(); i++){
			basho = drawings.indexOf(selectedDrawings.elementAt(i),0);
			drawings.removeElementAt(basho);
			if(basho < drawings.size()) basho++;
			drawings.add(basho,selectedDrawings.elementAt(i));
		}
		canvas.repaint();
	}
	
	public void to_ato(){
		int basho;
		for(int i=selectedDrawings.size()-1; i >= 0; i--){
			basho = drawings.indexOf(selectedDrawings.elementAt(i),0);
			drawings.removeElementAt(basho);
			if(basho > 0) basho--;
			drawings.add(basho,selectedDrawings.elementAt(i));
		}
		canvas.repaint();
	}
	
}