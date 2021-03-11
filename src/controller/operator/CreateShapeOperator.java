package controller.operator;

import controller.IOperator;
import controller.OperationHistory;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeList;
import model.interfaces.IUndoable;
import model.persistence.draw.ShapeConfig;
import model.persistence.draw.ShapeFactory;
import view.interfaces.draw.IShape;

/**
 * @author algorithm
 */
public class CreateShapeOperator  implements IOperator, IUndoable {
    private ShapeFactory shapeFactory = new ShapeFactory();
    private IShapeList shapeList;
    private ShapeConfig shapeConfig;
    private IApplicationState appState;
    private IShape nowShape;

    public CreateShapeOperator(IShapeList shapeList, ShapeConfig shapeConfig, IApplicationState appState) {
        this.shapeConfig = shapeConfig;
        this.shapeList = shapeList;
        this.appState = appState;
    }

    @Override
    public void run() {
        nowShape = shapeFactory.createShape(shapeConfig, appState);
        shapeList.add(nowShape);
        OperationHistory.add(this);
    }

    @Override
    public void undo() {
        shapeList.remove(nowShape);
    }

    @Override
    public void redo() {
        shapeList.add(nowShape);
    }
}
