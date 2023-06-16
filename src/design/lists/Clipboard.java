package design.lists;

import java.util.ArrayList;

import design.shapes.Shape;

public class Clipboard implements IList{
	private static ArrayList<Shape> clipboard;
	
	public Clipboard(){
		this.clipboard = new ArrayList<Shape>();
	}
	
	public void addShape(Shape shape) {
		clipboard.add(shape);
	}
	
	public void removeShape(Shape shape) {
		clipboard.remove(shape);
	}

	@Override
	public ArrayList<Shape> getIterable() {
		return this.clipboard;
	}
	
	public void clear() {
		this.clipboard.clear();
	}
}
