package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.clickHandlerCommands.MoveShape;
import controller.clickHandlerCommands.SelectShape;
import controller.clickHandlerCommands.ShapeFactory;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.IShape;
import design.shapes.Point;
import design.shapes.Shape;
import design.shapes.ShapeConfig;
import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

public class ClickHandler extends MouseAdapter{
	
	private Point startPoint;
	private Point endPoint;
	private PaintCanvas paintCanvas;
	private ShapeConfig shapeConfig;
	private ShapeList shapeList;
	private Shape shape;
	private ApplicationState applicationState;
	private ShapeFactory shapeFactory = new ShapeFactory();
	private SelectedList selectedList;
	private SelectShape select;
	private MoveShape move;
	
	public ClickHandler(PaintCanvas paintCanvas, ApplicationState applicationState, ShapeList shapeList, SelectedList selectedList) {
		this.paintCanvas = paintCanvas;
		this.applicationState = applicationState;
		this.shapeList = shapeList;
		this.selectedList = selectedList;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		endPoint = new Point(e.getX(), e.getY());
		
		int height = Math.abs(endPoint.y - startPoint.y), 
				width = Math.abs(endPoint.x - startPoint.x);
		
		int x = Math.min(startPoint.x, endPoint.x);
		int y = Math.min(startPoint.y, endPoint.y);
		
		
		shapeConfig = new ShapeConfig(startPoint, endPoint, applicationState, paintCanvas);
		
		//can refactor this
		String mouse = applicationState.getActiveMouseMode().toString();
		if(mouse.equals("DRAW")) {
			shape = shapeFactory.createShape(shapeConfig);
			shape.run();
		}
		else if(mouse.equals("SELECT")) {
			select = new SelectShape(startPoint, endPoint, shapeList, selectedList, paintCanvas);
			select.run();
			
		}
		else if(mouse.equals("MOVE")) {
			move = new MoveShape(endPoint);
			move.run();
			
		}

	}

}

