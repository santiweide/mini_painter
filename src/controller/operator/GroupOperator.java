package controller.operator;

import controller.OperationHistory;
import model.interfaces.IShapeList;
import model.interfaces.IUndoable;
import view.interfaces.draw.IShape;

import java.util.List;

public class GroupOperator implements IOperator, IUndoable {
    IShapeList shapeList;
    List<IShape> selectedShape;

    public GroupOperator(IShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        selectedShape = shapeList.getSelectList();
        shapeList.addGroup();
        OperationHistory.add(this);
    }

    @Override
    public void undo() {
        // addGroup 把selectedList聚合到selectedList和shapeList中
        // removeGroup 遍历selectedList，把最后面的 GroupList类型的IShape 从selectList和shapeList中拆解出来
        // undo 需要把聚合的selectedList拆解到selectedList和shapeList中
        selectedShape = shapeList.removeGroup().getGroupItemList();
    }

    @Override
    public void redo() {
        // 重新group
        shapeList.setSelectList(selectedShape);
        shapeList.addGroup();
    }
}
