package design.shapes.additionalShapes;

import java.awt.Color;
import java.awt.Graphics2D;

import design.interfaces.IPrototype;
import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;

public class Star extends Shape implements IPrototype{

	private int points = 11, width, height;
	private int[] xPoints = new int[points], yPoints = new int[points],
			outlineXPoints = new int[points], outlineYPoints = new int[points];

	
	public Star(ShapeConfig shapeConfig) {
		super(shapeConfig);
		setPoints();
	}

	@Override
	public void setPoints() {
		Point start = getStartPoint();
		Point end = getEndPoint();
		
		int x = Math.min(start.x, end.x);
		
		int x2 = Math.max(start.x, end.x);
		
		int y = Math.min(start.y, end.y);
		
		height = Math.abs(end.y - start.y);
		width = Math.abs(end.x - start.x);
		
		//System.out.println("start.x " + start.x + " start.y " + start.y + " end.x " + end.x + " end.y " + end.y +  "\n");
		
		
		int modifier;
		
		modifier = - 1;
		
		for(int i = 0; i < points; i++) {
			if(i > 5) {
				modifier = 1;
				x = x2;
			}
			if(i % 5 == 0) {
				//System.out.println(i + "\n");
				xPoints[i] = x - modifier * (width /2);
				outlineXPoints[i] = x - modifier * (width /2);
			}
			else if(i == 1 || i == 9) {
				xPoints[i] = x - modifier * (width/10 * 6 + height/20);
				outlineXPoints[i] = (x - (modifier * (width/10 * 6 + height/20))) - (modifier * super.getPadding());
			}
			else if(i == 2 || i == 8) {
				xPoints[i] = x - modifier * width;
				outlineXPoints[i] = (x - modifier * width) - (modifier * super.getPadding());
			}
			else if(i == 3 || i == 7) {
				xPoints[i] = x - modifier * (width/10 * 7);
				outlineXPoints[i] = (x - modifier * (width/10 * 7)) - (modifier * super.getPadding());
			}
			else if(i == 4 || i == 6) {
				xPoints[i] = x - modifier * (width/10 * 8);
				outlineXPoints[i] = x - modifier * (width/10 * 8)  - (modifier * super.getPadding()/2);
			}
		}
		
		for(int i = 0; i < points; i++) {
			if(i == 0 || i == 10) {
				yPoints[i] = y;
				outlineYPoints[i] = y - super.getPadding();
			}
			else if(i == 1 || i == 9) {
				yPoints[i] = y + (height/10 * 3 + height/20);
				outlineYPoints[i] = y + (height/10 * 3 + height/20) - super.getPadding()/2;
			}
			else if(i == 2 || i == 8) {
				yPoints[i] = y + (height/10 * 4 + height/20);
				outlineYPoints[i] =  y + (height/10 * 4 + height/20);
			}
			else if(i ==3 || i ==7) {
				yPoints[i] = y + (height/10 * 6 );
				outlineYPoints[i] = y + (height/10 * 6 );
			}
			else if(i == 4 || i == 6) {
				yPoints[i] = y + height;
				outlineYPoints[i] = y +  height + super.getPadding();
			}
			else if(i == 5) {
				yPoints[i] = y + (height/10 * 7);
				outlineYPoints[i] = y + + (height/10 * 7) +  super.getPadding();
				
			}
			
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
		// TODO Auto-generated method stub
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
		return new Star(this.getShapeConfig());
	}

}
