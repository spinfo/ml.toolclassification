package de.uni_koeln.spinfo.ml.toolclassification;

import java.io.IOException;

import de.uni_koeln.spinfo.ml.toolclassification.components.DataImporter;

public class Application {

	public static void main(String[] args) throws IOException {
		System.out.println("Tool Classifying Application");
		System.out.println("----------------------------");
		DataImporter di = new DataImporter();
		di.parseToolsAndClassesFromFile("src/main/resources/data/DatenTools.tsv");
		
		System.out.println(di.getTools().size());
	}

}
