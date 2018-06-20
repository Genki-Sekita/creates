package javaGUI5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class MyStar extends MyDrawing{
	public MyStar(int xpt, int ypt, int wide, int hight, Color lineColor, Color fillColor, int lineWidth){
		super(wide, hight, lineColor, fillColor, lineWidth);
		setLocation(xpt, ypt);
	}
	
	public MyStar(int xpt, int ypt, int wide, int hight){
		super(wide, hight);
		setLocation(xpt, ypt);
	}
	
	public MyStar(int xpt, int ypt, Color lineColor, Color fillColor, int lineWidth){
		super(lineColor, fillColor, lineWidth);
		setLocation(xpt, ypt);
	}
	
	public MyStar(int xpt, int ypt){
		super();
		setLocation(xpt, ypt);
	}
	
	public int[] points(int x, int w, boolean xTrue){
		//八角形の点の位置を決める配列　正弦余弦を使い角度を変えることで作り出す。
		int[] Points = new int[10];
		for(int i = 0; i < 10; i++){
			if(xTrue) {
				if(i%2 == 0) Points[i] = x + w/2 + (int)( (double)w/2 * Math.sin(Math.PI/5 * i));
				else Points[i] = x + w/2 + (int)( (double)w/5 * Math.sin(Math.PI/5 * i));
			}
			else {
				if(i%2 == 0) Points[i] = x + w/2 - (int)( (double)w/2 * Math.cos(Math.PI/5 * i));
				else Points[i] = x + w/2 - (int)( (double)w/5 * Math.cos(Math.PI/5 * i));
			}
		}
		return Points;
	}
	
	public void draw(Graphics g){
		super.draw(g);
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		int[] xPoints ,yPoints ;
		
		//高さや横幅が負の時のための処理
		if(w<0){
			x += w;
			w *= -1;
		}
		if(h<0){
			y += h;
			h *= -1;
		}
		
Graphics2D g2 = (Graphics2D) g;
		
		if(getKage()){
			xPoints = points(x+7,w,true);
			yPoints = points(y+7,h,false);
			g2.setStroke(new BasicStroke(getLineWidth()));
			g2.setColor(Color.black);
			Polygon polygon1 = new Polygon(xPoints,yPoints, 10);
			g2.fill(polygon1);
			g2.setColor(Color.black);
			Polygon polygon2 = new Polygon(xPoints,yPoints, 10);
			g2.draw(polygon2);
		}
		
		for(int i = 0; i < getLineJuu(); i++){	
			xPoints = points(x,w,true);
			yPoints = points(y,h,false);
			if(!getJissenhasen()) g2.setStroke(new BasicStroke(getLineWidth()));
			else {
				float dash[] = {10.0f, getHasen_type()};
				g2.setStroke(new BasicStroke(getLineWidth(),
					BasicStroke.CAP_BUTT, 
	                BasicStroke.JOIN_MITER, 
	                3.0f,
	                dash,
	                0.0f));
		    }
			int alpha = 0;
			if(i == getLineJuu()-1) alpha = al;
			Color fiCo = getFillColor();
			if(gray){
				int gr = (fiCo.getRed() + fiCo.getGreen() + fiCo.getBlue()) / 3;
				fiCo = new Color(gr,gr,gr,alpha);
			}else if(sepia){
				float gr = (float)(fiCo.getRed() + fiCo.getGreen() + fiCo.getBlue()) / (float)(3*255);
				fiCo = new Color((int)(gr * 240) ,(int)(gr * 200) ,(int)(gr * 145) ,alpha);
			}else{
				fiCo = new Color(fiCo.getRed(),fiCo.getGreen(),fiCo.getBlue(),alpha);
			}
			g2.setColor(fiCo);
			Polygon polygon1 = new Polygon(xPoints,yPoints, 10);
			g2.fill(polygon1);
			g2.setColor(getLineColor());
			Polygon polygon2 = new Polygon(xPoints,yPoints, 10);
			g2.draw(polygon2);
			
			x += 7;
			y += 7;
			w -= 14;
			h -= 14;
		}
	}

}
