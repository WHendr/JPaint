package design.shapes;

import java.awt.Color;

public interface IShape {
	
	public int getX();
	
	public int getY();
	
	public int getWidth();
	
	public int getHeight();
	
	public void drawShape();

	public Color getPrimaryColor();
}
