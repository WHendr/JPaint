package controller;

import controller.jPaintControllerCommands.CopyCommand;
import controller.jPaintControllerCommands.DeleteCommand;
import controller.jPaintControllerCommands.GroupCommand;
import controller.jPaintControllerCommands.PasteCommand;
import controller.jPaintControllerCommands.RedoCommand;
import controller.jPaintControllerCommands.UnGroupCommand;
import controller.jPaintControllerCommands.UndoCommand;
import model.interfaces.IApplicationState;
import model.persistence.SaveHistory;
import view.EventName;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    
    //added
    private final SaveHistory saveHistory;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, SaveHistory saveHistory) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.saveHistory = saveHistory;
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
        uiModule.addEvent(EventName.UNDO , () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO , () -> new RedoCommand().run());
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand().run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand().run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand().run());
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand().run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UnGroupCommand().run());
        uiModule.addEvent(EventName.SAVE, () -> saveHistory.save());
        uiModule.addEvent(EventName.LOAD, () -> saveHistory.loadSave());
    }
}
