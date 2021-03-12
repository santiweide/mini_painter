package view.gui.mouse;

import controller.operator.SelectShapeOperator;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.ShapeConfig;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author algorithm
 */
public class SelectMouseAdapter extends MouseAdapter {
    private ApplicationState appState;
    private IShapeList shapeList;
    private ShapeConfig shapeConfig;

    public SelectMouseAdapter(ApplicationState appState, IShapeList shapeList, ShapeConfig shapeConfig) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeConfig = shapeConfig;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shapeConfig.setStartPoint(new Point(e.getX(), e.getY()));
        System.out.println("Select Press " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shapeConfig.setEndPoint(new Point(e.getX(), e.getY()));
        System.out.println("Select Released " + e.getX() + "," + e.getY());
        (new SelectShapeOperator(shapeList, shapeConfig)).run();
    }

}
