package view.gui.mouse;

import controller.operator.CreateShapeOperator;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeList;
import model.persistence.draw.Point;
import model.persistence.draw.PositionConfig;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author algorithm
 */
public class DrawMouseAdapter extends MouseAdapter {

    private IApplicationState appState;
    private IShapeList shapeList;
    private PositionConfig positionConfig;

    public DrawMouseAdapter(IApplicationState appState, IShapeList shapeList, PositionConfig positionConfig) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.positionConfig = positionConfig;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        positionConfig.setStartPoint(new Point(e.getX(), e.getY()));
        System.out.println("Draw Press " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        positionConfig.setEndPoint(new Point(e.getX(), e.getY()));
        System.out.println("Draw Released " + e.getX() + "," + e.getY());
        (new CreateShapeOperator(shapeList, positionConfig, appState)).run();
    }

}
