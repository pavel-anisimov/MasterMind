package pavel.game;

// class that draws circle pins n the board

import java.awt.*;
import javax.swing.*;
import java.awt.Color;

@SuppressWarnings("serial")
public class CirclePin extends JPanel{
	private final int margin = 5;
	private int diameter;
	private int xLocation;
	private int yLocation;
	private Color color;
	private boolean empty;
	private boolean fieldPin;

	// constructor draws color pins
	public void setCirclePin( int d, int x, int y, Color c){
		
		diameter = d;
		xLocation = x + margin;
		yLocation = y + margin;
		color = c;
		empty = false;	
	}
	
	// extended constructor that may allow to draw grey pin 
	public void setCirclePin( int d, int x, int y, Color c, boolean e){		
		diameter = d;
		xLocation = x;
		yLocation = y;
		color = c;
		empty = e;
		fieldPin = true;
	}
	
	///////////////// method for drawing a pin
	// nothing complicated, just pure circles
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		
		/// method distinguish between color and gray pins
		if (fieldPin == true) {
			g.setColor(Color.blue.darker().darker());				
			g.drawRoundRect(xLocation, yLocation, diameter + 2 * margin, diameter + 2 * margin, margin, margin);
		} else {
			g.setColor(Color.lightGray.brighter());				
			g.drawRoundRect(xLocation, yLocation, diameter + 2 * margin, diameter + 2 * margin, margin, margin);
		}
	      
		Color pinColor = color;
		Color darkerColor;
		if (empty == true)
			darkerColor = pinColor;
		else 
			darkerColor = pinColor.darker();					
		Color whiteColor = Color.white;
		
		g.setColor(darkerColor);
		g.fillOval(xLocation + margin, yLocation + margin, diameter, diameter);
		
		g.setColor(pinColor);
		g.fillOval((int) (xLocation + diameter / 25) + margin, (int) (yLocation + diameter / 25) + margin, (int) (diameter * 0.85), (int) (diameter * 0.80));

		if (empty == false){
			g.setColor(whiteColor);
			g.fillOval((int) (xLocation + diameter * 4 / 25) + margin, (int) (yLocation + diameter / 10) + margin, (int) (diameter * 0.35), (int) (diameter * 0.30));
		}		
	}

	
	///// Standard set of methods for getting and setting data
	public void setLocation(int x, int y){
		xLocation = x;
		yLocation = y;		
	}
	
	public void setDiameter (int d) {
		diameter = d;
	}
	
	public void setNewColor (Color c) {
		color = c;
	}
	
	public void setEmptyOff(){
		empty = false;		
	}

	public void setEmptyOn(){
		empty = false;		
	}
}
