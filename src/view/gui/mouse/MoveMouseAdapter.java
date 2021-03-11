package view.gui.mouse;

import controller.operator.MoveShapeOperator;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.ShapeConfig;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveMouseAdapter extends MouseAdapter {
    private ApplicationState appState;
    private IShapeList shapeList;
    private ShapeConfig shapeConfig;

    public MoveMouseAdapter(ApplicationState appState, IShapeList shapeList, ShapeConfig shapeConfig) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeConfig = shapeConfig;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shapeConfig.setStartPoint(new Point(e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Move mouse released");
        shapeConfig.setEndPoint(new Point(e.getX(), e.getY()));
        (new MoveShapeOperator(shapeList, shapeConfig)).run();
    }

}
