package design.lists;

import view.gui.PaintCanvas;

public class Canvas {
	
	private static PaintCanvas paintCanvas;
	
	public Canvas(PaintCanvas paintCanvas) {
		this.paintCanvas = paintCanvas;
	}
	
	public static PaintCanvas getInstance() {
		return paintCanvas;
	}

}
