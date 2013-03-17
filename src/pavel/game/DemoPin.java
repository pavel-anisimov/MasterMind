// initial class that supposed to draw a demonstrational pin
// for picking the color.

package pavel.game;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;

@SuppressWarnings("serial")
public class DemoPin extends JPanel{
	private int radius;
	private int xLocation;
	private int yLocation;
	private final int margin = 5;
	private Color color;
	
	public DemoPin(){
		
	}
	
	// reads the data into the class
	public void SetDemoPin( int rd, int x, int y, Color c){		
		radius = rd;
		xLocation = x + margin;
		yLocation = y + margin;
		color = c;
	}
	
	// this class in general is similar to CirclePin.java
	// it draws objects similary.
	public void paintComponent(Graphics g){
		super.paintComponent (g);

		g.setColor(Color.lightGray.brighter());
		g.drawRoundRect(xLocation, yLocation, radius * 13 / 10 + 2 * margin, radius + 2 * margin, 2 * margin, 2 * margin);
		
		Color pinColor = color;
		Color darkerColor = pinColor.darker();					
		Color whiteColor = Color.white;
		
		g.setColor(darkerColor);
		g.fillOval(xLocation + margin, yLocation + margin, radius, radius);
		
		g.setColor(pinColor);
		g.fillOval((int) (xLocation + radius / 25) + margin, (int) (yLocation + radius / 25) + margin, (int) (radius * 0.85), (int) (radius * 0.80));
		
		g.setColor(whiteColor);
		g.fillOval((int) (xLocation + radius / 5) + margin, (int) (yLocation + radius / 10) + margin, (int) (radius * 0.20), (int) (radius * 0.15));
		
		g.setColor(Color.lightGray);
		g.fillRect(xLocation + radius / 2 + margin, yLocation + margin, radius / 2 , radius);

		g.setColor(darkerColor);
		g.fillRect(xLocation + radius / 2 + margin, yLocation + radius * 3 / 8 + margin, radius * 4 / 5, radius / 4);
		g.setColor(pinColor);
		g.fillRect(xLocation + radius / 2 + 10 + margin, yLocation + radius * 3 / 8 + 1 + margin, radius * 4 / 5 - 11, radius / 4 - 6);

	}
	
	public void changeColor(Color c){
		this.color = c;
		repaint(getBounds());
	}

	public Color getColor(){
		return color;
	}
}
