package de.uni_koeln.spinfo.ml.toolclassification.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.security.sasl.SaslException;

import de.uni_koeln.spinfo.ml.toolclassification.data.Tool;
import de.uni_koeln.spinfo.ml.toolclassification.data.ToolParentClass;
import de.uni_koeln.spinfo.ml.toolclassification.data.ToolSubClass;

/**
 * A class to import data from specified tools-classes-files to the ml.toolclassification framework
 * @author jhermes
 *
 */
public class DataImporter {
	
	private Map<String, Tool> tools;
	private Map<Integer, ToolParentClass> parentClasses;
	private Map<String, ToolSubClass> subClasses;
	
	/**
	 * Creates a new Data Importer
	 */
	public DataImporter(){
		tools = new HashMap<String, Tool>();
		parentClasses = new HashMap<Integer, ToolParentClass>();
		subClasses = new HashMap<String, ToolSubClass>();
	}
	
	/**
	 * Parses the specified file and aggregates contained tools and classes.
	 * @param filename File to parse
	 * @throws IOException 
	 */
	public void parseToolsAndClassesFromFile(String filename) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
		String nextLine = in.readLine();
		nextLine = in.readLine(); // skip first line
		int i=0; //line counter
		int contextfound = 0;
		while(nextLine!=null){
			
			String[] fields = nextLine.split("\\t");
			
			try {
				// Find information
				String toolName = fields[0];
				String subClassName = fields[3];
				String subClassID = fields[2];
				String context = fields[11];
				int parentClassID = Integer.parseInt(subClassID.charAt(1)+"");
				
				//Add tool to tool list
				if(!tools.containsKey(toolName)){
					Tool tool = new Tool(toolName, parentClassID, subClassID);
					if(!context.startsWith("NA")){
						tool.setContext(context);
						contextfound++;
					}
					tools.put(toolName, tool);
					
				}
				else{
					
					//Check data integrity!
					Tool inList = tools.get(toolName);
					String inListSCID = inList.getSubClassId();
					if(inListSCID.equals(subClassID)){
						//deleted, because there weren't any new contexts in the file
//						String inListContext = inList.getContext();
//						System.out.println(inListContext);
//						inListContext += "\n" + context;
//						System.out.println(context);
						
					}
					else{
						//System.out.println("Duplicate Tool: " + toolName + " at position " + i + " with unclear subClassID");
					}
				}
				
				//Add subClass to subclassList
				if(!subClasses.containsKey(subClassID)){
					subClasses.put(subClassID, new ToolSubClass(subClassName, subClassID, parentClassID));
				}
				else{
					//Check data integrity!
					ToolSubClass inList = subClasses.get(subClassID);
					int tpcID = inList.getParentClassID();
					if(parentClassID!=tpcID){
						System.out.println("Data Integrity Error");
					}
				}				
				i++;

			} catch (Exception e) {
				//e.printStackTrace();
				//System.out.println("MALFORMED LINE: " + i++);
			}			
			nextLine = in.readLine();
		}		
		System.out.println("Tool list count: " + tools.size());
		System.out.println("Contexts found: " + contextfound);
		System.out.println("Subclasses list count: " + subClasses.size());
		Set<String> keySet = subClasses.keySet();
		for (String subClassID : keySet) {
			ToolSubClass toolSubClass = subClasses.get(subClassID);
			int parentClassID = toolSubClass.getParentClassID();
			ToolParentClass tpc = parentClasses.get(parentClassID);
			if(tpc==null){
				tpc = new ToolParentClass("NONE", parentClassID);
			}
			tpc.addSubclass(toolSubClass);
			parentClasses.put(parentClassID, tpc);
		}
		System.out.println("Parentclasses list count: " + parentClasses.size());
		
	}
}
