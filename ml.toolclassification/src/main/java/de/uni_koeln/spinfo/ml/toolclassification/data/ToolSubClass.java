package de.uni_koeln.spinfo.ml.toolclassification.data;

import java.util.ArrayList;
import java.util.List;

public class ToolSubClass {
	private String name;
	private char subClassID;
	private List<Tool> toolsList;
	
	public ToolSubClass(String name, char subClassID) {
		super();
		this.name = name;
		this.subClassID = subClassID;
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
	
	public char getSubClassID() {
		return subClassID;
	}

}
