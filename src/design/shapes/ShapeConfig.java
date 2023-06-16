package design.shapes;

import java.awt.Color;
import java.awt.Shape;

import design.adapter.ShapeColorAdapter;
import design.interfaces.IAdapter;
import design.lists.MasterList;
import design.lists.ShapeList;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

//Holds individual Shape Configurations
public class ShapeConfig{

	private ShapeColorAdapter colorAdapter;
	
	private Color primaryColor;
	private Color secondaryColor;
	private String shapeShading;
	private String shapeType;
	private PaintCanvas paintCanvas;
	private Point startPoint, endPoint;
	private int height,width;
	private ApplicationState applicationState;
	
	public ShapeConfig(Point startPoint, Point endPoint, ApplicationState applicationState, PaintCanvas paintCanvas){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.paintCanvas = paintCanvas;
		this.applicationState = applicationState;
		setHeight(startPoint, endPoint);
		setWidth(startPoint, endPoint);
		if(applicationState != null) {
			setPrimaryColor(applicationState.getActivePrimaryColor());
			setSecondaryColor(applicationState.getActiveSecondaryColor());
			setShapeType(applicationState.getActiveShapeType());
			setShapeShading(applicationState.getActiveShapeShadingType());
		}
    }

	private void setPrimaryColor(ShapeColor shapeColor) {
		colorAdapter = new ShapeColorAdapter(shapeColor);
		this.primaryColor = colorAdapter.getColor();
    }
    
    private void setSecondaryColor(ShapeColor shapeColor) {
    	colorAdapter = new ShapeColorAdapter(shapeColor);
    	this.secondaryColor = colorAdapter.getColor();
    }
    private void setShapeType(ShapeType shapeType) {
    	this.shapeType = shapeType.toString();
    }
    
    private void setShapeShading(ShapeShadingType shapeShading) {
    	this.shapeShading = shapeShading.toString();
    }
    
    private void setHeight(Point startPoint, Point endPoint) {
    	this.height = Math.abs(endPoint.y - startPoint.y );
    }

    private void setWidth(Point startPoint, Point endPoint) {
    	this.width = Math.abs(endPoint.x - startPoint.x );
    }
    
    public int getHeight() {
    	return this.height; 
    }

    public int getWidth() {
    	return this.width; 
    }
    
    public Point getStartPoint() {return this.startPoint;}
    
    public Point getEndPoint() {return this.endPoint;}

	public Color getPrimaryColor() { return this.primaryColor; }

	public Color getSecondaryColor() { return this.secondaryColor; }
	
	public String getShapeType() {return this.shapeType;}
	
	public String getShapeShading() {return this.shapeShading;}
	
	public PaintCanvas getPaintCanvas() {return this.paintCanvas;}
	
	public ApplicationState getAppState() {return this.applicationState;}

}
