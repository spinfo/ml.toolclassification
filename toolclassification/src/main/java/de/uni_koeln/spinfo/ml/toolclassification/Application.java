package de.uni_koeln.spinfo.ml.toolclassification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.uni_koeln.spinfo.ml.toolclassification.components.DataImporter;
import de.uni_koeln.spinfo.ml.toolclassification.components.crossvalidation.CrossvalidationGroupBuilder;
import de.uni_koeln.spinfo.ml.toolclassification.components.crossvalidation.TrainingTestSets;
import de.uni_koeln.spinfo.ml.toolclassification.data.Tool;

public class Application {

	public static void main(String[] args) throws IOException {
		System.out.println("Tool Classifying Application");
		System.out.println("----------------------------");
		DataImporter di = new DataImporter();
		di.parseToolsAndClassesFromFile("src/main/resources/data/DatenTools.tsv");
//		
//		Set<String> keySet = di.getTools().keySet();
//		for (String string : keySet) {
//			System.out.println(di.getTools().get(string).getParentClassId());
//		}
		
		List<Tool> tools = new ArrayList<Tool>(di.getTools().values());
		List<Tool> bufferList = new ArrayList<Tool>();
		for (Tool tool : tools) {
			if(tool.getContext()!=null){
				//System.out.println(tool.getContext());
				bufferList.add(tool);
			}
		}
		//System.out.println("BufferList size: " + bufferList.size());
		System.out.println("Before cleanup: " + tools.size());
		tools= bufferList;
		System.out.println("After cleanup: " + tools.size());
		
		CrossvalidationGroupBuilder<Tool> cvgb = new CrossvalidationGroupBuilder<Tool>(tools, 10);
		for (TrainingTestSets<Tool> tts : cvgb) {
			//System.out.println(tts.getTrainingSet().size()); //--> Build model / choose classifier
			
			//System.out.println(tts.getTestSet().size()); //--> Classify 
			//--> Evaluate
			System.out.println();
			
			performClassification(tts);
		}
		//--> Calculate mean
		//End
	}
	
	public static void performClassification(TrainingTestSets<Tool> tts){
		List<Tool> trainingSet = tts.getTrainingSet();	
		Map<Integer, Map<String,Integer>> classBoWs = new HashMap<Integer,  Map<String,Integer>>();  
		
		for (Tool tool : trainingSet) {
			int parentClassId = tool.getParentClassId();
			if(classBoWs.get(parentClassId)==null){
				classBoWs.put(parentClassId, new HashMap<String, Integer>());
			}
			String context = tool.getContext();
			String[] split = context.split("\\W+");
			Map<String, Integer> boW = classBoWs.get(parentClassId);
			for (String word : split) {
				Integer count = boW.get(word);
				if(count==null){
					count = 0;
				}
				count++;
				boW.put(word,count);
			}
		}
		Set<Integer> keySet = classBoWs.keySet();
		for (Integer integer : keySet) {
			System.out.println(classBoWs.get(integer).size());
		}
	}

}
