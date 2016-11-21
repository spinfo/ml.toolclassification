package de.uni_koeln.spinfo.ml.toolclassification.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.uni_koeln.spinfo.ml.toolclassification.data.Tool;
import de.uni_koeln.spinfo.ml.toolclassification.data.ToolParentClass;
import de.uni_koeln.spinfo.ml.toolclassification.data.ToolSubClass;

public class DataImporter {
	
	private Map<String, Tool> tools;
	private Map<Integer, ToolParentClass> parentClasses;
	private Map<Character, ToolSubClass> subClasses;
	
	public DataImporter(){
		tools = new HashMap<String, Tool>();
		parentClasses = new HashMap<Integer, ToolParentClass>();
		subClasses = new HashMap<Character, ToolSubClass>();
	}
	
	/**
	 * Parses the specified file and aggregates contained tools and classes.
	 * @param filename File to parse
	 * @throws IOException 
	 */
	public void parseFile(String filename) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
		String nextLine = in.readLine();
		nextLine = in.readLine(); // skip first line
		int i=0; //line counter
		while(nextLine!=null){
			
			String[] fields = nextLine.split("\\t");
			try {
				String toolName = fields[0];
				String subClassName = fields[3];
				String classIdCombo = fields[2];
				char subClassId = classIdCombo.charAt(2);
				int parentClassId = Integer.parseInt(classIdCombo.charAt(1)+"");
				if(!tools.containsKey(toolName)){
					tools.put(toolName, new Tool(toolName, parentClassId, subClassId));
				}
				else{
					System.out.println("Duplicate Tool: " + toolName + " at position " + i);
				}
				i++;

			} catch (Exception e) {
				//e.printStackTrace();
				System.out.println("MALFORMED LINE: " + i++);
			}			
			nextLine = in.readLine();
		}
		
		System.out.println("Tool list count: " + tools.size());
	}
	

}
