package controller.operator;

import model.interfaces.IShapeList;
import model.persistence.draw.PositionConfig;
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
    private final PositionConfig positionConfig;

    public SelectShapeOperator(IShapeList shapeList, PositionConfig positionConfig) {
        this.shapeList = shapeList;
        this.positionConfig = positionConfig;
    }

    @Override
    public void run() {
        boolean containsSelectedShape = false;
        for (IShape shape : shapeList.getShapeList()) {
            // find all that could be selected
            if (shape.contains(positionConfig.getStartPoint())) {
                containsSelectedShape = true;
                shapeList.addSelectedList(shape);
            }
        }
        if (!containsSelectedShape) {
            // deselect
            shapeList.clearSelectList();
            shapeList.getClipBoard().clear();
        }
    }
}
