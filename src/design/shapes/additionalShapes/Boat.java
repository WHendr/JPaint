package design.shapes.additionalShapes;

import java.awt.Color;
import java.awt.Graphics2D;

import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;

public class Boat extends Shape{
	

	private int points = 11, width, height;
	private int[] xPoints = new int[points], yPoints = new int[points],
			outlineXPoints = new int[points], outlineYPoints = new int[points];


	public Boat(ShapeConfig shapeConfig) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPoints() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPoints(Point changedPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawShape() {
		Graphics2D g2d = super.getShapeConfig().getPaintCanvas().getGraphics2D();
		String shapeShading = super.getShapeShading();		
		g2d.setColor(super.getPrimaryColor());
		if(shapeShading.equals("FILLED_IN")) {
			//g2d.fillPolygon(xPoints, yPoints, points);
			g2d.fillArc(300,300 , 100, 100, 270, 270);
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
		// TODO Auto-generated method stub
		return null;
	}

}
