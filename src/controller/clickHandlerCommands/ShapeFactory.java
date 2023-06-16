package controller.clickHandlerCommands;

import controller.jPaintControllerCommands.ICommand;
import design.lists.MasterList;
import design.lists.ShapeList;
import design.shapes.Circle;
import design.shapes.Rectangle;
import design.shapes.Shape;
import design.shapes.ShapeConfig;
import design.shapes.Triangle;
import design.shapes.additionalShapes.Boat;
import design.shapes.additionalShapes.Cube;
import design.shapes.additionalShapes.Heptagon;
import design.shapes.additionalShapes.Hexagon;
import design.shapes.additionalShapes.Octagon;
import design.shapes.additionalShapes.PacMan;
import design.shapes.additionalShapes.Pentagon;
import design.shapes.additionalShapes.Star;
import model.ShapeType;
import view.gui.PaintCanvas;

public class ShapeFactory implements AbstractShapeFactory{

	private Shape shape;

	@Override
	public Shape createShape(ShapeConfig shapeConfig) {
		String shapeType = shapeConfig.getShapeType();
		if(shapeType.equals("RECTANGLE")) {
			shape = new Rectangle(shapeConfig);
			return shape;
		}
		else if(shapeType.equals("ELLIPSE")) {
			shape = new Circle(shapeConfig);
			return shape;
			
		}
		else if(shapeType.equals("TRIANGLE")) {
			shape = new Triangle(shapeConfig);
			return shape;
		}
		else if(shapeType.equals("PENTAGON")) {
			shape = new Pentagon(shapeConfig);
			return shape;
		}
		else if(shapeType.equals("HEXAGON")) {
			shape = new Hexagon(shapeConfig);
			return shape;
		}
		else if(shapeType.equals("HEPTAGON")) {
			shape = new Heptagon(shapeConfig);
			return shape;
		}
		else if(shapeType.equals("OCTAGON")) {
			shape = new Octagon(shapeConfig);
			return shape;
		}
		else if(shapeType.equals("STAR")) {
			shape = new Star(shapeConfig);
			return shape;
		}
		else if(shapeType.equals("CUBE")) {
			shape = new Cube(shapeConfig);
			return shape;
		}
		/*
		else if(shapeType.equals("BOAT")) {
			shape = new Boat(shapeConfig);
			return shape;
		}
		*/
		else if(shapeType.equals("PACMAN")) {
			shape = new PacMan(shapeConfig);
			return shape;
		}
		else
			System.out.println("How did you do this...");
		return null;
	}


}
