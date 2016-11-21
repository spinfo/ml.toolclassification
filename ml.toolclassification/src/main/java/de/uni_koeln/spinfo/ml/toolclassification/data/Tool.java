package de.uni_koeln.spinfo.ml.toolclassification.data;

public class Tool {
	private String name;
	private int parentClassId;
	private char subClassId;

	public Tool(String name, int parentClassId, char subClassId) {
		super();
		this.name = name;
		this.parentClassId = parentClassId;
		this.subClassId = subClassId;
	}

	public String getName() {
		return name;
	}

	public int getParentClassId() {
		return parentClassId;
	}

	public char getSubClassId() {
		return subClassId;
	}
	
	
}
