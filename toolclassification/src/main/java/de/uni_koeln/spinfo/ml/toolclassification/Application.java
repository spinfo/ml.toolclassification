package de.uni_koeln.spinfo.ml.toolclassification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		List<Tool> values = new ArrayList<Tool>(di.getTools().values());
		CrossvalidationGroupBuilder<Tool> cvgb = new CrossvalidationGroupBuilder<Tool>(values, 10);
		for (TrainingTestSets<Tool> tts : cvgb) {
			System.out.println(tts.getTrainingSet().size()); //--> Build model / choose classifier
			System.out.println(tts.getTestSet().size()); //--> Classify 
			//--> Evaluate
			System.out.println();
		}
		//--> Calculate mean
	}

}
