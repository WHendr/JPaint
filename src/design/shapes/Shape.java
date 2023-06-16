package design.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.ArrayList;

import controller.jPaintControllerCommands.CommandHistory;
import controller.jPaintControllerCommands.ICommand;
import controller.jPaintControllerCommands.IUndoable;
import design.interfaces.IPrototype;
import design.lists.Canvas;
import design.lists.GroupList;
import design.lists.MasterList;
import design.lists.ShapeList;
import view.gui.PaintCanvas;

public abstract class Shape implements  ICommand, IUndoable, IPrototype{
	private boolean isSelected;
	//stole your dotted line
    private Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
	private Color primaryColor;
	private Color secondaryColor;
	private String shapeShading;
	private Point startPoint, endPoint;
	private ShapeConfig shapeConfig;
	private ShapeList shapeList = MasterList.getInstance().getShapeList();
	private GroupList groupList = MasterList.getInstance().getGroupList();

	private PaintCanvas paintCanvas = Canvas.getInstance();
	
	//
	private boolean isGrouped;
	private ArrayList<Shape> groupedList = new ArrayList<Shape>();
	private ArrayList<Shape> selfList = new ArrayList<Shape>();
	
	//
	private int padding = 10;
	
	private static ArrayList<Point> oldPoints = new ArrayList<Point>();
	private static ArrayList<Point> newPoints = new ArrayList<Point>();
	
	public Shape(ShapeConfig shapeConfig){
		this.shapeConfig = shapeConfig;
		this.primaryColor = shapeConfig.getPrimaryColor();
		this.secondaryColor = shapeConfig.getSecondaryColor();
		this.shapeShading = shapeConfig.getShapeShading();
		this.startPoint = shapeConfig.getStartPoint();
		this.endPoint = shapeConfig.getEndPoint();
	}
	
	public Shape(/*ArrayList<Shape> shapes*/) {
		//this.shapes = shapes;
	}
	
	public void undo() {
		shapeList.removeShape(this);
	}

	public void redo() {
		shapeList.addShape(this);
	}

	public void run() {
		shapeList.addShape(this);
		CommandHistory.add(this);
	}
	
	protected void setNewPoints(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		};
	
	public ShapeConfig getShapeConfig() {return this.shapeConfig;}
	
	public Point getStartPoint() {return startPoint;}
	
	public Point getEndPoint() {return endPoint;}
	
	public abstract void setPoints();
	
	public abstract void setPoints(Point changedPoint);
	
	public Point getPreviousPoint() {
		Point oldPoint = null;
		for(var point : oldPoints) {
			oldPoint = point;
		}
		oldPoints.remove(oldPoint);
		return oldPoint;
	}
	
	public void addPreviousPoint() {oldPoints.add(this.startPoint);}
	
	public Point getNewPoint() {
		Point newPoint = null;
		for(var point : newPoints) {
			newPoint = point;
		}
		newPoints.remove(newPoint);
		return newPoint;
	}
	
	public void addNewPoint() {newPoints.add(this.startPoint);}
	
	public abstract void drawShape();

	public Color getPrimaryColor() {return this.primaryColor;}
	
	public Color getSecondaryColor() {return this.secondaryColor;}
	
	public String getShapeShading() {return this.shapeShading;}
	
	public int getPadding() {return this.padding;}

	//Think this is prototype like
	public abstract Shape clone();
	
	public boolean isSelected() {return this.isSelected;}
	
	public void select() {this.isSelected = true;}
	
	public void unSelect() {this.isSelected = false;}

	public Stroke getStroke() {
		return this.stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}
	
	public PaintCanvas getPaintCanvas() {
		return this.paintCanvas;
	}
	

	public void setAsGrouped() {
		this.isGrouped = true;
	}
	
	public boolean isGrouped() {
		return this.isGrouped;
	}
	
	public ArrayList<Shape> getGrouped(){
		return this.groupedList;
	}
	
	public void setGrouped(ArrayList<Shape> shapes) {
		for(Shape shape : shapes) {
			if(shape.isGrouped()) {
				ArrayList<Shape> shapes2 = shape.getGrouped();
				for(Shape shape2 : shapes2) {
					groupedList.add(shape2);
				}
			}
			else {
				groupedList.add(shape);
			}
		}
	}
	
	public void setSelf(ArrayList<Shape> shapes) {
		for(Shape shape : shapes) {
			if(!selfList.contains(shape))
				selfList.add(shape);
		}
	}
	
	public ArrayList<Shape> getSelf() {
		return this.selfList;
	}
	
	public void setShapeConfig(ShapeConfig shapeConfig) {
		this.shapeConfig = shapeConfig;
	}
}
