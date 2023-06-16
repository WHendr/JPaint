package controller.jPaintControllerCommands;

import java.util.ArrayList;

import controller.clickHandlerCommands.ShapeFactory;
import design.lists.Clipboard;
import design.lists.MasterList;
import design.lists.PastedList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;

public class PasteCommand implements IUndoable, ICommand{
	
	private Shape newShape;
	private static PastedList pastedList;
	private static PastedList removedList;
	private Clipboard clipboard = MasterList.getInstance().getClipboard();
	private ShapeList shapeList = MasterList.getInstance().getShapeList();
	private SelectedList selectedList = MasterList.getInstance().getSelectedList();
	private int startX = 0, startY = 0, biggestY = 0, count = 0;

	@Override
	public void run() {
		CommandHistory.add(this);

		startX = 0;
		startY = 0;
		biggestY = 0;
		ArrayList<Shape> pasted =  new ArrayList<Shape>();
		
		ArrayList<Shape> list; //= pasted;
		if(pastedList != null) {
			if(pastedList.getRoot() != null) {
				ArrayList<Shape> history = pastedList.getIterable();
				
				
				//goes through whats already been pasted and increases startX and possibly startY)
				for(var shape: history) {
					startX += shape.getShapeConfig().getWidth();
					if(shape.getShapeConfig().getHeight() > biggestY) {
						biggestY = shape.getShapeConfig().getHeight();
					}
					if(startX > 1500) {
						startY += biggestY;
						startX = 0;
					}
				}
			}
		}
		
		//creates each item from selected List and then increases their 
		list = clipboard.getIterable();
		for(var shape : list) {
			
			//configures and adds new shape
			Shape newShape = shape.clone();
			Point newStart = new Point(startX, startY);
			newShape.setPoints(newStart);
			pasted.add(newShape);
			shapeList.addShape(newShape);
			
			if(shape.getShapeConfig().getHeight() > biggestY)
				biggestY = shape.getShapeConfig().getHeight();
			startX += shape.getShapeConfig().getWidth();
			if(startX > 1500) {
				startY += biggestY;
				startX = 0; 
			}
		}
		
		if(pastedList == null) {
			pastedList = new PastedList(pasted);
		}
		else
			pastedList.insert(pasted);
		selectedList.update();
	}

	@Override
	public void undo() {
		
		ArrayList<Shape> list = pastedList.delete();
		
		if(removedList == null) {
			removedList = new PastedList(list);
		}
		else
			removedList.insert(list);
		
		for(var shape: list) {
			shapeList.removeShape(shape);
		}
	}

	@Override
	public void redo() {
		ArrayList<Shape> list = removedList.delete();
		pastedList.insert(list);
		for(var shape: list) {
			shapeList.addShape(shape);
		}
	}

}
