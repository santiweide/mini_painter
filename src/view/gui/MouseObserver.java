package view.gui;


import model.MouseMode;
import model.interfaces.IMouseAdapterObserver;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.PositionConfig;
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
    private PositionConfig positionConfig;

    public MouseObserver(ApplicationState appState, PaintCanvasBase paintCanvas, IShapeList shapeList, PositionConfig positionConfig) {
        this.appState = appState;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.positionConfig = positionConfig;
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
            paintCanvas.addMouseListener(new DrawMouseAdapter(appState, shapeList, positionConfig));
        } else if (appState.getActiveMouseMode().equals(MouseMode.MOVE)) {
            paintCanvas.addMouseListener(new MoveMouseAdapter(appState, shapeList, positionConfig));
        }  else if (appState.getActiveMouseMode().equals(MouseMode.SELECT)) {
            paintCanvas.addMouseListener(new SelectMouseAdapter(appState, shapeList, positionConfig));
        }
    }
}
