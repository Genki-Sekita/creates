package javaGUI5;

import java.awt.*;
import java.io.*;

public class MyDrawing implements Cloneable, Serializable {
	private int x, y, w, h; // x・y座標, 幅, 高さ
	private Color lineColor, fillColor; // 線の色, 塗り色
	private int lineWidth; //線の太さ
	private boolean jissenHasen; //実線と破線を変更
	private boolean kage; //影のon/off
	private float hasen_type = 3.0f;
	private int lineJuu = 1;
	boolean isSelected;
	Shape region;
	final int SIZE = 7;
	int al = 253;
	boolean gray = false;
	boolean sepia = false;

	public MyDrawing(int wide, int hight, Color lineColor, Color fillColor, int lineWidth){
		x = y = 0;
		w = wide;
		h = hight;
		this.lineColor = lineColor;
		this.fillColor = fillColor;
		this.lineWidth = lineWidth;
		this.isSelected = false;
		setRegion();
	}
	
	public MyDrawing(int wide, int hight){
		this(wide, hight, Color.black, Color.white, 1);
	}
	
	public MyDrawing(Color lineColor, Color fillColor,  int lineWidth){
		this(40, 40, lineColor, fillColor, lineWidth);
	}
	
	public MyDrawing(){
		this(40, 40);
	}
	
	public void draw(Graphics g){
		if (getSelected()) {
			g.setColor(Color.black);
			g.fillRect(x + w / 2 - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			g.fillRect(x + w / 2 - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
			g.fillRect(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
		}
	}
	
	public boolean getSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean contains(int x, int y) {
		return region.contains(x, y);
	}

	public void setRegion() {
		if (w < 0 && h >= 0) {
			region = new Rectangle(x + w, y, -1 * w, h);
		} else if (w >= 0 && h < 0) {
			region = new Rectangle(x, y + h, w, -1 * h);
		} else if (w < 0 && h < 0) {
			region = new Rectangle(x + w, y + h, -1 * w, -1 * h);
		} else {
			region = new Rectangle(x, y, w, h);
		}
	}
	
	public void move(int dx, int dy){
		this.x += dx;
		this.y += dy;
		this.setRegion();
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
		this.setRegion();
	}
	
	public void setSize(int w, int h){
		this.w = w;
		this.h = h;
		this.setRegion();
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getW(){
		return this.w;
	}
	
	public int getH(){
		return this.h;
	}
	
	public Color getLineColor(){
		return this.lineColor;
	}
	
	public Color getFillColor(){
		return this.fillColor;
	}
	
	public int getLineWidth(){
		return this.lineWidth;
	}
	
	public void setLineColor(Color lineColor){
		this.lineColor = lineColor;
	}
	
	public void setFillColor(Color fillColor){
		this.setRegion();
		this.fillColor = fillColor;
	}
	
	public void setLineWidth(int lineWidth){
		this.lineWidth = lineWidth;
	}
	
	public boolean getJissenhasen(){
		return this.jissenHasen;
	}
	
	public boolean getKage(){
		return this.kage;
	}
	
	public void setJissenHasen(boolean jissenHasen){
		this.jissenHasen = jissenHasen;
	}
	
	public void setKage(boolean kage){
		this.kage = kage;
	}
	
	public float getHasen_type(){
		return hasen_type;
	}
	
	public void setHasen_type(float hasen_type){
		this.hasen_type = hasen_type;
	}
	
	public int getLineJuu(){
		return lineJuu;
	}
	
	public void setLineJuu(int lineJuu){
		this.lineJuu = lineJuu;
	}
	
	public Object clone(){
		try{
		return super.clone();
		}catch (CloneNotSupportedException e){
			throw new InternalError(e.toString());
		}
	}
	
	public void setAlpha(int alpha){
		this.al = alpha;
	}
	
	public void setGray(boolean gray){
		this.gray = gray;
	}
	
	public void setSepia(boolean sepia){
		this.sepia = sepia;
	}

}
