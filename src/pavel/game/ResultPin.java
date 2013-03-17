
// class that draws result pins n the board
// the class is very similar to other drawing classes used in this project
package pavel.game;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;

@SuppressWarnings("serial")
public class ResultPin extends JPanel{
	private final int margin = 2;
	private int diameter;
	private int xLocation;
	private int yLocation;
	private Color insideColor;
	private Color outsideColor;
	private int type;

	
	// constructor draws color pins
	public void setPin( int d, int x, int y, int t){		
		diameter = d;
		xLocation = x + margin;
		yLocation = y + margin;
		type = t;
	}
		
	public void setLocation(int x, int y){
		xLocation = x;
		yLocation = y;		
	}
	
	public void setDiameter (int d) {
		diameter = d;
	}

	public void setType (int t) {
		type = t;
	}	
	
	// method for drawing a pin
	public void paintComponent(Graphics g){

		super.paintComponent(g);

		g.setColor(Color.lightGray);
		
		if (type == 0) {
			insideColor = Color.darkGray;
			outsideColor = Color.black;			
		} else if (type == 1) {
			insideColor = Color.lightGray;
			outsideColor = Color.gray;						
		} else if (type == 2) {
			insideColor = Color.white;
			outsideColor = Color.white;						
		} else {
			insideColor = Color.darkGray;
			outsideColor = Color.white;						
		}
				    
		if (type < 3 ) {
			g.setColor(outsideColor);
			g.fillOval(xLocation + margin, yLocation + margin, diameter, diameter);
		}

		g.setColor(insideColor);
		g.fillOval(margin + xLocation + (int) (diameter * .3), margin + yLocation + (int) (diameter * .3), (int) (diameter * 0.4), (int) (diameter * 0.4));

	}
}
