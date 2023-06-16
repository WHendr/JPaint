package controller.jPaintControllerCommands;

import java.util.ArrayList;

import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Group;
import design.shapes.Shape;

public class UnGroupCommand implements ICommand, IUndoable{
	
	private SelectedList selectedList = MasterList.getInstance().getSelectedList();
	private ShapeList shapeList = MasterList.getInstance().getShapeList();
	private static ArrayList<Shape> groupedShapes = new ArrayList<Shape>();
	private static ArrayList<Shape> unGroupedShapes = new ArrayList<Shape>();
	//private Group group;

	@Override
	public void undo() {
		for(Shape shape : unGroupedShapes) {
			shapeList.removeShape(shape);
			selectedList.removeShape(shape);
		}
		//unGroupedShapes.clear();
		for(Shape shape : groupedShapes) {
			shapeList.addShape(shape);
			selectedList.addShape(shape);
		}
	}

	@Override
	public void redo() {
		for(Shape shape : groupedShapes) {
			shapeList.removeShape(shape);
			selectedList.removeShape(shape);
		}
		for(Shape shape : unGroupedShapes) {
			shapeList.addShape(shape);
			selectedList.addShape(shape);
		}
	}

	@Override
	public void run() {
		CommandHistory.add(this);
		
		groupedShapes.clear();
		unGroupedShapes.clear();
		
		ArrayList<Shape> list = selectedList.getIterable();
		
		for(Shape shape : list) {
			if(shape.isGrouped()) {
				ArrayList<Shape> shapes = shape.getGrouped();
				for(Shape shape2 : shapes) {
					unGroupedShapes.add(shape2);
				}
				groupedShapes.add(shape);
			}
			else {
				unGroupedShapes.add(shape);
				groupedShapes.add(shape);
			}
			//shapeList.removeShape(shape);
		}
		selectedList.clear();
		for(Shape shape : groupedShapes) {
			shapeList.removeShape(shape);
		}
		
		
		for(Shape shape : unGroupedShapes) {
			shapeList.addShape(shape);
			selectedList.addShape(shape);
		}
	}

}
