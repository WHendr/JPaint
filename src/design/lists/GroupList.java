package design.lists;

import java.util.ArrayList;
import java.util.Iterator;

import design.lists.PastedList.Node;
import design.shapes.Shape;

public class GroupList implements IList{
	private static ArrayList<Shape> groupList;
	
	/*
	public SelectedList(ArrayList<Shape> selectedList) {
		this.selectedList = selectedList;
	}
	*/
	public GroupList() {
		this.groupList = new ArrayList<Shape>();
	}
	
	public void addShape(Shape shape) {
		groupList.add(shape);
	}
	
	public void removeShape(Shape shape) {
		groupList.remove(shape);
	}

	public void clear() {
		this.groupList.clear();	
	}

	@Override
	public ArrayList<Shape> getIterable() {
		return this.groupList;
	}
	
	
	public Shape getLast() {
		Shape last = null;
		//System.out.println(groupList.size());
		/*
		if(groupList != null) {
			int i = -1;
			for(Shape shape : groupList) {
				last = shape;
				i++;
				//System.out.println(shape);
			}
			groupList.remove(i);
		}
		*/
		for(Shape shape : groupList) {
			last = shape;
		}
		groupList.remove(last);
		//System.out.println(groupList.size());
		return last;
	}
	
	public void clearGroup() {
		ArrayList<Shape> g = new ArrayList<Shape>();
		
		for(Shape shape : groupList) {
			System.out.println(shape);
			g.add(shape);
		}
		//this.clear();
		//System.out.println("\nHello\n");
		for(Shape shape : g) {
			groupList.remove(0);
			System.out.println(shape);
		}
		
		for(Shape shape : groupList) {
			System.out.println(shape);
		}
	}
	
}