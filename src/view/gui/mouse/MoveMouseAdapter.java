package view.gui.mouse;

import controller.operator.MoveShapeOperator;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.PositionConfig;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveMouseAdapter extends MouseAdapter {
    private ApplicationState appState;
    private IShapeList shapeList;
    private PositionConfig positionConfig;

    public MoveMouseAdapter(ApplicationState appState, IShapeList shapeList, PositionConfig positionConfig) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.positionConfig = positionConfig;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        positionConfig.setStartPoint(new Point(e.getX(), e.getY()));
        System.out.println("Move Press " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        positionConfig.setEndPoint(new Point(e.getX(), e.getY()));
        System.out.println("Move Release " + e.getX() + "," + e.getY());
        (new MoveShapeOperator(shapeList, positionConfig)).run();
    }

}
