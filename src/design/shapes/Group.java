package design.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import design.lists.GroupList;
import design.lists.PastedList;

public class Group extends Shape{

	private int minX = 3000, minY = 3000, maxX = 0, maxY = 0;
	private int points = 4, padding = 10, height, width;
	private int[] outlineXPoints = new int[points], outlineYPoints = new int[points];

	private ArrayList<Shape> groupedShapes;
	//private ArrayList<Shape> selfShape;
	private ArrayList<Shape> clonedShapes = new ArrayList<Shape>();
	
	public Group(ArrayList<Shape> shapes) {
		
		this.setAsGrouped();
		this.setGrouped(shapes);
		this.groupedShapes = this.getGrouped();
		this.setSelf(shapes);
		this.setPoints();
		
	}
	
	@Override
	public void setPoints() {
		minX = 3000;
		minY = 3000;
		maxX = 0;
		maxY = 0;

		
		for(Shape shape : groupedShapes) {
			Point startPoint = shape.getStartPoint();
			Point endPoint = shape.getEndPoint();
			
			int currMinX = Math.min(startPoint.x, endPoint.x);
			int currMinY = Math.min(startPoint.y, endPoint.y);
			int currMaxX = Math.max(startPoint.x, endPoint.x);
			int currMaxY = Math.max(startPoint.y, endPoint.y);
			
			if(minX > currMinX) {
				this.minX = currMinX;
			}
			if(minY > currMinY) {
				this.minY = currMinY;
			}
			if(maxX < currMaxX) {
				this.maxX = currMaxX;
			}
			if(maxY < currMaxY) {
				this.maxY = currMaxY;
			}
			
			
			
			for(int i = 0; i < points; i++) {
				if(i == 0 || i == points - 1) {
					this. outlineXPoints[i] = minX - padding;
				}
				else {
					this. outlineXPoints[i] = maxX + padding;
				}
			}
			
			for(int i = 0; i < points; i++) {
				if(i < 2) {
					this. outlineYPoints[i] = minY - padding;
				}
				else {
					this. outlineYPoints[i] = maxY + padding;
				}
			}
			
		}
		Point newStart = new Point(minX, minY);
		Point newEnd = new Point(maxX, maxY);
		
		this.setNewPoints(newStart,newEnd);
		ShapeConfig shapeConfig = new ShapeConfig(newStart, newEnd, null, getPaintCanvas());
		this.setShapeConfig(shapeConfig);
		width = Math.abs(outlineXPoints[0] - outlineXPoints[1]); 
		height = Math.abs(outlineYPoints[0] - outlineYPoints[2]);
	}

	@Override
	public void setPoints(Point changedPoint) {
		minX = 3000;
		minY = 3000;
		maxX = 0;
		maxY = 0;
		
		Point startPoint = this.getStartPoint();

		for(Shape shape : groupedShapes) {
			Point diff = shape.getStartPoint();
			
			int diffX = Math.abs(startPoint.x - diff.x);
			int diffY = Math.abs(startPoint.y - diff.y);
			
			Point newPoint = new Point(changedPoint.x + diffX, changedPoint.y + diffY);
			
			shape.setPoints(newPoint);
			
		}
		
		
		width = Math.abs(outlineXPoints[0] - outlineXPoints[1]); 
		height = Math.abs(outlineYPoints[0] - outlineYPoints[2]);
		
		Point newEnd = new Point(changedPoint.x + width, changedPoint.y + height);
		this.setNewPoints(changedPoint, newEnd);
		
		setPoints();
		
	}

	@Override
	public void drawShape() {
		Graphics2D g2d = super.getPaintCanvas().getGraphics2D();
		for(Shape shape : groupedShapes) {
			shape.drawShape();
		}	

		if(this.isSelected()) {
			g2d.setStroke(super.getStroke());
			g2d.setColor(Color.BLACK);
			g2d.drawPolygon(outlineXPoints, outlineYPoints, points);
		}
	}

	@Override
	public Shape clone() {
		for(Shape shape : groupedShapes) {
			Shape clone = shape.clone();
			clonedShapes.add(clone);
		}
		return new Group(clonedShapes);
	}

}
