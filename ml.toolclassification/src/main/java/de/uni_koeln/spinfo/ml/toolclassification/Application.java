package de.uni_koeln.spinfo.ml.toolclassification;

import java.io.IOException;

import de.uni_koeln.spinfo.ml.toolclassification.components.DataImporter;

public class Application {

	public static void main(String[] args) throws IOException {
		System.out.println("Tool Classifying Application");
		DataImporter di = new DataImporter();
		di.parseFile("src/main/resources/data/DatenTools.tsv");
	}

}
