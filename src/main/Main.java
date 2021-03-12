package main;

import controller.IJPaintController;
import controller.JPaintController;
import view.gui.MouseObserver;
import model.ShapeList;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.ShapeConfig;
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

        // appState里面也要存一下shapeList...？有必要吗？ 不知道...也许把shapeList放到ApplicationState里面也不太合适吧？
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState, shapeList);
        controller.setup();

        MouseObserver mouseObserver = new MouseObserver(appState, paintCanvas, shapeList, new ShapeConfig());
        mouseObserver.run();
        // For example purposes only; remove all lines below from your final project.
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Filled in rectangle
//        Graphics2D graphics2d = paintCanvas.getGraphics2D();
//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//
//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);
    }
}
