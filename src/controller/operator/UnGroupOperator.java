package controller.operator;

import controller.OperationHistory;
import model.interfaces.IShapeList;
import model.interfaces.IUndoable;
import view.gui.draw.GroupShape;

public class UnGroupOperator implements IOperator, IUndoable {
    IShapeList shapeList;
    GroupShape removedItem;

    public UnGroupOperator(IShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        removedItem = shapeList.removeGroup();
        OperationHistory.add(this);
    }

    @Override
    public void undo() {
        // 撤回UnGroup，需要把removedItem作为selectedItem之后addGroup..
        shapeList.setSelectList(removedItem.getGroupItemList());
        shapeList.addGroup();
    }

    @Override
    public void redo() {
        // 重新UnGroup
        removedItem = shapeList.removeGroup();
    }
}
