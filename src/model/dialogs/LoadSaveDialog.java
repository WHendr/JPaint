package model.dialogs;

import model.Save;
import model.persistence.SaveHistory;
import view.interfaces.IDialogChoice;

public class LoadSaveDialog implements IDialogChoice<Save> {

	SaveHistory saveHistory;
	
	public LoadSaveDialog(SaveHistory saveHistory) {
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
