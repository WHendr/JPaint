package design.lists;

import java.util.ArrayList;

import design.shapes.Shape;
import view.gui.PaintCanvas;

public class SelectedList implements IList, ISubject{
	private static ArrayList<Shape> selectedList;
	private final ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	/*
	public SelectedList(ArrayList<Shape> selectedList) {
		this.selectedList = selectedList;
	}
	*/
	public SelectedList() {
		this.selectedList = new ArrayList<Shape>();
	}
	
	public void addShape(Shape shape) {
		selectedList.add(shape);
		shape.select();
		notifyObservers();
	}
	
	public void removeShape(Shape shape) {
		selectedList.remove(shape);
		shape.unSelect();
		notifyObservers();
	}
	
	public ArrayList<Shape> getSelectedList() {
		return this.selectedList;
	}

	public void clear() {
		for(Shape shape : selectedList) {
			shape.unSelect();
		}
		this.selectedList.clear();
		notifyObservers();
		
	}

	@Override
	public ArrayList<Shape> getIterable() {
		return this.selectedList;
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
	

	public void update() {
		notifyObservers();
	}
	/*
	public boolean isEmpty() {
		boolean isEmpty = selectedList.isEmpty();
		return isEmpty;
	}
	*/
}
