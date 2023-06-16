package controller.clickHandlerCommands;

import java.util.ArrayList;

import controller.jPaintControllerCommands.CommandHistory;
import controller.jPaintControllerCommands.ICommand;
import controller.jPaintControllerCommands.IUndoable;
import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Point;
import design.shapes.Shape;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

public class MoveShape implements ICommand, IUndoable {
	
	private Point movePoint;
	private ShapeList shapeList = MasterList.getInstance().getShapeList();
	private SelectedList selectedList = MasterList.getInstance().getSelectedList();
	
	private ArrayList<Shape> movedShape = new ArrayList<Shape>();
	
	public MoveShape(Point movePoint) {
		this.movePoint = movePoint;

	}

	private void move(Point movePoint) {
		ArrayList<Shape> selected = selectedList.getIterable();
		
		for(var shape : selected) {
			shape.addPreviousPoint();
			
			movedShape.add(shape);
			shapeList.removeShape(shape);
			shape.setPoints(movePoint);
			shapeList.addShape(shape);
			movePoint = new Point(shape.getEndPoint().x, movePoint.y);
		}
		selectedList.update();
	}
	
	@Override
	public void undo() {
		ArrayList<Shape> selected = selectedList.getIterable();
		
			for(var shape :  selected) {		
				shape.addNewPoint();
				shapeList.removeShape(shape);
				Point oldPoint = shape.getPreviousPoint();			
				shape.setPoints(oldPoint);
				shapeList.addShape(shape);
		}
		selectedList.update();
	}

	@Override
	public void redo() {
		ArrayList<Shape> selected = selectedList.getIterable();
		
		for(var shape: selected) {
			shape.addPreviousPoint();
			shapeList.removeShape(shape);
			Point newPoint = shape.getNewPoint();
			shape.setPoints(newPoint);
			shapeList.addShape(shape);
		}
		selectedList.update();
	}

	@Override
	public void run() {
		move(movePoint);
		CommandHistory.add(this);
		
	}
	
	private Point movedEndPoint(Point movePoint, Point startPoint, Point endPoint) {
		int width = Math.abs(startPoint.x - endPoint.x);  
		int height = Math.abs(startPoint.y - endPoint.y);  
		
		Point newEndPoint = new Point(movePoint.x + width, movePoint.y + height);
		
		return newEndPoint;
		
	}
	
	
}
