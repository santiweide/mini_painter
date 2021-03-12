package controller.operator;

import model.interfaces.IShapeList;
import model.persistence.draw.ShapeConfig;
import view.interfaces.draw.IShape;

/**
 * do not need to undo
 */
public class SelectShapeOperator implements IOperator {
    /**
     * all the shapes
     */
    private final IShapeList shapeList;
    /**
     * select area's position info
     */
    private final ShapeConfig shapeConfig;

    public SelectShapeOperator(IShapeList shapeList, ShapeConfig shapeConfig) {
        this.shapeList = shapeList;
        this.shapeConfig = shapeConfig;
    }

    @Override
    public void run() {
        boolean containsSelectedShape = false;
        for (IShape shape : shapeList.getShapeList()) {
            // find all that could be selected
            if (shape.contains(shapeConfig.getStartPoint())) {
                containsSelectedShape = true;
                shapeList.addSelectedList(shape);
            }
        }
        if (!containsSelectedShape) {
            shapeList.clearSelectList();
            shapeList.getClipBoard().clear();
        }
    }
}
