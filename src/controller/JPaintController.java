package controller;

import controller.operator.CopyOperator;
import controller.operator.DeleteOperator;
import controller.operator.PasteOperator;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeList;
import view.EventName;
import view.interfaces.IUiModule;

/**
 * @author algorithm
 */
public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final IShapeList shapeList;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, IShapeList shapeList) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO, () -> OperationHistory.undo());
        uiModule.addEvent(EventName.REDO, () -> OperationHistory.redo());
        uiModule.addEvent(EventName.COPY, () -> (new CopyOperator(shapeList)).run());
        uiModule.addEvent(EventName.PASTE, () -> (new PasteOperator(shapeList)).run());
        uiModule.addEvent(EventName.DELETE, () -> (new DeleteOperator(shapeList)).run());
    }
}
