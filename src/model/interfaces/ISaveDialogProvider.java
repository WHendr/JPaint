package model.interfaces;

import model.Save;
import view.interfaces.IDialogChoice;

public interface ISaveDialogProvider {
	public IDialogChoice<Save> chooseSave();

	public IDialogChoice loadSave();
}
