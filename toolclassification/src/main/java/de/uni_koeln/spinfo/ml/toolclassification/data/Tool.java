package de.uni_koeln.spinfo.ml.toolclassification.data;


/**
 * A class to represent tools extracted from job ads
 * @author jhermes
 *
 */
public class Tool {
	private String label;
	private int parentClassId;
	private String subClassId;
	private String context;

	/**
	 * Initializes a new Tool with specified features
	 * @param label Label of the Tool
	 * @param parentClassId The parent class of the Tool (integer value between 1-8)
	 * @param subClassId The subordinate class of the Toole (parent class PLUS a character)
	 */
	public Tool(String label, int parentClassId, String subClassId) {
		super();
		this.label = label;
		this.parentClassId = parentClassId;
		this.subClassId = subClassId;
	}

	public String getName() {
		return getLabel();
	}
	
	public String getLabel() {
		return label;
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
