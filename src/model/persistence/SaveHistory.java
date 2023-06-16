package model.persistence;


import controller.jPaintControllerCommands.CommandHistory;
import controller.jPaintControllerCommands.Intefaces.ICaretaker;
import controller.jPaintControllerCommands.Intefaces.IOriginator;
import design.adapter.SaveAdapter;
import model.Save;
import model.dialogs.SaveDialogProvider;
import model.interfaces.ISaveDialogProvider;
import view.interfaces.IUiModule;

public class SaveHistory implements ICaretaker{
	//private CommandHistory commandHistory;
    private final IUiModule uiModule;
    private final ISaveDialogProvider dialogProvider;
    private Save activeSave;
    private SaveAdapter saveAdapter;
    
    private static int length = Save.values().length;
    
    private static SaveFile[] saveHistory = new SaveFile[length];
    
    public SaveHistory(IUiModule uiModule) {
    	this.uiModule = uiModule;
    	this.dialogProvider = new SaveDialogProvider(this);
    	
    	for(int i = 0; i < length; i++) {
    		saveHistory[i] = new SaveFile();
    	}
    	
    }
    
    public void loadSave() {
    	this.activeSave = uiModule.getDialogResponse(dialogProvider.loadSave());
    	//System.out.println(this.activeSave);
    	saveAdapter = new SaveAdapter(this.activeSave);
    	int i = saveAdapter.getSaveFile();
    	
    	saveHistory[i].Load();
    }
    
    public SaveFile[] getSaveHistory() {
    	return saveHistory;
    }
    
    public Save getActiveSave() {
    	return this.activeSave;
    }

	public void save() {
		//this.activeSave = uiModule.getDialogResponse(dialogProvider.chooseSave());
		Save save = uiModule.getDialogResponse(dialogProvider.chooseSave());
		saveAdapter = new SaveAdapter(save);
		int i = saveAdapter.getSaveFile();
		
		saveHistory[i].Save();
	}

	
	
}
