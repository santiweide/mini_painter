package model.persistence.draw;

import model.ShapeType;
import model.interfaces.IApplicationState;
import view.gui.draw.RectShape;
import view.interfaces.draw.IShape;

public class ShapeFactory {

    public IShape createShape(ShapeConfig config, IApplicationState appState){
        if(appState.getActiveShapeType().equals(ShapeType.RECTANGLE)){
            // position factors
            ShapeConfig shapeConfig = new ShapeConfig();
            shapeConfig.setStartPoint(config.getStartPoint());
            shapeConfig.setEndPoint(config.getEndPoint());

            return new RectShape(shapeConfig, appState);
        }
        System.out.println("Error no active shape type");
        return new RectShape(config, appState);
    }
}
