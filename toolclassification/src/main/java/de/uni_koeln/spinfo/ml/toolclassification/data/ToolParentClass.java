package de.uni_koeln.spinfo.ml.toolclassification.data;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent rough-grained tool classes 
 * @author jhermes
 *
 */
public class ToolParentClass {
	private String name;
	private int classID;
	private Set<ToolSubClass> subclasses;
	
	public ToolParentClass(String name, int classID) {
		super();
		this.name = name;
		this.classID = classID;
		subclasses = new HashSet<ToolSubClass>();
	}
	
	public Set<ToolSubClass> getSubclasses() {
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
