package model.dialogs;

import model.Save;
import model.persistence.SaveHistory;
import view.interfaces.IDialogChoice;

public class ChooseSaveDialog implements IDialogChoice<Save>{

	SaveHistory saveHistory;
	
	public ChooseSaveDialog(SaveHistory saveHistory) {
		this.saveHistory = saveHistory;
	}
	
	@Override
	public String getDialogTitle() {
		return "Load Saved State";
	}

	@Override
	public String getDialogText() {
		return "Select a save from below";
	}

	@Override
	public Save[] getDialogOptions() {
		return Save.values();
	}

	@Override
	public Save getCurrentSelection() {
		return saveHistory.getActiveSave();
	}

}
