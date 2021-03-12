package main;

import controller.IJPaintController;
import controller.JPaintController;
import view.gui.MouseObserver;
import model.ShapeList;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.PositionConfig;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;


public class Main {
    public static void main(String[] args){
        // PaintCanvas渲染shapeList，Mouse增加shapeList信息; 本质上是一个Singleton
        IShapeList shapeList = new ShapeList();

        PaintCanvasBase paintCanvas = new PaintCanvas(shapeList);
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState, shapeList);
        controller.setup();

        MouseObserver mouseObserver = new MouseObserver(appState, paintCanvas, shapeList, new PositionConfig());
        mouseObserver.run();

    }
}
