package controller.jPaintControllerCommands;

import java.util.ArrayList;
import java.util.Stack;

//made public 
public class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();
	
	//
	private static final ArrayList<Object> cmdList = new ArrayList<Object>();

	public static void add(IUndoable cmd) {
		//System.out.println("add command: " + cmd.getClass());
		undoStack.push(cmd);
		redoStack.clear();
		cmdList.add(cmd);
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			//System.out.println("undo command: " + c.getClass());
			redoStack.push(c);
			c.undo();
			cmdList.add("undo");
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			//System.out.println("redo command: " + c.getClass());
			undoStack.push(c);
			c.redo();
			cmdList.add("redo");
		}
		return result;
	}
	
	
	//added this
	public static ArrayList<Object> getCmdList(){
		return cmdList;
	}
	
	
	public static void clear() {
		for(IUndoable c : undoStack) {
			c.undo();
		}
		redoStack.clear();
		cmdList.clear();
	}
	
	
}