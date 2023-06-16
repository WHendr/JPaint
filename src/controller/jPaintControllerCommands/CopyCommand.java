package controller.jPaintControllerCommands;

import java.util.ArrayList;

import design.lists.Clipboard;
import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.Shape;

public class CopyCommand implements ICommand{
	
	ArrayList<Shape> clipboard = MasterList.getInstance().getClipboard().getIterable();
	
	@Override
	public void run() {
		if(!clipboard.isEmpty()) {
			clipboard.clear();
		}
		ArrayList<Shape> selectedList = MasterList.getInstance().getSelectedList().getSelectedList();
		Clipboard clipboard = MasterList.getInstance().getClipboard();
		for(var shape : selectedList) {
			clipboard.addShape(shape);
		}	
	}

}
