package de.uni_koeln.spinfo.ml.toolclassification.data;


/**
 * A class to represent tools extracted from job ads
 * @author jhermes
 *
 */
public class Tool {
	private String name;
	private int parentClassId;
	private String subClassId;
	private String context;

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
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
