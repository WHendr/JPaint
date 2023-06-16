package controller.clickHandlerCommands;

import java.util.ArrayList;

import controller.jPaintControllerCommands.ICommand;
import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Point;
import design.shapes.Shape;
import view.gui.PaintCanvas;

public class SelectShape implements ICommand {
	
	private Point startPoint, endPoint;
	private SelectedList selectedList;
	private ShapeList shapeList;
	private int xMin, xMax, yMin, yMax;
	
	
	public SelectShape(Point startPoint, Point endPoint, ShapeList shapeList, SelectedList selectedList, PaintCanvas paintCanvas ) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.shapeList = shapeList;
		this.selectedList = selectedList;
		setPoints();
	}
	
	private void select() {
		
		ArrayList<Shape> list = shapeList.getIterable(); 
		
		for(var shape : list) {
			int x = Math.min(shape.getStartPoint().x, shape.getEndPoint().x);
			int y = Math.min(shape.getStartPoint().y, shape.getEndPoint().y); 
			int height = Math.abs(shape.getStartPoint().y - shape.getEndPoint().y);
			int width = Math.abs(shape.getStartPoint().x - shape.getEndPoint().x);
			
			if( x < xMax &&
				x + width > xMin && 
				y < yMax &&
				y + height > yMin
					) {
				//shape.select();
				selectedList.addShape(shape);
				//shape.drawBorder();
			}
			else {
				//shape.unSelect();
				this.selectedList.removeShape(shape);
				
				//shape.eraseBorder();
			}
			//selectedList.update();
		}
		
	}	

	@Override
	public void run() {
		select();
	}

	public void setPoints() {
		this.xMin = Math.min(startPoint.x, endPoint.x);
		this.xMax = Math.max(startPoint.x, endPoint.x);
		this.yMin = Math.min(startPoint.y, endPoint.y);
		this.yMax = Math.max(startPoint.y, endPoint.y);
	}
}
