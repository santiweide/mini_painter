package controller;

import model.interfaces.IApplicationState;
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
    private List<IShape> tmpList;

    public MoveShapeOperator(IShapeList shapeList, ShapeConfig shapeConfig) {
        this.shapeConfig = shapeConfig;
        this.shapeList = shapeList;
    }
    @Override
    public void run() {
        tmpList = new ArrayList<>();
        int dx = shapeConfig.getEndPoint().getX() - shapeConfig.getStartPoint().getX();
        int dy = shapeConfig.getEndPoint().getY() - shapeConfig.getStartPoint().getY();

        for (IShape selectedShape : shapeList.getShapeList())
        {
            oldShape = selectedShape;
            tmpList.add(oldShape);
            shapeList.remove(oldShape);

            for (IShape tempShape : tmpList)
            {
                tempShape.addAdjustPos(dx, dy);
                newShape = tempShape;
                shapeList.add(newShape);
            }
            tmpList.clear();
        }
        OperationHistory.add(this);
    }

    @Override
    public void undo() {
        shapeList.remove(newShape);
        shapeList.add(oldShape);
    }

    @Override
    public void redo() {
        shapeList.remove(newShape);
        shapeList.add(oldShape);
    }
}
