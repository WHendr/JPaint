package design.lists;

import java.util.ArrayList;

import design.shapes.Group;
import design.shapes.Shape;

public interface INodeList {
	
	
	public Group delete();
	
	public ArrayList<Shape> getIterable();

	public void insert(ArrayList<Shape> d);

}
