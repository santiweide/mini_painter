package model.persistence.draw;

import model.ShapeType;
import model.interfaces.IApplicationState;
import view.gui.draw.RectShape;
import view.interfaces.draw.IShape;

public class ShapeFactory {

    public IShape createShape(PositionConfig config, IApplicationState appState){
        if(appState.getActiveShapeType().equals(ShapeType.RECTANGLE)){
            // position factors
            PositionConfig positionConfig = new PositionConfig();
            positionConfig.setStartPoint(config.getStartPoint());
            positionConfig.setEndPoint(config.getEndPoint());

            return new RectShape(positionConfig, appState);
        }
        System.out.println("Error no active shape type");
        return new RectShape(config, appState);
    }
}
