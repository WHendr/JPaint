package controller.jPaintControllerCommands;

import java.util.ArrayList;

import design.lists.Clipboard;
import design.lists.GroupList;
import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Group;
import design.shapes.Shape;

public class GroupCommand implements IUndoable, ICommand{
	
	private SelectedList selectedList = MasterList.getInstance().getSelectedList();
	private ShapeList shapeList = MasterList.getInstance().getShapeList();
	
	private static GroupList groupList = MasterList.getInstance().getGroupList();
	//private static GroupList removedGroups = new ArrayList<Shape>(); //MasterList.getInstance().getRemovedGroupList();
	private static ArrayList<Shape> removedGroups = new ArrayList<Shape>();
	
	private static ArrayList<Shape> groupedShapes = new ArrayList<Shape>();
	private Group group;
	//
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//System.out.println("HELLO");
		groupedShapes.clear();
		CommandHistory.add(this);
		for(Shape shape : selectedList.getIterable()) {
			groupedShapes.add(shape);
			//shapeList.removeShape(shape);
		}
		for(Shape shape : groupedShapes) {
			shapeList.removeShape(shape);
		}
		
		selectedList.clear();
		group = new Group(groupedShapes);
		
		groupList.addShape(group);
		shapeList.addShape(group);
		selectedList.addShape(group);
	}

	@Override
	public void undo() {
		//groupList.clearGroup();
		
		Shape last = groupList.getLast();
		
		selectedList.removeShape(last);
		shapeList.removeShape(last);
		
		
		ArrayList<Shape> shapes = last.getSelf();
		for(Shape shape : shapes) {
			shapeList.addShape(shape);
			selectedList.addShape(shape);
			//System.out.println("help: " +shape);
		}
		removedGroups.add(last);
		//removedGroups.addShape(last);
		//groupList.removeShape(last);
		
	}

	@Override
	public void redo() {
		
		Shape last = removedGroups.get(removedGroups.size() - 1);
		ArrayList<Shape> shapes = last.getSelf();
		
		for(Shape shape : shapes) {
			shapeList.removeShape(shape);
			selectedList.removeShape(shape);
		}
		
		shapeList.addShape(last);
		selectedList.addShape(last);
		
		groupList.addShape(last);
		removedGroups.remove(last);
		//removedGroups.removeShape(last);

		
	}
	

}
