package design.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import design.interfaces.IPrototype;
import design.lists.ShapeList;
import view.gui.PaintCanvas;

public class Triangle extends Shape implements IPrototype{
	
	private PaintCanvas paintCanvas;
	private int points = 3;
	private int[] xPoints = new int[points], yPoints = new int[points],
			outlineXPoints = new int[points], outlineYPoints = new int[points];;
	

	public Triangle(ShapeConfig shapeConfig) {
		super(shapeConfig);
		setPoints();
	}

	@Override
	public void drawShape() {
		Graphics2D g2d = super.getShapeConfig().getPaintCanvas().getGraphics2D();
		String shapeShading = super.getShapeShading();		
		g2d.setColor(super.getPrimaryColor());
		if(shapeShading.equals("FILLED_IN")) {
			g2d.fillPolygon(xPoints, yPoints, 3);
		}
		else if(shapeShading.equals("OUTLINE")) {
			g2d.drawPolygon(xPoints, yPoints, 3);
		}
		else if(shapeShading.equals("OUTLINE_AND_FILLED_IN")) {
			g2d.fillPolygon(xPoints, yPoints, 3);
			g2d.setColor(getSecondaryColor());
			g2d.drawPolygon(xPoints, yPoints, 3);
		}
		if(this.isSelected()) {
	        g2d.setStroke(super.getStroke());
	        g2d.setColor(Color.BLACK);
			g2d.drawPolygon(outlineXPoints, outlineYPoints, points);
			
		}
	}

	@Override
	public void setPoints() {
		Point start = getStartPoint();
		Point end = getEndPoint();
		int half = points / 2;
		
		//sets xPoints
		for(int i = 0; i < points; i++) {
			if(i == 0 || i == points - 1) {
				this.xPoints[i] = start.x;
			}
			else {
				this.xPoints[i] = end.x;
			}
		}
		
		//sets yPoints
		for(int i = 0; i < points; i++) {
			if(i < half) {
				this.yPoints[i] = start.y;
			}
			else {
				this.yPoints[i] = end.y;
			}
		}
		
		//sets outlineXPoints
		if(xPoints[0] < xPoints[1]) {
			for(int i = 0; i < points; i++) {
				if(i == 0 || i == points - 1) {
					this.outlineXPoints[i] = start.x - super.getPadding();
				}
				else {
					this.outlineXPoints[i] = end.x + 2 * super.getPadding();
				}
			}
		}
		else {
			for(int i = 0; i < points; i++) {
				if(i == 0 || i == points - 1) {
					this.outlineXPoints[i] = start.x + 2 * super.getPadding();
				}
				else {
					this.outlineXPoints[i] = end.x - super.getPadding();
				}
			}
		}
		
		//sets outlineYPoints
		if(yPoints[0] < yPoints[1]) {
			for(int i = 0; i < points; i++) {
				if(i < half) {
					this.outlineYPoints[i] = start.y - 2 * super.getPadding();
				}
				else {
					this.outlineYPoints[i] = end.y + super.getPadding();
				}
			}
		}
		else {
			for(int i = 0; i < points; i++) {
				if(i < half) {
					this.outlineYPoints[i] = start.y + super.getPadding();

				}
				else {
					this.outlineYPoints[i] = end.y - 2 * super.getPadding();
				}
			}
		}
	}

	@Override
	public void setPoints(Point changedPoint) {
		int width = Math.abs(xPoints[0] - xPoints[1]); 
		int height = Math.abs(yPoints[0] - yPoints[1]);
		Point newEnd = new Point(changedPoint.x + width, changedPoint.y + height);		
		this.setNewPoints(changedPoint, newEnd);
		setPoints();
	
	}

	@Override
	public Shape clone() {
		return new Triangle(this.getShapeConfig());
	}
}
