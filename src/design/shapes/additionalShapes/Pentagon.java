package design.shapes.additionalShapes;

import java.awt.Color;
import java.awt.Graphics2D;

import design.interfaces.IPrototype;
import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;

public class Pentagon extends Shape implements IPrototype{
	
	private int points = 5, width, height;
	private int[] xPoints = new int[points], yPoints = new int[points],
			outlineXPoints = new int[points], outlineYPoints = new int[points];

	public Pentagon(ShapeConfig shapeConfig) {
		super(shapeConfig);
		setPoints();
	}

	@Override
	public void setPoints() {
		Point start = getStartPoint();
		Point end = getEndPoint();
		height = Math.abs(end.y - start.y);
		width = Math.abs(end.x - start.x);
		
		if(start.x < end.x) {
			xPoints[0] = start.x;
			xPoints[1] = start.x + (width / 2);
			xPoints[2] = end.x;
			xPoints[3] = end.x - (width / 4);
			xPoints[4] = start.x + (width/ 4);
			outlineXPoints[0] = start.x - super.getPadding();
			outlineXPoints[1] = start.x + (width / 2);
			outlineXPoints[2] = end.x + super.getPadding();
			outlineXPoints[3] = end.x - (width / 4) + super.getPadding();
			outlineXPoints[4] = start.x + (width/ 4) - super.getPadding();
		}
		if(start.x > end.x) {
			xPoints[0] = end.x;
			xPoints[1] = end.x + (width / 2);
			xPoints[2] = start.x;
			xPoints[3] = start.x - (width / 4);
			xPoints[4] = end.x + (width/ 4);
			outlineXPoints[0] = end.x - super.getPadding();
			outlineXPoints[1] = end.x + (width / 2);
			outlineXPoints[2] = start.x + super.getPadding();
			outlineXPoints[3] = start.x - (width / 4) + super.getPadding();
			outlineXPoints[4] = end.x + (width/ 4) - super.getPadding();
		}
		
		if(start.y < end.y) {
			yPoints[0] = start.y + (height / 3);
			yPoints[1] = start.y;
			yPoints[2] = start.y + (height / 3);
			yPoints[3] = end.y;
			yPoints[4] = end.y;
			outlineYPoints[0] = start.y + (height / 3);
			outlineYPoints[1] = start.y - super.getPadding() ;
			outlineYPoints[2] = start.y + (height / 3);
			outlineYPoints[3] = end.y + super.getPadding();
			outlineYPoints[4] = end.y + super.getPadding();

		}
		
		if(start.y > end.y) {
			yPoints[0] = end.y + (height / 3);
			yPoints[1] = end.y;
			yPoints[2] = end.y + (height / 3);
			yPoints[3] = start.y;
			yPoints[4] = start.y;
			outlineYPoints[0] = end.y + (height / 3);
			outlineYPoints[1] = end.y - super.getPadding() ;
			outlineYPoints[2] = end.y + (height / 3);
			outlineYPoints[3] = start.y + super.getPadding();
			outlineYPoints[4] = start.y + super.getPadding();
		}
		
	}

	@Override
	public void setPoints(Point changedPoint) {
		Point newEnd = new Point(changedPoint.x + width, changedPoint.y + height);
		this.setNewPoints(changedPoint, newEnd);
		
		setPoints();
	}

	@Override
	public void drawShape() {
		Graphics2D g2d = super.getShapeConfig().getPaintCanvas().getGraphics2D();
		String shapeShading = super.getShapeShading();		
		g2d.setColor(super.getPrimaryColor());
		if(shapeShading.equals("FILLED_IN")) {
			g2d.fillPolygon(xPoints, yPoints, points);
		}
		else if(shapeShading.equals("OUTLINE")) {
			g2d.drawPolygon(xPoints, yPoints, points);
			
		}
		else if(shapeShading.equals("OUTLINE_AND_FILLED_IN")) {
			g2d.fillPolygon(xPoints, yPoints, points);
			g2d.setColor(getSecondaryColor());
			g2d.drawPolygon(xPoints, yPoints, points);
		}
		if(this.isSelected()) {
	        g2d.setStroke(super.getStroke());
	        g2d.setColor(Color.BLACK);
			g2d.drawPolygon(outlineXPoints, outlineYPoints, points);
			
		}
		
	}

	@Override
	public Shape clone() {
		return new Pentagon(this.getShapeConfig());
	}

}
