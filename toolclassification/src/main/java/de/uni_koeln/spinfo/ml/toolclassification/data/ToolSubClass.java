package de.uni_koeln.spinfo.ml.toolclassification.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent fine-grained tool classes
 * @author jhermes
 *
 */
public class ToolSubClass {
	private String name;
	private String  subClassID;
	private int parentClassID;
	private List<Tool> toolsList;
	
	public ToolSubClass(String name, String subClassID, int parentClassID) {
		super();
		this.name = name;
		this.subClassID = subClassID;
		this.parentClassID = parentClassID;
		this.toolsList = new ArrayList<Tool>();
	}
	
	public List<Tool> getToolsList() {
		return toolsList;
	}
	
	public void addToolToList(Tool toAdd) {
		this.toolsList.add(toAdd);
	}
	
	public String getName() {
		return name;
	}
	
	public String getSubClassID() {
		return subClassID;
	}

	public int getParentClassID() {
		return parentClassID;
	}
}
