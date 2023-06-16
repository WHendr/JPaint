package design.lists;

import java.util.ArrayList;

public class MasterList{
	
	private static MasterList masterList;
	

	private static ShapeList shapeList;
	private static SelectedList selectedList;
	private static Clipboard clipboard;
	private static GroupList groupList;
	
	private MasterList() {
		//System.out.println("Hello");
		this.shapeList = new ShapeList();
		this.selectedList = new SelectedList();
		this.clipboard = new Clipboard();
		this.groupList = new GroupList();
	}
	
	public ShapeList getShapeList() {
		return this.shapeList;
	}
	
	public SelectedList getSelectedList() {
		return this.selectedList;
	}
	
	public Clipboard getClipboard() {
		return this.clipboard;
	}
	
	public GroupList getGroupList() {
		return this.groupList;
	}
	
	
	public static MasterList getInstance(){
		if(masterList == null) {
			masterList = new MasterList();
		}
		return masterList;
	}
	
	public void clear() {
		shapeList.clear();;
		selectedList.clear();
		clipboard.clear();
		groupList.clear();
	}
	
}
