package design.shapes.additionalShapes;

import java.awt.Color;
import java.awt.Graphics2D;

import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;

public class PacMan extends Shape{
	
	private int points = 2 , width, height,x,y,facing = 45, modifier = 1;
	private int[] outlineXPoints = new int[points], outlineYPoints = new int[points + 1];

	
	public PacMan(ShapeConfig shapeConfig) {
		super(shapeConfig);
		setPoints();
	}
	

	@Override
	public void setPoints() {
		Point start = getStartPoint();
		Point end = getEndPoint();
		
		x = Math.min(start.x, end.x);
		y = Math.min(start.y, end.y);
		
		height = Math.abs(end.y - start.y);
		width = Math.abs(end.x - start.x);
		
		if(start.x > end.x) {
			facing = 225;
			modifier = - 1;
		}
		
		outlineXPoints[0] = end.x - modifier * (width/10);
		outlineXPoints[1] = end.x - modifier * ((width/ 10) * 4 + (width/20)) ;
		outlineYPoints[0] = y +  (height/ 10) *2 - height/20;
		outlineYPoints[1] = y + height/10 * 5;
		outlineYPoints[2] = y + height/10 * 8 + height/20;
	}

	@Override
	public void setPoints(Point changedPoint) {
		Point newEnd = new Point(changedPoint.x + this.width, changedPoint.y + this.height);
		
		this.setNewPoints(changedPoint, newEnd);
		
		setPoints();
		
		
	}

	@Override
	public void drawShape() {
		Graphics2D g2d = super.getShapeConfig().getPaintCanvas().getGraphics2D();
		String shapeShading = super.getShapeShading();		
		g2d.setColor(super.getPrimaryColor());
		if(shapeShading.equals("FILLED_IN")) {
			//g2d.fillPolygon(xPoints, yPoints, points);
			g2d.fillArc(x, y, width, height, facing, 270);
		}
		else if(shapeShading.equals("OUTLINE")) {
			//g2d.drawPolygon(xPoints, yPoints, points);
			g2d.drawArc(x, y, width, height, facing, 270);
			
		}
		else if(shapeShading.equals("OUTLINE_AND_FILLED_IN")) {
			//g2d.fillPolygon(xPoints, yPoints, points);
			g2d.fillArc(x, y, width, height, facing, 270);
			g2d.setColor(getSecondaryColor());
			g2d.drawArc(x, y, width, height, facing, 270);
		}
		if(this.isSelected()) {
	        g2d.setStroke(super.getStroke());
	        g2d.setColor(Color.BLACK);
			//g2d.drawPolygon(outlineXPoints, outlineYPoints, points);
	        g2d.drawArc(x -super.getPadding(), y -super.getPadding(), width + super.getPadding() * 2, height + super.getPadding() * 2, facing, 270);
	        g2d.drawPolygon(outlineXPoints, outlineYPoints, points);
	        g2d.drawLine(outlineXPoints[0], outlineYPoints[0], outlineXPoints[1], outlineYPoints[1]);
	        g2d.drawLine(outlineXPoints[1], outlineYPoints[1], outlineXPoints[0], outlineYPoints[2]);
			
		}
		
	}

	@Override
	public Shape clone() {
		return new PacMan(this.getShapeConfig());
	}

}
