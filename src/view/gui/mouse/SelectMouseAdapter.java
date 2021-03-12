package view.gui.mouse;

import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.ShapeConfig;

import java.awt.event.MouseAdapter;

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
}
