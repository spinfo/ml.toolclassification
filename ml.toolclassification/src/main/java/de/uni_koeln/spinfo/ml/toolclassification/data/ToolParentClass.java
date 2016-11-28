package de.uni_koeln.spinfo.ml.toolclassification.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent rough-grained tool classes 
 * @author jhermes
 *
 */
public class ToolParentClass {
	private String name;
	private int classID;
	private List<ToolSubClass> subclasses;
	
	public ToolParentClass(String name, int classID) {
		super();
		this.name = name;
		this.classID = classID;
		subclasses = new ArrayList<ToolSubClass>();
	}
	
	public List<ToolSubClass> getSubclasses() {
		return subclasses;
	}
	public void addSubclass(ToolSubClass subclass) {
		this.subclasses.add(subclass);
	}
	public String getName() {
		return name;
	}
	public int getClassID() {
		return classID;
	}
	
}
