package model.persistence.draw;

import model.ShapeType;
import model.interfaces.IApplicationState;
import view.gui.draw.EllipseShape;
import view.gui.draw.RectShape;
import view.gui.draw.TriangleShape;
import view.interfaces.draw.IShape;

public class ShapeFactory {

    public IShape createShape(PositionConfig config, IApplicationState appState){
        PositionConfig positionConfig = new PositionConfig();
        positionConfig.setStartPoint(config.getStartPoint());
        positionConfig.setEndPoint(config.getEndPoint());
        if(appState.getActiveShapeType().equals(ShapeType.RECTANGLE)){
            return new RectShape(positionConfig, appState);
        } else if(appState.getActiveShapeType().equals(ShapeType.TRIANGLE)){
            return new TriangleShape(positionConfig, appState);
        } else if(appState.getActiveShapeType().equals(ShapeType.ELLIPSE)){
            return new EllipseShape(positionConfig, appState);
        }
        System.out.println("Error no active shape type");
        return new RectShape(config, appState);
    }
}
