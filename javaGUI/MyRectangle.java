package javaGUI5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyRectangle extends MyDrawing{
	public MyRectangle(int xpt, int ypt, int wide, int hight, Color lineColor, Color fillColor, int lineWidth){
		super(wide, hight, lineColor, fillColor, lineWidth);
		setLocation(xpt, ypt);
	}
	
	public MyRectangle(int xpt, int ypt, int wide, int hight){
		super(wide, hight);
		setLocation(xpt, ypt);
	}
	
	public MyRectangle(int xpt, int ypt, Color lineColor, Color fillColor, int lineWidth){
		super(lineColor, fillColor, lineWidth);
		setLocation(xpt, ypt);
	}
	
	public MyRectangle(int xpt, int ypt){
		super();
		setLocation(xpt, ypt);
	}
	
	public void draw(Graphics g){	
		super.draw(g);
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
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
			g2.setStroke(new BasicStroke(getLineWidth()));
			g2.setColor(Color.black);
			g2.fillRect(x+7, y+7, w, h);
			g2.setColor(Color.black);
			g2.drawRect(x+7, y+7, w, h);
		}
		
		for(int i = 0; i < getLineJuu(); i++){	
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
			g2.fillRect(x, y, w, h);
			g2.setColor(getLineColor());
			g2.drawRect(x, y, w, h);
			
			x += 3;
			y += 3;
			w -= 6;
			h -= 6;
		}
	}
}
