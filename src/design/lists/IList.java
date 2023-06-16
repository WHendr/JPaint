package design.lists;

import java.util.ArrayList;

import design.shapes.Shape;

public interface IList {
	
	public void addShape(Shape shape);
	
	public void removeShape(Shape shape);
	
	public ArrayList<Shape> getIterable();
}
