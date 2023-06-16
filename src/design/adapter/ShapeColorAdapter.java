package design.adapter;

import java.awt.Color;

import design.interfaces.IAdapter;
import model.ShapeColor;

public class ShapeColorAdapter implements IAdapter{

	Color color;
	
	public ShapeColorAdapter(ShapeColor shapeColor){
		String color = adapt(shapeColor);
		setColor(color);
	}

	@Override
	public <T> String adapt(Object generic) {
		return generic.toString();
	}

	
	private void setColor(String color) {
		Color result;
     	if(color== "BLACK")
    		this.color = Color.BLACK;
    	if(color == "BLUE")
    		this.color = Color.BLUE;
    	if(color == "CYAN")
    		this.color = Color.CYAN;
    	if(color == "DARK_GRAY")
    		this.color = Color.DARK_GRAY;
    	if(color == "GRAY")
    		this.color = Color.GRAY;
    	if(color == "GREEN")
    		this.color = Color.GREEN;
    	if(color == "LIGHT_GRAY")
    		this.color = Color.LIGHT_GRAY;
    	if(color == "MAGENTA")
    		this.color= Color.MAGENTA;
    	if(color == "ORANGE")
    		this.color = Color.ORANGE;
    	if(color == "PINK")
    		this.color = Color.PINK;
    	if(color == "RED")
    		this.color = Color.RED;
    	if(color == "WHITE")
    		this.color = Color.WHITE;
    	if(color == "YELLOW")
    		this.color = Color.YELLOW;
    	
	}
	
	public Color getColor() {
		return this.color;
	}
	

}
