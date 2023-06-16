package view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import design.lists.IObserver;
import design.lists.MasterList;
import design.lists.ShapeList;
import design.shapes.IShape;
import design.shapes.Shape;

public class PaintCanvas extends JComponent implements  IObserver {
	

    private Shape shape; 
    private Graphics2D g2d;

    //attempting to have this function similar to a setter for g2d
	public void paint(Graphics g) {
		this.g2d = getGraphics2D();
    }
   
    public Graphics2D getGraphics2D() {
    	return (Graphics2D) getGraphics();
    }


	@Override
	public void update() {
		g2d.setColor(Color.white);
		g2d.fillRect(0,0,2000, 2000);//Probably need to fix this so it itsn't hard coded
		
		ArrayList<Shape> list = MasterList.getInstance().getShapeList().getIterable();
		
		//System.out.println("hello");
		for(Shape shape : list) {
			//System.out.println("shape " + shape);
			shape.drawShape();
		}
		
	}

	/*
	@Override
	public void updateSelected() {
		ArrayList<Shape> list = MasterList.getInstance().getSelectedList().getIterable();
		
		for(Shape shape : list) {
			//shape.drawBorder();
		}
		
	}
	*/
}
