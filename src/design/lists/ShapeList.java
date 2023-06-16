package design.lists;

import java.util.ArrayList;

import design.shapes.Shape;
import view.gui.PaintCanvas;

public class ShapeList implements IList, ISubject {
	private static ArrayList<Shape> shapeList; //might need to make static
	private final ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	
	public ShapeList() {
		this.shapeList = new ArrayList<Shape>();
	}
	
	@Override
	public void registerObservers(IObserver observer) {
		observers.add(observer);		
	}
	
	@Override
	public void unregisterObserver(IObserver observer) {
		observers.remove(observer);
		
	}
	
	public void notifyObservers() {
		for(var observer : observers ) {
			observer.update();
		}
	}
	

	@Override
	public void addShape(Shape shape) {
		shapeList.add(shape);
		notifyObservers();
	}

	@Override
	public void removeShape(Shape shape) {
		shapeList.remove(shape);
		//SelectedList selectedList = MasterList.getInstance().getSelectedList();
		//selectedList.removeShape(shape);
		notifyObservers();
	}

	@Override
	public ArrayList<Shape> getIterable() {
		return this.shapeList;
		
	}
	
	public void clear() {
		this.shapeList.clear();
		notifyObservers();
	}

}
