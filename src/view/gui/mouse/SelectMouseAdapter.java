package view.gui.mouse;

import controller.operator.SelectShapeOperator;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.PositionConfig;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author algorithm
 */
public class SelectMouseAdapter extends MouseAdapter {
    private ApplicationState appState;
    private IShapeList shapeList;
    private PositionConfig positionConfig;

    public SelectMouseAdapter(ApplicationState appState, IShapeList shapeList, PositionConfig positionConfig) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.positionConfig = positionConfig;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        positionConfig.setStartPoint(new Point(e.getX(), e.getY()));
        System.out.println("Select Press " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        positionConfig.setEndPoint(new Point(e.getX(), e.getY()));
        System.out.println("Select Released " + e.getX() + "," + e.getY());
        (new SelectShapeOperator(shapeList, positionConfig)).run();
    }

}
