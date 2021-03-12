package view.gui.mouse;

import controller.operator.CreateShapeOperator;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.ShapeConfig;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author algorithm
 */
public class DrawMouseAdapter extends MouseAdapter {

    private IApplicationState appState;
    private IShapeList shapeList;
    private ShapeConfig shapeConfig;

    public DrawMouseAdapter(IApplicationState appState, IShapeList shapeList, ShapeConfig shapeConfig) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeConfig = shapeConfig;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shapeConfig.setStartPoint(new Point(e.getX(), e.getY()));
        System.out.println("Press " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shapeConfig.setEndPoint(new Point(e.getX(), e.getY()));
        System.out.println("Released " + e.getX() + "," + e.getY());
        (new CreateShapeOperator(shapeList, shapeConfig, appState)).run();
    }

}
