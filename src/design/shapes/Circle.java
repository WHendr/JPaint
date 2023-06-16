package design.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import design.interfaces.IPrototype;
import design.lists.ShapeList;
import view.gui.PaintCanvas;

public class Circle extends Shape implements IPrototype{

	private int x, y, width, height;
	
	public Circle(ShapeConfig shapeConfig) {
		super(shapeConfig);
		setPoints();

	}

	@Override
	public void drawShape() {
		Graphics2D g2d = super.getShapeConfig().getPaintCanvas().getGraphics2D();
		String shapeShading = super.getShapeShading();		
		g2d.setColor(super.getPrimaryColor());
		if(shapeShading.equals("FILLED_IN")) {
			g2d.fillOval(x, y, width, height);
		}
		else if(shapeShading.equals("OUTLINE")) {
			g2d.drawOval(x, y, width, height);
			
		}
		else if(shapeShading.equals("OUTLINE_AND_FILLED_IN")) {
			g2d.fillOval(x, y, width, height);
			g2d.setColor(getSecondaryColor());
			g2d.drawOval(x, y, width, height);
		}
		if(this.isSelected()) {
	        g2d.setStroke(super.getStroke());
	        g2d.setColor(Color.BLACK);
			g2d.drawOval(x - super.getPadding(), y - super.getPadding(), width +  2 * super.getPadding(), height + 2 * super.getPadding());		
		}
		
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
		Point newEnd = new Point(changedPoint.x + this.width, changedPoint.y + this.height);
		
		this.setNewPoints(changedPoint, newEnd);
		
		setPoints();
		
		
	}

	@Override
	public Shape clone() {
		return new Circle(this.getShapeConfig());
	}

}
