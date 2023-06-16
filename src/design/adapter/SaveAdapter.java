package design.adapter;

import design.interfaces.IAdapter;
import model.Save;
import model.persistence.SaveFile;

public class SaveAdapter implements IAdapter{
	
	//SaveFile saveFile;
	//private String saveFile;
	private Save save;
	
	
	public SaveAdapter(Save save){
		this.save = save;
	}
	
	@Override
	public <T> String adapt(Object generic) {
		return generic.toString();
	}
	
	public int getSaveFile() {
		String saveFile = adapt(save);
		
		Save[] saves = Save.values();
		int i = 0;
		for(Save savef : saves) {
			if(savef.toString() == saveFile) {
				break;
			}
			i++;
		}
		return i;
	}

}
