package model.dialogs;

import model.Save;
import model.interfaces.ISaveDialogProvider;
import model.persistence.SaveHistory;
import view.interfaces.IDialogChoice;

public class SaveDialogProvider implements ISaveDialogProvider{
	
	
	private final IDialogChoice<Save> chooseSave;
	private final IDialogChoice<Save> loadSave;

	public SaveDialogProvider(SaveHistory saveHistory) {
		this.chooseSave = new ChooseSaveDialog(saveHistory);
		this.loadSave = new LoadSaveDialog(saveHistory);
		
	}

	public IDialogChoice<Save> chooseSave(){
		return chooseSave;
		
	}
	
	public IDialogChoice<Save> loadSave(){
		return loadSave;
	}
}
