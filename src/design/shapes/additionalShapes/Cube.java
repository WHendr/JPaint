package design.shapes.additionalShapes;

import java.awt.Color;
import java.awt.Graphics2D;

import design.interfaces.IPrototype;
import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;

public class Cube extends Shape implements IPrototype {
	
	private int x, y, width, height;
	
	public Cube(ShapeConfig shapeConfig) {
		super(shapeConfig);
		setPoints();
	}

	@Override
	public void setPoints() {
		Point startPoint = getStartPoint();
		Point endPoint = getEndPoint();
		
		this.height = Math.abs(endPoint.y - startPoint.y);
		this.width = Math.abs(endPoint.x - startPoint.x);
		
		this.x = Math.min(startPoint.x, endPoint.x);
		this.y = Math.min(startPoint.y, endPoint.y);
	}

	@Override
	public void setPoints(Point changedPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawShape() {
		// TODO Auto-generated method stub
		Graphics2D g2d = super.getShapeConfig().getPaintCanvas().getGraphics2D();
		String shapeShading = super.getShapeShading();		
		g2d.setColor(super.getPrimaryColor());
		if(shapeShading.equals("FILLED_IN")) {
			g2d.fill3DRect(x, y, width, height, false);
		}
		else if(shapeShading.equals("OUTLINE")) {
			g2d.draw3DRect(x, y, width, height, true);
		}
		else if(shapeShading.equals("OUTLINE_AND_FILLED_IN")) {
			g2d.fill3DRect(x, y, width, height, true);
			g2d.setColor(getSecondaryColor());
			g2d.draw3DRect(x, y, width, height, true);
		}
		if(this.isSelected()) {
	        g2d.setStroke(super.getStroke());
	        g2d.setColor(Color.BLACK);
	        g2d.draw3DRect(x, y, width, height, true);
		}
		
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
