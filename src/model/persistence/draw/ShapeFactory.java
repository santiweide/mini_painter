package model.persistence.draw;

import model.ShapeType;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.gui.draw.RectShape;
import view.interfaces.draw.IShape;

public class ShapeFactory {

    public IShape createShape(ShapeConfig config, IApplicationState appState){
        if(appState.getActiveShapeType().equals(ShapeType.RECTANGLE)){
            return new RectShape(config, appState);
        }
        System.out.println("Error no active shape type");
        return new RectShape(config, appState);
    }
}
