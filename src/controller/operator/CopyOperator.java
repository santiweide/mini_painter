package controller.operator;

import model.interfaces.IShapeList;
import view.interfaces.draw.IShape;

public class CopyOperator implements IOperator {
    IShapeList shapeList;

    public CopyOperator(IShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        for (IShape shape : shapeList.getSelectList()) {
            shapeList.addClipBoard(shape);
        }
    }
}
