package view.gui;


import model.MouseMode;
import model.interfaces.IMouseAdapterObserver;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.ShapeConfig;
import view.gui.mouse.DrawMouseAdapter;
import view.gui.mouse.MoveMouseAdapter;
import view.gui.mouse.SelectMouseAdapter;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseListener;

/**
 * observe mouse movement by using different MouseAdapters
 * @author algorithm
 */
public class MouseObserver implements IMouseAdapterObserver {

    private ApplicationState appState;
    private PaintCanvasBase paintCanvas;
    private IShapeList shapeList;
    private ShapeConfig shapeConfig;

    public MouseObserver(ApplicationState appState, PaintCanvasBase paintCanvas, IShapeList shapeList, ShapeConfig shapeConfig) {
        this.appState = appState;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.shapeConfig = shapeConfig;
        appState.register(this);
    }

    /**
     * 为不同的MouseMode 适配 MouseAdapter
     */
    @Override
    public void run() {
        // 清除原来的Observer
        MouseListener[] mouseListeners = paintCanvas.getMouseListeners();
        for (MouseListener mouseListener : mouseListeners) {
            paintCanvas.removeMouseListener(mouseListener);
        }

        if (appState.getActiveMouseMode().equals(MouseMode.DRAW)) {
            paintCanvas.addMouseListener(new DrawMouseAdapter(appState, shapeList, shapeConfig));
        } else if (appState.getActiveMouseMode().equals(MouseMode.MOVE)) {
            paintCanvas.addMouseListener(new MoveMouseAdapter(appState, shapeList, shapeConfig));
        }  else if (appState.getActiveMouseMode().equals(MouseMode.SELECT)) {
            paintCanvas.addMouseListener(new SelectMouseAdapter(appState, shapeList, shapeConfig));
        }
    }
}
