package main;

import java.util.ArrayList;

import controller.ClickHandler;
import controller.IJPaintController;
import controller.JPaintController;
import design.lists.Canvas;
import design.lists.MasterList;
import design.lists.SelectedList;
import design.lists.ShapeList;
import design.shapes.IShape;
import design.shapes.Shape;
import model.persistence.ApplicationState;
import model.persistence.SaveHistory;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvas paintCanvas = new PaintCanvas();
        
        
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        
        SaveHistory saveHistory = new SaveHistory(uiModule);

        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState, saveHistory);
        

        Canvas canvas = new Canvas(paintCanvas); 
        /* Trying my best at an observer pattern but 
        probably need to clean this up */
        MasterList masterList = MasterList.getInstance();
        
        //probably should change this soon
        ShapeList shapeList = masterList.getShapeList();
        shapeList.registerObservers(paintCanvas);
        SelectedList selectedList = masterList.getSelectedList();
        selectedList.registerObservers(paintCanvas);
        
        ClickHandler clickHandler = new ClickHandler(paintCanvas, appState, shapeList, selectedList);
        paintCanvas.addMouseListener(clickHandler);

        controller.setup();
    }
}
