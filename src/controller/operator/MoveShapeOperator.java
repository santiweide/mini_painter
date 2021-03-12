package controller.operator;

import controller.OperationHistory;
import model.interfaces.IShapeList;
import model.interfaces.IUndoable;
import model.persistence.draw.PositionConfig;
import view.interfaces.draw.IShape;

import java.util.ArrayList;
import java.util.List;

public class MoveShapeOperator implements IOperator, IUndoable {

    private IShapeList shapeList;
    private PositionConfig positionConfig;
    private IShape oldShape;
    private IShape newShape;

    public MoveShapeOperator(IShapeList shapeList, PositionConfig positionConfig) {
        this.positionConfig = positionConfig;
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        int dx = positionConfig.getEndPoint().getX() - positionConfig.getStartPoint().getX();
        int dy = positionConfig.getEndPoint().getY() - positionConfig.getStartPoint().getY();
        List<IShape> newSelectedList = new ArrayList<>();
        // move depends on selected shaped ~~
        for (IShape selectedShape : shapeList.getSelectList()) {
            oldShape = selectedShape;
            newShape = selectedShape.move(dx, dy);
            shapeList.remove(selectedShape);
            shapeList.add(newShape);
            newSelectedList.add(newShape);
        }
        // moving should not detect any shapes
        shapeList.setSelectList(newSelectedList);
        OperationHistory.add(this);
    }

    @Override
    public void undo() {
        shapeList.remove(newShape);
        shapeList.add(oldShape);
    }

    @Override
    public void redo() {
        shapeList.remove(oldShape);
        shapeList.add(newShape);
    }
}
