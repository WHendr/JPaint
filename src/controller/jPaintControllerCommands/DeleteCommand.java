	package controller.jPaintControllerCommands;

import java.util.ArrayList;

import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Shape;

public class DeleteCommand implements IUndoable, ICommand{

	private ArrayList<Shape> deletedList = new ArrayList<Shape>();
	private SelectedList selectedList = MasterList.getInstance().getSelectedList();
	private ShapeList shapeList = MasterList.getInstance().getShapeList();
	
	@Override
	public void run() {
		CommandHistory.add(this);
		
		ArrayList<Shape> list = selectedList.getIterable();
		deletedList.clear();
		for(var shape : list) {
			deletedList.add(shape);
			shapeList.removeShape(shape);
		}
		selectedList.clear();
		
		
	}

	@Override
	public void undo() {
		ArrayList<Shape> list = deletedList;
		
		for(var shape : list) {
			shapeList.addShape(shape);
		}
	}

	@Override
	public void redo() {
		ArrayList<Shape> list = deletedList;
		
		for(var shape : list) {
			shapeList.removeShape(shape);
		}
	}

}
