package model.persistence;

import java.util.ArrayList;

import controller.jPaintControllerCommands.CommandHistory;
import controller.jPaintControllerCommands.ICommand;
import controller.jPaintControllerCommands.IUndoable;
import design.lists.Clipboard;
import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Shape;

public class SaveFile {
	
	private MasterList masterList = MasterList.getInstance();
	private ShapeList shapeList = MasterList.getInstance().getShapeList();
	private SelectedList selectedList = MasterList.getInstance().getSelectedList();
	private Clipboard clipboard = MasterList.getInstance().getClipboard();
	
	private static ArrayList<Shape> savedShapeList;
	private static ArrayList<Shape> savedSelectedList;
	private static ArrayList<Shape> savedClipboard;
	
	private ArrayList<Object> savedCmdList = new ArrayList<Object>();
	
	public SaveFile() {
		savedShapeList = new ArrayList<Shape>();
		savedSelectedList = new ArrayList<Shape>();
		savedClipboard = new ArrayList<Shape>();
		
	}
	
	
	public void Save() {
		ArrayList<Object> list = CommandHistory.getCmdList();
		for(Object cmd : list) {
			savedCmdList.add(cmd);
		}
		/*
		savedShapeList.clear();
		savedSelectedList.clear();
		savedClipboard.clear();
		
		ArrayList<Shape> list = shapeList.getIterable();
		for(Shape shape : list) {
			savedShapeList.add(shape);
		}
		list = selectedList.getIterable();
		for(Shape shape : list) {
			savedSelectedList.add(shape);
		}
		list = clipboard.getIterable();
		for(Shape shape : list) {
			savedClipboard.add(shape);
		}
		*/
	}
	
	public void Load() {
		CommandHistory.clear();
		
		ICommand cmd = null;
		IUndoable Iun;
		
		for(Object c : savedCmdList) {
			//System.out.println(c);
			if(c == "undo") {
				Iun = (IUndoable) cmd;
				Iun.undo();
			}
			else if(c == "redo") {
				Iun = (IUndoable) cmd;
				Iun.redo();
				
			}
			else {
				cmd = (ICommand) c;
				cmd.run();
			}
		}
		
		
		/*
		masterList.clear();
		
		ArrayList<Shape> list = savedShapeList;
		for(Shape shape : list) {
			shapeList.addShape(shape);
		}
		list = savedSelectedList;
		for(Shape shape : list) {
			selectedList.addShape(shape);
		}
		list = savedClipboard;
		for(Shape shape : list) {
			clipboard.addShape(shape);
		}
		*/
	}

	
}
