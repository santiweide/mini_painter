package controller.operator;

import model.interfaces.IShapeList;
import model.interfaces.IUndoable;
import view.interfaces.draw.IShape;

import java.util.List;

public class DeleteOperator implements IOperator, IUndoable {
    IShapeList shapeList;
    List<IShape> selectedList;

    public DeleteOperator(IShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        selectedList = shapeList.getSelectList();
        shapeList.removeAll(shapeList.getSelectList());
    }

    @Override
    public void undo() {
        shapeList.addAll(selectedList);
    }

    @Override
    public void redo() {
        shapeList.removeAll(selectedList);
    }
}
