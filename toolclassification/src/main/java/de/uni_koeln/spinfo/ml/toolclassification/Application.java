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
import de.uni_koeln.spinfo.ml.toolclassification.data.BayesModel;
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
		int cvgroups = 10;
		CrossvalidationGroupBuilder<Tool> cvgb = new CrossvalidationGroupBuilder<Tool>(tools, cvgroups);
		double overallResult = 0.0;
		for (TrainingTestSets<Tool> tts : cvgb) {
			//System.out.println(tts.getTrainingSet().size()); //--> Build model / choose classifier
			
			//System.out.println(tts.getTestSet().size()); //--> Classify 
			//--> Evaluate
			System.out.println();
			
			double singleResult = performClassification(tts);
			overallResult += singleResult;
		}
		//--> Calculate mean
		overallResult = overallResult/cvgroups;
		System.out.println("Overall accuracy: " + overallResult);
		
		//End
	}
	
	private static double performClassification(TrainingTestSets<Tool> tts) {
		
		BayesModel bayes = new BayesModel();
		
		//Train
		List<Tool> trainingSet = tts.getTrainingSet();
		for (Tool tool : trainingSet) {
			String context = tool.getContext();
			String[] words = context.split("\\W+");
			for (String word : words) {
				bayes.addWordToClass(word, tool.getParentClassId());
			}			
		}
		
		int matches = 0;
		int misses = 0;
		int notClassified = 0;
		
		//Test
		List<Tool> testSet = tts.getTestSet();
		for (Tool tool : testSet) {
			String context = tool.getContext();
			String[] words = context.split("\\W+");
			
			List<String> features = new ArrayList<String>();
			for (String string : words) {
				features.add(string);
			}
			
			int guessedClass = bayes.getClassification(features);
			int realClass = tool.getParentClassId();
			//System.out.println(guessedClass + " " + realClass);
			
			if(guessedClass==0){
				notClassified++;
			}
			else{
				if(guessedClass==realClass){
					matches++;
				}
				else{
					misses++;
				}
			}
		}
			
		System.out.println("Not classified: " + notClassified);
		System.out.println("Correctly classified: " + matches);
		System.out.println("Incorrectly classified: " + misses);
		double accuracy = matches/((double)matches+misses);
		System.out.println("Accuracy: " + accuracy);
		System.out.println();
		return accuracy;
	}


}
