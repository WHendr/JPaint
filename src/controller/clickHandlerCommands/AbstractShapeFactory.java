package controller.clickHandlerCommands;

import design.shapes.Shape;
import design.shapes.ShapeConfig;


//Like this?
public interface AbstractShapeFactory {

	public Shape createShape(ShapeConfig shapeConfig) ;
}
