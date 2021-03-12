package controller.operator;

import controller.OperationHistory;
import model.interfaces.IShapeList;
import model.interfaces.IUndoable;
import model.persistence.draw.ShapeConfig;
import view.interfaces.draw.IShape;

import java.util.ArrayList;
import java.util.List;

public class MoveShapeOperator implements IOperator, IUndoable {

    private IShapeList shapeList;
    private ShapeConfig shapeConfig;
    private IShape oldShape;
    private IShape newShape;

    public MoveShapeOperator(IShapeList shapeList, ShapeConfig shapeConfig) {
        this.shapeConfig = shapeConfig;
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        int dx = shapeConfig.getEndPoint().getX() - shapeConfig.getStartPoint().getX();
        int dy = shapeConfig.getEndPoint().getY() - shapeConfig.getStartPoint().getY();
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
