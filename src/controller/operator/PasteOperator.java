package controller.operator;

import controller.OperationHistory;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeList;
import model.interfaces.IUndoable;
import model.persistence.draw.ShapeConfig;
import view.interfaces.draw.IShape;

import java.util.ArrayList;
import java.util.List;

/**
 * after paste, the paintCanvas should be changed. so addAll should call notifyAll.
 */
public class PasteOperator implements IOperator, IUndoable {
    IShapeList shapeList;
    List<IShape> newShapeList;

    public PasteOperator(IShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        newShapeList = new ArrayList<>();
        for (IShape copiedShape : shapeList.getClipBoard()) {
            newShapeList.add(copiedShape.move(50, 50));
        }
        shapeList.addAll(newShapeList);
        shapeList.clearClipBoard();
        OperationHistory.add(this);
    }

    @Override
    public void undo() {
        shapeList.removeAll(newShapeList);
    }

    @Override
    public void redo() {
        shapeList.addAll(newShapeList);
    }
}
