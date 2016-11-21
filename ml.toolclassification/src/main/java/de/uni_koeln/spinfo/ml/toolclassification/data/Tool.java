package de.uni_koeln.spinfo.ml.toolclassification.data;


public class Tool {
	private String name;
	private int parentClassId;
	private String subClassId;

	public Tool(String name, int parentClassId, String subClassId) {
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

	public String getSubClassId() {
		return subClassId;
	}
	
	
}
