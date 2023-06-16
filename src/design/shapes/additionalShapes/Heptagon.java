package design.shapes.additionalShapes;

import java.awt.Color;
import java.awt.Graphics2D;

import design.interfaces.IPrototype;
import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;

public class Heptagon extends Shape implements IPrototype{
	
	private int points = 7, width, height;
	private int[] xPoints = new int[points], yPoints = new int[points],
			outlineXPoints = new int[points], outlineYPoints = new int[points];

	public Heptagon(ShapeConfig shapeConfig) {
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
			xPoints[0] = start.x + (width/2);
			xPoints[1] = end.x - (width / 10);
			xPoints[2] = end.x;
			xPoints[3] = end.x - (width / 4);
			xPoints[4] = start.x + (width / 4);
			xPoints[5] = start.x;
			xPoints[6] = start.x + (width/10);
			outlineXPoints[0] = start.x + (width/2) ;
			outlineXPoints[1] = end.x - (width / 10) + super.getPadding();
			outlineXPoints[2] = end.x + super.getPadding();
			outlineXPoints[3] = end.x - (width / 4) + super.getPadding()/2;
			outlineXPoints[4] = start.x + (width / 4) - super.getPadding()/2;
			outlineXPoints[5] = start.x - super.getPadding();
			outlineXPoints[6] = start.x + (width/10) - super.getPadding();
		}
		else if(start.x > end.x) {
			xPoints[0] = end.x + (width/2);
			xPoints[1] = start.x - (width / 10);
			xPoints[2] = start.x;
			xPoints[3] = start.x - (width / 4);
			xPoints[4] = end.x + (width / 4);
			xPoints[5] = end.x;
			xPoints[6] = end.x + (width/10);
			outlineXPoints[0] = end.x + (width/2) ;
			outlineXPoints[1] = start.x - (width / 10) + super.getPadding();
			outlineXPoints[2] = start.x + super.getPadding();
			outlineXPoints[3] = start.x - (width / 4) + super.getPadding()/2;
			outlineXPoints[4] = end.x + (width / 4) - super.getPadding()/2;
			outlineXPoints[5] = end.x - super.getPadding();
			outlineXPoints[6] = end.x + (width/10) - super.getPadding();
		}
		if(start.y < end.y) {
			yPoints[0] = start.y;
			yPoints[1] = start.y + (height/10 * 2);
			yPoints[2] = end.y - (height/10 *3);
			yPoints[3] = end.y;
			yPoints[4] = end.y;
			yPoints[5] = end.y - (height/10 * 3);
			yPoints[6] = start.y + (height/10 *2);
			outlineYPoints[0] = start.y - super.getPadding();
			outlineYPoints[1] = start.y + (height/10 * 2) - super.getPadding()/2;
			outlineYPoints[2] = end.y - (height/10 *3) + super.getPadding()/2;
			outlineYPoints[3] = end.y + super.getPadding();
			outlineYPoints[4] = end.y + super.getPadding();
			outlineYPoints[5] = end.y - (height/10 * 3) + super.getPadding()/2;
			outlineYPoints[6] = start.y + (height/10 *2) - super.getPadding()/2;
		}
		else if(start.y > end.y) {
			yPoints[0] = end.y;
			yPoints[1] = end.y + (height/10 * 2);
			yPoints[2] = start.y - (height/10 *3);
			yPoints[3] = start.y;
			yPoints[4] = start.y;
			yPoints[5] = start.y - (height/10 * 3);
			yPoints[6] = end.y + (height/10 *2);
			outlineYPoints[0] = end.y - super.getPadding();
			outlineYPoints[1] = end.y + (height/10 * 2) - super.getPadding()/2;
			outlineYPoints[2] = start.y - (height/10 *3) + super.getPadding()/2;
			outlineYPoints[3] = start.y + super.getPadding();
			outlineYPoints[4] = start.y + super.getPadding();
			outlineYPoints[5] = start.y - (height/10 * 3) + super.getPadding()/2;
			outlineYPoints[6] = end.y + (height/10 *2) - super.getPadding()/2;
			
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
		return new Heptagon(this.getShapeConfig());
	}

}
